package com.prsdemo.test;

import com.prsdemo.pages.ModAccess;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ModManagerStepDefs {

    ModAccess modAccess;

    @Before
    public void setup() {
        modAccess = new ModAccess();
        // check if logged in
        if (modAccess.isLoggedIn()) {
            modAccess.clickLogoutLink();
        }
    }

    @Given("^I logged into the moderation manager$")
    public void I_logged_into_the_moderation_manager() throws Throwable {
        modAccess.go();
    }

    @Then("^I will see the main view$")
    public void I_will_see_the_main_view() throws Throwable {
        assertEquals(modAccess.getTitle(), "Moderation Manager");
    }

    @When("^I click 'Select All' under Moderation Status$")
    public void I_click_Select_All_under_Moderation_Status() throws Throwable {
        modAccess.check(modAccess.modStatusSelectAllCheckBox());
    }

    @And("^I click the Filter button$")
    public void I_click_the_Filter_button() throws Throwable {
        modAccess.clickFilterButton();
    }

    @Then("^I will see reviews in all status$")
    public void I_will_see_reviews_in_all_status() throws Throwable {
        assertTrue(modAccess.totalReviewCount() > 0);
    }

    @When("^I click 'Escalated' under Moderation Status$")
    public void I_click_Escalated_under_Moderation_Status() throws Throwable {
        modAccess.check(modAccess.modStatusSelectAllCheckBox());
        modAccess.unCheck(modAccess.modStatusSelectAllCheckBox());
        modAccess.check(modAccess.modStatusEscalatedCheckBox());
    }

    @Then("^I will see reviews in Escalated status$")
    public void I_will_see_reviews_in_Escalated_status() throws Throwable {
        List<ModAccess.Review> reviews = modAccess.processReviews();
        for (ModAccess.Review rev : reviews) {
            assertEquals("Review id: " + rev.reviewId + "Was not Escalated",rev.reviewStatus,"Escalated");
        }
    }
}
