package com.vitals.pages;

import com.vitals.helpers.Constants;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import static org.openqa.selenium.By.cssSelector;

public class ReviewPage extends BasePage {

    HeaderModule headerModule;
    FooterModule footerModule;

    public ReviewPage() {
        headerModule = new HeaderModule();
        footerModule = new FooterModule();
    }

    public HeaderModule headerModule(){
        return headerModule;
    }

    public FooterModule footerModule() {
        return footerModule;
    }

    public FluentWebElement searchBox() {
        return input(cssSelector("#search-review .search-main"));
    }

    public FluentWebElements autocompleteCategories() {
        return lis(cssSelector("#ui-id-5 .ui-autocomplete-category"));
    }

    public FluentWebElements doctorNames() {
        return links(cssSelector("#ui-id-5 .provider>a"));
    }

    public FluentWebElements facilityNames() {
        return links(cssSelector("#ui-id-5 .facility>a"));
    }

    public FluentWebElements showAll() {
        return links(cssSelector("#ui-id-5 .show-all>a"));
    }

    public FluentWebElement locationBox() {
        return input(cssSelector("input.search-location"));
    }

    public FluentWebElements locationSuggestions() {
        return links(cssSelector("#ui-id-6 .ui-menu-item>a"));
    }

    public FluentWebElement goButton() {
        return input(cssSelector(".go-btn"));
    }

    public void enterSearchTerm (String text) {
        searchBox().clearField();
        searchBox().sendKeys(text);
        waitUntilVisible(autocompleteCategories().get(0), Constants.SELENIUM_EXPLICIT_WAIT);
    }

    public void enterLocation (String text) {
        locationBox().clearField();
        locationBox().sendKeys(text);
        waitUntilVisible(locationSuggestions().get(0), Constants.SELENIUM_EXPLICIT_WAIT);
    }
}
