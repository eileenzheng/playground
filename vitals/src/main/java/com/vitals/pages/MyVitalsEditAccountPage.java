package com.vitals.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.vitals.DriverManager;

public class MyVitalsEditAccountPage {
	
	private final WebDriver driver;
	private final WebDriverWait wait;

    public MyVitalsEditAccountPage() {
    	driver = DriverManager.getDriver();
    	wait = new WebDriverWait(driver,15,2000);
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
    	wait.until(ExpectedConditions.visibilityOf(passwordTextBox));
    	passwordTextBox.clear();
    	passwordTextBox.sendKeys(text);
    	return this;
    }
    
    public MyVitalsEditAccountPage typePasswordConfirmation(String text) {
    	wait.until(ExpectedConditions.visibilityOf(passwordConfirmationTextBox));
    	passwordConfirmationTextBox.clear();
    	passwordConfirmationTextBox.sendKeys(text);
    	return this;
    }
    
    public MyVitalsEditAccountPage typePasswordCurrent(String text) {
    	wait.until(ExpectedConditions.visibilityOf(passwordCurrentTextBox));
    	passwordCurrentTextBox.clear();
    	passwordCurrentTextBox.sendKeys(text);
    	return this;
    }
    
    public MyVitalsLocateProfilePage clickEditProfileNoProfile() {
    	wait.until(ExpectedConditions.visibilityOf(editProfileButton));
    	editProfileButton.click();
    	return PageFactory.initElements(driver, MyVitalsLocateProfilePage.class);
    }
    
    public MyVitalsProfessionalsPage clickEditProfileHasProfile() {
    	wait.until(ExpectedConditions.visibilityOf(editProfileButton));
    	editProfileButton.click();
    	return PageFactory.initElements(driver, MyVitalsProfessionalsPage.class);
    }
    
    public MyVitalsLocateProfilePage clickClaimProfile() {
    	wait.until(ExpectedConditions.visibilityOf(claimProfileButton));
    	claimProfileButton.click();
    	return PageFactory.initElements(driver, MyVitalsLocateProfilePage.class);
    }
    
    public MyVitalsHomePage clickSaveChanges() {
    	saveChangesButton.click();
    	return PageFactory.initElements(driver, MyVitalsHomePage.class);
    }
}
