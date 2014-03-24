package com.vitals.pages;

import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.linkText;

public class PatientGuideLandingPage extends BasePage{

	public FluentWebElement breadcrumb() {
        return span(cssSelector(".breadcrumbs>span:nth-child(2)>a>span"));
	}
	
	public FluentWebElement learnMore() {
		return link(linkText("Learn more"));
	}

    public FluentWebElements topGuides() {
        return links(cssSelector(".top_patient_guides p>a"));
    }
	
	public FluentWebElements alphaGuides() {
        return links(cssSelector(".column-list li>a"));
    }
	
}
