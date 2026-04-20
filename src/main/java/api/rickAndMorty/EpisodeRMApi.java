package api.rickAndMorty;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class EpisodeRMApi extends BaseRMApi {

    private static final String EPISODE_URN = "/episode";

    public ValidatableResponse getEpisode(long id) {
        return given()
                .when()
                .get(EPISODE_URN + "/" + id)
                .then();
    }
}
