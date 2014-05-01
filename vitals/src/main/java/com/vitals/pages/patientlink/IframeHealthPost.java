package com.vitals.pages.patientlink;

import com.vitals.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import static org.openqa.selenium.By.cssSelector;

public class IframeHealthPost extends BasePage{

    public boolean hasSlots() {
        return has().li(cssSelector(".slot"));
    }

    public FluentWebElement nextButton() {
        return link(cssSelector(".time_slots_navigation .next_link"));
    }
}
