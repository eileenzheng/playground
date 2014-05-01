package com.uchc.pages.patientlink;

import com.uchc.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import static org.openqa.selenium.By.cssSelector;

public class IframeBookThatDoc extends BasePage {

    public FluentWebElement name() {
        return link(cssSelector(".DocName"));
    }

    public boolean hasSlots() {
        return has().div(cssSelector(".time_slot:not(.empty_time_slot)"));
    }

    public FluentWebElement nextButton () {
        return link(cssSelector(".doctor_availability_container>a:last-of-type"));
    }
}
