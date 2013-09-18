package com.vitals.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import java.util.ArrayList;
import java.util.List;

public class HeaderPage {

    private WebDriver driver;

    public HeaderPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(css=".home-logo.current>img")
    private WebElement logo;

    @FindBy(css=".options>label:nth-child(2)")
    private WebElement specialtyLink;

    @FindBy(css=".options>label:nth-child(1)")
    private WebElement nameLink;

    @FindBy(css=".options>label:nth-child(3)")
    private WebElement conditionLink;

    @FindBy(css="#q")
    private WebElement searchTextBox;

    @FindBy(css="#specialist_id_dropdown>.ui-autocomplete-input")
    private WebElement specialtySearchTextBox;

    @FindBy(css="#autosuggest-dropdown>.ui-autocomplete>li")
    private List<WebElement> specialtySearchSuggestions;

    @FindBy(css=".ui-menu-item>a")
    private List<WebElement> searchSuggestions;

    @FindBy(css="#location")
    private WebElement locationTextBox;

    @FindBy(css=".ui-menu-item>a")
    private List<WebElement> locationSuggestions;

    @FindBy(css=".text.input.submit-button>input")
    private WebElement searchButton;

    @FindBy(css="#insurance_plan_id")
    private WebElement exclusiveInsuranceCheckbox;

    @FindBy(css=".contain>.left:nth-child(1)")
    private WebElement patientEduLink;

    @FindBy(css=".contain>.left:nth-child(2)")
    private WebElement writeReviewLink;

    @FindBy(css=".right>a:nth-child(2)")
    private WebElement patientSignInLink;

    @FindBy(css=".right>a:nth-child(4)")
    private WebElement doctorSignInLink;

    public HeaderPage enterName(String name) {
        searchTextBox.sendKeys(name);
        return this;
    }

    public HeaderPage enterSpecialty(String spec) {
        specialtySearchTextBox.sendKeys(spec);
        return this;
    }

    public HeaderPage enterLocation(String location) {
        locationTextBox.clear();
        locationTextBox.sendKeys(location);
        return this;
    }

    public SearchResultsPage clickSearch() {
        searchButton.click();
        return PageFactory.initElements(driver,SearchResultsPage.class);
    }

    public boolean checkLocationSuggestions(String suggestion) {
        Boolean flag = false;
        for (WebElement el : locationSuggestions) {
            if (el.getText().contains(suggestion)) flag = true;
            if (flag == true) break;
        }

        return flag;
    }

    public boolean checkSearchSuggestions(String suggestion) {
        Boolean flag = false;
        for (WebElement el : searchSuggestions) {
            if (el.getText().contains(suggestion)) flag = true;
            if (flag == true) break;
        }

        return flag;
    }

    public String getSearchSuggestions() {
        StringBuffer drs = new StringBuffer();

        for (WebElement el : searchSuggestions) {
            if (el.findElements(By.tagName("span")).size() > 0)
                drs.append(el.findElements(By.tagName("span")).get(0).getText().toString() + "\n");
        }

        return drs.toString();
    }

    public HeaderPage clickSpecialtyLink() {
        specialtyLink.click();
        return this;
    }

    public boolean locationSearchIsPopulated() {
        return !locationTextBox.getAttribute("value").isEmpty();
    }

    public String getCurrentPopulatedLocation() {
        return locationTextBox.getAttribute("value").toString();
    }

    public boolean searchBoxIsPopulated() {
        return !searchTextBox.getAttribute("value").isEmpty();
    }

    public String getSubSpecialtySearchSuggestions() {
        StringBuffer specs = new StringBuffer();

        List<WebElement> shortList = new ArrayList<WebElement>();

        for (WebElement el : searchSuggestions) {
            if (!el.getAttribute("class").contains("parent") && !el.getAttribute("class").contains("ui-autocomplete-category")) {
                shortList.add(el);
            }
        }
        //System.out.println("Short list total " + shortList.size());
        for (WebElement el : shortList) {
            specs.append(el.getText().toString() + "\n");
        }

        return specs.toString();
    }

    public HeaderPage clickSubSpec() {
        List<WebElement> shortList = new ArrayList<WebElement>();

        for (WebElement el : searchSuggestions) {
            if (!el.getAttribute("class").contains("parent") && !el.getAttribute("class").contains("ui-autocomplete-category")) {
                shortList.add(el);
            }
        }
        //System.out.println("Short list total " + shortList.size());
        int mid = shortList.size() / 2;
        Reporter.log("Clicking> " + shortList.get(mid).getText().toString());
        shortList.get(mid).click();

        return this;
    }

    public HeaderPage clickConditionLink() {
        conditionLink.click();
        return this;
    }

    public HeaderPage enterCondition(String cond) {
        searchTextBox.sendKeys(cond);
        return this;
    }

    public String getSubConditionSearchSuggestions() {
        StringBuffer specs = new StringBuffer();

        List<WebElement> shortList = new ArrayList<WebElement>();

        for (WebElement el : searchSuggestions) {
            if (!el.getAttribute("class").contains("parent") && !el.getAttribute("class").contains("ui-autocomplete-category")) {
                shortList.add(el);
            }
        }
        //System.out.println("Short list total " + shortList.size());
        for (WebElement el : shortList) {
            specs.append(el.getText().toString() + "\n");
        }

        return specs.toString();
    }
}
