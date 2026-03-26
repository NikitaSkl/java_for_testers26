package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.common.CommonFunctions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.addressbook.models.Contact;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase{

    public static List<Contact> contactProvider() {
        var result=new ArrayList<Contact>();
        for (int i = 0; i < 3; i++) {
            result.add(new Contact()
                    .withFirstName(CommonFunctions.randomString())
                    .withLastName(CommonFunctions.randomString())
                    .withAddress(CommonFunctions.randomString())
                    .withMobile(CommonFunctions.randomStringOfInts())
                    .withPhoto(randomFile("src/test/resources/images")));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(Contact contact){
        var contactsList=app.hbm().getContactList();
        Comparator<Contact> compareById=(c1,c2)->Integer.parseInt(c1.id())-Integer.parseInt(c2.id());
        app.contacts().createContact(contact);
        var actualContactList=app.hbm().getContactList();
        actualContactList.sort(compareById);
        var newContactList=new ArrayList<>(contactsList);
        String maxId = actualContactList.get(actualContactList.size() - 1).id();
        newContactList.add(contact.withId(maxId).withPhoto(""));
        newContactList.sort(compareById);
        Assertions.assertEquals(newContactList,actualContactList);
    }
}
