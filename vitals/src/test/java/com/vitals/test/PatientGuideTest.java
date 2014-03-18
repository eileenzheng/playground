package com.vitals.test;

import com.vitalsqa.testrail.TestCase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.vitals.DriverManager;
import com.vitals.pages.PatientGuideLandingPage;
import com.vitals.pages.PatientGuidePage;

public class PatientGuideTest {
    
    WebDriver driver;
    SoftAssert m_assert;
    static final String pglink = "/patient-education";
    
    String url;
    
    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) throws Exception {
        this.url = url;
    }

    @TestCase(id={1533,1535})
    @Test
    public void checkLandingPage() {
        
    	driver = DriverManager.getDriver();
        driver.get(url + pglink);
        
        PatientGuideLandingPage pglanding = PageFactory.initElements(driver, PatientGuideLandingPage.class);
        
        Assert.assertTrue(pglanding.isLandingPage(), 
                "Patient Guide landing page did not load successfully.");
    }

    @TestCase(id={1534,1535})
    @Test
    public void clickLearnMore() {
        if (url.toLowerCase().contains("qa"))
        	return;
    	driver = DriverManager.getDriver();
        driver.get(url + pglink);
        
        PatientGuideLandingPage pglanding = PageFactory.initElements(driver, PatientGuideLandingPage.class);
        pglanding.clickLearnMore();
        
        testIndividualGuide();
    }

    @TestCase(id={1536,1535})
    @Test
    public void clickTopGuide() {
        
    	driver = DriverManager.getDriver();
        driver.get(url + pglink);
        
        PatientGuideLandingPage pglanding = PageFactory.initElements(driver, PatientGuideLandingPage.class);
        PatientGuidePage pg = pglanding.clickTopGuide();
        
        if (pg != null)
        	testIndividualGuide();
    }

    @TestCase(id={1537,1535})
    @Test
    public void clickAtoZGuide() {
        
        driver = DriverManager.getDriver();
        driver.get(url + pglink);
        
        PatientGuideLandingPage pglanding = PageFactory.initElements(driver, PatientGuideLandingPage.class);
        pglanding.clickAtoZGuide();
        
        testIndividualGuide();
    }
    
    private void testIndividualGuide() {
        
        PatientGuidePage pgpage = PageFactory.initElements(driver, PatientGuidePage.class);
        
        m_assert = new SoftAssert();
        
        m_assert.assertTrue(pgpage.isOverviewPage(), 
                "Overview page did not load on: " + driver.getCurrentUrl());

        pgpage = pgpage.clickTeam();
        m_assert.assertTrue(pgpage.isTeamPage(),
                "The Team page did not load on: " + driver.getCurrentUrl());
        
        pgpage = pgpage.clickPrepare();
        m_assert.assertTrue(pgpage.isPreparePage(),
                "How to Prepare page did not load on " + driver.getCurrentUrl());
        
        pgpage = pgpage.clickQuestion();
        m_assert.assertTrue(pgpage.isQuestionPage(),
                "Questions to Ask page did not load on " + driver.getCurrentUrl());
        
        pgpage = pgpage.clickExpect();
        m_assert.assertTrue(pgpage.isExpectPage(),
                "What to Expect page did not load on " + driver.getCurrentUrl());
        
        pgpage = pgpage.clickTreatment();
        m_assert.assertTrue(pgpage.isTreatmentPage(),
                "Treatment Options page did not load on " + driver.getCurrentUrl());
        
        m_assert.assertAll();
    }
}
