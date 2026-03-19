package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.addressbook.models.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactCreationTests extends TestBase{

    public static List<Contact> contactProvider() {
        var result=new ArrayList<Contact>();
        for (int i = 0; i < 5; i++) {
            result.add(new Contact().withFirstName(randomString()).withLastName(randomString()).withAddress(randomString()).withMobile(randomStringOfInts()));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(Contact contact){
        var contactsCount=app.contacts().getCount();
        app.contacts().createContact(contact);
        Assertions.assertEquals(contactsCount+1,app.contacts().getCount());
    }
}
