package steps;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import pages.BrowseProjectsPage;
import pages.components.HeaderComponent;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProjectSteps {

    private final HeaderComponent headerComponent = new HeaderComponent();
    private final BrowseProjectsPage browseProjectsPage = new BrowseProjectsPage();

    @Step("Перейти в проект: {projectName}")
    public ProjectSteps navigateToProject(String projectName) {
        headerComponent.goToAllProjects();
        browseProjectsPage.isPageOpened();
        browseProjectsPage
                .searchProject(projectName)
                .navigateToProject(projectName);
        return this;
    }

    @Step("Проверить, что открыт проект: {projectName}")
    public ProjectSteps verifyProjectIsOpened(String projectName) {
        assertTrue(
                WebDriverRunner.url().contains(projectName.toUpperCase()),
                "URL должен содержать ключ проекта: " + projectName.toUpperCase()
        );
        return this;
    }
}
