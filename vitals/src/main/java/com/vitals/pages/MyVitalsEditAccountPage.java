package com.vitals.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.vitals.DriverManager;

public class MyVitalsEditAccountPage {
	
	private WebDriver driver;

    public MyVitalsEditAccountPage() {
    	driver = DriverManager.getDriver();
    }
    
    @FindBy(css="#user_password")
    private WebElement passwordTextBox;
    
    @FindBy(css="#user_password_confirmation")
    private WebElement passwordConfirmationTextBox;
    
    @FindBy(css="#user_current_password")
    private WebElement passwordCurrentTextBox;
    
    @FindBy(css="button[type=submit]")
    private WebElement saveChangesButton;
    
    @FindBy(css=".span3 .button")
    private WebElement editProfileButton;
    
    @FindBy(css=".span3 .button-proceed")
    private WebElement claimProfileButton;
    
    public MyVitalsEditAccountPage typePassword(String text) {
    	passwordTextBox.clear();
    	passwordTextBox.sendKeys(text);
    	return this;
    }
    
    public MyVitalsEditAccountPage typePasswordConfirmation(String text) {
    	passwordConfirmationTextBox.clear();
    	passwordConfirmationTextBox.sendKeys(text);
    	return this;
    }
    
    public MyVitalsEditAccountPage typePasswordCurrent(String text) {
    	passwordCurrentTextBox.clear();
    	passwordCurrentTextBox.sendKeys(text);
    	return this;
    }
    
    public MyVitalsLocateProfilePage clickEditProfileNoProfile() {
    	editProfileButton.click();
    	return PageFactory.initElements(driver, MyVitalsLocateProfilePage.class);
    }
    
    public MyVitalsProfessionalsPage clickEditProfileHasProfile() {
    	editProfileButton.click();
    	return PageFactory.initElements(driver, MyVitalsProfessionalsPage.class);
    }
    
    public MyVitalsLocateProfilePage clickClaimProfile() {
    	claimProfileButton.click();
    	return PageFactory.initElements(driver, MyVitalsLocateProfilePage.class);
    }
    
    public MyVitalsHomePage clickSaveChanges() {
    	saveChangesButton.click();
    	return PageFactory.initElements(driver, MyVitalsHomePage.class);
    }
}
