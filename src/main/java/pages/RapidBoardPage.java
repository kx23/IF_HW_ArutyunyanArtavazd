package pages;

import com.codeborne.selenide.Selenide;
import pages.components.HeaderComponent;
import pages.components.SidebarComponent;

public class RapidBoardPage extends BasePage<RapidBoardPage> {

    public final HeaderComponent header = new HeaderComponent();
    public final SidebarComponent sidebar = new SidebarComponent();
    private static final String ACTIVE_SPRINT =
            "/secure/RapidBoard.jspa?rapidView=1&projectKey=TEST";

    private static final String BACKLOG =
            "/secure/RapidBoard.jspa?rapidView=1&projectKey=TEST&view=planning.nodetail&issueLimit=100";

    @Override
    protected String getPageUrl() {
        return ACTIVE_SPRINT;
    }

    public RapidBoardPage openActiveSprint() {
        Selenide.open(ACTIVE_SPRINT);
        return this;
    }

    public RapidBoardPage openBacklog() {
        Selenide.open(BACKLOG);
        return this;
    }


}
