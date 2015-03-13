package com.vitals.test;

import com.vitals.pages.BasePage;
import com.vitalsqa.testrail.TestCase;
import org.apache.commons.lang3.StringUtils;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.ArrayList;

public class DFPMobileTest {

    String url;
    BasePage page;
    SoftAssert m_assert;
    private final String profile = "/doctors/Dr_Lisa_Eng/";
    private String[] leaderboardTopSizes = {"300, 50", "320, 50"};
    private String[] rectangleSizes = {"300, 250"};
    private String[] rectangleBottomSizes = {"300, 250"};
    private String[] stickyMobBannerSizes = {"300, 50", "320, 50"};

    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) throws Exception {
        this.url = url;
    }

    @TestCase(id=3468)
    @Test
    public void profileSummary() {
        page = new BasePage();
        page.setWindowSize(1024,768);
        m_assert = new SoftAssert();
        page.get(url);
        page.get(url + profile);

        String[] znValues = {"profile", "summary"};
        m_assert.assertTrue(checkKeys(page, "zn", znValues), "Incorrect zn");
        String[] specValues = {"obgn"};
        m_assert.assertTrue(checkKeys(page, "spec", specValues), "Incorrect spec");
        String[] pspecValues = {"obgn"};
        m_assert.assertTrue(checkKeys(page, "pspec", pspecValues), "Incorrect pspec");
        String[] fspecValues = {"obgn-obgn"};
        m_assert.assertTrue(checkKeys(page, "fspec", fspecValues), "Incorrect fspec");
        String[] spexValues = {"816", "2964", "3957"};
        m_assert.assertTrue(checkKeys(page, "spex", spexValues), "Incorrect spex");
        String[] midValues = {"13607954"};
        m_assert.assertTrue(checkKeys(page, "mid", midValues), "Incorrect mid");
        String[] inscValues = {"35", "72", "311", "270"};
        m_assert.assertTrue(checkKeys(page, "insc", inscValues), "Incorrect insc");

        String path = "/8905/vitals/profile/summary";
        m_assert.assertTrue(checkSlots(page, "leaderboard_top", leaderboardTopSizes, "1", path), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page, "rectangle", rectangleSizes, "1", path), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page, "rectangle_bottom", rectangleBottomSizes, "2", path), "Incorrect rectangle bottom");
        m_assert.assertTrue(checkSlots(page, "sticky_mob_banner", stickyMobBannerSizes, "0", path), "Incorrect sticky_mob_banner");
        m_assert.assertAll();
    }

    @TestCase(id=3469)
    @Test
    public void serpFindSpec() {
        page = new BasePage();
        page.setWindowSize(1024,768);
        m_assert = new SoftAssert();
        page.get(url + "/search?type=specialty&provider_type=1&specialist_id=135&q=Cardiologist+(heart)&insurance=35-514");

        String[] znValues = {"serp", "find", "spec"};
        m_assert.assertTrue(checkKeys(page, "zn", znValues), "Incorrect zn");
        String[] specValues = {"card"};
        m_assert.assertTrue(checkKeys(page, "spec", specValues), "Incorrect spec");
        String[] pspecValues = {"card"};
        m_assert.assertTrue(checkKeys(page, "pspec", pspecValues), "Incorrect pspec");
        String[] inscValues = {"35"};
        m_assert.assertTrue(checkKeys(page, "insc", inscValues), "Incorrect insc");
        String[] inspValues = {"514"};
        m_assert.assertTrue(checkKeys(page, "insp", inspValues), "Incorrect insp");

        String path = "/8905/vitals/serp/find/spec";
        m_assert.assertTrue(checkSlots(page, "leaderboard_top", leaderboardTopSizes, "1", path), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page, "rectangle", rectangleSizes, "1", path), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page, "rectangle_bottom", rectangleBottomSizes, "2", path), "Incorrect rectangle bottom");
        m_assert.assertTrue(checkSlots(page, "sticky_mob_banner", stickyMobBannerSizes, "0", path), "Incorrect sticky_mob_banner");
        m_assert.assertAll();
    }

    @TestCase(id=3470)
    @Test
    public void serpFindName() {
        page = new BasePage();
        page.setWindowSize(1024,768);
        m_assert = new SoftAssert();
        page.get(url + "/search?type=name&provider_type=1&q=todd&insurance=35-514");

        String[] znValues = {"serp", "find", "name"};
        m_assert.assertTrue(checkKeys(page, "zn", znValues), "Incorrect zn");
        String[] kwValues = {"name"};
        m_assert.assertTrue(checkKeys(page, "kw", kwValues), "Incorrect kw");
        String[] inscValues = {"35"};
        m_assert.assertTrue(checkKeys(page, "insc", inscValues), "Incorrect insc");
        String[] inspValues = {"514"};
        m_assert.assertTrue(checkKeys(page, "insp", inspValues), "Incorrect insp");

        String path = "/8905/vitals/serp/find/name";
        m_assert.assertTrue(checkSlots(page, "leaderboard_top", leaderboardTopSizes, "1", path), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page, "rectangle", rectangleSizes, "1", path), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page, "rectangle_bottom", rectangleBottomSizes, "2", path), "Incorrect rectangle bottom");
        m_assert.assertTrue(checkSlots(page, "sticky_mob_banner", stickyMobBannerSizes, "0", path), "Incorrect sticky_mob_banner");
        m_assert.assertAll();
    }

    @TestCase(id=3471)
    @Test
    public void serpBrowseSpecGeo() {
        page = new BasePage();
        page.setWindowSize(1024,768);
        m_assert = new SoftAssert();
        page.get(url + "/cardiologists/ny/new-york");

        String[] znValues = {"serp", "browse", "spec"};
        m_assert.assertTrue(checkKeys(page, "zn", znValues), "Incorrect zn");
        String[] specValues = {"card"};
        m_assert.assertTrue(checkKeys(page, "spec", specValues), "Incorrect spec");
        String[] pspecValues = {"card"};
        m_assert.assertTrue(checkKeys(page, "pspec", pspecValues), "Incorrect pspec");

        String path = "/8905/vitals/serp/browse/spec";
        m_assert.assertTrue(checkSlots(page, "leaderboard_top", leaderboardTopSizes, "1", path), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page, "rectangle", rectangleSizes, "1", path), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page, "rectangle_bottom", rectangleBottomSizes, "2", path), "Incorrect rectangle bottom");
        m_assert.assertTrue(checkSlots(page, "sticky_mob_banner", stickyMobBannerSizes, "0", path), "Incorrect sticky_mob_banner");
        m_assert.assertAll();
    }

    @TestCase(id=3472)
    @Test
    public void serpBrowseCond() {
        page = new BasePage();
        page.setWindowSize(1024,768);
        m_assert = new SoftAssert();
        page.get(url + "/condition/diabetes");

        String[] znValues = {"serp", "browse", "cond"};
        m_assert.assertTrue(checkKeys(page, "zn", znValues), "Incorrect zn");
        String[] spexValues = {"1628"};
        m_assert.assertTrue(checkKeys(page, "spex", spexValues), "Incorrect spex");

        String path = "/8905/vitals/serp/browse/cond";
        m_assert.assertTrue(checkSlots(page, "leaderboard_top", leaderboardTopSizes, "1", path), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page, "rectangle", rectangleSizes, "1", path), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page, "rectangle_bottom", rectangleBottomSizes, "2", path), "Incorrect rectangle bottom");
        m_assert.assertTrue(checkSlots(page, "sticky_mob_banner", stickyMobBannerSizes, "0", path), "Incorrect sticky_mob_banner");
        m_assert.assertAll();
    }

    @TestCase(id=3473)
    @Test
    public void uccSerpBrowse() {
        page = new BasePage();
        page.setWindowSize(1024,768);
        m_assert = new SoftAssert();
        page.get(url + "/urgent-care");

        String[] znValues = {"ucc", "ucc_serp"};
        m_assert.assertTrue(checkKeys(page, "zn", znValues), "Incorrect zn");

        String path = "/8905/vitals/ucc/ucc_serp";
        m_assert.assertTrue(checkSlots(page, "leaderboard_top", leaderboardTopSizes, "1", path), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page, "rectangle", rectangleSizes, "1", path), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page, "rectangle_bottom", rectangleBottomSizes, "2", path), "Incorrect rectangle bottom");
        m_assert.assertTrue(checkSlots(page, "sticky_mob_banner", stickyMobBannerSizes, "0", path), "Incorrect sticky_mob_banner");
        m_assert.assertAll();
    }

    public boolean checkKeys(BasePage page, String key, String[] values) {
        String command;
        String values_to_string = "";
        boolean found = false;
        boolean result = true;

        Long length = (Long) page.executeJS("return window.app.advert.googletag.logs['setTargeting'].length");
        int len = Integer.parseInt(length.toString());
        for (int i=0; i<len; i++) {
            command = " return window.app.advert.googletag.logs['setTargeting'][" + i + "][0]";
            if (((String) page.executeJS(command)).equals(key)) {
                found = true;
                command = "return window.app.advert.googletag.logs['setTargeting'][" + i + "][1]";
                Object js_output = page.executeJS(command);
                if (String.class.isInstance(js_output)) {
                    values_to_string = (String) js_output;
                }
                else if (ArrayList.class.isInstance(js_output)) {
                    ArrayList<String> values_string = (ArrayList<String>) page.executeJS(command);
                    values_to_string = StringUtils.join(values_string, ", ");
                }
                for (int j=0; j<values.length; j++) {
                    if (!values_to_string.contains(values[j])) {
                        result = false;
                        Reporter.log("Missing/incorrect value for " + key + ", expecting " + values[i] + " <br>");
                    }
                }
            }
        }

        if (!found) {
            Reporter.log("Missing key: " + key + " not found <br>");
            return false;
        }

        return result;
    }

    public boolean checkSlots(BasePage page, String slot, String[] sizes, String pos, String path) {
        String command;
        String size_string;
        boolean found = false;
        boolean result = true;

        Long length = (Long) page.executeJS("return window.app.advert.googletag.logs['defineSlot'].length");
        int len = Integer.parseInt(length.toString());
        for (int i=0; i<len; i++) {
            command = "return window.app.advert.googletag.logs['defineSlot'][" + i + "][0].name";
            if (((String) page.executeJS(command)).equals(slot)) {
                found = true;
                command = "return window.app.advert.googletag.logs['defineSlot'][" + i + "][0].path";
                if (!((String) page.executeJS(command)).equals(path)) {
                    result = false;
                    Reporter.log("Incorrect path for " + slot + ", expecting " + path + " <br>");
                }

                command = "return window.app.advert.googletag.logs['defineSlot'][" + i + "][0].position";

                // workaround because sticky_mob_banner's pos is string while others are number
                Object js_output = page.executeJS(command);
                if (String.class.isInstance(js_output)) {
                    js_output = (String) js_output;
                }
                else if (Long.class.isInstance(js_output)) {
                    js_output = (String) js_output.toString();
                }

                // check pos value
                if (!js_output.equals(pos)) {
                    result = false;
                    Reporter.log("Incorrect pos for " + slot + ", expecting " + pos + " <br>");
                }
                command = "return window.app.advert.googletag.logs['defineSlot'][" + i + "][0].responsive.mobile";
                ArrayList<String> sizesArray = (ArrayList<String>) page.executeJS(command);
                size_string = StringUtils.join(sizesArray, ", ");
                for (int j=0; j<sizes.length; j++) {
                    if (!size_string.contains(sizes[j])) {
                        result = false;
                        Reporter.log("Missing/incorrect size for " + slot + ", expecting " + sizes[j] + " <br>");
                    }
                }
            }
        }

        if (!found && !slot.equals("leaderboard_bottom")) {
            Reporter.log("Missing slot: " + slot + " not found <br>");
            return false;
        }

        return result;
    }
}
