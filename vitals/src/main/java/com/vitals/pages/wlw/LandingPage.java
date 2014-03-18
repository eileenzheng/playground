package com.vitals.pages.wlw;

import com.vitals.DriverManager;
import com.vitals.pages.wlw.HeaderPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

    private final WebDriver driver;
    public final HeaderPage header;

    public LandingPage() {
        driver = DriverManager.getDriver();
        header = PageFactory.initElements(driver,HeaderPage.class);
    }

    @FindBy(css=".contentHeader")
    private WebElement heading;

    @FindBy(css="#specialist_id")
    private WebElement specialtyDropDown;

    @FindBy(css="#location")
    private WebElement locationTextBox;

    @FindBy(css="input[type='image']")
    private WebElement searchButton;

    public LandingPage openDropDown(){
        specialtyDropDown.click();
        return PageFactory.initElements(driver,LandingPage.class);
    }

    public LandingPage typeInDropDown(String text){
        specialtyDropDown.sendKeys(text);
        return PageFactory.initElements(driver,LandingPage.class);
    }

    public LandingPage enterZipCode(String text){
        locationTextBox.clear();
        locationTextBox.sendKeys(text);
        return PageFactory.initElements(driver,LandingPage.class);
    }

    public SearchPage clickSearch(){
        searchButton.click();
        return PageFactory.initElements(driver,SearchPage.class);
    }
}