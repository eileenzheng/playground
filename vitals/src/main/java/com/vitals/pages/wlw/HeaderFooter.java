package com.vitals.pages.wlw;

import com.vitals.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import static org.openqa.selenium.By.cssSelector;

public class HeaderFooter extends BasePage {

    public FluentWebElement logo() {
        return img(cssSelector("img[src*='logo']"));
    }

    public FluentWebElement headerQsymia() {
        return div(cssSelector(".search-header"));
    }

    public FluentWebElement terms() {
        return p(cssSelector("footer>p:nth-of-type(2)"));
    }
}
