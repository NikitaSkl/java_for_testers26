package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.models.Group;

import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class GroupModificationTests extends TestBase{

    @Test
    public void canModifyGroup() {
        if (app.hbm().getGroupCount()==0) {
            app.hbm().createGroup(new Group().withName("name").withHeader("header").withFooter("footer"));
        }
        var groupList=app.hbm().getGroupList();
        var random=new Random();
        var index=random.nextInt(groupList.size());
        app.groups().modifyGroup(groupList.get(index),new Group().withName("modified name"));
        Comparator<Group> compareById = ((group1,group2)->Integer.parseInt(group1.id())-Integer.parseInt(group2.id()));
        var newGroupList=app.hbm().getGroupList();
        newGroupList.sort(compareById);
        groupList.set(index,new Group().withName("modified name").withId(groupList.get(index).id()));
        groupList.sort(compareById);
        Assertions.assertEquals(groupList,newGroupList);
    }
}
