package com.vitals.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.vitals.DriverManager;

public class MyVitalsHomePage {
	
	private final WebDriver driver;
	private final WebDriverWait wait;

    public MyVitalsHomePage() {
    	driver = DriverManager.getDriver();
    	wait = new WebDriverWait(driver,15,2000);
    }
    
    @FindBy(css=".alert>div")
    private WebElement alertText;
    
    @FindBy(css=".button-large")
    private List<WebElement> buttons;
    
    public MyVitalsEditAccountPage clickChangeSetting () {
    	wait.until(ExpectedConditions.visibilityOfAllElements(buttons));
    	buttons.get(0).click();
    	return PageFactory.initElements(driver, MyVitalsEditAccountPage.class);
    }
    
    public MyVitalsLocateProfilePage clickClaimProfile () {
    	wait.until(ExpectedConditions.visibilityOfAllElements(buttons));
    	buttons.get(1).click();
    	return PageFactory.initElements(driver, MyVitalsLocateProfilePage.class);
    }
    
    public boolean isSignInSuccessful() {
    	if (alertText.getText().equals("Signed in successfully."))
    		return true;
    	else
    		return false;
    }
    
    public boolean isAccountUpdateSuccessful() {
    	if (alertText.getText().equals("You updated your account successfully."))
    		return true;
    	else
    		return false;
    }

}
