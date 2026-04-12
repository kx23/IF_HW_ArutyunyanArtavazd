package steps;

import com.codeborne.selenide.WebDriverRunner;
import utils.ScenarioContext;
import data.TestData;
import io.cucumber.java.ru.*;
import models.enums.IssueStatus;
import pages.IssueDetailsPage;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IssueDetailsSteps {
    private final IssueDetailsPage issueDetailsPage = new IssueDetailsPage();

    @Тогда("страница задачи открыта")
    public void verifyIssuePageOpened() {
        issueDetailsPage.isPageOpened();
    }

    @Тогда("статус задачи равен {string}")
    public void verifyStatus(String expectedStatus) {
        assertEquals(issueDetailsPage.getStatusText(),expectedStatus);
    }

    @И("версия задачи равна {string}")
    public void verifyVersion(String expectedVersion) {
        assertEquals(issueDetailsPage.getStatusText(),expectedVersion);
    }

    @Тогда("URL содержит ID созданной задачи")
    public void verifyUrlContainsNewIssueId() {
        String id = ScenarioContext.get(ScenarioContext.NEW_ISSUE_ID);
        assertTrue(WebDriverRunner.url().contains(id));
    }

    @Тогда("статус задачи равен TO_DO")
    public void verifyStatusToDo() {
        assertEquals(IssueStatus.TO_DO.getValue(),issueDetailsPage.getStatusText());
    }

    @Когда("пользователь меняет статус на В работе")
    public void changeStatusToInProgress() {
        issueDetailsPage
                .issueSuccessfulChangeMessageIsNotVisible()
                .changeStatusToInProgress()
                .issueSuccessfulChangeMessageIsVisible();
    }

    @Когда("пользователь меняет статус на Выполнено")
    public void changeStatusToDone() {
        issueDetailsPage
                .issueSuccessfulChangeMessageIsNotVisible()
                .changeStatusToDone()
                .issueSuccessfulChangeMessageIsVisible();
    }

    @И("пользователь проверяет детали тестовой задачи")
    public void verifyTestTaskDetails() {
        assertEquals(TestData.TEST_TASK_STATUS,issueDetailsPage.getStatusText());
        assertEquals(TestData.TEST_TASK_VERSION,issueDetailsPage.getVersionText());
    }
}