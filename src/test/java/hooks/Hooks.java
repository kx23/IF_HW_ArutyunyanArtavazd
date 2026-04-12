package hooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.ScenarioContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @BeforeAll
    public static void setUpAll() {
        ChromeOptions options = new ChromeOptions();
        Configuration.browser = "chrome";
        Configuration.browserCapabilities = options;
    }

    @Before
    public void setUp() {
        Selenide.open();
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }

    @After
    public void tearDown() {
        ScenarioContext.reset();
        Selenide.closeWebDriver();
    }
}