package com.vitals.pages.sitemap;

import com.vitals.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import static org.openqa.selenium.By.cssSelector;

public class InsurancePage extends BasePage {

    public FluentWebElements companies() {
        return links(cssSelector(".insurance>ul>li>a"));
    }

    public FluentWebElements plans() {
        return links(cssSelector(".insurance div>ul>li>a"));
    }

    public FluentWebElements alphabet() {
        return links(cssSelector(".alphabet li>a"));
    }

    public FluentWebElement currentAlphabet() {
        return link(cssSelector(".alphabet li.all>a"));
    }
}
