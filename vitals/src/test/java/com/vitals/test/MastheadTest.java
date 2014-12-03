package com.vitals.test;

import com.vitals.pages.profile.ProfileCommonPage;
import com.vitalsqa.testrail.TestCase;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.vitals.pages.HomePage;
import com.vitals.pages.SearchResultsPage;
import com.vitals.pages.ucc.UccProfileSummaryPage;
import com.vitals.pages.ucc.UccSearchResultsPage;
import org.testng.asserts.SoftAssert;

public class MastheadTest {

	String[] url = new String[2];
    SoftAssert m_assert;

	@Parameters({"url"})
	@BeforeMethod
	public void setup(String url) throws Exception {
		this.url[0] = url;
		if (url.toLowerCase().contains("staging"))
			this.url[1] = "https://admin:mdx4dm1n@my.staging.vitals.com";
		else if (url.toLowerCase().contains("mdxdev"))
			this.url[1] = "http://my.qa.mdxdev.net";
		else
			this.url[1] = "https://my.vitals.com";
	}
	
	@TestCase(id=1603)
	@Test
	public void autoSuggestLocation() {
        m_assert = new SoftAssert();

		for (int i=0; i<2; i++) {
			HomePage homePage = new HomePage();

			String zip = "100";
			String city = "New York";

            homePage.get(url[i]);
            homePage.headerModule().enterLocation(zip);
            m_assert.assertTrue(homePage.checkSuggestions(homePage.headerModule().locationSuggestions(), zip), env(i));
            homePage.get(url[i]);
            homePage.headerModule().enterLocation(city);
            m_assert.assertTrue(homePage.checkSuggestions(homePage.headerModule().locationSuggestions(), city), env(i));

            m_assert.assertAll();
        }
	}

    @TestCase(id=1604)
	@Test
	public void autoSuggestName() {
        m_assert = new SoftAssert();

		for (int i=0; i<2; i++) {
            HomePage homePage = new HomePage();
            homePage.get(url[i]);

			String name = "John";

            homePage.headerModule().enterSearchTerm(name);
			Reporter.log("The Docs> " + homePage.headerModule().getNameSuggestions());

			m_assert.assertTrue(homePage.checkSuggestions(homePage.headerModule().nameSuggestions(), name),
                    env(i) + "One or more autosuggest doctor results do not contain search term");

            m_assert.assertTrue(homePage.checkSuggestions(homePage.headerModule().uccSuggestions(), name),
                    env(i) + "One or more autosuggest facility results do not contain search term");

            m_assert.assertAll();
        }
	}

	@TestCase(id=1605)
	@Test
	public void autoSuggestSpecialty() {

		for (int i=0; i<2; i++) {
            HomePage homePage = new HomePage();
            homePage.get(url[i]);

			String specialty = "Cardio";

			homePage.headerModule().enterSearchTerm(specialty);
			Reporter.log("The Specialties> " + homePage.headerModule().getSpecialtySearchSuggestions());

			Assert.assertTrue(homePage.checkSuggestions(homePage.headerModule().specialtySuggestions(), specialty),
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

			Assert.assertTrue(homePage.checkSuggestions(homePage.headerModule().conditionSuggestions(), condition),
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

    @TestCase(id=1618)
    @Test
    public void doctorSearchClick() {

        for (int i=0; i<2; i++) {
            HomePage homePage = new HomePage();
            homePage.get(url[i]);

            homePage.headerModule().enterSearchTerm("John");
            homePage.getRandom(homePage.headerModule().nameSuggestions()).click();

            ProfileCommonPage profilePage = new ProfileCommonPage();
            profilePage.dismissReviewIntercept();

            Assert.assertTrue(profilePage.isSummaryPage(), env(i));
        }
    }

	@TestCase(id=1617)
	@Test
	public void doctorSearchSeeAll() {

		for (int i=0; i<2; i++) {
            HomePage homePage = new HomePage();
            homePage.get(url[i]);

			homePage.headerModule().enterSearchTerm("John");
            homePage.headerModule().showAll().get(0).click();
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

			homePage.headerModule().enterSearchTerm("John");
            homePage.headerModule().showAll().get(1).click();
            homePage.headerModule().acceptAlertIfPresent();

            SearchResultsPage serp = new SearchResultsPage();
			Assert.assertTrue(serp.getResultsCountNumber()>100, env(i));
        }
	}

	@TestCase(id=1622)
	@Test
	public void uccSearchGo() {

		for (int i=0; i<2; i++) {
            HomePage homePage = new HomePage();
            homePage.get(url[i]);

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

            homePage.headerModule().enterSearchTerm("citymd");
            homePage.headerModule().showAll().get(0).click();
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

            homePage.headerModule().enterSearchTerm("citymd");
            homePage.getRandom(homePage.headerModule().uccSuggestions()).click();

            UccProfileSummaryPage profilePage = new UccProfileSummaryPage();

            Assert.assertTrue(profilePage.currentTrail().getText().toString().equals("Summary"), env(i));
		}
	}

	@TestCase(id=1735)
	@Test
	public void isLocationPopulated() {

		for (int i=0; i<2; i++) {
			HomePage homePage = new HomePage();
            homePage.deleteCookies();
            homePage.get(url[i]);

            // workaround to wait longer for it to load
            if (!homePage.headerModule().locationSearchIsPopulated()) {
                for (int j=0; j<10; j++) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (homePage.headerModule().locationSearchIsPopulated())
                        break;
                }
            }

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
		homePage.headerModule().enterLocation("Fort Lee, NJ");
		homePage.headerModule().locationSuggestions().get(0).click();

        homePage.get(url[1]);
        Assert.assertTrue(homePage.headerModule().locationTextBox().getAttribute("value").toString().equals("Fort Lee, NJ"));
	}

    private String env (int i) {
        if (i==0)
            return "Vitals: ";
        else
            return "MyVitals: ";
    }
}