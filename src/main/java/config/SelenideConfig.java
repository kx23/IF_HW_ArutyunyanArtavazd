package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:selenide.properties")
public interface SelenideConfig extends Config {

    @Key("selenide.baseUrl")
    String baseUrl();

    @Key("selenide.browser")
    String browser();

    @Key("selenide.timeout")
    long timeout();
}