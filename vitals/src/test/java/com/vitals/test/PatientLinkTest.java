package com.vitals.test;

import com.vitals.DriverManager;
import com.vitals.helpers.PatientLinkFeatures;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.vitals.pages.PatientLinkBookModal;
import com.vitals.pages.CitySpecPage;
import com.vitals.pages.HomePage;
import com.vitals.pages.PatientLinkCenterAd;
import com.vitals.pages.PatientLinkRrAd;
import com.vitals.pages.ProfilePage;
import com.vitals.pages.SEOProfilePage;
import com.vitals.pages.SearchResultsPage;

public class PatientLinkTest {
    private WebDriver driver;
    
    private static final String citySpecUrl = "/specialists/cardiologists/new-york/new-york";
    private static final String profileUrl = "/doctors/Dr_Nicholas_Soter/profile";
    private static final String profileHeaderUrl = "/doctors/Dr_Adelle_Quintana/profile";
    
    @Parameters({"url"})
    @Test
    public void testProfileHeader(String url) {
    	driver = DriverManager.getDriver();
        driver.get(url + profileHeaderUrl);
        // launch again to go to profile instead of seo profile
        driver.get(url + profileHeaderUrl);
        ProfilePage profile = PageFactory.initElements(driver, ProfilePage.class);
        
        Assert.assertTrue(profile.isDrSitePresent(), "Doctor's site is missing!");
        Assert.assertEquals(profile.getSiteUrl(), "http://www.laserandmohs.com", "Doctor's site url is wrong!");
        
        Assert.assertTrue(profile.isPLPhoneNumberPresent(), "Phone number is missing!");
        Assert.assertTrue(profile.getPLPhoneNumber().contains("(646) 499-2330") , "Phone number is incorrect!");
        
        Assert.assertTrue(profile.isBookApptPresent(), "Book appointment form is missing!");
    }
    
    @Parameters({"url"})
    @Test
    public void testRrAdSerp(String url) {
    	driver = DriverManager.getDriver();
        driver.get(url);
        
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.header.clickSpecialtyLink();
        String specialty = "Cardiologist";
        homePage.header.enterSpecialty(specialty);
        homePage.header.clickFirstMenuItem();
        SearchResultsPage serp = homePage.header.clickSearch();
        
        PatientLinkRrAd ad = serp.rrAd;
        testRrAd(ad);
    }

    @Parameters({"url"})
    @Test
    public void testRrAdFind(String url) {
    	driver = DriverManager.getDriver();
        driver.get(url+ citySpecUrl);
        
        CitySpecPage find = PageFactory.initElements(driver, CitySpecPage.class);
        
        PatientLinkRrAd ad = find.rrAd;
        testRrAd(ad);
    }

    @Parameters({"url"})
    @Test
    public void testRrSeoProfile(String url) {
    	driver = DriverManager.getDriver();
        driver.get(url + profileUrl);
        
        SEOProfilePage profile = PageFactory.initElements(driver, SEOProfilePage.class);
        
        PatientLinkRrAd ad = profile.rrAd;
        testRrAd(ad);    
    }

    @Parameters({"url"})
    @Test
    public void testRrProfile(String url) {
    	driver = DriverManager.getDriver();
        driver.get(url + profileUrl);
        driver.get(url + profileUrl);

        ProfilePage profile = PageFactory.initElements(driver, ProfilePage.class);
        
        PatientLinkRrAd ad = profile.rrAd;
        testRrAd(ad);    
    }

    @Parameters({"url"})
    @Test
    public void testCenterAdSerp(String url) {
    	driver = DriverManager.getDriver();
        driver.get(url);
        
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.header.clickSpecialtyLink();
        String specialty = "Cardiologist";
        homePage.header.enterSpecialty(specialty);
        homePage.header.clickFirstMenuItem();
        SearchResultsPage serp = homePage.header.clickSearch();
        
        PatientLinkCenterAd ad = serp.centerAd;
        testCenterAd(ad);
    }

    @Parameters({"url"})
    @Test
    public void testCenterAdFind(String url) {
    	driver = DriverManager.getDriver();
        driver.get(url+ citySpecUrl);
        
        CitySpecPage find = PageFactory.initElements(driver, CitySpecPage.class);
        
        PatientLinkCenterAd ad = find.centerAd;
        testCenterAd(ad);
    }
    
    public void testRrAd(PatientLinkRrAd ad) {
        
        for (int i=0; i<ad.getSize(); i++) {
        
            PatientLinkFeatures.setExpected(ad.getName(i));
            
            Assert.assertEquals(ad.getSpecialty(i), PatientLinkFeatures.getExpectedSpecialty(),
                    "Featured specialty for " + ad.getName(i) + " did not match");
            
            Assert.assertEquals(ad.getAddress(i), PatientLinkFeatures.getExpectedAddress(),
                    "Address for " + ad.getName(i) + " did not match");
            
            Assert.assertEquals(ad.getCity(i), PatientLinkFeatures.getExpectedCity(),
                    "City for " + ad.getName(i) + " did not match");
            
            Assert.assertEquals(ad.getState(i), PatientLinkFeatures.getExpectedState(),
                    "State for " + ad.getName(i) + " did not match");
            
            Assert.assertEquals(ad.getZip(i), PatientLinkFeatures.getExpectedZip(),
                    "Zip for " + ad.getName(i) + " did not match");
            
            if (ad.isBookPresent(i)) {
                PatientLinkBookModal modal = ad.clickBook(i);
                modal.typeFirstName("test");
                modal.typeLastName("test_last");
                modal.selectRadioAfternoon();
                modal.selectDrop1Week();
                modal.submit();
                modal.close();
            }
            
            if (ad.isCallPresent(i)) {    
                Assert.assertEquals(ad.getPhoneNumber(),PatientLinkFeatures.getExpectedNumber(),
                        "Phone number for " + ad.getName(i) + " did not match");
            }
        }
    }
    
    public void testCenterAd(PatientLinkCenterAd ad) {
        
        PatientLinkFeatures.setExpected(ad.getName());
            
        Assert.assertEquals(ad.getSpecialty(), PatientLinkFeatures.getExpectedSpecialty(),
                "Featured specialty for " + ad.getName() + " did not match");
            
        Assert.assertEquals(ad.getAddress(), PatientLinkFeatures.getExpectedAddress(),
                "Address for " + ad.getName() + " did not match");
            
        Assert.assertEquals(ad.getCity(), PatientLinkFeatures.getExpectedCity(),
                "City for " + ad.getName() + " did not match");
            
        Assert.assertEquals(ad.getState(), PatientLinkFeatures.getExpectedState(),
                "State for " + ad.getName() + " did not match");
            
        Assert.assertEquals(ad.getZip(), PatientLinkFeatures.getExpectedZip(),
                "Zip for " + ad.getName() + " did not match");
            
        if (ad.isBookPresent()) {
            PatientLinkBookModal modal = ad.clickBook();
            modal.typeFirstName("test_first");
            modal.typeLastName("test_last");
            modal.selectRadioAfternoon();
            modal.selectDrop1Week();
            modal.submit();
            modal.close();
        }

        if (ad.isCallPresent()) {
            ad.clickCall();
                
            Assert.assertEquals(ad.getPhoneNumber(), PatientLinkFeatures.getExpectedNumber(),
                    "Phone number for " + ad.getName() + " did not match");
        }
    }
}
