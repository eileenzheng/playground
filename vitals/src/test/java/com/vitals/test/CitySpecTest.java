package com.vitals.test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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
    
    /* - select 10 random city spec pages
     * - check all the providers on page 1 to make sure name is long enough*/
    @Test
    public void testNames() {
    	
    	driver = DriverManager.getDriver();
    	driver.get(url);
    	SoftAssert m_assert = new SoftAssert();
    	
    	HomePage home = PageFactory.initElements(driver,HomePage.class);
        home.footer.clickSpecialtyLink();
        
        CitySpecLandingPage landingPage = home.footer.clickRandomSpecialty();
        List<String> urls = landingPage.getCityUrl(10);
        
        for (String cityurl: urls) {
        	driver.get(cityurl);
        	CitySpecPage cityPage = PageFactory.initElements(driver, CitySpecPage.class); 
        	
        	for (WebElement el: cityPage.getResults()) {
        		m_assert.assertTrue((el.findElement(By.cssSelector(".head>h4>a"))).getText().length()>7, "Provider name is too short");
        		Reporter.log("Name too short: " + el.findElement(By.cssSelector(".head>h4>a")).getText() + "\n");
        	}
        }
        
        m_assert.assertAll();
    }
}
