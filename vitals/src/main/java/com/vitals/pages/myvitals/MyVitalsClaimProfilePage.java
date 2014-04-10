package com.vitals.pages.myvitals;

import com.vitals.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import static org.openqa.selenium.By.cssSelector;

public class MyVitalsClaimProfilePage extends BasePage {

    public FluentWebElements fillInfoLinks() {
        return links(cssSelector("#new_claim p a"));
    }

    public FluentWebElement claimProfileButton() {
        return button(cssSelector(".form-actions>button"));
    }

    public FluentWebElement alertText() {
        return p(cssSelector(".alert>p"));
    }
    
    public void clickFillLinks() {
    	for (FluentWebElement el: fillInfoLinks()) {
    		el.click();
    	}
    }
}
