package com.example.gorest.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserPatchRequest {
    private String name;
    private String email;
    private String gender;
    private String status;

    public UserPatchRequest(String name, String email, String gender, String status) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.status = status;
    }

    public UserPatchRequest() {
    }

    public String getName() {
        return name;
    }

    public UserPatchRequest setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserPatchRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public UserPatchRequest setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public UserPatchRequest setStatus(String status) {
        this.status = status;
        return this;
    }
}
