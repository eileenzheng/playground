package com.vitals.pages;

import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;

import static org.openqa.selenium.By.cssSelector;

public class SearchResultsRefinement extends BasePage{

    public FluentWebElement resetFilters() {
        return link(cssSelector(".reset"));

    }

    public FluentWebElement sortDropDown() {
        return button(cssSelector(".dropdown-toggle[data-id='sort']"));
    }

    public FluentWebElement sortByRelevancy() {
        return link(cssSelector(".panel-group>.btn-group:nth-of-type(1) .open li:first-child>a"));
    }

    public FluentWebElement sortByDistance() {
        return link(cssSelector(".panel-group>.btn-group:nth-of-type(1) .open li:nth-last-child(3)>a"));
    }

    public FluentWebElement sortByRating() {
        return link(cssSelector(".panel-group>.btn-group:nth-of-type(1) .open li:nth-last-child(2)>a"));
    }

    public FluentWebElement sortByName() {
        return link(cssSelector(".panel-group>.btn-group:nth-of-type(1) .open li:last-child>a"));
    }

    public FluentWebElement distanceDropDown() {
        return button(cssSelector(".dropdown-toggle[data-id='distance']"));
    }

    public FluentWebElement distance5() {
        return link(cssSelector(".btn-group:nth-of-type(2) .open li[rel='0']>a"));
    }

    public FluentWebElement distance10() {
        return link(cssSelector(".btn-group:nth-of-type(2) .open li[rel='1']>a"));
    }

    public FluentWebElement distance15() {
        return link(cssSelector(".btn-group:nth-of-type(2) .open li[rel='2']>a"));
    }

    public FluentWebElement distance25() {
        return link(cssSelector(".btn-group:nth-of-type(2) .open li[rel='3']>a"));
    }

    public FluentWebElement distance50() {
        return link(cssSelector(".btn-group:nth-of-type(2) .open li[rel='4']>a"));
    }

    public FluentWebElement distance100() {
        return link(cssSelector(".btn-group:nth-of-type(2) .open li[rel='5']>a"));
    }

    public FluentWebElement toggleFilter() {
        return link(cssSelector(".panel:nth-last-of-type(2) a[data-parent='#serp-panels']"));
    }

    public FluentWebElement genderDropDown() {
        return button(cssSelector(".dropdown-toggle[data-id='gender-select']"));
    }

    public FluentWebElement genderMale() {
        return link(cssSelector(".panel:nth-last-of-type(2) .form-group:nth-of-type(1) .open li[rel='1']>a"));
    }

    public FluentWebElement genderFemale() {
        return link(cssSelector(".panel:nth-last-of-type(2) .form-group:nth-of-type(1) .open li[rel='1']>a"));
    }

    public FluentWebElement boardCertified() {
        return input(cssSelector("#board-certified"));
    }

    public FluentWebElement usEducated() {
        return input(cssSelector("#us-educated"));
    }

    public FluentWebElements dropdowns() {
        return uls(cssSelector("ul.dropdown-menu"));
    }
}
