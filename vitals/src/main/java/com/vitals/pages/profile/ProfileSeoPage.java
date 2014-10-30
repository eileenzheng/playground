package com.vitals.pages.profile;

import com.vitals.helpers.Constants;
import com.vitals.pages.BasePage;
import com.vitals.pages.patientlink.PatientLinkRrAd;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.linkText;

public class ProfileSeoPage extends BasePage {

    PatientLinkRrAd rrAd;

    public ProfileSeoPage() {
        rrAd = new PatientLinkRrAd();
    }

    public PatientLinkRrAd rrAd() {
        return rrAd;
    }

    public FluentWebElement viewFullProfileLink() {
        return link(linkText("View full profile"));
    }

    private FluentWebElement reviewClose() {
        return link(cssSelector(".review-requests .close"));
    }

    public void dismissReviewIntercept() {
        setImplicitWait(0);
        if (has().div(cssSelector("#post-review-modal")))
            reviewClose().click();
        setImplicitWait(Constants.SELENIUM_IMPLICIT_WAIT);
    }
}
