package com.vitals.test;

import com.vitals.DriverManager;
import com.vitals.TestCase;
import com.vitals.helpers.PatientLinkSetFeatures;
import com.vitals.pages.ProfileSeoPage;
import com.vitals.pages.SearchResultsPage;
import com.vitals.pages.patientlink.*;
import com.vitals.pages.ucc.UccSearchResultsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.vitals.pages.ProfilePage;

public class PatientLinkTest {
	
    private WebDriver driver;
    private SoftAssert m_assert;
    private boolean alreadyInit = false;
    private String url;
    
    private static final String serpUrl = "/internists/ny/new-york";
    private static final String profileUrl = "/doctors/Dr_Donald_Belsito/profile";
    private static final String profileHeaderUrl = "/doctors/Dr_Adelle_Quintana/profile";
    private static final String uccUrl = "/urgent-care/ny/yonkers";
    
    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) throws Exception {
        this.url = url;
    }
    
    @TestCase(id=1553)
    @Test
    public void checkProfileHeader() {
    	driver = DriverManager.getDriver();

        driver.get(url);
        // launch again to go to profile instead of seo profile
        driver.get(url + profileHeaderUrl);
        ProfilePage profile = PageFactory.initElements(driver, ProfilePage.class);

        m_assert = new SoftAssert();
        m_assert.assertTrue(profile.isDrSitePresent(), "Doctor's site is missing!");
        if (profile.isDrSitePresent()) {
        	m_assert.assertEquals(profile.getSiteUrl(), "http://www.laserandmohs.com", "Doctor's site url is wrong!");
        }
        m_assert.assertTrue(profile.isPLPhoneNumberPresent(), "Phone number is missing!");
        if (profile.isPLPhoneNumberPresent()) {
        	m_assert.assertTrue(profile.getPLPhoneNumber().contains("(646) 499-2330") , "Phone number is incorrect!");
        }
        m_assert.assertTrue(profile.isBookApptPresent(), "Book online button is missing!");
        m_assert.assertAll();
    }

    @TestCase(id={1554,1558})
    @Test
    public void checkSeoProfile() {
    	driver = DriverManager.getDriver();

        driver.get(url + profileUrl);
        ProfileSeoPage profile = PageFactory.initElements(driver, ProfileSeoPage.class);

        PatientLinkRrAd ad = profile.rrAd;
        testIndividualAd(ad);
    }

    @TestCase(id={1555,1558})
    @Test
    public void checkProfile() {
    	driver = DriverManager.getDriver();

        driver.get(url);
        driver.get(url + profileUrl);
        ProfilePage profile = PageFactory.initElements(driver, ProfilePage.class);

        PatientLinkRrAd ad = profile.rrAd;
        testIndividualAd(ad);
    }

    @TestCase(id={1556,1558})
    @Test
    public void checkSerp() {
    	driver = DriverManager.getDriver();

        driver.get(url + serpUrl);
        SearchResultsPage serp = PageFactory.initElements(driver, SearchResultsPage.class);

        PatientLinkCenterAd ad = serp.centerAd;
        testIndividualAd(ad);
    }

    @TestCase(id={1557,1558})
    @Test
    public void checkUccSerp() {
    	driver = DriverManager.getDriver();

        driver.get(url+ uccUrl);
        UccSearchResultsPage ucc = PageFactory.initElements(driver, UccSearchResultsPage.class);

        PatientLinkCenterAd ad = ucc.centerAd;
        testIndividualAd(ad);
    }

    // only need to call init function once for all the tests
    public void init() {
    	if (!alreadyInit) {
        	PatientLinkSetFeatures.init();
        	alreadyInit = true;
    	}
    }

    // loop through given list of ad and test the patient link features against expected
    public void testIndividualAd(PatientLinkAd ad) {

    	init();
    	m_assert = new SoftAssert();
    	PatientLinkSetFeatures pl = new PatientLinkSetFeatures();
    	ModalEmail modal;

        for (int i=0; i<ad.getSize(); i++) {

        	pl.resetMatched();
            pl.setExpected(ad.getName(i));

            Assert.assertTrue(pl.isMatched(), ad.getName(i) + " is not in property file.");

            m_assert.assertEquals(ad.getSpecialty(i), pl.getExpectedSpecialty(),
                    "Featured specialty for " + ad.getName(i) + " did not match");

            m_assert.assertEquals(ad.getAddressLine1(i), pl.getExpectedAddress(),
                    "Address Line 1 for " + ad.getName(i) + " did not match");

            if (ad.getAddressLine2(i) != null) {
                m_assert.assertEquals(ad.getAddressLine2(i), pl.getExpectedAddressLine2(),
                        "Address Line 2 for " + ad.getName(i) + " did not match");
            }
            else {
            	m_assert.assertTrue(pl.getExpectedAddressLine2().equals(""), "Address Line 2 for " + ad.getName(i) + " is missing");
            }

            m_assert.assertEquals(ad.getCity(i), pl.getExpectedCity(),
                    "City for " + ad.getName(i) + " did not match");

            m_assert.assertEquals(ad.getState(i), pl.getExpectedState(),
                    "State for " + ad.getName(i) + " did not match");

            m_assert.assertEquals(ad.getZip(i), pl.getExpectedZip(),
                    "Zip for " + ad.getName(i) + " did not match");

            if (ad.getPhoneNumber(i) != null) {
				m_assert.assertEquals(ad.getPhoneNumber(i), pl.getExpectedNumber(),
						"Phone number for " + ad.getName(i) + " did not match");
			}
            else {
            	m_assert.assertTrue(pl.getExpectedNumberUchc().equals(""), "Phone number is empty for " + ad.getName(i));
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

    @TestCase(id=1757)
    @Test
    public void docAsapIframe() {
        driver = DriverManager.getDriver();
        m_assert = new SoftAssert();

        driver.get(url + "/doctors/Dr_Siby_Cherian/profile");
        ProfilePage profile = PageFactory.initElements(driver, ProfilePage.class);
        profile.clickBookAppt();
        ModalIframe modal = PageFactory.initElements(driver, ModalIframe.class);

        modal.setMainWindow();
        modal.switchIframe("iframe[src*='docasap']");
        IframeDocAsap iframe = PageFactory.initElements(driver, IframeDocAsap.class);

        m_assert.assertTrue(iframe.isNameCorrect("Siby Cherian, MD"));
        iframe = iframe.clickNext();
        m_assert.assertTrue(iframe.isThereSlot());
        modal.switchBack();

        modal.close();

        m_assert.assertAll();
    }

    @TestCase(id=1758)
    @Test
    public void healthPostIframe() {
        driver = DriverManager.getDriver();

        driver.get(url + "/doctors/Dr_Victoria_Adeleye/profile");
        ProfilePage profile = PageFactory.initElements(driver, ProfilePage.class);
        profile.clickBookAppt();
        ModalIframe modal = PageFactory.initElements(driver, ModalIframe.class);

        modal.setMainWindow();
        modal.switchIframe("iframe[src*='healthpost']");
        IframeHealthPost iframe = PageFactory.initElements(driver, IframeHealthPost.class);

        iframe = iframe.clickNext();
        Assert.assertTrue(iframe.isThereSlot());
        modal.switchBack();

        modal.close();
    }

    @TestCase(id=1759)
    @Test
    public void drChronoIframe() {
        driver = DriverManager.getDriver();
        m_assert = new SoftAssert();

        driver.get(url + "/doctors/Dr_Matthew_Krasucki/profile");
        ProfilePage profile = PageFactory.initElements(driver, ProfilePage.class);
        profile.clickBookAppt();
        ModalIframe modal = PageFactory.initElements(driver, ModalIframe.class);

        modal.setMainWindow();
        modal.switchIframe("iframe[src*='chrono']");
        IframeDrChrono iframe = PageFactory.initElements(driver, IframeDrChrono.class);

        m_assert.assertTrue(iframe.isNameCorrect("Dr. Matthew D. Krasucki M.D."));
        iframe = iframe.clickNext();
        m_assert.assertTrue(iframe.isThereSlot());
        modal.switchBack();

        modal.close();

        m_assert.assertAll();
    }
}
