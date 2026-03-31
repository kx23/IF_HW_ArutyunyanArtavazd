package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.HeaderComponent;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;

public class IssueDetailsPage extends BasePage<IssueDetailsPage> {


    public final HeaderComponent header = new HeaderComponent();

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

    public IssueDetailsPage isPageOpened() {
        issueTitle.shouldBe(visible);
        return this;
    }

    public IssueDetailsPage checkStatus(String expectedStatus) {
        issueStatus.shouldBe(visible).shouldHave(text(expectedStatus));
        return this;
    }

    public IssueDetailsPage issueSuccessfulChangeMessageIsVisible()
    {
        issueSuccessfulChangeMessage.shouldBe(visible);
        return this;
    }
    public IssueDetailsPage issueSuccessfulChangeMessageIsNotVisible()
    {
        issueSuccessfulChangeMessage.shouldNotBe(visible);
        return this;
    }


    public String getStatusText() {
        return issueStatus.shouldBe(visible).getText();
    }

    public IssueDetailsPage checkVersion(String expectedVersion) {
        issueVersion.shouldBe(visible).shouldHave(text(expectedVersion));
        return this;
    }

    public String getVersionText() {
        return issueVersion.shouldBe(visible).getText();
    }

    public IssueDetailsPage changeStatusToDone() {
        issueStatusesListButton.click();
        issueDoneStatusButton.shouldBe(visible).click();
        return this;
    }
    public IssueDetailsPage changeStatusToInProgress() {
        issueInProgressStatusButton.shouldBe(visible).click();
        return this;
    }

}