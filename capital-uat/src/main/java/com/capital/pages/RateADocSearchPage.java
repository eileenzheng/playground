package com.capital.pages;

import org.seleniumhq.selenium.fluent.FluentWebElement;

import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.name;
import static org.openqa.selenium.By.cssSelector;

public class RateADocSearchPage extends BasePage {

    protected SearchSideBar sideBar;

    public RateADocSearchPage() {
        sideBar = new SearchSideBar();
    }

    private FluentWebElement providerNameTextBox() {
        return input(name("criteria[provider-name]"));
    }

    private FluentWebElement providerLocationTextBox() {
        return input(id("provider_location"));
    }

    private FluentWebElement planDropDown() {
        return select(id("provider_plan"));
    }

    private FluentWebElement searchButton() {
        return input(cssSelector(".btn.btn-primary"));
    }

    private FluentWebElement searchHeaderText() {
        return h2(cssSelector(".panel>h2"));
    }

    private FluentWebElement alertBox() {
        return div(cssSelector(".alert-box.warning"));
    }

    public RateADocSearchPage go(String domain) {
        get(domain + "/search/rating");
        return this;
    }

    public RateADocSearchPage enterProviderName(String input) {
        providerNameTextBox().sendKeys(input);
        return this;
    }

    public RateADocSearchPage enterProviderLocation(String input) {
        providerLocationTextBox().sendKeys(input);
        return this;
    }

    public RateADocSearchPage selectPlan(String input) {
        select(id("provider_plan")).selectByVisibleText(input);
        return this;
    }

    public ResultsPage clickSearchButton() {
        searchButton().click();
        return new ResultsPage();
    }

    public String getHeaderText() {
        return searchHeaderText().getText().toString();
    }

    public String getErrorText() {
        return alertBox().getText().toString();
    }
}
