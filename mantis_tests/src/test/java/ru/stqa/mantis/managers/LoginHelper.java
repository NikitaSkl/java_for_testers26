package ru.stqa.mantis.managers;

import org.openqa.selenium.By;

public class LoginHelper extends HelperBase{
    public LoginHelper(ApplicationManager manager){
        super(manager);
    }

    public void login(String username, String password) {
        type(By.name("username"),username);
        click(By.xpath("(//input[@value=\'Login\'])"));
        type(By.name("password"),password);
        click(By.xpath("(//input[@value=\'Login\'])"));
    }

    public boolean isLoggedIn() {
        return isElementPresent(By.className("user-info"));
    }

}
