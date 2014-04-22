package com.uchc.pages.patientlink;

import com.uchc.helpers.Constants;
import com.uchc.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import static org.openqa.selenium.By.cssSelector;

public class IframeDocAsap extends BasePage {

    public boolean hasSlots() {
        return has().link(cssSelector("a[title='Click time to book the appointment']"));
    }

    public FluentWebElement nextButton () {
        return link(cssSelector("#calendar_next_0"));
    }

    public FluentWebElement calendar() {
        return div (cssSelector(".table-section"));
    }

    public void clickNext() {
        nextButton().click();
        waitUntilVisible(calendar(), Constants.SELENIUM_EXPLICIT_WAIT);
    }
}
