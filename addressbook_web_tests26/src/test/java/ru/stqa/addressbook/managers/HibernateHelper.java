package ru.stqa.addressbook.managers;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import ru.stqa.addressbook.dto.GroupData;
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
                        //.addAnnotatedClass(Author.class)
                        .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook")
                        // Credentials
                        .setProperty(AvailableSettings.USER, "root")
                        .setProperty(AvailableSettings.PASS, "")
                        .buildSessionFactory();
    }

    public static List<Group> convertList(List<GroupData> groupData) {
        var result = new ArrayList<Group>();
        for (var groupRecord : groupData) {
            result.add(convert(groupRecord));
        }
        return result;
    }

    private static Group convert(GroupData groupRecord) {
        return new Group(String.valueOf(groupRecord.id), groupRecord.name, groupRecord.footer, groupRecord.header);
    }

    private static GroupData convert(Group group) {
        var id = group.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new GroupData(Integer.parseInt(id), group.name(), group.footer(), group.header());
    }


    public List<Group> getGroupList() {
        return convertList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupData", GroupData.class).list();
        }));
    }

    public long getGroupCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count(*) from GroupData", Long.class).getSingleResult();
        });
    }

    public void createGroup(Group group) {
        sessionFactory.inSession(session -> {
            session.beginTransaction();
            session.persist(convert(group));
            session.getTransaction().commit();
        });
    }
}


