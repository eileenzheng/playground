package com.vitals.test;

import com.vitals.helpers.PatientLinkSetFeatures;
import com.vitals.pages.profile.ProfileCommonPage;
import com.vitals.pages.profile.ProfileSeoPage;
import com.vitals.pages.SearchResultsPage;
import com.vitals.pages.patientlink.*;
import com.vitals.pages.ucc.UccSearchResultsPage;
import com.vitalsqa.testrail.TestCase;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PatientLinkTest {

    private SoftAssert m_assert;
    private boolean alreadyInit = false;
    private String url;
    
    private static final String serpUrl = "/dermatologists/ny/new-york";
    private static final String dentistSerpUrl = "/dentists/fl/orlando";
    private static final String profileUrl = "/doctors/Dr_Donald_Belsito/profile";
    private static final String dentistProfileUrl = "/dentists/Dr_James_Flatley/profile";
    private static final String profileHeaderUrl = "/doctors/Dr_Adelle_Quintana/profile";
    private static final String dentistProfileHeaderUrl = "/dentists/Dr_Mark_Falco/profile";
    private static final String uccUrl = "/urgent-care/ct/hartford";
    
    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) throws Exception {


        this.url = url;
    }

    @TestCase(id=1553)
    @Test
    public void checkProfileHeader() {

        ProfileCommonPage profile = new ProfileCommonPage();
        profile.get(url);
        // launch again to go to profile instead of seo profile
        profile.get(url + profileHeaderUrl);
        profile.dismissReviewIntercept();

        m_assert = new SoftAssert();
        m_assert.assertTrue(profile.hasPlDrSite(), "Doctor's site is missing!");
        if (profile.hasPlDrSite()) {
        	m_assert.assertEquals(profile.getSiteUrl(), "http://www.laserandmohs.com", "Doctor's site url is wrong!");
        }
        m_assert.assertTrue(profile.hasPlPhoneNumber(), "Phone number is missing!");
        if (profile.hasPlPhoneNumber()) {
        	m_assert.assertTrue(profile.plPhoneNumber().getText().toString().contains("(646) 499-2330") , "Phone number is incorrect!");
        }
        m_assert.assertTrue(profile.hasPlBookAppt(), "Book online button is missing!");

        m_assert.assertTrue(profile.getPageSource().contains("c|5||"), "Click tracking missing");
        m_assert.assertTrue(profile.getPageSource().contains("d|5||"), "Display tracking missing");

        m_assert.assertAll();
    }

    @TestCase(id=2112)
    @Test
    public void checkDentistProfileHeader() {

        ProfileCommonPage profile = new ProfileCommonPage();
        profile.get(url);
        // launch again to go to profile instead of seo profile
        profile.get(url + dentistProfileHeaderUrl);
        profile.dismissReviewIntercept();

        m_assert = new SoftAssert();
        m_assert.assertTrue(profile.hasPlPhoneNumber(), "Phone number is missing!");
        if (profile.hasPlPhoneNumber()) {
            m_assert.assertTrue(profile.plPhoneNumber().getText().toString().contains("(407) 307-3243") , "Phone number is incorrect!");
        }
        m_assert.assertTrue(profile.hasPlBookAppt(), "Book online button is missing!");

        m_assert.assertTrue(profile.getPageSource().contains("c|5||"), "Click tracking missing");
        m_assert.assertTrue(profile.getPageSource().contains("d|5||"), "Display tracking missing");

        m_assert.assertAll();
    }

    @TestCase(id={1554,1558})
    @Test
    public void checkSeoProfile() {

        ProfileSeoPage profile = new ProfileSeoPage();
        profile.deleteCookies();
        profile.get(url + dentistProfileUrl);
        profile.dismissReviewIntercept();
        Assert.assertTrue(profile.rrAd().getSize()>0, "No CMT ads on profile page");
        testIndividualAd(profile.rrAd());
    }

    @TestCase(id={1555,1558})
    @Test
    public void checkProfile() {

        ProfileCommonPage profile = new ProfileCommonPage();
        profile.get(url);
        profile.get(url + profileUrl);
        profile.dismissReviewIntercept();
        Assert.assertTrue(profile.rrAd().getSize()>0, "No CMT ads on profile page");
        testIndividualAd(profile.rrAd());
    }

    @TestCase(id={1556,1558})
    @Test
    public void checkSerp() {

        SearchResultsPage serp = new SearchResultsPage();
        serp.get(url + serpUrl);

        m_assert = new SoftAssert();
        m_assert.assertTrue(serp.centerAd().getSize()>0, "No CMT ads on provider SERP");
        m_assert.assertTrue(serp.getPageSource().contains("c|3||"), "Click tracking missing");
        m_assert.assertTrue(serp.getPageSource().contains("d|3||"), "Display tracking missing");
        m_assert.assertAll();

        testIndividualAd(serp.centerAd());
    }

    @TestCase(id={2113,1558})
    @Test
    public void checkDentistSerp() {

        SearchResultsPage serp = new SearchResultsPage();
        serp.get(url + dentistSerpUrl);

        m_assert = new SoftAssert();
        m_assert.assertTrue(serp.centerAd().getSize()>0, "No CMT ads on dentist SERP");
        m_assert.assertTrue(serp.getPageSource().contains("c|3||"), "Click tracking missing");
        m_assert.assertTrue(serp.getPageSource().contains("d|3||"), "Display tracking missing");
        m_assert.assertAll();

        testIndividualAd(serp.centerAd());
    }

    @TestCase(id={1557,1558})
    @Test
    public void checkUccSerp() {

        UccSearchResultsPage ucc = new UccSearchResultsPage();
        ucc.get(url + uccUrl);

        m_assert = new SoftAssert();
        m_assert.assertTrue(ucc.centerAd().getSize()>0, "No CMT ads on UCC SERP");
        m_assert.assertTrue(ucc.getPageSource().contains("c|3||"), "Click tracking missing");
        m_assert.assertTrue(ucc.getPageSource().contains("d|3||"), "Display tracking missing");
        m_assert.assertAll();

        testIndividualAd(ucc.centerAd());
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
    	ModalEmail modal = new ModalEmail();

        for (int i=0; i<ad.getSize(); i++) {

        	pl.resetMatched();
            pl.setExpected(ad.name().get(i).getText().toString());

            if (!pl.isMatched()) {
                Reporter.log(ad.name().get(i).getText().toString() + " is not in property file.");
                continue;
            }

            m_assert.assertEquals(ad.specialty().get(i).getText().toString(), pl.getExpectedSpecialty(),
                    "Featured specialty for " + ad.specialty().get(i).getText().toString() + " did not match");

            m_assert.assertEquals(ad.address1().get(i).getText().toString(), pl.getExpectedAddress(),
                    "Address Line 1 for " + ad.name().get(i).getText().toString() + " did not match");

            if (ad.hasAddress2(i)) {
                m_assert.assertEquals(ad.address2().get(i).getText().toString(), pl.getExpectedAddressLine2(),
                        "Address Line 2 for " + ad.name().get(i).getText().toString() + " did not match");
            }
            else {
            	m_assert.assertTrue(pl.getExpectedAddressLine2().equals(""), "Address Line 2 for " + ad.name().get(i) + " is missing");
            }

            m_assert.assertEquals(ad.city().get(i).getText().toString(), pl.getExpectedCity(),
                    "City for " + ad.name().get(i).getText().toString() + " did not match");

            m_assert.assertEquals(ad.state().get(i).getText().toString(), pl.getExpectedState(),
                    "State for " + ad.name().get(i).getText().toString() + " did not match");

            m_assert.assertEquals(ad.zip().get(i).getText().toString(), pl.getExpectedZip(),
                    "Zip for " + ad.name().get(i).getText().toString() + " did not match");

            if (ad.phoneNumber().get(i) != null) {
				m_assert.assertEquals(ad.phoneNumber().get(i).getText(), pl.getExpectedNumber(),
						"Phone number for " + ad.name().get(i).getText().toString() + " did not match");
			}
            else {
            	m_assert.assertTrue(pl.getExpectedNumber().equals(""), "Phone number is empty for " + ad.name().get(i).getText().toString());
            }

            if (pl.hasBookOnline()) {
            	m_assert.assertTrue(ad.bookButton()!=null, "Book Online button is not displayed for " + ad.name().get(i).getText().toString());
            	if (pl.getBookType()==1) {
                    ad.bookButton().get(i).click();
                    modal.fname().clearField().sendKeys("test_first");
                    modal.lname().clearField().sendKeys("test_last");
                    modal.radioAfternoon().click();
                    modal.selectDropDown(modal.dropDownWhen(), "ASAP");
            		modal.closeButton().click();
            	}
            	else if (pl.getBookType()==2) {
            	    ad.bookButton().get(i).click();
            		modal.closeButton().click();
            	}
            }

            if (pl.hasLogo()) {
            	m_assert.assertTrue(ad.logo().get(i).isDisplayed().value(), "Logo is not displayed for " + ad.name().get(i).getText().toString());
            }
        }

        m_assert.assertAll();
    }

    @TestCase(id=1757)
    @Test
    public void docAsapIframe() {

        m_assert = new SoftAssert();

        ProfileCommonPage profile = new ProfileCommonPage();
        profile.get(url + "/doctors/Dr_Saka_Kazeem/profile");
        profile.dismissReviewIntercept();
        profile.plBookAppt().click();

        ModalIframe modal = new ModalIframe();
        String mainWindow = modal.getMainWindow();
        modal.switchIframe("iframe[src*='docasap']");

        IframeDocAsap iframe = new IframeDocAsap();
        m_assert.assertTrue(iframe.name().getText().toString().equals("Saka Kazeem, MD"), "Incorrect name");
        iframe.nextButton().click();
        m_assert.assertTrue(iframe.hasSlots(), "No time slots");

        modal.switchWindow(mainWindow);
        modal.closeButton().click();

        m_assert.assertAll();
    }

    @TestCase(id=1758)
    @Test
    public void healthPostIframe() {

        m_assert = new SoftAssert();

        ProfileCommonPage profile = new ProfileCommonPage();
        profile.get(url + "/doctors/Dr_Victoria_Adeleye/profile");
        profile.dismissReviewIntercept();
        profile.plBookAppt().click();

        ModalIframe modal = new ModalIframe();
        String mainWindow = modal.getMainWindow();
        modal.switchIframe("iframe[src*='healthpost']");

        IframeHealthPost iframe = new IframeHealthPost();
        iframe.nextButton().click();
        m_assert.assertTrue(iframe.hasSlots(), "No time slots");

        modal.switchWindow(mainWindow);
        modal.closeButton().click();

        m_assert.assertAll();
    }

    @TestCase(id=1759)
    @Test
    public void drChronoIframe() {

        m_assert = new SoftAssert();

        ProfileCommonPage profile = new ProfileCommonPage();
        profile.get(url + "/doctors/Dr_Matthew_Krasucki/profile");
        profile.dismissReviewIntercept();
        profile.plBookAppt().click();

        ModalIframe modal = new ModalIframe();
        String mainWindow = modal.getMainWindow();
        modal.switchIframe("iframe[src*='chrono']");

        IframeDrChrono iframe = new IframeDrChrono();
        m_assert.assertTrue(iframe.name().getText().toString().equals("Dr. Matthew D. Krasucki M.D."), "Incorrect name");
        iframe.nextButton().click();
        m_assert.assertTrue(iframe.hasSlots(), "No time slots");

        modal.switchWindow(mainWindow);
        modal.closeButton().click();

        m_assert.assertAll();
    }

    @TestCase(id=1871)
    @Test
    public void bookThatDocIframe() {

        m_assert = new SoftAssert();

        ProfileCommonPage profile = new ProfileCommonPage();
        profile.get(url + "/dentists/Dr_Mark_Falco/profile");
        profile.dismissReviewIntercept();
        profile.plBookAppt().click();

        ModalIframe modal = new ModalIframe();
        String mainWindow = modal.getMainWindow();
        modal.switchIframe("iframe[src*='bookthatdoc']");

        IframeBookThatDoc iframe = new IframeBookThatDoc();
        m_assert.assertTrue(iframe.name().getText().toString().equals("Dr. Mark Falco"), "Incorrect name");
        iframe.nextButton().click();
        m_assert.assertTrue(iframe.hasSlots(), "No time slots");

        modal.switchWindow(mainWindow);
        modal.closeButton().click();

        m_assert.assertAll();
    }

    @TestCase(id=2111)
    @Test
    public void greenvilleIframe() {

        m_assert = new SoftAssert();

        ProfileCommonPage profile = new ProfileCommonPage();
        profile.get(url + "/doctors/Dr_Molly_Adams/profile");
        profile.dismissReviewIntercept();
        profile.plBookAppt().click();

        ModalIframe modal = new ModalIframe();
        String mainWindow = modal.getMainWindow();
        modal.switchIframe("iframe[src*='ghswebdev.com']");

        IframeGreenville iframe = new IframeGreenville();
        m_assert.assertTrue(iframe.name().getText().toString().contains("Molly C. Adams, MD"), "Incorrect name");

        modal.switchWindow(mainWindow);
        modal.closeButton().click();

        m_assert.assertAll();
    }

    @TestCase(id=2447)
    @Test
    public void doctorDotComIframe() {

        m_assert = new SoftAssert();

        ProfileCommonPage profile = new ProfileCommonPage();
        profile.get(url + "/doctors/Dr_Tina_Beck/profile");
        profile.dismissReviewIntercept();
        profile.plBookAppt().click();

        ModalIframe modal = new ModalIframe();
        String mainWindow = modal.getMainWindow();
        modal.switchIframe("iframe[src*='patients.doctor.com']");

        IframeDoctorDotCom iframe = new IframeDoctorDotCom();
        m_assert.assertTrue(iframe.name().getText().toString().contains("Dr Tina Beck"), "Incorrect name");

        modal.switchWindow(mainWindow);
        modal.closeButton().click();

        m_assert.assertAll();
    }
}