package com.presby;

import com.presby.helpers.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
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

    public static WebDriver createSauceInstance(String user, String key, String browser, String version, String platform) {
        WebDriver driver = null;
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName(browser);
        caps.setVersion(version);
        caps.setCapability(CapabilityType.PLATFORM, platform);

        URL sauceUrl = null;
        try {
            sauceUrl = new URL("http://" + user + ":" + key + "@ondemand.saucelabs.com:80/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        driver = new RemoteWebDriver(sauceUrl, caps);

        setDriverFeatures(driver);

        return driver;
    }

    public static WebDriver createPhantomInstance() {
        WebDriver driver = null;
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                "/usr/local/bin/phantomjs");
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS,new String[] {
                "--web-security=false",
                "--ignore-ssl-errors=true",
                "--ssl-protocol=any"});

        caps.setCapability(PhantomJSDriverService.PHANTOMJS_GHOSTDRIVER_CLI_ARGS, new String[] {
                "--logLevel=WARN"
        });

        caps.setJavascriptEnabled(true);

        driver = new PhantomJSDriver(caps);
        setDriverFeatures(driver);

        return driver;
    }

    private static void setDriverFeatures(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Constants.SELENIUM_IMPLICIT_WAIT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

}
