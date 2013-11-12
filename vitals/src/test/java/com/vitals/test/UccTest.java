package com.vitals.test;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
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
import com.vitals.pages.UccStatePage;

public class UccTest {
	
	WebDriver driver;
	SoftAssert m_assert;
    String url;
    
    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) throws Exception {
        this.url = url;
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
    @Test (dataProvider = "urls")
    public void testCityPageResults(String urls) {
    	m_assert = new SoftAssert();
    	driver = DriverManager.getDriver();
    	driver.get(url + urls);
    	
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
    
    @DataProvider(name = "urls")
    public Object[][] generateUrls() {
        return new Object[][] {
                {"/urgent-care/ny/new-york"},
                {"/urgent-care/ca/los-angeles"},
                {"/urgent-care/ma/boston"}
        };
    }
}
