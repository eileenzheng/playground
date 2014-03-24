package com.vitals.pages.ucc;

import com.vitals.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import static org.openqa.selenium.By.cssSelector;

public class UccSitemapPage extends BasePage {

    public FluentWebElements states() {
        return links(cssSelector(".col-sm-4 li>a"));
    }

    public FluentWebElements cities() {
        return links(cssSelector(".col-sm-4 li>div>a"));
    }
}