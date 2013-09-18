package com.vitals.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.vitals.pages.PatientGuideLandingPage;
import com.vitals.pages.PatientGuidePage;
import com.vitals.runners.RemoteTestRunner;

public class PatientGuideTest extends RemoteTestRunner {
    
    WebDriver driver;
    static final String pglink = "/patient-education";

    @Parameters({"domain"})
    @Test
    public void checkBreadCrumb(String domain) {
        
        driver = getDriver();
        driver.get(getUrl(domain) + pglink);
        
        PatientGuideLandingPage pglanding = PageFactory.initElements(driver, PatientGuideLandingPage.class);
        
        Assert.assertTrue(pglanding.isLandingPage(), 
                "Patient Guide landing page did not load successfully.");
    }

    @Parameters({"domain"})
    @Test
    public void clickLearnMore(String domain) {
        
        driver = getDriver();
        driver.get(getUrl(domain) + pglink);
        
        PatientGuideLandingPage pglanding = PageFactory.initElements(driver, PatientGuideLandingPage.class);
        pglanding.clickLearnMore();
        
        testPatientGuide();
    }

    @Parameters({"domain"})
    @Test
    public void clickTopGuide(String domain) {
        
        driver = getDriver();
        driver.get(getUrl(domain) + pglink);
        
        PatientGuideLandingPage pglanding = PageFactory.initElements(driver, PatientGuideLandingPage.class);
        pglanding.clickTopGuide();
        
        testPatientGuide();
    }

    @Parameters({"domain"})
    @Test
    public void clickAtoZGuide(String domain) {
        
        driver = getDriver();
        driver.get(getUrl(domain) + pglink);
        
        PatientGuideLandingPage pglanding = PageFactory.initElements(driver, PatientGuideLandingPage.class);
        pglanding.clickAtoZGuide();
        
        testPatientGuide();
    }

    public void testPatientGuide() {
        // This is a common function for testing one patient guide page
        
        PatientGuidePage pgpage = PageFactory.initElements(driver, PatientGuidePage.class);
        
        Assert.assertTrue(pgpage.isOverviewPage(), 
                "Overview page did not load on: " + driver.getCurrentUrl());

        pgpage = pgpage.clickTeam();
        Assert.assertTrue(pgpage.isTeamPage(),
                "The Team page did not load on: " + driver.getCurrentUrl());
        
        pgpage = pgpage.clickPrepare();
        Assert.assertTrue(pgpage.isPreparePage(),
                "How to Prepare page did not load on " + driver.getCurrentUrl());
        
        pgpage = pgpage.clickQuestion();
        Assert.assertTrue(pgpage.isQuestionPage(),
                "Questions to Ask page did not load on " + driver.getCurrentUrl());
        
        pgpage = pgpage.clickExpect();
        Assert.assertTrue(pgpage.isExpectPage(),
                "What to Expect page did not load on " + driver.getCurrentUrl());
        
        pgpage = pgpage.clickTreatment();
        Assert.assertTrue(pgpage.isTreatmentPage(),
                "Treatment Options page did not load on " + driver.getCurrentUrl());
    }
}