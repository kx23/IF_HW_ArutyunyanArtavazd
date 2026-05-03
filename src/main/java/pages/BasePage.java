package pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;

public abstract class BasePage<T extends BasePage<T>> {

    protected abstract String getPageUrl();

    public T open() {
        Allure.step("Открыть страницу" , () ->
                Selenide.open(getPageUrl())
        );
        return (T) this;
    }
}