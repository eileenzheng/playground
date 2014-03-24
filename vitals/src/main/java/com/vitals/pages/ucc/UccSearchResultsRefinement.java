package com.vitals.pages.ucc;

import java.util.List;
import com.vitals.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.vitals.helpers.Constants;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import static org.openqa.selenium.By.cssSelector;

public class UccSearchResultsRefinement extends BasePage {

    public FluentWebElement toggleServices() {
        return link(cssSelector(".panel-title a"));
    }

    public FluentWebElement filterPhysicals() {
        return label(cssSelector(".physicals"));
    }

    public FluentWebElement filterLab() {
        return label(cssSelector(".lab"));
    }

    public FluentWebElement filterDiagnostic() {
        return label(cssSelector(".diagnostic"));
    }

    public FluentWebElement filterInjuries() {
        return label(cssSelector(".injuries"));
    }

    public FluentWebElement filterAilments() {
        return label(cssSelector(".ailments"));
    }

    public FluentWebElement filterPreventive() {
        return label(cssSelector(".preventive"));
    }

    public boolean isFilterOpen() {
        return has().div(cssSelector(".service-filters.in"));
    }
    
    @FindBy (css=".service-filters.in")
    private List<WebElement> filterOpened;
    
    public void clickToggleServices() throws InterruptedException {
    	setImplicitWait(0);
    	if (!isFilterOpen()) {
    		toggleServices().click();
    		while (!isFilterOpen()) {
    			Thread.sleep(100); // wait for filter to be opened
    		}
    	}
    	else
    		toggleServices().click();
    	setImplicitWait(Constants.SELENIUM_IMPLICIT_WAIT);
    }

    public void clickPhysicals() {
        waitUntilVisible(filterPhysicals(), Constants.SELENIUM_EXPLICIT_WAIT);
        filterPhysicals().click();
        waitUntilInvisible(cssSelector("#loading"), Constants.SELENIUM_EXPLICIT_WAIT);
    }
    
    public void clickLab() {
    	waitUntilVisible(filterLab(), Constants.SELENIUM_EXPLICIT_WAIT);
        filterLab().click();
        waitUntilInvisible(cssSelector("#loading"), Constants.SELENIUM_EXPLICIT_WAIT);
    }
    
    public void clickDiagnostic() {
        waitUntilVisible(filterDiagnostic(), Constants.SELENIUM_EXPLICIT_WAIT);
        filterDiagnostic().click();
        waitUntilInvisible(cssSelector("#loading"), Constants.SELENIUM_EXPLICIT_WAIT);
    }
    
    public void clickInjuries() {
        waitUntilVisible(filterInjuries(), Constants.SELENIUM_EXPLICIT_WAIT);
        filterInjuries().click();
        waitUntilInvisible(cssSelector("#loading"), Constants.SELENIUM_EXPLICIT_WAIT);
    }
    
    public void clickAilments() {
        waitUntilVisible(filterAilments(), Constants.SELENIUM_EXPLICIT_WAIT);
        filterAilments().click();
        waitUntilInvisible(cssSelector("#loading"), Constants.SELENIUM_EXPLICIT_WAIT);
    }
    
    public void clickPreventive() {
        waitUntilVisible(filterPreventive(), Constants.SELENIUM_EXPLICIT_WAIT);
        filterPreventive().click();
        waitUntilInvisible(cssSelector("#loading"), Constants.SELENIUM_EXPLICIT_WAIT);
    }
}
