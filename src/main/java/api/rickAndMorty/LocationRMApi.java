package api.rickAndMorty;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class LocationRMApi extends BaseRMApi {

    private static final String LOCATION_URN = "/location";

    public ValidatableResponse getLocation(long id) {
        return given()
                .when()
                .get(LOCATION_URN + "/" + id)
                .then();
    }
}
