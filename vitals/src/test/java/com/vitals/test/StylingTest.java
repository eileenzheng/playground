package com.vitals.test;

import com.vitals.pages.SearchResultsPage;
import com.vitals.pages.profile.ProfileCommonPage;
import com.vitals.pages.ucc.UccProfileSummaryPage;
import com.vitals.pages.ucc.UccSearchResultsPage;
import com.vitalsqa.testrail.TestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class StylingTest {

    String url;
    SoftAssert m_assert;

    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) throws Exception {
        this.url = url;
    }

    @TestCase(id=2480)
    @Test
    public void zindexProfile() {
        ProfileCommonPage page = new ProfileCommonPage();
        m_assert = new SoftAssert();

        page.get(url + "/doctors/Dr_Adelle_Quintana");
        page.get(url + "/doctors/Dr_Adelle_Quintana");

//        for (int i=0; i<page.divAdvertBox().size(); i++) {
//            m_assert.assertTrue(page.getStyle(page.divAdvertBox().get(i), "z-index").equals("5001"), "Advert Box " + i);
//        }

        m_assert.assertTrue(page.getStyle(page.headerModule().divMainHeader(), "z-index").equals("5000001") || page.getStyle(page.headerModule().divMainHeader(), "z-index").equals("5000000"), ".main-header");

        page.reviewQuestionMark().click();
        m_assert.assertTrue(page.getStyle(page.divTooltip(), "z-index").equals("6000001") || page.getStyle(page.divTooltip(), "z-index").equals("6000000"), "Tooltip");

        page.plBookAppt().click();
        m_assert.assertTrue(page.getStyle(page.divModal(), "z-index").equals("6000000"), "Modal");
        m_assert.assertTrue(page.getStyle(page.divModalBackdrop(), "z-index").equals("5999999") || page.getStyle(page.divModalBackdrop(), "z-index").equals("6000000"), "Modal Backdrop");

        m_assert.assertAll();
    }

    @TestCase(id=2481)
    @Test
    public void zindexUccProfile() {
        UccProfileSummaryPage page = new UccProfileSummaryPage();
        m_assert = new SoftAssert();

        page.get(url + "/urgent-care/emergency-medical-care");

        for (int i=0; i<page.divAdvertBox().size(); i++) {
            m_assert.assertTrue(page.getStyle(page.divAdvertBox().get(i), "z-index").equals("5001"), "Advert Box " + i);
        }
        m_assert.assertTrue(page.getStyle(page.divAdvertWrapper(), "z-index").equals("5001"), "Advert Wrapper");

        m_assert.assertTrue(page.getStyle(page.headerModule().divMainHeader(), "z-index").equals("5000001") || page.getStyle(page.headerModule().divMainHeader(), "z-index").equals("5000000"), ".main-header");

        m_assert.assertAll();
    }

    @TestCase(id=2482)
    @Test
    public void zindexSerp() {
        SearchResultsPage page = new SearchResultsPage();
        m_assert = new SoftAssert();

        page.get(url + "/dermatologists");

        for (int i=0; i<page.refinement().dropdowns().size(); i++) {
            m_assert.assertTrue(page.getStyle(page.refinement().dropdowns().get(i), "z-index").equals("5000000"), "Dropdown " + i);
        }

        m_assert.assertTrue(page.getStyle(page.ads().get(0), "z-index").equals("5001"), "leaderboard_top");
        m_assert.assertTrue(page.getStyle(page.ads().get(1), "z-index").equals("5001"), "rectangle");
        m_assert.assertTrue(page.getStyle(page.ads().get(2), "z-index").equals("5001"), "rectangle_bottom");

        m_assert.assertTrue(page.getStyle(page.headerModule().divMainHeader(), "z-index").equals("5000001") || page.getStyle(page.headerModule().divMainHeader(), "z-index").equals("5000000"), ".main-header");

        m_assert.assertAll();
    }

    @TestCase(id=2483)
    @Test
    public void zindexUccSerp() {
        UccSearchResultsPage page = new UccSearchResultsPage();
        m_assert = new SoftAssert();

        page.get(url + "/urgent-care");

        m_assert.assertTrue(page.getStyle(page.refinement().dropdowns().get(0), "z-index").equals("5000000"), "Dropdown Sort");
        m_assert.assertTrue(page.getStyle(page.refinement().dropdowns().get(1), "z-index").equals("5000000"), "Dropdown Distance");

        m_assert.assertTrue(page.getStyle(page.ads().get(0), "z-index").equals("5001"), "leaderboard_top");
        m_assert.assertTrue(page.getStyle(page.ads().get(1), "z-index").equals("5001"), "rectangle");
        m_assert.assertTrue(page.getStyle(page.ads().get(2), "z-index").equals("5001"), "rectangle_bottom");

        m_assert.assertTrue(page.getStyle(page.headerModule().divMainHeader(), "z-index").equals("5000001") || page.getStyle(page.headerModule().divMainHeader(), "z-index").equals("5000000"), ".main-header");

        m_assert.assertAll();
    }
}
