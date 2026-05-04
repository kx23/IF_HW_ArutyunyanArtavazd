package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import utils.MaskingFilter;
import utils.ScenarioContext;

public class ApiHooks {

    @BeforeAll
    public static void setUpAllure() {
        RestAssured.replaceFiltersWith(
                new RequestLoggingFilter(LogDetail.BODY),
                new ResponseLoggingFilter(LogDetail.BODY),
                new AllureRestAssured()
                        .setRequestTemplate("request.ftl")
                        .setResponseTemplate("response.ftl")
        );
    }

    @After
    public void tearDown() {
        ScenarioContext.reset();
    }
}
