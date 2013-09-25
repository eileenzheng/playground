package com.prsdemo.test;

import com.prsdemo.helpers.Constants;
import com.prsdemo.pages.DoctorReviews;
import com.prsdemo.pages.ReviewProvider;
import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.CucumberException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class PatientReviewsStepDefs {

    DoctorReviews drReviews;
    ReviewProvider reviewProvider;
    String providerNumber = "1";

    @Before
    public void setUp() {
        drReviews = new DoctorReviews();
        reviewProvider = new ReviewProvider();
    }

    @Given("^I have navigated to a provider review page$")
    public void I_have_navigated_to_a_provider_review_page() throws Throwable {
        drReviews.go(providerNumber);
    }

    @Then("^I will see patient reviews$")
    public void I_will_see_patient_reviews() throws Throwable {
        assertTrue(drReviews.patientReviews().size() > 0);
    }

    @When("^I click review this doctor$")
    public void I_click_review_this_doctor() throws Throwable {
        drReviews.clickReviewThisDoctor();
    }

    @Then("^I will be brought to the Terms of Use page$")
    public void I_will_be_brought_to_the_Terms_of_Use_page() throws Throwable {
        assertTrue("Probably not on the Terms of Use page",reviewProvider.getCurrentUrl().contains("rate"));
    }

    @Then("^I will see the sort order change$")
    public void I_will_see_the_sort_order_change() throws Throwable {
        if (drReviews.sortByDropDown().getFirstSelectedOption().getText().equals("Date: Newest to Oldest")) {
            //Date: Newest to Oldest is by default
            if (!drReviews.getCurrentUrl().equals(Constants.PRS_DEMO_SITE + "/reviews/Doctor" + providerNumber)) {
                assertTrue(drReviews.getCurrentUrl().contains("newtoold"));
            }
        } else if (drReviews.sortByDropDown().getFirstSelectedOption().getText().equals("Date: Oldest to Newest")) {
            assertTrue(drReviews.getCurrentUrl().contains("oldtonew"));
        } else if (drReviews.sortByDropDown().getFirstSelectedOption().getText().equals("Most Helpful")) {
            assertTrue(drReviews.getCurrentUrl().contains("mosthelpful"));
        } else if (drReviews.sortByDropDown().getFirstSelectedOption().getText().equals("Least Helpful")) {
            assertTrue(drReviews.getCurrentUrl().contains("leasthelpful"));
        } else if (drReviews.sortByDropDown().getFirstSelectedOption().getText().equals("Experience: Lowest to Highest")) {
            assertTrue(drReviews.getCurrentUrl().contains("lowtohigh"));
        } else if (drReviews.sortByDropDown().getFirstSelectedOption().getText().equals("Experience: Highest to Lowest")) {
            assertTrue(drReviews.getCurrentUrl().contains("hightolow"));
        }
    }

    @Given("^more than one page of reviews$")
    public void more_than_one_page_of_reviews() throws Throwable {
        assertTrue("Only one page of reviews",drReviews.totalResultPages() > 1);
    }

    @Then("^the page display says \"([^\"]*)\" of X$")
    public void the_page_display_says_of_X(String arg1) throws Throwable {
        assertEquals("Not on correct page",drReviews.currentResultPage(),Integer.parseInt(arg1));
    }

    @When("^I click \"([^\"]*)\"$")
    public void I_click(String arg1) throws Throwable {
        if (arg1.equals(">>")) {
            drReviews.clickNextPageLink();
        } else if (arg1.equals("<<")) {
            drReviews.clickPreviousPageLink();
        } else {
            throw new CucumberException("Argument '" + arg1 + "' is unexpected. Should be '>>' or '<<'");
        }
    }

    @When("^I select sort option Date: Newest to Oldest$")
    public void I_select_sort_option_sort_new_to_old() throws Throwable {
        drReviews.selectSortType("newtoold");
        assertEquals(drReviews.sortByDropDown().getFirstSelectedOption().getText().trim(),"Date: Newest to Oldest");
    }

    @When("^I select sort option Date: Oldest to Newest$")
    public void I_select_sort_option_sort_old_to_new() throws Throwable {
        drReviews.selectSortType("oldtonew");
        assertEquals(drReviews.sortByDropDown().getFirstSelectedOption().getText().trim(),"Date: Oldest to Newest");
    }

    @When("^I select sort option Most Helpful$")
    public void I_select_sort_option_sort_most_helpful() throws Throwable {
        drReviews.selectSortType("mosthelpful");
        assertEquals(drReviews.sortByDropDown().getFirstSelectedOption().getText().trim(),"Most Helpful");
    }

    @When("^I select sort option Least Helpful$")
    public void I_select_sort_option_sort_least_helpful() throws Throwable {
        drReviews.selectSortType("leasthelpful");
        assertEquals(drReviews.sortByDropDown().getFirstSelectedOption().getText().trim(),"Least Helpful");
    }

    @When("^I select sort option Experience: Lowest to Highest$")
    public void I_select_sort_option_sort_exp_low_to_high() throws Throwable {
        drReviews.selectSortType("experience: lowtohigh");
        assertEquals(drReviews.sortByDropDown().getFirstSelectedOption().getText().trim(),"Experience: Lowest to Highest");
    }

    @When("^I select sort option Experience: Highest to Lowest$")
    public void I_select_sort_option_sort_exp_high_to_low() throws Throwable {
        drReviews.selectSortType("experience: hightolow");
        assertEquals(drReviews.sortByDropDown().getFirstSelectedOption().getText().trim(),"Experience: Highest to Lowest");
    }

}
