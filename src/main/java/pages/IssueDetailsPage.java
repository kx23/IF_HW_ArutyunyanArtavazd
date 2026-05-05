package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class IssueDetailsPage extends BasePage<IssueDetailsPage> {

    private final SelenideElement issueTitle =
            $x("//*[@id='summary-val']").as("Заголовок задачи");

    private final SelenideElement issueSuccessfulChangeMessage =
            $x("//div[@class='aui-message closeable aui-message-success aui-will-close']").as("Сообщение о изменении задачи");

    private final SelenideElement issueId =
            $x("//*[@id='key-val']").as("Заголовок задачи");

    private final SelenideElement issueStatus =
            $x("//span[@id='status-val']/span").as("Статус задачи");

    private final SelenideElement issueVersion =
            $x("//span[@id='fixVersions-field']/a").as("Версия задачи");

    private final SelenideElement issueInProgressStatusButton =
            $x("//a[@id='action_id_21']").as("Кнопка переключения статуса на 'В работе'");

    private final SelenideElement issueStatusesListButton =
            $x("//a[@id='opsbar-transitions_more']").as("Кнопка раскрытия бизнес-процессов");


    private final SelenideElement issueDoneStatusButton =
            $x("//aui-item-link[@id='action_id_31']").as("Кнопка переключения статуса на 'Выполнено'");


    @Override
    protected String getPageUrl() {

        String _issueId=issueId.shouldBe(visible).getText();
        return "/browse/"+_issueId;
    }

    @Step("Проверить, что страница задачи открыта")
    public IssueDetailsPage isPageOpened() {
        issueTitle.shouldBe(visible);
        return this;
    }

    @Step("Проверить, что сообщение об успешном изменении задачи отображается")
    public IssueDetailsPage issueSuccessfulChangeMessageIsVisible()
    {
        issueSuccessfulChangeMessage.shouldBe(visible);
        return this;
    }

    @Step("Проверить, что сообщение об успешном изменении задачи не отображается")
    public IssueDetailsPage issueSuccessfulChangeMessageIsNotVisible()
    {
        issueSuccessfulChangeMessage.shouldNotBe(visible);
        return this;
    }


    @Step("Получить текущий статус задачи")
    public String getStatusText() {
        return issueStatus.shouldBe(visible).getText();
    }

    @Step("Получить версию задачи")
    public String getVersionText() {
        return issueVersion.shouldBe(visible).getText();
    }

    @Step("Изменить статус задачи на 'Выполнено'")
    public IssueDetailsPage changeStatusToDone() {
        issueStatusesListButton.click();
        issueDoneStatusButton.shouldBe(visible).click();
        return this;
    }

    @Step("Изменить статус задачи на 'В работе'")
    public IssueDetailsPage changeStatusToInProgress() {
        issueInProgressStatusButton.shouldBe(visible).click();
        return this;
    }

}