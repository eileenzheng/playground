package com.vitals.pages.patientlink;

import com.vitals.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import static org.openqa.selenium.By.cssSelector;

public class IframeCadit extends BasePage {

    public FluentWebElement comments() {
        return input(cssSelector("#SelectComments"));
    }
}