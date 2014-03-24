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

    public FluentWebElement findDropDown () {
        return link(cssSelector(".find .type-select .mini-selector"));
    }

    public FluentWebElement findByDoctor() {
        return link(cssSelector("a[data-type='1'])"));
    }

    public FluentWebElement findByDentist() {
        return link(cssSelector("a[data-type='4']"));
    }

    public FluentWebElement findByUcc() {
        return link(cssSelector("a[data-type='10']"));
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
        return links(cssSelector(".categorical-autosuggest .ui-menu-item.parent>a,.ui-menu-item.sub>a"));
    }

    public FluentWebElements conditionSuggestions() {
        return links(cssSelector(".ui-menu-item[data-id]>a"));
    }

    public FluentWebElements nameSuggestions() {
        return links(cssSelector(".ui-menu-item[data-provider-id]>a"));
    }

    public FluentWebElements uccSuggestions() {
        return links(cssSelector(".ui-menu-item[data-facility-id]>a"));
    }

    public FluentWebElement showAlllink() {
        return link(cssSelector(".ui-menu-item:last-child>a"));
    }
    
    public FluentWebElement showAllDoctors() {
        return link(linkText("Show all doctors..."));
    }

    public FluentWebElement showAllFacilities() {
        return link(linkText("Show all facilities..."));
    }
    
    public FluentWebElement locationTextBoxSelector() {
        return link(cssSelector(".location .location-textbox"));
    }

    public FluentWebElement locationTextBox() {
        return input(cssSelector(".location .location-form .ui-autocomplete-input"));
    }

    public FluentWebElements locationSuggestions() {
        return links(cssSelector(".ui-menu-item>a"));
    }

    public FluentWebElement goButton() {
        return button(cssSelector(".go-button>button"));
    }

    public FluentWebElement reviewGoButton() {
        return button(cssSelector(".review button"));
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
        return links(cssSelector(".ui-menu-item>a"));
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
    	
        boolean flag = true;

        for (FluentWebElement el : suggestions) {
            if (!el.getText().toString().toLowerCase().contains(suggestion.toLowerCase()))
                return false;
        }
 
        return flag;
    }

    public String getNameSuggestions() {
        StringBuffer drs = new StringBuffer();

        for (FluentWebElement el : nameSuggestions()) {
            drs.append(el.span().getText().toString() + "\n");
        }

        return drs.toString();
    }

    public String getSpecialtySearchSuggestions() {
    	StringBuffer specs = new StringBuffer();
        
        for (FluentWebElement el : specialtySuggestions()) {
            specs.append(el.getText().toString() + "\n");
        }

        return specs.toString();
    }

    public String getConditionSearchSuggestions() {
        StringBuffer conds = new StringBuffer();

        for (FluentWebElement el : conditionSuggestions()) {
            conds.append(el.getText().toString() + "\n");
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
}
