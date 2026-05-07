package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import utils.ScenarioContext;

public class ApiHooks {

    @Before
    public void setUpAllure() {
        RestAssured.replaceFiltersWith(
                new AllureRestAssured()
                        .setRequestTemplate("request.ftl")
                        .setResponseTemplate("response.ftl")
        );
    }

    @After
    public void tearDown() {
        ScenarioContext.reset();
        RestAssured.reset();
    }
}
