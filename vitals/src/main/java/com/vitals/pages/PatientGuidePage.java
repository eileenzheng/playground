package com.vitals.pages;

import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import static org.openqa.selenium.By.cssSelector;

public class PatientGuidePage extends BasePage{

	public FluentWebElements breadcrumbs() {
        return links(cssSelector(".bread-crumb .bitems"));
	}

    public FluentWebElement currentBreadcrumb() {
        return span(cssSelector(".bread-crumb span.actual"));
    }

}
