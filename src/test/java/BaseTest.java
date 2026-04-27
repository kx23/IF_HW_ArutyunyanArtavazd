import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {

    @BeforeAll
    public static void setUpAll() {
        ChromeOptions options = new ChromeOptions();
        Configuration.browser = "chrome";
        Configuration.browserCapabilities = options;
    }

    @BeforeAll
    public static void setUpAllure() {
        SelenideLogger.addListener("AllureSelenide",new AllureSelenide().
                screenshots(true).
                savePageSource(true));
    }

    @BeforeEach
    public void setUp()
    {

        Selenide.open();
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }

    @AfterEach
    public void tearUp()
    {
        Selenide.closeWebDriver();
    }
}
