package cucumber.steps;

import dto.rickAndMorty.Character;
import dto.rickAndMorty.CharacterFilter;
import dto.rickAndMorty.Episode;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import steps.rickAndMorty.CharacterSteps;
import steps.rickAndMorty.EpisodeSteps;
import utils.ScenarioContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RickAndMortySteps {

    private static final Logger log = LoggerFactory.getLogger(RickAndMortySteps.class);

    private final CharacterSteps characterSteps = new CharacterSteps();
    private final EpisodeSteps episodeSteps = new EpisodeSteps();

    @Given("персонаж {string} найден в API")
    public void characterIsFoundInApi(String name) {
        CharacterFilter filter = characterSteps.getCharacterByName(name);

        assertNotNull(filter.getResults(), "Character search returned null results for: " + name);
        assertFalse(filter.getResults().isEmpty(), "Character search returned empty list for: " + name);

        Character character = filter.getResults().get(0);
        log.info("Found character: id={}, name={}, species={}, location={}",
                character.getId(), character.getName(),
                character.getSpecies(), character.getLocation().getName());

        ScenarioContext.put(ScenarioContext.CHARACTER, character);
    }

    @When("я нахожу последний эпизод персонажа {string}")
    public void iFindTheLastEpisodeOf(String characterName) {
        Character character = ScenarioContext.get(ScenarioContext.CHARACTER);

        List<String> episodes = character.getEpisode();
        assertFalse(episodes.isEmpty(), characterName + " has no episodes");

        long lastEpisodeId = extractId(episodes.get(episodes.size() - 1));
        Episode lastEpisode = episodeSteps.getEpisodeById(lastEpisodeId);

        log.info("Last episode: id={}, code={}, name={}",
                lastEpisode.getId(), lastEpisode.getEpisode(), lastEpisode.getName());

        ScenarioContext.put(ScenarioContext.LAST_EPISODE, lastEpisode);
    }

    @And("я нахожу последнего персонажа этого эпизода")
    public void iFindTheLastCharacterOfThatEpisode() {
        Episode episode = ScenarioContext.get(ScenarioContext.LAST_EPISODE);

        List<String> chars = episode.getCharacters();
        assertFalse(chars.isEmpty(), "Episode has no characters");

        long lastCharId = extractId(chars.get(chars.size() - 1));
        Character lastChar = characterSteps.getCharacterById(lastCharId);

        log.info("Last character in episode: id={}, name={}, species={}, location={}",
                lastChar.getId(), lastChar.getName(),
                lastChar.getSpecies(), lastChar.getLocation().getName());

        ScenarioContext.put(ScenarioContext.LAST_CHAR, lastChar);
    }

    @Then("детали последнего персонажа залогированы и сравнены с {string}")
    public void lastCharacterDetailsAreLoggedAndComparedTo(String characterName) {
        Character original = ScenarioContext.get(ScenarioContext.CHARACTER);
        Character lastChar  = ScenarioContext.get(ScenarioContext.LAST_CHAR);

        log.info("Same species as {}: {}", characterName,
                original.getSpecies().equals(lastChar.getSpecies()));
        log.info("Same location as {}: {}", characterName,
                original.getLocation().getName().equals(lastChar.getLocation().getName()));
    }


    private long extractId(String url) {
        String[] parts = url.split("/");
        return Long.parseLong(parts[parts.length - 1]);
    }
}
