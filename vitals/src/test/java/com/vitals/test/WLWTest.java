package com.vitals.test;

import com.vitals.helpers.Profile;
import com.vitals.pages.HomePage;
import com.vitals.pages.SearchResultsPage;
import com.vitals.pages.wlw.LandingPage;
import com.vitals.pages.wlw.SearchPage;
import com.vitalsqa.testrail.TestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.List;

public class WLWTest {

    private String url;
    private String vitalsUrl;
    private SoftAssert m_assert;

    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) throws Exception {
        this.url = url;
        if (this.url.contains("features.hb"))
            vitalsUrl = "http://qa.mdxdev.net";
        else
            vitalsUrl = "http://www.vitals.com";
    }

    @TestCase(id=1780)
    @Test
    public void checkHeader() {
        m_assert = new SoftAssert();

        LandingPage landingPage = new LandingPage();
        landingPage.get(url);

        m_assert.assertEquals(landingPage.headerPage().header().getText().toString(), "Find a Physician in Your Area", "Incorrect header text on landing page");
        m_assert.assertTrue(landingPage.headerPage().logo().isDisplayed().value(), "Logo not displayed on landing page");

        landingPage.selectDropDown(landingPage.specialtyDropDown(), "Internists");
        landingPage.locationTextBox().clearField().sendKeys("10036");
        landingPage.searchButton().click();

        SearchPage serp = new SearchPage();
        m_assert.assertEquals(serp.headerPage().header().getText().toString(), "Find a Physician in Your Area", "Incorrect header text on search page");
        m_assert.assertTrue(serp.headerPage().logo().isDisplayed().value(), "Logo not displayed on search page");

        m_assert.assertAll();
    }

    @TestCase(id=1781)
    @Test
    public void checkSerpProfileUrl() {
        m_assert = new SoftAssert();

        LandingPage landingPage = new LandingPage();
        landingPage.get(url);

        landingPage.selectDropDown(landingPage.specialtyDropDown(), "Internists");
        landingPage.locationTextBox().clearField().sendKeys("10036");
        landingPage.searchButton().click();

        SearchPage serp = new SearchPage();
        m_assert.assertTrue(serp.isProfileLinkCorrect(), "Profile link format incorrect on page " + serp.currentPage().getText().toString());
        serp.nextLink().click();
        m_assert.assertTrue(serp.isProfileLinkCorrect(), "Profile link format incorrect on page " + serp.currentPage().getText().toString());
        serp.getRandom(serp.pageLinks()).click();
        m_assert.assertTrue(serp.isProfileLinkCorrect(), "Profile link format incorrect on page " + serp.currentPage().getText().toString());
        if (!serp.currentPage().getText().toString().equals("1")) {
            serp.prevLink().click();
            m_assert.assertTrue(serp.isProfileLinkCorrect(), "Profile link format incorrect on page " + serp.currentPage().getText().toString());
        }

        m_assert.assertAll();
    }

    @TestCase(id=1782)
    @Test
    public void compareWithVitals() {
        m_assert = new SoftAssert();

        HomePage home = new HomePage();
        home.get(vitalsUrl);
        home.headerModule().enterSearchTerm("Family Practitioner");
        home.headerModule().specialtySuggestions().get(0).click();

        SearchResultsPage vitalsSerp = new SearchResultsPage();
        String serpUrl = vitalsSerp.getCurrentUrl();
        vitalsSerp.get(serpUrl + "&location=10036");

        List<Profile> relevancyProfile = vitalsSerp.doctorResults(vitalsSerp.drList());
        int vitalsResultCount = vitalsSerp.getResultsCountNumber();
        vitalsSerp.refinement().sortDropDown().click();
        vitalsSerp.refinement().sortByDistance().click();
        vitalsSerp.waitForJQuery();
        List<Profile> distanceProfile = vitalsSerp.doctorResults(vitalsSerp.drList());
        vitalsSerp.refinement().sortDropDown().click();
        vitalsSerp.refinement().sortByName().click();
        vitalsSerp.waitForJQuery();
        List<Profile> nameProfile = vitalsSerp.doctorResults(vitalsSerp.drList());
        vitalsSerp.refinement().sortDropDown().click();
        vitalsSerp.refinement().sortByRating().click();
        vitalsSerp.waitForJQuery();
        List<Profile> ratingProfile = vitalsSerp.doctorResults(vitalsSerp.drList());

        LandingPage landingPage = new LandingPage();
        landingPage.get(url);
        landingPage.selectDropDown(landingPage.specialtyDropDown(), "Family Physicians");
        landingPage.locationTextBox().clearField().sendKeys("10036");
        landingPage.searchButton().click();

        SearchPage serp = new SearchPage();

        m_assert.assertEquals(vitalsResultCount, serp.getResultCount(), "Number of results does not match Vitals (sort by relevancy)");
        m_assert.assertTrue(serp.isProviderMatchingVitals(relevancyProfile), "First X results does not match Vitals(sort by relevancy)");
        serp.selectDropDown(serp.sortDropDown(), "Distance");
        m_assert.assertEquals(vitalsResultCount, serp.getResultCount(), "Number of results does not match Vitals(sort by distance)");
        m_assert.assertTrue(serp.isProviderMatchingVitals(distanceProfile), "First X results does not match Vitals(sort by distance)");
        serp.selectDropDown(serp.sortDropDown(), "Name");
        m_assert.assertEquals(vitalsResultCount, serp.getResultCount(), "Number of results does not match Vitals(sort by name)");
        m_assert.assertTrue(serp.isProviderMatchingVitals(nameProfile), "First X results does not match Vitals(sort by name)");
        serp.selectDropDown(serp.sortDropDown(), "Rating");
        m_assert.assertEquals(vitalsResultCount, serp.getResultCount(), "Number of results does not match Vitals(sort by rating)");
        m_assert.assertTrue(serp.isProviderMatchingVitals(ratingProfile), "First X results does not match Vitals(sort by rating)");

        m_assert.assertAll();
    }
}
