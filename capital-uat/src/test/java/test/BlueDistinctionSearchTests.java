package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.ResultsPage;
import pages.SpecialtyTypeSearchPage;

import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Capital Blue Distinction Test Suite
 */
public class BlueDistinctionSearchTests {

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

    @Test (dataProvider = "state")
    public void bariatricSurgerySearchFilterTest(String state) {
        m_assert = new SoftAssert();

        SpecialtyTypeSearchPage specialtySearch = new SpecialtyTypeSearchPage(driver);

        specialtySearch.go(url);

        // Facility Search
        specialtySearch.selectProviderType("Facility");

        specialtySearch.enterProviderLocation(state);

        ResultsPage results =  specialtySearch.clickSearchButton();

        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 10, 2000);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("filterSearchClose")));

        results.clickFilterYourSearch();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("filterSearchClose")));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

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
        m_assert = new SoftAssert();

        SpecialtyTypeSearchPage specialtySearch = new SpecialtyTypeSearchPage(driver);

        specialtySearch.go(url);

        // Facility Search
        specialtySearch.selectProviderType("Facility");

        specialtySearch.enterProviderLocation(state);
        ResultsPage results =  specialtySearch.clickSearchButton();

        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 10, 2000);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("filterSearchClose")));

        results.clickFilterYourSearch();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("filterSearchClose")));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

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

    @Test (dataProvider = "state")
    public void kneeHipSearchFilterTest(String state) {
        m_assert = new SoftAssert();

        SpecialtyTypeSearchPage specialtySearch = new SpecialtyTypeSearchPage(driver);

        specialtySearch.go(url);
        // Facility Search
        specialtySearch.selectProviderType("Facility");

        specialtySearch.enterProviderLocation(state);
        ResultsPage results =  specialtySearch.clickSearchButton();

        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 10, 2000);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("filterSearchClose")));

        results.clickFilterYourSearch();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("filterSearchClose")));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        results.filter.clickKneeAndHipCheckbox();

        results.filter.clickFilterSearch();

        String fac;
        // Check for the image
        for (FluentWebElement result : results.resultsList()) {
            fac = results.getFacilityName(result);
            m_assert.assertTrue(results.facilityHasBlueDistinction(result,"bd_knee_and_hip.jpg"),
                    "Result for " + fac + " did not contain image");
            Reporter.log(fac + " has bd_knee_and_hip.jpg.");
        }

    }

    @Test (dataProvider = "state")
    public void spineSurgerySearchFilterTest(String state) {
        m_assert = new SoftAssert();

        SpecialtyTypeSearchPage specialtySearch = new SpecialtyTypeSearchPage(driver);

        specialtySearch.go(url);

        // Facility Search
        specialtySearch.selectProviderType("Facility");

        specialtySearch.enterProviderLocation(state);
        ResultsPage results =  specialtySearch.clickSearchButton();

        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 10, 2000);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("filterSearchClose")));

        results.clickFilterYourSearch();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("filterSearchClose")));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        results.filter.clickSpineSurgeryCheckbox();

        results.filter.clickFilterSearch();

        String fac;
        // Check for the image
        for (FluentWebElement result : results.resultsList()) {
            fac = results.getFacilityName(result);
            m_assert.assertTrue(results.facilityHasBlueDistinction(result,"bd_spine_surgery.jpg"),
                    "Result for " + fac + " did not contain image");
            Reporter.log(fac + " has bd_spine_surgery.jpg.");
        }

    }

    @Test (dataProvider = "state")
    public void transplantsSearchFilterTest(String state) {
        m_assert = new SoftAssert();

        SpecialtyTypeSearchPage specialtySearch = new SpecialtyTypeSearchPage(driver);

        specialtySearch.go(url);

        // Facility Search
        specialtySearch.selectProviderType("Facility");

        specialtySearch.enterProviderLocation(state);
        ResultsPage results =  specialtySearch.clickSearchButton();

        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 10, 2000);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("filterSearchClose")));

        results.clickFilterYourSearch();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("filterSearchClose")));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        results.filter.clickTransplantsCheckbox();

        results.filter.clickFilterSearch();

        String fac;
        // Check for the image
        for (FluentWebElement result : results.resultsList()) {
            fac = results.getFacilityName(result);
            m_assert.assertTrue(results.facilityHasBlueDistinction(result,"bd_transplant.jpg"),
                    "Result for " + fac + " did not contain image");
            Reporter.log(fac + " has bd_transplant.jpg.");
        }

    }

    @Test (dataProvider = "state")
    public void complexCancerSearchFilterTest(String state) {
        m_assert = new SoftAssert();

        SpecialtyTypeSearchPage specialtySearch = new SpecialtyTypeSearchPage(driver);

        specialtySearch.go(url);
        // Facility Search
        specialtySearch.selectProviderType("Facility");

        specialtySearch.enterProviderLocation(state);
        ResultsPage results =  specialtySearch.clickSearchButton();

        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 10, 2000);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("filterSearchClose")));

        results.clickFilterYourSearch();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("filterSearchClose")));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        results.filter.clickComplexAndRareCancersCheckbox();

        results.filter.clickFilterSearch();

        String fac;
        // Check for the image
        for (FluentWebElement result : results.resultsList()) {
            fac = results.getFacilityName(result);
            m_assert.assertTrue(results.facilityHasBlueDistinction(result,"bd_complex_rare_can.jpg"),
                    "Result for " + fac + " did not contain image");
            Reporter.log(fac + " has bd_complex_rare_can.jpg.");
        }

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
 same test

 that's the generic walk thru test for both apps
 capital has people in PA
 presby has people in NM
 alina may have a few specific use cases she runs
 other than that,the gravy stuff is testing filters, testing plans, locations... ect..
 */