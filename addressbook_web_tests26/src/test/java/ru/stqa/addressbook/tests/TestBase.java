package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.BeforeEach;
import ru.stqa.addressbook.managers.ApplicationManager;

import java.util.Random;

public class TestBase {
    public static ApplicationManager app;

    public static String randomString() {
        var random=new Random();
        int length=random.nextInt(15);
        var result=new StringBuilder();
        for (int i = 0; i < length ; i++) {
            result.append((char)('a'+(random.nextInt(26))));
        }
        return result.toString();
    }
    public static String randomStringOfInts() {
        var random=new Random();
        int length=random.nextInt(11);
        var result=new StringBuilder();
        for (int i = 0; i < length ; i++) {
            result.append((char)('0'+(new Random().nextInt(9))));
        }
        return result.toString();
    }

    @BeforeEach
    public void setUp() {
        if (app==null){
            app=new ApplicationManager();
        }
        app.init(System.getProperty("browser","chrome"));
    }

}
