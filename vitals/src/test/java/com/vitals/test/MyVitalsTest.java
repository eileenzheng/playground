package com.vitals.test;

import com.vitals.pages.profile.ProfileCommonPage;
import com.vitalsqa.testrail.TestCase;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.vitals.pages.HomePage;
import com.vitals.pages.myvitals.MyVitalsClaimProfilePage;
import com.vitals.pages.myvitals.MyVitalsEditAccountPage;
import com.vitals.pages.myvitals.MyVitalsEditBasicInfoPage;
import com.vitals.pages.myvitals.MyVitalsHomePage;
import com.vitals.pages.myvitals.MyVitalsLocateProfilePage;
import com.vitals.pages.myvitals.MyVitalsProfessionalsPage;
import com.vitals.pages.myvitals.MyVitalsSignInPage;

public class MyVitalsTest {

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
    	HomePage homePage = new HomePage();
        homePage.deleteCookies();
        homePage.get(url);
        login(homePage);

        MyVitalsHomePage myVitalsHome = new MyVitalsHomePage();

        // verify successful alert
        Assert.assertTrue(myVitalsHome.isSignInSuccessful());
    }
    
    @TestCase(id=1560)
    @Test
    public void editAccount() {
        HomePage homePage = new HomePage();
        homePage.deleteCookies();
        homePage.get(url);

        // sign in to myvitals first
        login(homePage);
        MyVitalsHomePage myVitalsHome = new MyVitalsHomePage();

        // go to edit account setting page & change password
        myVitalsHome.buttons().get(0).click();
        MyVitalsEditAccountPage editAccountPage = new MyVitalsEditAccountPage();
        editAccountPage.passwordTextBox().clearField().sendKeys("test1234");
        editAccountPage.passwordConfirmationTextBox().clearField().sendKeys("test1234");
        editAccountPage.passwordCurrentTextBox().clearField().sendKeys(("test1234"));
        editAccountPage.saveChangesButton().click();

        // verify successful alert
        Assert.assertTrue(myVitalsHome.isAccountUpdateSuccessful());
    }

    @TestCase(id=1564)
    @Test
    public void claimProfileFail() {
        HomePage homePage = new HomePage();
        homePage.deleteCookies();
        homePage.get(url);

    	// sign in to myvitals & click "claim profile"
    	login(homePage);
        MyVitalsHomePage myVitalsHome = new MyVitalsHomePage();
        myVitalsHome.buttons().get(1).click();

        // search for 'Todd' and click a random provider
        MyVitalsLocateProfilePage locateProfilePage = new MyVitalsLocateProfilePage();
        locateProfilePage.enterName("Todd");
        locateProfilePage.getRandom(locateProfilePage.autoSuggestList()).click();

        // click submit button and expect alert because nothing is filled out
        MyVitalsClaimProfilePage claimPage = new MyVitalsClaimProfilePage();
        claimPage.claimProfileButton().click();
        Assert.assertTrue(claimPage.isEmptyAlertShown());
    }

    @TestCase(id=1565)
    @Test
    public void claimProfileSuccess() {
    	// only perform this test on staging or qa
    	if (url.contains("staging") || url.contains("qa")) {
            HomePage homePage = new HomePage();
            homePage.deleteCookies();
            homePage.get(url);

            // sign in to myvitals first
            login(homePage);

    		// go to profile page of doctor
            ProfileCommonPage profileToClaim = new ProfileCommonPage();
            profileToClaim.get(url + claimProfileUrl);
            profileToClaim.claimProfileLink().click();

    		// start filling in stuff on claim page and submit
            MyVitalsClaimProfilePage claimPage = new MyVitalsClaimProfilePage();
    		claimPage.firstNameTextBox().clearField().sendKeys("abc");
            claimPage.lastNameTextBox().clearField().sendKeys("abc");
    		claimPage.clickFillLinks();
            claimPage.claimProfileButton().click();

    		MyVitalsEditBasicInfoPage basicInfoPage = new MyVitalsEditBasicInfoPage();
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
    	if (url.contains("staging") || url.contains("mdxdev")) {
            HomePage homePage = new HomePage();
            homePage.deleteCookies();
            homePage.get(url);

            // sign in to myvitals first
            login(homePage);
            homePage.headerModule().signedInEmailLink().click();
            homePage.headerModule().editProfileLink().click();

    		// remove profile link
    		MyVitalsEditAccountPage editAccountPage = new MyVitalsEditAccountPage();
            editAccountPage.editProfileButton().click();

    		MyVitalsProfessionalsPage professionalsPage = new MyVitalsProfessionalsPage();
            professionalsPage.deleteButton().click();
            professionalsPage.acceptAlertIfPresent();

    		MyVitalsLocateProfilePage locateProfilePage = new MyVitalsLocateProfilePage();
    		Assert.assertTrue(locateProfilePage.isNoProfileAlertCorrect(), "No Profile alert is not shown");
    	}
    	else {
    		// do not perform this on production
    	}
    }

    @TestCase(id=1561)
    @Test (dependsOnMethods = {"removeProfileLink"})
    public void editNoProfile() {
        HomePage homePage = new HomePage();
        homePage.deleteCookies();
        homePage.get(url);

    	// sign in to myvitals & click "edit profile"
        login(homePage);
        MyVitalsHomePage myVitalsHome = new MyVitalsHomePage();
        myVitalsHome.buttons().get(0).click();

        MyVitalsEditAccountPage editAccountPage = new MyVitalsEditAccountPage();
        editAccountPage.editProfileButton().click();

        MyVitalsLocateProfilePage locateProfilePage = new MyVitalsLocateProfilePage();

        // no profile is linked message should be shown
        Assert.assertTrue(locateProfilePage.isNoProfileAlertCorrect());
    }

    @TestCase(id=1562)
    @Test (dependsOnMethods = {"removeProfileLink"})
    public void locateProfileAutoSuggestLocation() {
        HomePage homePage = new HomePage();
        homePage.deleteCookies();
        homePage.get(url);

    	// sign in to myvitals & click "claim profile"
        login(homePage);
        MyVitalsHomePage myVitalsHome = new MyVitalsHomePage();
        myVitalsHome.buttons().get(1).click();

        // search for NY in city/state box
        MyVitalsLocateProfilePage locateProfilePage = new MyVitalsLocateProfilePage();
        locateProfilePage.enterCityState("NY");
        Reporter.log("Location Suggestions: " + locateProfilePage.getLocationSuggestions());
        Assert.assertTrue(locateProfilePage.checkLocationSuggestions("NY"), "Location suggestions does not contain NY");
    }

    @TestCase(id=1563)
    @Test (dependsOnMethods = {"removeProfileLink"})
    public void locateProfileAutoSuggestName() {
        HomePage homePage = new HomePage();
        homePage.deleteCookies();
        homePage.get(url);

    	// sign in to myvitals & click "claim profile"
    	login(homePage);
        MyVitalsHomePage myVitalsHome = new MyVitalsHomePage();
        myVitalsHome.buttons().get(1).click();

        // search for 'Todd' in name box
        MyVitalsLocateProfilePage locateProfilePage = new MyVitalsLocateProfilePage();
        locateProfilePage.enterName("Todd");
        Reporter.log("Name Suggestions: " + locateProfilePage.getNameSuggestions());
        Assert.assertTrue(locateProfilePage.checkNameSuggestions("Todd"));
    }
    
    public void login(HomePage home) {
        home.headerModule().signInLink().click();
    	MyVitalsSignInPage signInPage = new MyVitalsSignInPage();
        signInPage.emailTextBox().clearField().sendKeys("selenium@mailinator.com");
        signInPage.passwordTextBox().clearField().sendKeys("test1234");
        signInPage.signInButton().click();
    }
}
