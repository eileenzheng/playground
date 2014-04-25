package com.uchc.pages.profile;

import com.uchc.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import static org.openqa.selenium.By.cssSelector;

public class ProfilePageDirections extends BasePage {

    public FluentWebElement fromAddress() {
        return input(cssSelector("#fromAddress"));
    }

    public FluentWebElement getDirectionsButton() {
        return button(cssSelector(".btn-primary"));
    }

    public FluentWebElements directionSteps() {
        return tds(cssSelector(".adp-substep:nth-child(3)"));
    }
}
