package com.example.gorest.provider;

import com.example.gorest.common.CommonFunctions;
import com.example.gorest.factories.UserDataFactory;
import com.example.gorest.pojo.UserRequestData;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserRequestDataProviders {
    public static List<UserRequestData> randomRequestUserProvider() {
        Supplier<UserRequestData> randomUserSupplier = () ->new UserDataFactory().validUser();
        return Stream.generate(randomUserSupplier).limit(3).collect(Collectors.toList());
    }

    public static List<Arguments> invalidRequestUserProvider() {
        return List.of(Arguments.of(new UserDataFactory().userWithBlankName(),
                        "name",
                        "can't be blank"),
                Arguments.of(new UserDataFactory().userWithBlankEmail(),
                        "email",
                        "can't be blank"),
                Arguments.of(new UserDataFactory().userWithBlankGender(),
                        "gender",
                        "can't be blank, can be male of female"),
                Arguments.of(new UserDataFactory().userWithBlankStatus(),
                        "status",
                        "can't be blank"));
    }
}
