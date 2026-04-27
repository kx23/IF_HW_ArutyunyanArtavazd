import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebHooks {

    @BeforeAll
    public static void setUpAll() {
        Configuration.browser = "chrome";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        Configuration.browserSize = null;
        Configuration.browserCapabilities = options;
    }

    @BeforeAll
    public static void setUpAllure() {
        SelenideLogger.addListener("AllureSelenide",new AllureSelenide().
                screenshots(true).
                savePageSource(true));
    }


    @Step("Закрыть браузер")
    @AfterEach
    public void tearUp()
    {
        Selenide.closeWebDriver();
    }
}
