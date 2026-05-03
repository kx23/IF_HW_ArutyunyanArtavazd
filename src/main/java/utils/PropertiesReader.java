package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    private static final Logger log = LoggerFactory.getLogger(PropertiesReader.class);
    private final Properties properties = new Properties();
    private final String fileName;

    public PropertiesReader(String fileName) {
        this.fileName = fileName;
        try (InputStream input = getClass()
                .getClassLoader()
                .getResourceAsStream(fileName)) {

            if (input == null) {
                throw new IllegalStateException("Файл " + fileName + " не найден.");
            }

            properties.load(input);
            log.info("{} успешно загружен", fileName);

        } catch (IOException e) {
            throw new IllegalStateException("Ошибка при чтении " + fileName, e);
        }
    }

    public String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new IllegalArgumentException("Ключ не найден в " + fileName + ": " + key);
        }
        return value;
    }

    public boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }
}