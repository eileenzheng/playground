package com.vitals.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vitals.DriverManager;

public class SEOProfilePage {

    private WebDriver driver;
    public final HeaderPage header;
    public final FooterPage footer;
    public final PatientLinkRrAd rrAd;

    public SEOProfilePage() {
    	driver = DriverManager.getDriver();
        this.header = PageFactory.initElements(driver,HeaderPage.class);
        this.footer = PageFactory.initElements(driver,FooterPage.class);
        this.rrAd = PageFactory.initElements(driver, PatientLinkRrAd.class);
    }

    @FindBy(css="#view-full")
    private WebElement viewFullProfileButton;

    public boolean viewFullProfileButtonIsVisible() {
        return viewFullProfileButton.isDisplayed();
    }


}
