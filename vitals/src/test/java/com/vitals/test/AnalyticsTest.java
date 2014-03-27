package com.vitals.test;

import com.vitals.pages.*;
import com.vitalsqa.testrail.TestCase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AnalyticsTest {

    String url;
    BasePage page;
    SoftAssert m_assert;
    private final String comScore = "var _comscore = _comscore";
    private final String googleAnalytics = "google-analytics.com";
    private final String optimizely = "cdn.optimizely.com";
    private final String profile = "/doctors/Dr_Todd_Rosengart/";
    private final String uccProfile = "/urgent-care/citymd-new-york-4/";
    private final String pg = "/patient-education/diabetes/";

    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) throws Exception {
        this.url = url;
    }

    @TestCase(id=1805)
    @Test
    public void homePage() {
        page = new BasePage();
        page.get(url);
        Assert.assertTrue(checkAnalytics(page.getPageSource()));
    }

    @TestCase(id=1806)
    @Test
    public void serp() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/dermatologists");
        m_assert.assertTrue(checkAnalytics(page.getPageSource()), "SERP browse path");
        page.get(url + "/urgent-care");
        m_assert.assertTrue(checkAnalytics(page.getPageSource()), "Ucc SERP Browse Path");
        page.get(url + "/search?type=name&provider_type=1&q=");
        m_assert.assertTrue(checkAnalytics(page.getPageSource()), "SERP search path");
        page.get(url + "/urgent-care/search?type=name&provider_type=10&q=");
        m_assert.assertTrue(checkAnalytics(page.getPageSource()), "Ucc SERP search path");
        m_assert.assertAll();
    }

    @TestCase(id=1807)
    @Test
    public void profile() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url);
        page.get(url + profile + "profile");
        m_assert.assertTrue(checkAnalytics(page.getPageSource()), "Summary tab");
        page.get(url + profile + "reviews");
        m_assert.assertTrue(checkAnalytics(page.getPageSource()), "Reviews tab");
        page.get(url + profile + "credentials");
        m_assert.assertTrue(checkAnalytics(page.getPageSource()), "Credentials tab");
        page.get(url + profile + "office-locations");
        m_assert.assertTrue(checkAnalytics(page.getPageSource()), "Locations tab");
        page.get(url + profile + "insurance");
        m_assert.assertTrue(checkAnalytics(page.getPageSource()), "Insurance tab");
        page.get(url + profile + "sponsored?utm_campaign=otlerax");
        m_assert.assertTrue(checkAnalytics(page.getPageSource()), "Sponsored tab");
        m_assert.assertAll();
    }

    @TestCase(id=1808)
    @Test
    public void uccProfile() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + uccProfile);
        m_assert.assertTrue(checkAnalytics(page.getPageSource()), "Summary page");
        page.get(url + uccProfile + "services");
        m_assert.assertTrue(checkAnalytics(page.getPageSource()), "Services page");
        page.get(url + uccProfile + "about");
        m_assert.assertTrue(checkAnalytics(page.getPageSource()), "About page");
        page.get(url + uccProfile + "reviews");
        m_assert.assertTrue(checkAnalytics(page.getPageSource()), "Reviews page");
        m_assert.assertAll();
    }

    @TestCase(id=1809)
    @Test
    public void reviewPages() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/review");
        m_assert.assertTrue(checkAnalytics(page.getPageSource()), "Review page");
        page.get(url + "/review/results?name=&location=New+York%2C+NY");
        m_assert.assertTrue(checkAnalytics(page.getPageSource()), "Review SERP");
        page.get(url + "/review/citymd-new-york-4");
        m_assert.assertTrue(checkAnalytics(page.getPageSource()), "Review Ucc");
        page.get(url + "/review/Dr_Todd_Rosengart");
        m_assert.assertTrue(checkAnalytics(page.getPageSource()), "Review Provider");
        m_assert.assertAll();
    }

    @TestCase(id=1810)
    @Test
    public void sitemaps() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/specialties");
        m_assert.assertTrue(checkAnalytics(page.getPageSource()), "Specialties");
        page.get(url + "/locations");
        m_assert.assertTrue(checkAnalytics(page.getPageSource()), "Locations");
        page.get(url + "/locations/cardiologists");
        m_assert.assertTrue(checkAnalytics(page.getPageSource()), "Location Specialty");
        page.get(url + "/locations/cardiologists/al");
        m_assert.assertTrue(checkAnalytics(page.getPageSource()), "Location Specialty State");
        page.get(url + "/directory");
        m_assert.assertTrue(checkAnalytics(page.getPageSource()), "Name");
        page.get(url + "/conditions");
        m_assert.assertTrue(checkAnalytics(page.getPageSource()), "Condition");
        page.get(url + "/insurances");
        m_assert.assertTrue(checkAnalytics(page.getPageSource()), "Insurance");
        page.get(url + "/insurances/aetna");
        m_assert.assertTrue(checkAnalytics(page.getPageSource()), "Insurance Company");
        m_assert.assertAll();
    }

    @TestCase(id=1811)
    @Test
    public void patientGuides() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/patient-education");
        m_assert.assertTrue(checkAnalytics(page.getPageSource()), "Landing Page");
        page.get(url + pg);
        m_assert.assertTrue(checkAnalytics(page.getPageSource()), "Overview");
        page.get(url + pg + "the-team");
        m_assert.assertTrue(checkAnalytics(page.getPageSource()), "The Team");
        page.get(url + pg + "how-to-prepare");
        m_assert.assertTrue(checkAnalytics(page.getPageSource()), "How to Prepare");
        page.get(url + pg + "questions-to-ask");
        m_assert.assertTrue(checkAnalytics(page.getPageSource()), "Questions to Ask");
        page.get(url + pg + "what-to-expect");
        m_assert.assertTrue(checkAnalytics(page.getPageSource()), "What to Expect");
        page.get(url + pg + "treatment-options");
        m_assert.assertTrue(checkAnalytics(page.getPageSource()), "Treatment Options");
        m_assert.assertAll();
    }

    @TestCase(id=1812)
    @Test
    public void topics() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/topics");
        m_assert.assertTrue(checkAnalytics(page.getPageSource()), "Landing Page");
        page.get(url + "/topics/copd");
        m_assert.assertTrue(checkAnalytics(page.getPageSource()), "Step 1");
        page.get(url + "/topics/copd/the-elderly-and-copd");
        m_assert.assertTrue(checkAnalytics(page.getPageSource()), "Step 2");
        page.get(url + "/topics/copd/why-copd");
        m_assert.assertTrue(checkAnalytics(page.getPageSource()), "Step 3");
        page.get(url + "/topics/copd/what-a-specialist-can-do");
        m_assert.assertTrue(checkAnalytics(page.getPageSource()), "Step 4");
        page.get(url + "/topics/copd/things-to-consider");
        m_assert.assertTrue(checkAnalytics(page.getPageSource()), "Step 5");
        m_assert.assertAll();
    }

    private boolean checkAnalytics(String source) {
        return (source.contains(comScore) && source.contains(googleAnalytics) && source.contains(optimizely));
    }
}
