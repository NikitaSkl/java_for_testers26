package ru.stqa.addressbook.managers;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import ru.stqa.addressbook.dto.ContactData;
import ru.stqa.addressbook.dto.GroupData;
import ru.stqa.addressbook.models.Contact;
import ru.stqa.addressbook.models.Group;

import java.util.ArrayList;
import java.util.List;

public class HibernateHelper extends HelperBase {
    SessionFactory sessionFactory;

    public HibernateHelper(ApplicationManager manager) {
        super(manager);
        sessionFactory =
                new Configuration()
                        .addAnnotatedClass(GroupData.class)
                        .addAnnotatedClass(ContactData.class)
                        .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook")
                        // Credentials
                        .setProperty(AvailableSettings.USER, "root")
                        .setProperty(AvailableSettings.PASS, "")
                        .buildSessionFactory();
    }

    public static List<Group> convertGroupList(List<GroupData> groupData) {
        var result = new ArrayList<Group>();
        for (var groupRecord : groupData) {
            result.add(convertGroup(groupRecord));
        }
        return result;
    }
    public static List<Contact> convertContactList(List<ContactData> contactData) {
        var result = new ArrayList<Contact>();
        for (var contactRecord : contactData) {
            result.add(convertContact(contactRecord));
        }
        return result;
    }

    private static Contact convertContact(ContactData contactRecord) {
        return new Contact(String.valueOf(contactRecord.id),contactRecord.firstName, contactRecord.lastName, contactRecord.address, contactRecord.email, contactRecord.mobile,"");
    }

    private static ContactData convertContact(Contact contact) {
        var id = contact.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new ContactData(Integer.parseInt(id), contact.firstName(), contact.lastName(), contact.address(), contact.email(),contact.mobile());
    }
    private static Group convertGroup(GroupData groupRecord) {
        return new Group(String.valueOf(groupRecord.id), groupRecord.name, groupRecord.footer, groupRecord.header);
    }

    private static GroupData convertGroup(Group group) {
        var id = group.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new GroupData(Integer.parseInt(id), group.name(), group.footer(), group.header());
    }


    public List<Group> getGroupList() {
         return  convertGroupList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupData", GroupData.class).list();
        }));
    }
    public List<Contact> getContactList() {
        return  convertContactList(sessionFactory.fromSession(session -> {
            return session.createQuery("from ContactData", ContactData.class).list();
        }));
    }
    public long getGroupCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count(*) from GroupData", Long.class).getSingleResult();
        });
    }
    public long getContactCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count(*) from ContactData", Long.class).getSingleResult();
        });
    }
    public void createGroup(Group group) {
        sessionFactory.inSession(session -> {
            session.beginTransaction();
            session.persist(convertGroup(group));
            session.getTransaction().commit();
        });
    }
    public void createContact(Contact contact) {
        sessionFactory.inSession(session -> {
            session.beginTransaction();
            session.persist(convertContact(contact));
            session.getTransaction().commit();
        });
    }
}


