package ru.stqa.addressbook.managers;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.stqa.addressbook.models.Group;

public class GroupHelper extends HelperBase {

    public GroupHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createGroup(Group group) {
        openGroupsPage();
        initGroupCreation();
        fillGroupInfo(group);
        submitGroupCreation();
        returnToGroupPage();
    }

    public void removeGroup() {
        openGroupsPage();
        selectGroup();
        deleteSelectedGroups();
        returnToGroupPage();
    }

    public void modifyGroup(Group group) {
        openGroupsPage();
        selectGroup();
        initGroupModification();
        clearGroupName();
        fillGroupInfo(group);
        submitGroupModification();
        returnToGroupPage();
    }

    private void clearGroupName() {
        manager.driver.findElement(By.name("group_name")).clear();
    }

    private void initGroupModification() {
        click(By.name("edit"));
    }

    private void deleteSelectedGroups() {
        click(By.name("delete"));
    }

    private void selectGroup() {
        click(By.name("selected[]"));
    }

    private void initGroupCreation() {
        click(By.name("new"));
    }

    private void fillGroupInfo(Group group) {
        manager.wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("group_name")));
        click(By.name("group_name"));
        type(By.name("group_name"), group.name());
        click(By.name("group_header"));
        type(By.name("group_header"), group.header());
        click(By.name("group_footer"));
        type(By.name("group_footer"), group.footer());
    }

    public void openGroupsPage() {
        if (!manager.isElementPresent(By.name("new"))) {
            manager.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("groups")));
            click(By.linkText("groups"));
        }
    }

    private void submitGroupCreation() {
        click(By.name("submit"));
    }

    private void submitGroupModification() {
        click(By.name("update"));
    }

    public void returnToGroupPage() {
        manager.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("group page")));
        click(By.linkText("group page"));
    }

    public boolean isGroupPresent() {
        return manager.isElementPresent(By.name("selected[]"));
    }
}
