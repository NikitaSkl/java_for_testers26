package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import ru.stqa.addressbook.managers.ApplicationManager;

import java.io.*;
import java.util.Properties;
import java.util.Random;

public class TestBase {
    public static ApplicationManager app;

    protected static String randomFile(String dir) {
        var random=new Random();
        var files=new File(dir).listFiles();
        return files[random.nextInt(files.length)].getAbsolutePath().toString();
    }

    @BeforeEach
    public void setUp() throws IOException {
        if (app==null){
            app=new ApplicationManager();
        }
        var properties=new Properties();
        properties.load(new FileReader(System.getProperty("target","local.properties")));
        app.init(System.getProperty("browser","chrome"),properties);
    }
    @AfterEach
    public void checkDbConsistency(){
        app.jdbc().checkConsistency();
    }

}
