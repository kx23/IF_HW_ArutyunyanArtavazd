package config;

import org.aeonbits.owner.ConfigFactory;

public class ConfigProvider {
    public static final TestDataConfig testDataConfig = ConfigFactory.create(TestDataConfig.class);
    public static final SelenideConfig selenideConfig = ConfigFactory.create(SelenideConfig.class);
    public static final AllureConfig allureConfig = ConfigFactory.create(AllureConfig.class);
}