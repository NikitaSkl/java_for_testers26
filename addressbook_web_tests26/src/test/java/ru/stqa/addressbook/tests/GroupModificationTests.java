package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.models.Group;

public class GroupModificationTests extends TestBase{
    @Test
    public void canModifyGroup() {
        app.groups().modifyGroup(new Group().withName("modified name"));
    }
}
