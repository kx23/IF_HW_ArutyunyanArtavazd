package steps;

import utils.ScenarioContext;
import data.TestData;
import io.cucumber.java.ru.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.AllIssuesPage;
import pages.IssuesSearchPage;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class IssuesSearchSteps {
    private static final Logger log = LoggerFactory.getLogger(IssuesSearchSteps.class);

    private final AllIssuesPage allIssuesPage = new AllIssuesPage(TestData.PROJECT_NAME);
    private final IssuesSearchPage issuesSearchPage = new IssuesSearchPage();

    @И("пользователь открывает все задачи через сайдбар")
    public void openAllTasksViaSidebar() {
        new pages.RapidBoardPage().sidebar.openAllTasks();
    }

    @И("пользователь переходит на страницу поиска задач")
    public void goToIssuesSearchPage() {
        allIssuesPage.goToIssuesSearchPage();
    }

    @И("пользователь включает фильтр только по задачам")
    public void turnOnTasksFilter() {
        issuesSearchPage.turnOnOnlyTasksFilter();
    }

    @И("пользователь запоминает текущее количество задач")
    public void saveCurrentCount() {
        int count = issuesSearchPage.getResultsTotalCount();
        log.info("Задач до создания: {}", count);
        ScenarioContext.put(ScenarioContext.COUNT_BEFORE, count);
    }

    @Тогда("количество задач увеличилось на 1")
    public void verifyCountIncreasedByOne() {
        int before = ScenarioContext.get(ScenarioContext.COUNT_BEFORE);
        int after  = issuesSearchPage.getResultsTotalCount();
        log.info("Задач после создания: {}", after);
        assertEquals(
                before + 1, after,
                "Счётчик должен увеличиться на 1: ожидали " + (before + 1) + ", получили " + after
        );
    }

    @Когда("пользователь ищет задачу {string} через поиск в шапке")
    public void searchIssue(String taskName) {
        issuesSearchPage.header.searchIssue(taskName);
    }

    @И("пользователь открывает задачу {string} из результатов поиска")
    public void openIssueFromSearch(String taskName) {
        issuesSearchPage.header.openIssueFromSearch(taskName);
    }
}