package ifellowTests;

import dto.ifellow.Credentials;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import steps.ifellow.AuthSteps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthTest {

    private static final Logger log = LoggerFactory.getLogger(AuthTest.class);

    private static final AuthSteps authSteps = new AuthSteps();
    private static final Credentials credentials = authSteps.readCredentials();
    private static String token;

    @Test
    @Order(1)
    @DisplayName("Register - success")
    void registerTest() {
        String body = authSteps.register(credentials)
                .statusCode(200)
                .extract().asString();

        log.info("Register response: {}", body);
        assertEquals("success register", body);
    }

    @Test
    @Order(2)
    @DisplayName("Login - user not found")
    void loginUserNotFoundTest() {
        String body = authSteps.loginWithUnknownUser(credentials)
                .statusCode(401)
                .extract().asString();

        log.info("Login (unknown user) response: {}", body);
        assertEquals("not found", body);
    }

    @Test
    @Order(3)
    @DisplayName("Login - wrong password")
    void loginWrongPasswordTest() {
        String body = authSteps.loginWithWrongPassword(credentials)
                .statusCode(401)
                .extract().asString();

        log.info("Login (wrong password) response: {}", body);
        assertEquals("not right pass", body);
    }

    @Test
    @Order(4)
    @DisplayName("Login - success, token received")
    void loginSuccessTest() {
        String body = authSteps.loginSuccess(credentials)
                .statusCode(200)
                .extract().asString();

        log.info("Login (success) response: {}", body);
        assertTrue(body.startsWith("token : "), "Response should start with 'token : '");

        token = body.replace("token : ", "").trim();
        log.info("Token saved: {}", token);
    }

    @Test
    @Order(5)
    @DisplayName("Logout - unauthorized (invalid token)")
    void logoutUnauthorizedTest() {
        String body = authSteps.logoutWithInvalidToken()
                .statusCode(401)
                .extract().asString();

        log.info("Logout (invalid token) response: {}", body);
        assertEquals("not found", body);
    }

    @Test
    @Order(6)
    @DisplayName("Logout - success")
    void logoutSuccessTest() {
        String body = authSteps.logoutSuccess(token)
                .statusCode(200)
                .extract().asString();

        log.info("Logout (success) response: {}", body);
        assertEquals("success logout", body);
    }
}