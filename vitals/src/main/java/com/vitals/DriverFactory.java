package com.vitals;

import com.vitals.helpers.Constants;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;

public class DriverFactory {

    public static WebDriver createRemoteInstance(String browserName) {
        WebDriver driver = null;
        URL server = null;

        try {
            server = new URL(Constants.SELENIUM_REMOTE);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if (browserName.toLowerCase().equals("firefox")) {

            DesiredCapabilities caps = DesiredCapabilities.firefox();
            driver = new RemoteWebDriver(server, caps);
            setDriverFeatures(driver);

        } else if (browserName.toLowerCase().equals("phantomjs")) {

            DesiredCapabilities caps = new DesiredCapabilities();

            ArrayList<String> cliArgsCap = new ArrayList<String>();
            cliArgsCap.add("--web-security=false");
            cliArgsCap.add("--ssl-protocol=any");
            cliArgsCap.add("--ignore-ssl-errors=true");
            cliArgsCap.add("--cookies-file=cookie");

            caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cliArgsCap);
            caps.setBrowserName("phantomjs");
            caps.setCapability("takesScreenshot", true);
            driver = new RemoteWebDriver(server,caps);
            driver.manage().window().setSize(new Dimension(1280,1024));

        }
        return driver;
    }

    public static WebDriver createLocalInstance(String browserName) {
        WebDriver driver = null;
        if (browserName.toLowerCase().contains("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.toLowerCase().contains("chrome")) {
            driver = new ChromeDriver();
        }
        setDriverFeatures(driver);
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
        caps.setJavascriptEnabled(true);

        driver = new PhantomJSDriver(caps);
        setDriverFeatures(driver);

        return driver;
    }

    private static void setDriverFeatures(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Constants.SELENIUM_IMPLICIT_WAIT, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1024,768));
    }

}