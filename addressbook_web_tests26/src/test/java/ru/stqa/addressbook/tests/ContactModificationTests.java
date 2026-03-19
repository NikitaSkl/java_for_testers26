package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.models.Contact;

public class ContactModificationTests extends TestBase {
    @Test
    public void canModifyContact() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new Contact());
        }
        app.contacts().modifyContact(new Contact().withFirstName("modified firstName").withLastName("modified lastName"));
    }
}
