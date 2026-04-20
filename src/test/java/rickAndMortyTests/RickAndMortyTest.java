package rickAndMortyTests;

import dto.rickAndMorty.Character;
import dto.rickAndMorty.CharacterFilter;
import dto.rickAndMorty.Episode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import steps.rickAndMorty.CharacterSteps;
import steps.rickAndMorty.EpisodeSteps;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class RickAndMortyTest {

    private static final Logger log = LoggerFactory.getLogger(RickAndMortyTest.class);

    private final CharacterSteps characterSteps = new CharacterSteps();
    private final EpisodeSteps episodeSteps = new EpisodeSteps();

    @Test
    @DisplayName("Compare last episode's last character with Morty Smith")
    void lastCharacterOfMortysLastEpisodeTest() {

        CharacterFilter mortyFilter = characterSteps.getCharacterByName("Morty Smith");
        assertNotNull(mortyFilter.getResults(), "Character search returned no results");
        assertFalse(mortyFilter.getResults().isEmpty(), "Character search returned no results");

        Character morty = mortyFilter.getResults().get(0);
        log.info("Found character: id={}, name={}, species={}, location={}",
                morty.getId(), morty.getName(), morty.getSpecies(), morty.getLocation().getName());

        List<String> mortyEpisodes = morty.getEpisode();
        assertFalse(mortyEpisodes.isEmpty(), "Morty has no episodes");

        long lastEpisodeId = extractId(mortyEpisodes.get(mortyEpisodes.size() - 1));
        Episode lastEpisode = episodeSteps.getEpisodeById(lastEpisodeId);
        log.info("Last episode: id={}, code={}, name={}", lastEpisode.getId(), lastEpisode.getEpisode(), lastEpisode.getName());

        List<String> episodeChars = lastEpisode.getCharacters();
        assertFalse(episodeChars.isEmpty(), "Episode has no characters");

        long lastCharId = extractId(episodeChars.get(episodeChars.size() - 1));
        Character lastChar = characterSteps.getCharacterById(lastCharId);
        log.info("Last character in episode: id={}, name={}, species={}, location={}",
                lastChar.getId(), lastChar.getName(), lastChar.getSpecies(), lastChar.getLocation().getName());

        log.info("Same species as Morty: {}", morty.getSpecies().equals(lastChar.getSpecies()));
        log.info("Same location as Morty: {}", morty.getLocation().getName().equals(lastChar.getLocation().getName()));
    }

    private long extractId(String url) {
        String[] parts = url.split("/");
        return Long.parseLong(parts[parts.length - 1]);
    }
}