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
    private String[] leaderboardTopSizes = {"[728, 90]", "[880, 150]", "[970, 250]", "[970, 90]"};
    private String[] rectangleSizes = {"[300, 250]", "[300, 600]", "[300, 1050]"};
    private String[] rectangleBottomSizes = {"[300, 250]", "[160, 600]", "[300, 600]"};
    private String[] skyscraperSizes = {"[160, 600]"};

    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) throws Exception {
        this.url = url;
    }

    @Test
    public void profileSummary() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url);
        page.get(url + profile);

        m_assert.assertTrue(page.getPageSource().contains("/8905/vitals/profile/summary"), "Incorrect ad unit zones");

        String[] znValues = {"profile", "summary"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "zn", znValues), "Incorrect zn");
        String[] specValues = {"ctsg", "surg", "card"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        String[] pspecValues = {"ctsg"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "pspec", pspecValues), "Incorrect pspec");
        String[] fspecValues = {"ctsg-ctsg", "surg-surg", "intm-cdis"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "fspec", fspecValues), "Incorrect fspec");
        String[] spexValues = {"77", "374", "383"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spex", spexValues), "Incorrect spex");
        String[] midValues = {"13679110"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "mid", midValues), "Incorrect mid");
        String[] inscValues = {"35", "107", "311", "270"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "insc", inscValues), "Incorrect insc");


        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle_bottom", rectangleBottomSizes, "2"), "Incorrect rectangle bottom");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "skyscraper", skyscraperSizes, "1"), "Incorrect skyscraper");
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
            String size_string = first_half.substring(first_half.lastIndexOf("googletag.defineSlot"), first_half.lastIndexOf("],")+1);
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
