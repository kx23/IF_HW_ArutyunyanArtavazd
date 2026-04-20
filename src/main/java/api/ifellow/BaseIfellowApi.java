package api.ifellow;

import api.Specifications;
import data.ConfigReader;
import io.restassured.RestAssured;

public class BaseIfellowApi {
    public BaseIfellowApi() {
        RestAssured.requestSpecification = Specifications.baseRequestSpec(ConfigReader.get("ifellow.base_url"));
        RestAssured.responseSpecification = Specifications.baseResponseSpec();
    }
}