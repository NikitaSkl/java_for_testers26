package com.example.gorest.factories;


import com.example.gorest.pojo.UserPatchRequest;
import com.example.gorest.pojo.UserRequestData;

import java.util.Random;
import static com.example.gorest.common.CommonFunctions.*;

public class UserDataFactory {
    private static Random random = new Random();
    public UserRequestData validUser(){
        return new UserRequestData()
                .setName(generateName())
                .setEmail(generateEmail())
                .setStatus(randomStatus())
                .setGender(randomGender());
    }
    public UserRequestData userWithBlankName(){
        return validUser().setName("");
    }
    public UserRequestData userWithBlankEmail(){
        return validUser().setEmail("");
    }
    public UserRequestData userWithBlankGender(){
        return validUser().setGender("");
    }
    public UserRequestData userWithBlankStatus(){
        return validUser().setStatus("");
    }
    public UserPatchRequest nameUpdate() {
        return new UserPatchRequest()
                .setName(generateName());
    }

    private String generateName() {
        return randomString(8);
    }

    private String generateEmail() {
        return String.format("%s@gorest.test",randomString(6));
    }

    private String randomGender() {
        return random.nextBoolean()==true?"male":"female";
    }

    private String randomStatus() {
        return random.nextBoolean()==true?"active":"inactive";
    }

}
