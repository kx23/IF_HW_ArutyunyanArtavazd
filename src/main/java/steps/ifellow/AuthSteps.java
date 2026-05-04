package steps.ifellow;

import api.ifellow.AuthApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.ifellow.Credentials;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.io.InputStream;

public class AuthSteps {

    private static final String REGISTER_URL = "/register";
    private static final String LOGIN_URL    = "/login";
    private static final ObjectMapper mapper = new ObjectMapper();

    private final AuthApi authApi = new AuthApi();

    @Step("Прочитать учётные данные из конфига")
    public Credentials readCredentials() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("Credentials.json")) {
            if (is == null) throw new IllegalStateException("Credentials.json not found in resources");
            return mapper.readValue(is, Credentials.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to read Credentials.json", e);
        }
    }

    @Step("Зарегистрировать пользователя")
    public ValidatableResponse register(Credentials credentials) {
        return authApi.postUserCredentialsToUrl(credentials, REGISTER_URL);
    }

    @Step("Войти с несуществующим именем пользователя")
    public ValidatableResponse loginWithUnknownUser(Credentials credentials) {
        Credentials modified = new Credentials();
        modified.setUsername("unknown_user_xzxz");
        modified.setPassword(credentials.getPassword());
        return authApi.postUserCredentialsToUrl(modified, LOGIN_URL);
    }

    @Step("Войти с неверным паролем")
    public ValidatableResponse loginWithWrongPassword(Credentials credentials) {
        Credentials modified = new Credentials();
        modified.setUsername(credentials.getUsername());
        modified.setPassword("wrongpassword999");
        return authApi.postUserCredentialsToUrl(modified, LOGIN_URL);
    }

    @Step("Войти с корректными учётными данными")
    public ValidatableResponse loginSuccess(Credentials credentials) {
        return authApi.postUserCredentialsToUrl(credentials, LOGIN_URL);
    }

    @Step("Выйти с невалидным токеном")
    public ValidatableResponse logoutWithInvalidToken() {
        return authApi.logout("00000000-0000-0000-0000-000000000000");
    }

    @Step("Выйти с валидным токеном")
    public ValidatableResponse logoutSuccess(String token) {
        return authApi.logout(token);
    }
}
