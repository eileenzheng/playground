package com.core;

import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@Cucumber.Options(features = {"src/it/resources"}, format = {"pretty",
        "html:target/cucumber-html-report",
        "json:target/cucumber.json", "usage:target/usage.jsonx", "junit:target/junit.xml"},
        tags = {"~@ignore"})
public class RunCukesIT {

    @BeforeClass
    public static void setUp() {
        // Check the API Key is passed via Jenkins
        if (System.getenv("SAUCE_API_KEY") != null) {
            WebDriverSingleton.getSauceInstance();
        } else {
            WebDriverSingleton.getDriver();
        }

    }

    @AfterClass
    public static void shutDown() {
        WebDriverSingleton.getDriver().quit();
    }
}