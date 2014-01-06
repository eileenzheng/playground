package com.vitals.test;

import com.vitals.DriverManager;
import com.vitals.helpers.Profile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
import com.vitals.pages.SearchResultsRefinement;

import java.util.List;

/**
 * Vitals.com test
 */
public class SearchTest {

    WebDriver driver;
    SoftAssert m_assert;
    String url;
    
    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) throws Exception {
        this.url = url;
    }

    /* - enter zip code '1000' in location box of global header
     * - verify that at least 1 suggestion of 'New York' is returned in auto-complete */
    @Test
    public void autoSuggestLocation() {
    	driver = DriverManager.getDriver();

        driver.get(url);
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);

        String location = "1000";
        String city = "New York";
       
        homePage.header.openLocationBox();
        homePage.header.enterLocation(location);
        
        Assert.assertTrue(homePage.header.checkLocationSuggestions(city), location + " does not contain " + city);
    }

    /* - enter 'Smith' in name search box of global header
     * - verify that at least 1 suggestion containing 'Smith' is returned in auto-complete */
    @Test
    public void autoSuggestName() {
    	driver = DriverManager.getDriver();

        driver.get(url);
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);

        String name = "Smith";

        homePage.header.enterSearchTerm(name);

        Reporter.log("The Docs> " + homePage.header.getNameSuggestions());

        Assert.assertTrue(homePage.header.checkNameSuggestions(name));
    }

    /* - search for 'Smith' in zip '10036' 
     * - verify the first page of serp all contains 'smith'*/
    // not necessarily true - smith can be a middle name and abbreviated 
    @Test
    public void searchByName() {
        m_assert = new SoftAssert();
        driver = DriverManager.getDriver();

        driver.get(url);
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);

        String name = "Smith";
        String location = "10036";

        homePage.header.enterSearchTerm(name);
        homePage.header.openLocationBox();
        homePage.header.enterLocation(location);

        SearchResultsPage results = homePage.header.clickGoButton();
        for (WebElement el : results.drList()) {
            String drName = el.findElement(By.cssSelector(".head>h4>a")).getText();
            Reporter.log(drName);
            m_assert.assertTrue(drName.toLowerCase().contains(name.toLowerCase()),drName + " Does not contain " + name);
        }

        m_assert.assertAll();
    }

    /* - search for 'cardiologist' in global header 
     * - click a sub-specialty in the middle and search
     * - log the number of results returned by the serp*/
    @Test
    public void selectSubSpecialtySearch() {
    	driver = DriverManager.getDriver();

        driver.get(url);
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);

        String spec = "Cardiologist";
        homePage.header.enterSearchTerm(spec);

        Reporter.log(homePage.header.getSpecialtySearchSuggestions());
        
        Assert.assertTrue(homePage.header.locationSearchIsPopulated(),"Location search is not populated");
        Reporter.log(homePage.header.getCurrentPopulatedLocation());

        SearchResultsPage results = homePage.header.clickFirstSpecialty();

        Reporter.log(results.getResultsCount() + " for search: " + spec);
    }

    /* - search for 'asthma' in global header 
     * - click a sub-condition in the middle and search
     * - log the number of results returned by the serp*/
    @Test
    public void selectSubConditionSearch() {
    	driver = DriverManager.getDriver();

        driver.get(url);
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);

        String cond = "Asthma";
        homePage.header.enterSearchTerm(cond);

        Reporter.log(homePage.header.getConditionSearchSuggestions());
        
        Assert.assertTrue(homePage.header.locationSearchIsPopulated(),"Location search is not populated");
        Reporter.log(homePage.header.getCurrentPopulatedLocation());

        SearchResultsPage results = homePage.header.clickRandomCondition();

        Reporter.log(results.getResultsCount() + " for search: " + cond);
    }
    
    /* - search for Cardiologists in New York
     * - check number of results is within expected range
     * - turn on different filters
     * - make sure filter is working by checking result number narrows */
    @Test
    public void serpFilters() {
    	driver = DriverManager.getDriver();

        driver.get(url);
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        
        m_assert = new SoftAssert();
        
		do {
			homePage.header.openLocationBox();
			homePage.header.enterLocation("10036");
		} while (!homePage.header.locationSearchIsPopulated());
		
		homePage.header.enterSearchTerm("Cardiologist");

        SearchResultsPage results = homePage.header.clickFirstSpecialty();
       
        m_assert.assertTrue((results.getResultsCountNumber()>1500 && results.getResultsCountNumber() <4000), 
        		"# of result for Cardiologists in New York not within expected range! ");     
        int count = results.getResultsCountNumber();
        Reporter.log(count + " results with default filter settings");
        SearchResultsRefinement filter = PageFactory.initElements(driver, SearchResultsRefinement.class);
 
        filter = filter.clickWithinFiveMiles();
        results = PageFactory.initElements(driver, SearchResultsPage.class);
        m_assert.assertTrue(results.getResultsCountNumber()< count && results.getResultsCountNumber()>0, 
        		"5 mile filter not returning proper number of results!");
        count = results.getResultsCountNumber();
        Reporter.log(count + " results after 5 miles filter");
        
        filter = filter.genderSelectMale();
        results = PageFactory.initElements(driver, SearchResultsPage.class);
        m_assert.assertTrue(results.getResultsCountNumber()< count && results.getResultsCountNumber()>0, 
        		"Gender filter not returning proper number of results!");
        count = results.getResultsCountNumber();
        Reporter.log(count + " results after male gender filter");
        
        filter = filter.clickBoardCertified();
        results = PageFactory.initElements(driver, SearchResultsPage.class);
        m_assert.assertTrue(results.getResultsCountNumber()<= count && results.getResultsCountNumber()>0, 
        		"Board certified filter not returning proper number of results!");
        count = results.getResultsCountNumber();
        Reporter.log(count + " results after board certified filter");
        
        filter = filter.clickUSEducated();
        results = PageFactory.initElements(driver, SearchResultsPage.class);
        m_assert.assertTrue(results.getResultsCountNumber()<= count && results.getResultsCountNumber()>0, 
        		"U.S. educated filter not returning proper number of results!");
        count = results.getResultsCountNumber();
        Reporter.log(count + " results after U.S. educated filter");
        
        filter = filter.clickResetFilters();
        
        m_assert.assertAll();
    }

    /* - loop through the provided zip codes, for each do:
     * - search for 'smith' in the given zip code
     * - for each result in serp, check the name in serp matches name in profile */
    @Test (dataProvider = "zipCodes")
    public void compareResultsToProfile(String zipCodes) {
        m_assert = new SoftAssert();

        driver = DriverManager.getDriver();

        driver.get(url);
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);

        String name = "Smith";

        homePage.header.enterSearchTerm(name);
        homePage.header.openLocationBox();
        homePage.header.enterLocation(zipCodes);

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