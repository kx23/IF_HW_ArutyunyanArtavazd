package steps;

import io.qameta.allure.Param;
import io.qameta.allure.Step;
import io.qameta.allure.model.Parameter;
import pages.LoginPage;
import pages.components.HeaderComponent;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginSteps {

    private final LoginPage loginPage = new LoginPage();
    private final HeaderComponent headerComponent = new HeaderComponent();

    @Step("Открыть страницу входа")
    public LoginSteps openLoginPage() {
        loginPage.open();
        return this;
    }

    @Step("Выполнить вход под пользователем {username}")
    public LoginSteps login(String username, @Param(mode = Parameter.Mode.MASKED) String password) {
        loginPage.enterUsername(username)
                .enterPassword(password)
                .clickSubmit();
        return this;
    }

    @Step("Проверить, что в систему вошёл пользователь: {expectedUser}")
    public LoginSteps verifyUserLoggedIn(String expectedUser) {
        assertEquals(
                expectedUser,
                headerComponent.getLoggedInUser(),
                "Залогиненный пользователь должен быть: " + expectedUser
        );
        return this;
    }
}
