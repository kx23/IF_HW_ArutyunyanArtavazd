package steps;

import com.codeborne.selenide.WebDriverRunner;
import data.TestData;
import io.cucumber.java.ru.*;
import pages.BrowseProjectsPage;
import pages.RapidBoardPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BrowseProjectsSteps {
    private final RapidBoardPage rapidBoardPage = new RapidBoardPage();
    private final BrowseProjectsPage browseProjectsPage = new BrowseProjectsPage();

    @Когда("пользователь переходит ко всем проектам")
    public void goToAllProjects() {
        rapidBoardPage.header.goToAllProjects();
    }

    @Тогда("открыта страница списка проектов")
    public void verifyBrowseProjectsOpened() {
        browseProjectsPage.isPageOpened();
    }

    @Когда("пользователь ищет и открывает проект")
    public void searchAndNavigateToProject() {
        browseProjectsPage
                .searchProject(TestData.PROJECT_NAME)
                .navigateToProject(TestData.PROJECT_NAME); // исправь navigateToProject чтоб возвращал this если нужно
    }

    @Тогда("URL содержит ключ проекта")
    public void verifyUrlContainsProjectKey() {
        assertTrue(WebDriverRunner.url()
                .contains(TestData.PROJECT_NAME.toUpperCase()));
    }
}