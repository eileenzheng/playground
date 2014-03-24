package com.vitals.pages;

import com.vitals.pages.patientlink.PatientLinkRrAd;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import static org.openqa.selenium.By.cssSelector;

public class ProfileSeoPage extends BasePage {

    PatientLinkRrAd rrAd;

    public ProfileSeoPage() {
        rrAd = new PatientLinkRrAd();
    }

    public PatientLinkRrAd rrAd() {
        return rrAd;
    }

    public FluentWebElement viewFullProfileButton() {
        return link(cssSelector("#view-full"));
    }
}
