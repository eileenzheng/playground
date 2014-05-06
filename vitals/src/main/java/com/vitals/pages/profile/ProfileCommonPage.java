package com.vitals.pages.profile;

import com.vitals.pages.BasePage;
import com.vitals.pages.patientlink.PatientLinkRrAd;
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

    public FluentWebElement summaryTab() {
        return link(cssSelector(".nav-tabs li:nth-child(1)>a"));
    }

    public FluentWebElement reviewsTab() {
        return link(cssSelector(".nav-tabs li:nth-child(2)>a"));
    }

    public FluentWebElement credentialsTab() {
        return link(cssSelector(".nav-tabs li:nth-child(3)>a"));
    }

    public FluentWebElement locationsTab() {
        return link(cssSelector(".nav-tabs li:nth-child(4)>a"));
    }

    public FluentWebElement insurancesTab() {
        return link(cssSelector(".nav-tabs li:nth-child(5)>a"));
    }

    public FluentWebElement sponsoredTab() {
        return link(cssSelector(".nav-tabs li:nth-child(6)>a"));
    }

    public FluentWebElements breadcrumbTrail() {
        return spans(cssSelector(".additionalInformation>span"));
    }

    public FluentWebElement drVideoLink() {
        return link(linkText("Video profile"));
    }

    public FluentWebElement plDrSite() {
        return link(linkText("Doctor's site"));
    }

    public boolean hasPlDrSite() {
        return has().link(linkText("Doctor's site"));
    }

    public FluentWebElement plBookAppt() {
        return link(cssSelector(".btn.modal-call"));
    }

    public boolean hasPlBookAppt() {
        return has().link(cssSelector(".btn.modal-call"));
    }

    public FluentWebElement plPhoneNumber() {
        return link(cssSelector(".call-appointment a"));
    }

    public boolean hasPlPhoneNumber() {
        return has().link(cssSelector(".call-appointment a"));
    }

    public FluentWebElement claimProfileLink() {
        return link(cssSelector(".claim-profile>a"));
    }

    public FluentWebElement name(){
        return link(cssSelector("h1>a"));
    }

    public boolean isSummaryPage() {
        return breadcrumbTrail().get(breadcrumbTrail().size()-1).getText().toString().equals("Summary");
    }

    public boolean isPatientReviewsPage() {
        return breadcrumbTrail().get(breadcrumbTrail().size()-1).getText().toString().equals("Patient Reviews");
    }

    public boolean isCredentialsPage() {
        return breadcrumbTrail().get(breadcrumbTrail().size()-1).getText().toString().equals("Credentials");
    }

    public boolean isLocationsAvailabilityPage() {
        return breadcrumbTrail().get(breadcrumbTrail().size()-1).getText().toString().equals("Locations & Availability");
    }

    public boolean isAcceptedInsurancePage() {
        return breadcrumbTrail().get(breadcrumbTrail().size()-1).getText().toString().equals("Accepted Insurance");
    }

    public String getSiteUrl() {
        String onClick = plDrSite().getAttribute("onclick").toString();
        int begin = onClick.indexOf("http");
        int end = onClick.indexOf("', '_blank'");
        return onClick.substring(begin, end);
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
