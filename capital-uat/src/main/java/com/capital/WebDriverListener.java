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

public class WebDriverListener implements IInvokedMethodListener, SauceOnDemandSessionIdProvider, SauceOnDemandAuthenticationProvider {

    Boolean isSauce = false;

    public SauceOnDemandAuthentication authentication;

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
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
        if (testResult.getStatus() == 3) {
            throw new SkipException("!!! Test method was skipped");
        }

        if (isSauce) {
            String user = System.getenv("SAUCE_USER_NAME");
            String key = System.getenv("SAUCE_API_KEY");
            String jobID = ((RemoteWebDriver) DriverManager.getDriver()).getSessionId().toString();
            SauceREST client = new SauceREST(user, key);
            Map<String, Object> sauceJob = new HashMap<String, Object>();
            sauceJob.put("name", "Test method: "+testResult.getMethod().getMethodName());
            if(testResult.isSuccess()) {
                client.jobPassed(jobID);
            } else {
                client.jobFailed(jobID);
            }
            client.updateJobInfo(jobID, sauceJob);
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

        if (method.isTestMethod()) {
            WebDriver driver = DriverManager.getDriver();
            if (driver != null) {
                driver.quit();
            }
        }
    }

    protected void reportLogScreenshot(File file, String date, String methodName, String FailedURL) {
        System.setProperty("org.uncommons.reportng.escape-output", "false");

        Reporter.log("<a href=\"testfailureimages/" + file + "\"><p align=\"left\">Error for " + methodName + " screenshot at " + date + "</p>");
        Reporter.log("<p align=\"left\">URL At Failure: " + FailedURL + "</p>");
        Reporter.log("<p><img width=\"768\" src=\"testfailureimages/" + file  + "\" alt=\"screenshot at " + date + "\"/></p></a><br />");
    }

    private void printSessionId(String methodName) {
        String message = String.format("SauceOnDemandSessionID=%1$s job-name=%2$s", ((RemoteWebDriver) DriverManager.getDriver()).getSessionId().toString(), methodName);
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
