package ru.stqa.addressbook.managers;

import org.openqa.selenium.By;

public class LoginHelper {
    public ApplicationManager manager;
    public LoginHelper(ApplicationManager app){
        this.manager =app;
    }

    public void login(String userName, String password) {
        manager.driver.findElement(By.name("user")).click();
        manager.driver.findElement(By.name("user")).sendKeys(userName);
        manager.driver.findElement(By.name("pass")).sendKeys(password);
        manager.driver.findElement(By.xpath("(//input[@value=\'Login\'])[1]")).click();
    }
}
