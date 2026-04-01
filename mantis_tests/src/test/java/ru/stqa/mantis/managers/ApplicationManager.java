package ru.stqa.mantis.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import java.util.Properties;

public class ApplicationManager {
    private WebDriver driver;
    private String browser;
    private Properties properties;
    private LoginHelper session;
    private HttpHelper http;
    private JamesCliHelper jamesCli;
    private MailHelper mail;

    public void init(String browser, Properties properties) {
        this.browser = browser;
        this.properties = properties;

    }
    public LoginHelper session(){
        if (session == null) {
            session=new LoginHelper(this);
        }
        return session;
    }
    public JamesCliHelper jamesCli(){
        if (jamesCli == null) {
            jamesCli=new JamesCliHelper(this);
        }
        return jamesCli;
    }
    public MailHelper mail(){
        if (mail == null) {
            mail=new MailHelper(this);
        }
        return mail;
    }

    public HttpHelper http() {
        if (http==null){
            http=new HttpHelper(this);
        }
        return http;
    }

    public WebDriver driver() {
        if (driver == null) {
            if ("chrome".equals(browser)) {
                driver = new ChromeDriver();
            } else if ("edge".equals(browser)) {
                driver = new EdgeDriver();
            } else throw new IllegalArgumentException(String.format("Unknown browser: %s", browser));
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get(properties.getProperty("web.baseUrl"));
        }
        return driver;
    }
    public String getProperty(String name){
        return properties.getProperty(name);
    }
}
