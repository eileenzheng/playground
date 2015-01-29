package com.vitals.pages.sitemap;

import com.vitals.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import static org.openqa.selenium.By.cssSelector;

public class LocationsPage extends BasePage {

    public FluentWebElements specialties() {
        return links(cssSelector(".location-row:first-of-type li>a"));
    }

    public FluentWebElements groupPractices() {
        return links(cssSelector(".location-row:last-of-type li>a"));
    }
}
