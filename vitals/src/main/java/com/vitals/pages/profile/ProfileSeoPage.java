package com.vitals.pages.profile;

import com.vitals.pages.BasePage;
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

    private FluentWebElement reviewModal () {
        return div(cssSelector("#post-review-modal"));
    }

    private FluentWebElement reviewClose() {
        return link(cssSelector(".review-requests .close"));
    }

    public void dismissReviewIntercept() {
        if (reviewModal().isDisplayed().value())
            reviewClose().click();
    }
}
