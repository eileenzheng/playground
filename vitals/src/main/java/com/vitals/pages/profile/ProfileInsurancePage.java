package com.vitals.pages.profile;

import com.vitals.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import static org.openqa.selenium.By.cssSelector;

public class ProfileInsurancePage extends BasePage {

    ProfileCommonPage common;

    public ProfileInsurancePage() {
        common = new ProfileCommonPage();
    }

    public ProfileCommonPage common() {
        return common;
    }

    public FluentWebElement company() {
        return link(cssSelector(".company"));
    }

    public FluentWebElement plan() {
        return tbody(cssSelector(".section"));
    }

    public FluentWebElement getQuoteLink() {
        return link(cssSelector(".pinkbox .modal-call"));
    }
}
