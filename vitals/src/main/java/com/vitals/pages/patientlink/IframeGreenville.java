package com.vitals.pages.patientlink;

import com.vitals.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import static org.openqa.selenium.By.cssSelector;

public class IframeGreenville extends BasePage {

    public FluentWebElement name() {
        return p(cssSelector(".container>div>div>p"));
    }
}