package com.vitals.test;

import com.vitals.helpers.Constants;
import com.vitals.pages.HomePage;
import com.vitals.pages.ReviewPage;
import com.vitals.pages.ReviewSearchResultsPage;
import com.vitals.pages.ReviewWritePage;
import com.vitalsqa.testrail.TestCase;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MastheadReviewsTest {

    SoftAssert m_assert;
    String url;
    String searchTerm;

    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) throws Exception {
        this.url = url;
    }

    @TestCase(id=2458)
    @Test
    public void autoSuggestLocation() {
        m_assert = new SoftAssert();
        String zip = "100";
        String city = "New York";
        ReviewPage reviewPage = new ReviewPage();

        reviewPage.get(url + "/review");
        reviewPage.headerModule().enterLocationReview(zip);
        m_assert.assertTrue(reviewPage.headerModule().checkSuggestions(Constants.SearchType.LOCATION, zip));
        reviewPage.get(url + "/review");
        reviewPage.headerModule().enterLocationReview(city);
        m_assert.assertTrue(reviewPage.headerModule().checkSuggestions(Constants.SearchType.LOCATION, city));
        m_assert.assertAll();
    }

    @TestCase(id=1625)
    @Test
    public void reviewSearchGo() {

        m_assert = new SoftAssert();
        searchTerm = "John";

        ReviewPage reviewPage = new ReviewPage();
        reviewPage.get(url + "/review");

        reviewPage.headerModule().enterReviewSearchTerm(searchTerm);
        reviewPage.headerModule().enterLocationReview("33021");
        reviewPage.headerModule().locationSuggestionsReview().get(0).click();
        reviewPage.headerModule().reviewGoButton().click();

        ReviewSearchResultsPage reviewSerp = new ReviewSearchResultsPage();
        m_assert.assertTrue(reviewSerp.activeToggle().getText().toString().equals("Doctors"), "Not at doctor toggle");
        m_assert.assertTrue(reviewSerp.getResultsCountNumber()>100, "Less than 100 results");
        m_assert.assertTrue(reviewSerp.searchBox().getAttribute("value").toString().contains(searchTerm), "Search box does not contain search term");
        m_assert.assertTrue(resultsContainsText(reviewSerp.listingNames(), searchTerm), "One or more results do not contain search term");
        m_assert.assertTrue(countOccurrences(reviewSerp.listingCities(), "Hollywood") >= 5, "Less than 5 results from selected city");
        m_assert.assertTrue(countOccurrences(reviewSerp.listingStates(), "FL") >= 25, "Less than 25 results from selected state");
        m_assert.assertAll();
    }

    @TestCase(id=1626)
    @Test
    public void reviewSearchSeeAllDoctors() {

        m_assert = new SoftAssert();
        searchTerm = "John";

        ReviewPage reviewPage = new ReviewPage();
        reviewPage.get(url + "/review");

        reviewPage.headerModule().enterReviewSearchTerm(searchTerm);
        reviewPage.headerModule().showAllDoctors().click();

        ReviewSearchResultsPage reviewSerp = new ReviewSearchResultsPage();
        m_assert.assertTrue(reviewSerp.activeToggle().getText().toString().equals("Doctors"), "Not at doctor toggle");
        m_assert.assertTrue(reviewSerp.getResultsCountNumber()>100, "Less than 100 results");
        m_assert.assertTrue(reviewSerp.searchBox().getAttribute("value").toString().contains(searchTerm), "Search box does not contain search term");
        m_assert.assertTrue(resultsContainsText(reviewSerp.listingNames(), searchTerm), "One or more results do not contain search term");
        m_assert.assertAll();
    }

    @TestCase(id=1627)
    @Test
    public void reviewSearchSeeAllFacilities() {

        m_assert = new SoftAssert();
        searchTerm = "city";

        ReviewPage reviewPage = new ReviewPage();
        reviewPage.get(url + "/review");

        reviewPage.headerModule().enterReviewSearchTerm(searchTerm);
        reviewPage.headerModule().showAllFacilities().click();

        ReviewSearchResultsPage reviewSerp = new ReviewSearchResultsPage();
        m_assert.assertTrue(reviewSerp.activeToggle().getText().toString().equals("Facilities"), "Not at facility toggle");
        m_assert.assertTrue(reviewSerp.getResultsCountNumber()>30 && reviewSerp.getResultsCountNumber()<40, "Number of results not between 30 and 40");
        m_assert.assertTrue(reviewSerp.searchBox().getAttribute("value").toString().contains(searchTerm), "Search box does not contain search term");
        m_assert.assertTrue(resultsContainsText(reviewSerp.listingNames(), searchTerm), "One or more results do not contain search term");
        m_assert.assertAll();
    }

    @TestCase(id=1628)
    @Test
    public void reviewSearchClickDoctor() {
        m_assert = new SoftAssert();
        HomePage homePage = new HomePage();
        homePage.get(url);

        homePage.headerModule().writeReviewTab().click();
        ReviewPage reviewPage = new ReviewPage();
        reviewPage.headerModule().enterReviewSearchTerm("John");
        reviewPage.getRandom(reviewPage.headerModule().reviewNameSuggestions()).click();

        ReviewWritePage reviewWritePage = new ReviewWritePage();
        Assert.assertTrue(reviewWritePage.isDoctorReview());
    }

    @TestCase(id=1629)
    @Test
    public void reviewSearchClickFacility() {
        m_assert = new SoftAssert();
        HomePage homePage = new HomePage();
        homePage.get(url);

        homePage.headerModule().writeReviewTab().click();
        ReviewPage reviewPage = new ReviewPage();
        reviewPage.headerModule().enterReviewSearchTerm("city");
        reviewPage.getRandom(reviewPage.headerModule().reviewUccSuggestions()).click();

        ReviewWritePage reviewWritePage = new ReviewWritePage();
        Assert.assertTrue(reviewWritePage.isFacilityReview());
    }

    private boolean resultsContainsText(FluentWebElements results, String text) {
        boolean value = true;
        for (FluentWebElement result: results) {
            if (!result.getText().toString().toLowerCase().contains(text.toLowerCase())) {
                value = false;
            }
        }
        return value;
    }

    private int countOccurrences(FluentWebElements els, String text) {
        int result = 0;
        for (FluentWebElement el: els) {
            if (el.getText().toString().equals(text)) result++;
        }
        return result;
    }
}
