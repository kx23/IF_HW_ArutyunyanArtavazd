package api.rickAndMorty;

import api.Specifications;
import config.ConfigProvider;
import io.restassured.RestAssured;

public class BaseRMApi {
    public BaseRMApi()
    {
        RestAssured.requestSpecification= Specifications.baseRequestSpec(ConfigProvider.config.rickAndMortyBaseUrl());
        RestAssured.responseSpecification= Specifications.baseResponseSpec();
    }
}
