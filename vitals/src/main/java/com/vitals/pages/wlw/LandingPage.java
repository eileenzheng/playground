package com.vitals.pages.wlw;

import com.vitals.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import static org.openqa.selenium.By.cssSelector;

public class LandingPage extends BasePage {

    HeaderBar headerBar;

    public LandingPage() {
        headerBar = new HeaderBar();
    }

    public HeaderBar headerPage(){
        return headerBar;
    }

    public FluentWebElement heading() {
        return div(cssSelector(".contentHeader"));
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