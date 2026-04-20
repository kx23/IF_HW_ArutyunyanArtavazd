package hooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.ScenarioContext;
import io.cucumber.java.After;

public class Hooks {

    @BeforeAll
    public static void setUpAll() {
        Configuration.browser = "chrome";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        Configuration.browserSize = null;
        Configuration.browserCapabilities = options;
    }

    @After
    public void tearDown() {
        ScenarioContext.reset();
        Selenide.closeWebDriver();
    }
}