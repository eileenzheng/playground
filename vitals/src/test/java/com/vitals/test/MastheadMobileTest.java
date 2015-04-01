package com.vitals.test;

import com.vitals.pages.BasePage;
import com.vitals.pages.HomePage;
import com.vitals.pages.SearchResultsPage;
import com.vitals.pages.profile.ProfileCommonPage;
import com.vitals.pages.ucc.UccProfileSummaryPage;
import com.vitals.pages.ucc.UccSearchResultsPage;
import com.vitalsqa.testrail.TestCase;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MastheadMobileTest {

    String url;
    BasePage page;
    SoftAssert m_assert;

    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) throws Exception {
        this.url = url;
    }

    @TestCase(id=3459)
    @Test
    public void autoSuggestLocation() {

        HomePage homePage = new HomePage();
        m_assert = new SoftAssert();

        String zip = "100";
        String city = "New York";

        homePage.get(url);
        homePage.headerModule().mobSearchMenu().click();
        homePage.headerModule().enterLocation(zip);
        m_assert.assertTrue(homePage.checkSuggestions(homePage.headerModule().locationSuggestions(), zip));

        homePage.get(url);
        homePage.headerModule().mobSearchMenu().click();
        homePage.headerModule().enterLocation(city);
        m_assert.assertTrue(homePage.checkSuggestions(homePage.headerModule().locationSuggestions(), city));

        m_assert.assertAll();
    }

    @TestCase(id=3460)
    @Test
    public void autoSuggestName() {

        m_assert = new SoftAssert();
        HomePage homePage = new HomePage();
        homePage.get(url);

        String name = "John";

        homePage.headerModule().mobSearchMenu().click();
        homePage.headerModule().enterSearchTerm(name);
        Reporter.log("The Docs> " + homePage.headerModule().getNameSuggestions());

        m_assert.assertTrue(homePage.checkSuggestions(homePage.headerModule().nameSuggestions(), name),
                "One or more autosuggest doctor results do not contain search term");

        m_assert.assertTrue(homePage.checkSuggestions(homePage.headerModule().uccSuggestions(), name),
                "One or more autosuggest facility results do not contain search term");

        m_assert.assertAll();
    }

    @TestCase(id=3461)
    @Test
    public void autoSuggestSpecialty() {

        HomePage homePage = new HomePage();
        homePage.get(url);

        String specialty = "Cardio";

        homePage.headerModule().mobSearchMenu().click();
        homePage.headerModule().enterSearchTerm(specialty);
        Reporter.log("The Specialties> " + homePage.headerModule().getSpecialtySearchSuggestions());

        Assert.assertTrue(homePage.checkSuggestions(homePage.headerModule().specialtySuggestions(), specialty),
                "One or more autosuggest results do not contain search term");

    }

    @TestCase(id=3462)
    @Test
    public void autoSuggestCondition() {

        HomePage homePage = new HomePage();
        homePage.get(url);

        String condition = "Diab";

        homePage.headerModule().mobSearchMenu().click();
        homePage.headerModule().enterSearchTerm(condition);

        Reporter.log("The Conditions> " + homePage.headerModule().getConditionSearchSuggestions());

        Assert.assertTrue(homePage.checkSuggestions(homePage.headerModule().conditionSuggestions(), condition),
                "One or more autosuggest results do not contain search term");
    }

    @TestCase(id=3463)
    @Test
    public void doctorSearchGo() {

        HomePage homePage = new HomePage();
        homePage.get(url);

        homePage.headerModule().mobSearchMenu().click();
        homePage.headerModule().enterSearchTerm("Sam");
        homePage.headerModule().goButton().click();

        SearchResultsPage serp = new SearchResultsPage();
        Assert.assertTrue(serp.getResultsCountNumber()>100);
    }

    @TestCase(id=3464)
    @Test
    public void doctorSearchClick() {

        HomePage homePage = new HomePage();
        homePage.get(url);

        homePage.headerModule().mobSearchMenu().click();
        homePage.headerModule().enterSearchTerm("John");
        homePage.getRandom(homePage.headerModule().nameSuggestions()).click();

        ProfileCommonPage profilePage = new ProfileCommonPage();
        profilePage.dismissReviewIntercept();

        Assert.assertTrue(profilePage.isSummaryPage());
    }

    @TestCase(id=3465)
    @Test
    public void uccSearchGo() {

        HomePage homePage = new HomePage();
        homePage.get(url);

        homePage.headerModule().mobSearchMenu().click();
        homePage.headerModule().enterSearchTerm("citymd");
        homePage.headerModule().goButton().click();
        homePage.headerModule().acceptAlertIfPresent();

        UccSearchResultsPage serp = new UccSearchResultsPage();
        Assert.assertTrue(serp.getResultsCountNumber()>=5);
    }

    @TestCase(id=3466)
    @Test
    public void uccSearchClick() {

        HomePage homePage = new HomePage();
        homePage.get(url);

        homePage.headerModule().mobSearchMenu().click();
        homePage.headerModule().enterSearchTerm("citymd");
        homePage.getRandom(homePage.headerModule().uccSuggestions()).click();

        UccProfileSummaryPage profilePage = new UccProfileSummaryPage();

        Assert.assertTrue(profilePage.currentTrail().getText().toString().equals("Summary"));
    }

    @TestCase(id=3467)
    @Test
    public void hamburger() {

        HomePage homePage = new HomePage();
        homePage.get(url);

        homePage.headerModule().mobSearchMenu().click();
        homePage.headerModule().enterLocation("New York");
        homePage.headerModule().locationSuggestions().get(0).click();
        homePage.headerModule().mobCloseSearch().click();
        homePage.headerModule().mobHamburger().click();
        homePage.headerModule().mobBrowseSpecialties().click();
        int rand = homePage.getRandomIndex(homePage.headerModule().mobSpecialtyList());
        homePage.scrollToElement(homePage.headerModule().mobSpecialtyList().get(rand));
        homePage.headerModule().mobSpecialtyList().get(rand).click();

        SearchResultsPage serp = new SearchResultsPage();
        Assert.assertTrue(serp.getResultsCountNumber()>=1);
    }
}
