package com.vitals.pages;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.vitals.DriverManager;

public class UccSearchResultsRefinement {
	
	private final WebDriver driver;
	private final WebDriverWait wait;

    public UccSearchResultsRefinement () {
    	driver = DriverManager.getDriver();
    	wait = new WebDriverWait(driver,15,2000);
    }

    @FindBy (css=".physicals")
    private WebElement filterPhysicals;

    @FindBy (css=".lab")
    private WebElement filterLab;

    @FindBy (css=".diagnostic")
    private WebElement filterDiagnostic;

    @FindBy (css=".injuries")
    private WebElement filterInjuries;

    @FindBy (css=".ailments")
    private WebElement filterAilments;

    @FindBy (css=".preventive")
    private WebElement filterPreventive;

    public UccSearchResultsRefinement clickPhysicals() {
        filterPhysicals.click();
        spinnerWait();
        return this;
    }
    
    public UccSearchResultsRefinement clickLab() {
        filterLab.click();
        spinnerWait();
        return this;
    }
    
    public UccSearchResultsRefinement clickDiagnostic() {
        filterDiagnostic.click();
        spinnerWait();
        return this;
    }
    
    public UccSearchResultsRefinement clickInjuries() {
        filterInjuries.click();
        spinnerWait();
        return this;
    }
    
    public UccSearchResultsRefinement clickAilments() {
        filterAilments.click();
        spinnerWait();
        return this;
    }
    
    public UccSearchResultsRefinement clickPreventive() {
        filterPreventive.click();
        spinnerWait();
        return this;
    }

    private void spinnerWait() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#loading")));

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

}
