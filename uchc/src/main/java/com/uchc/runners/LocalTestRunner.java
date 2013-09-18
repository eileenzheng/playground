package com.uchc.runners;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

/**
 * LocalTestRunner
 */
public class LocalTestRunner {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
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
