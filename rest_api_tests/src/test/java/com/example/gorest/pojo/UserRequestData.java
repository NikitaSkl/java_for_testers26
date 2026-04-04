package com.example.gorest.pojo;

public final class UserRequestData {
    private String name;
    private String email;
    private String gender;
    private String status;

    public UserRequestData(String name, String email, String gender, String status) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.status = status;
    }

    public UserRequestData() {
    }

    public String getName() {
        return name;
    }

    public UserRequestData setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRequestData setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public UserRequestData setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public UserRequestData setStatus(String status) {
        this.status = status;
        return this;
    }
}
