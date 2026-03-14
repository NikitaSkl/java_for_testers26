package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.models.Group;

public class GroupCreationTests extends TestBase {

    @Test
    public void canCreateGroup() {
        app.openGroupsPage(GroupCreationTests.this);
        app.createGroup(new Group("group name", "group header", "group footer"));
        app.returnToGroupPage();
    }

    @Test
    public void canCreateEmptyGroup() {
        app.openGroupsPage(GroupCreationTests.this);
        app.createGroup(new Group());
        app.returnToGroupPage();
    }
    @Test
    public void canCreateGroupWithNameOnly() {
        app.openGroupsPage(GroupCreationTests.this);
        app.createGroup(new Group().withName("group_name"));
        app.returnToGroupPage();
    }

}
