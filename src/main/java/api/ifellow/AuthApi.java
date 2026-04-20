package api.ifellow;

import dto.ifellow.Credentials;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class AuthApi extends BaseIfellowApi {

    private static final String REGISTER = "/register";
    private static final String LOGIN = "/login";
    private static final String LOGOUT = "/logout";

    public ValidatableResponse register(Credentials credentials) {
        return given()
                .body(credentials)
                .when()
                .post(REGISTER)
                .then();
    }

    public ValidatableResponse login(Credentials credentials) {
        return given()
                .body(credentials)
                .when()
                .post(LOGIN)
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