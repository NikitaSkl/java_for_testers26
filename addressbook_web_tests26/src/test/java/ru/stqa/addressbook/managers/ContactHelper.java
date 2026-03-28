package ru.stqa.addressbook.managers;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.stqa.addressbook.models.Contact;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {
    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createContact(Contact contact) {
        initContactCreation();
        fillContactInfo(contact);
        submitContactCreation();
        returnToHomePage();
    }

    public void removeContact(Contact contact) {
        openHomePage();
        initContactModification(contact);
        initContactRemoval();
    }

    private void initContactRemoval() {
        click(By.xpath("//input[@value=\"Delete\" and @name=\"update\"]"));
    }

    public void modifyContact(Contact contact, Contact modifiedContact) {
        openHomePage();
        initContactModification(contact);
        clearFirstName();
        clearLastName();
        fillContactInfo(modifiedContact);
        submitContactModification();
        returnToHomePage();
    }
    public void removeAllContacts() {
        openHomePage();
        selectAllContacts();
        initRemovalOfSelectedContacts();
    }

    private void selectAllContacts() {
        click(By.id("MassCB"));
    }

    private void clearLastName() {
        manager.driver.findElement(By.name("lastname")).clear();
    }

    private void clearFirstName() {
        manager.driver.findElement(By.name("firstname")).clear();
    }

    private void submitContactModification() {
        click(By.name("update"));
    }

    private void initContactModification(Contact contact) {
        var tr=manager.driver.findElement(By.xpath(String.format("//tr[@name='entry' and .//input[@id='%s']]",contact.id())));
        tr.findElement(By.xpath(".//a[contains(@href, 'edit.php')]")).click();
    }

    public boolean isContactPresent() {
        manager.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("home")));
        return isElementPresent(By.name("selected[]"));
    }

    private void initRemovalOfSelectedContacts() {
        click(By.xpath("//input[@value=\"Delete\"]"));
    }

    private void selectContact() {
        click(By.name("selected[]"));
    }

    private void openHomePage() {
        if (!isElementPresent(By.xpath("//img[@title=\"Edit\"]"))) {
            manager.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("home"))).click();
        }
    }

    private void fillContactInfo(Contact contact) {
        type(By.name("firstname"), contact.firstName());
        type(By.name("lastname"), contact.lastName());
        type(By.name("address"), contact.address());
        type(By.name("mobile"), contact.mobile());
        type(By.name("email"), contact.email());
        attach(By.name("photo"),contact.photo());
    }

    private void attach(By locator,String photo) {
        if (!"".equals(photo))
        type(locator, Paths.get(photo).toAbsolutePath().toString());
    }

    private void returnToHomePage() {
        manager.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("home page"))).click();
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    private void initContactCreation() {
        manager.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("add new"))).click();
    }

    public int getCount() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }


    public List<Contact> getList() {
        openHomePage();
        var result=new ArrayList<Contact>();
        var trs=manager.driver.findElements(By.name("entry"));
        for (var tr:trs){
            var id=tr.findElement(By.name("selected[]")).getAttribute("value");
            var lastName=tr.findElement(By.xpath(".//td[2]")).getText();
            var firstName=tr.findElement(By.xpath(".//td[3]")).getText();
            var address=tr.findElement(By.xpath(".//td[4]")).getText();
            var phone=tr.findElement(By.xpath(".//td[6]")).getText();
            result.add(new Contact()
                    .withId(id)
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .withAddress(address)
                    .withMobile(phone));
        }
        return result;
    }
}
