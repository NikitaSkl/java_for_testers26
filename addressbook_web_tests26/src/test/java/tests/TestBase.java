package tests;

import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestBase {
    protected static WebDriver driver;
    public static WebDriverWait wait;

    protected static void createGroup(String group_name, String group_header, String group_footer) {
        driver.findElement(By.name("new")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("group_name")));
        driver.findElement(By.name("group_name")).click();
        driver.findElement(By.name("group_name")).sendKeys(group_name);
        driver.findElement(By.name("group_header")).click();
        driver.findElement(By.name("group_header")).sendKeys(group_header);
        driver.findElement(By.name("group_footer")).click();
        driver.findElement(By.name("group_footer")).sendKeys(group_footer);
        driver.findElement(By.name("submit")).click();
    }

    protected static void returnToGroupPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("group page")));
        driver.findElement(By.linkText("group page")).click();
    }

    protected static void removeGroup() {
        driver.findElement(By.name("selected[]")).click();
        driver.findElement(By.name("delete")).click();
        driver.findElement(By.linkText("group page")).click();
    }

    @BeforeEach
    public void setUp() {
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

    protected boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    protected void openGroupsPage() {
        if (!isElementPresent(By.name("new"))) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("groups")));
            driver.findElement(By.linkText("groups")).click();
        }
    }

    protected boolean isGroupPresent() {
        return isElementPresent(By.name("selected[]"));
    }
}
