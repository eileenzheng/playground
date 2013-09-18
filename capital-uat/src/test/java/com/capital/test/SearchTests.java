package com.capital.test;

import com.capital.helpers.Profile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import com.capital.pages.ResultsPage;
import com.capital.pages.SpecialtyTypeSearchPage;

import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Capital Search Test Suite
 */
public class SearchTests {

    private WebDriver driver;
    private SoftAssert m_assert;
    private String url;

    @Parameters({"url","testLocation"})
    @BeforeMethod
    public void setup(String url, @Optional("")String testLocation) throws Exception {
        this.url = url;

        if (testLocation.equals("remoteWD")) {
            URL server = new URL("http://thvitdatadev01.mdx.med:4444/wd/hub");
            DesiredCapabilities caps = DesiredCapabilities.firefox();
            driver = new RemoteWebDriver(server, caps);
        } else {
            driver = new FirefoxDriver();
        }

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @AfterMethod
    public void shutdown() {
        driver.quit();
    }

    @Test (enabled = false)
    public void simpleSearchTest() {
        SpecialtyTypeSearchPage specialtyTypeSearchPage = new SpecialtyTypeSearchPage(driver);

        specialtyTypeSearchPage.go(url)
                .enterProviderLocation("10001")
                .clickSearchButton();

    }

    @Test (dataProvider = "state")
    public void compareStateSearchResultsNameToProfile(String state) {
        m_assert = new SoftAssert();

        SpecialtyTypeSearchPage specialtySearch = new SpecialtyTypeSearchPage(driver);

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

        // Initial report line
        //TODO: Doctor Profile page
        Reporter.log("ResultsPage,ProfilePage,ProfilePageUrl");
        for (Profile doc : docs) {
            driver.get(doc.getUrl());
            //Assert.assertTrue((driver.findElements(By.xpath("/html/body/b")).size() == 0),"MONGO ERROR: \n" + driver.getPageSource().toString() );
            doc.setProfileName(driver.findElement(By.cssSelector(".six.columns.mobile>h2")).getText().trim());
            m_assert.assertTrue(doc.searchAndProfileMatches(),"Did not match: " + doc.toString());
            Reporter.log(doc.toString());
        }

        m_assert.assertAll();
    }

    @Test (dataProvider = "state")
    public void compareStateSearchResultsFacilityToProfile(String state) {
        m_assert = new SoftAssert();

        SpecialtyTypeSearchPage specialtySearch = new SpecialtyTypeSearchPage(driver);

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

        // Initial report line
        //TODO: Doctor Profile page
        Reporter.log("ResultsPage,ProfilePage,ProfilePageUrl");
        for (Profile doc : docs) {
            driver.get(doc.getUrl());
            doc.setProfileName(driver.findElement(By.cssSelector(".six.columns.mobile>h2")).getText().trim());
            m_assert.assertTrue(doc.searchAndProfileMatches(),"Did not match: " + doc.toString());
            Reporter.log(doc.toString());

        }

        m_assert.assertAll();
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
/**
 * load app
 perform a search
 does name (display_title) on search results match the loaded profile if you click on title on results page
 paginate
 same com.capital.test

 that's the generic walk thru com.capital.test for both apps
 capital has people in PA
 presby has people in NM
 alina may have a few specific use cases she runs
 other than that,the gravy stuff is testing filters, testing plans, locations... ect..
 */