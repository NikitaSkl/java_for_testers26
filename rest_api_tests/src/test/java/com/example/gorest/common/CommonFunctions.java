package com.example.gorest.common;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonFunctions {
    public static String randomString(int length) {
        var random=new Random();
        Supplier<Integer> intSupplier=()->random.nextInt(26);
        return Stream
                .generate(intSupplier)
                .limit(random.nextInt(length)+1)
                .map(integer -> 'a'+integer)
                .map(ch->Character.toString(ch))
                .collect(Collectors.joining());
    }

    public static String randomStringOfInts(int length) {
        var random=new Random();
        Supplier<Integer> intSupplier=()->random.nextInt(9);
        return Stream
                .generate(intSupplier)
                .limit(random.nextInt(length))
                .map(integer -> '0'+integer)
                .map(ch->Character.toString(ch))
                .collect(Collectors.joining());
    }
}
