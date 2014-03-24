package com.vitals.pages.myvitals;

import com.vitals.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import static org.openqa.selenium.By.cssSelector;

public class MyVitalsProfessionalsPage extends BasePage {

    public FluentWebElement deleteButton() {
        return link(cssSelector(".button-discreet"));
    }
}
