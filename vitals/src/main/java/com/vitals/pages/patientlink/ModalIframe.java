package com.vitals.pages.patientlink;

import com.vitalsqa.listener.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ModalIframe {

    private final WebDriver driver;
    private String mainWindow;

    public ModalIframe() {
        driver = DriverManager.getDriver();
    }

    @FindBy(css=".modal.in .modal-header .close")
    private WebElement close;

    public void switchIframe(String css) {
        driver.switchTo().frame(driver.findElement(By.cssSelector(css)));
    }

    public void switchBack() {
        driver.switchTo().window(mainWindow);
    }

    public void setMainWindow() {
        mainWindow = driver.getWindowHandle();
    }

    public void close() {
        close.click();
    }
}