package com.vitals.pages;

import com.vitals.helpers.Constants;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import static org.openqa.selenium.By.cssSelector;

public class SearchResultsRefinement extends BasePage{

    public FluentWebElement sortDropDown() {
        return button(cssSelector("#serp-sidebar-filter .serplist-filter-sort .dropdown-menu.selectpicker li:last-child>a.dropdown-toggle[data-id='sort']"));
    }

    public FluentWebElement sortByRelevancy() {
        return link(cssSelector("#serp-sidebar-filter .serplist-filter-sort .dropdown-menu.selectpicker li:last-child>a.serplist-filter-sort .dropdown-menu.selectpicker li:first-child>a"));
    }

    public FluentWebElement sortByDistance() {
        return link(cssSelector("#serp-sidebar-filter .serplist-filter-sort .dropdown-menu.selectpicker li:last-child>a.serplist-filter-sort .dropdown-menu.selectpicker li:nth-last-child(3)>a"));
    }

    public FluentWebElement sortByRating() {
        return link(cssSelector("#serp-sidebar-filter .serplist-filter-sort .dropdown-menu.selectpicker li:last-child>a.serplist-filter-sort .dropdown-menu.selectpicker li:nth-last-child(2)>a"));
    }

    public FluentWebElement sortByName() {
        return link(cssSelector("#serp-sidebar-filter .serplist-filter-sort .dropdown-menu.selectpicker li:last-child>a.serplist-filter-sort .dropdown-menu.selectpicker li:last-child>a"));
    }

    private FluentWebElement openFilterArrow() {
        return div(cssSelector("#serplist-filter-toggle-arrow.icon-chevron-thin-down"));
    }

    public FluentWebElement distanceDropDown() {
        return button(cssSelector("#serp-sidebar-filter .dropdown-toggle[data-id='distance']"));
    }

    public FluentWebElement distance5() {
        return link(cssSelector("#serp-sidebar-filter #serp-refine-filters .btn-group:nth-of-type(1) li[rel='0']>a"));
    }

    public FluentWebElement distance10() {
        return link(cssSelector("#serp-sidebar-filter #serp-refine-filters .btn-group:nth-of-type(1) li[rel='1']>a"));
    }

    public FluentWebElement distance15() {
        return link(cssSelector("#serp-sidebar-filter #serp-refine-filters .btn-group:nth-of-type(1) li[rel='2']>a"));
    }

    public FluentWebElement distance25() {
        return link(cssSelector("#serp-sidebar-filter #serp-refine-filters .btn-group:nth-of-type(1) li[rel='3']>a"));
    }

    public FluentWebElement distance50() {
        return link(cssSelector("#serp-sidebar-filter #serp-refine-filters .btn-group:nth-of-type(1) li[rel='4']>a"));
    }

    public FluentWebElement distance100() {
        return link(cssSelector("#serp-sidebar-filter #serp-refine-filters .btn-group:nth-of-type(1) li[rel='5']>a"));
    }

    public FluentWebElement genderDropDown() {
        return button(cssSelector("#serp-sidebar-filter .dropdown-toggle[data-id='gender-select']"));
    }

    public FluentWebElement genderMale() {
        return link(cssSelector("#serp-sidebar-filter #serp-refine-filters .btn-group:nth-of-type(2) li[rel='1']>a"));
    }

    public FluentWebElement genderFemale() {
        return link(cssSelector("#serp-sidebar-filter #serp-refine-filters .btn-group:nth-of-type(2) li[rel='2']>a"));
    }

    public FluentWebElement boardCertified() {
        return input(cssSelector("#serp-sidebar-filter #board-certified"));
    }

    public FluentWebElement usEducated() {
        return input(cssSelector("#serp-sidebar-filter #us-educated"));
    }

    public FluentWebElement applyToResults() {
        return button(cssSelector("#serp-sidebar-filter #serp-apply-filters"));
    }

    public FluentWebElements dropdowns() {
        return uls(cssSelector("#serp-sidebar-filter ul.dropdown-menu"));
    }

    public void openFilter() {
        openFilterArrow().click();
        waitUntilVisible(boardCertified(), Constants.SELENIUM_EXPLICIT_WAIT);
    }
}
