package api.rickAndMorty;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CharacterRMApi extends BaseRMApi {

    private static final String CHARACTER_URN = "/character";

    public ValidatableResponse getCharacter(long id) {
        return given()
                .when()
                .get(CHARACTER_URN + "/" + id)
                .then();
    }

    public ValidatableResponse getCharacterByName(String name) {
        return given()
                .queryParam("name", name)
                .when()
                .get(CHARACTER_URN)
                .then();
    }
}
