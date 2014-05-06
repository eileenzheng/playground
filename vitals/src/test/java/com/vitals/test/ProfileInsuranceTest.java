package com.vitals.test;

import com.vitals.pages.profile.ProfileCommonPage;
import com.vitals.pages.profile.ProfileInsuranceIframe;
import com.vitals.pages.profile.ProfileInsurancePage;
import com.vitalsqa.testrail.TestCase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.seleniumhq.selenium.fluent.Period.millis;

public class ProfileInsuranceTest {

    static final String drProfile = "/doctors/Dr_Marina_Gafanovich/insurance";

    String url;

    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) throws Exception {
        this.url = url;
    }

    @TestCase(id=1826)
    @Test
    public void expandInsuranceCompany() {
        ProfileInsurancePage insurancePage = new ProfileInsurancePage();
        insurancePage.get(url);
        insurancePage.get(url + drProfile);
        ProfileCommonPage profile = new ProfileCommonPage();
        profile.dismissReviewIntercept();

        insurancePage.company().click();
        Assert.assertTrue(insurancePage.plan().within(millis(500)).isDisplayed().value(), "Expand insurance company doesn't work");
    }

    @TestCase(id=1827)
    @Test
    public void getQuotes() {
        ProfileInsurancePage insurancePage = new ProfileInsurancePage();
        insurancePage.get(url);
        insurancePage.get(url + drProfile);
        ProfileCommonPage profile = new ProfileCommonPage();
        profile.dismissReviewIntercept();

        insurancePage.getQuoteLink().click();
        insurancePage.switchIframe("iframe[src*='getinsurance']");
        ProfileInsuranceIframe iframe = new ProfileInsuranceIframe();
        Assert.assertTrue(iframe.topImage().isDisplayed().value(), "Top image is not displayed in insurance iframe");
        Assert.assertTrue(iframe.searchButton().isDisplayed().value(), "Top image is not displayed in insurance iframe");
    }
}
