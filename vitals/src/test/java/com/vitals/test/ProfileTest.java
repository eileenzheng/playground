package com.vitals.test;

import com.vitals.pages.profile.ProfileCommonPage;
import com.vitals.pages.profile.ProfileSeoPage;
import com.vitalsqa.testrail.TestCase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ProfileTest {
    
    static final String drProfile = "/doctors/Dr_Emile_Bacha/profile";
    static final String dentistProfile = "/dentists/Dr_Grace_Smart/profile";
    static final String drVideoProfile = "/doctors/Dr_Emile_Bacha/video";
    
    String url;
    
    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) throws Exception {
        this.url = url;
    }

    @TestCase(id=1454)
    @Test
    public void doctorSeoProfileTest() {
        ProfileSeoPage seoProfile = new ProfileSeoPage();
        seoProfile.deleteCookies();
        seoProfile.get(url + drProfile);
        seoProfile.dismissReviewIntercept();

        Assert.assertTrue(seoProfile.viewFullProfileLink().isDisplayed().value(),
                "SEO Doctor Page did not load successfully" + drProfile);

        seoProfile.get(url + drProfile);
        ProfileCommonPage fullProfile = new ProfileCommonPage();
        fullProfile.dismissReviewIntercept();
        Assert.assertTrue(fullProfile.isSummaryPage(),
                "Profile page did not load: " + fullProfile.getCurrentUrl());
    }

    @TestCase(id=1455)
    @Test
    public void dentistSeoProfileTest() {
        ProfileSeoPage seoProfile = new ProfileSeoPage();
        seoProfile.deleteCookies();
        seoProfile.get(url + dentistProfile);
        seoProfile.dismissReviewIntercept();

        Assert.assertTrue(seoProfile.viewFullProfileLink().isDisplayed().value(),
                "SEO Dentist Page did not load successfully: " + dentistProfile);

        seoProfile.get(url + dentistProfile);
        ProfileCommonPage fullProfile = new ProfileCommonPage();
        Assert.assertTrue(fullProfile.isSummaryPage(),
                "Profile page did not load: " + fullProfile.getCurrentUrl());
    }

    @TestCase(id=1456)
    @Test
    public void viewFullProfileSummaryTest() {
        ProfileCommonPage profilePage = new ProfileCommonPage();
        profilePage.get(url);
        profilePage.get(url + drProfile);
        profilePage.dismissReviewIntercept();

        Assert.assertTrue(profilePage.isSummaryPage(),
                "Summary page was not visible: " + profilePage.getCurrentUrl());
    }

    @TestCase(id=1457)
    @Test
    public void viewFullProfileReviewsTest() {
        ProfileCommonPage profilePage = new ProfileCommonPage();
        profilePage.get(url);
        profilePage.get(url+ drProfile);
        profilePage.dismissReviewIntercept();
        profilePage.reviewsTab().click();
        profilePage.dismissReviewIntercept();

        Assert.assertTrue(profilePage.isPatientReviewsPage(),
                "Patient Reviews page was not visible: " + profilePage.getCurrentUrl());
    }

    @TestCase(id=1633)
    @Test
    public void viewFullProfileCredentialsTest() {
    	ProfileCommonPage profilePage = new ProfileCommonPage();
        profilePage.get(url);
        profilePage.get(url + drProfile);
        profilePage.dismissReviewIntercept();
        profilePage.credentialsTab().click();

        Assert.assertTrue(profilePage.isCredentialsPage(),
                "Credentials page was not visible: " + profilePage.getCurrentUrl());

    }

    @TestCase(id=1458)
    @Test
    public void viewFullProfileLocationsTest() {
        ProfileCommonPage profilePage = new ProfileCommonPage();
        profilePage.get(url);
        profilePage.get(url + drProfile);
        profilePage.dismissReviewIntercept();
        profilePage.locationsTab().click();

        Assert.assertTrue(profilePage.isLocationsAvailabilityPage(),
                "Locations availability page was not visible: " + profilePage.getCurrentUrl());
    }

    @TestCase(id=1459)
    @Test
    public void viewFullProfileInsurancesTest() {
        ProfileCommonPage profilePage = new ProfileCommonPage();
        profilePage.get(url);
        profilePage.get(url + drProfile);
        profilePage.dismissReviewIntercept();
        profilePage.insurancesTab().click();

        Assert.assertTrue(profilePage.isAcceptedInsurancePage(),
                "Accepted insurance page was not visible: " + profilePage.getCurrentUrl());
    }

    @TestCase(id=1460)
    @Test
    public void viewVideoProfileTest() {
        ProfileCommonPage profilePage = new ProfileCommonPage();
        profilePage.get(url);
        profilePage.get(url + drVideoProfile);
        profilePage.dismissReviewIntercept();

        Assert.assertTrue(profilePage.drVideoLink().isDisplayed().value(),
                "Dr Video is not visible: " + profilePage.getCurrentUrl());
    }
}