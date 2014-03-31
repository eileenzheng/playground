package com.uchc.pages;

import com.uchc.pages.patientlink.PatientLinkRrAd;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import static org.openqa.selenium.By.cssSelector;

public class ProfilePage extends BasePage {

    PatientLinkRrAd rrAd;

    public ProfilePage() {
        rrAd = new PatientLinkRrAd();
    }

    public PatientLinkRrAd rrAd() {
        return rrAd;
    }

    public FluentWebElement plBookAppt() {
        return link(cssSelector(".featured .bookonline"));
    }

    public boolean hasBookOnline() {
        return has().link(cssSelector(".featured .bookonline"));
    }
}
