package ru.stqa.addressbook.tests;

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
        var contactList=app.contacts().getList();
        var index=new Random().nextInt(contactList.size()-1);
        app.contacts().modifyContact(contactList.get(index),new Contact().withFirstName("modified firstName").withLastName("modified lastName"));
        Comparator<Contact> compareById=(c1,c2)->Integer.parseInt(c1.id())-Integer.parseInt(c2.id());
        contactList.set(index,contactList.get(index).withFirstName("modified firstName").withLastName("modified lastName"));
        contactList.sort(compareById);
        var actualContactList=app.contacts().getList();
        Assertions.assertEquals(contactList,actualContactList);
    }
}
