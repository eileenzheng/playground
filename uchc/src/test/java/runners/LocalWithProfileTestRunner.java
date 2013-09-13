package runners;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class LocalWithProfileTestRunner {

    private WebDriver driver;

    @BeforeMethod
    public void setUp(String domain) throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();

        File fireBugExt = new File("FFExtensions/firebug-1.11.4.xpi");
//        File ySlowExt = new File("FFExtensions/yslow-firefox-3.1.6.xpi");
//        File netExportExt = new File ("FFExtensions/netExport-0.9b3.xpi");
//        File pageSpeedExt = new File ("FFExtensions/page-speed.xpi");

        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.addExtension(fireBugExt);
//        firefoxProfile.addExtension(ySlowExt);
//        firefoxProfile.addExtension(netExportExt);
//        firefoxProfile.addExtension(pageSpeedExt);

        firefoxProfile.setPreference("app.update.auto",false);
        firefoxProfile.setPreference("app.update.enabled",false);
        firefoxProfile.setPreference("browser.search.update",false);

        firefoxProfile.setPreference("extensions.firebug.currentVersion","1.11.4");
        firefoxProfile.setPreference("extensions.firebug.allPagesActivation","on");
        firefoxProfile.setPreference("extensions.firebug.defaultPanelName", "net");
        firefoxProfile.setPreference("extensions.firebug.net.enableSites", true);
        firefoxProfile.setPreference("extensions.firebug.addonBarOpened", true);
        firefoxProfile.setPreference("extensions.firebug.commandEditor", true);
        firefoxProfile.setPreference("extensions.firebug.firepath.showParentToolbar", true);
        firefoxProfile.setPreference("extensions.firebug.previousPlacement", 1);
        firefoxProfile.setPreference("extensions.firebug.showInfoTips",false);

        caps.setCapability("firefox_profile", firefoxProfile);
        //caps.setBrowserName("firefox");

        driver = new FirefoxDriver(caps);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
//        driver.quit();
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
