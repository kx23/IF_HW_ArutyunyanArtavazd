package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.HeaderComponent;
import pages.components.SidebarComponent;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class AllIssuesPage extends BasePage<AllIssuesPage> {

    public final HeaderComponent header = new HeaderComponent();
    public final SidebarComponent sidebar = new SidebarComponent();

    private final String projectKey;

    private final SelenideElement issuesList =
            $x("//table[@id='issuetable']").as("Таблица задач");

    private SelenideElement issueCounter =
            $x("//div[@class='issue-tools']//div[@class='pager']//span").as("Счётчик задач");
    private final SelenideElement refreshButton=
            $x("//*[@class='aui-button aui-button-subtle refresh-table']").as("Кнопка обновления");

    private final SelenideElement issuesSearchPageLink=
            $x("//div[@id='full-issue-navigator']/a").as("Ссылка на страницу с задачами и фильтрами");

    public AllIssuesPage(String projectKey) {
        this.projectKey = projectKey;
    }

    @Override
    protected String getPageUrl() {
        return "/projects/" + projectKey.toUpperCase() + "/issues";
    }



    public int getTotalIssuesCount() {

        // при использовании локатора, после перезагрузки возвращается необновленное значение
        // пока оставил так, т.к. пока не нашел хорошего решения

        //String text = issueCounter.shouldBe(Condition.visible).getText();
        String text = $x("//div[@class='issue-tools']//div[@class='pager']//span").shouldBe(visible).getText();

        return Integer.parseInt(text.replaceAll(".*из\\s*(\\d+).*", "$1"));
    }

    public void goToIssuesSearchPage()
    {
        issuesSearchPageLink.shouldBe(visible).click();

    }

}