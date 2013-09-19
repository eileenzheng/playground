package com.capital;

import com.capital.helpers.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

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
        }
        return driver;
    }

    public static WebDriver createLocalInstance(String browserName) {
        WebDriver driver = null;
        if (browserName.toLowerCase().contains("firefox")) {
            driver = new FirefoxDriver();
        }
        return driver;
    }

}
