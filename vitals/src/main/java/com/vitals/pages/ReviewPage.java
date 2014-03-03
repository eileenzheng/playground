package com.vitals.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import com.vitals.DriverManager;

public class ReviewPage {
	
    private final WebDriver driver;
    public final HeaderPage header;
    
    public ReviewPage() {
    	driver = DriverManager.getDriver();
        this.header = PageFactory.initElements(driver, HeaderPage.class);
    }
}
