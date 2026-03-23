package ru.stqa.addressbook.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Properties;

public class ApplicationManager {
    public WebDriver driver;
    public WebDriverWait wait;
    public LoginHelper session;
    public GroupHelper groupHelper;
    public ContactHelper contactHelper;
    public Properties properties;
    public JdbcHelper jdbc;

    public JdbcHelper jdbc(){
        if (jdbc ==null){
            jdbc =new JdbcHelper(this);
        }
        return jdbc;
    }

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

    public void init(String browser, Properties properties) {
        this.properties=properties;
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
            driver.get(properties.getProperty("web.baseUrl"));
            session().login(properties.getProperty("web.user"), properties.getProperty("web.password"));
        }
    }

}
