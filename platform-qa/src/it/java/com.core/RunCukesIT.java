package com.core;

import com.core.WebDriverSingleton;
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
        WebDriverSingleton.getInstance();
    }

    @AfterClass
    public static void shutDown() {
        WebDriverSingleton.getInstance().quit();
    }
}