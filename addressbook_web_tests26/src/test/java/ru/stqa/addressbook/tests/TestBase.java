package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.BeforeEach;
import ru.stqa.addressbook.managers.ApplicationManager;

public class TestBase {
    public static ApplicationManager app;

    @BeforeEach
    public void setUp() {
        if (app==null){
            app=new ApplicationManager();
        }
        app.init(System.getProperty("browser","chrome"));
    }

}
