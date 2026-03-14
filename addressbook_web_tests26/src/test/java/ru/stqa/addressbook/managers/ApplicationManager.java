package ru.stqa.addressbook.managers;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ApplicationManager {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private LoginHelper session;
    public GroupHelper groupHelper;

    public LoginHelper session() {
        if (session == null) {
            session = new LoginHelper(this);
        }
        return session;
    }

    public GroupHelper groups() {
        if (groupHelper ==null){
            groupHelper =new GroupHelper(this);
        }
        return groupHelper;
    }

    public void init() {
        if (driver == null) {
            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get("http://localhost/addressbook");
            session().login("admin", "secret");
        }
    }

    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }
}
