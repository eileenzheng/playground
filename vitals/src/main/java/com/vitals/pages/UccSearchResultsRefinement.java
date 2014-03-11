package com.vitals.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.vitals.DriverManager;
import com.vitals.helpers.Constants;

public class UccSearchResultsRefinement {
	
	private final WebDriver driver;
	private final WebDriverWait wait;

    public UccSearchResultsRefinement () {
    	driver = DriverManager.getDriver();
    	wait = new WebDriverWait(driver,15,2000);
    }
    
    @FindBy (css=".toggle-plus-minus")
    private WebElement toggleServices;

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
    
    @FindBy (css=".service-filters.in")
    private List<WebElement> filterOpened;
    
    public UccSearchResultsRefinement clickToggleServices() throws InterruptedException {
    	driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    	if (filterOpened.size()==0) {
    		toggleServices.click();
    		while (filterOpened.size()!=1) {
    			Thread.sleep(100); // wait for filter to be opened
    		}
    	}
    	else
    		toggleServices.click();
    	driver.manage().timeouts().implicitlyWait(Constants.SELENIUM_IMPLICIT_WAIT, TimeUnit.SECONDS);
    	return this;
    }

    public UccSearchResultsRefinement clickPhysicals() {
    	wait.until(ExpectedConditions.visibilityOf(filterPhysicals));
        filterPhysicals.click();
        waitForJQuery();
        return this;
    }
    
    public UccSearchResultsRefinement clickLab() {
    	wait.until(ExpectedConditions.visibilityOf(filterLab));
        filterLab.click();
        waitForJQuery();
        return this;
    }
    
    public UccSearchResultsRefinement clickDiagnostic() {
    	wait.until(ExpectedConditions.visibilityOf(filterDiagnostic));
        filterDiagnostic.click();
        waitForJQuery();
        return this;
    }
    
    public UccSearchResultsRefinement clickInjuries() {
    	wait.until(ExpectedConditions.visibilityOf(filterInjuries));
        filterInjuries.click();
        waitForJQuery();
        return this;
    }
    
    public UccSearchResultsRefinement clickAilments() {
    	wait.until(ExpectedConditions.visibilityOf(filterAilments));
        filterAilments.click();
        waitForJQuery();
        return this;
    }
    
    public UccSearchResultsRefinement clickPreventive() {
    	wait.until(ExpectedConditions.visibilityOf(filterPreventive));
        filterPreventive.click();
        waitForJQuery();
        return this;
    }

    private void waitForJQuery() {
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#loading")));
    }
}
