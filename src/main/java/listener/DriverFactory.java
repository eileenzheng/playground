package listener;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    /**
     * Create a remote web driver instance
     * @param browserName firefox or chrome
     * @param record whether you want to record video
     * @return driver
     */
    public static WebDriver createGridlasticInstance(String browserName, String record) {
        WebDriver driver = null;
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setPlatform(Platform.LINUX);

        if (record.equalsIgnoreCase("true")) {
            caps.setCapability("video", "True");
        }
        else {
            caps.setCapability("video", "False");
        }

        if (browserName.toLowerCase().equals("firefox")) {
            caps.setBrowserName("firefox");
            caps.setVersion("45");
            FirefoxProfile firefoxProfile = new FirefoxProfile();
            firefoxProfile.setAlwaysLoadNoFocusLib(true);
            try {
                driver = new RemoteWebDriver(new URL("http://7hFkpRkg6hs6QXrW3Wj10e2aX3y9LAEJ:H9UWR43OcmKT92F3OPVFTOFHI4GkuaT2@I08BPXKN.gridlastic.com:80/wd/hub"), caps);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            driver.manage().window().setSize(new Dimension(1920,1080));

        }
        else if (browserName.toLowerCase().equals("chrome")) {
            caps.setBrowserName("chrome");
            caps.setVersion("latest");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("disable-infobars");
            options.addArguments(Arrays.asList("--window-position=0,0"));
            options.addArguments(Arrays.asList("--window-size=1920,1080"));
            caps.setCapability(ChromeOptions.CAPABILITY, options);
            try {
                driver = new RemoteWebDriver(new URL("http://7hFkpRkg6hs6QXrW3Wj10e2aX3y9LAEJ:H9UWR43OcmKT92F3OPVFTOFHI4GkuaT2@I08BPXKN.gridlastic.com:80/wd/hub"), caps);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        if (record.equalsIgnoreCase("true")) {
            System.out.println("Test Video: http://s3.amazonaws.com/4ad4a405-ef2a-b3d3-a629-1ab0a2d338b1/20e2eaa4-9264-dfd4-52c6-8f3af914e90f/play.html?" + ((RemoteWebDriver) driver).getSessionId());
        }
        return driver;
    }

    /**
     * Create a remote web driver instance
     * @param browserName firefox, chrome, internet explorer (or ie)
     * @param server server to the webdriver ex. http://thvitdatadev01.mdx.med:4444/wd/hub
     * @return driver
     */
    public static WebDriver createRemoteInstance(String browserName,String deviceName,URL server) {
        WebDriver driver = null;

        if (browserName.toLowerCase().equals("firefox")) {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setBrowserName("firefox");

            FirefoxProfile firefoxProfile = new FirefoxProfile();
            firefoxProfile.setAlwaysLoadNoFocusLib(true);
            driver = new RemoteWebDriver(server, caps);
            driver.manage().window().setSize(new Dimension(1280,1024));

        }
        else if (browserName.toLowerCase().equals("chrome") && !deviceName.equals("")) {
            Map<String, String> mobileEmulation = new HashMap<String, String>();
            mobileEmulation.put("deviceName", deviceName);
            Map<String, Object> chromeOptions = new HashMap<String, Object>();
            chromeOptions.put("mobileEmulation", mobileEmulation);
            DesiredCapabilities caps = DesiredCapabilities.chrome();
            caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
            driver = new RemoteWebDriver(server, caps);
        }
        else if (browserName.toLowerCase().equals("chrome")) {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setBrowserName("chrome");
            driver = new RemoteWebDriver(server, caps);
            driver.manage().window().setSize(new Dimension(1280,1024));
        }
        else if (browserName.toLowerCase().equals("internet explorer") || browserName.toLowerCase().equals("ie")) {
            DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
            driver = new RemoteWebDriver(server, caps);
            driver.manage().window().setSize(new Dimension(1280,1024));
        }
        return driver;
    }

    /**
     * Create a local webdriver instance
     * @param browserName firefox,chrome,ie
     * @return driver
     */
    public static WebDriver createLocalInstance(String browserName, String deviceName) {
        WebDriver driver = null;
        if (browserName.toLowerCase().contains("firefox")) {
            driver = new FirefoxDriver();
            driver.manage().window().setSize(new Dimension(1280,1024));
        }
        else if (browserName.toLowerCase().contains("chrome") && !deviceName.equals("")) {
            Map<String, String> mobileEmulation = new HashMap<String, String>();
            mobileEmulation.put("deviceName", deviceName);
            Map<String, Object> chromeOptions = new HashMap<String, Object>();
            chromeOptions.put("mobileEmulation", mobileEmulation);
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        	driver = new ChromeDriver(capabilities);
        }
        else if (browserName.toLowerCase().contains("chrome")) {
            driver = new ChromeDriver();
            driver.manage().window().setSize(new Dimension(1280,1024));
        }
        else if (browserName.toLowerCase().contains("internet explorer") || browserName.toLowerCase().contains("ie")) {
            driver = new InternetExplorerDriver();
            driver.manage().window().setSize(new Dimension(1280,1024));
        }
        return driver;
    }
}
