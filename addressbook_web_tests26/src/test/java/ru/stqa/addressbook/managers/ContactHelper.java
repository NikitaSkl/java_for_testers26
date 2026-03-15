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
        manager.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("add new")));
        click(By.linkText("add new"));
    }
}
