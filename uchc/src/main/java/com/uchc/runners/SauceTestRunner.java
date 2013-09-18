package com.uchc.runners;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class SauceTestRunner {

    private WebDriver driver;
    private RemoteWebDriver remoteDriver;

    private String domain;

    @Parameters({"domain"})
    @BeforeMethod
    public void setUp(String domain) throws Exception {
        this.domain = domain;

        DesiredCapabilities caps = DesiredCapabilities.firefox();
        caps.setCapability("platform", "Windows 7");
        caps.setCapability("version", "22");

        this.driver = new RemoteWebDriver(
                new URL("http://sargenzi:96bf4019-9049-4ed0-af31-1baaf6535b87@ondemand.saucelabs.com:80/wd/hub"),
                caps);

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

    public String getDomain() {
        return domain;
    }

    public String getUrl() {
        return "http://" + getDomain();
    }

    public String getSecureUrl() {
        return "https://" + getDomain();
    }

    public String getHttpSecureUrl(String user, String pw) {
        return "http://" + user + ":" + pw + "@" + getDomain();
    }
}
