package com.vitals.pages.patientlink;

import com.vitals.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import static org.openqa.selenium.By.cssSelector;

public class IframeDoctorDotCom extends BasePage {

    public FluentWebElement name() {
        return div(cssSelector(".docm-title"));
    }
}