package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class BrowseProjectsPage extends BasePage<BrowseProjectsPage> {


    private final SelenideElement projectsTable =
            $x("//tbody[@class='projects-list']").as("Таблица проектов");

    private final SelenideElement searchInput =
            $x("//input[@id='project-filter-text']").as("Поле поиска проектов");

    @Override
    protected String getPageUrl() {
        return "/secure/BrowseProjects.jspa";
    }

    @Step("Проверить, что страница проектов открыта")
    public BrowseProjectsPage isPageOpened() {
        projectsTable.shouldBe(visible);
        return this;
    }

    @Step("Найти проект: {projectName}")
    public BrowseProjectsPage searchProject(String projectName) {
        searchInput.shouldBe(visible).setValue(projectName).pressEnter();
        return this;
    }

    @Step("Перейти в проект: {projectName}")
    public void navigateToProject(String projectName) {
        projectsTable.$x(".//td[@data-cell-type='name']//a[@original-title='"+ projectName + "']")
                .as("Проект: " + projectName)
                .shouldBe(visible)
                .click();
    }
}