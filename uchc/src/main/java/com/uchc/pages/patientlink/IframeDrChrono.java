package com.uchc.pages.patientlink;

import com.uchc.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import static org.openqa.selenium.By.cssSelector;

public class IframeDrChrono extends BasePage {

    public FluentWebElement name () {
        return h3();
    }

    public boolean hasSlots() {
        return has().link(cssSelector(".timeslot"));
    }

    public FluentWebElement nextButton () {
        return link(cssSelector(".btn-next-week"));
    }
}
