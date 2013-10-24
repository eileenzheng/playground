package com.uchc.test;

import com.uchc.DriverManager;
import com.uchc.helpers.PatientLinkFeatures;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.uchc.pages.PatientLinkAd;
import com.uchc.pages.PatientLinkBookForm;
import com.uchc.pages.PatientLinkCenterAd;
import com.uchc.pages.PatientLinkRrAd;
import java.util.ArrayList;
import java.util.List;

public class PatientLinkTest {
    private WebDriver driver;

    private static List<String> apptUrl = new ArrayList<String>();
    private static String serpUrl = "/drs/physician_search.html?looking_for=physician&last_name=&location=10036&range=15&specialty_id=7&x=36&y=5";
    private static String profileUrl = "/drs/roopal_kundu/";
    
    
    @Parameters({"url"})
    @Test
    public void testCenter(String url) {
        driver = DriverManager.getDriver();
        
        driver.get(url + serpUrl);

        PatientLinkCenterAd ad = PageFactory.initElements(driver, PatientLinkCenterAd.class);
        
        testAd(ad,driver);
    }

    @Parameters({"url"})
    @Test
    public void testRight(String url) {
        driver = DriverManager.getDriver();
        
        driver.get(url + profileUrl);
        
        PatientLinkRrAd ad = PageFactory.initElements(driver, PatientLinkRrAd.class);
        
        testAd(ad, driver);
    }
    
    public static void testAd(PatientLinkAd ad, WebDriver driver) {
    	
    	Assert.assertTrue(ad.getSize()>0, "Ad is not showing up!");
        
        for (int i=0; i<ad.getSize(); i++) {
            
            PatientLinkFeatures.setExpected(ad.getName(i));
            
            Assert.assertEquals(ad.getAddress(i), PatientLinkFeatures.getExpectedAddress(),
                    "Address for " + ad.getName(i) + " did not match");
            
            Assert.assertEquals(ad.getCity(i), PatientLinkFeatures.getExpectedCity(),
                    "City for " + ad.getName(i) + " did not match");
            
            Assert.assertEquals(ad.getState(i), PatientLinkFeatures.getExpectedState(),
                    "State for " + ad.getName(i) + " did not match");
            
            Assert.assertEquals(ad.getZip(i), PatientLinkFeatures.getExpectedZip(),
                    "Zip for " + ad.getName(i) + " did not match");
            
            if (ad.isBookPresent(i)) {
                apptUrl.add(ad.getApptUrl(i));
            }
            
            if (ad.isPhonePresent(i)) {
                Assert.assertEquals(ad.getPhoneNumber(i),PatientLinkFeatures.getExpectedNumberUchc(),
                        "Phone number for " + ad.getName(i) + " did not match");
            }
        }
        
        for (int i=0; i<apptUrl.size(); i++) {
            driver.get(apptUrl.get(i));
            PatientLinkBookForm form = PageFactory.initElements(driver, PatientLinkBookForm.class);
            form.typeFirstName("test");
            form.typeLastName("test_last");
            form.selectRadioAfternoon();
            form.selectDrop1Week();
            form.submit();
        }
    }
}
