package pages;

import com.codeborne.selenide.SelenideElement;

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

    public LoginPage enterUsername(String username) {
        usernameInput.setValue(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }

    public LoginPage clickSubmit() {
        submitButton.click();
        return this;
    }

    public void login(String username, String password) {
        enterUsername(username)
                .enterPassword(password)
                .clickSubmit();
    }
}
