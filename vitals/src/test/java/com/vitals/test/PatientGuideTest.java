package com.vitals.test;

import com.vitalsqa.testrail.TestCase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.vitals.pages.PatientGuideLandingPage;
import com.vitals.pages.PatientGuidePage;

public class PatientGuideTest {

    static final String pglink = "/patient-education";
    SoftAssert m_assert;
    String url;
    
    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) throws Exception {
        this.url = url;
    }

    @TestCase(id={1533,1535})
    @Test
    public void checkLandingPage() {
        PatientGuideLandingPage lp = new PatientGuideLandingPage();
    	lp.get(url + pglink);

        Assert.assertTrue(lp.breadcrumb().getText().toString().equals("Patient Education"),
                "Patient Guide landing page did not load successfully.");
    }

    @TestCase(id={1534,1535})
    @Test
    public void clickLearnMore() {
        if (url.toLowerCase().contains("qa"))
        	return;
        PatientGuideLandingPage lp = new PatientGuideLandingPage();
        lp.get(url + pglink);
        lp.learnMore().click();

        testIndividualGuide();
    }

    @TestCase(id={1536,1535})
    @Test
    public void clickTopGuide() {
        PatientGuideLandingPage lp = new PatientGuideLandingPage();
        lp.get(url + pglink);
        lp.getRandom(lp.topGuides()).click();

        testIndividualGuide();
    }

    @TestCase(id={1537,1535})
    @Test
    public void clickAtoZGuide() {
        PatientGuideLandingPage lp = new PatientGuideLandingPage();
        lp.get(url + pglink);
        lp.getRandom(lp.alphaGuides()).click();

        testIndividualGuide();
    }

    private void testIndividualGuide() {

        m_assert = new SoftAssert();

        PatientGuidePage pgpage = new PatientGuidePage();

        m_assert.assertTrue(pgpage.isOverviewPage(),
                "Overview page did not load on: " + pgpage.getCurrentUrl());

        pgpage.menuTheTeam().click();
        m_assert.assertTrue(pgpage.isTeamPage(),
                "The Team page did not load on: " + pgpage.getCurrentUrl());

        pgpage.menuPrepare().click();
        m_assert.assertTrue(pgpage.isPreparePage(),
                "How to Prepare page did not load on " + pgpage.getCurrentUrl());

        pgpage.menuQuestion().click();
        m_assert.assertTrue(pgpage.isQuestionPage(),
                "Questions to Ask page did not load on " + pgpage.getCurrentUrl());

        pgpage.menuExpect().click();
        m_assert.assertTrue(pgpage.isExpectPage(),
                "What to Expect page did not load on " + pgpage.getCurrentUrl());

        pgpage.menuTreatment().click();
        m_assert.assertTrue(pgpage.isTreatmentPage(),
                "Treatment Options page did not load on " + pgpage.getCurrentUrl());

        m_assert.assertAll();
    }
}
