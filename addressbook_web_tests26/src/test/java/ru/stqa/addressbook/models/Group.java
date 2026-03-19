package ru.stqa.addressbook.models;

public record Group(String id, String name, String footer, String header) {

    public Group() {
        this("", "", "", "");
    }

    public Group withId(String id) {
        return new Group(id, this.name, footer, this.header);
    }

    public Group withName(String name) {
        return new Group(this.id, name, this.footer, this.header);
    }

    public Group withHeader(String header) {
        return new Group(this.id, this.name, this.footer, header);
    }

    public Group withFooter(String footer) {
        return new Group(this.id, this.name, footer, this.header);
    }


}