package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.models.Contact;
import ru.stqa.addressbook.models.Group;

import java.util.ArrayList;
import java.util.List;

public class ContactRemovalTests extends TestBase{
    public static List<Group> randomGroups() {
        var result= new ArrayList<Group>(5);
        for (int i = 0; i < 3 ; i++) {
            result.add(new Group(randomString(),randomString(),randomString()));
        }
        return result;
    }

    @Test
    public void canRemoveContact(){
        if (!app.contacts().isContactPresent()){
            app.contacts().createContact(new Contact());
        }
        app.contacts().removeContact();
    }
}
