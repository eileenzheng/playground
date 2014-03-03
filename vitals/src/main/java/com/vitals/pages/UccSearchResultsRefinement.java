package com.vitals.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.vitals.DriverManager;

public class UccSearchResultsRefinement {
	
	private final WebDriver driver;

    public UccSearchResultsRefinement () {
    	driver = DriverManager.getDriver();
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
        waitForJQuery();
        return this;
    }
    
    public UccSearchResultsRefinement clickLab() {
        filterLab.click();
        waitForJQuery();
        return this;
    }
    
    public UccSearchResultsRefinement clickDiagnostic() {
        filterDiagnostic.click();
        waitForJQuery();
        return this;
    }
    
    public UccSearchResultsRefinement clickInjuries() {
        filterInjuries.click();
        waitForJQuery();
        return this;
    }
    
    public UccSearchResultsRefinement clickAilments() {
        filterAilments.click();
        waitForJQuery();
        return this;
    }
    
    public UccSearchResultsRefinement clickPreventive() {
        filterPreventive.click();
        waitForJQuery();
        return this;
    }

    private void waitForJQuery() {
		if ((Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active==1")) {
			do {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// do nothing
				}
			} while ((Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active==1"));
			return;
		}
		else {
			do {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e2) {
					// do nothing
				}
			} while ((Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active==0"));
			waitForJQuery();
		}
    }

}
