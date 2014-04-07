package com.vitals.test;

import com.vitals.helpers.Profile;
import com.vitals.pages.profile.ProfileCommonPage;
import com.vitalsqa.testrail.TestCase;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.vitals.pages.HomePage;
import com.vitals.pages.SearchResultsPage;
import java.util.List;

public class SearchTest {

    SoftAssert m_assert;
    String url;
    
    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) throws Exception {
        this.url = url;
    }

    @TestCase(id=1548)
    @Test
    public void searchByName() {
        m_assert = new SoftAssert();

        HomePage home = new HomePage();
        home.get(url);

        String name = "Todd";
        String location = "Austin, TX";

        home.headerModule().locationTextBoxSelector().click();
        home.headerModule().enterLocation(location);
        home.headerModule().locationSuggestions().get(0).click();
        home.headerModule().enterSearchTerm(name);
        home.headerModule().goButton().click();

        SearchResultsPage results = new SearchResultsPage();
        Assert.assertTrue(results.drList().size()>0, "No results for name search!");

        for (Profile el : results.doctorResults(results.drList())) {
            String drName = el.getName();
            Reporter.log(drName);
            m_assert.assertTrue(drName.toLowerCase().contains(name.toLowerCase()),drName + " Does not contain " + name);
        }

        m_assert.assertAll();
    }

    @TestCase (id=1549)
    @Test
    public void searchBySpecialty() {
        HomePage home = new HomePage();
        home.get(url);

        String spec = "Cardiologist";
        home.headerModule().enterSearchTerm(spec);
        Reporter.log(home.headerModule().getSpecialtySearchSuggestions());

        home.headerModule().specialtySuggestions().get(0).click();
        SearchResultsPage results = new SearchResultsPage();

        Assert.assertTrue(results.drList().size()>0, "No results for specialty search!");
        Reporter.log(results.getResultsCountNumber() + " for search: " + spec);
    }

    @TestCase (id=1550)
    @Test
    public void searchByCondition() {
        HomePage home = new HomePage();
        home.get(url);

        String cond = "Asthma";
        home.headerModule().enterSearchTerm(cond);
        Reporter.log(home.headerModule().getConditionSearchSuggestions());

        home.headerModule().conditionSuggestions().get(0).click();
        SearchResultsPage results = new SearchResultsPage();

        Assert.assertTrue(results.drList().size()>0, "No results for condition search!");
        Reporter.log(results.getResultsCountNumber() + " for search: " + cond);
    }

    @TestCase (id=1551)
    @Test
    public void serpFilters() {
        m_assert = new SoftAssert();

        SearchResultsPage results = new SearchResultsPage();
        results.get(url + "/cardiologists/ny/new-york");

        m_assert.assertTrue((results.getResultsCountNumber()>2500 && results.getResultsCountNumber() <3000),
        		"# of result for Cardiologists in New York not within expected range! ");
        int count = results.getResultsCountNumber();
        Reporter.log(count + " results with default filter settings");

        results.refinement().distanceDropDown().click();
        results.refinement().distance5().click();
        results.waitForJQuery();

        m_assert.assertTrue(results.getResultsCountNumber()< count && results.getResultsCountNumber()>0,
        		"5 mile filter not returning proper number of results!");
        count = results.getResultsCountNumber();
        Reporter.log(count + " results after 5 miles filter");

        results.refinement().toggleFilter().click();
        results.refinement().genderDropDown().click();
        results.refinement().genderMale().click();
        results.waitForJQuery();

        m_assert.assertTrue(results.getResultsCountNumber()< count && results.getResultsCountNumber()>0,
        		"Gender filter not returning proper number of results!");
        count = results.getResultsCountNumber();
        Reporter.log(count + " results after male gender filter");

        results.refinement().boardCertified().click();
        results.waitForJQuery();

        m_assert.assertTrue(results.getResultsCountNumber()<= count && results.getResultsCountNumber()>0,
        		"Board certified filter not returning proper number of results!");
        count = results.getResultsCountNumber();
        Reporter.log(count + " results after board certified filter");

        results.refinement().usEducated().click();
        results.waitForJQuery();

        m_assert.assertTrue(results.getResultsCountNumber()<= count && results.getResultsCountNumber()>0,
        		"U.S. educated filter not returning proper number of results!");
        count = results.getResultsCountNumber();
        Reporter.log(count + " results after U.S. educated filter");

        results.refinement().resetFilters().click();

        m_assert.assertAll();
    }

    @TestCase(id=1733)
    @Test
    public void checkMap() {
    	m_assert = new SoftAssert();

        SearchResultsPage serp = new SearchResultsPage();
        serp.get(url + "/dermatologists");
        m_assert.assertTrue(!serp.isMapEmpty(), "Map is empty for specialty browse path");

        serp.get(url + "/condition/diabetes");
        m_assert.assertTrue(!serp.isMapEmpty(), "Map is empty for condition browse path");

        serp.headerModule().enterSearchTerm("Todd");
        serp.headerModule().goButton().click();
        m_assert.assertTrue(!serp.isMapEmpty(), "Map is empty for name search path");

        serp.headerModule().enterSearchTerm("Cardiologist");
        serp.headerModule().specialtySuggestions().get(0).click();
        m_assert.assertTrue(!serp.isMapEmpty(), "Map is empty for specialty search path");

        serp.headerModule().enterSearchTerm("Diabetes");
        serp.headerModule().conditionSuggestions().get(0).click();
        m_assert.assertTrue(!serp.isMapEmpty(), "Map is empty for condition serach path");

        m_assert.assertAll();
    }

    @TestCase(id=1552)
    @Test (dataProvider = "zipCodes")
    public void compareResultsToProfile(String zipCodes) {
        m_assert = new SoftAssert();

        HomePage homePage = new HomePage();
        homePage.get(url);

        String name = "Smith";

        homePage.headerModule().locationTextBoxSelector().click();
        homePage.headerModule().enterLocation(zipCodes);
        homePage.headerModule().locationSuggestions().get(0).click();
        homePage.headerModule().enterSearchTerm(name);
        homePage.headerModule().goButton().click();

        SearchResultsPage results = new SearchResultsPage();
        ProfileCommonPage profile = new ProfileCommonPage();

        List<Profile> docs = results.doctorResults(results.drList());

        Reporter.log("ResultsPage,ProfileCommonPage,ProfilePageUrl");
        for (Profile doc : docs) {
            profile.get(doc.getUrl());
            doc.setProfileName(profile.name().getText().toString().trim());
            m_assert.assertTrue(doc.searchAndProfileMatches(),"Did not match: " + doc.toString());
            Reporter.log(doc.toString());
        }

        m_assert.assertAll();

    }

    @DataProvider(name = "zipCodes")
    public Object[][] generateZipCodes() {
        return new Object[][] {
                {"33021"},
                {"18015"},
                {"16434"},
                {"02201"},
                {"10001"},
                {"07801"},
                {"18102"}
        };
    }

}