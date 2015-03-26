package com.vitals.pages.patientlink;

import com.vitals.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElement;

import static org.openqa.selenium.By.cssSelector;

public class IframeAthena extends BasePage {

    public FluentWebElement name() {
        return h1(cssSelector("h1"));
    }

    public boolean hasSlots() {
        return has().link(cssSelector(".time-slots>li>a"));
    }

    public FluentWebElement nextButton () {
        return button(cssSelector("#next-week-button"));
    }

}
