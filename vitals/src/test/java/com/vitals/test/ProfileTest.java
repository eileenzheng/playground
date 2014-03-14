package com.vitals.test;

import com.vitals.pages.ProfileSeoPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.vitals.DriverManager;
import com.vitals.TestCase;
import com.vitals.pages.ProfilePage;

public class ProfileTest {

    WebDriver driver;
    
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
    	driver = DriverManager.getDriver();
    	
    	driver.manage().deleteAllCookies();
        driver.get(url + drProfile);
        ProfileSeoPage seoProfile = PageFactory.initElements(driver, ProfileSeoPage.class);
        Assert.assertTrue(seoProfile.viewFullProfileButtonIsVisible(),
                "SEO Doctor Page did not load successfully" + drProfile);
        
        driver.get(url + drProfile);
        ProfilePage fullProfile = PageFactory.initElements(driver, ProfilePage.class);
        Assert.assertTrue(fullProfile.isSummaryPage(),
                "Profile page did not load: " + driver.getCurrentUrl());
    }

    @TestCase(id=1455)
    @Test
    public void dentistSeoProfileTest() {
    	driver = DriverManager.getDriver();

    	driver.manage().deleteAllCookies();
        driver.get(url + dentistProfile);
        ProfileSeoPage seoProfile = PageFactory.initElements(driver, ProfileSeoPage.class);
        Assert.assertTrue(seoProfile.viewFullProfileButtonIsVisible(),
                "SEO Dentist Page did not load successfully: " + dentistProfile);
        
        driver.get(url + dentistProfile);
        ProfilePage fullProfile = PageFactory.initElements(driver, ProfilePage.class);
        Assert.assertTrue(fullProfile.isSummaryPage(),
                "Profile page did not load: " + driver.getCurrentUrl());
    }

    @TestCase(id=1456)
    @Test
    public void viewFullProfileSummaryTest() {
    	driver = DriverManager.getDriver();

        driver.get(url);
        driver.get(url + drProfile);

        ProfilePage profilePage = PageFactory.initElements(driver, ProfilePage.class);

        Assert.assertTrue(profilePage.isSummaryPage(),
                "Summary page was not visible: " + driver.getCurrentUrl());
    }

    @TestCase(id=1457)
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

    @TestCase(id=1633)
    @Test
    public void viewFullProfileCredentialsTest() {
    	driver = DriverManager.getDriver();

        driver.get(url);

        driver.get(url + drProfile);

        ProfilePage profilePage = PageFactory.initElements(driver, ProfilePage.class);

        profilePage.clickCredentials();

        Assert.assertTrue(profilePage.isCredentialsPage(),"Credentials page was not visible: " + driver.getCurrentUrl());

    }

    @TestCase(id=1458)
    @Test
    public void viewFullProfileLocationsTest() {
    	driver = DriverManager.getDriver();

        driver.get(url);

        driver.get(url + drProfile);

        ProfilePage profilePage = PageFactory.initElements(driver, ProfilePage.class);

        profilePage.clickLocationsAvailabilityTab();

        Assert.assertTrue(profilePage.isLocationsAvailabilityPage(),
                "Locations availability page was not visible: " + driver.getCurrentUrl());
    }

    @TestCase(id=1459)
    @Test
    public void viewFullProfileInsurancesTest() {
    	driver = DriverManager.getDriver();

        driver.get(url);

        driver.get(url + drProfile);

        ProfilePage profilePage = PageFactory.initElements(driver, ProfilePage.class);

        profilePage.clickAcceptedInsurance();

        Assert.assertTrue(profilePage.isAcceptedInsurancePage(),
                "Accepted insurance page was not visible: " + driver.getCurrentUrl());
    }

    @TestCase(id=1460)
    @Test
    public void viewVideoProfileTest() {
    	driver = DriverManager.getDriver();

        driver.get(url);

        driver.get(url + drVideoProfile);

        ProfilePage profilePage = PageFactory.initElements(driver, ProfilePage.class);

        Assert.assertTrue(profilePage.drVideoIsVisible(), "Dr Video is not visible: " + driver.getCurrentUrl());
    }
}