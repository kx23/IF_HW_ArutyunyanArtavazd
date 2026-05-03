package utils;

public class ConfigPropsReader {

    private static final PropertiesReader reader = new PropertiesReader("config.properties");

    public static String get(String key) {
        return reader.get(key);
    }
}