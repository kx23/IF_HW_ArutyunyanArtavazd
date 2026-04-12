package steps;

import utils.ScenarioContext;
import data.TestData;
import io.cucumber.java.ru.*;
import pages.IssueDetailsPage;
import pages.IssuesSearchPage;

public class CreateIssueSteps {
    private final IssuesSearchPage issuesSearchPage = new IssuesSearchPage();
    private final IssueDetailsPage issueDetailsPage = new IssueDetailsPage();

    @И("пользователь создаёт задачу типа Задача")
    public void createTask() {
        issuesSearchPage.header
                .clickCreateIssue()
                .selectIssueType(TestData.ISSUE_TYPE_TASK)
                .fillSummary(TestData.NEW_TASK_SUMMARY_TEXT)
                .fillDescription(TestData.NEW_TASK_DESCRIPTION_TEXT)
                .submit();
    }

    @И("пользователь создаёт баг")
    public void createBug() {
        issueDetailsPage.header
                .clickCreateIssue()
                .selectIssueType(TestData.ISSUE_TYPE_BUG)
                .ensureDescriptionVisualMode()
                .ensureEnvironmentVisualMode()
                .fillSummary(TestData.NEW_TASK_SUMMARY_TEXT)
                .fillDescription(TestData.NEW_TASK_DESCRIPTION_TEXT)
                .fillEnvironment(TestData.NEW_TASK_ENVIRONMENT_TEXT)
                .submit();

        String newIssueId = issueDetailsPage.header.getSuccessfulCreatedIssueID();
        ScenarioContext.put(ScenarioContext.NEW_ISSUE_ID, newIssueId);
    }

    @И("пользователь переходит на страницу созданной задачи")
    public void goToCreatedIssuePage() {
        issueDetailsPage.header.goToCreatedIssuePage();
    }
}