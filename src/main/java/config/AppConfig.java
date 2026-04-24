package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config.properties")
public interface AppConfig extends Config {

    @Key("rickandmorty.base_url")
    String rickAndMortyBaseUrl();

    @Key("ifellow.base_url")
    String ifellowBaseUrl();
}