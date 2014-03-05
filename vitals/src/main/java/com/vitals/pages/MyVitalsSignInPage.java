package com.vitals.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.vitals.DriverManager;

public class MyVitalsSignInPage {
	
	private final WebDriver driver;
	private final WebDriverWait wait;

    public MyVitalsSignInPage() {
    	driver = DriverManager.getDriver();
    	wait = new WebDriverWait(driver,15,2000);
    }
    
    @FindBy(css=".sign-in input[type=email]")
    private WebElement emailTextBox;
    
    @FindBy(css=".sign-in input[type=password]")
    private WebElement passwordTextBox;
    
    @FindBy(css=".sign-in .button")
    private WebElement signInButton;
    
    public MyVitalsSignInPage enterEmail(String text) {
    	wait.until(ExpectedConditions.visibilityOf(emailTextBox));
    	emailTextBox.clear();
    	emailTextBox.sendKeys(text);
    	return this;
    }
    
    public MyVitalsSignInPage enterPassword(String text) {
    	wait.until(ExpectedConditions.visibilityOf(passwordTextBox));
    	passwordTextBox.clear();
    	passwordTextBox.sendKeys(text);
    	return this;
    }
    
    public MyVitalsHomePage clickSignIn() {
    	signInButton.click();
    	return PageFactory.initElements(driver, MyVitalsHomePage.class);
    }
}
