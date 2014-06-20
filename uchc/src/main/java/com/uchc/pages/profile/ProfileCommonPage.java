package com.uchc.pages.profile;

import com.uchc.helpers.Constants;
import com.uchc.pages.BasePage;
import com.uchc.pages.patientlink.PatientLinkRrAd;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.linkText;

public class ProfileCommonPage extends BasePage {

    PatientLinkRrAd rrAd;

    public ProfileCommonPage() {
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

    public FluentWebElement plDrSite() {
        return link(cssSelector(".vcard>div>a"));
    }

    public boolean hasPlDrSite() {
        return has().link(cssSelector(".vcard>div>a"));
    }

    public FluentWebElement plPhoneNumber() {
        return link(cssSelector(".phone a"));
    }

    public boolean hasPlPhoneNumber() {
        return has().link(cssSelector(".phone a"));
    }

    public boolean hasPlBookAppt() {
        return has().link(cssSelector(".bookonline"));
    }

    public String getSiteUrl() {
        String onClick = plDrSite().getAttribute("onclick").toString();
        int begin = onClick.indexOf("http");
        int end = onClick.indexOf("'); return false;");
        return onClick.substring(begin, end);
    }

    public FluentWebElement drName() {
        return span(cssSelector("h1 .fn"));
    }

    public FluentWebElement contactButton() {
        return link(cssSelector(".profile-header-wrapper .btn-info"));
    }

    public FluentWebElement reviewsButton() {
        return link(cssSelector(".profile-header-wrapper .btn-warning"));
    }

    public FluentWebElement compareButton() {
        return link(cssSelector(".profile-header-wrapper .cn-compare .btn-warning"));
    }

    public FluentWebElement freeDoctorRpt() {
        return link(linkText("free doctor report"));
    }

    public FluentWebElements ratingLinks() {
        return links(cssSelector(".rating-average a"));
    }

    public FluentWebElement beTheFirstLink() {
        return link(cssSelector(".cta-first-review"));
    }

    public FluentWebElement contactLink() {
        return link(cssSelector(".vcard>div>a"));
    }

    private FluentWebElement reviewClose() {
        return button(cssSelector(".btn-close"));
    }

    public void dismissReviewIntercept() {
        setImplicitWait(1);
        if (has().div(cssSelector(".review-modal")))
            reviewClose().click();
        setImplicitWait(Constants.SELENIUM_IMPLICIT_WAIT);
    }
}
