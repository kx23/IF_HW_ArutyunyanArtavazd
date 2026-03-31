package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class SidebarComponent {

    private final SelenideElement allTasksButton =
            $x("//a[@data-link-id='com.atlassian.jira.jira-projects-issue-navigator:sidebar-issue-navigator']")
                    .as("Кнопка 'Задачи' в сайдбаре");

    public void openAllTasks() {
        allTasksButton.shouldBe(visible).click();
    }
}