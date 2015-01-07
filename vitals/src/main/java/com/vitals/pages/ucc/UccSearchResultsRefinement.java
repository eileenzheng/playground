package com.vitals.pages.ucc;

import com.vitals.pages.BasePage;
import com.vitals.helpers.Constants;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;

public class UccSearchResultsRefinement extends BasePage {

    public FluentWebElement toggleServices() {
        return div(cssSelector("#serplist-filter-toggle-arrow"));
    }

    public FluentWebElement filterPhysicals() {
        return input(cssSelector("#search-physicals"));
    }

    public FluentWebElement filterLab() {
        return input(cssSelector("#search-lab"));
    }

    public FluentWebElement filterDiagnostic() {
        return input(cssSelector("#search-diagnostic"));
    }

    public FluentWebElement filterInjuries() {
        return input(cssSelector("#search-injuries"));
    }

    public FluentWebElement filterAilments() {
        return input(cssSelector("#search-ailments"));
    }

    public FluentWebElement filterPreventive() {
        return input(cssSelector("#search-preventive"));
    }

    public FluentWebElements dropdowns() {
        return uls(cssSelector("ul.dropdown-menu.selectpicker"));
    }

    public FluentWebElement applyToResults() {
        return button(id("serp-apply-filters"));
    }

    public void clickApply() {
        applyToResults().click();
        waitUntilInvisible(cssSelector("#loading"), Constants.SELENIUM_EXPLICIT_WAIT);
    }
}
