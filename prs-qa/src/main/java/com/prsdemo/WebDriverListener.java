package com.prsdemo;

import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

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
        if (method.isTestMethod()) {
            WebDriver driver = DriverManager.getDriver();
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
