package com.capital.test;

import com.capital.WebDriverSingleton;
import com.capital.pages.SAMLLogin;
import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;


import java.net.MalformedURLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.openqa.selenium.By.cssSelector;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SAMLLoginStepDefs {

    protected WebDriver driver;
    private SAMLLogin samlLogin;

    @Before({"@search"})
    public void setUp() throws MalformedURLException {
        samlLogin = new SAMLLogin();
        this.driver = WebDriverSingleton.getInstance();
    }

    @Given("^I have logged into SAML$")
    public void I_have_logged_into_SAML() {
        samlLogin.go();
        samlLogin.doLogin();
    }

    @When("^I click \"([^\"]*)\"$")
    public void I_click(String arg1) throws Throwable {
        // Express the Regexp above with the code you wish you had
        throw new PendingException();
    }
}
