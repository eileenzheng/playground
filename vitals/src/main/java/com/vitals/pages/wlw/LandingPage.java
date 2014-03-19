package com.vitals.pages.wlw;

import com.vitalsqa.listener.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

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

    @FindBy(css="#specialist_id option")
    private List<WebElement> options;

    @FindBy(css="#location")
    private WebElement locationTextBox;

    @FindBy(css="input[type='image']")
    private WebElement searchButton;

    public LandingPage selectOption(String text){
        Select drop = new Select(specialtyDropDown);
        drop.selectByVisibleText(text);
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