package ru.stqa.addressbook.models;

public record Contact(String id,
                      String firstName,
                      String lastName,
                      String address,
                      String email,
                      String mobile,
                      String photo) {
    public Contact() {
        this("", "", "", "", "", "", "");
    }
    public Contact withId(String id){
        return new Contact(id, this.firstName, this.lastName, this.address, this.email, this.mobile, this.photo);
    }
    public Contact withFirstName(String firstName){
        return new Contact(this.id, firstName, this.lastName, this.address, this.email, this.mobile, this.photo);
    }
    public Contact withLastName(String lastName){
        return new Contact(this.id, this.firstName, lastName, this.address, this.email, this.mobile, this.photo);
    }
    public Contact withAddress(String address){
        return new Contact(this.id, this.firstName, this.lastName, address, this.email, this.mobile, this.photo);
    }
    public Contact withMobile(String mobile){
        return new Contact(this.id, this.firstName, this.lastName, this.address, this.email, mobile, this.photo);
    }
    public Contact withPhoto(String photo){
        return new Contact(this.id, this.firstName, this.lastName, this.address, this.email, mobile, photo);
    }
}
