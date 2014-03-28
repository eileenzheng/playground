package com.uchc.pages;

public class ProfilePage extends BasePage {

    PatientLinkRrAd rrAd;

    public ProfilePage() {
        rrAd = new PatientLinkRrAd();
    }

    public PatientLinkRrAd rrAd() {
        return rrAd;
    }
}
