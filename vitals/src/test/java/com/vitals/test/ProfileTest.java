package com.vitals.test;

import com.vitals.pages.ProfileSeoPage;
import com.vitalsqa.testrail.TestCase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.vitals.pages.ProfilePage;

public class ProfileTest {
    
    static final String drProfile = "/doctors/Dr_John_Nemunaitis/profile";
    static final String dentistProfile = "/dentists/Dr_Grace_Smart/profile";
    static final String drVideoProfile = "/doctors/Dr_John_Nemunaitis/video";
    
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
        seoProfile.deleteCookies();;
        seoProfile.get(url + drProfile);

        Assert.assertTrue(seoProfile.viewFullProfileButton().isDisplayed().value(),
                "SEO Doctor Page did not load successfully" + drProfile);

        seoProfile.get(url + drProfile);
        ProfilePage fullProfile = new ProfilePage();
        Assert.assertTrue(fullProfile.isSummaryPage(),
                "Profile page did not load: " + fullProfile.getCurrentUrl());
    }

    @TestCase(id=1455)
    @Test
    public void dentistSeoProfileTest() {
        ProfileSeoPage seoProfile = new ProfileSeoPage();
        seoProfile.deleteCookies();
        seoProfile.get(url + dentistProfile);

        Assert.assertTrue(seoProfile.viewFullProfileButton().isDisplayed().value(),
                "SEO Dentist Page did not load successfully: " + dentistProfile);

        seoProfile.get(url + dentistProfile);
        ProfilePage fullProfile = new ProfilePage();
        Assert.assertTrue(fullProfile.isSummaryPage(),
                "Profile page did not load: " + fullProfile.getCurrentUrl());
    }

    @TestCase(id=1456)
    @Test
    public void viewFullProfileSummaryTest() {
        ProfilePage profilePage = new ProfilePage();
        profilePage.get(url);
        profilePage.get(url + drProfile);

        Assert.assertTrue(profilePage.isSummaryPage(),
                "Summary page was not visible: " + profilePage.getCurrentUrl());
    }

    @TestCase(id=1457)
    @Test
    public void viewFullProfileReviewsTest() {
        ProfilePage profilePage = new ProfilePage();
        profilePage.get(url);
        profilePage.get(url+ drProfile);
        profilePage.reviewsTab().click();

        Assert.assertTrue(profilePage.isPatientReviewsPage(),
                "Patient Reviews page was not visible: " + profilePage.getCurrentUrl());
    }

    @TestCase(id=1633)
    @Test
    public void viewFullProfileCredentialsTest() {
    	ProfilePage profilePage = new ProfilePage();
        profilePage.get(url);
        profilePage.get(url + drProfile);
        profilePage.credentialsTab().click();

        Assert.assertTrue(profilePage.isCredentialsPage(),
                "Credentials page was not visible: " + profilePage.getCurrentUrl());

    }

    @TestCase(id=1458)
    @Test
    public void viewFullProfileLocationsTest() {
        ProfilePage profilePage = new ProfilePage();
        profilePage.get(url);
        profilePage.get(url + drProfile);
        profilePage.locationsTab().click();

        Assert.assertTrue(profilePage.isLocationsAvailabilityPage(),
                "Locations availability page was not visible: " + profilePage.getCurrentUrl());
    }

    @TestCase(id=1459)
    @Test
    public void viewFullProfileInsurancesTest() {
        ProfilePage profilePage = new ProfilePage();
        profilePage.get(url);
        profilePage.get(url + drProfile);
        profilePage.insurancesTab().click();

        Assert.assertTrue(profilePage.isAcceptedInsurancePage(),
                "Accepted insurance page was not visible: " + profilePage.getCurrentUrl());
    }

    @TestCase(id=1460)
    @Test
    public void viewVideoProfileTest() {
        ProfilePage profilePage = new ProfilePage();
        profilePage.get(url);
        profilePage.get(url + drVideoProfile);

        Assert.assertTrue(profilePage.drVideoLink().isDisplayed().value(),
                "Dr Video is not visible: " + profilePage.getCurrentUrl());
    }
}