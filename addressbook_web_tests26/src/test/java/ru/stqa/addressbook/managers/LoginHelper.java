package ru.stqa.addressbook.managers;

import org.openqa.selenium.By;
import ru.stqa.addressbook.tests.TestBase;

public class LoginHelper extends HelperBase {
    public LoginHelper(ApplicationManager manager){
        super(manager);
    }

    public void login(String userName, String password) {
        type(By.name("user"),userName);
        type(By.name("pass"),password);
        click(By.xpath("(//input[@value=\'Login\'])[1]"));
    }
}
