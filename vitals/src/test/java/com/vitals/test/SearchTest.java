package com.vitals.test;

import com.vitals.DriverManager;
import com.vitals.helpers.Profile;
import com.vitals.helpers.TestCase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.vitals.pages.HomePage;
import com.vitals.pages.ProfilePage;
import com.vitals.pages.SearchResultsPage;
import java.util.List;

public class SearchTest {

    WebDriver driver;
    SoftAssert m_assert;
    String url;
    
    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) throws Exception {
        this.url = url;
    }

    @TestCase(id = 1548)
    @Test
    public void searchByName() {
        m_assert = new SoftAssert();
        driver = DriverManager.getDriver();

        driver.get(url);
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);

        String name = "Todd";
        String location = "Austin, TX";

        homePage.header.openLocationBox();
        homePage.header.enterLocation(location);
        homePage.header.clickFirstLocation();
        homePage.header.enterSearchTerm(name);

        SearchResultsPage results = homePage.header.clickGoButton();
        for (Profile el : results.doctorResults(results.drList())) {
            String drName = el.getName();
            Reporter.log(drName);
            m_assert.assertTrue(drName.toLowerCase().contains(name.toLowerCase()),drName + " Does not contain " + name);
        }

        m_assert.assertAll();
    }

    @TestCase (id = 1549)
    @Test
    public void searchBySpecialty() {
    	driver = DriverManager.getDriver();

        driver.get(url);
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);

        String spec = "Cardiologist";
        homePage.header.enterSearchTerm(spec);

        Reporter.log(homePage.header.getSpecialtySearchSuggestions());
        
        Assert.assertTrue(homePage.header.locationSearchIsPopulated(),"Location search is not populated");
        Reporter.log(homePage.header.getCurrentPopulatedLocation());

        SearchResultsPage results = homePage.header.clickFirstSpecialty();

        Reporter.log(results.getResultsCountNumber() + " for search: " + spec);
    }

    @TestCase (id=1550)
    @Test
    public void searchByCondition() {
    	driver = DriverManager.getDriver();

        driver.get(url);
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);

        String cond = "Asthma";
        homePage.header.enterSearchTerm(cond);

        Reporter.log(homePage.header.getConditionSearchSuggestions());
        
        Assert.assertTrue(homePage.header.locationSearchIsPopulated(),"Location search is not populated");
        Reporter.log(homePage.header.getCurrentPopulatedLocation());

        SearchResultsPage results = homePage.header.clickFirstCondition();

        Reporter.log(results.getResultsCountNumber() + " for search: " + cond);
    }
    
    @TestCase (id=1551)
    @Test
    public void serpFilters() {
    	driver = DriverManager.getDriver();

        driver.get(url + "/cardiologists/ny/new-york");
        SearchResultsPage results = PageFactory.initElements(driver, SearchResultsPage.class);
        
        m_assert = new SoftAssert();
        
        m_assert.assertTrue((results.getResultsCountNumber()>2500 && results.getResultsCountNumber() <3000), 
        		"# of result for Cardiologists in New York not within expected range! ");     
        int count = results.getResultsCountNumber();
        Reporter.log(count + " results with default filter settings");
 
        results.refinement.openDistanceDropDown();
        results.refinement.clickWithin5Miles();
        results = PageFactory.initElements(driver, SearchResultsPage.class);
        m_assert.assertTrue(results.getResultsCountNumber()< count && results.getResultsCountNumber()>0, 
        		"5 mile filter not returning proper number of results!");
        count = results.getResultsCountNumber();
        Reporter.log(count + " results after 5 miles filter");
        
        results.refinement.clickToggleFilter();
        results.refinement.openGenderDropDown();
        results.refinement.genderSelectMale();
        results = PageFactory.initElements(driver, SearchResultsPage.class);
        m_assert.assertTrue(results.getResultsCountNumber()< count && results.getResultsCountNumber()>0, 
        		"Gender filter not returning proper number of results!");
        count = results.getResultsCountNumber();
        Reporter.log(count + " results after male gender filter");
        
        results.refinement.clickBoardCertified();
        results = PageFactory.initElements(driver, SearchResultsPage.class);
        m_assert.assertTrue(results.getResultsCountNumber()<= count && results.getResultsCountNumber()>0, 
        		"Board certified filter not returning proper number of results!");
        count = results.getResultsCountNumber();
        Reporter.log(count + " results after board certified filter");
        
        results.refinement.clickUSEducated();
        results = PageFactory.initElements(driver, SearchResultsPage.class);
        m_assert.assertTrue(results.getResultsCountNumber()<= count && results.getResultsCountNumber()>0, 
        		"U.S. educated filter not returning proper number of results!");
        count = results.getResultsCountNumber();
        Reporter.log(count + " results after U.S. educated filter");
        
        results.refinement.clickResetFilters();
        
        m_assert.assertAll();
    }

    @TestCase(id=1552)
    @Test (dataProvider = "zipCodes")
    public void compareResultsToProfile(String zipCodes) {
        m_assert = new SoftAssert();

        driver = DriverManager.getDriver();

        driver.get(url);
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);

        String name = "Smith";

        homePage.header.openLocationBox();
        homePage.header.enterLocation(zipCodes);
        homePage.header.clickFirstLocation();
        homePage.header.enterSearchTerm(name);
        

        SearchResultsPage results = homePage.header.clickGoButton();

        List<Profile> docs = results.doctorResults(results.drList());

        Reporter.log("ResultsPage,ProfilePage,ProfilePageUrl");
        for (Profile doc : docs) {
            driver.get(doc.getUrl());
            ProfilePage profilePg = PageFactory.initElements(driver, ProfilePage.class);
            doc.setProfileName(profilePg.getName().getText().trim());
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