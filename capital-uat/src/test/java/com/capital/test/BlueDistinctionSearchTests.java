package com.capital.test;

import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import com.capital.pages.ResultsPage;
import com.capital.pages.SpecialtyTypeSearchPage;

/**
 * Capital Blue Distinction Test Suite
 */
public class BlueDistinctionSearchTests {

    private String url;

    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) {
        this.url = url;
    }

    @AfterMethod
    public void shutdown() {
    }

    @Test (dataProvider = "state")
    public void bariatricSurgerySearchFilterTest(String state) {
        SoftAssert m_assert = new SoftAssert();

        SpecialtyTypeSearchPage specialtySearch = new SpecialtyTypeSearchPage();

        specialtySearch.go(url);

        // Facility Search
        specialtySearch.selectProviderType("Facility");

        specialtySearch.enterProviderLocation(state);

        ResultsPage results = specialtySearch.clickSearchButton();

        results.clickFilterYourSearch();

        results.filter.clickBariatricSurgeryCheckbox();

        results.filter.clickFilterSearchButton();

        String fac;
        // Check for the image
        for (FluentWebElement result : results.resultsList()) {
            fac = results.getFacilityName(result);
            m_assert.assertTrue(results.facilityHasBlueDistinction(result,"bd_bariatric.jpg"),
                    "Result for " + fac + " did not contain image");
            Reporter.log(fac + " has bd_bariatric.jpg.");
        }

    }

    @Test (dataProvider = "state")
    public void cardiacCareSearchFilterTest(String state) {
        SoftAssert m_assert = new SoftAssert();

        SpecialtyTypeSearchPage specialtySearch = new SpecialtyTypeSearchPage();

        specialtySearch.go(url);

        // Facility Search
        specialtySearch.selectProviderType("Facility");

        specialtySearch.enterProviderLocation(state);
        ResultsPage results =  specialtySearch.clickSearchButton();

        results.clickFilterYourSearch();

        results.filter.clickCardiacCareCheckbox();

        results.filter.clickFilterSearch();

        String fac;
        // Check for the image
        for (FluentWebElement result : results.resultsList()) {
            fac = results.getFacilityName(result);
            m_assert.assertTrue(results.facilityHasBlueDistinction(result,"bd_cardiac.jpg"),
                    "Result for " + fac + " did not contain image");
            Reporter.log(fac + " has bd_cardiac.jpg.");
        }

    }
//
//    @Test (dataProvider = "state")
//    public void kneeHipSearchFilterTest(String state) {
//        SoftAssert m_assert = new SoftAssert();
//
//        SpecialtyTypeSearchPage specialtySearch = new SpecialtyTypeSearchPage();
//
//        specialtySearch.go(url);
//        // Facility Search
//        specialtySearch.selectProviderType("Facility");
//
//        specialtySearch.enterProviderLocation(state);
//        ResultsPage results =  specialtySearch.clickSearchButton();
//
//        results.clickFilterYourSearch();
//
//        results.filter.clickKneeAndHipCheckbox();
//
//        results.filter.clickFilterSearch();
//
//        String fac;
//        // Check for the image
//        for (FluentWebElement result : results.resultsList()) {
//            fac = results.getFacilityName(result);
//            m_assert.assertTrue(results.facilityHasBlueDistinction(result,"bd_knee_and_hip.jpg"),
//                    "Result for " + fac + " did not contain image");
//            Reporter.log(fac + " has bd_knee_and_hip.jpg.");
//        }
//
//    }
//
//    @Test (dataProvider = "state")
//    public void spineSurgerySearchFilterTest(String state) {
//        SoftAssert m_assert = new SoftAssert();
//
//        SpecialtyTypeSearchPage specialtySearch = new SpecialtyTypeSearchPage();
//
//        specialtySearch.go(url);
//
//        // Facility Search
//        specialtySearch.selectProviderType("Facility");
//
//        specialtySearch.enterProviderLocation(state);
//        ResultsPage results =  specialtySearch.clickSearchButton();
//
//        results.clickFilterYourSearch();
//
//        results.filter.clickSpineSurgeryCheckbox();
//
//        results.filter.clickFilterSearch();
//
//        String fac;
//        // Check for the image
//        for (FluentWebElement result : results.resultsList()) {
//            fac = results.getFacilityName(result);
//            m_assert.assertTrue(results.facilityHasBlueDistinction(result,"bd_spine_surgery.jpg"),
//                    "Result for " + fac + " did not contain image");
//            Reporter.log(fac + " has bd_spine_surgery.jpg.");
//        }
//
//    }
//
//    @Test (dataProvider = "state")
//    public void transplantsSearchFilterTest(String state) {
//        SoftAssert m_assert = new SoftAssert();
//
//        SpecialtyTypeSearchPage specialtySearch = new SpecialtyTypeSearchPage();
//
//        specialtySearch.go(url);
//
//        // Facility Search
//        specialtySearch.selectProviderType("Facility");
//
//        specialtySearch.enterProviderLocation(state);
//        ResultsPage results =  specialtySearch.clickSearchButton();
//
//        results.clickFilterYourSearch();
//
//        results.filter.clickTransplantsCheckbox();
//
//        results.filter.clickFilterSearch();
//
//        String fac;
//        // Check for the image
//        for (FluentWebElement result : results.resultsList()) {
//            fac = results.getFacilityName(result);
//            m_assert.assertTrue(results.facilityHasBlueDistinction(result,"bd_transplant.jpg"),
//                    "Result for " + fac + " did not contain image");
//            Reporter.log(fac + " has bd_transplant.jpg.");
//        }
//
//    }
//
//    @Test (dataProvider = "state")
//    public void complexCancerSearchFilterTest(String state) {
//        SoftAssert m_assert = new SoftAssert();
//
//        SpecialtyTypeSearchPage specialtySearch = new SpecialtyTypeSearchPage();
//
//        specialtySearch.go(url);
//        // Facility Search
//        specialtySearch.selectProviderType("Facility");
//
//        specialtySearch.enterProviderLocation(state);
//        ResultsPage results =  specialtySearch.clickSearchButton();
//
//        results.clickFilterYourSearch();
//
//        results.filter.clickComplexAndRareCancersCheckbox();
//
//        results.filter.clickFilterSearch();
//
//        String fac;
//        // Check for the image
//        for (FluentWebElement result : results.resultsList()) {
//            fac = results.getFacilityName(result);
//            m_assert.assertTrue(results.facilityHasBlueDistinction(result,"bd_complex_rare_can.jpg"),
//                    "Result for " + fac + " did not contain image");
//            Reporter.log(fac + " has bd_complex_rare_can.jpg.");
//        }
//
//    }

    @DataProvider(name = "zipCodes")
    public Object[][] generateZipCodes() {
        return new Object[][] {
                {"18102"},
                {"18015"},
                {"16434"},
                {"02201"},
                {"10001"},
                {"07801"},
                {"90210"}
        };
    }

    @DataProvider(name = "state")
    public Object[][] generateStates() {
        return new Object[][] {
//                {"PA"},
//                {"CA"},
//                {"MI"},
                {"VA"}
        };
    }
}