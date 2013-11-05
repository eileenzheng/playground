package com.vitals.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.vitals.DriverManager;
import com.vitals.pages.PatientGuideLandingPage;
import com.vitals.pages.PatientGuidePage;

public class PatientGuideTest {
    
    WebDriver driver;
    static final String pglink = "/patient-education";
    
    String url;
    
    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) throws Exception {
        this.url = url;
    }

    /* - check breadcrumb of PG landing page */
    @Test
    public void checkBreadCrumb() {
        
    	driver = DriverManager.getDriver();
        driver.get(url + pglink);
        
        PatientGuideLandingPage pglanding = PageFactory.initElements(driver, PatientGuideLandingPage.class);
        
        Assert.assertTrue(pglanding.isLandingPage(), 
                "Patient Guide landing page did not load successfully.");
    }

    /* - go to PG landing page
     * - click a random learn more link
     * - perform test for single guide */
    @Test
    public void clickLearnMore() {
        
    	driver = DriverManager.getDriver();
        driver.get(url + pglink);
        
        PatientGuideLandingPage pglanding = PageFactory.initElements(driver, PatientGuideLandingPage.class);
        pglanding.clickLearnMore();
        
        testPatientGuide();
    }

    /* - go to PG landing page
     * - click a random top guide
     * - perform test for single guide */
    @Test
    public void clickTopGuide() {
        
    	driver = DriverManager.getDriver();
        driver.get(url + pglink);
        
        PatientGuideLandingPage pglanding = PageFactory.initElements(driver, PatientGuideLandingPage.class);
        pglanding.clickTopGuide();
        
        testPatientGuide();
    }

    /* - go to PG landing page
     * - click a random A to Z guide
     * - perform test for single guide */
    @Test
    public void clickAtoZGuide() {
        
        driver = DriverManager.getDriver();
        driver.get(url + pglink);
        
        PatientGuideLandingPage pglanding = PageFactory.initElements(driver, PatientGuideLandingPage.class);
        pglanding.clickAtoZGuide();
        
        testPatientGuide();
    }

    /* shared function for testing a single guide, navigate thru the tabs and check breadcrumb */
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
