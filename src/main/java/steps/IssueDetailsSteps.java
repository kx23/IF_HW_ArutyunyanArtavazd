package steps;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import models.enums.IssueStatus;
import pages.IssueDetailsPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IssueDetailsSteps {

    private final IssueDetailsPage issueDetailsPage = new IssueDetailsPage();

    @Step("Проверить, что страница задачи открыта")
    public IssueDetailsSteps verifyIssueOpened() {
        issueDetailsPage.isPageOpened();
        return this;
    }

    @Step("Проверить, что URL содержит ID задачи: {issueId}")
    public IssueDetailsSteps verifyNewIssueUrl(String issueId) {
        assertTrue(
                WebDriverRunner.url().contains(issueId),
                "URL не содержит id созданной задачи: " + issueId
        );
        return this;
    }

    @Step("Проверить статус задачи: {expectedStatus}")
    public IssueDetailsSteps verifyStatus(IssueStatus expectedStatus) {
        assertEquals(
                expectedStatus.getValue(),
                issueDetailsPage.getStatusText(),
                "Статус задачи должен быть: " + expectedStatus.getValue()
        );
        return this;
    }

    @Step("Проверить версию задачи: {expectedVersion}")
    public IssueDetailsSteps verifyVersion(String expectedVersion) {
        assertEquals(
                expectedVersion,
                issueDetailsPage.getVersionText(),
                "Версия задачи должна быть: " + expectedVersion
        );
        return this;
    }

    @Step("Изменить статус задачи на 'В работе' и проверить сообщение об изменении")
    public IssueDetailsSteps changeStatusToInProgress() {
        issueDetailsPage
                .issueSuccessfulChangeMessageIsNotVisible()
                .changeStatusToInProgress()
                .issueSuccessfulChangeMessageIsVisible();
        return this;
    }

    @Step("Изменить статус задачи на 'Готово' и проверить сообщение об изменении")
    public IssueDetailsSteps changeStatusToDone() {
        issueDetailsPage
                .issueSuccessfulChangeMessageIsNotVisible()
                .changeStatusToDone()
                .issueSuccessfulChangeMessageIsVisible();
        return this;
    }
}
