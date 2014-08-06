package com.vitals.test;

import com.vitals.pages.wlw.LandingPage;
import com.vitals.pages.wlw.SearchPage;
import com.vitalsqa.testrail.TestCase;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class WLWTest {

    private String url;
    private SoftAssert m_assert;

    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) throws Exception {
        this.url = url;
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
        m_assert.assertTrue(serp.getResultCount()==5000, "Less than 5000 results for Internists");
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

    @TestCase(id=2455)
    @Test
    public void checkSerpRelevancy() {
        m_assert = new SoftAssert();

        LandingPage landingPage = new LandingPage();
        landingPage.get(url);

        landingPage.selectDropDown(landingPage.specialtyDropDown(), "Psychiatrists");
        landingPage.locationTextBox().clearField().sendKeys("33021");
        landingPage.searchButton().click();

        SearchPage serp = new SearchPage();
        m_assert.assertTrue(countOccurrences(serp.specialties(), "Psychiatry")>=5 && countOccurrences(serp.specialties(), "Psychiatry") <=10,
                "Less than 5 Psychiatrists returned in relevacy sort");
        m_assert.assertTrue(countOccurrences(serp.cities(), "Hollywood")>=1 && countOccurrences(serp.cities(), "Hollywood")<=10,
                "Less than 1 from Hollywood returned in relevancy sort");
        m_assert.assertTrue(countOccurrences(serp.states(), "FL")>=8 && countOccurrences(serp.states(), "FL")<=10,
                "Less than 8 from FL returned in relevancy sort");

        m_assert.assertAll();
    }

    @TestCase(id=2456)
    @Test
    public void checkSerpDistance() {
        m_assert = new SoftAssert();

        LandingPage landingPage = new LandingPage();
        landingPage.get(url);

        landingPage.selectDropDown(landingPage.specialtyDropDown(), "Psychiatrists");
        landingPage.locationTextBox().clearField().sendKeys("33021");
        landingPage.searchButton().click();

        SearchPage serp = new SearchPage();
        serp.selectDropDown(serp.sortDropDown(), "Distance");
        m_assert.assertTrue(countOccurrences(serp.specialties(), "Psychiatry")>=5 && countOccurrences(serp.specialties(), "Psychiatry") <=10,
                "Less than 5 Psychiatrists returned in distance sort");
        m_assert.assertTrue(countOccurrences(serp.cities(), "Hollywood")>=8 && countOccurrences(serp.cities(), "Hollywood")<=10,
                "Less than 8 from Hollywood returned in distance sort");
        m_assert.assertTrue(countOccurrences(serp.states(), "FL")==10,
                "Not everything from FL is returned in distance sort");

        m_assert.assertAll();
    }

    @TestCase(id=2457)
    @Test
    public void checkSerpName() {
        m_assert = new SoftAssert();

        LandingPage landingPage = new LandingPage();
        landingPage.get(url);

        landingPage.selectDropDown(landingPage.specialtyDropDown(), "Psychiatrists");
        landingPage.locationTextBox().clearField().sendKeys("33021");
        landingPage.searchButton().click();

        SearchPage serp = new SearchPage();
        serp.selectDropDown(serp.sortDropDown(), "Name");
        m_assert.assertTrue(countOccurrences(serp.specialties(), "Psychiatry")>=5 && countOccurrences(serp.specialties(), "Psychiatry") <=10,
                "Less than 5 Psychiatrists returned in name sort");
        m_assert.assertTrue(countOccurrences(serp.cities(), "Hollywood")>=1 && countOccurrences(serp.cities(), "Hollywood")<=10,
                "Less than 1 from Hollywood returned in name sort");
        m_assert.assertTrue(countOccurrences(serp.states(), "FL")>=8 && countOccurrences(serp.states(), "FL")<=10,
                "Less than 8 from FL returned in name sort");
        m_assert.assertTrue(sortedByLastName(serp.names()), "Results are not sorted by name");

        m_assert.assertAll();
    }

    private int countOccurrences(FluentWebElements els, String text) {
        int result = 0;
        for (FluentWebElement el: els) {
            if (el.getText().toString().equals(text)) result++;
        }
        return result;
    }

    private boolean sortedByLastName(FluentWebElements names) {
        int offCount = 0;
        for (int i=0; i<9; i++) {
            if (parseName(names.get(i).getText().toString()).compareTo(parseName(parseName(names.get(i+1).getText().toString())))>0) {
                offCount++;
            }
        }

        return offCount<2; // allow 1 off result due to difference of name and display name
    }

    private String parseName(String fullName) {
        String name = fullName.split(",")[0];
        String[] names = name.split(" ");
        return names[names.length-1];
    }
}
