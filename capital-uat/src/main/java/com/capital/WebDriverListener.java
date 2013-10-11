package com.capital;

import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.saucerest.SauceREST;
import com.saucelabs.testng.SauceOnDemandAuthenticationProvider;
import org.apache.commons.io.FileUtils;
import org.json.simple.*;
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

public class WebDriverListener implements IInvokedMethodListener, SauceOnDemandSessionIdProvider, SauceOnDemandAuthenticationProvider {

    public String browser;
    public String platform;
    public String browserVersion;
    public String user;
    public String key;

    public String jobName; // Jenkins job name

    public static final String SELENIUM_BROWSER = "SELENIUM_BROWSER";
    public static final String SELENIUM_VERSION = "SELENIUM_VERSION";
    public static final String SELENIUM_PLATFORM = "SELENIUM_PLATFORM";

    private static final String SAUCE_USER_NAME = "SAUCE_USER_NAME";
    private static final String SAUCE_API_KEY = "SAUCE_API_KEY";

    private static final String JOB_NAME = "JOB_NAME";

    public static String jobID;

    public SauceREST rest;

    public SauceOnDemandAuthentication authentication;

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        // We don't care about configuration methods
        if (!method.isTestMethod()) return;

        // Do we have sauce info from Jenkins?
        user = System.getenv(SAUCE_USER_NAME) != null ? System.getenv(SAUCE_USER_NAME) : null;
        key = System.getenv(SAUCE_API_KEY) != null ? System.getenv(SAUCE_API_KEY) : null;

        if (user != null && key != null) {
            browser = System.getenv(SELENIUM_BROWSER) != null ? System.getenv(SELENIUM_BROWSER) : null;
            platform = System.getenv(SELENIUM_PLATFORM) != null ? System.getenv(SELENIUM_PLATFORM) : null;
            browserVersion = System.getenv(SELENIUM_VERSION) != null ? System.getenv(SELENIUM_VERSION) : null;

            authentication = new SauceOnDemandAuthentication(user, key);
            rest = new SauceREST(user, key);

            jobName = System.getenv(JOB_NAME) != null ? System.getenv(JOB_NAME) : null;

            WebDriver driver = DriverFactory.createSauceInstance(user,key,browser,browserVersion,platform);
            DriverManager.setWebDriver(driver);
            DriverManager.setAugmentedWebDriver(driver);
            // If we're a sauce test output the id
            if (getSessionId() != null) {
                jobID = getSessionId();
                printSessionId(jobID,testResult.getMethod().getMethodName());
            }
            // Update sauce with the test method being tested
            Map<String, Object> sauceJob = new HashMap<String, Object>();
            sauceJob.put("name", "Test method: "+testResult.getMethod().getMethodName());
            JSONArray tags = new JSONArray();
            tags.add(jobName);

            // If we have parameters
            if (testResult.getParameters().length > 0) {
                for (Object para : testResult.getParameters()) {
                    tags.add(para.toString());
                }
            }

            sauceJob.put("tags", tags);
            rest.updateJobInfo(jobID, sauceJob);




        } else {
            //Not a sauce test
            String driverType = method.getTestMethod().getXmlTest().getAllParameters().get("testLocation") != null
                    ? method.getTestMethod().getXmlTest().getAllParameters().get("testLocation")
                    : "";

            WebDriver driver;
            // Check if we're using sauce
            if (driverType.equals("remoteWD")) {
                driver = DriverFactory.createRemoteInstance("firefox");
                DriverManager.setWebDriver(driver);
                DriverManager.setAugmentedWebDriver(driver);
            // Assume local
            } else if (driverType.equals("")) {
                driver = DriverFactory.createLocalInstance("firefox");
                DriverManager.setWebDriver(driver);
            }
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        // We don't care about configuration methods
        if (!method.isTestMethod()) return;

        if (testResult.getStatus() == 3) {
            throw new SkipException("!!! Test method was skipped");
        }

        // If we're a sauce test update pass/fail status
        if (rest != null) {
            Map<String, Object> sauceJob = new HashMap<String, Object>();
            if(testResult.isSuccess()) {
                rest.jobPassed(jobID);
            } else {
                rest.jobFailed(jobID);
            }
            rest.updateJobInfo(jobID, sauceJob);
        }

        if (!testResult.isSuccess()) {

            // Take screenshot
            File scrFile = ((TakesScreenshot) DriverManager.getAugmentedDriver())
                    .getScreenshotAs(OutputType.FILE);

            // Make the file name
            String date = new SimpleDateFormat("MM-dd-yyyy_HHssSSS").format(new GregorianCalendar().getTime());
            String failureImageFileName = testResult.getMethod().getMethodName() + "_on_" + date + ".png";

            File failureImageFile = new File(failureImageFileName);

            try {
                FileUtils.moveFile(scrFile, failureImageFile);
                FileUtils.moveFileToDirectory(failureImageFile, new File("target/surefire-reports/html/testfailureimages/"),true);
            } catch (IOException e) {
                // Nothing to catch
            }

            String failedURL = DriverManager.getAugmentedDriver().getCurrentUrl();
            String methodName = testResult.getMethod().getMethodName();

            reportLogScreenshot(failureImageFile, date, methodName,failedURL);

        }

        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            driver.quit();
        }
    }

    protected void reportLogScreenshot(File file, String date, String methodName, String FailedURL) {
        System.setProperty("org.uncommons.reportng.escape-output", "false");

        Reporter.log("<a href=\"testfailureimages/" + file + "\"><p align=\"left\">Error for " + methodName + " screenshot at " + date + "</p>");
        Reporter.log("<p align=\"left\">URL At Failure: " + FailedURL + "</p>");
        Reporter.log("<p><img width=\"768\" src=\"testfailureimages/" + file  + "\" alt=\"screenshot at " + date + "\"/></p></a><br />");
    }

    private void printSessionId(String jobID, String methodName) {
        String message = String.format("SauceOnDemandSessionID=%1$s job-name=%2$s", jobID, methodName);
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
