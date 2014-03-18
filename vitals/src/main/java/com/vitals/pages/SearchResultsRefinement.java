package com.vitals.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.vitals.DriverManager;

public class SearchResultsRefinement {

    private final WebDriver driver;

    public SearchResultsRefinement () {
    	driver = DriverManager.getDriver();
    }

    @FindBy(css=".reset")
    private WebElement resetFilters;
    
    @FindBy(css=".dropdown-toggle[data-id='sort']")
    private WebElement sortDropDown;
    
    @FindBy (css=".panel-group>.btn-group:nth-of-type(1) .open li:first-child>a")
    private WebElement sortByRelevancy;
    
    @FindBy (css=".panel-group>.btn-group:nth-of-type(1) .open li:nth-last-child(3)>a")
    private WebElement sortByDistance;
    
    @FindBy (css=".panel-group>.btn-group:nth-of-type(1) .open li:nth-last-child(2)>a")
    private WebElement sortByRating;
    
    @FindBy (css=".panel-group>.btn-group:nth-of-type(1) .open li:last-child>a")
    private WebElement sortByName;

    @FindBy(css=".dropdown-toggle[data-id='distance']")
    private WebElement distanceDropDown;
    
    @FindBy (css=".btn-group:nth-of-type(2) .open li[rel='0']>a")
    private WebElement distance5;

    @FindBy (css=".btn-group:nth-of-type(2) .open li[rel='1']>a")
    private WebElement distance10;
    
    @FindBy (css=".btn-group:nth-of-type(2) .open li[rel='2']>a")
    private WebElement distance15;
    
    @FindBy (css=".btn-group:nth-of-type(2) .open li[rel='3']>a")
    private WebElement distance25;
    
    @FindBy (css=".btn-group:nth-of-type(2) .open li[rel='4']>a")
    private WebElement distance50;

    @FindBy (css=".btn-group:nth-of-type(2) .open li[rel='5']>a")
    private WebElement distance100;
    
    @FindBy (css=".panel:nth-last-of-type(2) .toggle-plus-minus")
    private WebElement toggleFilter;
    
    @FindBy (css=".dropdown-toggle[data-id='gender-select']")
    private WebElement genderDropDown;

    @FindBy (css=".panel:nth-last-of-type(2) .form-group:nth-of-type(1) .open li[rel='1']>a")
    private WebElement genderMale;

    @FindBy (css=".panel:nth-last-of-type(2) .form-group:nth-of-type(1) .open li[rel='1']>a")
    private WebElement genderFemale;

    @FindBy (css="#board-certified")
    private WebElement boardCertifiedCheckBox;

    @FindBy (css="#us-educated")
    private WebElement usEducatedCheckBox;

    public SearchResultsRefinement clickResetFilters() {
        resetFilters.click();
        return this;
    }

    public SearchResultsRefinement openSortDropDown() {
        sortDropDown.click();
        return this;
    }

    public SearchResultsRefinement clickSortByDistance() {
        sortByDistance.click();
        waitForJQuery();
        return this;
    }

    public SearchResultsRefinement clickSortByName() {
        sortByName.click();
        waitForJQuery();
        return this;
    }

    public SearchResultsRefinement clickSortByRating() {
        sortByRating.click();
        waitForJQuery();
        return this;
    }

    public SearchResultsRefinement openDistanceDropDown() {
    	distanceDropDown.click();
    	return this;
    }

    public SearchResultsRefinement clickWithin5Miles() {
        distance5.click();
        waitForJQuery();
        return this;
    }

    public SearchResultsRefinement clickWithin10Miles() {
        distance10.click();
        waitForJQuery();
        return this;
    }
    
    public SearchResultsRefinement clickWithin15Miles() {
        distance15.click();
        waitForJQuery();
        return this;
    }
    
    public SearchResultsRefinement clickWithin25Miles() {
        distance25.click();
        waitForJQuery();
        return this;
    }
    
    public SearchResultsRefinement clickWithin50Miles() {
        distance50.click();
        waitForJQuery();
        return this;
    }

    public SearchResultsRefinement clickAnyDistance() {
        distance100.click();
        waitForJQuery();
        return this;
    }

    public SearchResultsRefinement clickToggleFilter() {
        toggleFilter.click();
        return this;
    }
    
    public SearchResultsRefinement openGenderDropDown() {
    	genderDropDown.click();
    	return this;
    }
    
    public SearchResultsRefinement genderSelectMale() {
        genderMale.click();
        waitForJQuery();
        return this;
    }

    public SearchResultsRefinement genderSelectFemale() {
        genderFemale.click();
        waitForJQuery();
        return this;
    }

    public SearchResultsRefinement clickBoardCertified() {
        boardCertifiedCheckBox.click();
        waitForJQuery();
        return this;
    }

    public SearchResultsRefinement clickUSEducated() {
        usEducatedCheckBox.click();
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
