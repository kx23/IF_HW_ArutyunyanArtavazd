package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:allure.properties")
public interface AllureConfig extends Config {

    @Key("allure.screenshots")
    boolean screenshots();

    @Key("allure.save.page.source")
    boolean savePageSource();

    @Key("allure.include.selenide.steps")
    boolean includeSelenideSteps();
}