package com.vitals.pages.myvitals;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vitals.DriverManager;

public class MyVitalsClaimProfilePage {
	
	private final WebDriver driver;

    public MyVitalsClaimProfilePage() {
    	driver = DriverManager.getDriver();
    }
    
    @FindBy(css="#claim_first_name")
    private WebElement firstNameTextBox;
    
    @FindBy(css="#claim_last_name")
    private WebElement lastNameTextBox;
    
    @FindBy(css="#new_claim p a")
    private List<WebElement> fillInfoLinks;
    
    @FindBy(css=".form-actions>button")
    private WebElement claimProfileButton;
    
    @FindBy(css=".alert>p")
    private WebElement alertText;
    
    public MyVitalsClaimProfilePage clickClaimExpectFailure() {
    	claimProfileButton.click();
    	return this;
    }

    public boolean isEmptyAlertShown() {
        return alertText.getText().equals("We could not verify your information. You must enter your full date of birth.");
    }
    
    public MyVitalsClaimProfilePage clickFillLinks() {
    	for (WebElement el: fillInfoLinks) {
    		el.click();
    	}
    	return this;
    }
    
    public MyVitalsEditBasicInfoPage clickClaimExpectSuccess() {
    	claimProfileButton.click();
    	return PageFactory.initElements(driver, MyVitalsEditBasicInfoPage.class);
    }
    
    public MyVitalsClaimProfilePage typeFirstName (String text) {
    	firstNameTextBox.clear();
    	firstNameTextBox.sendKeys(text);
    	return this;
    }
    
    public MyVitalsClaimProfilePage typeLastName (String text) {
    	lastNameTextBox.clear();
    	lastNameTextBox.sendKeys(text);
    	return this;
    }
}
