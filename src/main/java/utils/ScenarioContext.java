package utils;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    public static final String COUNT_BEFORE = "countBefore";
    public static final String NEW_ISSUE_ID  = "newIssueId";

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