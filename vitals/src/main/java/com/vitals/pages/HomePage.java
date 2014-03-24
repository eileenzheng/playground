package com.vitals.pages;

import org.seleniumhq.selenium.fluent.FluentWebElement;
import static org.openqa.selenium.By.cssSelector;

public class HomePage extends BasePage {

    HeaderModule headerModule;
    FooterModule footerModule;

    public HomePage() {
        headerModule = new HeaderModule();
        footerModule = new FooterModule();
    }

    public HeaderModule headerModule(){
        return headerModule;
    }

    public FooterModule footerModule() {
        return footerModule;
    }

    public FluentWebElement homePhoto() {
        return div(cssSelector("#home-photo"));
    }
}
