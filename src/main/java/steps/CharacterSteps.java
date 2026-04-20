package steps;

import api.rickAndMorty.CharacterRMApi;
import dto.Character;
import dto.CharacterFilter;
import org.apache.http.HttpStatus;

public class CharacterSteps {

    private static final CharacterRMApi charApi = new CharacterRMApi();


    public Character getCharacterById(long id) {
        return charApi.getCharacter(id)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(Character.class);
    }

    public CharacterFilter getCharacterByName(String name) {
        return charApi.getCharacterByName(name)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(CharacterFilter.class);
    }
}
