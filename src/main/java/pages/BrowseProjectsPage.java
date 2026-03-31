package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.HeaderComponent;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class BrowseProjectsPage extends BasePage<BrowseProjectsPage> {

    public final HeaderComponent header = new HeaderComponent();

    private final SelenideElement projectsTable =
            $x("//tbody[@class='projects-list']").as("Таблица проектов");

    private final SelenideElement searchInput =
            $x("//input[@id='project-filter-text']").as("Поле поиска проектов");

    @Override
    protected String getPageUrl() {
        return "/secure/BrowseProjects.jspa";
    }

    public BrowseProjectsPage isPageOpened() {
        projectsTable.shouldBe(visible);
        return this;
    }

    public BrowseProjectsPage searchProject(String projectName) {
        searchInput.shouldBe(visible).setValue(projectName).pressEnter();
        return this;
    }

    public void navigateToProject(String projectName) {
        projectsTable.$x(".//td[@data-cell-type='name']//a[@original-title='"+ projectName + "']")
                .as("Проект: " + projectName)
                .shouldBe(visible)
                .click();
    }
}