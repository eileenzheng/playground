package com.vitals.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SEOProfilePage {

    private WebDriver driver;
    public final HeaderPage header;
    public final FooterPage footer;

    public SEOProfilePage(WebDriver driver) {
        this.driver = driver;
        this.header = PageFactory.initElements(driver,HeaderPage.class);
        this.footer = PageFactory.initElements(driver,FooterPage.class);
    }

    @FindBy(css="#view-full")
    private WebElement viewFullProfileButton;

    public boolean viewFullProfileButtonIsVisible() {
        return viewFullProfileButton.isDisplayed();
    }


}
