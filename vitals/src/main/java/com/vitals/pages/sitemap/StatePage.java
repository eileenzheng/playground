package com.vitals.pages.sitemap;

import com.vitals.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import static org.openqa.selenium.By.cssSelector;

public class StatePage extends BasePage{

    public FluentWebElements cities() {
        return links(cssSelector(".column-list li>a"));
    }
}
