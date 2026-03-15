package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.models.Contact;

public class ContactCreationTests extends TestBase{
    @Test
    public void canCreateContact(){
        app.contacts().createContact(new Contact("John","Doe","Moscow","test@test.test","1234567890"));
    }
}
