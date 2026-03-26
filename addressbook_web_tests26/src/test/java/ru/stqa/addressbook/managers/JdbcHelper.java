package ru.stqa.addressbook.managers;

import ru.stqa.addressbook.models.Contact;
import ru.stqa.addressbook.models.Group;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcHelper extends HelperBase{
    public JdbcHelper(ApplicationManager manager) {
        super(manager);
    }

    public List<Group> getGroupList() {
        var result=new ArrayList<Group>();
        try (var connect = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement=connect.createStatement();
             var resultSet=statement.executeQuery("SELECT group_id, group_name, group_header, group_footer FROM group_list")) {
            while (resultSet.next()){
                result.add(new Group()
                        .withId(resultSet.getString("group_id"))
                        .withName(resultSet.getString("group_name"))
                        .withHeader((resultSet.getString("group_header")))
                        .withFooter((resultSet.getString("group_footer"))));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public List<Contact> getContactList() {
        var result=new ArrayList<Contact>();
        try (var connect = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement=connect.createStatement();
             var resultSet=statement.executeQuery("SELECT id, firstname, lastname, address, email, mobile FROM addressbook")) {
            while (resultSet.next()){
                result.add(new Contact()
                        .withId(resultSet.getString("id"))
                        .withFirstName(resultSet.getString("firstname"))
                        .withLastName(resultSet.getString("lastname"))
                        .withAddress((resultSet.getString("address")))
                        .withMobile((resultSet.getString("mobile")))
                        .withEmail((resultSet.getString("email"))));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
