package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.components.HeaderComponent;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class IssuesSearchPage extends BasePage<IssuesSearchPage> {


    public final HeaderComponent header = new HeaderComponent();

    private final SelenideElement issueTypeFilterDropdown =
            $x("//div[@data-id='issuetype']");

    private final SelenideElement issueTaskTypeFilterCheckbox =
            $x("//ul[@id='стандартные-задачи']//label[@data-descriptor-title='Задача']");

    private final SelenideElement refreshButton=
            $x("//a[@class='refresh-table']").as("Кнопка обновления таблицы");

    private final SelenideElement resultsTotalCount=
            $x("//span[@class='results-count-total results-count-link']").as("Количество задач в результате поиска");

    @Override
    protected String getPageUrl() {
        return "/issues";
    }

    @Step("Применить фильтр по типу задачи: Задача")
    public IssuesSearchPage turnOnOnlyTasksFilter()
    {
        issueTypeFilterDropdown.shouldBe(visible).click();
        issueTaskTypeFilterCheckbox.shouldBe(visible).click();
        return this;
    }

    @Step("Получить общее количество задач в результатах поиска")
    public int getResultsTotalCount() {

        $x("//div[@class='issue-table-wrapper']").shouldBe(visible);
        refreshButton.shouldBe(visible).click();
        $x("//div[@class='issue-table-wrapper']").shouldBe(visible);

        String text = resultsTotalCount.shouldBe(visible).getText();

        return Integer.parseInt(text);
    }
}
