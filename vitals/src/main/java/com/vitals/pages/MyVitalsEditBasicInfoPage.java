package com.vitals.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vitals.DriverManager;

public class MyVitalsEditBasicInfoPage {
	
	private WebDriver driver;
	public final HeaderPage header;

    public MyVitalsEditBasicInfoPage() {
    	driver = DriverManager.getDriver();
    	this.header = PageFactory.initElements(driver, HeaderPage.class);
    }
    
    @FindBy(css=".alert>div")
    private WebElement alertText;
    
    public boolean isProfileLinkSuccessAlertShown() {
    	if (alertText.getText().equals("Your profile is now linked."))
    		return true;
    	else
    		return false;
    }

}
