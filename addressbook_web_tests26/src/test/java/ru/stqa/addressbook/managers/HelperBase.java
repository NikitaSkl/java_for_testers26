package ru.stqa.addressbook.managers;

import org.openqa.selenium.By;

public class HelperBase {
    public ApplicationManager manager;

    public HelperBase(ApplicationManager manager) {
        this.manager = manager;
    }

    protected void click(By locator) {
        manager.driver.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        manager.driver.findElement(locator).sendKeys(text);
    }
}
