package ru.stqa.addressbook.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "addressbook")
public class ContactData {
    @Id
    public int id;
    public String firstName;
    public String lastName;
    public String address;
    public String email;
    public String mobile;
    public String middleName="";
    public String nickname="";
    public String company="";
    public String title="";
    public String home="";
    public String work="";
    public String fax="";
    public String email2="";
    public String email3="";
    public String homepage="";

    public ContactData() {
    }

    public ContactData(int id, String firstName, String lastName, String address, String email, String mobile) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.mobile = mobile;
    }
}

