package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;

public class SearchResults extends BasePage {

    public SearchResults(WebDriver delegate) {
        super(delegate);
    }

    public FluentWebElements resultList() {
        return divs(id("placeholder"));
    }

    public FluentWebElements resultName(FluentWebElements elements) {
        FluentWebElements retValue = null;

        for (FluentWebElement el : elements) {
            retValue.add(el.div(id("placeholder")));
        }

        return retValue;
    }

    public FluentWebElements resultAddresses(FluentWebElements elements) {
        FluentWebElements retValue = null;

        for (FluentWebElement el : elements) {
            retValue.add(el.div(id("placeholder")));
        }

        return retValue;
    }

    public FluentWebElement seeMoreLink() {
        return div(id("placeholder"));
    }

    public boolean seeMoreLinkIsVisible() {
        return seeMoreLink().isDisplayed().value();
    }

    public WebElement googleMap(){
        return delegate.findElement(By.cssSelector(".map"));
    }

}
