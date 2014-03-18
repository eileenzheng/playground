package com.vitals.pages.myvitals;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.vitals.DriverManager;

public class MyVitalsHomePage {
	
	private final WebDriver driver;

    public MyVitalsHomePage() {
    	driver = DriverManager.getDriver();
    }
    
    @FindBy(css=".alert>div")
    private WebElement alertText;
    
    @FindBy(css=".button-large")
    private List<WebElement> buttons;
    
    public MyVitalsEditAccountPage clickChangeSetting () {
    	buttons.get(0).click();
    	return PageFactory.initElements(driver, MyVitalsEditAccountPage.class);
    }
    
    public MyVitalsLocateProfilePage clickClaimProfile () {
    	buttons.get(1).click();
    	return PageFactory.initElements(driver, MyVitalsLocateProfilePage.class);
    }
    
    public boolean isSignInSuccessful() {
        return alertText.getText().equals("Signed in successfully.");
    }
    
    public boolean isAccountUpdateSuccessful() {
        return alertText.getText().equals("You updated your account successfully.");
    }

}
