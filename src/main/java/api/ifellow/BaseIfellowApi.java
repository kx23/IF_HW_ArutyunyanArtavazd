package api.ifellow;

import api.Specifications;
import config.ConfigProvider;
import io.restassured.RestAssured;

public class BaseIfellowApi {
    public BaseIfellowApi() {
        RestAssured.requestSpecification = Specifications.baseRequestSpec(ConfigProvider.config.ifellowBaseUrl());
        RestAssured.responseSpecification = Specifications.baseResponseSpec();
    }
}