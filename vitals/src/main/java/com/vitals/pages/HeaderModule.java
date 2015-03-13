package com.vitals.pages;

import com.vitals.helpers.Constants;
import org.openqa.selenium.WebElement;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import static org.openqa.selenium.By.cssSelector;

public class HeaderModule extends BasePage {

    // ***** shared by mobile & desktop *****

    public FluentWebElement logo() {
        return link(cssSelector(".masthead-logo>a"));
    }

    public FluentWebElement searchTextBox() {
        return input(cssSelector("#q"));
    }

    public FluentWebElements autocompleteCategories() {
        return lis(cssSelector("#ui-id-4 .ui-autocomplete-category"));
    }

    public FluentWebElements specialtySuggestions() {
        return links(cssSelector(".specialty>a"));
    }

    public FluentWebElements conditionSuggestions() {
        return links(cssSelector(".disorder>a"));
    }

    public FluentWebElements nameSuggestions() {
        return links(cssSelector("#ui-id-4 .provider>a"));
    }

    public FluentWebElements uccSuggestions() {
        return links(cssSelector("#ui-id-4 .facility>a"));
    }

    public FluentWebElement locationTextBox() {
        return input(cssSelector("#location_text"));
    }

    public FluentWebElements locationSuggestions() {
        return links(cssSelector("#ui-id-1 .ui-menu-item>a"));
    }

    public FluentWebElement goButton() {
        return button(cssSelector("#provider-go"));
    }

    public FluentWebElement insurance() {
        return link(cssSelector(".mini-selector.my-any-insurance"));
    }

    public FluentWebElement insuranceBox() {
        return div(cssSelector(".insurance_box"));
    }

    public FluentWebElement insuranceCompanyTextBox() {
        return input(cssSelector(".insurance_company.ui-autocomplete-input"));
    }

    public FluentWebElements insuranceSuggestions() {
        return links(cssSelector("#ui-id-2 .ui-menu-item>a"));
    }

    public FluentWebElement insurancePlanDropDown() {
        return input(cssSelector(".insurance_plan"));
    }

    public FluentWebElements insurancePlanSuggestions() {
        return links(cssSelector(".plan.ui-menu-item>a"));
    }

    // ***** desktop only *****

    public FluentWebElements showAll() {
        return links(cssSelector("#ui-id-4 .show-all>a"));
    }

    public FluentWebElement signUpLink() {
        return link(cssSelector(".session-info .sign-in"));
    }

    public FluentWebElement signInLink() {
        return link(cssSelector(".session-info .sign-up"));
    }

    public FluentWebElement signedInEmailLink() {
        return link(cssSelector(".when-signed-in .email"));
    }

    public FluentWebElement editProfileLink() {
        return link(cssSelector(".when-signed-in .edit-profile"));
    }

    public FluentWebElement signOutLink() {
        return link(cssSelector(".when-signed-in .sign-out"));
    }

    public FluentWebElement divMainHeader() {
        return div(cssSelector(".masthead-container"));
    }

    // ***** mobile only *****

    public FluentWebElement mobHamburger() {
        return span(cssSelector(".nav-handle"));
    }

    public WebElement mobCloseHamburger() {
        return webDriver().findElement(cssSelector(".floating-menu-button .icon-x-bold"));
    }

    public WebElement mobSearchMenu() {
        return webDriver().findElement(cssSelector(".icon-magnify-left"));
    }

    public WebElement mobCloseSearch () {
        return webDriver().findElement(cssSelector(".search-wrapper-icon .icon-x-bold"));
    }

    public FluentWebElement mobBrowseSpecialties() {
        return button(cssSelector("button[data-target='#browseSpecialties']"));
    }

    public FluentWebElement mobPatientEducation() {
        return button(cssSelector("button[data-target='#patientEducation']"));
    }

    public FluentWebElements mobSpecialtyList() {
        return lis(cssSelector("#browseSpecialties li"));
    }

    // ***** functions *****

    public void enterSearchTerm (String text) {
    	searchTextBox().clearField();
        searchTextBox().sendKeys(text);
        waitUntilVisible(autocompleteCategories().get(0), Constants.SELENIUM_EXPLICIT_WAIT);
    }
    
    public void enterInsuranceCompany(String text) {
        waitUntilVisible(insuranceBox(), Constants.SELENIUM_EXPLICIT_WAIT);
    	insuranceCompanyTextBox().clearField();
    	insuranceCompanyTextBox().sendKeys(text);
    	waitUntilVisible(insuranceSuggestions().get(0), Constants.SELENIUM_EXPLICIT_WAIT);
    }

    public String getNameSuggestions() {
        StringBuilder drs = new StringBuilder();

        for (FluentWebElement el : nameSuggestions()) {
            drs.append(el.span().getText().toString()).append("\n");
        }

        return drs.toString();
    }

    public String getSpecialtySearchSuggestions() {
    	StringBuilder specs = new StringBuilder();
        
        for (FluentWebElement el : specialtySuggestions()) {
            specs.append(el.getText().toString()).append("\n");
        }

        return specs.toString();
    }

    public String getConditionSearchSuggestions() {
        StringBuilder conds = new StringBuilder();

        for (FluentWebElement el : conditionSuggestions()) {
            conds.append(el.getText().toString()).append("\n");
        }

        return conds.toString();
    }

    public void enterLocation(String location) {
    	locationTextBox().clearField();
    	locationTextBox().sendKeys(location);
    	waitUntilVisible(locationSuggestions().get(0), Constants.SELENIUM_EXPLICIT_WAIT);
    }

    public boolean locationSearchIsPopulated() {
        return (!locationTextBox().getAttribute("value").toString().equals("") && !locationTextBox().getAttribute("value").toString().equals(","));
    }

    public void openInsurancePlan() {
        int count=0;
        insurancePlanDropDown().click();
        if (insurancePlanSuggestions().size()==0 && count<5) { // workaround to random failing
            insurancePlanDropDown().click();
            waitUntilVisible(insurancePlanSuggestions().get(0), Constants.SELENIUM_EXPLICIT_WAIT);
            count++;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // do nothing
            }
        }
    }
}
