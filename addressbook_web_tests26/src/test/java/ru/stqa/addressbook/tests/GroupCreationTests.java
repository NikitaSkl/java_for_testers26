package ru.stqa.addressbook.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.addressbook.models.Group;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {
    @ParameterizedTest
    @MethodSource("groupProvider")
    public void canCreateMultipleGroups(Group group) {
        var groupList = app.groups().getList();
        app.groups().createGroup(group);
        var actualGroupList = app.groups().getList();
        Comparator<Group> compareById = ((group1, group2) -> Integer.parseInt(group1.id()) - Integer.parseInt(group2.id()));
        actualGroupList.sort(compareById);
        groupList.add(group.withId(actualGroupList.get(actualGroupList.size() - 1).id()).withFooter("").withHeader(""));
        groupList.sort(compareById);
        Assertions.assertEquals(groupList, actualGroupList);
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void canNotCreateGroup(Group group) {
        var groupList = app.groups().getList();
        app.groups().createGroup(group);
        var actualGroupList = app.groups().getList();
        Assertions.assertEquals(groupList, actualGroupList);
    }

    public static List<Group> groupProvider() throws IOException {
        var result = new ArrayList<Group>();
        var json="";
        try (var reader=new FileReader("groups.json");
             var breader=new BufferedReader(reader)){
            var line = breader.readLine();
            while (line!=null){
                json=json+line;
                line = breader.readLine();
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(json, new TypeReference<List<Group>>() {
        });

        result.addAll(value);
        /*for (var name : List.of("", "some name")) {
            for (var header : List.of("", "some header")) {
                for (var footer : List.of("", "some footer")) {
                    result.add(new Group("", name, header, footer));
                }
            }
        }*/
        return result;
    }

    public static List<Group> negativeGroupProvider() {
        return new ArrayList<>(List.of(new Group().withName("group name'")));
    }
}
