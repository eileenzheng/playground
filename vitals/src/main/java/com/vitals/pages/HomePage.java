package com.vitals.pages;

import org.openqa.selenium.By;
import org.seleniumhq.selenium.fluent.FluentWebElement;

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
        return div(By.cssSelector("#home-photo"));
    }
}
