package api.rickAndMorty;

import api.Specifications;
import data.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;

public class BaseRMApi {
    public BaseRMApi()
    {
        RestAssured.requestSpecification= Specifications.baseRequestSpec(ConfigReader.get("rickandmorty.base_url"));
        RestAssured.responseSpecification= Specifications.baseResponseSpec();
    }
}
