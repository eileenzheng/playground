package listener;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

public class WebDriverListener extends TestListenerAdapter implements ITestListener {

    private static Map<String,String> testParams = new HashMap<String, String>();
    private static Map<Class, List<ITestNGMethod>> classListMap = new HashMap<Class, List<ITestNGMethod>>();

    ThreadLocal<WebDriver> wd = new ThreadLocal<WebDriver>();

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
//        if (tr.getMethod().getRetryAnalyzer() != null) {
//            Retry retryAnalyzer = (Retry)tr.getMethod().getRetryAnalyzer();
//
//            if(retryAnalyzer.retry(tr)) {
//                tr.setStatus(ITestResult.SKIP);
//            } else {
//                tr.setStatus(ITestResult.FAILURE);
//            }
//            Reporter.setCurrentTestResult(tr);
//        }
        endDriver(tr);
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        super.onTestSkipped(tr);
        endDriver(tr);
    }

    @Override
    public void onFinish(ITestContext context) {
        Set<ITestResult> failedTests = context.getFailedTests().getAllResults();
        for (ITestResult temp : failedTests) {
            ITestNGMethod method = temp.getMethod();
            if (context.getFailedTests().getResults(method).size() > 1) {
                failedTests.remove(temp);
            } else {
                if (context.getPassedTests().getResults(method).size() > 0) {
                    failedTests.remove(temp);
                }
            }
        }
    }

    // Class Methods
    private void setParams(ITestContext tc) {
        String driverType = tc.getCurrentXmlTest().getParameter("driverType") != null
                ? tc.getCurrentXmlTest().getParameter("driverType")
                : "";

        String browser = tc.getCurrentXmlTest().getParameter("browser") != null
                ? tc.getCurrentXmlTest().getParameter("browser")
                : "firefox";

        String device = tc.getCurrentXmlTest().getParameter("device") != null
                ? tc.getCurrentXmlTest().getParameter("device")
                : "";

        String video = tc.getCurrentXmlTest().getParameter("video") != null
                ? tc.getCurrentXmlTest().getParameter("video")
                : "";

        testParams.put("driverType",driverType);
        testParams.put("browser",browser);
        testParams.put("device",device);
        testParams.put("video",video);
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
     * Depending on the parameters from the TestNG xml this will start a local or remote instance
     *
     */
    private void initializeDriver() {
        WebDriver driver = null;
        String dt = System.getProperty("driverType") != null ? System.getProperty("driverType") : testParams.get("driverType");

        if (dt.equals("") || dt.equals("local")) {
            driver = DriverFactory.createLocalInstance(testParams.get("browser"), testParams.get("device"));
        }
        else if (dt.equals("remote")) {
                URL server = getRemoteServerURL();
                driver = DriverFactory.createRemoteInstance(testParams.get("browser"),testParams.get("device"),server);
        }
        else if (dt.equals("gridlastic")) {
            driver = DriverFactory.createGridlasticInstance(testParams.get("browser"), testParams.get("video"));
        }

        DriverManager.setWebDriver(driver);

        // Augment the remote driver so we can take screen shots
        if (dt.equals("remote")) {
            wd.set(new Augmenter().augment(DriverManager.getDriver()));
        } else {
            wd.set(DriverManager.getDriver());
        }

    }

    /**
     * Generate the RemoteWebDriver URL
     * @return URL
     */
    private URL getRemoteServerURL() {
        String remoteServer;
        URL server;

        try {
            if (System.getProperty("remoteUrl") != null) {
                remoteServer = System.getProperty("remoteUrl");
            } else {
                remoteServer = System.getenv("remoteUrl");
            }
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Missing remote driver url, check pom file");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("URL can't be blank check pom file");
        }

        try {
            server = new URL(remoteServer);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Malformed remote driver url check pom file for correct url");
        }

        return server;
    }

    private void quitDriver() {
        if (wd.get() != null) {
            if (((RemoteWebDriver) wd.get()).getSessionId() != null) { wd.get().quit(); }
        }
    }

    /**
     * This method is run after every test (method). But we don't want the driver to quit until
     * the whole class is finished. So each time we remove the finished method from the classListMap.
     * And if there are more than 1 invocations for each method, we decrease the invokation count by 1.
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

        synchronized(classListMap) {

            // for every method in tc
            for (ITestNGMethod m : tc.getAllTestMethods()) {
                Class methodsClass = m.getRealClass(); // get the class of the method
                // look by class (key) in the classListMap, if list of methods (value) doesn't exist, create list
                List<ITestNGMethod> methods = classListMap.get(methodsClass);
                if (methods == null) {
                    methods = new ArrayList<ITestNGMethod>();
                }
                methods.add(m); // add current method to the list of methods
                classListMap.put(methodsClass, methods); // update map with class (key) and method list (value)
            }
        }
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

        Reporter.log("<p align=\"left\">Error for " + methodName + " screenshot at " + date + "</p>");
        Reporter.log("<p align=\"left\">URL At Failure: " + FailedURL + "</p>");
        Reporter.log("<p><a href=\"testfailureimages/\" + file + \"\"><img width=\"768\" src=\"testfailureimages/" + file  + "\" alt=\"screenshot at " + date + "\"/></p></a><br />");
    }

}
