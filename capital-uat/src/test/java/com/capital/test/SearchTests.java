package com.capital.test;

import com.capital.helpers.Profile;
import org.testng.annotations.*;
import com.capital.pages.ResultsPage;
import com.capital.pages.SpecialtyTypeSearchPage;

import java.util.List;

/**
 * Capital Search Test Suite
 */
public class SearchTests {

    private String url;

    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) {
        this.url = url;
    }

    @AfterMethod
    public void shutdown() {
    }

    @Test (enabled = false)
    public void simpleSearchTest() {
        SpecialtyTypeSearchPage specialtyTypeSearchPage = new SpecialtyTypeSearchPage();

        specialtyTypeSearchPage.go(url)
                .enterProviderLocation("10001")
                .clickSearchButton();

    }

    @Test (dataProvider = "state")
    public void compareStateSearchResultsNameToProfile(String state) {
        SpecialtyTypeSearchPage specialtySearch = new SpecialtyTypeSearchPage();

        specialtySearch.go(url);
        specialtySearch.enterProviderLocation(state);

        ResultsPage results = specialtySearch.clickSearchButton();

        // Add the current list of doctors
        List<Profile> docs = results.doctorResults();

        // Click page 4
        results.goToPageNumber(4);

        // Add those results to the list of doctors
        for (Profile doc : results.doctorResults()) {
            docs.add(doc);
        }

        // Click page up
        results.pageJumpForward();

        // Add that final list
        for (Profile doc : results.doctorResults()) {
            docs.add(doc);
        }

        // Do the compare
        results.doctorListCompareAndReport(docs);
    }

    @Test (dataProvider = "state")
    public void compareStateSearchResultsFacilityToProfile(String state) {
        SpecialtyTypeSearchPage specialtySearch = new SpecialtyTypeSearchPage();

        specialtySearch.go(url);

        // Facility Search
        specialtySearch.selectProviderType("Facility");

        specialtySearch.enterProviderLocation(state);
        ResultsPage results = specialtySearch.clickSearchButton();

        // Add the current list of doctors
        List<Profile> docs = results.facilityResults();

        // Click page 4
        results.goToPageNumber(4);

        // Add those results to the list of doctors
        for (Profile doc : results.facilityResults()) {
            docs.add(doc);
        }

        // Click page up
        results.pageJumpForward();

        // Add that final list
        for (Profile doc : results.facilityResults()) {
            docs.add(doc);
        }

        // Do the compare
        results.facilityListCompareAndReport(docs);

    }

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
                {"PA"},
                {"CA"},
                {"MI"},
                {"VA"}
        };
    }

}