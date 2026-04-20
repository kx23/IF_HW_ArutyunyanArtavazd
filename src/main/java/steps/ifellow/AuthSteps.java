package steps.ifellow;

import api.ifellow.AuthApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.ifellow.Credentials;
import io.restassured.response.ValidatableResponse;

import java.io.InputStream;

public class AuthSteps {

    private static final AuthApi authApi = new AuthApi();
    private static final ObjectMapper mapper = new ObjectMapper();

    // Читает credentials.json из classpath
    public Credentials readCredentials() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("credentials.json")) {
            if (is == null) throw new IllegalStateException("credentials.json not found in resources");
            return mapper.readValue(is, Credentials.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to read credentials.json", e);
        }
    }

    public ValidatableResponse register(Credentials credentials) {
        return authApi.register(credentials);
    }

    public ValidatableResponse loginWithUnknownUser(Credentials credentials) {
        Credentials modified = new Credentials();
        modified.setUsername("unknown_user_xzxz");
        modified.setPassword(credentials.getPassword());
        return authApi.login(modified);
    }

    public ValidatableResponse loginWithWrongPassword(Credentials credentials) {
        Credentials modified = new Credentials();
        modified.setUsername(credentials.getUsername());
        modified.setPassword("wrongpassword999");
        return authApi.login(modified);
    }

    public ValidatableResponse loginSuccess(Credentials credentials) {
        return authApi.login(credentials);
    }

    public ValidatableResponse logoutWithInvalidToken() {
        return authApi.logout("00000000-0000-0000-0000-000000000000");
    }

    public ValidatableResponse logoutSuccess(String token) {
        return authApi.logout(token);
    }
}