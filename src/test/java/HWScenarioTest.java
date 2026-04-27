import com.codeborne.selenide.WebDriverRunner;
import data.TestData;
import models.enums.IssueStatus;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HWScenarioTest extends WebHooks {
    private static final Logger log = LoggerFactory.getLogger(HWScenarioTest.class);



    private final LoginPage loginPage = new LoginPage();
    private final BrowseProjectsPage browseProjectsPage = new BrowseProjectsPage();
    private final RapidBoardPage rapidBoardPage = new RapidBoardPage();
    private final AllIssuesPage allIssuesPage = new AllIssuesPage(TestData.PROJECT_NAME);
    private final IssueDetailsPage issueDetailsPage = new IssueDetailsPage();
    private final IssuesSearchPage issuesSearchPage= new IssuesSearchPage();


    private void step1_login() {
        loginPage.open();
        loginPage.login(TestData.VALID_USER, TestData.VALID_PASS);

        assertEquals(
                TestData.VALID_USER,
                rapidBoardPage.header.getLoggedInUser()
        );
    }

    private void step2_navigateToProject() {
        rapidBoardPage.header.goToAllProjects();
        browseProjectsPage.isPageOpened();
        browseProjectsPage
                .searchProject(TestData.PROJECT_NAME)
                .navigateToProject(TestData.PROJECT_NAME);
        assertTrue(
                WebDriverRunner.url().contains(TestData.PROJECT_NAME.toUpperCase()),
                "URL должен содержать ключ проекта: " + TestData.PROJECT_NAME.toUpperCase()
        );

    }

    private int step3_getCountBeforeCreation() {
        rapidBoardPage.sidebar.openAllTasks();
        allIssuesPage.goToIssuesSearchPage();
        issuesSearchPage.turnOnOnlyTasksFilter();
        int count = issuesSearchPage.getResultsTotalCount();
        log.info("Задач до создания: {}", count);
        return count;
    }

    private void step3_createIssueAndCheckCounter(int countBefore) {

        issuesSearchPage.header.clickCreateIssue()
                .selectIssueType(TestData.ISSUE_TYPE_TASK)
                .fillSummary(TestData.NEW_TASK_SUMMARY_TEXT)
                .fillDescription(TestData.NEW_TASK_DESCRIPTION_TEXT)
                .submit();

        int countAfter = issuesSearchPage.getResultsTotalCount();
        log.info("Задач после создания: {}", countAfter);

        assertEquals(
                countBefore + 1, countAfter,
                "Счётчик должен увеличиться на 1: ожидали " + (countBefore + 1) + ", получили " + countAfter
        );
    }

    private void step4_checkTestTaskDetails() {
        issuesSearchPage.header.searchIssue(TestData.TEST_TASK_NAME);
        issuesSearchPage.header.openIssueFromSearch(TestData.TEST_TASK_NAME);

        issueDetailsPage.isPageOpened();

        assertEquals(TestData.TEST_TASK_STATUS,issueDetailsPage.getStatusText());
        assertEquals(TestData.TEST_TASK_VERSION,issueDetailsPage.getVersionText());

    }

    private void step5_createBugAndTransitionToClose() {


        issueDetailsPage.header.clickCreateIssue()
                .selectIssueType(TestData.ISSUE_TYPE_BUG)
                .ensureDescriptionVisualMode()
                .ensureEnvironmentVisualMode()
                .fillSummary(TestData.NEW_TASK_SUMMARY_TEXT)
                .fillDescription(TestData.NEW_TASK_DESCRIPTION_TEXT)
                .fillEnvironment(TestData.NEW_TASK_ENVIRONMENT_TEXT)
                .submit();


        String newIssueId= issueDetailsPage.header.getSuccessfulCreatedIssueID();

        issueDetailsPage.header.goToCreatedIssuePage();

        assertTrue(
                WebDriverRunner.url().contains(newIssueId),
                "Url не соодержит id созданной задачи"
        );

        assertEquals(IssueStatus.TO_DO.getValue(),issueDetailsPage.getStatusText());

        issueDetailsPage
                .issueSuccessfulChangeMessageIsNotVisible()
                .changeStatusToInProgress()
                .issueSuccessfulChangeMessageIsVisible();

        assertEquals(IssueStatus.IN_PROGRESS.getValue(),issueDetailsPage.getStatusText());

        issueDetailsPage
                .issueSuccessfulChangeMessageIsNotVisible()
                .changeStatusToDone()
                .issueSuccessfulChangeMessageIsVisible();;
        assertEquals(IssueStatus.DONE.getValue(),issueDetailsPage.getStatusText());

    }

    @Test
    @DisplayName("Авторизация в edujira.ifellow.ru")
    void test1_login() {
        step1_login();
    }

    @Test
    @DisplayName("Авторизация + переход в проект Test")
    void test2_navigateToProject() {
        step1_login();
        step2_navigateToProject();
    }

    @Test
    @DisplayName("Авторизация + проект + проверка счётчика задач до и после создания")
    void test3_checkIssueCounter() {
        step1_login();
        step2_navigateToProject();

        int countBefore = step3_getCountBeforeCreation();

        step3_createIssueAndCheckCounter(countBefore);
    }

    @Test
    @DisplayName("Авторизация + проект + счётчик + проверка статуса и версии TestSeleniumATHomework")
    void test4_checkIssueDetails() {
        step1_login();
        step2_navigateToProject();

        int countBefore = step3_getCountBeforeCreation();
        step3_createIssueAndCheckCounter(countBefore);

        step4_checkTestTaskDetails();
    }

    @Test
    @DisplayName("Полный сценарий: авторизация + проект + счётчик + детали задачи + создание бага + переход по статусам")
    void test5_fullScenario() {
        step1_login();
        step2_navigateToProject();

        int countBefore = step3_getCountBeforeCreation();
        step3_createIssueAndCheckCounter(countBefore);

        step4_checkTestTaskDetails();

        step5_createBugAndTransitionToClose();
    }
}