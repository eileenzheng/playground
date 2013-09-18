package com.core;

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

    public static WebDriver getInstance() {
        if (driver == null) {
//            driver = new FirefoxDriver();

            URL server = null;
            try {
                server = new URL("http://thvitdatadev01.mdx.med:4444/wd/hub");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            DesiredCapabilities caps = DesiredCapabilities.firefox();
            driver = new RemoteWebDriver(server, caps);
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            driver.manage().window().maximize();

            augmentedDriver = new Augmenter().augment(driver);
        }
        return driver;
    }

    public static WebDriver getAugmentedDriver() {
        return augmentedDriver;
    }
}
