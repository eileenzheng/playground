package com.capital.pages;

import org.openqa.selenium.WebDriver;
import org.seleniumhq.selenium.fluent.FluentWebElement;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;

public class SpecialtyTypeSearchPage extends BasePage {

    public SearchSideBar sideBar;

    public SpecialtyTypeSearchPage(WebDriver delegate) {
        super(delegate);
        sideBar = new SearchSideBar(delegate);
    }

    public FluentWebElement providerLocationTextBox() {
        return input(id("provider_location"));
    }

    public FluentWebElement primaryCareCheckBox() {
        return input(id("pcp_only"));
    }

    public FluentWebElement searchButton() {
        return input(cssSelector(".btn.btn-primary"));
    }

    public FluentWebElement alertBox() {
        return div(cssSelector(".alert-box.warning"));
    }

    public SpecialtyTypeSearchPage go(String domain) {
        get(domain + "/search/specialty");
        return this;
    }

    public SpecialtyTypeSearchPage selectProviderType(String type) {
        if (!type.equals("Physician") && !type.equals("Facility")) {
            throw new IllegalArgumentException("Type must be 'physician' or 'facility'");
        }

        select(cssSelector(".selectplan")).selectByVisibleText(type);
        return this;
    }

    public SpecialtyTypeSearchPage enterProviderLocation(String input) {
        providerLocationTextBox().sendKeys(input);
        return this;
    }

    public SpecialtyTypeSearchPage clickPrimaryCareProviders() {
        primaryCareCheckBox().click();
        return this;
    }

    public boolean isPrimaryCareProvidersChecked() {
        return primaryCareCheckBox().isSelected().value();
    }

    public SpecialtyTypeSearchPage selectProviderSpecialty(String input) {
        select(id("specialist_id")).selectByVisibleText(input);
        return this;
    }

    public SpecialtyTypeSearchPage selectPlan(String input) {
        select(id("provider_plan")).selectByVisibleText(input);
        return this;
    }

    public ResultsPage clickSearchButton() {
        searchButton().click();
        return new ResultsPage(delegate);
    }

    public String getErrorText() {
        return alertBox().getText().toString();
    }
}
