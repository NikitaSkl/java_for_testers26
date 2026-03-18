package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.BeforeEach;
import ru.stqa.addressbook.managers.ApplicationManager;

import java.util.Random;

public class TestBase {
    public static ApplicationManager app;

    public static String randomString() {
        int length=new Random().nextInt(15);
        String result="";
        for (int i = 0; i < length ; i++) {
            result=result+(char)('a'+(new Random().nextInt(26)));
        }
        return result;
    }
    public static String randomStringOfInts() {
        int length=new Random().nextInt(11);
        String result="";
        for (int i = 0; i < length ; i++) {
            result=result+(char)('0'+(new Random().nextInt(9)));
        }
        return result;
    }

    @BeforeEach
    public void setUp() {
        if (app==null){
            app=new ApplicationManager();
        }
        app.init(System.getProperty("browser","chrome"));
    }

}
