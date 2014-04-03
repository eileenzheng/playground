package com.vitals.pages.profile;

import com.vitals.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import static org.openqa.selenium.By.cssSelector;

public class ProfileInsuranceIframe extends BasePage {

    public FluentWebElement searchButton() {
        return img(cssSelector("#form2 img"));
    }

    public FluentWebElement topImage() {
        return img(cssSelector("#content_hc>div>img"));
    }
}
