package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.models.Group;

public class GroupCreationTests extends TestBase {

    @Test
    public void canCreateGroup() {
        app.groups().createGroup(new Group("group name", "group header", "group footer"));
        app.groups().returnToGroupPage();
    }

    @Test
    public void canCreateEmptyGroup() {
        app.groups().createGroup(new Group());
        app.groups().returnToGroupPage();
    }
    @Test
    public void canCreateGroupWithNameOnly() {
        app.groups().createGroup(new Group().withName("group_name"));
        app.groups().returnToGroupPage();
    }

}
