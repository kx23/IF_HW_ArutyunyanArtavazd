package hooks;

import io.cucumber.java.After;
import io.cucumber.java.BeforeAll;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import utils.ScenarioContext;

public class ApiHooks {

    @BeforeAll
    public static void setUpAllure() {
        RestAssured.filters(new AllureRestAssured());
    }

    @After
    public void tearDown() {
        ScenarioContext.reset();
    }
}
