package com.vitals.pages.myvitals;

import com.vitals.pages.HeaderPage;
import com.vitalsqa.listener.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyVitalsEditBasicInfoPage {
	
	private final WebDriver driver;
	public final HeaderPage header;

    public MyVitalsEditBasicInfoPage() {
    	driver = DriverManager.getDriver();
    	this.header = PageFactory.initElements(driver, HeaderPage.class);
    }
    
    @FindBy(css=".alert>div")
    private WebElement alertText;
    
    public boolean isProfileLinkSuccessAlertShown() {
        return alertText.getText().equals("Your profile is now linked.");
    }

}
