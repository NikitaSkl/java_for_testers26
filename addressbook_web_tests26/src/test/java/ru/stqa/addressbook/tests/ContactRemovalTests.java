package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.models.Contact;

public class ContactRemovalTests extends TestBase{
    @Test
    public void canRemoveContact(){
        if (!app.contacts().isContactPresent()){
            app.contacts().createContact(new Contact());
        }
        app.contacts().removeContact();
    }
}
