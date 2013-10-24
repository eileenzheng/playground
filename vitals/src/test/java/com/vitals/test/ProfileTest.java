package com.vitals.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.vitals.DriverManager;
import com.vitals.pages.ProfilePage;
import com.vitals.pages.SEOProfilePage;

public class ProfileTest {

    WebDriver driver;

    static final String seoDrProfile = "/doctors/Dr_John_Nemunaitis.html?basic=1";
    static final String seoDentistProfile = "/dentists/Dr_Grace_Smart?basic=1";
    static final String drProfile = "/doctors/Dr_John_Nemunaitis/profile";
    static final String dentistProfile = "/dentists/Dr_Grace_Smart/profile";
    static final String drVideoProfile = "/doctors/Dr_John_Nemunaitis/video";

    @Parameters({"url"})
    @Test
    public void doctorSeoProfileTest(String url) {
    	driver = DriverManager.getDriver();

        driver.get(url + seoDrProfile);

        SEOProfilePage seoProfile = PageFactory.initElements(driver, SEOProfilePage.class);

        Assert.assertTrue(seoProfile.viewFullProfileButtonIsVisible(),
                "SEO Doctor Page did not load successfully" + seoProfile);
    }

    @Parameters({"url"})
    @Test
    public void dentistSeoProfileTest(String url) {
    	driver = DriverManager.getDriver();

        driver.get(url + seoDentistProfile);

        SEOProfilePage seoProfile = PageFactory.initElements(driver, SEOProfilePage.class);

        Assert.assertTrue(seoProfile.viewFullProfileButtonIsVisible(),
                "SEO Dentist Page did not load successfully: " + seoDentistProfile);
    }

    @Parameters({"url"})
    @Test
    public void viewFullProfileSummaryTest(String url) {
    	driver = DriverManager.getDriver();

        driver.get(url);

        driver.get(url + drProfile);

        ProfilePage profilePage = PageFactory.initElements(driver, ProfilePage.class);

        Assert.assertTrue(profilePage.isSummaryPage(),
                "Summary page was not visible: " + driver.getCurrentUrl());
    }

    @Parameters({"url"})
    @Test
    public void viewFullProfileReviewsTest(String url) {
    	driver = DriverManager.getDriver();

        driver.get(url);

        driver.get(url+ drProfile);

        ProfilePage profilePage = PageFactory.initElements(driver, ProfilePage.class);

        profilePage.clickPatientReviewsTab();

        Assert.assertTrue(profilePage.isPatientReviewsPage(),
                "Patient Reviews page was not visible: " + driver.getCurrentUrl());
    }

    @Parameters({"url"})
    @Test
    public void viewFullProfileCredentialsTest(String url) {
    	driver = DriverManager.getDriver();

        driver.get(url);

        driver.get(url + drProfile);

        ProfilePage profilePage = PageFactory.initElements(driver, ProfilePage.class);

        profilePage.clickCredentials();

        Assert.assertTrue(profilePage.isCredentialsPage(),"Credentials page was not visible: " + driver.getCurrentUrl());

    }

    @Parameters({"url"})
    @Test
    public void viewLocationsAvailabilityTest(String url) {
    	driver = DriverManager.getDriver();

        driver.get(url);

        driver.get(url + drProfile);

        ProfilePage profilePage = PageFactory.initElements(driver, ProfilePage.class);

        profilePage.clickLocationsAvailabilityTab();

        Assert.assertTrue(profilePage.isLocationsAvailabilityPage(),
                "Locations availability page was not visible: " + driver.getCurrentUrl());
    }

    @Parameters({"url"})
    @Test
    public void viewAcceptedInsuranceTest(String url) {
    	driver = DriverManager.getDriver();

        driver.get(url);

        driver.get(url + drProfile);

        ProfilePage profilePage = PageFactory.initElements(driver, ProfilePage.class);

        profilePage.clickAcceptedInsurance();

        Assert.assertTrue(profilePage.isAcceptedInsurancePage(),
                "Accepted insurance page was not visible: " + driver.getCurrentUrl());
    }

    @Parameters({"url"})
    @Test
    public void viewVideoProfileTest(String url) {
    	driver = DriverManager.getDriver();

        driver.get(url);

        driver.get(url + drVideoProfile);

        ProfilePage profilePage = PageFactory.initElements(driver, ProfilePage.class);

        Assert.assertTrue(profilePage.drVideoIsVisible(), "Dr Video is not visible: " + driver.getCurrentUrl());
    }

    @Parameters({"url"})
    @Test
    public void redirectToSeoProfileTest(String url) {
    	driver = DriverManager.getDriver();

        driver.get(url + drProfile);

        SEOProfilePage seoProfile = PageFactory.initElements(driver, SEOProfilePage.class);

        Assert.assertTrue(seoProfile.viewFullProfileButtonIsVisible(),
                "SEO Profile page did not load: " + driver.getCurrentUrl());

    }

    @Parameters({"url"})
    @Test
    public void seoToFullProfileTest(String url) {
    	driver = DriverManager.getDriver();

        driver.get(url + drProfile);

        SEOProfilePage seoProfile = PageFactory.initElements(driver, SEOProfilePage.class);

        Assert.assertTrue(seoProfile.viewFullProfileButtonIsVisible(),
                "SEO Profile page did not load: " + driver.getCurrentUrl());

        driver.get(url + drProfile);

        ProfilePage fullProfile = PageFactory.initElements(driver, ProfilePage.class);

        Assert.assertTrue(fullProfile.isSummaryPage(),
                "Profile page did not load: " + driver.getCurrentUrl());

    }

}