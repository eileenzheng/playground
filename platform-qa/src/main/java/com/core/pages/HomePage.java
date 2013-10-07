package com.core.pages;

import com.core.helpers.Constants;
import org.seleniumhq.selenium.fluent.FluentWebElement;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;

public class HomePage extends BasePage {

    public HomePage() {
    }

    public void go() {
        get(Constants.TEST_SITE);

    }

    public void clickDemoFacilitySearch() {
        link(cssSelector(".panel-body>ul>li:nth-child(1)>a")).click();
    }

    public void clickDemoFacilityProfile() {
        link(cssSelector(".panel-body>ul>li:nth-child(2)>a")).click();
    }

    public FluentWebElement imLookingForADoctor() {
        return div(id("placeholder"));
    }

    public FluentWebElement imLookingForAFacility() {
        return div(id("placeholder"));
    }

    public FluentWebElement imLookingForAProcedureCost() {
        return div(id("placeholder"));
    }

    public FluentWebElement imLookingForAPrescriptionCost() {
        return div(id("placeholder"));
    }

    public FluentWebElement searchBar() {
        return div(id("placeholder"));
    }

    public FluentWebElement searchButton() {
        return div(id("placeholder"));
    }

}
