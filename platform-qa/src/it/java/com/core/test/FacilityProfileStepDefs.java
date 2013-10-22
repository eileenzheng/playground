package com.core.test;

import com.core.WebDriverSingleton;
import com.core.pages.HomePage;
import com.core.pages.ProfilePage;
import com.core.pages.SearchResults;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.openqa.selenium.By.cssSelector;

public class FacilityProfileStepDefs {

    protected WebDriver driver;
    private ProfilePage profilePage;

    // Hold results here
    private FluentWebElements results;


    @Before({"@facility"})
    public void setUp() {
        profilePage = new ProfilePage();
        this.driver = WebDriverSingleton.getInstance();
    }

    @Given("^I am viewing a facility with awards$")
    public void I_am_viewing_a_facility_with_at_least_award(int arg1) throws Throwable {
        profilePage.get("http://qa.vitalschoice.com/profile/facility?id=1000000000");
    }

    @Then("^I will see the facility awards module$")
    public void I_will_see_the_facility_awards_module() throws Throwable {
        assertThat("Awards module was not visible",profilePage.awardsModuleIsPresent(), is(true));
    }

    @And("^the header displays as \"([^\"]*)\"$")
    public void the_header_displays_as(String arg1) throws Throwable {
        String title = profilePage.getModuleTitleText(profilePage.awardsModule());
        assertThat("Expected " + arg1 + " to be the module title. Title is currently: " + title, title, equalTo(arg1));
    }

    @And("^up to (\\d+) awards will display on the module$")
    public void up_to_awards_will_display_on_the_module(int arg1) throws Throwable {
        assertThat(profilePage.awardsModuleCompressedCount(),is(lessThanOrEqualTo(arg1)));
    }
}
