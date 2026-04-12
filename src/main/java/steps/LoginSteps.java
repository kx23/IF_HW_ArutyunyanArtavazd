package steps;

import data.TestData;
import io.cucumber.java.ru.*;
import pages.LoginPage;
import pages.RapidBoardPage;
import data.ConfigReader;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginSteps {
    private final LoginPage loginPage = new LoginPage();
    private final RapidBoardPage rapidBoardPage = new RapidBoardPage();

    @Дано("открыта страница авторизации")
    public void openLoginPage() {
        loginPage.open();
    }

    @Когда("пользователь авторизуется")
    public void login() {
        loginPage.login(
                ConfigReader.get("user.login"),
                ConfigReader.get("user.password")
        );
    }

    @Тогда("в шапке отображается авторизованный пользователь")
    public void verifyLoggedInUser() {
        assertEquals(
                TestData.VALID_USER,
                rapidBoardPage.header.getLoggedInUser()
        );
    }
}