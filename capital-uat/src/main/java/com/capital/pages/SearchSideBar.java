package com.capital.pages;

import org.seleniumhq.selenium.fluent.FluentWebElement;

import static org.openqa.selenium.By.cssSelector;

public class SearchSideBar extends BasePage {

    public SearchSideBar () {
    }

    private FluentWebElement searchByNameButton() {
        return link(cssSelector(".vertical.tabs>dd:nth-child(1)>a"));
    }

    private FluentWebElement searchByTypeSpecButton() {
        return link(cssSelector(".vertical.tabs>dd:nth-child(2)>a"));
    }

    private FluentWebElement estimateTreamentButton() {
        return link(cssSelector(".vertical.tabs>dd:nth-child(3)>a"));
    }

    private FluentWebElement intlProviderButton() {
        return link(cssSelector(".vertical.tabs>dd:nth-child(4)>a"));
    }

    private FluentWebElement rateADocButton() {
        return link(cssSelector(".vertical.tabs>dd:nth-child(5)>a"));
    }

    public SearchSideBar clickSearchByName() {
        searchByNameButton().click();
        return this;
    }

    public SearchSideBar clickSearchByTypeorSpecialty() {
        searchByTypeSpecButton().click();
        return this;
    }

    public SearchSideBar clickEstimateTreatmentCosts() {
        estimateTreamentButton().click();
        return this;
    }

    public SearchSideBar clickIntlProviders() {
        intlProviderButton().click();
        return this;
    }

    public SearchSideBar clickRateADoc() {
        rateADocButton().click();
        return this;
    }
}
