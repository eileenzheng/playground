package com.vitals.pages.wlw;

import com.vitals.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import static org.openqa.selenium.By.cssSelector;

public class LandingPage extends BasePage {

    HeaderFooter headerFooter;

    public LandingPage() {
        headerFooter = new HeaderFooter();
    }

    public HeaderFooter commonModule(){
        return headerFooter;
    }

    public FluentWebElement specialtyDropDown() {
        return select(cssSelector("#specialist_id"));
    }

    public FluentWebElement locationTextBox() {
        return input(cssSelector("#location"));
    }

    public FluentWebElement searchButton() {
        return input(cssSelector("input[type='image']"));
    }
}