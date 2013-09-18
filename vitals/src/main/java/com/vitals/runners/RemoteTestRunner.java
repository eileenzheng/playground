package com.vitals.runners;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class RemoteTestRunner {

    private WebDriver driver;
    private WebDriver augmentedDriver;
    private String domain;

    @Parameters({"domain"})
    @BeforeMethod
    public void setUp(String domain, ITestContext context) throws Exception {
        System.setProperty("org.uncommons.reportng.escape-output", "false");
        this.domain = domain;

        URL server = new URL("http://thvitdatadev01.mdx.med:4444/wd/hub");
        DesiredCapabilities caps = DesiredCapabilities.firefox();

        driver = new RemoteWebDriver(server, caps);

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        augmentedDriver = new Augmenter().augment(driver);

    }

    @AfterMethod (alwaysRun = true)
    public void tearDown(ITestResult result) throws IOException {
        if (result.getStatus() == 3) {
            throw new SkipException("!!! Test method was skipped");
        }

        // Capture screen shot on com.capital.test failure
        if (!result.isSuccess()) {

            // Take screenshot
            File scrFile = ((TakesScreenshot) getAugmentedDriver())
                    .getScreenshotAs(OutputType.FILE);

            // Make the file name
            String date = new SimpleDateFormat("MM-dd-yyyy_HHssSSS").format(new GregorianCalendar().getTime());
            String failureImageFileName = result.getMethod().getMethodName() + "_on_" + date + ".png";


            File failureImageFile = new File(failureImageFileName);
            FileUtils.moveFile(scrFile, failureImageFile);
            FileUtils.moveFileToDirectory(failureImageFile, new File("target/surefire-reports/html/testfailureimages/"),true);
//            File finalLocation = FileUtils.getFile("target/surefire-reports/html/testfailureimages/" + failureImageFile);

            reportLogScreenshot(failureImageFile, date, result.getMethod().getMethodName());
        }

        driver.manage().deleteAllCookies();
        driver.quit();
    }

    protected void reportLogScreenshot(File file, String date, String methodName) {
        System.setProperty("org.uncommons.reportng.escape-output", "false");

        Reporter.log("<a href=\"testfailureimages/" + file + "\"><p align=\"left\">Error for " + methodName + " screenshot at " + date + "</p>");
        Reporter.log("<p><img width=\"1024\" src=\"testfailureimages/" + file  + "\" alt=\"screenshot at " + date + "\"/></p></a><br />");
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

    public WebDriver getAugmentedDriver() {
        return augmentedDriver;
    }

}
