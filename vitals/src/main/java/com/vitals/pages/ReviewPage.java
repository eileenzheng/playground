package com.vitals.pages;

import com.vitalsqa.listener.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ReviewPage {
	
    private final WebDriver driver;
    public final HeaderPage header;
    
    public ReviewPage() {
    	driver = DriverManager.getDriver();
        this.header = PageFactory.initElements(driver, HeaderPage.class);
    }
}
