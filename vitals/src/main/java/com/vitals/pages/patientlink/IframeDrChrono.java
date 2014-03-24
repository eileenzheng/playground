package com.vitals.pages.patientlink;

import com.vitals.helpers.Constants;
import com.vitals.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;
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

    public FluentWebElement table() {
        return table(cssSelector("#id_tbl_schedule_appointment_times"));
    }

    public void clickNext() {
        nextButton().click();
        waitUntilVisible(table(), Constants.SELENIUM_EXPLICIT_WAIT);
    }
}
