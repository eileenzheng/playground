package com.vitals;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class WebDriverListener extends TestListenerAdapter {

    private static Map<String,String> testParams = new HashMap<String, String>();
    private static Map<Class, List<ITestNGMethod>> classListMap = new HashMap<Class, List<ITestNGMethod>>();

    ThreadLocal<WebDriver> wd = new ThreadLocal<WebDriver>();
    ThreadLocal<Object> testClassInstance = new ThreadLocal<Object>();


    // Start
    @Override
    public void onStart(ITestContext tc) {
        super.onStart(tc);
        setClassListMap(tc);
        setParams(tc);
    }

    @Override
    public void onTestStart(ITestResult tr) {
        super.onTestStart(tr);
        setTestClassInstance(tr);
        startDriver(tr);
    }

    // Finish Methods
    @Override
    public void onTestSuccess(ITestResult tr) {
        super.onTestSuccess(tr);
        endDriver(tr);
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        super.onTestFailure(tr);
        takeScreenshot(tr,wd.get());
        endDriver(tr);
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        super.onTestSkipped(tr);
        endDriver(tr);
    }

    // Class Methods
    private void setParams(ITestContext tc) {
        String driverType = tc.getCurrentXmlTest().getParameter("driverType") != null
                ? tc.getCurrentXmlTest().getParameter("driverType")
                : "";

        String browser = tc.getCurrentXmlTest().getParameter("browser") != null
                ? tc.getCurrentXmlTest().getParameter("browser")
                : "firefox";

        testParams.put("driverType",driverType);
        testParams.put("browser",browser);
    }

    /**
     * Initialize and then start the WebDriver
     * @param tr test result
     */
    private void startDriver(ITestResult tr) {
        if(!isDriverRunning()) {
            initializeDriver();
        }
    }

    private boolean isDriverRunning() {
        if ((wd.get() == null) || (((RemoteWebDriver) wd.get()).getSessionId() == null)) {
            return false;
        } else {
            return true;
        }

    }

    /**
     * Initializing WebDriver.
     * Depending on the parameters from the TestNG xml this will start a local, remote, or PhantomJS instance
     *
     */
    private void initializeDriver() {
        WebDriver driver = null;
        String dt = testParams.get("driverType");
        String browser = testParams.get("browser");

        if (dt.equals("") || dt.equals("local")) {
            driver = DriverFactory.createLocalInstance(browser);

        } else if (dt.equals("remote")) {
            driver = DriverFactory.createRemoteInstance(browser);

        } else if (dt.equals("phantom")) {
            driver = DriverFactory.createPhantomInstance();
        }

        DriverManager.setWebDriver(driver);
        //Augment the remote driver so we can take screen shots
        if (dt.equals("remote")) {
            wd.set(new Augmenter().augment(DriverManager.getDriver()));
        } else {
            wd.set(DriverManager.getDriver());
        }

    }

    private void quitDriver() {
        if (DriverManager.getDriver() != null) { DriverManager.getDriver().quit(); }
    }

    /**
     * Checks to see how many methods are left to run before ending the driver
     * We don't want the driver to end too soon by counting
     * @param tr testresult
     */
    private void endDriver(ITestResult tr) {
        ITestNGMethod current = tr.getMethod();
        Class currentClass = current.getRealClass();
        List<ITestNGMethod> remainingMethods = classListMap.get(currentClass);
        ITestNGMethod foundMethod = null;
        for (ITestNGMethod m : remainingMethods) {
            if (m.getMethodName().equals(current.getMethodName())) {
                foundMethod = m;
                int count = m.getInvocationCount();
                if (count > 1) {
                    m.setInvocationCount(count - 1);
                    return;
                }
            }
        }
        // We have one left
        if (foundMethod != null) {
            remainingMethods.remove(foundMethod); // Now there are none
        }

        // Now quit the driver
        if(remainingMethods.size() == 0) {
            quitDriver();
        }

    }

    private void setClassListMap(ITestContext tc) {
        for (ITestNGMethod m : tc.getAllTestMethods()) {
            Class methodsClass = m.getRealClass();

            List<ITestNGMethod> methods = classListMap.get(methodsClass);
            if (methods == null) {
                methods = new ArrayList<ITestNGMethod>();
            }
            methods.add(m);
            classListMap.put(methodsClass, methods);
        }
    }

    private void setTestClassInstance(ITestResult tr) {
        testClassInstance.set(tr.getInstance());
    }

    private void takeScreenshot(ITestResult tr, WebDriver driver) {
        // Take screenshot
        if (driver == null) return;

        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Make the file name
        String date = new SimpleDateFormat("MM-dd-yyyy_HHssSSS").format(new GregorianCalendar().getTime());
        String failureImageFileName = tr.getMethod().getMethodName() + "_on_" + date + ".png";

        File failureImageFile = new File(failureImageFileName);

        try {
            FileUtils.moveFile(scrFile, failureImageFile);
            FileUtils.moveFileToDirectory(failureImageFile, new File("target/surefire-reports/html/testfailureimages/"),true);
        } catch (IOException e) {
            // Nothing to catch
        }

        String failedURL = driver.getCurrentUrl();
        String methodName = tr.getMethod().getMethodName();

        reportLogScreenshot(failureImageFile, date, methodName,failedURL);
    }

    private void reportLogScreenshot(File file, String date, String methodName, String FailedURL) {
        System.setProperty("org.uncommons.reportng.escape-output", "false");

        Reporter.log("<a href=\"testfailureimages/" + file + "\"><p align=\"left\">Error for " + methodName + " screenshot at " + date + "</p>");
        Reporter.log("<p align=\"left\">URL At Failure: " + FailedURL + "</p>");
        Reporter.log("<p><img width=\"768\" src=\"testfailureimages/" + file  + "\" alt=\"screenshot at " + date + "\"/></p></a><br />");
    }

}