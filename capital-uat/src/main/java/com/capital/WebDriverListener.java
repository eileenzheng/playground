package com.capital;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class WebDriverListener implements IInvokedMethodListener {

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            String driverType = method.getTestMethod().getXmlTest().getAllParameters().get("testLocation") != null
                    ? method.getTestMethod().getXmlTest().getAllParameters().get("testLocation")
                    : "";

            WebDriver driver = driverType.equals("remoteWD")
                    ? DriverFactory.createRemoteInstance("firefox")
                    : DriverFactory.createLocalInstance("firefox");

            DriverManager.setWebDriver(driver);

            if (driverType.equals("remoteWD")) DriverManager.setAugmentedWebDriver(driver);
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (testResult.getStatus() == 3) {
            throw new SkipException("!!! Test method was skipped");
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
}
