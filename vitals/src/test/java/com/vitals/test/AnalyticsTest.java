package com.vitals.test;

import com.vitals.pages.*;
import com.vitalsqa.testrail.TestCase;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AnalyticsTest {

    String url;
    BasePage page;
    SoftAssert m_assert;
    private final String comScore = "scorecardresearch.com";
    private final String googleAnalytics = "google-analytics.com";
    private final String googletTagManager = "googletagmanager";
    private final String tynt = "tynt.com";
    private final String quant = "quantserve.com";
    private final String adroll = "adroll.com";
    private final String marketo = "marketo.net";
    private final String profile = "/doctors/Dr_Emile_Bacha/";
    private final String uccProfile = "/urgent-care/citymd-new-york-4/";
    private final String pg = "/patient-education/diabetes/";
    private final String head = "document.head.innerHTML";
    private final String body = "document.body.innerHTML";

    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) throws Exception {
        this.url = url;
    }

    @TestCase(id=1805)
    @Test
    public void homePage() {
        page = new BasePage();
        String[] exceptions = {marketo, adroll, tynt};
        page.get(url);
        Assert.assertTrue(checkAnalytics(page, exceptions));
    }

    @TestCase(id=1806)
    @Test
    public void serp() {
        page = new BasePage();
        String[] exceptions = {marketo, adroll, tynt};
        m_assert = new SoftAssert();
        page.get(url + "/dermatologists");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "SERP browse path geo");
        page.get(url + "/urgent-care");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "Ucc SERP Browse Path geo");
        page.get(url + "/dermatologists/ny/new-york");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "SERP browse path city");
        page.get(url + "/urgent-care/ny/new-york");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "Ucc SERP Browse Path city");
        page.get(url + "/search?type=name&provider_type=1&q=");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "SERP search path");
        page.get(url + "/urgent-care/search?type=name&provider_type=10&q=");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "Ucc SERP search path");
        m_assert.assertAll();
    }

    @TestCase(id=1807)
    @Test
    public void profile() {
        page = new BasePage();
        String[] exceptions = {marketo, adroll};
        m_assert = new SoftAssert();
        page.get(url);
        page.get(url + profile + "profile");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "Summary tab");
        page.get(url + profile + "reviews");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "Reviews tab");
        page.get(url + profile + "credentials");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "Credentials tab");
        page.get(url + profile + "office-locations");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "Locations tab");
        page.get(url + profile + "insurance");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "Insurance tab");
        page.get(url + profile + "sponsored?utm_campaign=otlerax");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "Sponsored tab");
        m_assert.assertAll();
    }

    @TestCase(id=1808)
    @Test
    public void uccProfile() {
        page = new BasePage();
        String[] exceptions = {marketo, adroll};
        m_assert = new SoftAssert();
        page.get(url + uccProfile);
        m_assert.assertTrue(checkAnalytics(page, exceptions), "Summary page");
        page.get(url + uccProfile + "services");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "Services page");
        page.get(url + uccProfile + "about");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "About page");
        page.get(url + uccProfile + "reviews");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "Reviews page");
        m_assert.assertAll();
    }

    @TestCase(id=1809)
    @Test
    public void reviewPages() {
        page = new BasePage();
        String[] exceptions = {marketo, adroll};
        m_assert = new SoftAssert();
        page.get(url + "/review");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "Review page");
        page.get(url + "/review/results?name=&location=New+York%2C+NY");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "Review SERP");
        page.get(url + "/review/citymd-new-york-4");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "Review Ucc");
        page.get(url + "/review/Dr_Emile_Bacha");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "Review Provider");
        m_assert.assertAll();
    }

    @TestCase(id=1810)
    @Test
    public void sitemaps() {
        page = new BasePage();
        String[] exceptions = {marketo, adroll};
        m_assert = new SoftAssert();
        page.get(url + "/specialties");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "Specialties");
        page.get(url + "/locations");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "Locations");
        page.get(url + "/locations/cardiologists");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "Location Specialty");
        page.get(url + "/locations/cardiologists/al");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "Location Specialty State");
        page.get(url + "/directory");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "Name");
        page.get(url + "/conditions");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "Condition");
        page.get(url + "/insurances");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "Insurance");
        page.get(url + "/insurances/aetna");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "Insurance Company");
        m_assert.assertAll();
    }

    @TestCase(id=1811)
    @Test
    public void patientGuides() {
        page = new BasePage();
        String[] exceptions = {marketo, adroll};
        m_assert = new SoftAssert();
        page.get(url + "/patient-education");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "Landing Page");
        page.get(url + pg);
        m_assert.assertTrue(checkAnalytics(page, exceptions), "Overview");
        page.get(url + pg + "the-team");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "The Team");
        page.get(url + pg + "how-to-prepare");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "How to Prepare");
        page.get(url + pg + "questions-to-ask");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "Questions to Ask");
        page.get(url + pg + "what-to-expect");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "What to Expect");
        page.get(url + pg + "treatment-options");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "Treatment Options");
        m_assert.assertAll();
    }

    @TestCase(id=1812)
    @Test
    public void topics() {
        page = new BasePage();
        String[] exceptions = {marketo, adroll};
        m_assert = new SoftAssert();
        page.get(url + "/topics");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "Landing Page");
        page.get(url + "/topics/copd");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "Step 1");
        page.get(url + "/topics/copd/the-elderly-and-copd");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "Step 2");
        page.get(url + "/topics/copd/why-copd");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "Step 3");
        page.get(url + "/topics/copd/what-a-specialist-can-do");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "Step 4");
        page.get(url + "/topics/copd/things-to-consider");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "Step 5");
        m_assert.assertAll();
    }

    @TestCase(id=2161)
    @Test
    public void groupPractice() {
        page = new BasePage();
        String[] exceptions = {marketo, adroll};
        m_assert = new SoftAssert();
        page.get(url + "/group-practice");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "Main Page");
        page.get(url + "/group-practice/alabama/");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "State Page");
        page.get(url + "/group-practice/alabama/shelby/birmingham/");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "City Page");
        page.get(url + "/group-practice/alabama/shelby/birmingham/greenvale-pediatric-assoc/");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "Group Page");
        m_assert.assertAll();
    }

    @TestCase(id=2162)
    @Test
    public void about() {
        page = new BasePage();
        String[] exceptions = {adroll};
        m_assert = new SoftAssert();
        page.get(url + "/about");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "Main Page");
        page.get(url + "/about/patients");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "For Consumers");
        page.get(url + "/about/healthplans");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "For Health Plans");
        page.get(url + "/about/providers");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "For Providers");
        page.get(url + "/about/advertisers");
        m_assert.assertTrue(checkAnalytics(page, null), "For Advertisers");
        page.get(url + "/about/vitals-team");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "The Team");
        page.get(url + "/about/press");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "News");
        page.get(url + "/about/resources");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "Resources");
        page.get(url + "/about/doctor-awards");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "Awards");
        page.get(url + "/about/careers");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "Careers");
        page.get(url + "/contact");
        m_assert.assertTrue(checkAnalytics(page, exceptions), "Contact Us");
        m_assert.assertAll();
    }

    private boolean checkAnalytics(BasePage page, String[] exceptions) {
        String source = page.getPageSource();

        boolean result = true;

        if (!isInArray(googletTagManager, exceptions) && !source.contains(googletTagManager)) {
            Reporter.log("Google Tag Manager<br>");
            result = false;
        }

        if (!isInArray(comScore, exceptions) && !checkTag(page, head, comScore)) {
            Reporter.log("ComScore<br>");
            result = false;
        }

        if (!isInArray(tynt, exceptions) && !checkTag(page, head, tynt)) {
            Reporter.log("Tynt<br>");
            result = false;
        }

        if (!isInArray(googleAnalytics, exceptions) && !checkTag(page, head, googleAnalytics)) {
            Reporter.log("Google Analytics<br>");
            result = false;
        }

        if (!isInArray(quant, exceptions) && !checkTag(page, body, quant)) {
            Reporter.log("Quant<br>");
            result = false;
        }

        if (!isInArray(marketo, exceptions) && !checkTag(page, body, marketo)) {
            Reporter.log("Marketo<br>");
            result = false;
        }

        if (!isInArray(adroll, exceptions) && !checkTag(page, head, adroll)) {
            Reporter.log("Adroll<br>");
            result = false;
        }

        return result;
    }

    private boolean checkTag(BasePage page, String element, String search) {

        String elementOutput = page.getConsoleLog(element);

        int i=0;
        while (i<20 && !elementOutput.contains(search)) {
            try {
                Thread.sleep(500);
                i++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            elementOutput = page.getConsoleLog(element);
        }

        return elementOutput.contains(search);
    }

    private boolean isInArray(String text, String[] array) {

        if (array==null)
            return false;

        for (String current:array) {
            if (current.equals(text))
                return true;
        }
        return false;
    }
}
