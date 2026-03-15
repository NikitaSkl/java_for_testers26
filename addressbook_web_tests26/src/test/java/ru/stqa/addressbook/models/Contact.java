package ru.stqa.addressbook.models;

public record Contact(String firstName, String lastName, String address, String email, String mobile) {
    public Contact() {
        this("","","","","");
    }
    public Contact withFirstName(Contact contact){
        return new Contact(contact.firstName,this.lastName,this.address,this.email,this.mobile);
    }

    public Contact withLastName(Contact contact){
        return new Contact(this.firstName,contact.lastName,this.address,this.email,this.mobile);
    }
}
