import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
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

    @BeforeEach
    public void setUp()
    {

        Selenide.open();
        //System.out.println(Configuration.pageLoadStrategy);
        //System.out.println("--------");
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }

    @AfterEach
    public void tearUp()
    {
        Selenide.closeWebDriver();
    }
}
