package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.models.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupModificationTests extends TestBase{
    public static List<Group> randomGroups() {
        var result= new ArrayList<Group>(5);
        for (int i = 0; i < 3 ; i++) {
            result.add(new Group(randomString(),randomString(),randomString()));
        }
        return result;
    }

    @Test
    public void canModifyGroup() {
        app.groups().modifyGroup(new Group().withName("modified name"));
    }
}
