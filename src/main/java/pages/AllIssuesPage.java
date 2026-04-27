package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.components.HeaderComponent;
import pages.components.SidebarComponent;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class AllIssuesPage extends BasePage<AllIssuesPage> {

    public final HeaderComponent header = new HeaderComponent();
    public final SidebarComponent sidebar = new SidebarComponent();

    private final String projectKey;

    private final SelenideElement issuesSearchPageLink=
            $x("//div[@id='full-issue-navigator']/a").as("Ссылка на страницу с задачами и фильтрами");

    public AllIssuesPage(String projectKey) {
        this.projectKey = projectKey;
    }

    @Override
    protected String getPageUrl() {
        return "/projects/" + projectKey.toUpperCase() + "/issues";
    }


    @Step("Перейти на страницу поиска задач и фильтров")
    public void goToIssuesSearchPage()
    {
        issuesSearchPageLink.shouldBe(visible).click();

    }

}