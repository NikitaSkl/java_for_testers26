package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.models.Group;

public class GroupCreationTests extends TestBase {

    @Test
    public void canCreateGroup() {
        var groupNumber=app.groups().getCount();
        app.groups().createGroup(new Group("group name", "group header", "group footer"));
        Assertions.assertEquals(groupNumber+1,app.groups().getCount());
    }

    @Test
    public void canCreateEmptyGroup() {
        app.groups().createGroup(new Group());
    }
    @Test
    public void canCreateGroupWithNameOnly() {
        app.groups().createGroup(new Group().withName("group_name"));
    }

}
