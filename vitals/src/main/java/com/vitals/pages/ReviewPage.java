package com.vitals.pages;

import com.vitals.helpers.Constants;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.linkText;

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
        return lis(cssSelector("ui-autocomplete-category"));
    }

    public FluentWebElements doctorNames() {
        return links(cssSelector("li[data-provider-id]>a"));
    }

    public FluentWebElements facilityNames() {
        return links(cssSelector("li[data-facility-id]>a"));
    }

    public FluentWebElements doctorReviewButtons() {
        return spans(cssSelector("li[data-provider-id] span.btn"));
    }

    public FluentWebElements facilityReviewButtons() {
        return buttons(cssSelector("li[data-facility-id] button"));
    }

    public FluentWebElement showAllDoctors() {
        return link(linkText("Show all doctors..."));
    }

    public FluentWebElement showAllFacilities() {
        return link(linkText("Show all facilities..."));
    }

    public FluentWebElement goButton() {
        return input(cssSelector(".go-btn"));
    }

    public void enterSearchTerm (String text) {
        searchBox().clearField();
        searchBox().sendKeys(text);
        waitUntilVisible(autocompleteCategories().get(0), Constants.SELENIUM_EXPLICIT_WAIT);
    }
}
