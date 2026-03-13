package tests;

import org.junit.jupiter.api.Test;
import tests.models.Group;

public class GroupCreationTests extends TestBase {

    @Test
    public void canCreateGroup() {
        openGroupsPage();
        createGroup(new Group("group name", "group header", "group footer"));
        returnToGroupPage();
    }

    @Test
    public void canCreateEmptyGroup() {
        openGroupsPage();
        createGroup(new Group());
        returnToGroupPage();
    }
    @Test
    public void canCreateGroupWithNameOnly() {
        openGroupsPage();
        createGroup(new Group().withName("group_name"));
        returnToGroupPage();
    }

}
