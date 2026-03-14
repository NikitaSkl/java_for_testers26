package ru.stqa.addressbook.managers;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.addressbook.models.Group;
import ru.stqa.addressbook.tests.TestBase;

import java.time.Duration;

public class ApplicationManager {
    public static WebDriver driver;
    public static WebDriverWait wait;

    public void createGroup(Group group) {
        driver.findElement(By.name("new")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("group_name")));
        driver.findElement(By.name("group_name")).click();
        driver.findElement(By.name("group_name")).sendKeys(group.name());
        driver.findElement(By.name("group_header")).click();
        driver.findElement(By.name("group_header")).sendKeys(group.header());
        driver.findElement(By.name("group_footer")).click();
        driver.findElement(By.name("group_footer")).sendKeys(group.footer());
        driver.findElement(By.name("submit")).click();
    }

    public void returnToGroupPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("group page")));
        driver.findElement(By.linkText("group page")).click();
    }

    public void removeGroup() {
        driver.findElement(By.name("selected[]")).click();
        driver.findElement(By.name("delete")).click();
        driver.findElement(By.linkText("group page")).click();
    }

    public void init() {
        if (driver == null) {
            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get("http://localhost/addressbook");
            driver.findElement(By.name("user")).click();
            driver.findElement(By.name("user")).sendKeys("admin");
            driver.findElement(By.name("pass")).sendKeys("secret");
            driver.findElement(By.xpath("(//input[@value=\'Login\'])[1]")).click();
        }
    }

    public boolean isGroupPresent(TestBase testBase) {
        return testBase.app.isElementPresent(By.name("selected[]"));
    }

    public void openGroupsPage(TestBase testBase) {
        if (!testBase.app.isElementPresent(By.name("new"))) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("groups")));
            driver.findElement(By.linkText("groups")).click();
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
