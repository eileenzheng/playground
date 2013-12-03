package com.vitals.test;

import com.vitals.DriverManager;
import com.vitals.helpers.PatientLinkSetFeatures;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.vitals.pages.PatientLinkBookModal;
import com.vitals.pages.CitySpecPage;
import com.vitals.pages.PatientLinkCenterAd;
import com.vitals.pages.PatientLinkRrAd;
import com.vitals.pages.ProfilePage;
import com.vitals.pages.SEOProfilePage;
import com.vitals.pages.SearchResultsPage;
import com.vitals.pages.UccCityPage;

public class PatientLinkTest {
	
    private WebDriver driver;
    private SoftAssert m_assert;
    private boolean alreadyInit = false;
    private String url;
    
    private static final String serpUrl = "/search?type=specialty&provider_type=1&q=110-Internist&specialist_id=110&location=New+York%2C+NY";
    private static final String citySpecUrl = "/specialists/internists/new-york/new-york";
    private static final String profileUrl = "/doctors/Dr_Donald_Belsito/profile";
    private static final String profileHeaderUrl = "/doctors/Dr_Adelle_Quintana/profile";
    private static final String uccUrl = "/urgent-care/ny/yonkers";
    
    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) throws Exception {
        this.url = url;
    }
    
    /* - tests patient link elements on the profile header, including: 
     * - doctor's site, phone number, book online button */
    @Test
    public void testProfileHeader() {
    	driver = DriverManager.getDriver();
    	
        driver.get(url + profileHeaderUrl);
        // launch again to go to profile instead of seo profile
        driver.get(url + profileHeaderUrl);
        ProfilePage profile = PageFactory.initElements(driver, ProfilePage.class);
        
        m_assert = new SoftAssert();
        m_assert.assertTrue(profile.isDrSitePresent(), "Doctor's site is missing!");
        m_assert.assertEquals(profile.getSiteUrl(), "http://www.laserandmohs.com", "Doctor's site url is wrong!");
        m_assert.assertTrue(profile.isPLPhoneNumberPresent(), "Phone number is missing!");
        m_assert.assertTrue(profile.getPLPhoneNumber().contains("(646) 499-2330") , "Phone number is incorrect!");
        m_assert.assertTrue(profile.isBookApptPresent(), "Book online button is missing!");
        m_assert.assertAll();
    }
    
    /* - tests the right rail ad on provider serp
     * - opens up provider serp page and call testRrAd() */
    @Test
    public void testRrAdSerp() {
    	driver = DriverManager.getDriver();
    	
        driver.get(url + serpUrl);
        SearchResultsPage serp = PageFactory.initElements(driver, SearchResultsPage.class);
        
        PatientLinkRrAd ad = serp.rrAd;
        testRrAd(ad);
    }

    /* - tests the right rail ad on city spec page
     * - opens up city spec page and call testRrAd() */
    @Test
    public void testRrAdCitySpec() {
    	driver = DriverManager.getDriver();
    	
        driver.get(url+ citySpecUrl);
        CitySpecPage find = PageFactory.initElements(driver, CitySpecPage.class);
        
        PatientLinkRrAd ad = find.rrAd;
        testRrAd(ad);
    }
    
    /* - tests the right rail ad on ucc city listing page
     * - opens up ucc city listing page and call testRrAd() */
    @Test
    public void testRrAdUcc() {
    	driver = DriverManager.getDriver();
    	
        driver.get(url+ uccUrl);
        UccCityPage ucc = PageFactory.initElements(driver, UccCityPage.class);
        
        PatientLinkRrAd ad = ucc.rrAd;
        testRrAd(ad);
    }

    /* - tests the right rail ad on seo profile
     * - opens up seo profile page and call testRrAd() */
    @Test
    public void testRrSeoProfile() {
    	driver = DriverManager.getDriver();
    	
        driver.get(url + profileUrl);
        SEOProfilePage profile = PageFactory.initElements(driver, SEOProfilePage.class);
        
        PatientLinkRrAd ad = profile.rrAd;
        testRrAd(ad);    
    }

    /* - tests the right rail ad on full profile
     * - opens up full profile page and call testRrAd() */
    @Test
    public void testRrProfile() {
    	driver = DriverManager.getDriver();
    	
        driver.get(url + profileUrl);
        driver.get(url + profileUrl);
        ProfilePage profile = PageFactory.initElements(driver, ProfilePage.class);
        
        PatientLinkRrAd ad = profile.rrAd;
        testRrAd(ad);    
    }

    /* - tests the center well ad on provider serp
     * - opens up provider serp page and call testCenterAd() */
    @Test
    public void testCenterAdSerp() {
    	driver = DriverManager.getDriver();
    	
        driver.get(url + serpUrl);
        SearchResultsPage serp = PageFactory.initElements(driver, SearchResultsPage.class);
        
        PatientLinkCenterAd ad = serp.centerAd;
        testCenterAd(ad);
    }

    /* - tests the center well ad on city spec page
     * - opens up city spec page and call testCenterAd() */
    @Test
    public void testCenterAdCitySpec() {
    	driver = DriverManager.getDriver();
    	
        driver.get(url+ citySpecUrl);
        CitySpecPage find = PageFactory.initElements(driver, CitySpecPage.class);
        
        PatientLinkCenterAd ad = find.centerAd;
        testCenterAd(ad);
    }
    
    /* - tests the center well ad on ucc city listing page
     * - opens up ucc city listing page and call testCenterAd() */
    @Test
    public void testCenterAdUcc() {
    	driver = DriverManager.getDriver();
    	
        driver.get(url+ uccUrl);
        UccCityPage ucc = PageFactory.initElements(driver, UccCityPage.class);
        
        PatientLinkCenterAd ad = ucc.centerAd;
        testCenterAd(ad);
    }
    
    // only need to call init function once for all the tests
    public void init() {
    	if (!alreadyInit) {
        	PatientLinkSetFeatures.init();
        	alreadyInit = true;
    	}
    }
    
    // loop through given list of right rail ad and test the patient link features against expected
    public void testRrAd(PatientLinkRrAd ad) {

    	init();
    	m_assert = new SoftAssert();
    	PatientLinkSetFeatures pl = new PatientLinkSetFeatures();
    	PatientLinkBookModal modal;
        
        for (int i=0; i<ad.getSize(); i++) {

            pl.setExpected(ad.getName(i));

            m_assert.assertEquals(ad.getSpecialty(i), pl.getExpectedSpecialty(),
                    "Featured specialty for " + ad.getName(i) + " did not match");
            
            m_assert.assertEquals(ad.getAddressLine1(i), pl.getExpectedAddress(),
                    "Address Line 1 for " + ad.getName(i) + " did not match");
            
    		if (ad.getAddressLine2(i) != null) {
    			m_assert.assertEquals(ad.getAddressLine2(i), pl.getExpectedAddressLine2(),
    					"Address Line 2 for " + ad.getName(i) + " did not match");
    		}
            
            m_assert.assertEquals(ad.getCity(i), pl.getExpectedCity(),
                    "City for " + ad.getName(i) + " did not match");
            
            m_assert.assertEquals(ad.getState(i), pl.getExpectedState(),
                    "State for " + ad.getName(i) + " did not match");
            
            m_assert.assertEquals(ad.getZip(i), pl.getExpectedZip(),
                    "Zip for " + ad.getName(i) + " did not match");
            
            if (!pl.getExpectedNumber().equals("")) {
            	m_assert.assertEquals(ad.getPhoneNumber(i), pl.getExpectedNumber(),
            			"Phone number for " + ad.getName(i) + " did not match");
            }
            
            if (pl.hasBookOnline()) {
            	m_assert.assertTrue(ad.isBookPresent(i), "Book Online button is not displayed for " + ad.getName(i));
            	if (pl.getBookType()==1) {
            		modal = ad.clickBook(i);
            		modal.typeFirstName("test");
            		modal.typeLastName("test_last");
            		modal.selectRadioAfternoon();
            		modal.selectDrop1Week();
            		modal.submit();
            		modal.close();
            	}
            	else if (pl.getBookType()==2) {
            		modal = ad.clickBook(i);
            		modal.close();
            	}
            }
            
            if (pl.hasLogo()) {
            	m_assert.assertTrue(ad.isLogoPresent(i), "Logo is not displayed for " + ad.getName(i));
            }
        }
        
        m_assert.assertAll();
    }
    
 // loop through given center well ad and test the patient link features against expected
    public void testCenterAd(PatientLinkCenterAd ad) {
        
    	init();
    	m_assert = new SoftAssert();
    	PatientLinkSetFeatures pl = new PatientLinkSetFeatures();
    	pl.setExpected(ad.getName());
    	PatientLinkBookModal modal;

    	m_assert.assertEquals(ad.getSpecialty(), pl.getExpectedSpecialty(),
                "Featured specialty for " + ad.getName() + " did not match");
        
        m_assert.assertEquals(ad.getAddressLine1(), pl.getExpectedAddress(),
                "Address Line 1 for " + ad.getName() + " did not match");
        
		if (ad.getAddressLine2() != null) {
			m_assert.assertEquals(ad.getAddressLine2(), pl.getExpectedAddressLine2(),
					"Address Line 2 for " + ad.getName() + " did not match");
		}
        
        m_assert.assertEquals(ad.getCity(), pl.getExpectedCity(),
                "City for " + ad.getName() + " did not match");
        
        m_assert.assertEquals(ad.getState(), pl.getExpectedState(),
                "State for " + ad.getName() + " did not match");
        
        m_assert.assertEquals(ad.getZip(), pl.getExpectedZip(),
                "Zip for " + ad.getName() + " did not match");
        
        if (!pl.getExpectedNumber().equals("")) {
        	m_assert.assertEquals(ad.getPhoneNumber(), pl.getExpectedNumber(),
        			"Phone number for " + ad.getName() + " did not match" + ad.getPhoneNumber() + pl.getExpectedNumber());
        }
        
        if (pl.hasBookOnline()) {
        	m_assert.assertTrue(ad.isBookPresent(), "Book Online button is not displayed for " + ad.getName());
        	if (pl.getBookType()==1) {
        		modal = ad.clickBook();
        		modal.typeFirstName("test");
        		modal.typeLastName("test_last");
        		modal.selectRadioAfternoon();
        		modal.selectDrop1Week();
        		modal.submit();
        		modal.close();
        	}
        	else if (pl.getBookType()==2) {
        		modal = ad.clickBook();
        		modal.close();
        	}
        }
        
        if (pl.hasLogo()) {
        	m_assert.assertTrue(ad.isLogoPresent(), "Logo is not displayed");
        }
        
        m_assert.assertAll();
    }
}
