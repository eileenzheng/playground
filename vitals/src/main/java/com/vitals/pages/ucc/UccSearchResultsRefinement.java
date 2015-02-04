package com.vitals.pages.ucc;

import com.vitals.pages.BasePage;
import com.vitals.helpers.Constants;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;

public class UccSearchResultsRefinement extends BasePage {

    public FluentWebElement toggleServices() {
        return div(cssSelector("#serp-sidebar-filter #serplist-filter-toggle-arrow"));
    }

    public FluentWebElement filterPhysicals() {
        return input(cssSelector("#serp-sidebar-filter #search-physicals"));
    }

    public FluentWebElement filterLab() {
        return input(cssSelector("#serp-sidebar-filter #search-lab"));
    }

    public FluentWebElement filterDiagnostic() {
        return input(cssSelector("#serp-sidebar-filter #search-diagnostic"));
    }

    public FluentWebElement filterInjuries() {
        return input(cssSelector("#serp-sidebar-filter #search-injuries"));
    }

    public FluentWebElement filterAilments() {
        return input(cssSelector("#serp-sidebar-filter #search-ailments"));
    }

    public FluentWebElement filterPreventive() {
        return input(cssSelector("#serp-sidebar-filter #search-preventive"));
    }

    public FluentWebElements dropdowns() {
        return uls(cssSelector("#serp-sidebar-filter ul.dropdown-menu.selectpicker"));
    }

    public FluentWebElement applyToResults() {
        return button(cssSelector("#serp-sidebar-filter #serp-apply-filters"));
    }

    public void clickApply() {
        applyToResults().click();
        waitUntilInvisible(cssSelector("#loading"), Constants.SELENIUM_EXPLICIT_WAIT);
    }
}
