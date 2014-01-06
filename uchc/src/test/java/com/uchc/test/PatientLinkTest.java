package com.uchc.test;

import com.uchc.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.uchc.pages.PatientLinkAd;
import com.uchc.pages.PatientLinkBookForm;
import com.uchc.pages.PatientLinkCenterAd;
import com.uchc.pages.PatientLinkRrAd;
import com.uchc.helpers.PatientLinkSetFeatures;
import java.util.ArrayList;
import java.util.List;

public class PatientLinkTest {
    private WebDriver driver;
    private boolean alreadyInit = false;
    private SoftAssert m_assert;

    private static List<String> apptUrl = new ArrayList<String>();
    private static String serpUrl = "/drs/physician_search.html?looking_for=physician&last_name=&location=10036&range=15&specialty_id=7&x=36&y=5";
    private static String profileUrl = "/drs/carrie_aaron/";

    String url;

    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) {
        this.url = url;
    }

    @AfterMethod
    public void shutdown() {
    }
    
    @Test
    public void testCenter() {
        driver = DriverManager.getDriver();
        
        driver.get(url + serpUrl);

        PatientLinkCenterAd ad = PageFactory.initElements(driver, PatientLinkCenterAd.class);
        
        testAd(ad,driver);
    }

    @Test
    public void testRight() {
        driver = DriverManager.getDriver();
        
        driver.get(url + profileUrl);
        
        PatientLinkRrAd ad = PageFactory.initElements(driver, PatientLinkRrAd.class);
        
        testAd(ad, driver);
    }
    
 // only need to call init function once for all the tests
    public void init() {
        if (!alreadyInit) {
            PatientLinkSetFeatures.init();
            alreadyInit = true;
        }
    }
    
    public void testAd(PatientLinkAd ad, WebDriver driver) {
        
        init();
        m_assert = new SoftAssert();
        PatientLinkSetFeatures pl = new PatientLinkSetFeatures();
        PatientLinkBookForm form;
        
        for (int i=0; i<ad.getSize(); i++) {

            pl.resetMatched();
            pl.setExpected(ad.getName(i));
            
            Assert.assertTrue(pl.isMatched(), ad.getName(i) + " is not in property file.");
            
            m_assert.assertEquals(ad.getAddressLine1(i), pl.getExpectedAddress(),
                    "Address Line 1 for " + ad.getName(i) + " did not match");
            
            if (ad.getAddressLine2(i) != null) {
                m_assert.assertEquals(ad.getAddressLine2(i), pl.getExpectedAddressLine2(),
                        "Address Line 2 for " + ad.getName(i) + " did not match");
            }
            else {
            	m_assert.assertTrue(pl.getExpectedAddressLine2().equals(""), "Address Line 2 for " + ad.getName(i) + " is missing");
            }
            
            m_assert.assertEquals(ad.getCity(i), pl.getExpectedCity(),
                    "City for " + ad.getName(i) + " did not match");
            
            m_assert.assertEquals(ad.getState(i), pl.getExpectedState(),
                    "State for " + ad.getName(i) + " did not match");
            
            m_assert.assertEquals(ad.getZip(i), pl.getExpectedZip(),
                    "Zip for " + ad.getName(i) + " did not match");

            if (ad.getPhoneNumber(i) != null) {
				m_assert.assertEquals(ad.getPhoneNumber(i), pl.getExpectedNumberUchc(), 
						"Phone number for " + ad.getName(i) + " did not match");
			}
            else {
            	m_assert.assertTrue(pl.getExpectedNumberUchc().equals(""), "Phone number is empty for " + ad.getName(i));
            }
            
            if (pl.hasBookOnline()) {
                m_assert.assertTrue(ad.isBookPresent(i), "Book Online button is not displayed for " + ad.getName(i));
                if (pl.getBookType()==1) {
                    if (ad.isBookPresent(i)) {
                        apptUrl.add(ad.getApptUrl(i));
                    }
                }
            }
            
            if (pl.hasLogo()) {
                m_assert.assertTrue(ad.isLogoPresent(i), "Logo is not displayed for " + ad.getName(i));
            }
        }

        for (String anApptUrl : apptUrl) {
            driver.get(anApptUrl);
            form = PageFactory.initElements(driver, PatientLinkBookForm.class);
            form.typeFirstName("test");
            form.typeLastName("test_last");
            form.selectRadioAfternoon();
            form.selectDrop1Week();
            form.submit();
        }
        
        m_assert.assertAll();
    }
}
