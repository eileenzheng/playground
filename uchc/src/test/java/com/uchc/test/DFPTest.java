package com.uchc.test;

import com.uchc.pages.BasePage;
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
    private String[] leaderboardTopSizes = {"[728, 90]"};
    private String[] rectangleSizes = {"[300, 250]", "[300, 600]", "[300, 1050]"};
    private String[] skyscraperSizes = {"[160, 600]"};

    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) throws Exception {
        this.url = url;
    }

    @TestCase(id=2118)
    @Test
    public void findDoctorLanding() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/drs");

        m_assert.assertTrue(page.getPageSource().contains("/8905/uchc/findadoctor"), "Incorrect ad unit zones");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "skyscraper", skyscraperSizes, "1"), "Incorrect skyscraper");
        m_assert.assertAll();
    }

    @TestCase(id=2119)
    @Test
    public void findDoctorState() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/drs/alabama/");

        m_assert.assertTrue(page.getPageSource().contains("/8905/uchc/findadoctor"), "Incorrect ad unit zones");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "skyscraper", skyscraperSizes, "1"), "Incorrect skyscraper");
        m_assert.assertAll();
    }

    @TestCase(id=2120)
    @Test
    public void findDoctorCitySerp() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/drs/alabama/BIRMINGHAM.html");

        m_assert.assertTrue(page.getPageSource().contains("/8905/uchc/findadoctor"), "Incorrect ad unit zones");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "skyscraper", skyscraperSizes, "1"), "Incorrect skyscraper");
        m_assert.assertAll();
    }

    @TestCase(id=2121)
    @Test
    public void findDoctorSpecialty() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/drs/physician_specialty/");

        m_assert.assertTrue(page.getPageSource().contains("/8905/uchc/findadoctor"), "Incorrect ad unit zones");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "skyscraper", skyscraperSizes, "1"), "Incorrect skyscraper");
        m_assert.assertAll();
    }

    @TestCase(id=2122)
    @Test
    public void findDoctorSpecialtyLanding() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/drs/cardiologists/");

        String[] specValues = {"card"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        String[] fspecValues = {"intm-card"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "fspec", fspecValues), "Incorrect fspec");

        m_assert.assertTrue(page.getPageSource().contains("/8905/uchc/findadoctor"), "Incorrect ad unit zones");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "skyscraper", skyscraperSizes, "1"), "Incorrect skyscraper");
        m_assert.assertAll();
    }

    @TestCase(id=2123)
    @Test
    public void findDoctorSpecialtyState() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/drs/alabama/cardiologists/");

        String[] specValues = {"card"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        String[] fspecValues = {"intm-card"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "fspec", fspecValues), "Incorrect fspec");

        m_assert.assertTrue(page.getPageSource().contains("/8905/uchc/findadoctor"), "Incorrect ad unit zones");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "skyscraper", skyscraperSizes, "1"), "Incorrect skyscraper");
        m_assert.assertAll();
    }

    @TestCase(id=2124)
    @Test
    public void findDoctorSpecialtyCitySerp() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/drs/alabama/cardiologists/Birmingham.html/");

        String[] specValues = {"card"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        String[] fspecValues = {"intm-card"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "fspec", fspecValues), "Incorrect fspec");

        m_assert.assertTrue(page.getPageSource().contains("/8905/uchc/findadoctor"), "Incorrect ad unit zones");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "skyscraper", skyscraperSizes, "1"), "Incorrect skyscraper");
        m_assert.assertAll();
    }

    @TestCase(id=2125)
    @Test
    public void findDoctorName() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/drs/dr_name.html");

        m_assert.assertTrue(page.getPageSource().contains("/8905/uchc/findadoctor"), "Incorrect ad unit zones");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "skyscraper", skyscraperSizes, "1"), "Incorrect skyscraper");
        m_assert.assertAll();
    }

    @TestCase(id=2126)
    @Test
    public void findDoctorNameState() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/drs/alabama/dr_name.html");

        m_assert.assertTrue(page.getPageSource().contains("/8905/uchc/findadoctor"), "Incorrect ad unit zones");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "skyscraper", skyscraperSizes, "1"), "Incorrect skyscraper");
        m_assert.assertAll();
    }

    @TestCase(id=2127)
    @Test
    public void findDoctorNameSerp() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/drs/alabama/A.html");

        m_assert.assertTrue(page.getPageSource().contains("/8905/uchc/findadoctor"), "Incorrect ad unit zones");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "skyscraper", skyscraperSizes, "1"), "Incorrect skyscraper");
        m_assert.assertAll();
    }

    @TestCase(id=2128)
    @Test
    public void findDentistLanding() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/dentist");

        String[] specValues = {"dent"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        m_assert.assertTrue(page.getPageSource().contains("/8905/uchc/dentist_find"), "Incorrect ad unit zones");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "skyscraper", skyscraperSizes, "1"), "Incorrect skyscraper");
        m_assert.assertAll();
    }

    @TestCase(id=2129)
    @Test
    public void findDentistState() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/dentist/alabama/");

        String[] specValues = {"dent"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        m_assert.assertTrue(page.getPageSource().contains("/8905/uchc/dentist_find"), "Incorrect ad unit zones");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "skyscraper", skyscraperSizes, "1"), "Incorrect skyscraper");
        m_assert.assertAll();
    }

    @TestCase(id=2130)
    @Test
    public void findDentistCitySerp() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/dentist/alabama/birmingham.html");

        String[] specValues = {"dent"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        m_assert.assertTrue(page.getPageSource().contains("/8905/uchc/dentist_find"), "Incorrect ad unit zones");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "skyscraper", skyscraperSizes, "1"), "Incorrect skyscraper");
        m_assert.assertAll();
    }

    @TestCase(id=2131)
    @Test
    public void findDentistSpecialty() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/dentist/dentist_specialty/");

        String[] specValues = {"dent"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        m_assert.assertTrue(page.getPageSource().contains("/8905/uchc/dentist_find"), "Incorrect ad unit zones");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "skyscraper", skyscraperSizes, "1"), "Incorrect skyscraper");
        m_assert.assertAll();
    }

    @TestCase(id=2132)
    @Test
    public void findDentistSpecialtyLanding() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/dentist/general_dentists/");

        String[] specValues = {"dent"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        m_assert.assertTrue(page.getPageSource().contains("/8905/uchc/dentist_find"), "Incorrect ad unit zones");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "skyscraper", skyscraperSizes, "1"), "Incorrect skyscraper");
        m_assert.assertAll();
    }

    @TestCase(id=2133)
    @Test
    public void findDentistSpecialtyState() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/dentist/alabama/general_dentistry/");

        String[] specValues = {"dent"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        m_assert.assertTrue(page.getPageSource().contains("/8905/uchc/dentist_find"), "Incorrect ad unit zones");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "skyscraper", skyscraperSizes, "1"), "Incorrect skyscraper");
        m_assert.assertAll();
    }

    @TestCase(id=2134)
    @Test
    public void findDentistSpecialtyCitySerp() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/dentist/alabama/general_dentistry/Birmingham.html/");

        String[] specValues = {"dent"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        m_assert.assertTrue(page.getPageSource().contains("/8905/uchc/dentist_find"), "Incorrect ad unit zones");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "skyscraper", skyscraperSizes, "1"), "Incorrect skyscraper");
        m_assert.assertAll();
    }

    @TestCase(id=2135)
    @Test
    public void findDentistName() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/dentist/dentist_name.html");

        String[] specValues = {"dent"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        m_assert.assertTrue(page.getPageSource().contains("/8905/uchc/dentist_find"), "Incorrect ad unit zones");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "skyscraper", skyscraperSizes, "1"), "Incorrect skyscraper");
        m_assert.assertAll();
    }

    @TestCase(id=2136)
    @Test
    public void findDentistNameState() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/dentist/alabama/dentist_name.html");

        String[] specValues = {"dent"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        m_assert.assertTrue(page.getPageSource().contains("/8905/uchc/dentist_find"), "Incorrect ad unit zones");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "skyscraper", skyscraperSizes, "1"), "Incorrect skyscraper");
        m_assert.assertAll();
    }

    @TestCase(id=2137)
    @Test
    public void findDentistNameSerp() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/dentist/alabama/A.html");

        String[] specValues = {"dent"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        m_assert.assertTrue(page.getPageSource().contains("/8905/uchc/dentist_find"), "Incorrect ad unit zones");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "skyscraper", skyscraperSizes, "1"), "Incorrect skyscraper");
        m_assert.assertAll();
    }

    @TestCase(id=2138)
    @Test
    public void findPodiatristLanding() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/podiatrist");

        String[] specValues = {"podt"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        m_assert.assertTrue(page.getPageSource().contains("/8905/uchc/podiatrist_find"), "Incorrect ad unit zones");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "skyscraper", skyscraperSizes, "1"), "Incorrect skyscraper");
        m_assert.assertAll();
    }

    @TestCase(id=2139)
    @Test
    public void findPodiatristState() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/podiatrist/alabama/");

        String[] specValues = {"podt"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        m_assert.assertTrue(page.getPageSource().contains("/8905/uchc/podiatrist_find"), "Incorrect ad unit zones");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "skyscraper", skyscraperSizes, "1"), "Incorrect skyscraper");
        m_assert.assertAll();
    }

    @TestCase(id=2140)
    @Test
    public void findPodiatristCitySerp() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/podiatrist/alabama/birmingham.html");

        String[] specValues = {"podt"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        m_assert.assertTrue(page.getPageSource().contains("/8905/uchc/podiatrist_find"), "Incorrect ad unit zones");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "skyscraper", skyscraperSizes, "1"), "Incorrect skyscraper");
        m_assert.assertAll();
    }

    @TestCase(id=2141)
    @Test
    public void findPodiatristSpecialty() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/podiatrist/podiatrist_specialty/");

        String[] specValues = {"podt"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        m_assert.assertTrue(page.getPageSource().contains("/8905/uchc/podiatrist_find"), "Incorrect ad unit zones");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "skyscraper", skyscraperSizes, "1"), "Incorrect skyscraper");
        m_assert.assertAll();
    }

    @TestCase(id=2142)
    @Test
    public void findPodiatristSpecialtyLanding() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/podiatrist/general_podiatrists/");

        String[] specValues = {"podt"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        m_assert.assertTrue(page.getPageSource().contains("/8905/uchc/podiatrist_find"), "Incorrect ad unit zones");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "skyscraper", skyscraperSizes, "1"), "Incorrect skyscraper");
        m_assert.assertAll();
    }

    @TestCase(id=2143)
    @Test
    public void findPodiatristSpecialtyState() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/podiatrist/alabama/general_podiatry/");

        String[] specValues = {"podt"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        m_assert.assertTrue(page.getPageSource().contains("/8905/uchc/podiatrist_find"), "Incorrect ad unit zones");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "skyscraper", skyscraperSizes, "1"), "Incorrect skyscraper");
        m_assert.assertAll();
    }

    @TestCase(id=2144)
    @Test
    public void findPodiatristSpecialtyCitySerp() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/podiatrist/alabama/general_podiatry/birmingham.html/");

        String[] specValues = {"podt"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        m_assert.assertTrue(page.getPageSource().contains("/8905/uchc/podiatrist_find"), "Incorrect ad unit zones");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "skyscraper", skyscraperSizes, "1"), "Incorrect skyscraper");
        m_assert.assertAll();
    }

    @TestCase(id=2145)
    @Test
    public void findPodiatristName() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/podiatrist/podiatrist_name.html");

        String[] specValues = {"podt"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        m_assert.assertTrue(page.getPageSource().contains("/8905/uchc/podiatrist_find"), "Incorrect ad unit zones");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "skyscraper", skyscraperSizes, "1"), "Incorrect skyscraper");
        m_assert.assertAll();
    }

    @TestCase(id=2146)
    @Test
    public void findPodiatristNameState() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/podiatrist/alabama/podiatrist_name.html");

        String[] specValues = {"podt"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        m_assert.assertTrue(page.getPageSource().contains("/8905/uchc/podiatrist_find"), "Incorrect ad unit zones");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "skyscraper", skyscraperSizes, "1"), "Incorrect skyscraper");
        m_assert.assertAll();
    }

    @TestCase(id=2147)
    @Test
    public void findPodiatristNameSerp() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/podiatrist/alabama/a.html");

        String[] specValues = {"podt"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        m_assert.assertTrue(page.getPageSource().contains("/8905/uchc/podiatrist_find"), "Incorrect ad unit zones");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "skyscraper", skyscraperSizes, "1"), "Incorrect skyscraper");
        m_assert.assertAll();
    }

    @TestCase(id=2148)
    @Test
    public void doctorReport() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/drs/report2.html?L=6153172717444435173511&R=9byD8BIa0S8lYfR6ZkcBy2027&I=61531727");

        String[] specValues = {"card", "intm"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        String[] fspecValues = {"intm-card", "intm-intm"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "fspec", fspecValues), "Incorrect fspec");
        m_assert.assertTrue(page.getPageSource().contains("/8905/uchc/profile_report"), "Incorrect ad unit zones");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "skyscraper", skyscraperSizes, "1"), "Incorrect skyscraper");
        m_assert.assertAll();
    }

    @TestCase(id=2149)
    @Test
    public void dentistReport() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/dentist/report2.html?L=6152699315535306150684&R=M1G00Y18I1T7D6kmbk8QwZ6rc&I=61526993");

        String[] specValues = {"dent"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        m_assert.assertTrue(page.getPageSource().contains("/8905/uchc/dentist_report"), "Incorrect ad unit zones");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "skyscraper", skyscraperSizes, "1"), "Incorrect skyscraper");
        m_assert.assertAll();
    }

    @TestCase(id=2150)
    @Test
    public void profileGeneral() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/drs/todd_rosengart");

        String[] specValues = {"surg", "ctsg"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        String[] fspecValues = {"surg-vssg", "ctsg-ctsg"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "fspec", fspecValues), "Incorrect fspec");
        String[] pspecValues = {"surg"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "pspec", pspecValues), "Incorrect pspec");
        String[] midValues = {"193060"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "mid", midValues), "Incorrect mid");

        m_assert.assertTrue(page.getPageSource().contains("/8905/uchc/profile"), "Incorrect ad unit zones");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "skyscraper", skyscraperSizes, "1"), "Incorrect skyscraper");
        m_assert.assertAll();
    }

    @TestCase(id=2151)
    @Test
    public void profileReviewsYes() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/drs/marina_gafanovich/reviews.html");

        String[] specValues = {"intm"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        String[] fspecValues = {"intm-intm"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "fspec", fspecValues), "Incorrect fspec");
        String[] pspecValues = {"intm"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "pspec", pspecValues), "Incorrect pspec");
        String[] midValues = {"1253818"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "mid", midValues), "Incorrect mid");

        m_assert.assertTrue(page.getPageSource().contains("/8905/uchc/reviews_yes"), "Incorrect ad unit zones");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "skyscraper", skyscraperSizes, "1"), "Incorrect skyscraper");
        m_assert.assertAll();
    }

    @TestCase(id=2152)
    @Test
    public void profileReviewsNo() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/drs/lawrence_i_harrison/reviews.html");

        String[] specValues = {"radi", "nmed"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        String[] fspecValues = {"radi-radi", "nmed-nmed"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "fspec", fspecValues), "Incorrect fspec");
        String[] pspecValues = {"radi"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "pspec", pspecValues), "Incorrect pspec");
        String[] midValues = {"1158889"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "mid", midValues), "Incorrect mid");

        m_assert.assertTrue(page.getPageSource().contains("/8905/uchc/reviews_no"), "Incorrect ad unit zones");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "skyscraper", skyscraperSizes, "1"), "Incorrect skyscraper");
        m_assert.assertAll();
    }

    @TestCase(id=2153)
    @Test
    public void profileAppointment() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/drs/marina_gafanovich/?tab=appointment");

        String[] specValues = {"intm"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        String[] fspecValues = {"intm-intm"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "fspec", fspecValues), "Incorrect fspec");
        String[] pspecValues = {"intm"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "pspec", pspecValues), "Incorrect pspec");
        String[] midValues = {"1253818"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "mid", midValues), "Incorrect mid");

        m_assert.assertTrue(page.getPageSource().contains("/8905/uchc/profile"), "Incorrect ad unit zones");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "skyscraper", skyscraperSizes, "1"), "Incorrect skyscraper");
        m_assert.assertAll();
    }

    @TestCase(id=2154)
    @Test
    public void profileLocations() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/drs/todd_rosengart/offices.html");

        String[] specValues = {"surg", "ctsg"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        String[] fspecValues = {"surg-vssg", "ctsg-ctsg"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "fspec", fspecValues), "Incorrect fspec");
        String[] pspecValues = {"surg"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "pspec", pspecValues), "Incorrect pspec");
        String[] midValues = {"193060"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "mid", midValues), "Incorrect mid");

        m_assert.assertTrue(page.getPageSource().contains("/8905/uchc/profile"), "Incorrect ad unit zones");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "skyscraper", skyscraperSizes, "1"), "Incorrect skyscraper");
        m_assert.assertAll();
    }

    @TestCase(id=2155)
    @Test
    public void profileHospital() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/drs/todd_rosengart/hospital.html");

        String[] specValues = {"surg", "ctsg"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        String[] fspecValues = {"surg-vssg", "ctsg-ctsg"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "fspec", fspecValues), "Incorrect fspec");
        String[] pspecValues = {"surg"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "pspec", pspecValues), "Incorrect pspec");
        String[] midValues = {"193060"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "mid", midValues), "Incorrect mid");

        m_assert.assertTrue(page.getPageSource().contains("/8905/uchc/profile"), "Incorrect ad unit zones");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "skyscraper", skyscraperSizes, "1"), "Incorrect skyscraper");
        m_assert.assertAll();
    }

    @TestCase(id=2156)
    @Test
    public void profileDirections() {
        page = new BasePage();
        m_assert = new SoftAssert();
        page.get(url + "/drs/todd_rosengart/directions.html");

        String[] specValues = {"surg", "ctsg"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "spec", specValues), "Incorrect spec");
        String[] fspecValues = {"surg-vssg", "ctsg-ctsg"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "fspec", fspecValues), "Incorrect fspec");
        String[] pspecValues = {"surg"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "pspec", pspecValues), "Incorrect pspec");
        String[] midValues = {"193060"};
        m_assert.assertTrue(checkKeys(page.getPageSource(), "mid", midValues), "Incorrect mid");

        m_assert.assertTrue(page.getPageSource().contains("/8905/uchc/profile"), "Incorrect ad unit zones");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "leaderboard_top", leaderboardTopSizes, "1"), "Incorrect leaderboard top");
        m_assert.assertTrue(checkSlots(page.getPageSource(), "rectangle", rectangleSizes, "1"), "Incorrect rectangle");
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
