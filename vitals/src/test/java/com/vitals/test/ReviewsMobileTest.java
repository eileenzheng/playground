package com.vitals.test;

import com.vitals.pages.ReviewPage;
import com.vitals.pages.ReviewSearchResultsPage;
import com.vitals.pages.ReviewWritePage;
import com.vitalsqa.testrail.TestCase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ReviewsMobileTest {

    SoftAssert m_assert;
    String url;
    String searchTerm;

    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) throws Exception {
        this.url = url;
    }

    @TestCase(id=3480)
    @Test
    public void autoSuggestLocation() {
        m_assert = new SoftAssert();
        String zip = "300";
        String city = "Charles";
        ReviewPage reviewPage = new ReviewPage();

        reviewPage.get(url + "/review");
        m_assert.assertTrue(reviewPage.headerModule().locationTextBox().getAttribute("value").toString().equals(
            reviewPage.locationBox().getAttribute("value").toString()), "Location box out-of-sync with masthead");

        reviewPage.enterLocation(zip);
        m_assert.assertTrue(reviewPage.checkSuggestions(reviewPage.locationSuggestions(), zip), "Location suggestion doesn't contain search term");
        reviewPage.get(url + "/review");
        reviewPage.enterLocation(city);
        m_assert.assertTrue(reviewPage.checkSuggestions(reviewPage.locationSuggestions(), city), "Location suggestion doesn't contain search term");

        reviewPage.locationSuggestions().get(0).click();
        m_assert.assertTrue(reviewPage.headerModule().locationTextBox().getAttribute("value").toString().equals(
                reviewPage.locationBox().getAttribute("value").toString()), "Location box out-of-sync with masthead");

        m_assert.assertAll();
    }

    @TestCase(id=3481)
    @Test
    public void autoSuggestName() {

        m_assert = new SoftAssert();
        ReviewPage reviewPage = new ReviewPage();
        reviewPage.get(url + "/review");

        String name = "John";
        reviewPage.enterSearchTerm(name);
        m_assert.assertTrue(reviewPage.checkSuggestions(reviewPage.doctorNames(), name),
                "One or more autosuggest provider results do not contain search term");
        m_assert.assertTrue(reviewPage.checkSuggestions(reviewPage.facilityNames(), name),
                "One or more autosuggest facility results do not contain search term");

        m_assert.assertAll();
    }

    @TestCase(id=3482)
    @Test
    public void reviewSearchGo() {

        m_assert = new SoftAssert();
        searchTerm = "John";

        ReviewPage reviewPage = new ReviewPage();
        reviewPage.get(url + "/review");

        reviewPage.enterSearchTerm(searchTerm);
        reviewPage.enterLocation("33021");
        reviewPage.locationSuggestions().get(0).click();
        reviewPage.goButton().click();

        ReviewSearchResultsPage reviewSerp = new ReviewSearchResultsPage();
        m_assert.assertTrue(reviewSerp.activeToggle().getText().toString().equals("Doctors"), "Not at doctor toggle");
        m_assert.assertTrue(reviewSerp.getResultsCountNumber()>100, "Less than 100 results");
        m_assert.assertTrue(reviewSerp.searchBox().getAttribute("value").toString().contains(searchTerm), "Search box does not contain search term");
        m_assert.assertTrue(reviewSerp.resultsContainsText(reviewSerp.listingNames(), searchTerm), "One or more results do not contain search term");
        m_assert.assertTrue(reviewSerp.countOccurrences(reviewSerp.listingCities(), "Hollywood") >= 5, "Less than 5 results from selected city");
        m_assert.assertTrue(reviewSerp.countOccurrences(reviewSerp.listingStates(), "FL") >= 25, "Less than 25 results from selected state");
        m_assert.assertAll();
    }

    @TestCase(id=3483)
    @Test
         public void reviewSearchClickDoctor() {

        m_assert = new SoftAssert();
        ReviewPage reviewPage = new ReviewPage();
        reviewPage.get(url + "/review");

        reviewPage.enterSearchTerm("John");
        reviewPage.getRandom(reviewPage.doctorNames()).click();

        ReviewWritePage reviewWritePage = new ReviewWritePage();
        Assert.assertTrue(reviewWritePage.isDoctorReview());
    }

    @TestCase(id=3484)
    @Test
    public void reviewSearchClickFacility() {

        m_assert = new SoftAssert();
        ReviewPage reviewPage = new ReviewPage();
        reviewPage.get(url + "/review");

        reviewPage.enterSearchTerm("city");
        reviewPage.getRandom(reviewPage.facilityNames()).click();

        ReviewWritePage reviewWritePage = new ReviewWritePage();
        Assert.assertTrue(reviewWritePage.isFacilityReview());
    }
}
