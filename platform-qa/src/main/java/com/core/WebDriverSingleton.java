package com.core;

import com.core.helpers.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WebDriverSingleton {

    private static WebDriver driver;
    private static WebDriver augmentedDriver;

    /**
     * Create or return an instance of a local WebDriver
     * @return WebDriver object that runs Firefox
     */
    public static WebDriver getInstance() {
        if (driver == null) {
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(Constants.SELENIUM_IMPLICIT_WAIT, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        return driver;
    }

    /**
     * Returns an instance of the Augmented WebDriver
     * Use for adding ScreenCap capabilities to the RemoteWebDriver
     * @return an augmented RemoteWebDriver
     */
    public static WebDriver getAugmentedDriver() {
        return augmentedDriver;
    }

    /**
     * Create or return an instance of RemoteWebDriver
     * Remote instance will run a FireFox browser
     * @return a RemoteWebDriver object
     */
    public static WebDriver getRemoteInstance() {
        if (driver == null) {
            URL server = null;
            try {
                server = new URL(Constants.SELENIUM_REMOTE);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            DesiredCapabilities caps = DesiredCapabilities.firefox();
            driver = new RemoteWebDriver(server, caps);
            driver.manage().timeouts().implicitlyWait(Constants.SELENIUM_IMPLICIT_WAIT, TimeUnit.SECONDS);
            driver.manage().window().maximize();

            augmentedDriver = new Augmenter().augment(driver);
        }
        return driver;
    }

    /**
     * Get the WebDriver object
     * @return WebDriver
     */
    public static WebDriver getDriver() {
        return driver;
    }
}
