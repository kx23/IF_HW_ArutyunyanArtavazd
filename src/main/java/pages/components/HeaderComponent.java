package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class HeaderComponent {

    private final SelenideElement projectsListButton =
            $x("//a[@id='browse_link']").as("Список проектов");

    private final SelenideElement allProjectsLink =
            $x("//a[@id='project_view_all_link_lnk']").as("Все проекты");

    private final SelenideElement userAvatar =
            $x("//a[@id='header-details-user-fullname']").as("Аватар пользователя");


    private final SelenideElement createIssueButton=
            $x("//a[@id='create_link']").as("Кнопка создания задачи");

    private final SelenideElement searchInput =
            $x("//input[@id='quickSearchInput']").as("Поле поиска в хедере");

    private final SelenideElement searchResults =
            $x("//li[@class='quick-search-result-item']/parent::ul").as("Результаты поиска");

    private SelenideElement getSearchResult(String taskName) {
        return searchResults.$x(".//span[text()='" + taskName + "']/parent::a").as("Результат поиска: " + taskName);
    }

    private final SelenideElement successfulCreatedIssueLink=
            $x("//a[@class='issue-created-key issue-link']");

    public HeaderComponent shouldBeLoggedIn() {
        userAvatar.shouldBe(visible);
        return this;
    }
    public String getLoggedInUser() {
        return userAvatar.getAttribute("data-username");
    }


    public void goToAllProjects() {
        projectsListButton.click();
        allProjectsLink.shouldBe(visible).click();
    }

    public CreateIssueModalComponent clickCreateIssue() {
        createIssueButton.shouldBe(visible).click();
        return new CreateIssueModalComponent();
    }

    public HeaderComponent searchIssue(String taskName) {
        searchInput.shouldBe(visible).click();
        searchInput.setValue(taskName);
        return this;
    }

    public void openIssueFromSearch(String taskName) {
        getSearchResult(taskName).shouldBe(visible).click();
    }
    public void goToCreatedIssuePage()
    {
        successfulCreatedIssueLink.shouldBe(visible).click();
    }
    public String getSuccessfulCreatedIssueID()
    {
       return successfulCreatedIssueLink.shouldBe(visible).getAttribute("data-issue-key").toString();
    }

}
