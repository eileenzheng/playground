package com.vitals.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vitals.DriverManager;

public class MyVitalsSignInPage {
	
	private WebDriver driver;

    public MyVitalsSignInPage() {
    	driver = DriverManager.getDriver();
    }
    
    @FindBy(css=".sign-in #user_email")
    private WebElement emailTextBox;
    
    @FindBy(css=".sign-in #user_password")
    private WebElement passwordTextBox;
    
    @FindBy(css=".sign-in .button")
    private WebElement signInButton;
    
    public MyVitalsSignInPage enterEmail(String text) {
    	emailTextBox.clear();
    	emailTextBox.sendKeys(text);
    	return this;
    }
    
    public MyVitalsSignInPage enterPassword(String text) {
    	passwordTextBox.clear();
    	passwordTextBox.sendKeys(text);
    	return this;
    }
    
    public MyVitalsHomePage clickSignIn() {
    	signInButton.click();
    	return PageFactory.initElements(driver, MyVitalsHomePage.class);
    }
}
