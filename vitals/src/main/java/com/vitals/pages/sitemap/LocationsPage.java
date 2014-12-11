package com.vitals.pages.sitemap;

import com.vitals.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import static org.openqa.selenium.By.cssSelector;

public class LocationsPage extends BasePage {

    public FluentWebElements specialties() {
        return links(cssSelector("div.row.location:first-of-type ul>li>a"));
    }

    public FluentWebElements groupPractices() {
        return links(cssSelector("div.row:last-of-type ul>li>a"));
    }
}
