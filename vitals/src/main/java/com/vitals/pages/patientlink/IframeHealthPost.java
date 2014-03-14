package com.vitals.pages.patientlink;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.vitals.DriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class IframeHealthPost {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public IframeHealthPost() {
        driver = DriverManager.getDriver();
        wait = new WebDriverWait(driver,15,2000);
    }

    @FindBy(css=".slot")
    private List<WebElement> timeSlots;

    @FindBy(css=".time_slots_navigation .next_link")
    private WebElement nextButton;

    @FindBy(css=".day_of_week")
    private List<WebElement> week;

    public boolean isThereSlot() {
        if (timeSlots.size()>0)
            return true;
        else
            return false;
    }

    public IframeHealthPost clickNext() {
        nextButton.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(week));
        return PageFactory.initElements(driver, IframeHealthPost.class);
    }
}
