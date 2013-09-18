package com.vitals.test;

import com.vitals.helpers.Profile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.vitals.pages.HomePage;
import com.vitals.pages.SearchResultsPage;
import com.vitals.runners.RemoteTestRunner;

import java.util.List;

/**
 * Vitals.com test
 */
public class SearchTest extends RemoteTestRunner {

    WebDriver driver;
    SoftAssert m_assert;

    @Parameters({"domain"})
    @Test
    public void autoSuggestLocation(String domain) {
        driver = getDriver();

        driver.get(getUrl(domain));
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);

        String location = "1000";
        String city = "New York";

        homePage.header.enterLocation(location);
        Assert.assertTrue(homePage.header.checkLocationSuggestions(city), location + " does not contain " + city);
    }

    @Parameters({"domain"})
    @Test
    public void autoSuggestName(String domain) {
        driver = getDriver();

        driver.get(getUrl(domain));
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);

        String name = "Smith";

        homePage.header.enterName(name);

        Reporter.log("The Docs> " + homePage.header.getSearchSuggestions());

        Assert.assertTrue(homePage.header.checkSearchSuggestions(name));

    }

    //Results contain the search name
    @Parameters({"domain"})
    @Test
    public void searchByName(String domain) {
        m_assert = new SoftAssert();
        driver = getDriver();

        driver.get(getUrl(domain));
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);

        String name = "Smith";
        String location = "10001";

        homePage.header.enterName(name);
        homePage.header.enterLocation(location);

        SearchResultsPage results = homePage.header.clickSearch();
        for (WebElement el : results.drList()) {
            String drName = el.findElement(By.cssSelector(".head>h4>a")).getText();
            Reporter.log(drName);
            m_assert.assertTrue(drName.toLowerCase().contains(name.toLowerCase()),drName + " Does not contain " + name);
        }

        m_assert.assertAll();
    }

    @Parameters({"domain"})
    @Test
    public void searchBySpecialty(String domain) {
        driver = getDriver();

        driver.get(getUrl(domain));
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);

        homePage.header.clickSpecialtyLink();
        String spec = "Cardiologist";
        homePage.header.enterSpecialty(spec);

        Assert.assertTrue(homePage.header.locationSearchIsPopulated(),"Location search is not populated");
        Reporter.log(homePage.header.getCurrentPopulatedLocation());

        SearchResultsPage results = homePage.header.clickSearch();

        Reporter.log(results.getResultsCount() + " for search: " + spec);
    }

    @Parameters({"domain"})
    @Test
    public void selectSubSpecialtySearch(String domain) {
        driver = getDriver();

        driver.get(getUrl(domain));
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);

        homePage.header.clickSpecialtyLink();
        String spec = "Cardiologist";
        homePage.header.enterSpecialty(spec);

        Reporter.log(homePage.header.getSubSpecialtySearchSuggestions());

        homePage.header.clickSubSpec();

        SearchResultsPage results = homePage.header.clickSearch();

        Reporter.log(results.getResultsCount() + " for search: " + spec);

    }

    @Parameters({"domain"})
    @Test
    public void selectSubConditionSearch(String domain) {
        driver = getDriver();

        driver.get(getUrl(domain));
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);

        homePage.header.clickConditionLink();
        String cond = "Heart Aneurysm";
        homePage.header.enterCondition(cond);

        Reporter.log(homePage.header.getSubSpecialtySearchSuggestions());

        homePage.header.clickSubSpec();

        SearchResultsPage results = homePage.header.clickSearch();

        Reporter.log(results.getResultsCount() + " for search: " + cond);

    }

    @Parameters({"domain"})
    @Test (dataProvider = "zipCodes")
    public void compareResultsToProfile(String zipCodes, String domain) {
        m_assert = new SoftAssert();

        driver = getDriver();

        driver.get(getUrl(domain));
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);

        String name = "Smith";

        homePage.header.enterName(name);
        homePage.header.enterLocation(zipCodes);

        SearchResultsPage results = homePage.header.clickSearch();

        List<Profile> docs = results.doctorResults(results.drList());

        Reporter.log("ResultsPage,ProfilePage,ProfilePageUrl");
        for (Profile doc : docs) {
            driver.get(doc.getUrl());
            doc.setProfileName(driver.findElement(By.cssSelector(".name.awards .current")).getText().trim());
            m_assert.assertTrue(doc.searchAndProfileMatches(),"Did not match: " + doc.toString());
            Reporter.log(doc.toString());

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

}