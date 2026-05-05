package pages;

import pages.components.SidebarComponent;

public class RapidBoardPage extends BasePage<RapidBoardPage> {

    public final SidebarComponent sidebar = new SidebarComponent();
    private static final String ACTIVE_SPRINT =
            "/secure/RapidBoard.jspa?rapidView=1&projectKey=TEST";

    @Override
    protected String getPageUrl() {
        return ACTIVE_SPRINT;
    }

}
