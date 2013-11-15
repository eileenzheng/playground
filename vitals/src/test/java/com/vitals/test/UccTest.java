package com.vitals.test;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.vitals.DriverManager;
import com.vitals.helpers.Ucc;
import com.vitals.pages.HomePage;
import com.vitals.pages.UccCityPage;
import com.vitals.pages.UccLandingPage;
import com.vitals.pages.UccProfileAboutPage;
import com.vitals.pages.UccProfileReviewsPage;
import com.vitals.pages.UccProfileServicesPage;
import com.vitals.pages.UccProfileSummaryPage;
import com.vitals.pages.UccStatePage;

public class UccTest {
	
	private WebDriver driver;
	private SoftAssert m_assert;
	private String url;
	private List<String> profileUrl = new ArrayList<String>();
	private List<String> cityUrl = new ArrayList<String>();
    
    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) throws Exception {
        this.url = url;
    }
    
    /* - this is a method serving the data providers
     * - it fetches up to 3 urgent care city listing urls randomly
     * - it fetches up to 15 urgent care center urls randomly */
    @Test
    public void testGenerateUrls() {
    	driver = DriverManager.getDriver();
        driver.get(url);
        
        HomePage home = PageFactory.initElements(driver,HomePage.class);
        UccLandingPage landingPage = home.header.clickUrgentCareLink();
        String landingPageUrl = driver.getCurrentUrl();
        
        for (int i=0; i<3; i++) {
        	UccCityPage cityPage = landingPage.clickCity();
        	cityUrl.add(driver.getCurrentUrl());
        	List<Ucc> uccs = cityPage.getResults();
        	for (Ucc ucc:uccs) {
        		if (profileUrl.size() < 15) {
        			System.out.println("#################profile size: " + profileUrl.size());
        			profileUrl.add(ucc.getUrl());
        		}
        		else
        			break;
        	}
        	driver.get(landingPageUrl);
        }
    }

    /* - click urgent care from header
     * - check breadcrumb of landing page
     * - click random state 
     * - check breadcrumb of state page
     * - click random city
     * - check breadcrumb of city page */
    @Test
    public void testPageTitles() {
        
    	driver = DriverManager.getDriver();
        driver.get(url);
        
        HomePage home = PageFactory.initElements(driver,HomePage.class);
        
        UccLandingPage landingPage = home.header.clickUrgentCareLink();      
        Assert.assertTrue(landingPage.isTitleMatched(), 
                "Landing Page title did not match.");
        
        UccStatePage statePage = landingPage.clickState();
        Assert.assertTrue(statePage.isTitleMatched(), 
                "State Page title did not match.");
        
        UccCityPage cityPage = statePage.clickCity();
        Assert.assertTrue(cityPage.isTitleMatched(), 
                "City Page title did not match.");
    }

    /* - go to urgent care landing page
     * - click a random city
     * - verify the page contains at least 1 result */
    @Test
    public void testCityPage() {
        
    	driver = DriverManager.getDriver();
        driver.get(url);
        
        HomePage home = PageFactory.initElements(driver,HomePage.class);
        UccLandingPage landingPage = home.header.clickUrgentCareLink();
        
        UccCityPage cityPage = landingPage.clickCity();
        Assert.assertTrue(cityPage.hasResult(), 
                "City Page does not have result!");
    }
    
    /* - for each ucc city page given, check: 
     * - the names, address for each results seems valid */
    @Test (dataProvider = "cityUrls", dependsOnMethods = {"testGenerateUrls"})
    public void testCityPageResults(String urls) {
    	m_assert = new SoftAssert();
    	driver = DriverManager.getDriver();
    	driver.get(urls);
    	
    	UccCityPage cityPage = PageFactory.initElements(driver, UccCityPage.class);
    	List<Ucc> uccs = cityPage.getResults();
    	
    	for (Ucc ucc : uccs) {
    		m_assert.assertTrue(ucc.isNameLongEnough(), "Name may be too short: " + ucc.getName());
    		m_assert.assertTrue(ucc.isAddressLongEnough(), "Address may be too short: " + ucc.getAddress());
    		m_assert.assertTrue(ucc.isCityLongEnough(), "City may be too short: " + ucc.getCity());
    		m_assert.assertTrue(ucc.isStateValid(), "State is invalid: " + ucc.getState());
    		m_assert.assertTrue(ucc.isZipValid(), "Zip is invalid: " + ucc.getZip());
    	}
    	
    	m_assert.assertAll();
    }
    
    /* - for each ucc profile page give, check:
     * - breadcrumb and h1 matches on all pages (summary, profile, services, about)
     * - the map links on summary page matches
     * - the total rating and rating breakdown adds up correctly*/
    @Test (dataProvider = "profileUrls", dependsOnMethods = {"testGenerateUrls"})
    public void testProfilePage(String urls) {
    	driver = DriverManager.getDriver();
    	driver.get(urls);
    	
    	UccProfileSummaryPage summary = PageFactory.initElements(driver, UccProfileSummaryPage.class);
    	Assert.assertTrue(summary.isTitleMatched(), "H1 name does not match breadcrumb.");
    	Assert.assertTrue(summary.isSummaryPage());
    	Assert.assertTrue(summary.isAddressMatched(), "Address in profile header card does not match the map.");
    	
    	UccProfileServicesPage services = summary.clickSeeAllLink();
    	Assert.assertTrue(services.isTitleMatched(), "H1 name does not match breadcrumb.");
    	Assert.assertTrue(services.isServicesPage());
    	Assert.assertTrue(services.getNumberOfUnavailableService()<6, "Too many unavailable services!");
    	
    	services = services.clickMenu();
    	UccProfileAboutPage about = services.clickMenuAbout();
    	Assert.assertTrue(about.isTitleMatched(), "H1 name does not match breadcrumb.");
    	Assert.assertTrue(about.isAboutPage());
    	
    	about = about.clickMenu();
    	UccProfileReviewsPage reviews = about.clickMenuReviews();
    	Assert.assertTrue(reviews.isTitleMatched(), "H1 name does not match breadcrumb.");
    	Assert.assertTrue(reviews.isReviewsPage());
    	Assert.assertEquals(reviews.getTotalRating(), reviews.getTotalRatingFromBreakDown(), "Total rating and breadown do not match! ");
    	Reporter.log("Total Rating: " + reviews.getTotalRating() + " vs Rating Breakdown: " 
    			+ reviews.getTotalRatingFromBreakDown() + " " + driver.getCurrentUrl());
    }
    
    // used for testCityPageResults method
    @DataProvider(name = "cityUrls")
    public Object[][] generateCityUrls() {
        Object[][] obj = new Object[cityUrl.size()][];
        
        for (int i=0; i<cityUrl.size(); i++) {
        	obj[i] = new Object[]{cityUrl.get(i)};
        }
        
        return obj;
    }
    
    // used for testUccProfilePage method
    @DataProvider(name = "profileUrls")
    public Object[][] generateProfileUrls() {
    	
        Object[][] obj = new Object[profileUrl.size()][];
        
        System.out.println("*******profileUrl size: " +profileUrl.size());
        for (int i=0; i<profileUrl.size(); i++) {
        	obj[i] = new Object[]{profileUrl.get(i)};
        	System.out.println(i);
        }
        
        return obj;
    }
}
