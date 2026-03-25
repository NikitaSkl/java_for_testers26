package ru.stqa.addressbook.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "group_list")
public class GroupData {
    @Id
    @Column(name = "group_id")
    public int id;
    @Column(name = "group_name")
    public String name;
    @Column(name = "group_header")
    public String header;
    @Column(name = "group_footer")
    public String footer;

    public GroupData() {
    }

    public GroupData(int id, String name, String footer, String header) {
        this.id = id;
        this.name = name;
        this.footer = footer;
        this.header = header;
    }
}
