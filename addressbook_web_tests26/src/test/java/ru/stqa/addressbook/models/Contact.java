package ru.stqa.addressbook.models;

public record Contact(String firstName, String lastName, String address, String email, String mobile) {
    public Contact() {
        this("","","","","");
    }
    public Contact withFirstName(String firstName){
        return new Contact(firstName,this.lastName,this.address,this.email,this.mobile);
    }

    public Contact withLastName(String lastName){
        return new Contact(this.firstName,lastName,this.address,this.email,this.mobile);
    }
    public Contact withAddress(String address){
        return new Contact(this.firstName,this.lastName,address,this.email,this.mobile);
    }
    public Contact withMobile(String mobile){
        return new Contact(this.firstName,this.lastName,this.address,this.email,mobile);
    }
}
