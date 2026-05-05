package steps;

import config.TestDataConfig;
import io.qameta.allure.Step;
import models.enums.IssueType;
import org.aeonbits.owner.ConfigCache;
import pages.AllIssuesPage;
import pages.IssuesSearchPage;
import pages.RapidBoardPage;
import pages.components.HeaderComponent;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IssueSteps {

    private static final TestDataConfig cfg = ConfigCache.getOrCreate(TestDataConfig.class);

    private final RapidBoardPage rapidBoardPage = new RapidBoardPage();
    private final AllIssuesPage allIssuesPage = new AllIssuesPage(cfg.projectName());
    private final IssuesSearchPage issuesSearchPage = new IssuesSearchPage();
    private final HeaderComponent headerComponent = new HeaderComponent();

    @Step("Открыть список задач с фильтром 'Только задачи'")
    public IssueSteps openTasksList() {
        rapidBoardPage.sidebar.openAllTasks();
        allIssuesPage.goToIssuesSearchPage();
        issuesSearchPage.turnOnOnlyTasksFilter();
        return this;
    }

    @Step("Получить количество задач в результатах поиска")
    public int getTasksCount() {
        return issuesSearchPage.getResultsTotalCount();
    }

    @Step("Создать задачу с темой: {summary}")
    public IssueSteps createTask(String summary, String description) {
        issuesSearchPage.header.clickCreateIssue()
                .selectIssueType(IssueType.TASK.getValue())
                .fillSummary(summary)
                .fillDescription(description)
                .submit();
        return this;
    }

    @Step("Создать баг и перейти на страницу созданной задачи")
    public String createBugAndNavigate(String summary, String description, String environment) {
        headerComponent.clickCreateIssue()
                .selectIssueType(IssueType.BUG.getValue())
                .ensureDescriptionVisualMode()
                .ensureEnvironmentVisualMode()
                .fillSummary(summary)
                .fillDescription(description)
                .fillEnvironment(environment)
                .submit();
        String newIssueId = headerComponent.getSuccessfulCreatedIssueID();
        headerComponent.goToCreatedIssuePage();
        return newIssueId;
    }

    @Step("Найти задачу '{taskName}' через поиск и открыть её")
    public IssueSteps searchAndOpenIssue(String taskName) {
        issuesSearchPage.header.searchIssue(taskName);
        issuesSearchPage.header.openIssueFromSearch(taskName);
        return this;
    }

    @Step("Проверить, что счётчик задач увеличился на 1 (было: {countBefore}, стало: {countAfter})")
    public void verifyCounterIncreasedByOne(int countBefore, int countAfter) {
        assertEquals(
                countBefore + 1,
                countAfter,
                "Счётчик должен увеличиться на 1: ожидали " + (countBefore + 1) + ", получили " + countAfter
        );
    }
}
