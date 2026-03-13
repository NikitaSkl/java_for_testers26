package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GroupCreationTest {
    private static WebDriver driver;

    @BeforeEach
    public void setUp() {
        if (driver == null) {
            driver = new ChromeDriver();
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get("http://localhost/addressbook");
            driver.findElement(By.name("user")).click();
            driver.findElement(By.name("user")).sendKeys("admin");
            driver.findElement(By.name("pass")).sendKeys("secret");
            driver.findElement(By.xpath("(//input[@value=\'Login\'])[1]")).click();
        }
    }

   /* @AfterAll
    public static void tearDown() {
        // driver.findElement(By.linkText("Logout")).click();

    }*/

    @Test
    public void canCreateGroup() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        if (!isElementPresent(By.name("new"))) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("groups")));
            driver.findElement(By.linkText("groups")).click();

        }
        driver.findElement(By.name("new")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("group_name")));
        driver.findElement(By.name("group_name")).click();
        driver.findElement(By.name("group_name")).sendKeys("group name");
        driver.findElement(By.name("group_header")).click();
        driver.findElement(By.name("group_header")).sendKeys("group header");
        driver.findElement(By.name("group_footer")).click();
        driver.findElement(By.name("group_footer")).sendKeys("group footer");
        driver.findElement(By.name("submit")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("group page")));
        driver.findElement(By.linkText("group page")).click();
    }


    @Test
    public void canCreateEmptyGroup() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        if (!isElementPresent(By.name("new"))) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("groups")));
            driver.findElement(By.linkText("groups")).click();
        }
        driver.findElement(By.name("new")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("group_name")));
        driver.findElement(By.name("group_name")).click();
        driver.findElement(By.name("group_name")).sendKeys("");
        driver.findElement(By.name("group_header")).click();
        driver.findElement(By.name("group_header")).sendKeys("");
        driver.findElement(By.name("group_footer")).click();
        driver.findElement(By.name("group_footer")).sendKeys("");
        driver.findElement(By.name("submit")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("group page")));
        driver.findElement(By.linkText("group page")).click();
    }

    private boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }
}
