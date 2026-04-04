package com.example.gorest.provider;

import com.example.gorest.common.CommonFunctions;
import com.example.gorest.pojo.UserRequestData;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserRequestDataProviders {
    public static List<UserRequestData> randomRequestUserProvider() {
        Supplier<UserRequestData> randomUserSupplier = () -> new UserRequestData()
                .setName(CommonFunctions.randomString(9))
                .setEmail(String.format("%s@gorest.test", CommonFunctions.randomString(7)))
                .setGender("male")
                .setStatus("active");
        return Stream.generate(randomUserSupplier).limit(3).collect(Collectors.toList());
    }

    public static List<Arguments> invalidRequestUserProvider() {
        return List.of(Arguments.of(new UserRequestData()
                                .setEmail(String.format("%s@gorest.test", CommonFunctions.randomString(7)))
                                .setGender("male")
                                .setStatus("active"),
                        "name", "can't be blank"),
                Arguments.of(new UserRequestData()
                                .setName(CommonFunctions.randomString(7))
                                .setEmail("")
                                .setGender("male")
                                .setStatus("active"),
                        "email", "can't be blank"),
                Arguments.of(new UserRequestData()
                        .setName(CommonFunctions.randomString(7))
                        .setEmail(String.format("%s@gorest.test", CommonFunctions.randomString(7)))
                        .setGender("")
                        .setStatus("active"),
                        "gender", "can't be blank, can be male of female"),
                Arguments.of(new UserRequestData()
                        .setName(CommonFunctions.randomString(7))
                        .setEmail(String.format("%s@gorest.test", CommonFunctions.randomString(7)))
                        .setGender("male")
                        .setStatus(""),
                        "status", "can't be blank"));
    }
}
