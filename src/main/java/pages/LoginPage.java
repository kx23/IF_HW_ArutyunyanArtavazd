package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Param;
import io.qameta.allure.model.Parameter;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.SetValueOptions.withText;

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

    public LoginPage enterPassword(@Param(mode= Parameter.Mode.MASKED) String password) {
        passwordInput.setValue(withText(password).sensitive());
        return this;
    }

    public LoginPage clickSubmit() {
        submitButton.click();
        return this;
    }

}
