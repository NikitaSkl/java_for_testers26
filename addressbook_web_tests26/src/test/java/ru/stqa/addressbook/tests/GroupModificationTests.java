package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.models.Group;

public class GroupModificationTests extends TestBase{

    @Test
    public void canModifyGroup() {
        if (!app.groups().isGroupPresent()) {
            app.groups().createGroup(new Group("name", "header", "footer"));
        }
        app.groups().modifyGroup(new Group().withName("modified name"));
    }
}
