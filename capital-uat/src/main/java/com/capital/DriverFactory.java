package com.capital;

import com.capital.helpers.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    public static WebDriver createRemoteInstance(String browserName) {
        WebDriver driver = null;
        if (browserName.toLowerCase().contains("firefox")) {
            URL server = null;
            try {
                server = new URL(Constants.SELENIUM_REMOTE);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            DesiredCapabilities caps = DesiredCapabilities.firefox();

            driver = new RemoteWebDriver(server, caps);

            setDriverFeatures(driver);
        }
        return driver;
    }

    public static WebDriver createLocalInstance(String browserName) {
        WebDriver driver = null;
        if (browserName.toLowerCase().contains("firefox")) {
            driver = new FirefoxDriver();
            setDriverFeatures(driver);
        }
        return driver;
    }

    private static void setDriverFeatures(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Constants.SELENIUM_IMPLICIT_WAIT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

}
