package ru.stqa.addressbook.managers;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ApplicationManager {
    public WebDriver driver;
    public WebDriverWait wait;
    private LoginHelper session;
    public GroupHelper groupHelper;
    public ContactHelper contactHelper;

    public ContactHelper contacts(){
        if (contactHelper==null){
            contactHelper=new ContactHelper(this);
        }
        return contactHelper;
    }

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

    public void init(String browser) {
        if (driver == null) {
            if ("chrome".equals(browser)){
                driver = new ChromeDriver();
            }
            else if ("edge".equals(browser)){
                driver=new EdgeDriver();
            }
            else throw new IllegalArgumentException(String.format("Unknown browser: %s",browser));
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
