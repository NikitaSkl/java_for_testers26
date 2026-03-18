package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.models.Contact;

public class ContactRemovalTests extends TestBase{

    @Test
    public void canRemoveContact(){
        if (!app.contacts().isContactPresent()){
            app.contacts().createContact(new Contact());
        }
        var contactsCount=app.contacts().getContactsCount();
        app.contacts().removeContact();
        Assertions.assertEquals(contactsCount-1,app.contacts().getContactsCount());
    }
    @Test
    public void canRemoveAllContacts(){
        if (!app.contacts().isContactPresent()){
            app.contacts().createContact(new Contact());
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0, app.contacts().getContactsCount());
    }
}
