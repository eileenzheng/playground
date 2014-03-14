package com.vitals.pages.patientlink;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.vitals.DriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class IframeDocAsap {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public IframeDocAsap() {
        driver = DriverManager.getDriver();
        wait = new WebDriverWait(driver,15,2000);
    }

    @FindBy(css=".title_blue_small a")
    private WebElement name;

    @FindBy(css="a[title='Click time to book the appointment']")
    private List<WebElement> timeSlots;

    @FindBy(css=".buttons .old-cal-next")
    private WebElement nextButton;

    @FindBy(css=".calendar")
    private WebElement calendar;

    public boolean isNameCorrect(String name) {
        if (this.name.getText().equals(name))
            return true;
        else
            return false;
    }

    public boolean isThereSlot() {
        if (timeSlots.size()>0)
            return true;
        else
            return false;
    }

    public IframeDocAsap clickNext() {
        nextButton.click();
        wait.until(ExpectedConditions.visibilityOf(calendar));
        return PageFactory.initElements(driver, IframeDocAsap.class);
    }
}
