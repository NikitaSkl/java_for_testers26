package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.models.Contact;
import ru.stqa.addressbook.models.Group;

import java.util.ArrayList;
import java.util.List;

public class ContactCreationTests extends TestBase{
    public static List<Group> randomGroups() {
        var result= new ArrayList<Group>(5);
        for (int i = 0; i < 3 ; i++) {
            result.add(new Group(randomString(),randomString(),randomString()));
        }
        return result;
    }

    @Test
    public void canCreateContact(){
        app.contacts().createContact(new Contact("John","Doe","Moscow","test@test.test","1234567890"));
    }

    @Test
    public void canCreateEmptyContact(){
        app.contacts().createContact(new Contact());
    }
}
