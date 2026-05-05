import config.TestDataConfig;
import models.enums.IssueStatus;
import org.aeonbits.owner.ConfigCache;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import steps.IssueDetailsSteps;
import steps.IssueSteps;
import steps.LoginSteps;
import steps.ProjectSteps;

public class HWScenarioTest extends WebHooks {

    private static final Logger log = LoggerFactory.getLogger(HWScenarioTest.class);
    private static final TestDataConfig cfg = ConfigCache.getOrCreate(TestDataConfig.class);

    private final LoginSteps loginSteps = new LoginSteps();
    private final ProjectSteps projectSteps = new ProjectSteps();
    private final IssueSteps issueSteps = new IssueSteps();
    private final IssueDetailsSteps issueDetailsSteps = new IssueDetailsSteps();

    @Test
    @DisplayName("Авторизация в edujira.ifellow.ru")
    void test1_login() {
        loginSteps.openLoginPage();
        loginSteps.login(cfg.userLogin(), cfg.userPassword());
        loginSteps.verifyUserLoggedIn(cfg.userLogin());
    }

    @Test
    @DisplayName("Авторизация + переход в проект Test")
    void test2_navigateToProject() {
        loginSteps.openLoginPage();
        loginSteps.login(cfg.userLogin(), cfg.userPassword());
        loginSteps.verifyUserLoggedIn(cfg.userLogin());

        projectSteps.navigateToProject(cfg.projectName());
        projectSteps.verifyProjectIsOpened(cfg.projectName());
    }

    @Test
    @DisplayName("Авторизация + проект + проверка счётчика задач до и после создания")
    void test3_checkIssueCounter() {
        loginSteps.openLoginPage();
        loginSteps.login(cfg.userLogin(), cfg.userPassword());
        loginSteps.verifyUserLoggedIn(cfg.userLogin());

        projectSteps.navigateToProject(cfg.projectName());
        projectSteps.verifyProjectIsOpened(cfg.projectName());

        issueSteps.openTasksList();
        int countBefore = issueSteps.getTasksCount();
        log.info("Задач до создания: {}", countBefore);

        issueSteps.createTask(cfg.issueSummary(), cfg.issueDescription());

        int countAfter = issueSteps.getTasksCount();
        log.info("Задач после создания: {}", countAfter);

        issueSteps.verifyCounterIncreasedByOne(countBefore, countAfter);
    }

    @Test
    @DisplayName("Авторизация + проект + счётчик + проверка статуса и версии TestSeleniumATHomework")
    void test4_checkIssueDetails() {
        loginSteps.openLoginPage();
        loginSteps.login(cfg.userLogin(), cfg.userPassword());
        loginSteps.verifyUserLoggedIn(cfg.userLogin());

        projectSteps.navigateToProject(cfg.projectName());
        projectSteps.verifyProjectIsOpened(cfg.projectName());

        issueSteps.openTasksList();
        int countBefore = issueSteps.getTasksCount();
        log.info("Задач до создания: {}", countBefore);

        issueSteps.createTask(cfg.issueSummary(), cfg.issueDescription());

        int countAfter = issueSteps.getTasksCount();
        log.info("Задач после создания: {}", countAfter);

        issueSteps.verifyCounterIncreasedByOne(countBefore, countAfter);

        issueSteps.searchAndOpenIssue(cfg.testTaskName());
        issueDetailsSteps.verifyIssueOpened();
        issueDetailsSteps.verifyStatus(IssueStatus.TO_DO);
        issueDetailsSteps.verifyVersion(cfg.testTaskVersion());
    }

    @Test
    @DisplayName("Полный сценарий: авторизация + проект + счётчик + детали задачи + создание бага + переход по статусам")
    void test5_fullScenario() {
        loginSteps.openLoginPage();
        loginSteps.login(cfg.userLogin(), cfg.userPassword());
        loginSteps.verifyUserLoggedIn(cfg.userLogin());

        projectSteps.navigateToProject(cfg.projectName());
        projectSteps.verifyProjectIsOpened(cfg.projectName());

        issueSteps.openTasksList();
        int countBefore = issueSteps.getTasksCount();
        log.info("Задач до создания: {}", countBefore);

        issueSteps.createTask(cfg.issueSummary(), cfg.issueDescription());

        int countAfter = issueSteps.getTasksCount();
        log.info("Задач после создания: {}", countAfter);

        issueSteps.verifyCounterIncreasedByOne(countBefore, countAfter);

        issueSteps.searchAndOpenIssue(cfg.testTaskName());
        issueDetailsSteps.verifyIssueOpened();
        issueDetailsSteps.verifyStatus(IssueStatus.TO_DO);
        issueDetailsSteps.verifyVersion(cfg.testTaskVersion());

        String newIssueId = issueSteps.createBugAndNavigate(
                cfg.issueSummary(), cfg.issueDescription(), cfg.issueEnvironment()
        );
        issueDetailsSteps.verifyNewIssueUrl(newIssueId);
        issueDetailsSteps.verifyStatus(IssueStatus.TO_DO);

        issueDetailsSteps.changeStatusToInProgress();
        issueDetailsSteps.verifyStatus(IssueStatus.IN_PROGRESS);

        issueDetailsSteps.changeStatusToDone();
        issueDetailsSteps.verifyStatus(IssueStatus.DONE);
    }
}