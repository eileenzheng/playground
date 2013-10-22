package com.core.test;

import com.core.pages.ProfilePage;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.seleniumhq.selenium.fluent.FluentWebElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ProfileStepDefs {

    private ProfilePage profilePage;

    @Before()
    public void setUp() {
        profilePage = new ProfilePage();
    }

    @Given("^I am viewing a Provider with awards$")
    public void I_am_viewing_a_provider_with_awards() {
        profilePage.go("http://qa.vitalschoice.com/profile/professional?id=1000000000");
    }

    @Given("^I am viewing a facility with awards$")
    public void I_am_viewing_a_facility_with_awards() {
        profilePage.go("http://qa.vitalschoice.com/profile/facility?id=1000000000");
    }

    @Then("^I will see the awards module$")
    public void I_will_see_the_provider_awards_module() {
        assertThat("Awards module was not visible",profilePage.awardsModuleIsPresent(), is(true));
    }

    @And("^the header displays as \"([^\"]*)\"$")
    public void the_header_displays_as(String arg1) {
        String title = profilePage.getModuleTitleText(profilePage.awardsModule());
        assertThat("Expected " + arg1 + " to be the module title. Title is currently: " + title, title, equalTo(arg1));
    }

    @And("^up to (\\d+) awards will display on the module$")
    public void up_to_awards_will_display_on_the_module(int arg1) {
        assertThat(profilePage.awardsModuleCompressedCount(),is(lessThanOrEqualTo(arg1)));
    }

    @And("^I will see a link that reads \"([^\"]*)\"$")
    public void I_will_see_a_link_that_reads(String arg1) {
        assertThat(profilePage.getViewMoreLinkText(),equalTo(arg1));
    }

    @And("^I will see the award name$")
    public void I_will_see_the_award_name() {
        for (FluentWebElement el : profilePage.awardsList()) {
            assertThat("An award is missing its title on this page: " + profilePage.getCurrentUrl(),
                    profilePage.awardNameIsPresent(el), is(true));
        }
    }

    @When("^I click See Awards and Recognitions$")
    public void I_click_See_Awards_and_Recognitions() {
        profilePage.viewAwardsExpanded();
    }

}
