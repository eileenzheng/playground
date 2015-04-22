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

public class WlwQsymiaTest {

    private String url;
    private SoftAssert m_assert;

    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) throws Exception {
        if (url.contains("staging")) {
            this.url = "http://finder.dev.features.hb.mdxdev.net/obsrx";
        }
        else {
            this.url = "http://finder.vitals.com/obsrx";
        }
    }

    @TestCase(id=3357)
    @Test
    public void checkText() {
        m_assert = new SoftAssert();

        LandingPage landingPage = new LandingPage();
        landingPage.get(url);

        m_assert.assertEquals(landingPage.commonModule().headerQsymia().getText().toString(), "Find a Weight Loss Specialist Near You", "Incorrect header text on landing page");
        m_assert.assertEquals(landingPage.commonModule().terms().getText().toString(), "The provider finder that generated the above stated physician results list was created by MDX Medical, Inc. (“Vitals”) specifically for Vivus, Inc. As more fully set forth in a website's terms of use, (1) nothing contained on or offered by or through this website should be construed as medical advice and should not be relied upon for medical diagnosis or treatment. MDX Medical, Inc. (\"MDX\"), the provider of this website, does not recommend or endorse any particular healthcare provider whose information or ratings appear on this website; and (2) MDX has granted you a limited license to access and use this website for your own noncommercial use. You are not permitted to copy, reproduce, distribute, transmit, mirror, frame, scrape, extract, wrap, create derivative works of, reverse engineer, decompile or disassemble any part or aspect of this website.", "Incorrect terms text on landing page");
        m_assert.assertTrue(landingPage.commonModule().logo().isDisplayed().value(), "Logo not displayed on landing page");

        landingPage.locationTextBox().clearField().sendKeys("98144");
        landingPage.searchButton().click();

        SearchPage serp = new SearchPage();
        m_assert.assertEquals(serp.commonModule().headerQsymia().getText().toString(), "Find a Weight Loss Specialist Near You", "Incorrect header text on search page");
        m_assert.assertEquals(serp.commonModule().terms().getText().toString(), "The provider finder that generated the above stated physician results list was created by MDX Medical, Inc. (“Vitals”) specifically for Vivus, Inc. As more fully set forth in a website's terms of use, (1) nothing contained on or offered by or through this website should be construed as medical advice and should not be relied upon for medical diagnosis or treatment. MDX Medical, Inc. (\"MDX\"), the provider of this website, does not recommend or endorse any particular healthcare provider whose information or ratings appear on this website; and (2) MDX has granted you a limited license to access and use this website for your own noncommercial use. You are not permitted to copy, reproduce, distribute, transmit, mirror, frame, scrape, extract, wrap, create derivative works of, reverse engineer, decompile or disassemble any part or aspect of this website.", "Incorrect terms text on landing page");
        m_assert.assertTrue(serp.commonModule().logo().isDisplayed().value(), "Logo not displayed on search page");
        m_assert.assertTrue(serp.resultHeaderQsymia().getText().toString().equals("All providers sorted by prescription data and location based on zip code searched."), "Incorrect search text");

        m_assert.assertAll();
    }

    @TestCase(id=3358)
    @Test
    public void checkSerp() {
        m_assert = new SoftAssert();

        LandingPage landingPage = new LandingPage();
        landingPage.get(url);
        landingPage.locationTextBox().clearField().sendKeys("33021");
        landingPage.searchButton().click();

        SearchPage serp = new SearchPage();
        serp.locationTextBox().clearField().sendKeys("02111");
        serp.searchButton().click();

        m_assert.assertTrue(serp.getResultCount()==19, "Number of results not 19");
//        m_assert.assertTrue(countOccurrences(serp.states(), "WA")==10,
//                "Not all 10 results are from WA");
        m_assert.assertTrue(sortedByExternal(serp.namesQsymia()), "Results are not sorted by external file");
        m_assert.assertTrue(serp.isProfileLinkCorrect(), "Profile link format incorrect on page " + serp.currentPage().getText().toString());

        m_assert.assertAll();
    }

    private int countOccurrences(FluentWebElements els, String text) {
        int result = 0;
        for (FluentWebElement el: els) {
            if (el.getText().toString().equals(text)) result++;
        }
        return result;
    }

    private boolean sortedByExternal(FluentWebElements names) {
        if (!names.get(0).getText().toString().equals("Dr. Caroline M Apovian MD")) {
            return false;
        }
        if (!names.get(1).getText().toString().equals("Dr. Osama S Hamdy MD, PHD")) {
            return false;
        }
        if (!names.get(2).getText().toString().equals("Dr. Florencia Halperin MD")) {
            return false;
        }
        if (!names.get(3).getText().toString().equals("Dr. Winfield S Butsch MD")) {
            return false;
        }
        return true;
    }
}
