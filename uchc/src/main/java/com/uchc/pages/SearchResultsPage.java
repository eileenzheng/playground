package com.uchc.pages;

import com.uchc.pages.patientlink.PatientLinkCenterAd;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultsPage extends BasePage {

    PatientLinkCenterAd centerAd;

    public SearchResultsPage() {
        centerAd = new PatientLinkCenterAd();
    }

    public PatientLinkCenterAd centerAd() {
        return centerAd;
    }

    @FindBy(css=".bluebox-featured")
    private WebElement featuredDoctorsSection;

    @FindBy(css=".bluebox-featured>.content")
    private List<WebElement> featuredDoctors;

    @FindBy(css=".providers>li")
    private List<WebElement> drResults;

    @FindBy(css=".blue-bulleted>li>span")
    private WebElement totalResultsText;

    @FindBy(css="#jump_page_id")
    private WebElement pageJumpTextBox;

    @FindBy(css=".tabbercontent>div>form")
    private WebElement lowerPaginationBar;


    public SearchResultsPage clickPreviousButton() {
        List<WebElement> elements = lowerPaginationBar.findElements(By.cssSelector("a"));
        for (WebElement el : elements) {
            if (el.getText().toLowerCase().contains("previous")) {
                el.click();
                break;
            } else {
                // At the first page so button not found
            }
        }
        return this;
    }

    public SearchResultsPage clickNextButton() {
        List<WebElement> elements = lowerPaginationBar.findElements(By.cssSelector("a"));
        for (WebElement el : elements) {
            if (el.getText().toLowerCase().contains("next")) {
                el.click();
                break;
            } else {
                // At the Last page so button not found
            }
        }
        return this;
    }

    public SearchResultsPage jumpToPage(int page) {
        pageJumpTextBox.clear();
        pageJumpTextBox.sendKeys(String.valueOf(page));
        return this;
    }

    public Integer featuredDrCount() {
        return featuredDoctors.size();
    }


}
