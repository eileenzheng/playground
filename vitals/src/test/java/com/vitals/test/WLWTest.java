package com.vitals.test;

import com.vitals.helpers.Profile;
import com.vitals.pages.HomePage;
import com.vitals.pages.SearchResultsPage;
import com.vitals.pages.wlw.LandingPage;
import com.vitals.pages.wlw.SearchPage;
import com.vitalsqa.listener.DriverManager;
import com.vitalsqa.testrail.TestCase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class WLWTest {

    private WebDriver driver;
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
        driver = DriverManager.getDriver();
        m_assert = new SoftAssert();
        driver.get(url);

        LandingPage landingPage = PageFactory.initElements(driver,LandingPage.class);
        m_assert.assertEquals(landingPage.header.getHeaderText(), "Find a Physician in Your Area", "Incorrect header text on landing page");
        m_assert.assertTrue(landingPage.header.isLogoDisplayed(), "Logo not displayed on landing page");

        landingPage.selectOption("Internists");
        landingPage.enterZipCode("10036");

        SearchPage serp = landingPage.clickSearch();
        m_assert.assertEquals(serp.header.getHeaderText(), "Find a Physician in Your Area", "Incorrect header text on search page");
        m_assert.assertTrue(serp.header.isLogoDisplayed(), "Logo not displayed on search page");

        m_assert.assertAll();
    }

    @TestCase(id=1781)
    @Test
    public void checkSerpProfileUrl() {
        driver = DriverManager.getDriver();
        m_assert = new SoftAssert();
        driver.get(url);

        LandingPage landingPage = PageFactory.initElements(driver,LandingPage.class);
        landingPage.selectOption("Internists");
        landingPage.enterZipCode("10036");

        SearchPage serp = landingPage.clickSearch();
        m_assert.assertTrue(serp.isProfileLinkCorrect(), "Profile link format incorrect on page " + serp.getCurrentPageNumber());
        serp = serp.clickNext();
        m_assert.assertTrue(serp.isProfileLinkCorrect(), "Profile link format incorrect on page " + serp.getCurrentPageNumber());
        serp = serp.clickRandomPage();
        m_assert.assertTrue(serp.isProfileLinkCorrect(), "Profile link format incorrect on page " + serp.getCurrentPageNumber());
        if (!serp.getCurrentPageNumber().equals("1")) {
            serp = serp.clickPrev();
            m_assert.assertTrue(serp.isProfileLinkCorrect(), "Profile link format incorrect on page " + serp.getCurrentPageNumber());
        }

        m_assert.assertAll();
    }

    @TestCase(id=1782)
    @Test
    public void compareWithVitals() {
        m_assert = new SoftAssert();
        driver = DriverManager.getDriver();

        driver.get(vitalsUrl);
        HomePage home = PageFactory.initElements(driver, HomePage.class);
        home.header.enterSearchTerm("Family Practitioner");
        home.header.selectFirstSpecialty();
        String serpUrl = driver.getCurrentUrl();
        driver.get(serpUrl + "&location=10036");

        SearchResultsPage vitalsSerp = PageFactory.initElements(driver,SearchResultsPage.class);
        List<Profile> relevancyProfile = vitalsSerp.doctorResults(vitalsSerp.drList());
        int vitalsResultCount = vitalsSerp.getResultsCountNumber();
        vitalsSerp.refinement.openSortDropDown();
        vitalsSerp.refinement.clickSortByDistance();
        vitalsSerp = PageFactory.initElements(driver,SearchResultsPage.class);
        List<Profile> distanceProfile = vitalsSerp.doctorResults(vitalsSerp.drList());
        vitalsSerp.refinement.openSortDropDown();
        vitalsSerp.refinement.clickSortByName();
        vitalsSerp = PageFactory.initElements(driver, SearchResultsPage.class);
        List<Profile> nameProfile = vitalsSerp.doctorResults(vitalsSerp.drList());
        vitalsSerp.refinement.openSortDropDown();
        vitalsSerp.refinement.clickSortByRating();
        vitalsSerp = PageFactory.initElements(driver, SearchResultsPage.class);
        List<Profile> ratingProfile = vitalsSerp.doctorResults(vitalsSerp.drList());

        driver.get(url);
        LandingPage landingPage = PageFactory.initElements(driver,LandingPage.class);
        landingPage.enterZipCode("10036");
        landingPage.selectOption("Family Physicians");
        SearchPage serp = landingPage.clickSearch();

        m_assert.assertEquals(vitalsResultCount, serp.getResultCount(), "Number of results does not match Vitals (sort by relevancy)");
        m_assert.assertTrue(serp.isProviderMatchingVitals(relevancyProfile), "First X results does not match Vitals(sort by relevancy)");
        serp = serp.sortBy("Distance");
        m_assert.assertEquals(vitalsResultCount, serp.getResultCount(), "Number of results does not match Vitals(sort by distance)");
        m_assert.assertTrue(serp.isProviderMatchingVitals(distanceProfile), "First X results does not match Vitals(sort by distance)");
        serp = serp.sortBy("Name");
        m_assert.assertEquals(vitalsResultCount, serp.getResultCount(), "Number of results does not match Vitals(sort by name)");
        m_assert.assertTrue(serp.isProviderMatchingVitals(nameProfile), "First X results does not match Vitals(sort by name)");
        serp = serp.sortBy("Rating");
        m_assert.assertEquals(vitalsResultCount, serp.getResultCount(), "Number of results does not match Vitals(sort by rating)");
        m_assert.assertTrue(serp.isProviderMatchingVitals(ratingProfile), "First X results does not match Vitals(sort by rating)");

        m_assert.assertAll();
    }
}
