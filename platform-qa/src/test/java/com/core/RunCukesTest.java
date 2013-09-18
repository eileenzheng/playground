package com.core;

import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@Cucumber.Options(features = {"src/com.capital.test/resources"}, format = {"pretty",
        "html:target/cucumber-html-report",
        "json:target/cucumber.json", "usage:target/usage.jsonx", "junit:target/junit.xml"})
public class RunCukesTest {

    @BeforeClass
    public static void setUp() {
        WebDriverSingleton.getInstance();
    }

    @AfterClass
    public static void shutDown() {
        WebDriverSingleton.getInstance().quit();
    }
}