package pages;

import helpers.Constants;
import org.openqa.selenium.WebDriver;
import org.seleniumhq.selenium.fluent.FluentWebElement;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;

public class HomePage extends BasePage {

    public HomePage(WebDriver delegate) {
        super(delegate);
    }

    public void go() {
        get(Constants.TEST_SITE);

    }

    public void clickDemoFacilitySearch() {
        link(cssSelector(".container>div:nth-child(2)>a")).click();
    }

    public void clickDemoFacilityProfile() {
        link(cssSelector(".container>div:nth-child(3)>a")).click();
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
