package com.vitals.pages.sitemap;

import com.vitals.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import static org.openqa.selenium.By.cssSelector;

public class DirectoryPage extends BasePage {

    public FluentWebElements doctors() {
        return links(cssSelector("div.doctor>a"));
    }

    public FluentWebElements alphabet() {
        return links(cssSelector(".alphabet li>a"));
    }

    public FluentWebElement currentAlphabet() {
        return link(cssSelector(".alphabet li.all>a"));
    }

    public FluentWebElements pagination() {
        return links(cssSelector(".pagination li>a[href]"));
    }

    public FluentWebElement currentPage() {
        return link(cssSelector(".pagination li.active>a"));
    }

    public FluentWebElement lastJumpPageLink() {
        return link(cssSelector(".dropdown-menu li:last-child>a"));
    }
}
