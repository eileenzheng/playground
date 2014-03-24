package com.vitals.pages.wlw;

import com.vitals.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import static org.openqa.selenium.By.cssSelector;

public class HeaderBar extends BasePage {

    public FluentWebElement header() {
        return div(cssSelector(".header"));
    }

    public FluentWebElement logo() {
        return img(cssSelector("img[src*='logo']"));
    }
}
