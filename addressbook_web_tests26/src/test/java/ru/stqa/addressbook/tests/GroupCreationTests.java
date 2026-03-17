package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.addressbook.models.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupCreationTests extends TestBase {
    @ParameterizedTest
    @MethodSource("groupProvider")
    public void canCreateMultipleGroups(Group group) {
        var groupNumber = app.groups().getCount();
        app.groups().createGroup(group);
        Assertions.assertEquals(groupNumber + 1, app.groups().getCount());
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void canNotCreateGroup(Group group){
        var groupNumber = app.groups().getCount();
        app.groups().createGroup(group);
        Assertions.assertEquals(groupNumber, app.groups().getCount());
    }

    public static List<Group> groupProvider() {
        var result = new ArrayList<Group>();
        for (int i = 0; i < 3; i++) {
            result.add(new Group(randomString(), randomString(), randomString()));
        }
        for (var name : List.of("", "some name")) {
            for (var header : List.of("", "some header")) {
                for (var footer : List.of("", "some footer")) {
                    result.add(new Group(name, header, footer));
                }
            }
        }
        return result;
    }
    public static List<Group> negativeGroupProvider(){
        return new ArrayList<>(List.of(new Group().withName("group name'")));
    }
}
