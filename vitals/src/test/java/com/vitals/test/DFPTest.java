package com.vitals.test;

import com.vitals.pages.BasePage;
import com.vitalsqa.testrail.TestCase;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DFPTest {

    String url;
    BasePage page;
    SoftAssert m_assert;
    private final String profile = "/doctors/Dr_Emile_Bacha/";

    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) throws Exception {
        this.url = url;
    }

    @Test
    public void homePage() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + profile);
        String[] values = {"profile", "basic"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "zn", values), "Incorrect zn");
        String[] sizes = {"[728, 90]", "[880, 150]", "[970, 250]"};
        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", sizes, "1"), "Incorrect leaderboard top");
        m_assert.assertAll();
    }

    public boolean checkKeys(String source, String key, String[] values) {
        int start = source.indexOf("googletag.pubads().setTargeting(\"" + key);
        if (start==-1) {
            Reporter.log("Missing key: " + key + " not found <br>");
            return false;
        }
        else {
            boolean result = true;
            String line = source.substring(source.indexOf(",", start), source.indexOf(";", start));
            for (int i=0; i<values.length; i++) {
                if (!line.contains(values[i])) {
                    result = false;
                    Reporter.log("Missing/incorrect value for " + key + ", expecting " + values[i] + " <br>");
                }
            }
            return result;
        }
    }

    public boolean checkSlots(String source, String slot, String[] sizes, String pos) {
        int start = source.indexOf(slot + "').addService");
        if (start==-1) {
            Reporter.log("Missing slot: " + slot + "not found <br>");
            return false;
        }
        else {
            boolean result = true;
            String first_half = source.substring(0, start);
            String size_string = first_half.substring(first_half.lastIndexOf("googletag.defineSlot"), first_half.lastIndexOf("],"));
            String pos_string = source.substring(start, source.indexOf("')", start+30));
            for (int i=0; i<sizes.length; i++) {
                if (!size_string.contains(sizes[i])) {
                    result = false;
                    Reporter.log("Missing/incorrect size for " + slot + ", expecting " + sizes[i] + " <br>");
                }
            }
            if (!pos_string.contains("'pos', '" + pos)) {
                result = false;
                Reporter.log("Missing/incorrect pos for " + slot + ", expecting " + pos + " <br>");
            }
            return result;
        }
    }
}
