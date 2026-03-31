package pages;

import com.codeborne.selenide.Selenide;

public abstract class BasePage<T extends BasePage<T>> {

    protected abstract String getPageUrl();

    public T open() {
        Selenide.open(getPageUrl());
        return (T) this;
    }
}