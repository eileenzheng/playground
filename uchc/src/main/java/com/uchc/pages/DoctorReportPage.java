package com.uchc.pages;

import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import static org.openqa.selenium.By.cssSelector;

public class DoctorReportPage extends BasePage {

    private FluentWebElements doctorNames() {
        return divs(cssSelector("#report-heading-top div"));
    }

    public boolean hasDrInReport(String name) {
        for (FluentWebElement el : doctorNames()) {
            if (el.getText().toString().contains(name))
                return true;
        }
        return false;
    }
}
