package utils;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    public static final String CREDENTIALS  = "credentials";
    public static final String RESPONSE     = "response";
    public static final String TOKEN        = "token";
    public static final String CHARACTER    = "character";
    public static final String LAST_EPISODE = "lastEpisode";
    public static final String LAST_CHAR    = "lastChar";

    private static final ThreadLocal<Map<String, Object>> store =
            ThreadLocal.withInitial(HashMap::new);

    public static void put(String key, Object value) {
        store.get().put(key, value);
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(String key) {
        return (T) store.get().get(key);
    }

    public static void reset() {
        store.remove();
    }
}
