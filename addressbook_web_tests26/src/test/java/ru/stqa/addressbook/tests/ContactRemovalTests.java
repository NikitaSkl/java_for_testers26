package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.models.Contact;

import java.util.Random;

public class ContactRemovalTests extends TestBase{

    @Test
    public void canRemoveContact(){
        if (app.hbm().getContactCount()==0){
            app.hbm().createContact(new Contact());
        }
        var contactsList=app.hbm().getContactList();
        var random=new Random();
        var index= random.nextInt(contactsList.size());
        app.contacts().removeContact(contactsList.get(index));
        var actualContactList=app.hbm().getContactList();
        var newContactList=contactsList;
        newContactList.remove(index);
        Assertions.assertEquals(newContactList,actualContactList);
    }
    @Test
    public void canRemoveAllContacts(){
        if (app.jdbc().getContactList().size()==0){
            app.contacts().createContact(new Contact());
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0, app.contacts().getCount());
    }
}
