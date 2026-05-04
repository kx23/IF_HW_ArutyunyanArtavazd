package steps.rickAndMorty;

import api.rickAndMorty.CharacterRMApi;
import dto.rickAndMorty.Character;
import dto.rickAndMorty.CharacterFilter;
import io.qameta.allure.Step;
import org.apache.http.HttpStatus;

public class CharacterSteps {

    private final CharacterRMApi charApi = new CharacterRMApi();

    @Step("Получить персонажа по ID: {id}")
    public Character getCharacterById(long id) {
        return charApi.getCharacter(id)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(Character.class);
    }

    @Step("Получить персонажа по имени: {name}")
    public CharacterFilter getCharacterByName(String name) {
        return charApi.getCharacterByName(name)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(CharacterFilter.class);
    }
}
