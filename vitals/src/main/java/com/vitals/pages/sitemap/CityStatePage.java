package com.vitals.pages.sitemap;

import com.vitals.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import static org.openqa.selenium.By.cssSelector;

public class CityStatePage extends BasePage {

    public FluentWebElements states() {
        return links(cssSelector(".sitemap-container .col-sm-4 li>a"));
    }

    public FluentWebElements cities() {
        return links(cssSelector(".sitemap-container .col-sm-4 li>div>a"));
    }
}
