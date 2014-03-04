package com.vitals.test;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.vitals.DriverManager;
import com.vitals.TestCase;
import com.vitals.helpers.Ucc;
import com.vitals.pages.HomePage;
import com.vitals.pages.UccSitemapPage;
import com.vitals.pages.UccProfileAboutPage;
import com.vitals.pages.UccProfileReviewsPage;
import com.vitals.pages.UccProfileServicesPage;
import com.vitals.pages.UccProfileSummaryPage;
import com.vitals.pages.UccSearchResultsPage;
import com.vitals.pages.UccSitemapStatePage;

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
    
    @Test
    public void generateUrls() {
    	driver = DriverManager.getDriver();
        driver.get(url + "/locations/urgent-care");
   
        UccSitemapPage locationPage = PageFactory.initElements(driver,UccSitemapPage.class);
        String landingPageUrl = driver.getCurrentUrl();
        
        for (int i=0; i<2; i++) {
        	UccSearchResultsPage cityPage = locationPage.clickCity();
        	cityUrl.add(driver.getCurrentUrl());
        	List<Ucc> uccs = cityPage.uccResults();
        	for (Ucc ucc:uccs) {
        		if (profileUrl.size() < 5) {
        			profileUrl.add(ucc.getUrl());
        		}
        		else
        			break;
        	}
        	driver.get(landingPageUrl);
        }
    }
    
    @TestCase(id=1647)
    @Test
    public void checkStatePages() {
    	m_assert = new SoftAssert();
    	driver = DriverManager.getDriver();
    	driver.get(url + "/locations/urgent-care");
    	
    	UccSitemapPage locationPage;
    	UccSitemapStatePage statePage;

    	for (int i=0; i<5; i++) {
    		locationPage = PageFactory.initElements(driver,UccSitemapPage.class);
    		locationPage.clickState();
    		statePage = PageFactory.initElements(driver, UccSitemapStatePage.class);
    		m_assert.assertTrue(statePage.hasResults(), "State page has no result! " + driver.getCurrentUrl());
    		driver.get(url + "/locations/urgent-care");
    	}
    	
    	m_assert.assertAll();
    }
    
    @TestCase(id=1644)
    @Test (dataProvider = "cityUrls", dependsOnMethods = {"generateUrls"})
    public void checkCitySerpResults(String urls) {
    	m_assert = new SoftAssert();
    	driver = DriverManager.getDriver();
    	driver.get(urls);
    	
    	UccSearchResultsPage serp = PageFactory.initElements(driver, UccSearchResultsPage.class);
    	List<Ucc> uccs = serp.uccResults();
    	
    	for (Ucc ucc : uccs) {
    		m_assert.assertTrue(ucc.isNameLongEnough(), "Name may be too short: " + ucc.getName());
    		m_assert.assertTrue(ucc.isAddressLongEnough(), "Address may be too short: " + ucc.getAddress());
    		m_assert.assertTrue(ucc.isCityLongEnough(), "City may be too short: " + ucc.getCity());
    		m_assert.assertTrue(ucc.isStateValid(), "State is invalid: " + ucc.getState());
    		m_assert.assertTrue(ucc.isZipValid(), "Zip is invalid: " + ucc.getZip());
    	}
    	
    	m_assert.assertAll();
    }
    
    @TestCase(id=1645)
    @Test (dataProvider = "profileUrls", dependsOnMethods = {"generateUrls"})
    public void checkProfilePages(String urls) {
    	m_assert = new SoftAssert();
    	driver = DriverManager.getDriver();
    	driver.get(urls);
    	
    	UccProfileSummaryPage summary = PageFactory.initElements(driver, UccProfileSummaryPage.class);
    	m_assert.assertTrue(summary.isSummaryPage(), "Summary page did not load");
    	m_assert.assertTrue(summary.isAddressMatched(), "Address in profile header card does not match the map.");
    	
    	UccProfileServicesPage services = summary.clickSeeAllLink();
    	m_assert.assertTrue(services.isTitleMatched(), "H1 name does not match breadcrumb.");
    	m_assert.assertTrue(services.isServicesPage(), "Services page did not load");
    	m_assert.assertTrue(services.getNumberOfUnavailableService()<6, "Too many unavailable services!");
    	
    	services = services.clickMenu();
    	UccProfileAboutPage about = services.clickMenuAbout();
    	m_assert.assertTrue(about.isTitleMatched(), "H1 name does not match breadcrumb.");
    	m_assert.assertTrue(about.isAboutPage(), "About page did not load");
    	
    	about = about.clickMenu();
    	UccProfileReviewsPage reviews = about.clickMenuReviews();
    	m_assert.assertTrue(reviews.isTitleMatched(), "H1 name does not match breadcrumb.");
    	m_assert.assertTrue(reviews.isReviewsPage(), "Review page did not load");
    	m_assert.assertEquals(reviews.getTotalRating(), reviews.getTotalRatingFromBreakDown(), "Total rating and breadown do not match! ");
    	Reporter.log("Total Rating: " + reviews.getTotalRating() + " vs Rating Breakdown: " 
    			+ reviews.getTotalRatingFromBreakDown() + " " + driver.getCurrentUrl());
    	
    	m_assert.assertAll();
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

        for (int i=0; i<profileUrl.size(); i++) {
        	obj[i] = new Object[]{profileUrl.get(i)};
        }
        
        return obj;
    }
    
    @TestCase(id=1646)
    @Test
    public void serpFilters() {
    	m_assert = new SoftAssert(); 
    	driver = DriverManager.getDriver();
        driver.get(url);
        
        HomePage home = PageFactory.initElements(driver,HomePage.class);
        home.header.openFindDropdown().clickFindByUcc();
        home.header.enterSearchTerm("city");
        
        UccSearchResultsPage uccSerp = home.header.clickGoButtonUcc();
        m_assert.assertTrue(uccSerp.getResultsCountNumber()>70 && uccSerp.getResultsCountNumber()<100,
        		"Number of results not within expected range");
        int count = uccSerp.getResultsCountNumber();
        int initialCount = uccSerp.getResultsCountNumber();
        Reporter.log(count + " results with no filters");
        
        uccSerp.refinement.clickToggleServices();
        
        uccSerp.refinement.clickPhysicals();
        uccSerp = PageFactory.initElements(driver, UccSearchResultsPage.class);
        m_assert.assertTrue(uccSerp.getResultsCountNumber()<=count && uccSerp.getResultsCountNumber()>0,
        		"Number of results incorrect with 'Physicals' filter");
        count = uccSerp.getResultsCountNumber();
        Reporter.log(count + " results with physicals filter");
        
        uccSerp.refinement.clickInjuries();
        uccSerp = PageFactory.initElements(driver, UccSearchResultsPage.class);
        m_assert.assertTrue(uccSerp.getResultsCountNumber()<=count && uccSerp.getResultsCountNumber()>0,
        		"Number of results incorrect with 'Injuries' filter");
        count = uccSerp.getResultsCountNumber();
        Reporter.log(count + " results with injuries filter");
        
        uccSerp.refinement.clickPhysicals();
        uccSerp.refinement.clickInjuries();
        uccSerp = PageFactory.initElements(driver, UccSearchResultsPage.class);
        
        int finalCount = uccSerp.getResultsCountNumber();
        m_assert.assertTrue(finalCount==initialCount,
        		"Number of results incorrect after resetting filters" + finalCount + " vs " + initialCount);
        Reporter.log(finalCount + " results after resetting filters vs " + initialCount + " initially");
        
        m_assert.assertAll();
    }
}
