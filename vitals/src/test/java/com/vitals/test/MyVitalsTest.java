package com.vitals.test;

import com.vitalsqa.testrail.TestCase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.vitals.DriverManager;
import com.vitals.pages.HomePage;
import com.vitals.pages.myvitals.MyVitalsClaimProfilePage;
import com.vitals.pages.myvitals.MyVitalsEditAccountPage;
import com.vitals.pages.myvitals.MyVitalsEditBasicInfoPage;
import com.vitals.pages.myvitals.MyVitalsHomePage;
import com.vitals.pages.myvitals.MyVitalsLocateProfilePage;
import com.vitals.pages.myvitals.MyVitalsProfessionalsPage;
import com.vitals.pages.myvitals.MyVitalsSignInPage;
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

    @TestCase(id=1559)
    @Test
    public void loginMyVitals() {
    	driver = DriverManager.getDriver();
    	driver.manage().deleteAllCookies();
        driver.get(url);
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        MyVitalsHomePage myVitalsHome = login(homePage);
        
        // verify successful alert
        Assert.assertTrue(myVitalsHome.isSignInSuccessful());
    }
    
    @TestCase(id=1560)
    @Test
    public void editAccount() {
    	driver = DriverManager.getDriver();
    	driver.manage().deleteAllCookies();
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
    
    @TestCase(id=1564)
    @Test
    public void claimProfileFail() {
    	driver = DriverManager.getDriver();
    	driver.manage().deleteAllCookies();
    	driver.get(url);
    	
    	// sign in to myvitals & click "claim profile"
    	HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        MyVitalsHomePage myVitalsHome = login(homePage);
        MyVitalsLocateProfilePage locateProfilePage = myVitalsHome.clickClaimProfile();
        
        // search for 'Todd' and click a random provider
        locateProfilePage.enterName("Todd");
        MyVitalsClaimProfilePage claimPage = locateProfilePage.clickRandomProvider();
        
        // click submit button and expect alert because nothing is filled out
        Assert.assertTrue(claimPage.clickClaimExpectFailure().isEmptyAlertShown());
    }

    @TestCase(id=1565)
    @Test
    public void claimProfileSuccess() {
    	// only perform this test on staging or qa
    	if (url.contains("staging") || url.contains("qa")) {
            driver = DriverManager.getDriver();
            
            // sign in to myvitals first
            driver.manage().deleteAllCookies();
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
    	}
    	else {
    		// do not perform this on production
    	}
    }
    
    @TestCase(id=1566)
    @Test (dependsOnMethods = {"claimProfileSuccess"})
    public void removeProfileLink() {
    	// only perform this test on staging or qa
    	if (url.contains("staging") || url.contains("qa")) {
            driver = DriverManager.getDriver();
            
            // sign in to myvitals first
            driver.manage().deleteAllCookies();
            driver.get(url);
            HomePage homePage = PageFactory.initElements(driver, HomePage.class);
            login(homePage);
            
    		// remove profile link
    		MyVitalsEditAccountPage editAccountPage = homePage.header.clickSignedInEmail().clickEditProfile();
    		MyVitalsProfessionalsPage professionalsPage = editAccountPage.clickEditProfileHasProfile();
    		MyVitalsLocateProfilePage locateProfilePage = professionalsPage.clickDeleteButton();
    		Assert.assertTrue(locateProfilePage.isNoProfileAlertCorrect(), "No Profile alert is not shown");
    	}
    	else {
    		// do not perform this on production
    	}
    }
    
    @TestCase(id=1561)
    @Test (dependsOnMethods = {"removeProfileLink"})
    public void editNoProfile() {
    	driver = DriverManager.getDriver();
    	driver.manage().deleteAllCookies();
    	driver.get(url);
    	
    	// sign in to myvitals & click "edit profile"
    	HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        MyVitalsHomePage myVitalsHome = login(homePage);
        MyVitalsEditAccountPage editAccountPage = myVitalsHome.clickChangeSetting();
        MyVitalsLocateProfilePage locateProfilePage = editAccountPage.clickEditProfileNoProfile();
        
        // no profile is linked message should be shown
        Assert.assertTrue(locateProfilePage.isNoProfileAlertCorrect());
    }
    
    @TestCase(id=1562)
    @Test (dependsOnMethods = {"removeProfileLink"})
    public void locateProfileAutoSuggestLocation() {
    	driver = DriverManager.getDriver();
    	driver.manage().deleteAllCookies();
    	driver.get(url);
    	
    	// sign in to myvitals & click "claim profile"
    	HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        MyVitalsHomePage myVitalsHome = login(homePage);
        MyVitalsLocateProfilePage locateProfilePage = myVitalsHome.clickClaimProfile();
        
        // search for NY in city/state box
        locateProfilePage.enterCityState("NY");
        Reporter.log("Location Suggestions: " + locateProfilePage.getLocationSuggestions());
        Assert.assertTrue(locateProfilePage.checkLocationSuggestions("NY"), "Location suggestions does not contain NY");
    }
    
    @TestCase(id=1563)
    @Test (dependsOnMethods = {"removeProfileLink"})
    public void locateProfileAutoSuggestName() {
    	driver = DriverManager.getDriver();
    	driver.manage().deleteAllCookies();
    	driver.get(url);
    	
    	// sign in to myvitals & click "claim profile"
    	HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        MyVitalsHomePage myVitalsHome = login(homePage);
        MyVitalsLocateProfilePage locateProfilePage = myVitalsHome.clickClaimProfile();
        
        // search for 'Todd' in name box
        locateProfilePage.enterName("Todd");
        Reporter.log("Name Suggestions: " + locateProfilePage.getNameSuggestions());
        Assert.assertTrue(locateProfilePage.checkNameSuggestions("Todd"));
    }
    
    public MyVitalsHomePage login(HomePage home) {
    	MyVitalsSignInPage signInPage = home.header.clickSignIn();
    	signInPage.enterEmail("selenium@mailinator.com");
    	signInPage.enterPassword("test1234");
    	return signInPage.clickSignIn();
    }
}
