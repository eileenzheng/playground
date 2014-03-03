package com.vitals.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vitals.DriverManager;

public class HomePage {

    private final WebDriver driver;
    public final HeaderPage header;
    public final FooterPage footer;

    public HomePage() {
    	driver = DriverManager.getDriver();
        this.header = PageFactory.initElements(driver, HeaderPage.class);
        this.footer = PageFactory.initElements(driver, FooterPage.class);
    }

    @FindBy(css="#home-photo")
    private WebElement homePhoto;

    public String homePhotoImage() {
        return homePhoto.getAttribute("style");
    }

    public boolean homePhotoisVisible() {
        return homePhoto.isDisplayed();
    }

}
