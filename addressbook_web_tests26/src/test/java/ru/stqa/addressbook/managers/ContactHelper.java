package ru.stqa.addressbook.managers;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.stqa.addressbook.models.Contact;

public class ContactHelper extends HelperBase{
    public ContactHelper(ApplicationManager manager){
        super(manager);
    }

    public void createContact(Contact contact) {
        initContactCreation();
        fillContactInfo(contact);
        submitContactCreation();
        returnToHomePage();
    }
    public void removeContact(){
        openHomePage();
        selectContact();
        deleteSelectedContacts();
    }
    public void modifyContact(Contact contact) {
        openHomePage();
        initContactModification();
        clearFirstName();
        clearLastName();
        fillContactInfo(contact);
        submitContactModification();
        returnToHomePage();
    }

    private void clearLastName() {
        manager.driver.findElement(By.name("firstname")).clear();
    }

    private void clearFirstName() {
        manager.driver.findElement(By.name("lastname")).clear();
    }

    private void submitContactModification() {
        click(By.name("update"));
    }

    private void initContactModification() {
        click(By.xpath("//img[@title=\"Edit\"]"));
    }

    public boolean isContactPresent(){
        manager.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("home")));
        return isElementPresent(By.name("selected[]"));
    }

    private void deleteSelectedContacts() {
        click(By.xpath("//input[@value=\"Delete\"]"));
    }

    private void selectContact() {
        click(By.name("selected[]"));
    }

    private void openHomePage() {
        manager.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("home"))).click();
    }

    private void fillContactInfo(Contact contact) {
        type(By.name("firstname"),contact.firstName());
        type(By.name("lastname"),contact.lastName());
        type(By.name("address"), contact.address());
        type(By.name("mobile"), contact.mobile());
        type(By.name("email"), contact.email());
    }
    private void returnToHomePage() {
        click(By.linkText("home page"));
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    private void initContactCreation() {
        manager.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("add new"))).click();
    }


}
