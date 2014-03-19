package com.vitals.pages.myvitals;

import com.vitalsqa.listener.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyVitalsProfessionalsPage {
	
	private final WebDriver driver;

    public MyVitalsProfessionalsPage() {
    	driver = DriverManager.getDriver();
    }
    
    @FindBy(css=".button-discreet")
    private WebElement deleteButton;
    
    public MyVitalsLocateProfilePage clickDeleteButton() {
    	deleteButton.click();
    	driver.switchTo().alert().accept();
    	return PageFactory.initElements(driver, MyVitalsLocateProfilePage.class);
    }

}
