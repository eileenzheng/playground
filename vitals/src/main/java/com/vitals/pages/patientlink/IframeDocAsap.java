package com.vitals.pages.patientlink;

import com.vitals.helpers.Constants;
import com.vitals.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import static org.openqa.selenium.By.cssSelector;

public class IframeDocAsap extends BasePage {

    public FluentWebElement name() {
        return link(cssSelector(".title_blue_small a"));
    }

    public boolean hasSlots() {
        return has().link(cssSelector("a[title='Click time to book the appointment']"));
    }

    public FluentWebElement nextButton () {
        return div(cssSelector(".buttons .old-cal-next"));
    }

    public FluentWebElement calendar() {
        return div (cssSelector(".calendar"));
    }

    public void clickNext() {
        nextButton().click();
        waitUntilVisible(calendar(), Constants.SELENIUM_EXPLICIT_WAIT);
    }
}
