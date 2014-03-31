package com.uchc.pages.patientlink;

import com.uchc.helpers.Constants;
import com.uchc.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import static org.openqa.selenium.By.cssSelector;

public class IframeHealthPost extends BasePage {
    public boolean hasSlots() {
        return has().li(cssSelector(".slot"));
    }

    public FluentWebElement nextButton() {
        return link(cssSelector(".time_slots_navigation .next_link"));
    }

    public FluentWebElements week() {
        return spans(cssSelector(".day_of_week"));
    }

    public void clickNext() {
        nextButton().click();
        waitUntilVisible(week().get(0), Constants.SELENIUM_EXPLICIT_WAIT);
    }
}
