package com.vitals.pages;

import org.seleniumhq.selenium.fluent.FluentWebElement;
import static org.openqa.selenium.By.cssSelector;

public class ReviewPage extends BasePage {

    HeaderModule headerModule;
    FooterModule footerModule;

    public ReviewPage() {
        headerModule = new HeaderModule();
        footerModule = new FooterModule();
    }

    public HeaderModule headerModule(){
        return headerModule;
    }

    public FooterModule footerModule() {
        return footerModule;
    }

    public FluentWebElement goButton() {
        return input(cssSelector(".go-btn"));
    }
}
