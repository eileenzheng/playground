package com.vitals.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.vitals.DriverManager;
import com.vitals.pages.HomePage;
import com.vitals.pages.MyVitalsClaimProfilePage;
import com.vitals.pages.MyVitalsEditAccountPage;
import com.vitals.pages.MyVitalsEditBasicInfoPage;
import com.vitals.pages.MyVitalsHomePage;
import com.vitals.pages.MyVitalsLocateProfilePage;
import com.vitals.pages.MyVitalsProfessionalsPage;
import com.vitals.pages.MyVitalsSignInPage;
import com.vitals.pages.ProfilePage;

public class MyVitalsTest {
	private WebDriver driver;
    private String url;
    private final String claimProfileUrl = "/doctors/Dr_Marina_Katz/profile";
    
    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) throws Exception {
        this.url = url;
    }

    @Test
    public void testLogin() {
    	driver = DriverManager.getDriver();
        driver.get(url);
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        MyVitalsHomePage myVitalsHome = login(homePage);
        
        // verify successful alert
        Assert.assertTrue(myVitalsHome.isSignInSuccessful());
    }
    
    @Test
    public void testEditAccount() {
    	driver = DriverManager.getDriver();
        driver.get(url);
        
        // sign in to myvitals first
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        MyVitalsHomePage myVitalsHome = login(homePage);
        
        // go to edit account setting page & change password
        MyVitalsEditAccountPage editAccountPage = myVitalsHome.clickChangeSetting();
        editAccountPage.typePassword("test1234");
        editAccountPage.typePasswordConfirmation("test1234");
        editAccountPage.typePasswordCurrent("test1234");
        myVitalsHome = editAccountPage.clickSaveChanges();
        
        // verify successful alert 
        Assert.assertTrue(myVitalsHome.isAccountUpdateSuccessful());
    }
    
    // pre-requisite: account is not linked to any profile
    @Test
    public void testLocateProfile() {
    	driver = DriverManager.getDriver();
    	driver.get(url);
    	
    	// sign in to myvitals & click "edit profile"
    	HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        MyVitalsHomePage myVitalsHome = login(homePage);
        MyVitalsEditAccountPage editAccountPage = myVitalsHome.clickChangeSetting();
        MyVitalsLocateProfilePage locateProfilePage = editAccountPage.clickEditProfileNoProfile();
        
        // no profile is linked message should be shown
        Assert.assertTrue(locateProfilePage.isNoProfileAlertCorrect());
        
        // search for ny in city/state box
        locateProfilePage.enterCityState("NY");
        Reporter.log("Location Suggestions: " + locateProfilePage.getLocationSuggestions());
        Assert.assertTrue(locateProfilePage.checkLocationSuggestions("NY"));
        locateProfilePage = locateProfilePage.clickRandomLocation();
        
        // refresh page
        String currentUrl = driver.getCurrentUrl();
        driver.get(currentUrl);
        
        // search for 'smith' in name box
        locateProfilePage.enterName("smith");
        Reporter.log("Name Suggestions: " + locateProfilePage.getNameSuggestions());
        //Assert.assertTrue(locateProfilePage.checkNameSuggestions("smith"));
        MyVitalsClaimProfilePage claimPage = locateProfilePage.clickRandomProvider();
        
        // click submit button and expect alert because nothing is filled out
        Assert.assertTrue(claimPage.clickClaimExpectFailure().isEmptyAlertShown());
    }
    
    @Test
    public void testClaimProfile() {
    	// only perform this test on staging or qa
    	if (url.contains("staging") || url.contains("qa")) {
            driver = DriverManager.getDriver();
            
            // sign in to myvitals first
            driver.get(url);
            HomePage homePage = PageFactory.initElements(driver, HomePage.class);
            login(homePage);
            
    		// go to profile page of doctor 
    		driver.get(url + claimProfileUrl);
    		ProfilePage profileToClaim = PageFactory.initElements(driver, ProfilePage.class);
    		MyVitalsClaimProfilePage claimPage = profileToClaim.clickClaimProfileLink();
    		
    		// start filling in stuff on claim page and submit
    		claimPage.typeFirstName("abc");
    		claimPage.typeLastName("abc");
    		claimPage.clickFillLinks();
    		MyVitalsEditBasicInfoPage basicInfoPage = claimPage.clickClaimExpectSuccess();
    		Assert.assertTrue(basicInfoPage.isProfileLinkSuccessAlertShown(), "Profile link success text is NOT shown");
    		
    		// remove profile link after test
    		MyVitalsEditAccountPage editAccountPage = basicInfoPage.header.clickSignedInEmail();
    		MyVitalsProfessionalsPage professionalsPage = editAccountPage.clickEditProfileHasProfile();
    		MyVitalsLocateProfilePage locateProfilePage = professionalsPage.clickDeleteButton();
    		Assert.assertTrue(locateProfilePage.isNoProfileAlertCorrect(), "No Profile alert is not shown");
    	}
    	else {
    		// do not perform this on production
    	}
    }
    
    public MyVitalsHomePage login(HomePage home) {
    	MyVitalsSignInPage signInPage = home.header.clickSignIn();
    	signInPage.enterEmail("selenium@mailinator.com");
    	signInPage.enterPassword("test1234");
    	return signInPage.clickSignIn();
    }
}
