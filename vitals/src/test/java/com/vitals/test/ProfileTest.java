package com.vitals.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
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
    
    String url;
    
    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) throws Exception {
        this.url = url;
    }

    @Test
    public void doctorSeoProfileTest() {
    	driver = DriverManager.getDriver();

        driver.get(url + seoDrProfile);

        SEOProfilePage seoProfile = PageFactory.initElements(driver, SEOProfilePage.class);

        Assert.assertTrue(seoProfile.viewFullProfileButtonIsVisible(),
                "SEO Doctor Page did not load successfully" + seoProfile);
    }

    @Test
    public void dentistSeoProfileTest() {
    	driver = DriverManager.getDriver();

        driver.get(url + seoDentistProfile);

        SEOProfilePage seoProfile = PageFactory.initElements(driver, SEOProfilePage.class);

        Assert.assertTrue(seoProfile.viewFullProfileButtonIsVisible(),
                "SEO Dentist Page did not load successfully: " + seoDentistProfile);
    }

    @Test
    public void viewFullProfileSummaryTest() {
    	driver = DriverManager.getDriver();

        driver.get(url);

        driver.get(url + drProfile);

        ProfilePage profilePage = PageFactory.initElements(driver, ProfilePage.class);

        Assert.assertTrue(profilePage.isSummaryPage(),
                "Summary page was not visible: " + driver.getCurrentUrl());
    }

    @Test
    public void viewFullProfileReviewsTest() {
    	driver = DriverManager.getDriver();

        driver.get(url);

        driver.get(url+ drProfile);

        ProfilePage profilePage = PageFactory.initElements(driver, ProfilePage.class);

        profilePage.clickPatientReviewsTab();

        Assert.assertTrue(profilePage.isPatientReviewsPage(),
                "Patient Reviews page was not visible: " + driver.getCurrentUrl());
    }

    @Test
    public void viewFullProfileCredentialsTest() {
    	driver = DriverManager.getDriver();

        driver.get(url);

        driver.get(url + drProfile);

        ProfilePage profilePage = PageFactory.initElements(driver, ProfilePage.class);

        profilePage.clickCredentials();

        Assert.assertTrue(profilePage.isCredentialsPage(),"Credentials page was not visible: " + driver.getCurrentUrl());

    }

    @Test
    public void viewLocationsAvailabilityTest() {
    	driver = DriverManager.getDriver();

        driver.get(url);

        driver.get(url + drProfile);

        ProfilePage profilePage = PageFactory.initElements(driver, ProfilePage.class);

        profilePage.clickLocationsAvailabilityTab();

        Assert.assertTrue(profilePage.isLocationsAvailabilityPage(),
                "Locations availability page was not visible: " + driver.getCurrentUrl());
    }

    @Test
    public void viewAcceptedInsuranceTest() {
    	driver = DriverManager.getDriver();

        driver.get(url);

        driver.get(url + drProfile);

        ProfilePage profilePage = PageFactory.initElements(driver, ProfilePage.class);

        profilePage.clickAcceptedInsurance();

        Assert.assertTrue(profilePage.isAcceptedInsurancePage(),
                "Accepted insurance page was not visible: " + driver.getCurrentUrl());
    }

    @Test
    public void viewVideoProfileTest() {
    	driver = DriverManager.getDriver();

        driver.get(url);

        driver.get(url + drVideoProfile);

        ProfilePage profilePage = PageFactory.initElements(driver, ProfilePage.class);

        Assert.assertTrue(profilePage.drVideoIsVisible(), "Dr Video is not visible: " + driver.getCurrentUrl());
    }

    @Test
    public void redirectToSeoProfileTest() {
    	driver = DriverManager.getDriver();

        driver.get(url + drProfile);

        SEOProfilePage seoProfile = PageFactory.initElements(driver, SEOProfilePage.class);

        Assert.assertTrue(seoProfile.viewFullProfileButtonIsVisible(),
                "SEO Profile page did not load: " + driver.getCurrentUrl());

    }

    @Test
    public void seoToFullProfileTest() {
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