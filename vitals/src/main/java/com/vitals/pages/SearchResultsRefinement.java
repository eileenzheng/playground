package com.vitals.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.vitals.DriverManager;
import java.util.List;

public class SearchResultsRefinement {

    private WebDriver driver;

    public SearchResultsRefinement () {
    	driver = DriverManager.getDriver();
    }

    @FindBy(css=".filter-reset")
    private WebElement resetFilters;

    @FindBy (css="#distance-1")
    private WebElement withinAMileRadioButton;

    @FindBy (css="#distance-3")
    private WebElement threeMileRadioButton;

    @FindBy (css="#distance-5")
    private WebElement fiveMileRadioButton;

    @FindBy (css="#distance-10")
    private WebElement tenMileRadioButton;

    @FindBy (css="#distance-any")
    private WebElement anyDistanceRadioButton;

    @FindBy (css="#avg-wait-time")
    private WebElement aveWaitTimeDropDown;

    @FindBy (css="#gender-M")
    private WebElement maleRadioButton;

    @FindBy (css=".body>form>.radio:nth-child(12) .facet.extra")
    private WebElement maleCountText;

    @FindBy (css="#gender-F")
    private WebElement femaleRadioButton;

    @FindBy (css=".body>form>.radio:nth-child(13) .facet.extra")
    private WebElement femaleCountText;

    @FindBy (css="#gender-any")
    private WebElement anyGenderRadioButton;

    @FindBy (css="#board-certified")
    private WebElement boardCertifiedCheckBox;

    @FindBy (css=".body>form>.checkbox:nth-child(16) .facet.extra")
    private WebElement boardCertifiedCountText;

    @FindBy (css="#us-educated")
    private WebElement usEducatedCheckBox;

    @FindBy (css=".body>form>.checkbox:nth-child(17) .facet.extra")
    private WebElement usEducatedCountText;

    @FindBy (css="#experience")
    private WebElement yearsExpDropDown;

    @FindBy (css="#language")
    private WebElement otherLanguagesDropDown;

    @FindBy (css=".apple>li")
    private List<WebElement> applesScore;

    @FindBy (css=".cross>li")
    private List<WebElement> hospitalScore;

    @FindBy (css="#top-doctor")
    private WebElement castleConnolyCheckBox;

    @FindBy (css=".body>form>.checkbox:nth-child(26) .facet.extra")
    private WebElement castleConnolyCountText;

    @FindBy (css="#patients-choice")
    private WebElement patientsChoiceCheckBox;

    @FindBy (css=".body>form>.checkbox:nth-child(27) .facet.extra")
    private WebElement patientsChoiceCountText;

    public SearchResultsRefinement clickResetFilters() {
        resetFilters.click();
        return this;
    }

    public SearchResultsRefinement clickWithinAMile() {
        withinAMileRadioButton.click();
        spinnerWait();
        return this;
    }

    public SearchResultsRefinement clickWithinThreeMiles() {
        threeMileRadioButton.click();
        spinnerWait();
        return this;
    }

    public SearchResultsRefinement clickWithinFiveMiles() {
        fiveMileRadioButton.click();
        spinnerWait();
        return this;
    }

    public SearchResultsRefinement clickWithinTenMiles() {
        tenMileRadioButton.click();
        spinnerWait();
        return this;
    }

    public SearchResultsRefinement clickAnyDistance() {
        tenMileRadioButton.click();
        spinnerWait();
        return this;
    }

    public SearchResultsRefinement selectAverageWaitTime(int index) {
        if (index < 0 || index > 3) throw new IllegalArgumentException("Index must be between 0 and 3");

        Select sel = new Select(aveWaitTimeDropDown);
        sel.selectByIndex(index);
        spinnerWait();
        return this;
    }

    public SearchResultsRefinement genderSelectMale() {
        maleRadioButton.click();
        spinnerWait();
        return this;
    }

    public String getMaleDrCount() {
        return maleCountText.getText();
    }

    public SearchResultsRefinement genderSelectFemale() {
        femaleRadioButton.click();
        spinnerWait();
        return this;
    }

    public String getFemaleDrCount() {
        return femaleCountText.getText();
    }

    public SearchResultsRefinement genderSelectAny() {
        anyGenderRadioButton.click();
        spinnerWait();
        return this;
    }

    public SearchResultsRefinement clickBoardCertified() {
        boardCertifiedCheckBox.click();
        spinnerWait();
        return this;
    }

    public String getBoardCertifiedCount() {
        return boardCertifiedCountText.getText();
    }

    public SearchResultsRefinement clickUSEducated() {
        usEducatedCheckBox.click();
        spinnerWait();
        return this;
    }

    public String getUSEducatedCount() {
        return usEducatedCountText.getText();
    }

    public SearchResultsRefinement selectYearsExperience(int index) {
        if (index < 0 || index > 6) throw new IllegalArgumentException("Index must be between 0 and 6");

        Select sel = new Select(yearsExpDropDown);
        sel.selectByIndex(index);
        spinnerWait();
        return this;
    }

    // Language names include counts, this could be difficult

    public SearchResultsRefinement selectLanguage(int index) {

        Select sel = new Select(otherLanguagesDropDown);
        sel.selectByIndex(index);
        spinnerWait();
        return this;
    }

    private void spinnerWait() {
        WebDriverWait wait = new WebDriverWait(driver,15,2000);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#loading")));
        
        // not ideal...but haven't found a better way to solve the problem (page updates after spinner disappears)
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
}
