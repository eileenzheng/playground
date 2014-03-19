package com.vitals.pages;

import com.vitals.helpers.Constants;
import com.vitals.pages.myvitals.MyVitalsClaimProfilePage;
import com.vitals.pages.patientlink.ModalEmail;
import com.vitals.pages.patientlink.PatientLinkRrAd;
import com.vitalsqa.listener.DriverManager;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BasePage {

    private final WebDriver driver;


    public BasePage() {
    	driver = DriverManager.getDriver();
        driver.manage().window().maximize();

    }

    public WebDriver getDriver() {
        return driver;
    }

}
