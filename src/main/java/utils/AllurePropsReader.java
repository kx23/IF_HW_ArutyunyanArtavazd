package utils;

public class AllurePropsReader {
    private static final PropertiesReader reader = new PropertiesReader("allure.properties");

    public static String get(String key) {
        return reader.get(key);
    }
    public static boolean getBoolean(String key) {
        return reader.getBoolean(key);
    }
}