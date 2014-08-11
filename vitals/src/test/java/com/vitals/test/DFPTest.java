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
    private final String profile = "/doctors/Dr_Lisa_Eng/";
    private final String ucc = "/urgent-care/citymd-new-york-3/";
    private final String[] conditionCenters = {"/topics/high-cholesterol", "/topics/high-cholesterol/should-children-be-checked",
            "/topics/high-cholesterol/things-to-consider", "/topics/high-cholesterol/lifestyle-matters", "/topics/high-cholesterol/specialist-vs-pcp"};
    private final String[] patientGuides = {"/patient-education/infertility", "/patient-education/infertility/the-team",
            "/patient-education/infertility/how-to-prepare", "/patient-education/infertility/questions-to-ask", "/patient-education/infertility/what-to-expect", "/patient-education/infertility/treatment-options"};
    private String[] leaderboardTopSizes = {"[728, 90]", "[880, 150]", "[970, 250]", "[970, 90]"};
    private String[] rectangleSizes = {"[300, 250]", "[300, 600]", "[300, 1050]"};
    private String[] rectangleBottomSizes = {"[300, 250]", "[160, 600]", "[300, 600]"};
    private String[] skyscraperSizes = {"[160, 600]"};
    private String[] sponsoredSizes = {"[615, 7250]"};

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

        m_assert.assertTrue(page.getPageSource().contains("/8905/vitals/profile/summary"), "Incorrect ad unit zones");

        String[] znValues = {"profile", "summary"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "zn", znValues), "Incorrect zn");
        String[] specValues = {"obgn"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        String[] pspecValues = {"obgn"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "pspec", pspecValues), "Incorrect pspec");
        String[] fspecValues = {"obgn-obgn"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "fspec", fspecValues), "Incorrect fspec");
        String[] spexValues = {"816", "2964", "3957"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spex", spexValues), "Incorrect spex");
        String[] midValues = {"13607954"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "mid", midValues), "Incorrect mid");
        String[] inscValues = {"35", "72", "311", "270"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "insc", inscValues), "Incorrect insc");

        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle_bottom", rectangleBottomSizes, "2"), "Incorrect rectangle bottom");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "skyscraper", skyscraperSizes, "1"), "Incorrect skyscraper");
        m_assert.assertAll();
    }

    @TestCase(id=2084)
    @Test
    public void profileReviewsYes() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + profile + "reviews");

        m_assert.assertTrue(page.getPageSource().contains("/8905/vitals/profile/reviews_yes"), "Incorrect ad unit zones");

        String[] znValues = {"profile", "reviews_yes"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "zn", znValues), "Incorrect zn");
        String[] specValues = {"obgn"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        String[] pspecValues = {"obgn"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "pspec", pspecValues), "Incorrect pspec");
        String[] fspecValues = {"obgn-obgn"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "fspec", fspecValues), "Incorrect fspec");
        String[] spexValues = {"816", "2964", "3957"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spex", spexValues), "Incorrect spex");
        String[] midValues = {"13607954"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "mid", midValues), "Incorrect mid");
        String[] inscValues = {"35", "72", "311", "270"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "insc", inscValues), "Incorrect insc");

        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle_bottom", rectangleBottomSizes, "2"), "Incorrect rectangle bottom");

        m_assert.assertAll();
    }

    @TestCase(id=2085)
    @Test
    public void profileReviewsNo() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/doctors/Dr_Angie_Wen/reviews");

        m_assert.assertTrue(page.getPageSource().contains("/8905/vitals/profile/reviews_no"), "Incorrect ad unit zones");

        String[] znValues = {"profile", "reviews_no"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "zn", znValues), "Incorrect zn");
        String[] specValues = {"opth"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        String[] pspecValues = {"opth"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "pspec", pspecValues), "Incorrect pspec");
        String[] fspecValues = {"opth-opth"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "fspec", fspecValues), "Incorrect fspec");
        String[] spexValues = {"996", "2153", "6493"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spex", spexValues), "Incorrect spex");
        String[] midValues = {"13678488"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "mid", midValues), "Incorrect mid");
        String[] inscValues = {"66", "217", "270"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "insc", inscValues), "Incorrect insc");

        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle_bottom", rectangleBottomSizes, "2"), "Incorrect rectangle bottom");

        m_assert.assertAll();
    }

    @TestCase(id=2086)
    @Test
    public void profileCredentials() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + profile + "credentials");

        m_assert.assertTrue(page.getPageSource().contains("/8905/vitals/profile/credentials"), "Incorrect ad unit zones");

        String[] znValues = {"profile", "credentials"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "zn", znValues), "Incorrect zn");
        String[] specValues = {"obgn"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        String[] pspecValues = {"obgn"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "pspec", pspecValues), "Incorrect pspec");
        String[] fspecValues = {"obgn-obgn"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "fspec", fspecValues), "Incorrect fspec");
        String[] spexValues = {"816", "2964", "3957"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spex", spexValues), "Incorrect spex");
        String[] midValues = {"13607954"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "mid", midValues), "Incorrect mid");
        String[] inscValues = {"35", "72", "311", "270"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "insc", inscValues), "Incorrect insc");

        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle_bottom", rectangleBottomSizes, "2"), "Incorrect rectangle bottom");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "skyscraper", skyscraperSizes, "1"), "Incorrect skyscraper");
        m_assert.assertAll();
    }

    @TestCase(id=2087)
    @Test
    public void profileLocations() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + profile + "office-locations");

        m_assert.assertTrue(page.getPageSource().contains("/8905/vitals/profile/locations"), "Incorrect ad unit zones");

        String[] znValues = {"profile", "locations"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "zn", znValues), "Incorrect zn");
        String[] specValues = {"obgn"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        String[] pspecValues = {"obgn"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "pspec", pspecValues), "Incorrect pspec");
        String[] fspecValues = {"obgn-obgn"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "fspec", fspecValues), "Incorrect fspec");
        String[] spexValues = {"816", "2964", "3957"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spex", spexValues), "Incorrect spex");
        String[] midValues = {"13607954"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "mid", midValues), "Incorrect mid");
        String[] inscValues = {"35", "72", "311", "270"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "insc", inscValues), "Incorrect insc");

        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle_bottom", rectangleBottomSizes, "2"), "Incorrect rectangle bottom");
        m_assert.assertAll();
    }

    @TestCase(id=2088)
    @Test
    public void profileInsurance() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + profile + "insurance");

        m_assert.assertTrue(page.getPageSource().contains("/8905/vitals/profile/insurance"), "Incorrect ad unit zones");

        String[] znValues = {"profile", "insurance"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "zn", znValues), "Incorrect zn");
        String[] specValues = {"obgn"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        String[] pspecValues = {"obgn"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "pspec", pspecValues), "Incorrect pspec");
        String[] fspecValues = {"obgn-obgn"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "fspec", fspecValues), "Incorrect fspec");
        String[] spexValues = {"816", "2964", "3957"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spex", spexValues), "Incorrect spex");
        String[] midValues = {"13607954"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "mid", midValues), "Incorrect mid");
        String[] inscValues = {"35", "72", "311", "270"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "insc", inscValues), "Incorrect insc");

        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle_bottom", rectangleBottomSizes, "2"), "Incorrect rectangle bottom");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "skyscraper", skyscraperSizes, "1"), "Incorrect skyscraper");
        m_assert.assertAll();
    }

    @TestCase(id=2089)
    @Test
    public void profileSponsored() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + profile + "sponsored?utm_campaign=otlerax");

        m_assert.assertTrue(page.getPageSource().contains("/8905/vitals/profile/sponsored"), "Incorrect ad unit zones");

        String[] znValues = {"profile", "sponsored"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "zn", znValues), "Incorrect zn");
        String[] specValues = {"obgn"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        String[] pspecValues = {"obgn"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "pspec", pspecValues), "Incorrect pspec");
        String[] fspecValues = {"obgn-obgn"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "fspec", fspecValues), "Incorrect fspec");
        String[] spexValues = {"816", "2964", "3957"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spex", spexValues), "Incorrect spex");
        String[] midValues = {"13607954"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "mid", midValues), "Incorrect mid");
        String[] inscValues = {"35", "72", "311", "270"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "insc", inscValues), "Incorrect insc");

        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle_bottom", rectangleBottomSizes, "2"), "Incorrect rectangle bottom");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "sponsored_page", sponsoredSizes, "1"), "Incorrect sponsored_page");

        m_assert.assertAll();
    }

    @TestCase(id=2090)
    @Test
    public void profileVideo() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url);
        page.get(url + "/doctors/Dr_Todd_Rosengart/video");

        m_assert.assertTrue(page.getPageSource().contains("/8905/vitals/profile/video"), "Incorrect ad unit zones");

        String[] znValues = {"profile", "video"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "zn", znValues), "Incorrect zn");
        String[] specValues = {"surg", "ctsg", "famp"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        String[] pspecValues = {"surg"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "pspec", pspecValues), "Incorrect pspec");
        String[] fspecValues = {"ctsg-ctsg", "surg-surg", "famp-amed"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "fspec", fspecValues), "Incorrect fspec");
        String[] spexValues = {"953", "958", "959"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spex", spexValues), "Incorrect spex");
        String[] midValues = {"13716928"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "mid", midValues), "Incorrect mid");

        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle_bottom", rectangleBottomSizes, "2"), "Incorrect rectangle bottom");
        m_assert.assertAll();
    }

    @TestCase(id=2091)
    @Test
    public void profileBasic() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.deleteCookies();
        page.get(url + profile);

        m_assert.assertTrue(page.getPageSource().contains("/8905/vitals/profile/basic"), "Incorrect ad unit zones");

        String[] znValues = {"profile", "basic"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "zn", znValues), "Incorrect zn");
        String[] specValues = {"obgn"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        String[] pspecValues = {"obgn"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "pspec", pspecValues), "Incorrect pspec");
        String[] fspecValues = {"obgn-obgn"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "fspec", fspecValues), "Incorrect fspec");
        String[] spexValues = {"816", "2964", "3957"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spex", spexValues), "Incorrect spex");
        String[] midValues = {"13607954"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "mid", midValues), "Incorrect mid");
        String[] inscValues = {"35", "72", "311", "270"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "insc", inscValues), "Incorrect insc");

        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle_bottom", rectangleBottomSizes, "2"), "Incorrect rectangle bottom");
        m_assert.assertAll();
    }

    @TestCase(id=2092)
    @Test
    public void serpFindSpec() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/search?type=specialty&provider_type=1&specialist_id=135&q=Cardiologist+(heart)&insurance=35-514");

        m_assert.assertTrue(page.getPageSource().contains("/8905/vitals/serp/find/spec"), "Incorrect ad unit zones");

        String[] znValues = {"serp", "find", "spec"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "zn", znValues), "Incorrect zn");
        String[] specValues = {"card"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        String[] pspecValues = {"card"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "pspec", pspecValues), "Incorrect pspec");
        String[] inscValues = {"35"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "insc", inscValues), "Incorrect insc");
        String[] inspValues = {"514"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "insp", inspValues), "Incorrect insp");

        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle_bottom", rectangleBottomSizes, "2"), "Incorrect rectangle bottom");
        m_assert.assertAll();
    }

    @TestCase(id=2093)
    @Test
    public void serpFindSubspec() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/search?type=subspecialty&provider_type=1&specialist_id=135&field_specialty_id=34&q=Cardiovascular+Disease+Practitioner&insurance=35-514");

        m_assert.assertTrue(page.getPageSource().contains("/8905/vitals/serp/find/spec/subspec"), "Incorrect ad unit zones");

        String[] znValues = {"serp", "find", "spec", "subspec"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "zn", znValues), "Incorrect zn");
        String[] specValues = {"card"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        String[] pspecValues = {"card"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "pspec", pspecValues), "Incorrect pspec");
        String[] fspecValues = {"intm-cdis"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "fspec", fspecValues), "Incorrect fspec");
        String[] inscValues = {"35"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "insc", inscValues), "Incorrect insc");
        String[] inspValues = {"514"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "insp", inspValues), "Incorrect insp");

        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle_bottom", rectangleBottomSizes, "2"), "Incorrect rectangle bottom");
        m_assert.assertAll();
    }

    @TestCase(id=2094)
    @Test
    public void serpFindCond() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/search?type=condition&provider_type=1&disorder_id=1628&q=Diabetes&insurance=35-514");

        m_assert.assertTrue(page.getPageSource().contains("/8905/vitals/serp/find/cond"), "Incorrect ad unit zones");

        String[] znValues = {"serp", "find", "cond"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "zn", znValues), "Incorrect zn");
        String[] spexValues = {"1628"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spex", spexValues), "Incorrect spex");
        String[] inscValues = {"35"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "insc", inscValues), "Incorrect insc");
        String[] inspValues = {"514"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "insp", inspValues), "Incorrect insp");

        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle_bottom", rectangleBottomSizes, "2"), "Incorrect rectangle bottom");
        m_assert.assertAll();
    }

    @TestCase(id=2095)
    @Test
    public void serpFindName() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/search?type=name&provider_type=1&q=todd&insurance=35-514");

        m_assert.assertTrue(page.getPageSource().contains("/8905/vitals/serp/find/name"), "Incorrect ad unit zones");

        String[] znValues = {"serp", "find", "name"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "zn", znValues), "Incorrect zn");
        String[] kwValues = {"name"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "kw", kwValues), "Incorrect kw");
        String[] inscValues = {"35"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "insc", inscValues), "Incorrect insc");
        String[] inspValues = {"514"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "insp", inspValues), "Incorrect insp");

        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle_bottom", rectangleBottomSizes, "2"), "Incorrect rectangle bottom");
        m_assert.assertAll();
    }

    @TestCase(id=2096)
    @Test
    public void serpBrowseSpec() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/cardiologists");

        m_assert.assertTrue(page.getPageSource().contains("/8905/vitals/serp/browse/spec"), "Incorrect ad unit zones");

        String[] znValues = {"serp", "browse", "spec"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "zn", znValues), "Incorrect zn");
        String[] specValues = {"card"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        String[] pspecValues = {"card"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "pspec", pspecValues), "Incorrect pspec");

        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle_bottom", rectangleBottomSizes, "2"), "Incorrect rectangle bottom");
        m_assert.assertAll();
    }

    @TestCase(id=2097)
    @Test
    public void serpBrowseSpecGeo() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/cardiologists/ny/new-york");

        m_assert.assertTrue(page.getPageSource().contains("/8905/vitals/serp/browse/spec"), "Incorrect ad unit zones");

        String[] znValues = {"serp", "browse", "spec"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "zn", znValues), "Incorrect zn");
        String[] specValues = {"card"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        String[] pspecValues = {"card"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "pspec", pspecValues), "Incorrect pspec");

        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle_bottom", rectangleBottomSizes, "2"), "Incorrect rectangle bottom");
        m_assert.assertAll();
    }

    @TestCase(id=2098)
    @Test
    public void serpBrowseCond() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/condition/diabetes");

        m_assert.assertTrue(page.getPageSource().contains("/8905/vitals/serp/browse/cond"), "Incorrect ad unit zones");

        String[] znValues = {"serp", "browse", "cond"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "zn", znValues), "Incorrect zn");
        String[] spexValues = {"1628"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spex", spexValues), "Incorrect spex");

        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle_bottom", rectangleBottomSizes, "2"), "Incorrect rectangle bottom");
        m_assert.assertAll();
    }

    @TestCase(id=2099)
    @Test
    public void serpBrowseSpecIns() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/cardiologists/united-healthcare/definity-ppo");

        m_assert.assertTrue(page.getPageSource().contains("/8905/vitals/serp/browse/spec/ins"), "Incorrect ad unit zones");

        String[] znValues = {"serp", "browse", "spec", "ins"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "zn", znValues), "Incorrect zn");
        String[] specValues = {"card"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        String[] pspecValues = {"card"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "pspec", pspecValues), "Incorrect pspec");
        String[] inscValues = {"35"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "insc", inscValues), "Incorrect insc");
        String[] inspValues = {"514"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "insp", inspValues), "Incorrect insp");

        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle_bottom", rectangleBottomSizes, "2"), "Incorrect rectangle bottom");
        m_assert.assertAll();
    }

    @TestCase(id=2100)
    @Test
    public void serpBrowseSpecInsGeo() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/cardiologists/ny/new-york/united-healthcare");

        m_assert.assertTrue(page.getPageSource().contains("/8905/vitals/serp/browse/spec/ins"), "Incorrect ad unit zones");

        String[] znValues = {"serp", "browse", "spec", "ins"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "zn", znValues), "Incorrect zn");
        String[] specValues = {"card"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        String[] pspecValues = {"card"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "pspec", pspecValues), "Incorrect pspec");
        String[] inscValues = {"35"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "insc", inscValues), "Incorrect insc");

        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle_bottom", rectangleBottomSizes, "2"), "Incorrect rectangle bottom");
        m_assert.assertAll();
    }

    @TestCase(id=2101)
    @Test
    public void uccSummary() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + ucc);

        m_assert.assertTrue(page.getPageSource().contains("/8905/vitals/ucc/ucc_summary"), "Incorrect ad unit zones");

        String[] znValues = {"ucc", "ucc_summary"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "zn", znValues), "Incorrect zn");
        String[] svcValues = {"comail", "mininj"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "svc", svcValues), "Incorrect svc");

        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle_bottom", rectangleBottomSizes, "2"), "Incorrect rectangle bottom");
        m_assert.assertAll();
    }

    @TestCase(id=2102)
    @Test
    public void uccAbout() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + ucc + "about");

        m_assert.assertTrue(page.getPageSource().contains("/8905/vitals/ucc/ucc_about"), "Incorrect ad unit zones");

        String[] znValues = {"ucc", "ucc_about"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "zn", znValues), "Incorrect zn");
        String[] svcValues = {"comail", "mininj"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "svc", svcValues), "Incorrect svc");

        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle_bottom", rectangleBottomSizes, "2"), "Incorrect rectangle bottom");
        m_assert.assertAll();
    }

    @TestCase(id=2103)
    @Test
    public void uccReviews() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + ucc + "reviews");

        m_assert.assertTrue(page.getPageSource().contains("/8905/vitals/ucc/ucc_reviews"), "Incorrect ad unit zones");

        String[] znValues = {"ucc", "ucc_reviews"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "zn", znValues), "Incorrect zn");
        String[] svcValues = {"comail", "mininj"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "svc", svcValues), "Incorrect svc");

        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle_bottom", rectangleBottomSizes, "2"), "Incorrect rectangle bottom");
        m_assert.assertAll();
    }

    @TestCase(id=2104)
    @Test
    public void uccServices() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + ucc + "services");

        m_assert.assertTrue(page.getPageSource().contains("/8905/vitals/ucc/ucc_services"), "Incorrect ad unit zones");

        String[] znValues = {"ucc", "ucc_services"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "zn", znValues), "Incorrect zn");
        String[] svcValues = {"comail", "mininj"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "svc", svcValues), "Incorrect svc");

        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle_bottom", rectangleBottomSizes, "2"), "Incorrect rectangle bottom");
        m_assert.assertAll();
    }

    @TestCase(id=2105)
    @Test
    public void uccSerpFind() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/search?type=name&provider_type=10&q=");

        m_assert.assertTrue(page.getPageSource().contains("/8905/vitals/ucc/ucc_serp"), "Incorrect ad unit zones");

        String[] znValues = {"ucc", "ucc_serp"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "zn", znValues), "Incorrect zn");

        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle_bottom", rectangleBottomSizes, "2"), "Incorrect rectangle bottom");
        m_assert.assertAll();
    }

    @TestCase(id=2106)
    @Test
    public void uccSerpBrowse() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/urgent-care");

        m_assert.assertTrue(page.getPageSource().contains("/8905/vitals/ucc/ucc_serp"), "Incorrect ad unit zones");

        String[] znValues = {"ucc", "ucc_serp"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "zn", znValues), "Incorrect zn");

        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle_bottom", rectangleBottomSizes, "2"), "Incorrect rectangle bottom");
        m_assert.assertAll();
    }

    @TestCase(id=2107)
    @Test
    public void uccSerpBrowseGeo() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/urgent-care/ny/new-york");

        m_assert.assertTrue(page.getPageSource().contains("/8905/vitals/ucc/ucc_serp"), "Incorrect ad unit zones");

        String[] znValues = {"ucc", "ucc_serp"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "zn", znValues), "Incorrect zn");

        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle_bottom", rectangleBottomSizes, "2"), "Incorrect rectangle bottom");
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

            m_assert.assertTrue(page.getPageSource().contains("/8905/vitals/condition_centers"), "Incorrect ad unit zones " + condUrl);
            //m_assert.assertTrue(checkKeys(page.getPageSource(), "zn", znValues), "Incorrect zn " + condUrl);
            m_assert.assertTrue(checkKeys(page.getPageSource(), "kw", kwValues), "Incorrect kw " + condUrl);
            m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec " + condUrl);
            m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top " + condUrl);
            m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle " + condUrl);
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

            m_assert.assertTrue(page.getPageSource().contains("/8905/vitals/patient_education"), "Incorrect ad unit zones " + pgUrl);
            //m_assert.assertTrue(checkKeys(page.getPageSource(), "zn", znValues), "Incorrect zn " + pgUrl);
            m_assert.assertTrue(checkKeys(page.getPageSource(), "kw", kwValues), "Incorrect kw " + pgUrl);
            m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec " + pgUrl);
            m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top " + pgUrl);
            m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle " + pgUrl);
        }

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
            Reporter.log("Missing slot: " + slot + " not found <br>");
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
