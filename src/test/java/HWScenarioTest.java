import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import config.TestDataConfig;
import models.enums.IssueStatus;
import models.enums.IssueType;
import org.aeonbits.owner.ConfigCache;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HWScenarioTest extends WebHooks {
    private static final Logger log = LoggerFactory.getLogger(HWScenarioTest.class);
    private static final TestDataConfig cfg = ConfigCache.getOrCreate(TestDataConfig.class);

    private final LoginPage loginPage = new LoginPage();
    private final BrowseProjectsPage browseProjectsPage = new BrowseProjectsPage();
    private final RapidBoardPage rapidBoardPage = new RapidBoardPage();
    private final AllIssuesPage allIssuesPage = new AllIssuesPage(cfg.projectName());
    private final IssueDetailsPage issueDetailsPage = new IssueDetailsPage();
    private final IssuesSearchPage issuesSearchPage = new IssuesSearchPage();


    @Test
    @DisplayName("Авторизация в edujira.ifellow.ru")
    void test1_login() {
        loginPage.open();
        loginPage.login(cfg.userLogin(), cfg.userPassword());

        assertEquals(cfg.userLogin(), rapidBoardPage.header.getLoggedInUser());
    }

    @Test
    @DisplayName("Авторизация + переход в проект Test")
    void test2_navigateToProject() {
        loginPage.open();
        loginPage.login(cfg.userLogin(), cfg.userPassword());

        assertEquals(cfg.userLogin(), rapidBoardPage.header.getLoggedInUser());

        rapidBoardPage.header.goToAllProjects();
        browseProjectsPage.isPageOpened();
        browseProjectsPage
                .searchProject(cfg.projectName())
                .navigateToProject(cfg.projectName());
        assertTrue(
                WebDriverRunner.url().contains(cfg.projectName().toUpperCase()),
                "URL должен содержать ключ проекта: " + cfg.projectName().toUpperCase()
        );
    }

    @Test
    @DisplayName("Авторизация + проект + проверка счётчика задач до и после создания")
    void test3_checkIssueCounter() {
        loginPage.open();
        loginPage.login(cfg.userLogin(), cfg.userPassword());

        assertEquals(cfg.userLogin(), rapidBoardPage.header.getLoggedInUser());

        rapidBoardPage.header.goToAllProjects();
        browseProjectsPage.isPageOpened();
        browseProjectsPage
                .searchProject(cfg.projectName())
                .navigateToProject(cfg.projectName());
        assertTrue(
                WebDriverRunner.url().contains(cfg.projectName().toUpperCase()),
                "URL должен содержать ключ проекта: " + cfg.projectName().toUpperCase()
        );

        rapidBoardPage.sidebar.openAllTasks();
        allIssuesPage.goToIssuesSearchPage();
        issuesSearchPage.turnOnOnlyTasksFilter();
        int count = issuesSearchPage.getResultsTotalCount();
        log.info("Задач до создания: {}", count);
        int countBefore = count;

        issuesSearchPage.header.clickCreateIssue()
                .selectIssueType(IssueType.TASK.getValue())
                .fillSummary(cfg.issueSummary())
                .fillDescription(cfg.issueDescription())
                .submit();

        int countAfter = issuesSearchPage.getResultsTotalCount();
        log.info("Задач после создания: {}", countAfter);

        assertEquals(
                countBefore + 1, countAfter,
                "Счётчик должен увеличиться на 1: ожидали " + (countBefore + 1) + ", получили " + countAfter
        );
    }

    @Test
    @DisplayName("Авторизация + проект + счётчик + проверка статуса и версии TestSeleniumATHomework")
    void test4_checkIssueDetails() {
        loginPage.open();
        loginPage.login(cfg.userLogin(), cfg.userPassword());

        assertEquals(cfg.userLogin(), rapidBoardPage.header.getLoggedInUser());

        rapidBoardPage.header.goToAllProjects();
        browseProjectsPage.isPageOpened();
        browseProjectsPage
                .searchProject(cfg.projectName())
                .navigateToProject(cfg.projectName());
        assertTrue(
                WebDriverRunner.url().contains(cfg.projectName().toUpperCase()),
                "URL должен содержать ключ проекта: " + cfg.projectName().toUpperCase()
        );

        rapidBoardPage.sidebar.openAllTasks();
        allIssuesPage.goToIssuesSearchPage();
        issuesSearchPage.turnOnOnlyTasksFilter();
        int countBefore = issuesSearchPage.getResultsTotalCount();
        log.info("Задач до создания: {}", countBefore);

        issuesSearchPage.header.clickCreateIssue()
                .selectIssueType(IssueType.TASK.getValue())
                .fillSummary(cfg.issueSummary())
                .fillDescription(cfg.issueDescription())
                .submit();

        int countAfter = issuesSearchPage.getResultsTotalCount();
        log.info("Задач после создания: {}", countAfter);

        assertEquals(
                countBefore + 1, countAfter,
                "Счётчик должен увеличиться на 1: ожидали " + (countBefore + 1) + ", получили " + countAfter
        );

        issuesSearchPage.header.searchIssue(cfg.testTaskName());
        issuesSearchPage.header.openIssueFromSearch(cfg.testTaskName());

        issueDetailsPage.isPageOpened();

        assertEquals(IssueStatus.TO_DO.getValue(), issueDetailsPage.getStatusText());
        assertEquals(cfg.testTaskVersion(), issueDetailsPage.getVersionText());
    }

    @Test
    @DisplayName("Полный сценарий: авторизация + проект + счётчик + детали задачи + создание бага + переход по статусам")
    void test5_fullScenario() {
        loginPage.open();
        loginPage.login(cfg.userLogin(), cfg.userPassword());

        assertEquals(cfg.userLogin(), rapidBoardPage.header.getLoggedInUser());

        rapidBoardPage.header.goToAllProjects();
        browseProjectsPage.isPageOpened();
        browseProjectsPage
                .searchProject(cfg.projectName())
                .navigateToProject(cfg.projectName());
        assertTrue(
                WebDriverRunner.url().contains(cfg.projectName().toUpperCase()),
                "URL должен содержать ключ проекта: " + cfg.projectName().toUpperCase()
        );

        rapidBoardPage.sidebar.openAllTasks();
        allIssuesPage.goToIssuesSearchPage();
        issuesSearchPage.turnOnOnlyTasksFilter();
        int countBefore = issuesSearchPage.getResultsTotalCount();
        log.info("Задач до создания: {}", countBefore);

        issuesSearchPage.header.clickCreateIssue()
                .selectIssueType(IssueType.TASK.getValue())
                .fillSummary(cfg.issueSummary())
                .fillDescription(cfg.issueDescription())
                .submit();

        int countAfter = issuesSearchPage.getResultsTotalCount();
        log.info("Задач после создания: {}", countAfter);

        assertEquals(
                countBefore + 1, countAfter,
                "Счётчик должен увеличиться на 1: ожидали " + (countBefore + 1) + ", получили " + countAfter
        );

        issuesSearchPage.header.searchIssue(cfg.testTaskName());
        issuesSearchPage.header.openIssueFromSearch(cfg.testTaskName());

        issueDetailsPage.isPageOpened();

        assertEquals(IssueStatus.TO_DO.getValue(), issueDetailsPage.getStatusText());
        assertEquals(cfg.testTaskVersion(), issueDetailsPage.getVersionText());

        issueDetailsPage.header.clickCreateIssue()
                .selectIssueType(IssueType.BUG.getValue())
                .ensureDescriptionVisualMode()
                .ensureEnvironmentVisualMode()
                .fillSummary(cfg.issueSummary())
                .fillDescription(cfg.issueDescription())
                .fillEnvironment(cfg.issueEnvironment())
                .submit();

        String newIssueId = issueDetailsPage.header.getSuccessfulCreatedIssueID();

        issueDetailsPage.header.goToCreatedIssuePage();

        assertTrue(
                WebDriverRunner.url().contains(newIssueId),
                "Url не содержит id созданной задачи"
        );

        assertEquals(IssueStatus.TO_DO.getValue(), issueDetailsPage.getStatusText());

        issueDetailsPage
                .issueSuccessfulChangeMessageIsNotVisible()
                .changeStatusToInProgress()
                .issueSuccessfulChangeMessageIsVisible();

        assertEquals(IssueStatus.IN_PROGRESS.getValue(), issueDetailsPage.getStatusText());

        issueDetailsPage
                .issueSuccessfulChangeMessageIsNotVisible()
                .changeStatusToDone()
                .issueSuccessfulChangeMessageIsVisible();

        assertEquals(IssueStatus.DONE.getValue(), issueDetailsPage.getStatusText());
    }
}