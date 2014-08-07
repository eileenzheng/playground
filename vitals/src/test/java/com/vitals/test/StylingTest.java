package com.vitals.test;

import com.vitals.pages.BasePage;
import com.vitalsqa.testrail.TestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class StylingTest {

    String url;
    SoftAssert m_assert;
    BasePage page;
    private final String profile = "/doctors/Dr_Emile_Bacha/";

    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) throws Exception {
        this.url = url;
    }

    @Test
    @TestCase(id=2459)
    public void marginBelowMasthead() {
        page = new BasePage();
        m_assert = new SoftAssert();

        page.get(url + "/internists");
        m_assert.assertTrue(page.getStyle(page.divMaincontent(), "margin-top").equals("10px"), "SERP");

        page.get(url + profile + "profile");
        m_assert.assertTrue(page.getStyle(page.divMaincontent(), "margin-top").equals("10px"), "Profile Summary");

        page.get(url + profile + "reviews");
        m_assert.assertTrue(page.getStyle(page.divMaincontent(), "margin-top").equals("10px"), "Profile Reviews");

        page.get(url + profile + "credentials");
        m_assert.assertTrue(page.getStyle(page.divMaincontent(), "margin-top").equals("10px"), "Profile Credentials");

        page.get(url + profile + "office-locations");
        m_assert.assertTrue(page.getStyle(page.divMaincontent(), "margin-top").equals("10px"), "Profile Locations");

        page.get(url + profile + "insurance");
        m_assert.assertTrue(page.getStyle(page.divMaincontent(), "margin-top").equals("10px"), "Profile Insurance");

        page.get(url + profile + "video");
        m_assert.assertTrue(page.getStyle(page.divMaincontent(), "margin-top").equals("10px"), "Profile Video");

        m_assert.assertAll();
    }
}
