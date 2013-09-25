package com.prsdemo.test;

import com.google.common.base.Function;
import com.prsdemo.WebDriverSingleton;
import com.prsdemo.helpers.Constants;
import com.prsdemo.pages.DoctorReviews;
import com.prsdemo.pages.ReviewProvider;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RateADoctorStepDefs {
    DoctorReviews drReviews;
    ReviewProvider reviewProvider;

    @Before
    public void setup() {
        drReviews = new DoctorReviews();
        reviewProvider = new ReviewProvider();
    }

    @Given("^I am rating doctor with id \"([^\"]*)\"$")
    public void I_am_rating_doctor_with_id(String arg1) throws Throwable {
        reviewProvider.go(arg1);
    }

    @And("^I have accepted the terms of use$")
    public void I_have_accepted_the_terms_of_use() throws Throwable {
        reviewProvider.acceptTerms();

    }

    @And("^I have checked the verify I have received services checkbox$")
    public void I_have_checked_the_verify_I_have_received_services_checkbox() throws Throwable {
        reviewProvider.checkVerifyServicesReceived();
    }

    @Then("^I will rate my experience as \"([^\"]*)\"$")
    public void I_will_rate_my(String arg1) throws Throwable {
        if (!arg1.equals("")) {
            reviewProvider.rateQuestion(reviewProvider.experience(),Integer.parseInt(arg1));
        }
    }

    @And("^I will make my recommendation as \"([^\"]*)\"$")
    public void I_will_make_my_recommendation_as(String arg1) throws Throwable {
        if (arg1.equals("Y")) {
            reviewProvider.recommendProvider();
        } else if (arg1.equals("N")) {
            reviewProvider.dontRecommendProvider();
        }
    }

    @And("^I will rate their communication as \"([^\"]*)\"$")
    public void I_will_rate_their_communication_as(String arg1) throws Throwable {
        if (!arg1.equals("")) {
            reviewProvider.rateQuestion(reviewProvider.communication(),Integer.parseInt(arg1));
        }
    }

    @And("^I will rate their availability as \"([^\"]*)\"$")
    public void I_will_rate_their_availability_as(String arg1) throws Throwable {
        if (!arg1.equals("")) {
            reviewProvider.rateQuestion(reviewProvider.availability(),Integer.parseInt(arg1));
        }
    }

    @And("^I will rate their environment as \"([^\"]*)\"$")
    public void I_will_rate_their_environment_as(String arg1) throws Throwable {
        if (!arg1.equals("")) {
            reviewProvider.rateQuestion(reviewProvider.environment(),Integer.parseInt(arg1));
        }
    }

    @And("^I will provide comments \"([^\"]*)\"$")
    public void I_will_provide_comments(String arg1) throws Throwable {
        if (!arg1.equals("")) {
            reviewProvider.additionalComments(arg1);
        }
    }

    @And("^I click submit$")
    public void I_click_submit() throws Throwable {
        reviewProvider.submitReview();
    }

    @Then("^I will be brought back to the view reviews page$")
    public void I_will_be_brought_back_to_the_view_reviews_page() throws Throwable {
        WebDriver driver = WebDriverSingleton.getDriver();
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

        WebDriverWait wait = new WebDriverWait(driver,Constants.SELENIUM_IMPLICIT_WAIT,3000);
        try {
            wait.until(new Function<WebDriver, Boolean>() {
                @Override
                public Boolean apply(WebDriver webDriver) {
                    return webDriver.getCurrentUrl().contains("reviews");
                }
            });
        } catch (TimeoutException e) {
            // Will be false
            assertTrue(drReviews.getCurrentUrl().contains("reviews"));
        }
        // Assert will be true
        assertTrue(drReviews.getCurrentUrl().contains("reviews"));
        driver.manage().timeouts().implicitlyWait(Constants.SELENIUM_IMPLICIT_WAIT, TimeUnit.SECONDS);
    }

    @Then("^I will see the following error \"([^\"]*)\"$")
    public void I_will_see_the_following_error(String arg1) throws Throwable {
        String jsAlertText = reviewProvider.getJSAlertText();
        assertTrue("JS Alert \"" + jsAlertText + "\" does not contain \"" + arg1 + "\"",
                reviewProvider.getJSAlertText().contains(arg1));
    }
}
