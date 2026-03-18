package ru.stqa.addressbook.models;

public record Contact(String firstName, String lastName, String address, String email, String mobile) {
    public Contact() {
        this("","","","","");
    }
    public Contact withFirstName(String name){
        return new Contact(name,this.lastName,this.address,this.email,this.mobile);
    }

    public Contact withLastName(String name){
        return new Contact(this.firstName,name,this.address,this.email,this.mobile);
    }
}
