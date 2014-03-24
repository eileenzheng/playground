package com.vitals.pages.ucc;

import com.vitals.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import static org.openqa.selenium.By.cssSelector;

public class UccProfileAboutPage extends BasePage {

    public FluentWebElements breadcrumbs() {
        return links(cssSelector(".full.ucc>span>a"));
    }

    public FluentWebElement currentTrail() {
        return span(cssSelector(".full.ucc>span:last-child"));
    }

    public FluentWebElement menu() {
        return div(cssSelector(".nav-menu"));
    }

    public FluentWebElement menuReviews() {
        return link(cssSelector(".nav-menu li:nth-child(2)>a"));
    }

    public FluentWebElement menuServices() {
        return link(cssSelector(".nav-menu li:nth-child(4)>a"));
    }
	
	public boolean isTitleMatched() {
        return (breadcrumbs().get(breadcrumbs().size() - 1)).getText().toString().equals(h1().getText().toString());
	}
}
