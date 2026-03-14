package ru.stqa.addressbook.managers;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.stqa.addressbook.models.Group;

public class GroupHelper {
    public ApplicationManager manager;

    public GroupHelper(ApplicationManager manager) {
        this.manager = manager;
    }
    public void openGroupsPage() {
        if (!manager.isElementPresent(By.name("new"))) {
            manager.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("groups")));
            manager.driver.findElement(By.linkText("groups")).click();
        }
    }
    public void createGroup(Group group) {
        openGroupsPage();
        manager.driver.findElement(By.name("new")).click();
        manager.wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("group_name")));
        manager.driver.findElement(By.name("group_name")).click();
        manager.driver.findElement(By.name("group_name")).sendKeys(group.name());
        manager.driver.findElement(By.name("group_header")).click();
        manager.driver.findElement(By.name("group_header")).sendKeys(group.header());
        manager.driver.findElement(By.name("group_footer")).click();
        manager.driver.findElement(By.name("group_footer")).sendKeys(group.footer());
        manager.driver.findElement(By.name("submit")).click();
    }

    public void returnToGroupPage() {
        manager.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("group page")));
        manager.driver.findElement(By.linkText("group page")).click();
    }

    public void removeGroup() {
        openGroupsPage();
        manager.driver.findElement(By.name("selected[]")).click();
        manager.driver.findElement(By.name("delete")).click();
        manager.driver.findElement(By.linkText("group page")).click();
    }

    public boolean isGroupPresent() {
        return manager.isElementPresent(By.name("selected[]"));
    }


}
