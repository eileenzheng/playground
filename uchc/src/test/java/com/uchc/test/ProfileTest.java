package com.uchc.test;

import com.uchc.pages.ProfileCommonPage;
import com.uchc.pages.patientlink.DoctorReportPage;
import com.vitalsqa.testrail.TestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ProfileTest {

    private SoftAssert m_assert;
    private String url;
    private static String profileUrlHasReview = "/drs/todd_rosengart/";
    private static String profileUrlNoReview = "/drs/lawrence_i_harrison/";

    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) {
        this.url = url;
    }

    @TestCase(id=1839)
    @Test
    public void checkHeaderLinksNoReview() {
        m_assert = new SoftAssert();
        String urlToCheck;

        ProfileCommonPage profile = new ProfileCommonPage();
        profile.get(url + profileUrlNoReview);
        urlToCheck = profile.beTheFirstLink().getAttribute("href").toString();
        m_assert.assertTrue(urlToCheck.equals(url + profileUrlNoReview + "reviews.html#pr"), "General Info Page Rate Link");
        urlToCheck = profile.reviewsButton().getAttribute("href").toString();
        m_assert.assertTrue(urlToCheck.equals(url + profileUrlNoReview + "reviews.html#pr"), "General Info Page Review Button");

        profile.get(url + profileUrlNoReview + "reviews.html");
        urlToCheck = profile.beTheFirstLink().getAttribute("href").toString();
        m_assert.assertTrue(urlToCheck.equals(url + profileUrlNoReview + "reviews.html#pr"), "Ratings Page Rate Link");
        urlToCheck = profile.reviewsButton().getAttribute("href").toString();
        m_assert.assertTrue(urlToCheck.equals(url + profileUrlNoReview + "reviews.html#pr"), "Ratings Page Review Button");

        profile.get(url + profileUrlNoReview + "offices.html");
        urlToCheck = profile.beTheFirstLink().getAttribute("href").toString();
        m_assert.assertTrue(urlToCheck.equals(url + profileUrlNoReview + "reviews.html#pr"), "Locations Page Rate Link");
        urlToCheck = profile.reviewsButton().getAttribute("href").toString();
        m_assert.assertTrue(urlToCheck.equals(url + profileUrlNoReview + "reviews.html#pr"), "Locations Page Review Button");

        profile.get(url + profileUrlNoReview + "hospital.html");
        urlToCheck = profile.beTheFirstLink().getAttribute("href").toString();
        m_assert.assertTrue(urlToCheck.equals(url + profileUrlNoReview + "reviews.html#pr"), "Hospital Affiliations Page Rate Link");
        urlToCheck = profile.reviewsButton().getAttribute("href").toString();
        m_assert.assertTrue(urlToCheck.equals(url + profileUrlNoReview + "reviews.html#pr"), "Hospital Affiliations Page Review Button");

        profile.get(url + profileUrlNoReview + "directions.html");
        urlToCheck = profile.beTheFirstLink().getAttribute("href").toString();
        m_assert.assertTrue(urlToCheck.equals(url + profileUrlNoReview + "reviews.html#pr"), "Directions Page Rate Link");
        urlToCheck = profile.reviewsButton().getAttribute("href").toString();
        m_assert.assertTrue(urlToCheck.equals(url + profileUrlNoReview + "reviews.html#pr"), "Directions Page Review Button");

        m_assert.assertAll();
    }

    @TestCase(id=1840)
    @Test
    public void checkHeaderLinksHasReview() {
        m_assert = new SoftAssert();
        String urlToCheck;

        ProfileCommonPage profile = new ProfileCommonPage();
        profile.get(url + profileUrlHasReview);
        urlToCheck = profile.ratingLinks().get(0).getAttribute("href").toString();
        m_assert.assertTrue(urlToCheck.equals(url + profileUrlHasReview + "reviews.html"), "General Info Page 0");
        urlToCheck = profile.ratingLinks().get(1).getAttribute("href").toString();
        m_assert.assertTrue(urlToCheck.equals(url + profileUrlHasReview + "reviews.html"), "General Info Page 1");
        urlToCheck = profile.ratingLinks().get(2).getAttribute("href").toString();
        m_assert.assertTrue(urlToCheck.equals(url + profileUrlHasReview + "reviews.html"), "General Info Page 2");
        urlToCheck = profile.ratingLinks().get(3).getAttribute("href").toString();
        m_assert.assertTrue(urlToCheck.equals(url + profileUrlHasReview + "reviews.html#pr"), "General Info Page 3");
        urlToCheck = profile.reviewsButton().getAttribute("href").toString();
        m_assert.assertTrue(urlToCheck.equals(url + profileUrlHasReview + "reviews.html"), "General Info Page Review Button");

        profile.get(url + profileUrlHasReview + "reviews.html");
        urlToCheck = profile.ratingLinks().get(0).getAttribute("href").toString();
        m_assert.assertTrue(urlToCheck.equals(url + profileUrlHasReview + "reviews.html"), "Ratings Page 0");
        urlToCheck = profile.ratingLinks().get(1).getAttribute("href").toString();
        m_assert.assertTrue(urlToCheck.equals(url + profileUrlHasReview + "reviews.html"), "Ratings Page 1");
        urlToCheck = profile.ratingLinks().get(2).getAttribute("href").toString();
        m_assert.assertTrue(urlToCheck.equals(url + profileUrlHasReview + "reviews.html"), "Ratings Page 2");
        urlToCheck = profile.ratingLinks().get(3).getAttribute("href").toString();
        m_assert.assertTrue(urlToCheck.equals(url + profileUrlHasReview + "reviews.html#pr"), "Ratings Page 3");
        urlToCheck = profile.reviewsButton().getAttribute("href").toString();
        m_assert.assertTrue(urlToCheck.equals(url + profileUrlHasReview + "reviews.html"), "Ratings Page Review Button");

        profile.get(url + profileUrlHasReview + "offices.html");
        urlToCheck = profile.ratingLinks().get(0).getAttribute("href").toString();
        m_assert.assertTrue(urlToCheck.equals(url + profileUrlHasReview + "reviews.html"), "Locations Page 0");
        urlToCheck = profile.ratingLinks().get(1).getAttribute("href").toString();
        m_assert.assertTrue(urlToCheck.equals(url + profileUrlHasReview + "reviews.html"), "Locations Page 1");
        urlToCheck = profile.ratingLinks().get(2).getAttribute("href").toString();
        m_assert.assertTrue(urlToCheck.equals(url + profileUrlHasReview + "reviews.html"), "Locations Page 2");
        urlToCheck = profile.ratingLinks().get(3).getAttribute("href").toString();
        m_assert.assertTrue(urlToCheck.equals(url + profileUrlHasReview + "reviews.html#pr"), "Locations Page 3");
        urlToCheck = profile.reviewsButton().getAttribute("href").toString();
        m_assert.assertTrue(urlToCheck.equals(url + profileUrlHasReview + "reviews.html"), "Locations Page Review Button");

        profile.get(url + profileUrlHasReview + "hospital.html");
        urlToCheck = profile.ratingLinks().get(0).getAttribute("href").toString();
        m_assert.assertTrue(urlToCheck.equals(url + profileUrlHasReview + "reviews.html"), "Hospital Affiliations Page 0");
        urlToCheck = profile.ratingLinks().get(1).getAttribute("href").toString();
        m_assert.assertTrue(urlToCheck.equals(url + profileUrlHasReview + "reviews.html"), "Hospital Affiliations Page 1");
        urlToCheck = profile.ratingLinks().get(2).getAttribute("href").toString();
        m_assert.assertTrue(urlToCheck.equals(url + profileUrlHasReview + "reviews.html"), "Hospital Affiliations Page 2");
        urlToCheck = profile.ratingLinks().get(3).getAttribute("href").toString();
        m_assert.assertTrue(urlToCheck.equals(url + profileUrlHasReview + "reviews.html#pr"), "Hospital Affiliations Page 3");
        urlToCheck = profile.reviewsButton().getAttribute("href").toString();
        m_assert.assertTrue(urlToCheck.equals(url + profileUrlHasReview + "reviews.html"), "Hospital Affiliations Page Review Button");

        profile.get(url + profileUrlHasReview + "directions.html");
        urlToCheck = profile.ratingLinks().get(0).getAttribute("href").toString();
        m_assert.assertTrue(urlToCheck.equals(url + profileUrlHasReview + "reviews.html"), "Directions Page 0");
        urlToCheck = profile.ratingLinks().get(1).getAttribute("href").toString();
        m_assert.assertTrue(urlToCheck.equals(url + profileUrlHasReview + "reviews.html"), "Directions Page 1");
        urlToCheck = profile.ratingLinks().get(2).getAttribute("href").toString();
        m_assert.assertTrue(urlToCheck.equals(url + profileUrlHasReview + "reviews.html"), "Directions Page 2");
        urlToCheck = profile.ratingLinks().get(3).getAttribute("href").toString();
        m_assert.assertTrue(urlToCheck.equals(url + profileUrlHasReview + "reviews.html#pr"), "Directions Page 3");
        urlToCheck = profile.reviewsButton().getAttribute("href").toString();
        m_assert.assertTrue(urlToCheck.equals(url + profileUrlHasReview + "reviews.html"), "Directions Page Review Button");

        m_assert.assertAll();
    }

    @TestCase(id=1841)
    @Test
    public void checkReportLinks() {
        m_assert = new SoftAssert();
        String drName;

        ProfileCommonPage profile = new ProfileCommonPage();
        DoctorReportPage report = new DoctorReportPage();

        profile.get(url + profileUrlHasReview);
        drName = profile.drName().getText().toString();
        profile.compareButton().click();
        m_assert.assertTrue(report.hasDrInReport(drName), "General Info Page: Compare Button");
        profile.get(url + profileUrlHasReview);
        profile.contactButton().click();
        m_assert.assertTrue(report.hasDrInReport(drName), "General Info Page: Contact Button");
        profile.get(url + profileUrlHasReview);
        profile.contactLink().click();
        m_assert.assertTrue(report.hasDrInReport(drName), "General Info Page: Contact Button");
        profile.get(url + profileUrlHasReview);
        profile.freeDoctorRpt().click();
        m_assert.assertTrue(report.hasDrInReport(drName), "General Info Page: Free Doctor Report link");

        profile.get(url + profileUrlHasReview + "reviews.html");
        profile.compareButton().click();
        m_assert.assertTrue(report.hasDrInReport(drName), "Reviews Page: Compare Button");
        profile.get(url + profileUrlHasReview+ "reviews.html");
        profile.contactButton().click();
        m_assert.assertTrue(report.hasDrInReport(drName), "Reviews Page: Contact Button");
        profile.get(url + profileUrlHasReview+ "reviews.html");
        profile.contactLink().click();
        m_assert.assertTrue(report.hasDrInReport(drName), "Reviews Page: Contact Button");
        profile.get(url + profileUrlHasReview+ "reviews.html");
        profile.freeDoctorRpt().click();
        m_assert.assertTrue(report.hasDrInReport(drName), "Reviews Page: Free Doctor Report link");

        profile.get(url + profileUrlHasReview + "offices.html");
        profile.compareButton().click();
        m_assert.assertTrue(report.hasDrInReport(drName), "Locations Page: Compare Button");
        profile.get(url + profileUrlHasReview+ "offices.html");
        profile.contactButton().click();
        m_assert.assertTrue(report.hasDrInReport(drName), "Locations Page: Contact Button");
        profile.get(url + profileUrlHasReview+ "offices.html");
        profile.contactLink().click();
        m_assert.assertTrue(report.hasDrInReport(drName), "Locations Page: Contact Button");
        profile.get(url + profileUrlHasReview+ "offices.html");
        profile.freeDoctorRpt().click();
        m_assert.assertTrue(report.hasDrInReport(drName), "Locations Page: Free Doctor Report link");

        profile.get(url + profileUrlHasReview + "hospital.html");
        profile.compareButton().click();
        m_assert.assertTrue(report.hasDrInReport(drName), "Hospital Affiliation Page: Compare Button");
        profile.get(url + profileUrlHasReview+ "hospital.html");
        profile.contactButton().click();
        m_assert.assertTrue(report.hasDrInReport(drName), "Hospital Affiliation Page: Contact Button");
        profile.get(url + profileUrlHasReview+ "hospital.html");
        profile.contactLink().click();
        m_assert.assertTrue(report.hasDrInReport(drName), "Hospital Affiliation Page: Contact Button");
        profile.get(url + profileUrlHasReview+ "hospital.html");
        profile.freeDoctorRpt().click();
        m_assert.assertTrue(report.hasDrInReport(drName), "Hospital Affiliation Page: Free Doctor Report link");

        profile.get(url + profileUrlHasReview + "directions.html");
        profile.compareButton().click();
        m_assert.assertTrue(report.hasDrInReport(drName), "Directions Page: Compare Button");
        profile.get(url + profileUrlHasReview+ "directions.html");
        profile.contactButton().click();
        m_assert.assertTrue(report.hasDrInReport(drName), "Directions Page: Contact Button");
        profile.get(url + profileUrlHasReview+ "directions.html");
        profile.contactLink().click();
        m_assert.assertTrue(report.hasDrInReport(drName), "Directions Page: Contact Button");
        profile.get(url + profileUrlHasReview+ "directions.html");
        profile.freeDoctorRpt().click();
        m_assert.assertTrue(report.hasDrInReport(drName), "Directions Page: Free Doctor Report link");

        m_assert.assertAll();
    }
}
