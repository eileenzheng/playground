package com.vitals.pages;

import com.vitals.pages.patientlink.PatientLinkRrAd;
import com.vitalsqa.listener.DriverManager;
import org.bouncycastle.jcajce.provider.symmetric.ARC4;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfileSeoPage extends BasePage {

    private final WebDriver driver;
    public final HeaderPage header;
    public final FooterPage footer;
    public final PatientLinkRrAd rrAd;

    public ProfileSeoPage() {
    	driver = getDriver();
        header = PageFactory.initElements(driver,HeaderPage.class);
        footer = PageFactory.initElements(driver,FooterPage.class);
        rrAd = PageFactory.initElements(driver, PatientLinkRrAd.class);
    }

    @FindBy(css="#view-full")
    private WebElement viewFullProfileButton;

    public boolean viewFullProfileButtonIsVisible() {
    	return viewFullProfileButton.isDisplayed();
    }


}
