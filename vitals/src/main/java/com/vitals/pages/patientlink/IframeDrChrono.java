package com.vitals.pages.patientlink;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.vitals.DriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class IframeDrChrono {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public IframeDrChrono() {
        driver = DriverManager.getDriver();
        wait = new WebDriverWait(driver,15,2000);
    }

    @FindBy(css="h3")
    private WebElement name;

    @FindBy(css=".timeslot")
    private List<WebElement> timeSlots;

    @FindBy(css=".btn-next-week")
    private WebElement nextButton;

    @FindBy(css="#id_tbl_schedule_appointment_times")
    private WebElement table;

    public boolean isNameCorrect(String name) {
        return this.name.getText().equals(name);
    }

    public boolean isThereSlot() {
        return timeSlots.size() > 0;
    }

    public IframeDrChrono clickNext() {
        nextButton.click();
        wait.until(ExpectedConditions.visibilityOf(table));
        return PageFactory.initElements(driver, IframeDrChrono.class);
    }
}
