package pages;

import org.openqa.selenium.WebDriver;
import org.seleniumhq.selenium.fluent.FluentWebElement;

import static org.openqa.selenium.By.id;

public class HomePage extends BasePage {

    public HomePage(WebDriver delegate) {
        super(delegate);
    }

    public void go() {

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
