package ru.stqa.addressbook.common;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonFunctions {
    public static String randomString() {
        var random=new Random();
        Supplier<Integer> intSupplier=()->random.nextInt(26);
        return Stream
                .generate(intSupplier)
                .limit(random.nextInt(15))
                .map(integer -> 'a'+integer)
                .map(ch->Character.toString(ch))
                .collect(Collectors.joining());
    }

    public static String randomStringOfInts() {
        var random=new Random();
        Supplier<Integer> intSupplier=()->random.nextInt(9);
        return Stream
                .generate(intSupplier)
                .limit(random.nextInt(11))
                .map(integer -> '0'+integer)
                .map(ch->Character.toString(ch))
                .collect(Collectors.joining());
    }
}
