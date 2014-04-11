package com.uchc.test;

import com.uchc.pages.*;
import com.uchc.pages.patientlink.*;
import com.vitalsqa.testrail.TestCase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.uchc.helpers.PatientLinkSetFeatures;
import java.util.ArrayList;
import java.util.List;

public class PatientLinkTest {
    private SoftAssert m_assert;
    private boolean alreadyInit = false;
    private String url;
    private static String serpUrl = "/drs/new_york/internal_medicine/New_York.html";
    private static String profileUrl = "/drs/carrie_aaron/";
    private static String profileHeaderUrl = "/drs/adelle_quintana";

    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) {
        this.url = url;
    }

    @TestCase(id=1832)
    @Test
    public void checkProfileHeader() {

        ProfileCommonPage profile = new ProfileCommonPage();
        profile.get(url + profileHeaderUrl);

        m_assert = new SoftAssert();
        m_assert.assertTrue(profile.hasPlDrSite(), "Doctor's site is missing!");
        if (profile.hasPlDrSite()) {
            m_assert.assertEquals(profile.getSiteUrl(), "http://www.laserandmohs.com", "Doctor's site url is wrong!");
        }
        m_assert.assertTrue(profile.hasPlPhoneNumber(), "Phone number is missing!");
        if (profile.hasPlPhoneNumber()) {
            m_assert.assertTrue(profile.plPhoneNumber().getText().toString().contains("(646) 499-2346") , "Phone number is incorrect!");
        }
        m_assert.assertTrue(profile.hasPlBookAppt(), "Book online button is missing!");
        m_assert.assertAll();
    }

    @TestCase(id=1833)
    @Test
    public void checkProfile() {

        ProfileCommonPage profile = new ProfileCommonPage();
        profile.get(url + profileUrl);

        testIndividualAd(profile.rrAd());
    }

    @TestCase(id=1834)
    @Test
    public void checkSerp() {

        SearchResultsPage serp = new SearchResultsPage();
        serp.get(url + serpUrl);

        testIndividualAd(serp.centerAd());
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
        PatientLinkBookForm form = new PatientLinkBookForm();
        List<String> apptUrl = new ArrayList<String>();

        for (int i=0; i<ad.getSize(); i++) {

            pl.resetMatched();
            pl.setExpected(ad.name().get(i).getText().toString());

            Assert.assertTrue(pl.isMatched(), ad.name().get(i).getText().toString() + " is not in property file.");

            m_assert.assertEquals(ad.address1().get(i), pl.getExpectedAddress(),
                    "Address Line 1 for " + ad.name().get(i).getText().toString() + " did not match");

            if (ad.hasAddress2(i)) {
                m_assert.assertEquals(ad.address2().get(i), pl.getExpectedAddressLine2(),
                        "Address Line 2 for " + ad.name().get(i).getText().toString() + " did not match");
            }
            else {
                m_assert.assertTrue(pl.getExpectedAddressLine2().equals(""), "Address Line 2 for " + ad.name().get(i) + " is missing");
            }

            m_assert.assertEquals(ad.city().get(i), pl.getExpectedCity(),
                    "City for " + ad.name().get(i).getText().toString() + " did not match");

            m_assert.assertEquals(ad.state().get(i), pl.getExpectedState(),
                    "State for " + ad.name().get(i).getText().toString() + " did not match");

            m_assert.assertEquals(ad.zip().get(i), pl.getExpectedZip(),
                    "Zip for " + ad.name().get(i).getText().toString() + " did not match");

            if (ad.phoneNumber().get(i) != null) {
                m_assert.assertEquals(ad.phoneNumber().get(i).getText().toString(), pl.getExpectedNumberUchc(),
                        "Phone number for " + ad.name().get(i).getText().toString() + " did not match");
            }
            else {
                m_assert.assertTrue(pl.getExpectedNumberUchc().equals(""), "Phone number is empty for " + ad.name().get(i).getText().toString());
            }

            if (pl.hasBookOnline()) {
                m_assert.assertTrue(ad.bookButton()!=null, "Book Online button is not displayed for " + ad.name().get(i).getText().toString());
                if (pl.getBookType()==1) {
                    apptUrl.add(ad.bookButton().get(i).getAttribute("href").toString());
                }
            }

            if (pl.hasLogo()) {
                m_assert.assertTrue(ad.logo().get(i).isDisplayed().value(), "Logo is not displayed for " + ad.name().get(i).getText().toString());
            }
        }

        for (String appt : apptUrl) {
            form.get(appt);
            form.fname().clearField().sendKeys("first name");
            form.lname().clearField().sendKeys("last name");
        }

        m_assert.assertAll();
    }

    @TestCase(id=1836)
    @Test
    public void docAsapIframe() {

        m_assert = new SoftAssert();

        ProfileCommonPage profile = new ProfileCommonPage();
        profile.get(url + "/drs/siby_cherian");
        Assert.assertTrue(profile.hasBookOnline(), "Book Online button is not present");
        profile.plBookAppt().click();

        profile.switchIframe("iframe[src*='docasap']");

        IframeDocAsap iframe = new IframeDocAsap();
        m_assert.assertTrue(iframe.name().getText().toString().equals("Siby Cherian, MD"), "Incorrect name");
        iframe.nextButton().click();
        m_assert.assertTrue(iframe.hasSlots(), "No time slots");

        m_assert.assertAll();
    }

    @TestCase(id=1837)
    @Test
    public void healthPostIframe() {

        m_assert = new SoftAssert();

        ProfileCommonPage profile = new ProfileCommonPage();
        profile.get(url + "/drs/victoria_adeleye");
        Assert.assertTrue(profile.hasBookOnline(), "Book Online button is not present");
        profile.plBookAppt().click();

        profile.switchIframe("iframe[src*='healthpost']");

        IframeHealthPost iframe = new IframeHealthPost();
        iframe.nextButton().click();
        m_assert.assertTrue(iframe.hasSlots(), "No time slots");

        m_assert.assertAll();
    }

    @TestCase(id=1838)
    @Test
    public void drChronoIframe() {

        m_assert = new SoftAssert();

        ProfileCommonPage profile = new ProfileCommonPage();
        profile.get(url + "/drs/matthew_krasucki");
        Assert.assertTrue(profile.hasBookOnline(), "Book Online button is not present");
        profile.plBookAppt().click();

        profile.switchIframe("iframe[src*='chrono']");

        IframeDrChrono iframe = new IframeDrChrono();
        m_assert.assertTrue(iframe.name().getText().toString().equals("Dr. Matthew D. Krasucki M.D."), "Incorrect name");
        iframe.nextButton().click();
        m_assert.assertTrue(iframe.hasSlots(), "No time slots");

        m_assert.assertAll();
    }
}
