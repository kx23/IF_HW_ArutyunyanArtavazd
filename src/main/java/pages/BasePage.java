package pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

public abstract class BasePage<T extends BasePage<T>> {

    protected abstract String getPageUrl();

    @Step("Открыть страницу: {getPageUrl()}")
    public T open() {
        Selenide.open(getPageUrl());
        return (T) this;
    }
}