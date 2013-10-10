package com.capital;

import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.saucerest.SauceREST;
import com.saucelabs.testng.SauceOnDemandAuthenticationProvider;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class SauceListener implements IInvokedMethodListener, SauceOnDemandSessionIdProvider, SauceOnDemandAuthenticationProvider {

    public String browser;
    public String platform;
    public String browserVersion;
    public String user;
    public String key;

    public static final String SELENIUM_BROWSER = "SELENIUM_BROWSER";
    public static final String SELENIUM_VERSION = "SELENIUM_VERSION";
    public static final String SELENIUM_PLATFORM = "SELENIUM_PLATFORM";

    private static final String SAUCE_USER_NAME = "SAUCE_USER_NAME";
    private static final String SAUCE_API_KEY = "SAUCE_API_KEY";

    public static String jobID;

    public SauceREST rest;

    public SauceOnDemandAuthentication authentication;

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        user = System.getenv(SAUCE_USER_NAME);
        key = System.getenv(SAUCE_API_KEY);
        browser = System.getenv(SELENIUM_BROWSER);
        platform = System.getenv(SELENIUM_PLATFORM);
        browserVersion = System.getenv(SELENIUM_VERSION);

        if (method.isTestMethod()) {
            authentication = new SauceOnDemandAuthentication(user, key);

            rest = new SauceREST(user, key);

            WebDriver driver = DriverFactory.createSauceInstance(user,key,browser,browserVersion,platform);
            DriverManager.setWebDriver(driver);
            DriverManager.setAugmentedWebDriver(driver);
            // If we're a sauce test output the id
            if (getSessionId() != null) {
                printSessionId(testResult.getMethod().getMethodName());
                jobID = getSessionId();
            }

            Map<String, Object> sauceJob = new HashMap<String, Object>();
            sauceJob.put("name", "Test method: "+testResult.getMethod().getMethodName());
            rest.updateJobInfo(jobID, sauceJob);
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (testResult.getStatus() == 3) {
            throw new SkipException("!!! Test method was skipped");
        }

        if (method.isTestMethod()) {
            Map<String, Object> sauceJob = new HashMap<String, Object>();
            if (testResult.isSuccess()) {
                rest.jobPassed(jobID);
            } else {
                rest.jobFailed(jobID);
            }
            rest.updateJobInfo(jobID, sauceJob);

            WebDriver driver = DriverManager.getDriver();
            if (driver != null) {
                driver.quit();
            }
        }

//        if (!testResult.isSuccess()) {
//
//            // Take screenshot
//            File scrFile = ((TakesScreenshot) DriverManager.getAugmentedDriver())
//                    .getScreenshotAs(OutputType.FILE);
//
//            // Make the file name
//            String date = new SimpleDateFormat("MM-dd-yyyy_HHssSSS").format(new GregorianCalendar().getTime());
//            String failureImageFileName = testResult.getMethod().getMethodName() + "_on_" + date + ".png";
//
//            File failureImageFile = new File(failureImageFileName);
//
//            try {
//                FileUtils.moveFile(scrFile, failureImageFile);
//                FileUtils.moveFileToDirectory(failureImageFile, new File("target/surefire-reports/html/testfailureimages/"),true);
//            } catch (IOException e) {
//                // Nothing to catch
//            }
//
//            String failedURL = DriverManager.getAugmentedDriver().getCurrentUrl();
//            String methodName = testResult.getMethod().getMethodName();
//
//            reportLogScreenshot(failureImageFile, date, methodName,failedURL);
//
//        }

    }

    protected void reportLogScreenshot(File file, String date, String methodName, String FailedURL) {
        System.setProperty("org.uncommons.reportng.escape-output", "false");

        Reporter.log("<a href=\"testfailureimages/" + file + "\"><p align=\"left\">Error for " + methodName + " screenshot at " + date + "</p>");
        Reporter.log("<p align=\"left\">URL At Failure: " + FailedURL + "</p>");
        Reporter.log("<p><img width=\"768\" src=\"testfailureimages/" + file  + "\" alt=\"screenshot at " + date + "\"/></p></a><br />");
    }

    private void printSessionId(String methodName) {
        String message = String.format("SauceOnDemandSessionID=%1$s job-name=%2$s", getSessionId(), methodName);
        System.out.println(message);
    }

    @Override
    public SauceOnDemandAuthentication getAuthentication() {
        return authentication;
    }

    @Override
    public String getSessionId() {
        SessionId sessionId = ((RemoteWebDriver)DriverManager.getDriver()).getSessionId();
        return (sessionId == null) ? null : sessionId.toString();
    }

}