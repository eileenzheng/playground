package com.uchc.pages.browsepath;

import com.uchc.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import static org.openqa.selenium.By.cssSelector;

public class CityStatePage extends BasePage {

    public FluentWebElements navTabLinks() {
        return links(cssSelector(".tab-menu .nav-tabs a"));
    }

    public FluentWebElement navTabActive() {
        return li(cssSelector(".tab-menu .nav-tabs .active"));
    }

    public FluentWebElements stateLinks() {
        return links(cssSelector(".state"));
    }

    public FluentWebElements cityLinks() {
        return links(cssSelector(".city"));
    }


}
