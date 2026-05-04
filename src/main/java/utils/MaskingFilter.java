package utils;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MaskingFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(MaskingFilter.class);

    // Все поля, которые нужно маскировать
    private static final List<String> SENSITIVE_FIELDS = List.of("password", "token", "secret");

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext ctx) {

        Object rawBody = requestSpec.getBody();
        if (rawBody instanceof String body) {
            String masked = maskJson(body);
            if (!masked.equals(body)) {
                log.debug("Request body masked before Allure capture");
            }
            requestSpec.body(masked);
        }

        return ctx.next(requestSpec, responseSpec);
    }

    public static String maskJson(String json) {
        if (json == null || json.isBlank()) return json;
        String result = json;
        for (String field : SENSITIVE_FIELDS) {
            result = result.replaceAll(
                    "(?i)(\"" + field + "\"\\s*:\\s*\")([^\"]*)(\")",
                    "$1****$3"
            );
        }
        return result;
    }
}