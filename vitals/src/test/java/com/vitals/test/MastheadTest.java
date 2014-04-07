package com.vitals.test;

import com.vitals.pages.profile.ProfileCommonPage;
import com.vitalsqa.testrail.TestCase;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.vitals.pages.HomePage;
import com.vitals.pages.ReviewPage;
import com.vitals.pages.ReviewWritePage;
import com.vitals.pages.ReviewSearchResultsPage;
import com.vitals.pages.SearchResultsPage;
import com.vitals.pages.ucc.UccProfileSummaryPage;
import com.vitals.pages.ucc.UccSearchResultsPage;
import com.vitals.helpers.Constants;
import org.testng.asserts.SoftAssert;

public class MastheadTest {

	String[] url = new String[2];
    SoftAssert m_assert;

	@Parameters({"url"})
	@BeforeMethod
	public void setup(String url) throws Exception {
		this.url[0] = url;
		if (url.toLowerCase().contains("staging"))
			this.url[1] = "https://my.staging.vitals.com";
		else if (url.toLowerCase().contains("mdxdev"))
			this.url[1] = "http://my.qa.mdxdev.net";
		else
			this.url[1] = "https://my.vitals.com";
	}
	
	@TestCase(id=1603)
	@Test
	public void autoSuggestLocation() {

		for (int i=0; i<2; i++) {
			HomePage homePage = new HomePage();
            homePage.get(url[i]);

			String location = "1000";
			String city = "New York";

            homePage.headerModule().locationTextBoxSelector().click();
            homePage.headerModule().enterLocation(location);

			Assert.assertTrue(homePage.headerModule().checkSuggestions(Constants.SearchType.LOCATION, city), env(i));

            homePage.deleteCookies();
		}
	}

    @TestCase(id=1604)
	@Test
	public void autoSuggestName() {

		for (int i=0; i<2; i++) {
            HomePage homePage = new HomePage();
            homePage.get(url[i]);

			String name = "Todd";

            homePage.headerModule().enterSearchTerm(name);
			Reporter.log("The Docs> " + homePage.headerModule().getNameSuggestions());

			Assert.assertTrue(homePage.headerModule().checkSuggestions(Constants.SearchType.NAME, name),
                    env(i) + "One or more autosuggest results do not contain search term");
		}
	}

	@TestCase(id=1605)
	@Test
	public void autoSuggestSpecialty() {

		for (int i=0; i<2; i++) {
            HomePage homePage = new HomePage();
            homePage.get(url[i]);

			String specialty = "Fam";

			homePage.headerModule().enterSearchTerm(specialty);
			Reporter.log("The Specialties> " + homePage.headerModule().getSpecialtySearchSuggestions());

			Assert.assertTrue(homePage.headerModule().checkSuggestions(Constants.SearchType.SPECIALTY, specialty),
                    env(i) + "One or more autosuggest results do not contain search term");
		}
	}

	@TestCase(id=1606)
	@Test
	public void autoSuggestCondition() {

		for (int i=0; i<2; i++) {
            HomePage homePage = new HomePage();
            homePage.get(url[i]);

			String condition = "Diab";

			homePage.headerModule().enterSearchTerm(condition);
			Reporter.log("The Conditions> " + homePage.headerModule().getConditionSearchSuggestions());

			Assert.assertTrue(homePage.headerModule().checkSuggestions(Constants.SearchType.CONDITION, condition),
                    env(i) + "One or more autosuggest results do not contain search term");
		}
	}

	@TestCase(id=1616)
	@Test
	public void doctorSearchGo() {

		for (int i=0; i<2; i++) {
			HomePage homePage = new HomePage();
            homePage.get(url[i]);

			homePage.headerModule().enterSearchTerm("John");
			homePage.headerModule().goButton().click();
            homePage.headerModule().acceptAlertIfPresent();

            SearchResultsPage serp = new SearchResultsPage();
			Assert.assertTrue(serp.getResultsCountNumber()>100, env(i));
		}
	}

	@TestCase(id=1617)
	@Test
	public void doctorSearchSeeAll() {

		for (int i=0; i<2; i++) {
            HomePage homePage = new HomePage();
            homePage.get(url[i]);

			homePage.headerModule().enterSearchTerm("John");
            homePage.headerModule().showAlllink().click();
            homePage.headerModule().acceptAlertIfPresent();

            SearchResultsPage serp = new SearchResultsPage();
			Assert.assertTrue(serp.getResultsCountNumber()>100, env(i));
		}
	}

	@TestCase(id=1618)
	@Test
	public void doctorSearchClick() {

		for (int i=0; i<2; i++) {
            HomePage homePage = new HomePage();
            homePage.get(url[i]);

			homePage.headerModule().enterSearchTerm("John");
            homePage.getRandom(homePage.headerModule().nameSuggestions()).click();

			ProfileCommonPage profilePage = new ProfileCommonPage();

			Assert.assertTrue(profilePage.isSummaryPage(), env(i));
		}
	}

	@TestCase(id=1619)
	@Test
	public void dentistSearchGo() {

		for (int i=0; i<2; i++) {
            HomePage homePage = new HomePage();
            homePage.get(url[i]);

			homePage.headerModule().findDropDown().click();
			homePage.headerModule().findByDentist().click();
			homePage.headerModule().enterSearchTerm("John");
            homePage.headerModule().goButton().click();
            homePage.headerModule().acceptAlertIfPresent();

            SearchResultsPage serp = new SearchResultsPage();
			Assert.assertTrue(serp.getResultsCountNumber()>100, env(i));
		}
	}

	@TestCase(id=1620)
	@Test
	public void dentistSearchSeeAll() {

		for (int i=0; i<2; i++) {
            HomePage homePage = new HomePage();
            homePage.get(url[i]);

			homePage.headerModule().findDropDown().click();
			homePage.headerModule().findByDentist().click();
			homePage.headerModule().enterSearchTerm("John");
            homePage.headerModule().showAlllink().click();
            homePage.headerModule().acceptAlertIfPresent();

            SearchResultsPage serp = new SearchResultsPage();
			Assert.assertTrue(serp.getResultsCountNumber()>100, env(i));
        }
	}

	@TestCase(id=1621)
	@Test
	public void dentistSearchClick() {

		for (int i=0; i<2; i++) {
            HomePage homePage = new HomePage();
            homePage.get(url[i]);

            homePage.headerModule().findDropDown().click();
            homePage.headerModule().findByDentist().click();
            homePage.headerModule().enterSearchTerm("John");
            homePage.getRandom(homePage.headerModule().nameSuggestions()).click();

			ProfileCommonPage profilePage = new ProfileCommonPage();

			Assert.assertTrue(profilePage.isSummaryPage(), env(i));
		}
	}

	@TestCase(id=1622)
	@Test
	public void uccSearchGo() {

		for (int i=0; i<2; i++) {
            HomePage homePage = new HomePage();
            homePage.get(url[i]);

            homePage.headerModule().findDropDown().click();
            homePage.headerModule().findByUcc().click();
            homePage.headerModule().enterSearchTerm("citymd");
            homePage.headerModule().goButton().click();
            homePage.headerModule().acceptAlertIfPresent();

            UccSearchResultsPage serp = new UccSearchResultsPage();
            Assert.assertTrue(serp.getResultsCountNumber()>=5, env(i));
		}
	}

	@TestCase(id=1623)
	@Test
	public void uccSearchSeeAll() {

		for (int i=0; i<2; i++) {
            HomePage homePage = new HomePage();
            homePage.get(url[i]);

            homePage.headerModule().findDropDown().click();
            homePage.headerModule().findByUcc().click();
            homePage.headerModule().enterSearchTerm("citymd");
            homePage.headerModule().showAlllink().click();
            homePage.headerModule().acceptAlertIfPresent();

            UccSearchResultsPage serp = new UccSearchResultsPage();
            Assert.assertTrue(serp.getResultsCountNumber()>=5, env(i));
		}
	}

	@TestCase(id=1624)
	@Test
	public void uccSearchClick() {

		for (int i=0; i<2; i++) {
            HomePage homePage = new HomePage();
            homePage.get(url[i]);

            homePage.headerModule().findDropDown().click();
            homePage.headerModule().findByUcc().click();
            homePage.headerModule().enterSearchTerm("citymd");
            homePage.getRandom(homePage.headerModule().uccSuggestions()).click();

            UccProfileSummaryPage profilePage = new UccProfileSummaryPage();

            Assert.assertTrue(profilePage.currentTrail().getText().toString().equals("Summary"), env(i));
		}
	}

	@TestCase(id=1625)
	@Test
	public void reviewSearchGo() {

        m_assert = new SoftAssert();

		HomePage homePage = new HomePage();
        homePage.get(url[0]);

        homePage.headerModule().writeReviewTab().click();
		ReviewPage reviewPage = new ReviewPage();
		reviewPage.headerModule().enterReviewSearchTerm("John");
        reviewPage.headerModule().reviewGoButton().click();

		ReviewSearchResultsPage reviewSerp = new ReviewSearchResultsPage();
		m_assert.assertTrue(reviewSerp.activeToggle().getText().toString().equals("Doctors"));
		m_assert.assertTrue(reviewSerp.getResultsCountNumber()>100);

        m_assert.assertAll();
	}

	@TestCase(id=1626)
	@Test
	public void reviewSearchSeeAllDoctors() {

        m_assert = new SoftAssert();
        HomePage homePage = new HomePage();
        homePage.get(url[0]);

        homePage.headerModule().writeReviewTab().click();
        ReviewPage reviewPage = new ReviewPage();
        reviewPage.headerModule().enterReviewSearchTerm("John");
        reviewPage.headerModule().showAllDoctors().click();

		ReviewSearchResultsPage reviewSerp = new ReviewSearchResultsPage();
        m_assert.assertTrue(reviewSerp.activeToggle().getText().toString().equals("Doctors"));
        m_assert.assertTrue(reviewSerp.getResultsCountNumber()>100);

        m_assert.assertAll();
	}

	@TestCase(id=1627)
	@Test
	public void reviewSearchSeeAllFacilities() {
        m_assert = new SoftAssert();
        HomePage homePage = new HomePage();
        homePage.get(url[0]);

        homePage.headerModule().writeReviewTab().click();
        ReviewPage reviewPage = new ReviewPage();
        reviewPage.headerModule().enterReviewSearchTerm("city");
        reviewPage.headerModule().showAllFacilities().click();

        ReviewSearchResultsPage reviewSerp = new ReviewSearchResultsPage();
        m_assert.assertTrue(reviewSerp.activeToggle().getText().toString().equals("Facilities"));
        m_assert.assertTrue(reviewSerp.getResultsCountNumber()>30 && reviewSerp.getResultsCountNumber()<40);

        m_assert.assertAll();
	}

	@TestCase(id=1628)
	@Test
	public void reviewSearchClickDoctor() {
        m_assert = new SoftAssert();
        HomePage homePage = new HomePage();
        homePage.get(url[0]);

        homePage.headerModule().writeReviewTab().click();
        ReviewPage reviewPage = new ReviewPage();
        reviewPage.headerModule().enterReviewSearchTerm("John");
        reviewPage.getRandom(reviewPage.headerModule().nameSuggestions()).click();

		ReviewWritePage reviewWritePage = new ReviewWritePage();
		Assert.assertTrue(reviewWritePage.isDoctorReview());
	}

	@TestCase(id=1629)
	@Test
	public void reviewSearchClickFacility() {
        m_assert = new SoftAssert();
        HomePage homePage = new HomePage();
        homePage.get(url[0]);

        homePage.headerModule().writeReviewTab().click();
        ReviewPage reviewPage = new ReviewPage();
        reviewPage.headerModule().enterReviewSearchTerm("city");
        reviewPage.getRandom(reviewPage.headerModule().uccSuggestions()).click();

        ReviewWritePage reviewWritePage = new ReviewWritePage();
		Assert.assertTrue(reviewWritePage.isFacilityReview());
	}

	@TestCase(id=1735)
	@Test
	public void isLocationPopulated() {

		for (int i=0; i<2; i++) {
			HomePage homePage = new HomePage();
            homePage.deleteCookies();
            homePage.get(url[i]);

			Assert.assertTrue(homePage.headerModule().locationSearchIsPopulated(), env(i));
	        Reporter.log(homePage.headerModule().locationTextBox().getAttribute("value").toString());
		}
	}

	@TestCase(id=1736)
	@Test
	public void isLocationSynced() {

        HomePage homePage = new HomePage();
        homePage.deleteCookies();
        homePage.get(url[0]);
		homePage.headerModule().locationTextBoxSelector().click();
		homePage.headerModule().enterLocation("Fort Lee, NJ");
		homePage.headerModule().locationSuggestions().get(0).click();

        homePage.get(url[1]);
		Assert.assertTrue(homePage.headerModule().locationTextBox().getAttribute("value").toString().equals("Fort lee, NJ"));
	}

    private String env (int i) {
        if (i==0)
            return "Vitals: ";
        else
            return "MyVitals: ";
    }
}