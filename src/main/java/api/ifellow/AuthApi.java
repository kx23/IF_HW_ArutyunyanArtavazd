package api.ifellow;

import dto.ifellow.Credentials;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class AuthApi extends BaseIfellowApi {

    private static final String LOGOUT = "/logout";

    public ValidatableResponse postUserCredentialsToUrl(Credentials credentials, String url) {
        return given()
                .body(credentials)
                .when()
                .post(url)
                .then();
    }

    public ValidatableResponse logout(String token) {
        return given()
                .header("Authorization", token)
                .when()
                .get(LOGOUT)
                .then();
    }
}