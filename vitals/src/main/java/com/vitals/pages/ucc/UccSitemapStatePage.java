package com.vitals.pages.ucc;

import com.vitals.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import static org.openqa.selenium.By.cssSelector;

public class UccSitemapStatePage extends BasePage {

    public FluentWebElements cities() {
        return links(cssSelector(".column-list li>a"));
    }
}
