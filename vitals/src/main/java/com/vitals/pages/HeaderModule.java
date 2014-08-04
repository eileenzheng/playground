package com.vitals.pages;

import com.vitals.helpers.Constants;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import com.vitals.helpers.Constants.SearchType;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.linkText;

public class HeaderModule extends BasePage {

    public FluentWebElement logo() {
        return link(cssSelector(".masthead-logo>a"));
    }

    public FluentWebElement searchTextBox() {
        return input(cssSelector("#q"));
    }

    public FluentWebElement reviewSearchTextBox() {
        return input(cssSelector("#review_q"));
    }

    public FluentWebElements autocompleteCategories() {
        return lis(cssSelector(".ui-autocomplete-category"));
    }

    public FluentWebElements specialtySuggestions() {
        return links(cssSelector("#ui-id-5 .ui-menu-item:not([data-facility-id]):not([data-provider-id]):not([data-id])>a:not(.link)"));
    }

    public FluentWebElements conditionSuggestions() {
        return links(cssSelector("#ui-id-5 .ui-menu-item[data-id]>a"));
    }

    public FluentWebElements nameSuggestions() {
        return links(cssSelector("#ui-id-5 .ui-menu-item[data-provider-id]>a"));
    }

    public FluentWebElements reviewNameSuggestions() {
        return links(cssSelector("#ui-id-6 .ui-menu-item[data-provider-id]>a"));
    }

    public FluentWebElements uccSuggestions() {
        return links(cssSelector("#ui-id-5 .ui-menu-item[data-facility-id]>a"));
    }

    public FluentWebElements reviewUccSuggestions() {
        return links(cssSelector("#ui-id-6 .ui-menu-item[data-facility-id]>a"));
    }
    
    public FluentWebElement showAllDoctors() {
        return link(linkText("Show all doctors..."));
    }

    public FluentWebElement showAllDentists() {
        return link(linkText("Show all dentists..."));
    }

    public FluentWebElement showAllFacilities() {
        return link(linkText("Show all facilities..."));
    }

    public FluentWebElement locationTextBox() {
        return input(cssSelector("#doctor-tab .location .location-form .ui-autocomplete-input"));
    }

    public FluentWebElement locationTextBoxReview() {
        return input(cssSelector("#review-tab .location .location-form .ui-autocomplete-input"));
    }

    public FluentWebElements locationSuggestions() {
        return links(cssSelector("#ui-id-1 .ui-menu-item>a"));
    }

    public FluentWebElements locationSuggestionsReview() {
        return links(cssSelector("#ui-id-2 .ui-menu-item>a"));
    }

    public FluentWebElement goButton() {
        return button(cssSelector(".doctor button"));
    }

    public FluentWebElement reviewGoButton() {
        return button(cssSelector("#review-go"));
    }

    public FluentWebElement urgentCareLink() {
        return link(cssSelector(".session-info li>a:nth-child(2)"));
    }

    public FluentWebElement signUpLink() {
        return link(cssSelector(".session-info li>a:nth-child(1)"));
    }

    public FluentWebElement signInLink() {
        return link(cssSelector(".session-info li>a:nth-child(3)"));
    }

    public FluentWebElement signedInEmailLink() {
        return link(cssSelector(".when-signed-in .email"));
    }
    
    public FluentWebElement editProfileLink() {
        return link(cssSelector(".edit-profile"));
    }

    public FluentWebElement signOutLink() {
        return link(cssSelector(".sign-out"));
    }

    public FluentWebElement getInfoTab() {
        return link(cssSelector(".nav-tab.get-info"));
    }

    public FluentWebElement writeReviewTab() {
        return link(cssSelector(".nav-tab.write-review"));
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
        return links(cssSelector("#ui-id-3 .ui-menu-item>a"));
    }

    public FluentWebElement insurancePlanDropDown() {
        return input(cssSelector(".insurance_plan"));
    }

    public FluentWebElements insurancePlanSuggestions() {
        return links(cssSelector(".plan.ui-menu-item>a"));
    }

    public FluentWebElement saveInsuranceButton() {
        return link(cssSelector(".save_insurance"));
    }

    public void enterSearchTerm (String text) {
    	searchTextBox().clearField();
        searchTextBox().sendKeys(text);
        waitUntilVisible(autocompleteCategories().get(0), Constants.SELENIUM_EXPLICIT_WAIT);
    }
    
    public void enterReviewSearchTerm (String text) {
    	reviewSearchTextBox().clearField();
        reviewSearchTextBox().sendKeys(text);
        waitUntilVisible(autocompleteCategories().get(0), Constants.SELENIUM_EXPLICIT_WAIT);
    }
    
    public void enterInsuranceCompany(String text) {
        waitUntilVisible(insuranceBox(), Constants.SELENIUM_EXPLICIT_WAIT);
    	insuranceCompanyTextBox().clearField();
    	insuranceCompanyTextBox().sendKeys(text);
    	waitUntilVisible(insuranceSuggestions().get(0), Constants.SELENIUM_EXPLICIT_WAIT);
    }

    public boolean checkSuggestions(SearchType type, String suggestion) {
    	FluentWebElements suggestions = null;

    	if (type.equals(SearchType.NAME))
    		suggestions = nameSuggestions();
        else if (type.equals(SearchType.SPECIALTY))
    		suggestions = specialtySuggestions();
        else if (type.equals(SearchType.CONDITION))
    		suggestions = conditionSuggestions();
        else if (type.equals(SearchType.UCC))
    		suggestions = uccSuggestions();
        else if (type.equals(SearchType.LOCATION))
    		suggestions = locationSuggestions();

        for (FluentWebElement el : suggestions) {
            if (!el.getText().toString().toLowerCase().contains(suggestion.toLowerCase()))
                return false;
        }
 
        return true;
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
        insurancePlanDropDown().click();
        if (insurancePlanSuggestions().size()==0) // workaround to random failing
            openInsurancePlan();
        waitUntilVisible(insurancePlanSuggestions().get(0), Constants.SELENIUM_EXPLICIT_WAIT);
    }
}
