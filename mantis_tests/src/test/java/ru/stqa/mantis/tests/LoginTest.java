package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class LoginTest extends TestBase{
    @Test
    public void canLogin(){
        app.session().login("administrator","root");
        Assertions.assertTrue(app.session().isLoggedIn());
    }
}
