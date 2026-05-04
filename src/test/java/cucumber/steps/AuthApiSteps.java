package cucumber.steps;

import dto.ifellow.Credentials;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.ValidatableResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import steps.ifellow.AuthSteps;
import utils.ScenarioContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthApiSteps {

    private static final Logger log = LoggerFactory.getLogger(AuthApiSteps.class);

    private final AuthSteps authSteps = new AuthSteps();


    @Given("учётные данные загружены из конфига")
    public void credentialsAreLoadedFromConfig() {
        Credentials credentials = authSteps.readCredentials();
        ScenarioContext.put(ScenarioContext.CREDENTIALS, credentials);
    }

    @Given("пользователь предварительно зарегистрирован")
    public void userIsPreRegistered() {
        Credentials credentials = ScenarioContext.get(ScenarioContext.CREDENTIALS);
        authSteps.register(credentials).statusCode(200);
    }

    @And("пользователь вошёл в систему и токен сохранён")
    public void userIsLoggedInAndTokenIsSaved() {
        Credentials credentials = ScenarioContext.get(ScenarioContext.CREDENTIALS);
        String body = authSteps.loginSuccess(credentials)
                .statusCode(200)
                .extract().asString();

        assertTrue(body.startsWith("token : "), "Login response should start with 'token : '");
        String token = body.replace("token : ", "").trim();
        ScenarioContext.put(ScenarioContext.TOKEN, token);
        log.info("Token saved: {}", token);
    }

    @When("пользователь регистрируется")
    public void userRegisters() {
        Credentials credentials = ScenarioContext.get(ScenarioContext.CREDENTIALS);
        ValidatableResponse response = authSteps.register(credentials);
        ScenarioContext.put(ScenarioContext.RESPONSE, response);
    }

    @When("пользователь входит с несуществующим именем пользователя")
    public void userLogsInWithUnknownUsername() {
        Credentials credentials = ScenarioContext.get(ScenarioContext.CREDENTIALS);
        ValidatableResponse response = authSteps.loginWithUnknownUser(credentials);
        ScenarioContext.put(ScenarioContext.RESPONSE, response);
    }

    @When("пользователь входит с неверным паролем")
    public void userLogsInWithWrongPassword() {
        Credentials credentials = ScenarioContext.get(ScenarioContext.CREDENTIALS);
        ValidatableResponse response = authSteps.loginWithWrongPassword(credentials);
        ScenarioContext.put(ScenarioContext.RESPONSE, response);
    }

    @When("пользователь входит с корректными учётными данными")
    public void userLogsInWithValidCredentials() {
        Credentials credentials = ScenarioContext.get(ScenarioContext.CREDENTIALS);
        ValidatableResponse response = authSteps.loginSuccess(credentials);
        ScenarioContext.put(ScenarioContext.RESPONSE, response);
    }

    @When("пользователь выходит с невалидным токеном")
    public void userLogsOutWithInvalidToken() {
        ValidatableResponse response = authSteps.logoutWithInvalidToken();
        ScenarioContext.put(ScenarioContext.RESPONSE, response);
    }

    @When("пользователь выходит с валидным токеном")
    public void userLogsOutWithValidToken() {
        String token = ScenarioContext.get(ScenarioContext.TOKEN);
        ValidatableResponse response = authSteps.logoutSuccess(token);
        ScenarioContext.put(ScenarioContext.RESPONSE, response);
    }

    @Then("тело ответа равно {string}")
    public void responseBodyIs(String expectedBody) {
        ValidatableResponse response = ScenarioContext.get(ScenarioContext.RESPONSE);
        String body = response.statusCode(200).extract().asString();
        log.info("Response body: {}", body);
        assertEquals(expectedBody, body);
    }

    @Then("статус ответа 401 и тело равно {string}")
    public void responseStatusIs401AndBodyIs(String expectedBody) {
        ValidatableResponse response = ScenarioContext.get(ScenarioContext.RESPONSE);
        String body = response.statusCode(401).extract().asString();
        log.info("Response body: {}", body);
        assertEquals(expectedBody, body);
    }

    @Then("статус ответа 200 и тело начинается с {string}")
    public void responseStatusIs200AndBodyStartsWith(String prefix) {
        ValidatableResponse response = ScenarioContext.get(ScenarioContext.RESPONSE);
        String body = response.statusCode(200).extract().asString();
        log.info("Login response: {}", body);
        assertTrue(body.startsWith(prefix),
                "Response should start with '" + prefix + "', but was: " + body);
    }

    @Then("статус ответа 200 и тело равно {string}")
    public void responseStatusIs200AndBodyIs(String expectedBody) {
        ValidatableResponse response = ScenarioContext.get(ScenarioContext.RESPONSE);
        String body = response.statusCode(200).extract().asString();
        log.info("Response body: {}", body);
        assertEquals(expectedBody, body);
    }
}
