package com.vitals.test;

import com.vitals.runners.RemoteTestRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.vitals.pages.ProfilePage;
import com.vitals.pages.SEOProfilePage;

public class ProfileTest extends RemoteTestRunner {

    WebDriver driver;

    static final String seoDrProfile = "/doctors/Dr_John_Nemunaitis.html?basic=1";
    static final String seoDentistProfile = "/dentists/Dr_Grace_Smart?basic=1";
    static final String drProfile = "/doctors/Dr_John_Nemunaitis/profile";
    static final String dentistProfile = "/dentists/Dr_Grace_Smart/profile";
    static final String drVideoProfile = "/doctors/Dr_John_Nemunaitis/video";

    @Parameters({"domain"})
    @Test
    public void doctorSeoProfileTest(String domain) {
        driver = getDriver();

        driver.get(getUrl(domain) + seoDrProfile);

        SEOProfilePage seoProfile = PageFactory.initElements(driver, SEOProfilePage.class);

        Assert.assertTrue(seoProfile.viewFullProfileButtonIsVisible(),
                "SEO Doctor Page did not load successfully" + seoProfile);
    }

    @Parameters({"domain"})
    @Test
    public void dentistSeoProfileTest(String domain) {
        driver = getDriver();

        driver.get(getUrl(domain) + seoDentistProfile);

        SEOProfilePage seoProfile = PageFactory.initElements(driver, SEOProfilePage.class);

        Assert.assertTrue(seoProfile.viewFullProfileButtonIsVisible(),
                "SEO Dentist Page did not load successfully: " + seoDentistProfile);
    }

    @Parameters({"domain"})
    @Test
    public void viewFullProfileSummaryTest(String domain) {
        driver = getDriver();

        driver.get(getUrl(domain));

        driver.get(getUrl(domain) + drProfile);

        ProfilePage profilePage = PageFactory.initElements(driver, ProfilePage.class);

        Assert.assertTrue(profilePage.isSummaryPage(),
                "Summary page was not visible: " + driver.getCurrentUrl());
    }

    @Parameters({"domain"})
    @Test
    public void viewFullProfileReviewsTest(String domain) {
        driver = getDriver();

        driver.get(getUrl(domain));

        driver.get(getUrl(domain) + drProfile);

        ProfilePage profilePage = PageFactory.initElements(driver, ProfilePage.class);

        profilePage.clickPatientReviewsTab();

        Assert.assertTrue(profilePage.isPatientReviewsPage(),
                "Patient Reviews page was not visible: " + driver.getCurrentUrl());
    }

    @Parameters({"domain"})
    @Test
    public void viewFullProfileCredentialsTest(String domain) {
        driver = getDriver();

        driver.get(getUrl(domain));

        driver.get(getUrl(domain) + drProfile);

        ProfilePage profilePage = PageFactory.initElements(driver, ProfilePage.class);

        profilePage.clickCredentials();

        Assert.assertTrue(profilePage.isCredentialsPage(),"Credentials page was not visible: " + driver.getCurrentUrl());

    }

    @Parameters({"domain"})
    @Test
    public void viewLocationsAvailabilityTest(String domain) {
        driver = getDriver();

        driver.get(getUrl(domain));

        driver.get(getUrl(domain) + drProfile);

        ProfilePage profilePage = PageFactory.initElements(driver, ProfilePage.class);

        profilePage.clickLocationsAvailabilityTab();

        Assert.assertTrue(profilePage.isLocationsAvailabilityPage(),
                "Locations availability page was not visible: " + driver.getCurrentUrl());
    }

    @Parameters({"domain"})
    @Test
    public void viewAcceptedInsuranceTest(String domain) {
        driver = getDriver();

        driver.get(getUrl(domain));

        driver.get(getUrl(domain) + drProfile);

        ProfilePage profilePage = PageFactory.initElements(driver, ProfilePage.class);

        profilePage.clickAcceptedInsurance();

        Assert.assertTrue(profilePage.isAcceptedInsurancePage(),
                "Accepted insurance page was not visible: " + driver.getCurrentUrl());
    }

    @Parameters({"domain"})
    @Test
    public void viewVideoProfileTest(String domain) {
        driver = getDriver();

        driver.get(getUrl(domain));

        driver.get(getUrl(domain) + drVideoProfile);

        ProfilePage profilePage = PageFactory.initElements(driver, ProfilePage.class);

        Assert.assertTrue(profilePage.drVideoIsVisible(), "Dr Video is not visible: " + driver.getCurrentUrl());
    }

    @Parameters({"domain"})
    @Test
    public void redirectToSeoProfileTest(String domain) {
        driver = getDriver();

        driver.get(getUrl(domain) + drProfile);

        SEOProfilePage seoProfile = PageFactory.initElements(driver, SEOProfilePage.class);

        Assert.assertTrue(seoProfile.viewFullProfileButtonIsVisible(),
                "SEO Profile page did not load: " + driver.getCurrentUrl());

    }

    @Parameters({"domain"})
    @Test
    public void seoToFullProfileTest(String domain) {
        driver = getDriver();

        driver.get(getUrl(domain) + drProfile);

        SEOProfilePage seoProfile = PageFactory.initElements(driver, SEOProfilePage.class);

        Assert.assertTrue(seoProfile.viewFullProfileButtonIsVisible(),
                "SEO Profile page did not load: " + driver.getCurrentUrl());

        driver.get(getUrl(domain) + drProfile);

        ProfilePage fullProfile = PageFactory.initElements(driver, ProfilePage.class);

        Assert.assertTrue(fullProfile.isSummaryPage(),
                "Profile page did not load: " + driver.getCurrentUrl());

    }

}