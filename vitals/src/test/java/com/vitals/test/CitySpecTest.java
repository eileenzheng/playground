package com.vitals.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.vitals.DriverManager;
import com.vitals.pages.CitySpecLandingPage;
import com.vitals.pages.CitySpecPage;
import com.vitals.pages.CitySpecStatePage;
import com.vitals.pages.HomePage;

public class CitySpecTest{
    
    WebDriver driver;
    String url;
    
    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) throws Exception {
        this.url = url;
    }

    /* - click random specialty from footer
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
        home.footer.clickSpecialtyLink();

        CitySpecLandingPage landingPage = home.footer.clickRandomSpecialty();        
        Assert.assertTrue(landingPage.isTitleMatched(), 
                "Landing Page title did not match.");
        
        CitySpecStatePage statePage = landingPage.clickState();
        Assert.assertTrue(statePage.isTitleMatched(), 
                "State Page title did not match.");
        
        CitySpecPage cityPage = statePage.clickCity();
        Assert.assertTrue(cityPage.isTitleMatched(), 
                "City Page title did not match.");
    }

    /* - select random city listing page
     * - verify the page contains at least 1 result
     * - click 'next page' link if it exists
     * - verify page 2 is the current page and contains at least 1 result */
    @Test
    public void testCityPage() {
        
    	driver = DriverManager.getDriver();
        driver.get(url);
        
        HomePage home = PageFactory.initElements(driver,HomePage.class);
        home.footer.clickSpecialtyLink();
        
        CitySpecLandingPage landingPage = home.footer.clickRandomSpecialty();
        CitySpecPage cityPage = landingPage.clickCity();
        
        Assert.assertTrue(cityPage.hasResult(), 
                "City Page does not have result!");
        
        if (cityPage.hasNext()) {
            cityPage = cityPage.clickNext();
            
            Assert.assertTrue(cityPage.hasResult(), 
                    "City Page does not have result!");
            
            Assert.assertTrue(cityPage.getActivePageNumber().equals("2"), 
                    "Incorrect page number, should be on city/spec page 2 now!");
        }
    }
}
