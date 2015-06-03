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

public class DFPTest {

    String url;
    BasePage page;
    SoftAssert m_assert;
    private final String profile = "/doctors/Dr_Lisa_Eng/";
    private final String ucc = "/urgent-care/citymd-new-york-3/";
    private final String[] conditionCenters = {"/topics/high-cholesterol", "/topics/high-cholesterol/should-children-be-checked",
            "/topics/high-cholesterol/things-to-consider", "/topics/high-cholesterol/lifestyle-matters", "/topics/high-cholesterol/specialist-vs-pcp"};
    private final String[] patientGuides = {"/patient-education/infertility", "/patient-education/infertility/the-team",
            "/patient-education/infertility/how-to-prepare", "/patient-education/infertility/questions-to-ask", "/patient-education/infertility/what-to-expect", "/patient-education/infertility/treatment-options"};
    private String[] leaderboardTopSizes = {"728, 90", "880, 150", "970, 250", "970, 90"};
    private String[] leaderboardBottomSizes = {"728, 90"};
    private String[] rectangleSizes = {"300, 250", "300, 600", "300, 1050"};
    private String[] rectangleBottomSizes = {"300, 250", "160, 600", "300, 600"};
    private String[] skyscraperSizes = {"160, 600"};
    private String[] sponsoredSizes = {"615, 7250"};

    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) throws Exception {
        this.url = url;
    }

    @TestCase(id=2083)
    @Test
    public void profileSummary() {
        page = new BasePage();
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
        String[] inscValues = {"33", "92", "105", "486"};
        m_assert.assertTrue(checkKeys(page, "insc", inscValues), "Incorrect insc");

        String path = "/8905/vitals/profile/summary";
        m_assert.assertTrue(checkSlots(page, "leaderboard_top", leaderboardTopSizes, "1", path), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page, "leaderboard_bottom", leaderboardBottomSizes, "2", path), "Incorrect rectangle bottom");
        m_assert.assertTrue(checkSlots(page, "rectangle", rectangleSizes, "1", path), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page, "rectangle_bottom", rectangleBottomSizes, "2", path), "Incorrect rectangle bottom");
        m_assert.assertTrue(checkSlots(page, "skyscraper", skyscraperSizes, "1", path), "Incorrect skyscraper");
        m_assert.assertAll();
    }

    @TestCase(id=2084)
    @Test
    public void profileReviewsYes() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + profile + "reviews");

        String[] znValues = {"profile", "reviews_yes"};
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
        String[] inscValues = {"33", "92", "105", "486"};
        m_assert.assertTrue(checkKeys(page, "insc", inscValues), "Incorrect insc");

        String path = "/8905/vitals/profile/reviews_yes";
        m_assert.assertTrue(checkSlots(page, "leaderboard_top", leaderboardTopSizes, "1", path), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page, "leaderboard_bottom", leaderboardBottomSizes, "2", path), "Incorrect rectangle bottom");
        m_assert.assertTrue(checkSlots(page, "rectangle", rectangleSizes, "1", path), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page, "rectangle_bottom", rectangleBottomSizes, "2", path), "Incorrect rectangle bottom");

        m_assert.assertAll();
    }

    @TestCase(id=2085)
    @Test
    public void profileReviewsNo() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/doctors/Dr_Angie_Wen/reviews");

        String[] znValues = {"profile", "reviews_no"};
        m_assert.assertTrue(checkKeys(page, "zn", znValues), "Incorrect zn");
        String[] specValues = {"opth"};
        m_assert.assertTrue(checkKeys(page, "spec", specValues), "Incorrect spec");
        String[] pspecValues = {"opth"};
        m_assert.assertTrue(checkKeys(page, "pspec", pspecValues), "Incorrect pspec");
        String[] fspecValues = {"opth-opth"};
        m_assert.assertTrue(checkKeys(page, "fspec", fspecValues), "Incorrect fspec");
        String[] spexValues = {"996", "2153", "6493"};
        m_assert.assertTrue(checkKeys(page, "spex", spexValues), "Incorrect spex");
        String[] midValues = {"13678488"};
        m_assert.assertTrue(checkKeys(page, "mid", midValues), "Incorrect mid");
        String[] inscValues = {"4", "57", "105"};
        m_assert.assertTrue(checkKeys(page, "insc", inscValues), "Incorrect insc");

        String path = "/8905/vitals/profile/reviews_no";
        m_assert.assertTrue(checkSlots(page, "leaderboard_top", leaderboardTopSizes, "1", path), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page, "leaderboard_bottom", leaderboardBottomSizes, "2", path), "Incorrect rectangle bottom");
        m_assert.assertTrue(checkSlots(page, "rectangle", rectangleSizes, "1", path), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page, "rectangle_bottom", rectangleBottomSizes, "2", path), "Incorrect rectangle bottom");

        m_assert.assertAll();
    }

    @TestCase(id=2086)
    @Test
    public void profileCredentials() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + profile + "credentials");

        String[] znValues = {"profile", "credentials"};
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
        String[] inscValues = {"33", "92", "105", "486"};
        m_assert.assertTrue(checkKeys(page, "insc", inscValues), "Incorrect insc");

        String path = "/8905/vitals/profile/credentials";
        m_assert.assertTrue(checkSlots(page, "leaderboard_top", leaderboardTopSizes, "1", path), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page, "leaderboard_bottom", leaderboardBottomSizes, "2", path), "Incorrect rectangle bottom");
        m_assert.assertTrue(checkSlots(page, "rectangle", rectangleSizes, "1", path), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page, "rectangle_bottom", rectangleBottomSizes, "2", path), "Incorrect rectangle bottom");
        m_assert.assertTrue(checkSlots(page, "skyscraper", skyscraperSizes, "1", path), "Incorrect skyscraper");
        m_assert.assertAll();
    }

    @TestCase(id=2087)
    @Test
    public void profileLocations() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + profile + "office-locations");

        String[] znValues = {"profile", "locations"};
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
        String[] inscValues = {"33", "92", "105", "486"};
        m_assert.assertTrue(checkKeys(page, "insc", inscValues), "Incorrect insc");

        String path = "/8905/vitals/profile/locations";
        m_assert.assertTrue(checkSlots(page, "leaderboard_top", leaderboardTopSizes, "1", path), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page, "leaderboard_bottom", leaderboardBottomSizes, "2", path), "Incorrect rectangle bottom");
        m_assert.assertTrue(checkSlots(page, "rectangle", rectangleSizes, "1", path), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page, "rectangle_bottom", rectangleBottomSizes, "2", path), "Incorrect rectangle bottom");
        m_assert.assertAll();
    }

    @TestCase(id=2088)
    @Test
    public void profileInsurance() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + profile + "insurance");

        String[] znValues = {"profile", "insurance"};
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
        String[] inscValues = {"33", "92", "105", "486"};
        m_assert.assertTrue(checkKeys(page, "insc", inscValues), "Incorrect insc");

        String path = "/8905/vitals/profile/insurance";
        m_assert.assertTrue(checkSlots(page, "leaderboard_top", leaderboardTopSizes, "1", path), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page, "leaderboard_bottom", leaderboardBottomSizes, "2", path), "Incorrect rectangle bottom");
        m_assert.assertTrue(checkSlots(page, "rectangle", rectangleSizes, "1", path), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page, "rectangle_bottom", rectangleBottomSizes, "2", path), "Incorrect rectangle bottom");
        m_assert.assertTrue(checkSlots(page, "skyscraper", skyscraperSizes, "1", path), "Incorrect skyscraper");
        m_assert.assertAll();
    }

    @TestCase(id=2089)
    @Test
    public void profileSponsored() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + profile + "sponsored?utm_campaign=otlerax");

        String[] znValues = {"profile", "sponsored"};
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
        String[] inscValues = {"33", "92", "105", "486"};
        m_assert.assertTrue(checkKeys(page, "insc", inscValues), "Incorrect insc");

        String path = "/8905/vitals/profile/sponsored";
        m_assert.assertTrue(checkSlots(page, "leaderboard_top", leaderboardTopSizes, "1", path), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page, "leaderboard_bottom", leaderboardBottomSizes, "2", path), "Incorrect rectangle bottom");
        m_assert.assertTrue(checkSlots(page, "rectangle", rectangleSizes, "1", path), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page, "rectangle_bottom", rectangleBottomSizes, "2", path), "Incorrect rectangle bottom");
        m_assert.assertTrue(checkSlots(page, "sponsored_page", sponsoredSizes, "1", path), "Incorrect sponsored_page");

        m_assert.assertAll();
    }

    @TestCase(id=2090)
    @Test
    public void profileVideo() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url);
        page.get(url + "/doctors/Dr_Craig_Birkby/video");

        String[] znValues = {"profile", "video"};
        m_assert.assertTrue(checkKeys(page, "zn", znValues), "Incorrect zn");
        String[] specValues = {"derm"};
        m_assert.assertTrue(checkKeys(page, "spec", specValues), "Incorrect spec");
        String[] pspecValues = {"derm"};
        m_assert.assertTrue(checkKeys(page, "pspec", pspecValues), "Incorrect pspec");
        String[] fspecValues = {"derm-derm"};
        m_assert.assertTrue(checkKeys(page, "fspec", fspecValues), "Incorrect fspec");
        String[] spexValues = {"49", "47"};
        m_assert.assertTrue(checkKeys(page, "spex", spexValues), "Incorrect spex");
        String[] midValues = {"13573435"};
        m_assert.assertTrue(checkKeys(page, "mid", midValues), "Incorrect mid");
        String[] inscValues = {"33", "14", "83", "91"};
        m_assert.assertTrue(checkKeys(page, "insc", inscValues), "Incorrect insc");

        String path = "/8905/vitals/profile/video";
        m_assert.assertTrue(checkSlots(page, "leaderboard_top", leaderboardTopSizes, "1", path), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page, "leaderboard_bottom", leaderboardBottomSizes, "2", path), "Incorrect rectangle bottom");
        m_assert.assertTrue(checkSlots(page, "rectangle", rectangleSizes, "1", path), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page, "rectangle_bottom", rectangleBottomSizes, "2", path), "Incorrect rectangle bottom");
        m_assert.assertAll();
    }

    @TestCase(id=2091)
    @Test
    public void profileBasic() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.deleteCookies();
        page.get(url + profile);

        String[] znValues = {"profile", "basic"};
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
        String[] inscValues = {"33", "92", "105", "486"};
        m_assert.assertTrue(checkKeys(page, "insc", inscValues), "Incorrect insc");

        String path = "/8905/vitals/profile/basic";
        m_assert.assertTrue(checkSlots(page, "leaderboard_top", leaderboardTopSizes, "1", path), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page, "leaderboard_bottom", leaderboardBottomSizes, "2", path), "Incorrect rectangle bottom");
        m_assert.assertTrue(checkSlots(page, "rectangle", rectangleSizes, "1", path), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page, "rectangle_bottom", rectangleBottomSizes, "2", path), "Incorrect rectangle bottom");
        m_assert.assertAll();
    }

    @TestCase(id=2092)
    @Test
    public void serpFindSpec() {
        page = new BasePage();
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
        m_assert.assertAll();
    }

    @TestCase(id=2093)
    @Test
    public void serpFindSubspec() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/search?type=subspecialty&provider_type=1&specialist_id=135&field_specialty_id=34&q=Cardiovascular+Disease+Practitioner&insurance=35-514");

        String[] znValues = {"serp", "find", "spec", "subspec"};
        m_assert.assertTrue(checkKeys(page, "zn", znValues), "Incorrect zn");
        String[] specValues = {"card"};
        m_assert.assertTrue(checkKeys(page, "spec", specValues), "Incorrect spec");
        String[] pspecValues = {"card"};
        m_assert.assertTrue(checkKeys(page, "pspec", pspecValues), "Incorrect pspec");
        String[] fspecValues = {"intm-cdis"};
        m_assert.assertTrue(checkKeys(page, "fspec", fspecValues), "Incorrect fspec");
        String[] inscValues = {"35"};
        m_assert.assertTrue(checkKeys(page, "insc", inscValues), "Incorrect insc");
        String[] inspValues = {"514"};
        m_assert.assertTrue(checkKeys(page, "insp", inspValues), "Incorrect insp");

        String path = "/8905/vitals/serp/find/spec/subspec";
        m_assert.assertTrue(checkSlots(page, "leaderboard_top", leaderboardTopSizes, "1", path), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page, "rectangle", rectangleSizes, "1", path), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page, "rectangle_bottom", rectangleBottomSizes, "2", path), "Incorrect rectangle bottom");
        m_assert.assertAll();
    }

    @TestCase(id=2094)
    @Test
    public void serpFindCond() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/search?type=condition&provider_type=1&disorder_id=1628&q=Diabetes&insurance=35-514");

        String[] znValues = {"serp", "find", "cond"};
        m_assert.assertTrue(checkKeys(page, "zn", znValues), "Incorrect zn");
        String[] spexValues = {"1628"};
        m_assert.assertTrue(checkKeys(page, "spex", spexValues), "Incorrect spex");
        String[] inscValues = {"35"};
        m_assert.assertTrue(checkKeys(page, "insc", inscValues), "Incorrect insc");
        String[] inspValues = {"514"};
        m_assert.assertTrue(checkKeys(page, "insp", inspValues), "Incorrect insp");

        String path = "/8905/vitals/serp/find/cond";
        m_assert.assertTrue(checkSlots(page, "leaderboard_top", leaderboardTopSizes, "1", path), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page, "rectangle", rectangleSizes, "1", path), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page, "rectangle_bottom", rectangleBottomSizes, "2", path), "Incorrect rectangle bottom");
        m_assert.assertAll();
    }

    @TestCase(id=2095)
    @Test
    public void serpFindName() {
        page = new BasePage();
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
        m_assert.assertAll();
    }

    @TestCase(id=2096)
    @Test
    public void serpBrowseSpec() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/cardiologists");

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
        m_assert.assertAll();
    }

    @TestCase(id=2097)
    @Test
    public void serpBrowseSpecGeo() {
        page = new BasePage();
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
        m_assert.assertAll();
    }

    @TestCase(id=2098)
    @Test
    public void serpBrowseCond() {
        page = new BasePage();
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
        m_assert.assertAll();
    }

    @TestCase(id=2099)
    @Test
    public void serpBrowseSpecIns() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/cardiologists/aetna/aetna-hmo");

        String[] znValues = {"serp", "browse", "spec", "ins"};
        m_assert.assertTrue(checkKeys(page, "zn", znValues), "Incorrect zn");
        String[] specValues = {"card"};
        m_assert.assertTrue(checkKeys(page, "spec", specValues), "Incorrect spec");
        String[] pspecValues = {"card"};
        m_assert.assertTrue(checkKeys(page, "pspec", pspecValues), "Incorrect pspec");
        String[] inscValues = {"34"};
        m_assert.assertTrue(checkKeys(page, "insc", inscValues), "Incorrect insc");
        String[] inspValues = {"122"};
        m_assert.assertTrue(checkKeys(page, "insp", inspValues), "Incorrect insp");

        String path = "/8905/vitals/serp/browse/spec/ins";
        m_assert.assertTrue(checkSlots(page, "leaderboard_top", leaderboardTopSizes, "1", path), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page, "rectangle", rectangleSizes, "1", path), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page, "rectangle_bottom", rectangleBottomSizes, "2", path), "Incorrect rectangle bottom");
        m_assert.assertAll();
    }

    @TestCase(id=2100)
    @Test
    public void serpBrowseSpecInsGeo() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/cardiologists/ny/new-york/aetna");

        String[] znValues = {"serp", "browse", "spec", "ins"};
        m_assert.assertTrue(checkKeys(page, "zn", znValues), "Incorrect zn");
        String[] specValues = {"card"};
        m_assert.assertTrue(checkKeys(page, "spec", specValues), "Incorrect spec");
        String[] pspecValues = {"card"};
        m_assert.assertTrue(checkKeys(page, "pspec", pspecValues), "Incorrect pspec");
        String[] inscValues = {"34"};
        m_assert.assertTrue(checkKeys(page, "insc", inscValues), "Incorrect insc");

        String path = "/8905/vitals/serp/browse/spec/ins";
        m_assert.assertTrue(checkSlots(page, "leaderboard_top", leaderboardTopSizes, "1", path), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page, "rectangle", rectangleSizes, "1", path), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page, "rectangle_bottom", rectangleBottomSizes, "2", path), "Incorrect rectangle bottom");
        m_assert.assertAll();
    }

    @TestCase(id=2101)
    @Test
    public void uccSummary() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + ucc);

        String[] znValues = {"ucc", "ucc_summary"};
        m_assert.assertTrue(checkKeys(page, "zn", znValues), "Incorrect zn");
        String[] svcValues = {"comail", "mininj"};
        m_assert.assertTrue(checkKeys(page, "svc", svcValues), "Incorrect svc");

        String path = "/8905/vitals/ucc/ucc_summary";
        m_assert.assertTrue(checkSlots(page, "leaderboard_top", leaderboardTopSizes, "1", path), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page, "rectangle", rectangleSizes, "1", path), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page, "rectangle_bottom", rectangleBottomSizes, "2", path), "Incorrect rectangle bottom");
        m_assert.assertAll();
    }

    @TestCase(id=2102)
    @Test
    public void uccAbout() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + ucc + "about");

        String[] znValues = {"ucc", "ucc_about"};
        m_assert.assertTrue(checkKeys(page, "zn", znValues), "Incorrect zn");
        String[] svcValues = {"comail", "mininj"};
        m_assert.assertTrue(checkKeys(page, "svc", svcValues), "Incorrect svc");

        String path = "/8905/vitals/ucc/ucc_about";
        m_assert.assertTrue(checkSlots(page, "leaderboard_top", leaderboardTopSizes, "1", path), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page, "rectangle", rectangleSizes, "1", path), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page, "rectangle_bottom", rectangleBottomSizes, "2", path), "Incorrect rectangle bottom");
        m_assert.assertAll();
    }

    @TestCase(id=2103)
    @Test
    public void uccReviews() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + ucc + "reviews");

        String[] znValues = {"ucc", "ucc_reviews"};
        m_assert.assertTrue(checkKeys(page, "zn", znValues), "Incorrect zn");
        String[] svcValues = {"comail", "mininj"};
        m_assert.assertTrue(checkKeys(page, "svc", svcValues), "Incorrect svc");

        String path = "/8905/vitals/ucc/ucc_reviews";
        m_assert.assertTrue(checkSlots(page, "leaderboard_top", leaderboardTopSizes, "1", path), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page, "rectangle", rectangleSizes, "1", path), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page, "rectangle_bottom", rectangleBottomSizes, "2", path), "Incorrect rectangle bottom");
        m_assert.assertAll();
    }

    @TestCase(id=2104)
    @Test
    public void uccServices() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + ucc + "services");

        String[] znValues = {"ucc", "ucc_services"};
        m_assert.assertTrue(checkKeys(page, "zn", znValues), "Incorrect zn");
        String[] svcValues = {"comail", "mininj"};
        m_assert.assertTrue(checkKeys(page, "svc", svcValues), "Incorrect svc");

        String path = "/8905/vitals/ucc/ucc_services";
        m_assert.assertTrue(checkSlots(page, "leaderboard_top", leaderboardTopSizes, "1", path), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page, "rectangle", rectangleSizes, "1", path), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page, "rectangle_bottom", rectangleBottomSizes, "2", path), "Incorrect rectangle bottom");
        m_assert.assertAll();
    }

    @TestCase(id=2105)
    @Test
    public void uccSerpFind() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/search?type=name&provider_type=10&q=");

        String[] znValues = {"ucc", "ucc_serp"};
        m_assert.assertTrue(checkKeys(page, "zn", znValues), "Incorrect zn");

        String path = "/8905/vitals/ucc/ucc_serp";
        m_assert.assertTrue(checkSlots(page, "leaderboard_top", leaderboardTopSizes, "1", path), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page, "rectangle", rectangleSizes, "1", path), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page, "rectangle_bottom", rectangleBottomSizes, "2", path), "Incorrect rectangle bottom");
        m_assert.assertAll();
    }

    @TestCase(id=2106)
    @Test
    public void uccSerpBrowse() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/urgent-care");

        String[] znValues = {"ucc", "ucc_serp"};
        m_assert.assertTrue(checkKeys(page, "zn", znValues), "Incorrect zn");

        String path = "/8905/vitals/ucc/ucc_serp";
        m_assert.assertTrue(checkSlots(page, "leaderboard_top", leaderboardTopSizes, "1", path), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page, "rectangle", rectangleSizes, "1", path), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page, "rectangle_bottom", rectangleBottomSizes, "2", path), "Incorrect rectangle bottom");
        m_assert.assertAll();
    }

    @TestCase(id=2107)
    @Test
    public void uccSerpBrowseGeo() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/urgent-care/ny/new-york");

        String[] znValues = {"ucc", "ucc_serp"};
        m_assert.assertTrue(checkKeys(page, "zn", znValues), "Incorrect zn");

        String path = "/8905/vitals/ucc/ucc_serp";
        m_assert.assertTrue(checkSlots(page, "leaderboard_top", leaderboardTopSizes, "1", path), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page, "rectangle", rectangleSizes, "1", path), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page, "rectangle_bottom", rectangleBottomSizes, "2", path), "Incorrect rectangle bottom");
        m_assert.assertAll();
    }

    @TestCase(id=2108)
    @Test
    public void conditionCenter() {
        page = new BasePage();
        m_assert = new SoftAssert();
        String[] znValues = {"condition_centers"};
        String[] kwValues = {"cc_high_chole"};
        String[] specValues = {"card", "ctsg"};

        for (String condUrl: conditionCenters) {
            page.get(url + condUrl);

            //m_assert.assertTrue(checkKeys(page, "zn", znValues), "Incorrect zn " + condUrl);
            m_assert.assertTrue(checkKeys(page, "kw", kwValues), "Incorrect kw " + condUrl);
            m_assert.assertTrue(checkKeys(page, "spec", specValues), "Incorrect spec " + condUrl);
            String path = "/8905/vitals/condition_centers";
            m_assert.assertTrue(checkSlots(page, "leaderboard_top", leaderboardTopSizes, "1", path), "Incorrect leaderboard top " + condUrl);
            m_assert.assertTrue(checkSlots(page, "rectangle", rectangleSizes, "1", path), "Incorrect rectangle " + condUrl);
        }

        m_assert.assertAll();
    }

    @TestCase(id=2109)
    @Test
    public void patientGuide() {
        page = new BasePage();
        m_assert = new SoftAssert();
        String[] znValues = {"patient_education"};
        String[] kwValues = {"pg_infertility"};
        String[] specValues = {"obgn", "mgen"};

        for (String pgUrl: patientGuides) {
            page.get(url + pgUrl);

            //m_assert.assertTrue(checkKeys(page, "zn", znValues), "Incorrect zn " + pgUrl);
            m_assert.assertTrue(checkKeys(page, "kw", kwValues), "Incorrect kw " + pgUrl);
            m_assert.assertTrue(checkKeys(page, "spec", specValues), "Incorrect spec " + pgUrl);
            String path = "/8905/vitals/patient_education/";
            if (pgUrl.contains("the-team"))
                path = path.concat("the_team");
            else if (pgUrl.contains("how-to-prepare"))
                path = path.concat("how_to_prepare");
            else if (pgUrl.contains("questions-to-ask"))
                path = path.concat("questions_to_ask");
            else if (pgUrl.contains("what-to-expect"))
                path = path.concat("what_to_expect");
            else if (pgUrl.contains("treatment-options"))
                path = path.concat("treatment_options");
            else
                path = path.concat("overview");
            m_assert.assertTrue(checkSlots(page, "leaderboard_top", leaderboardTopSizes, "1", path), "Incorrect leaderboard top " + pgUrl);
            m_assert.assertTrue(checkSlots(page, "rectangle", rectangleSizes, "1", path), "Incorrect rectangle " + pgUrl);
        }

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
                        Reporter.log("Missing/incorrect value for " + key + ", expecting " + values[j] + " <br>");
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
                if (!((Long) page.executeJS(command)).toString().equals(pos)) {
                    result = false;
                    Reporter.log("Incorrect pos for " + slot + ", expecting " + pos + " <br>");
                }
                command = "return window.app.advert.googletag.logs['defineSlot'][" + i + "][0].size";
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
