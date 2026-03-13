package tests;

import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void canCreateGroup() {
        openGroupsPage();
        createGroup("group name", "group header", "group footer");
        returnToGroupPage();
    }

    @Test
    public void canCreateEmptyGroup() {
        openGroupsPage();
        createGroup("", "", "");
        returnToGroupPage();
    }

}
