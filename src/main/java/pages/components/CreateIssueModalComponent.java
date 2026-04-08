package pages.components;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CreateIssueModalComponent {

    private final SelenideElement summaryInput =
            $x("//input[@id='summary']")
                    .as("Поле ввода темы");

    private final SelenideElement issueTypeInput =
            $x("//input[@id='issuetype-field']")
                    .as("Поле ввода для типов задач");

    private final SelenideElement environmentVisualTypeButton =
            $x("//label[text()='Окружение']/../descendant::button[text()='Визуальный']")
                    .as("Кнопка визуального режима окружения");

    private final SelenideElement descriptionVisualTypeButton =
            $x("//label[text()='Описание']/../descendant::button[text()='Визуальный']")
                    .as("Кнопка визуального режима описания");

    private final SelenideElement submitButton =
            $x("//input[@id='create-issue-submit']")
                    .as("Кнопка создания задачи");

    private final SelenideElement descriptionIframe =
            $x("//label[text()='Описание']/../descendant::iframe")
                    .as("Iframe описания");

    private final SelenideElement environmentIframe =
            $x("//label[text()='Окружение']/../descendant::iframe")
                    .as("Iframe окружения");



    private boolean isVisualModeActive(SelenideElement button) {
        return "true".equals(button.getAttribute("aria-pressed"));
    }

    private void ensureVisualMode(SelenideElement button) {
        if (!isVisualModeActive(button)) {
            button.click();
        }
    }

    public CreateIssueModalComponent ensureDescriptionVisualMode() {
        ensureVisualMode(descriptionVisualTypeButton);
        return this;
    }

    public CreateIssueModalComponent ensureEnvironmentVisualMode() {
        ensureVisualMode(environmentVisualTypeButton);
        return this;
    }

    public CreateIssueModalComponent fillSummary(String summary) {
        summaryInput.shouldBe(visible).setValue(summary);
        return this;
    }

    private void fillTinyMce(SelenideElement iframe, String text) {
        switchTo().frame(iframe);

        SelenideElement editorBody = $("#tinymce");
        editorBody.shouldBe(visible).setValue(text);

        switchTo().defaultContent();
    }

    public CreateIssueModalComponent fillDescription(String text) {
        ensureDescriptionVisualMode();
        fillTinyMce(descriptionIframe, text);
        return this;
    }

    public CreateIssueModalComponent fillEnvironment(String text) {
        ensureEnvironmentVisualMode();
        fillTinyMce(environmentIframe, text);
        return this;
    }

    public CreateIssueModalComponent selectIssueType(String typeName) {
        issueTypeInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        issueTypeInput.sendKeys(Keys.DELETE);
        issueTypeInput.setValue(typeName).pressEnter();
        return this;
    }

    public void submit() {
        submitButton.shouldBe(visible).click();
    }
}