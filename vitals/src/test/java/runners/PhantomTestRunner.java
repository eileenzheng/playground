package runners;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

public class PhantomTestRunner {

    private WebDriver driver;

    private String domain;

    @Parameters({"domain"})
    @BeforeMethod
    public void setUp(String domain) {
        this.domain = domain;

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                "C:\\Users\\sargenziano\\Development\\phantomjs.exe");
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS,new String[] {
                "--cookies-file=.\\target\\cookies.txt",
                "--web-security=false",
                "--ignore-ssl-errors=true",
                "--ssl-protocol=any"});
        caps.setJavascriptEnabled(true);

        driver = new PhantomJSDriver(caps);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
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
}
