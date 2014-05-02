package com.uchc.test;

import com.uchc.pages.DoctorReportPage;
import com.uchc.pages.SearchResultsPage;
import com.uchc.pages.profile.ProfileCommonPage;
import com.uchc.pages.profile.ProfilePageRatings;
import com.vitalsqa.testrail.TestCase;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SearchTest {

    private String url;
    private static String byDoctorUrl = "/drs/massachusetts/BOSTON.html";
    private static String bySpecialtyUrl = "/drs/massachusetts/internal_medicine/Boston.html";
    private static String byNameUrl = "/drs/massachusetts/A.html";
    private static String byDentistUrl = "/dentist/illinois/chicago.html";
    private static String byPodiatristUrl = "/podiatrist/pennsylvania/philadelphia.html";

    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) {
        this.url = url;
    }

    @TestCase(id={1859,1868})
    @Test
    public void byDoctorCheckResults() {
        SearchResultsPage serp = new SearchResultsPage();
        serp.get(url + byDoctorUrl);
        Assert.assertTrue(checkIndividualResult(serp, true));
    }

    @TestCase(id={1860,1869})
    @Test
    public void byDoctorPageRanges() {
        SearchResultsPage serp = new SearchResultsPage();
        serp.get(url + byDoctorUrl);
        Assert.assertTrue(resultPageRanges(serp));
    }

    @TestCase(id={1861,1870})
    @Test
    public void byDoctorPagination() {
        SearchResultsPage serp = new SearchResultsPage();
        serp.get(url + byDoctorUrl);
        Assert.assertTrue(resultPagination(serp));
    }

    @TestCase(id={1862,1868})
    @Test
    public void bySpecialtyCheckResults() {
        SearchResultsPage serp = new SearchResultsPage();
        serp.get(url + bySpecialtyUrl);
        Assert.assertTrue(checkIndividualResult(serp,true));
    }

    @TestCase(id={1863,1869})
    @Test
    public void bySpecialtyPageRanges() {
        SearchResultsPage serp = new SearchResultsPage();
        serp.get(url + bySpecialtyUrl);
        Assert.assertTrue(resultPageRanges(serp));
    }

    @TestCase(id={1864,1870})
    @Test
    public void bySpecialtyPagination() {
        SearchResultsPage serp = new SearchResultsPage();
        serp.get(url + bySpecialtyUrl);
        Assert.assertTrue(resultPagination(serp));
    }

    @TestCase(id={1865,1868})
    @Test
    public void byNameCheckResults() {
        SearchResultsPage serp = new SearchResultsPage();
        serp.get(url + byNameUrl);
        Assert.assertTrue(checkIndividualResult(serp,true));
    }

    @TestCase(id={1866,1869})
    @Test
    public void byNamePageRanges() {
        SearchResultsPage serp = new SearchResultsPage();
        serp.get(url + byNameUrl);
        Assert.assertTrue(resultPageRanges(serp));
    }

    @TestCase(id={1867,1870})
    @Test
    public void byNamePagination() {
        SearchResultsPage serp = new SearchResultsPage();
        serp.get(url + byNameUrl);
        Assert.assertTrue(resultPagination(serp));
    }

    @TestCase(id={1917,1868})
    @Test
    public void byDentistCheckResults() {
        SearchResultsPage serp = new SearchResultsPage();
        serp.get(url + byDentistUrl);
        Assert.assertTrue(checkIndividualResult(serp,false));
    }

    @TestCase(id={1918,1869})
    @Test
    public void byDentistPageRanges() {
        SearchResultsPage serp = new SearchResultsPage();
        serp.get(url + byDentistUrl);
        Assert.assertTrue(resultPageRanges(serp));
    }

    @TestCase(id={1919,1870})
    @Test
    public void byDentistPagination() {
        SearchResultsPage serp = new SearchResultsPage();
        serp.get(url + byDentistUrl);
        Assert.assertTrue(resultPagination(serp));
    }

    @TestCase(id={1920,1868})
    @Test
    public void byPodiatristCheckResults() {
        SearchResultsPage serp = new SearchResultsPage();
        serp.get(url + byPodiatristUrl);
        Assert.assertTrue(checkIndividualResult(serp,false));
    }

    @TestCase(id={1921,1869})
    @Test
    public void byPodiatristPageRanges() {
        SearchResultsPage serp = new SearchResultsPage();
        serp.get(url + byPodiatristUrl);
        Assert.assertTrue(resultPageRanges(serp));
    }

    @TestCase(id={1922,1870})
    @Test
    public void byPodiatristPagination() {
        SearchResultsPage serp = new SearchResultsPage();
        serp.get(url + byPodiatristUrl);
        Assert.assertTrue(resultPagination(serp));
    }

    private boolean checkIndividualResult(SearchResultsPage serp, boolean hasRating) {
        boolean result = true;
        int rand;
        String name;
        String currentUrl=serp.getCurrentUrl();
        ProfileCommonPage profile = new ProfileCommonPage();
        ProfilePageRatings ratings = new ProfilePageRatings();
        DoctorReportPage report = new DoctorReportPage();

        // click photo
        rand = (int) Math.floor(Math.random() * (serp.resultNames().size() - 1));
        name = serp.resultNames().get(rand).getText().toString();
        serp.resultPhotos().get(rand).click();
        if (!name.contains(profile.drName().getText().toString())) {
            result = false;
            Reporter.log("Profile name does not match SERP name when clicking photo<br>");
        }

        // go back to serp, click name link
        serp.get(currentUrl);
        rand = (int) Math.floor(Math.random() * (serp.resultNames().size() - 1));
        name = serp.resultNames().get(rand).getText().toString();
        serp.resultNames().get(rand).click();
        if (!name.contains(profile.drName().getText().toString())) {
            result = false;
            Reporter.log("Profile name does not match SERP name when clicking name<br>");
        }

        // go back to serp, click view profile link
        serp.get(currentUrl);
        rand = (int) Math.floor(Math.random() * (serp.resultNames().size() - 1));
        name = serp.resultNames().get(rand).getText().toString();
        serp.resultsViewProfileLinks().get(rand).click();
        if (!name.contains(profile.drName().getText().toString())) {
            result = false;
            Reporter.log("Profile name does not match SERP name when clicking view profile link<br>");
        }

        // go back to serp, click get report link
        serp.get(currentUrl);
        rand = (int) Math.floor(Math.random() * (serp.resultNames().size() - 1));
        name = serp.resultNames().get(rand).getText().toString();
        serp.resultGetReportLinks().get(rand).click();
        if (!report.hasDrInReport(name)) {
            result = false;
            Reporter.log("Report does not contain SERP name when clicking get report link<br>");
        }

        if (hasRating) {
            // go back to serp, click rating stars
            serp.get(currentUrl);
            rand = (int) Math.floor(Math.random() * (serp.resultNames().size() - 1));
            name = serp.resultNames().get(rand).getText().toString();
            serp.resultsRating3().get(rand).click();
            if (!name.contains(profile.drName().getText().toString())) {
                result = false;
                Reporter.log("Profile name does not match SERP name when clicking rating stars<br>");
            }
            if (ratings.selectedStars().size()!=3) {
                result = false;
                Reporter.log("Selected rating isn't correct on profile page when clicking rating stars<br>");
            }
        }

        return result;
    }

    private boolean resultPageRanges(SearchResultsPage serp) {
        boolean result = true;

        if (serp.jumpPageDropDown().options().size()!=serp.browsePanelNameLinks().size()+1) {
            result = false;
            Reporter.log("Number of options different for jump page drop down & browse pane links on page: " + serp.paginationCurrent().getText().toString() + "<br>");
        }
        if (!serp.isCorrectJumpPage()) {
            result = false;
            Reporter.log("Range of names in jump page drop down does not match first/last name on page: " + serp.paginationCurrent().getText().toString() + "<br>");
        }
        if (!serp.isCorrectBrowsePane()) {
            result = false;
            Reporter.log("Range of names in browse pane links does not match first/last name on page: " + serp.paginationCurrent().getText().toString() + "<br>");
        }

        serp.getRandom(serp.browsePanelNameLinks()).click();
        if (serp.jumpPageDropDown().options().size()!=serp.browsePanelNameLinks().size()+1) {
            result = false;
            Reporter.log("Number of options different for jump page drop down & browse pane links on page: " + serp.paginationCurrent().getText().toString() + "<br>");
        }
        if (!serp.isCorrectJumpPage()) {
            result = false;
            Reporter.log("Range of names in jump page drop down does not match first/last name on page: " + serp.paginationCurrent().getText().toString() + "<br>");
        }
        if (!serp.isCorrectBrowsePane()) {
            result = false;
            Reporter.log("Range of names in browse pane links does not match first/last name on page: " + serp.paginationCurrent().getText().toString() + "<br>");
        }

        serp.selectDropDown(serp.jumpPageDropDown());
        if (serp.jumpPageDropDown().options().size()!=serp.browsePanelNameLinks().size()+1) {
            result = false;
            Reporter.log("Number of options different for jump page drop down & browse pane links on page: " + serp.paginationCurrent().getText().toString() + "<br>");
        }
        if (!serp.isCorrectJumpPage()) {
            result = false;
            Reporter.log("Range of names in jump page drop down does not match first/last name on page: " + serp.paginationCurrent().getText().toString() + "<br>");
        }
        if (!serp.isCorrectBrowsePane()) {
            result = false;
            Reporter.log("Range of names in browse pane links does not match first/last name on page: " + serp.paginationCurrent().getText().toString() + "<br>");
        }

        return result;
    }

    private boolean resultPagination(SearchResultsPage serp) {
        boolean result = true;

        // on 1st page
        if (serp.resultNames().size()!=50) {
            result = false;
            Reporter.log("Not showing 50 results on page 1<br>");
        }

        // click 2nd page
        serp.paginationLinks().get(3).click();
        if (Math.floor(serp.totalResults()/50) != 1) { // meaning 2nd page isn't the last page
            if (serp.resultNames().size()!=50) {
                result = false;
                Reporter.log("Not showing 50 results on page 2<br>");
            }
        }
        else { // 2nd page is the last page
            if (serp.resultNames().size()!=(serp.totalResults()%50)) {
                result = false;
                Reporter.log("Not showing correct # of results on 2nd (last) page<br>");
                return result;
            }
        }

        // click to go to last page
        serp.paginationLinks().get(serp.paginationLinks().size()-1).click();
        if (!serp.paginationCurrent().getText().toString().equals("" + Math.floor(serp.totalResults()/50)+1)) {
            result = false;
            Reporter.log("Last page's page number is incorrect<br>");
        }
        if (serp.resultNames().size()!=(serp.totalResults()%50)) {
            result = false;
            Reporter.log("Not showing correct # of results on last page<br>");
        }

        return result;
    }

}
