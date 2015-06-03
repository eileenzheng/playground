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

public class WlwTest {

    private String url;
    private SoftAssert m_assert;

    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) throws Exception {
        if (url.contains("staging")) {
            this.url = "http://finder.dev.features.hb.mdxdev.net/";
        }
        else {
            this.url = "http://finder.vitals.com/";
        }
    }

    @TestCase(id=1780)
    @Test
    public void checkText() {
        m_assert = new SoftAssert();

        LandingPage landingPage = new LandingPage();
        landingPage.get(url + "adhd");

        m_assert.assertEquals(landingPage.commonModule().header().getText().toString(), "Find a Physician in Your Area", "Incorrect header text on landing page");
        m_assert.assertTrue(landingPage.commonModule().logo().isDisplayed().value(), "Logo not displayed on landing page");
        m_assert.assertEquals(landingPage.commonModule().terms().getText().toString(), "As more fully set forth in a website's terms of use, (1) nothing contained on or offered by or through this website should be construed as medical advice and should not be relied upon for medical diagnosis or treatment. MDX Medical, Inc. (\"MDX\"), the provider of this website, does not recommend or endorse any particular healthcare provider whose information or ratings appear on this website; and (2) MDX has granted you a limited license to access and use this website for your own noncommercial use. You are not permitted to copy, reproduce, distribute, transmit, mirror, frame, scrape, extract, wrap, create derivative works of, reverse engineer, decompile or disassemble any part or aspect of this website.", "Incorrect terms text on landing page");

        landingPage.selectDropDown(landingPage.specialtyDropDown(), "Internists");
        landingPage.locationTextBox().clearField().sendKeys("10036");
        landingPage.searchButton().click();

        SearchPage serp = new SearchPage();
        m_assert.assertEquals(serp.commonModule().header().getText().toString(), "Find a Physician in Your Area", "Incorrect header text on search page");
        m_assert.assertTrue(serp.commonModule().logo().isDisplayed().value(), "Logo not displayed on search page");
        m_assert.assertEquals(serp.commonModule().terms().getText().toString(), "As more fully set forth in a website's terms of use, (1) nothing contained on or offered by or through this website should be construed as medical advice and should not be relied upon for medical diagnosis or treatment. MDX Medical, Inc. (\"MDX\"), the provider of this website, does not recommend or endorse any particular healthcare provider whose information or ratings appear on this website; and (2) MDX has granted you a limited license to access and use this website for your own noncommercial use. You are not permitted to copy, reproduce, distribute, transmit, mirror, frame, scrape, extract, wrap, create derivative works of, reverse engineer, decompile or disassemble any part or aspect of this website.", "Incorrect terms text on landing page");
        m_assert.assertTrue(serp.resultHeader().getText().toString().equals("All providers in current zip code"), "Incorrect search text");

        m_assert.assertAll();
    }

    @TestCase(id=1781)
    @Test
    public void checkSerpProfileUrl() {
        m_assert = new SoftAssert();

        SearchPage serp = new SearchPage();
        serp.get(url + "bed/search?provider_type=1&type=specialty&distance=15&specialist_id=108&lat=40.8035755&lng=-74.10095749999999");

        serp.selectDropDown(serp.specialtyDropDown(), "Internal Medicine");
        serp.locationTextBox().clearField().sendKeys("10036");
        serp.searchButton().click();

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
        landingPage.get(url + "adhd");

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
        landingPage.get(url + "bed");

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
        landingPage.get(url + "adhd");

        landingPage.selectDropDown(landingPage.specialtyDropDown(), "Psychiatrists");
        landingPage.locationTextBox().clearField().sendKeys("33021");
        landingPage.searchButton().click();

        SearchPage serp = new SearchPage();
        serp.selectDropDown(serp.sortDropDown(), "Name");
        m_assert.assertTrue(countOccurrences(serp.specialties(), "Psychiatry")>=5 && countOccurrences(serp.specialties(), "Psychiatry") <=10,
                "Less than 5 Psychiatrists returned in name sort");
        m_assert.assertTrue(countOccurrences(serp.cities(), "Hollywood")>=0 && countOccurrences(serp.cities(), "Hollywood")<=10,
                "Less than 1 from Hollywood returned in name sort");
        m_assert.assertTrue(countOccurrences(serp.states(), "FL")>=8 && countOccurrences(serp.states(), "FL")<=10,
                "Less than 8 from FL returned in name sort");
        m_assert.assertTrue(sortedByLastName(serp.names()), "Results are not sorted by name");

        m_assert.assertAll();
    }

    @TestCase(id=3356)
    @Test
    public void checkSerpLatLong() {
        m_assert = new SoftAssert();

        SearchPage serp = new SearchPage();
        serp.get(url + "adhd/search?provider_type=1&type=specialty&distance=15&specialist_id=144&lat=26.03184&lng=-80.19062");

        serp.selectDropDown(serp.sortDropDown(), "Distance");
        m_assert.assertTrue(countOccurrences(serp.specialties(), "Psychiatry")>=5 && countOccurrences(serp.specialties(), "Psychiatry") <=10,
                "Less than 5 Psychiatrists returned in distance sort");
        m_assert.assertTrue(countOccurrences(serp.cities(), "Hollywood")>=8 && countOccurrences(serp.cities(), "Hollywood")<=10,
                "Less than 8 from Hollywood returned in distance sort");
        m_assert.assertTrue(countOccurrences(serp.states(), "FL")==10,
                "Not everything from FL is returned in distance sort");

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
