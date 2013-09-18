package com.vitals.runners;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BrowserStackTestRunner {

    private WebDriver driver;
    private RemoteWebDriver remoteDriver;

    private String domain;

    public static final String USERNAME = "ScottArgenziano1";
    public static final String AUTOMATE_KEY = "rXXgfcVpSjHfUEqzBbqq";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";

    @Parameters({"domain"})
    @BeforeMethod
    public void setUp(String domain) throws Exception {
        this.domain = domain;

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("project", "Capital");
        caps.setCapability("browser", "Firefox");
        caps.setCapability("browser_version", "22.0");
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "7");
        caps.setCapability("browserstack.debug", "true");

        this.driver = new RemoteWebDriver(new URL(URL),caps);

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getUrl(String domain) {
        return "http://" + domain;
    }

    public String getSecureUrl(String domain) {
        return "https://" + domain;
    }

    public String getHttpSecureUrl(String user, String pw, String domain) {
        return "http://" + user + ":" + pw + "@" + domain;
    }
}
