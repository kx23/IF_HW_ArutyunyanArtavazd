package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage extends BasePage<LoginPage> {

    private final SelenideElement usernameInput= $x("//input[@id='login-form-username']").as("Поле ввода юзернейма");
    private final SelenideElement passwordInput= $x("//input[@id='login-form-password']").as("Поле ввода пароля");
    private final SelenideElement submitButton= $x("//input[@id='login-form-submit']").as("Кнопка входа");


    @Override
    protected String getPageUrl() {
        return "/login.jsp";
    }

    @Step("Ввести имя пользователя: {username}")
    public LoginPage enterUsername(String username) {
        usernameInput.setValue(username);
        return this;
    }

    @Step("Ввести пароль")
    public LoginPage enterPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }

    @Step("Нажать кнопку входа")
    public LoginPage clickSubmit() {
        submitButton.click();
        return this;
    }

    @Step("Выполнить вход под пользователем {username}")
    public void login(String username, String password) {
        enterUsername(username)
                .enterPassword(password)
                .clickSubmit();
    }
}
