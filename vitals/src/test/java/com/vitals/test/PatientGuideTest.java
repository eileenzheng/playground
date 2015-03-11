package com.vitals.test;

import com.vitals.pages.PatientGuidePage;
import com.vitalsqa.testrail.TestCase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.vitals.pages.PatientGuideLegacyPage;

public class PatientGuideTest {

    static final String legacylink = "/patient-education/lupus/overview";
    static final String newlink = "/patient-education/breast-cancer/overview";
    SoftAssert m_assert;
    String url;
    
    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) throws Exception {
        this.url = url;
    }

    @TestCase(id={1534})
    @Test
    public void checkLegacyGuide() {
        PatientGuideLegacyPage page = new PatientGuideLegacyPage();
        page.get(url + legacylink);

        Assert.assertTrue(page.isOverviewPage(),
                "Overview page did not load on: " + page.getCurrentUrl());
    }

    @TestCase(id={1533})
    @Test
    public void checkNewGuide() {
        PatientGuidePage page = new PatientGuidePage();
        page.get(url + newlink);

        m_assert = new SoftAssert();
        m_assert.assertTrue(page.currentBreadcrumb().getText().equals("Overview"),
                "Overview page did not load on: " + page.getCurrentUrl());

        page.breadcrumbs().get(2).click();

        m_assert.assertTrue(page.currentBreadcrumb().getText().equals("Breast Cander"),
                "Landing page did not load on: " + page.getCurrentUrl());
    }
}
