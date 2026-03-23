package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.common.CommonFunctions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.models.Contact;

import java.util.Comparator;
import java.util.Random;

public class ContactModificationTests extends TestBase {
    @Test
    public void canModifyContact() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new Contact());
        }
        var contactList = app.contacts().getList();
        int index;
        if (app.contacts().getCount() == 1) index = 0;
        else index = new Random().nextInt(contactList.size() - 1);
        String newFirstName = CommonFunctions.randomString(), newLastname = CommonFunctions.randomString();
        app.contacts().modifyContact(contactList.get(index), new Contact().withFirstName(newFirstName).withLastName(newLastname).withPhoto(randomFile("src/test/resources/images")));
        Comparator<Contact> compareById = (c1, c2) -> Integer.parseInt(c1.id()) - Integer.parseInt(c2.id());
        contactList.set(index, contactList.get(index).withFirstName(newFirstName).withLastName(newLastname));
        contactList.sort(compareById);
        var actualContactList = app.contacts().getList();
        actualContactList.sort(compareById);
        Assertions.assertEquals(contactList, actualContactList);
    }
}
