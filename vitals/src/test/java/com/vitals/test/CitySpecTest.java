package com.vitals.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.vitals.pages.CitySpecLandingPage;
import com.vitals.pages.CitySpecPage;
import com.vitals.pages.CitySpecStatePage;
import com.vitals.pages.HomePage;
import com.vitals.runners.RemoteTestRunner;

public class CitySpecTest extends RemoteTestRunner {
    
    WebDriver driver;

    @Parameters({"domain"})
    @Test
    public void testPageTitles(String domain) {
        
        driver = getDriver();
        driver.get(getUrl(domain));
        
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

    @Parameters({"domain"})
    @Test
    public void testCityPage(String domain) {
        
        driver = getDriver();
        driver.get(getUrl(domain));
        
        HomePage home = PageFactory.initElements(driver,HomePage.class);
        home.footer.clickSpecialtyLink();
        
        CitySpecLandingPage landingPage = home.footer.clickRandomSpecialty();
        CitySpecPage cityPage = landingPage.clickCity();
        
        Assert.assertTrue(cityPage.hasResult(), 
                "City Page does not have result!");
        
        if (cityPage.hasNext()) {
            cityPage.clickNext();
            
            Assert.assertTrue(cityPage.hasResult(), 
                    "City Page does not have result!");
            
            Assert.assertTrue(cityPage.getActivePageNumber().equals("2"), 
                    "Incorrect page number, should be on city/spec page 2 now!");
        }
    }
}