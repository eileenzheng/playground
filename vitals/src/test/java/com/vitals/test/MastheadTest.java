package com.vitals.test;

import com.vitalsqa.listener.DriverManager;
import com.vitalsqa.testrail.TestCase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.vitals.pages.HomePage;
import com.vitals.pages.ProfilePage;
import com.vitals.pages.ReviewPage;
import com.vitals.pages.ReviewWritePage;
import com.vitals.pages.ReviewSearchResultsPage;
import com.vitals.pages.SearchResultsPage;
import com.vitals.pages.ucc.UccProfileSummaryPage;
import com.vitals.pages.ucc.UccSearchResultsPage;
import com.vitals.helpers.Constants;

public class MastheadTest {

	private WebDriver driver;
	String[] url = new String[2];

	@Parameters({"url"})
	@BeforeMethod
	public void setup(String url) throws Exception {
		this.url[0] = url;
		if (url.toLowerCase().contains("staging"))
			this.url[1] = "https://my.staging.vitals.com";
		else if (url.toLowerCase().contains("qa"))
			this.url[1] = "http://my.qa.mdxdev.net";
		else
			this.url[1] = "https://my.vitals.com";
	}
	
	@TestCase(id=1603)
	@Test
	public void autoSuggestLocation() {
		driver = DriverManager.getDriver();

		for (int i=0; i<2; i++) {
			driver.get(url[i]);
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);

			String location = "1000";
			String city = "New York";

			homePage.header.openLocationBox();
			homePage.header.enterLocation(location);

			Assert.assertTrue(homePage.header.checkSuggestions(Constants.SearchType.LOCATION, city), env(i));
		}
		
		driver.manage().deleteAllCookies();
	}
	
	@TestCase(id=1604)
	@Test
	public void autoSuggestName() {
		driver = DriverManager.getDriver();

		for (int i=0; i<2; i++) {
			driver.get(url[i]);
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);

			String name = "Todd";

			homePage.header.enterSearchTerm(name);
			Reporter.log("The Docs> " + homePage.header.getNameSuggestions());

			Assert.assertTrue(homePage.header.checkSuggestions(Constants.SearchType.NAME, name), env(i));
		}
	}
	
	@TestCase(id=1605)
	@Test
	public void autoSuggestSpecialty() {
		driver = DriverManager.getDriver();

		for (int i=0; i<2; i++) {
			driver.get(url[i]);
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);

			String specialty = "Cardio";

			homePage.header.enterSearchTerm(specialty);
			Reporter.log("The Specialties> " + homePage.header.getSpecialtySearchSuggestions());

			Assert.assertTrue(homePage.header.checkSuggestions(Constants.SearchType.SPECIALTY, specialty), env(i));
		}
	}
	
	@TestCase(id=1606)
	@Test
	public void autoSuggestCondition() {
		driver = DriverManager.getDriver();

		for (int i=0; i<2; i++) {
			driver.get(url[i]);
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);

			String condition = "Diab";

			homePage.header.enterSearchTerm(condition);
			Reporter.log("The Conditions> " + homePage.header.getConditionSearchSuggestions());

			Assert.assertTrue(homePage.header.checkSuggestions(Constants.SearchType.CONDITION, condition), env(i));
		}
	}

	@TestCase(id=1616)
	@Test
	public void doctorSearchGo() {
		driver = DriverManager.getDriver();

		for (int i=0; i<2; i++) {
			driver.get(url[i]);
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);

			homePage.header.enterSearchTerm("John");
			SearchResultsPage serp = homePage.header.clickGoButton();
			acceptAlertIfPresent(driver);
			Assert.assertTrue(serp.getResultsCountNumber()>100, env(i));
		}
	}

	@TestCase(id=1617)
	@Test
	public void doctorSearchSeeAll() {
		driver = DriverManager.getDriver();

		for (int i=0; i<2; i++) {
			driver.get(url[i]);
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);

			homePage.header.enterSearchTerm("John");
			SearchResultsPage serp = homePage.header.clickShowAllLink();
			acceptAlertIfPresent(driver);
			Assert.assertTrue(serp.getResultsCountNumber()>100, env(i));
		}
	}

	@TestCase(id=1618)
	@Test
	public void doctorSearchClick() {
		driver = DriverManager.getDriver();

		for (int i=0; i<2; i++) {
			driver.get(url[i]);
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);

			homePage.header.enterSearchTerm("John");
			ProfilePage profilePage = homePage.header.selectRandomName();

			Assert.assertTrue(profilePage.isSummaryPage(), env(i));
		}
	}

	@TestCase(id=1619)
	@Test
	public void dentistSearchGo() {
		driver = DriverManager.getDriver();

		for (int i=0; i<2; i++) {
			driver.get(url[i]);
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			homePage.header.openFindDropdown();
			homePage.header.selectFindByDentist();

			homePage.header.enterSearchTerm("John");

			SearchResultsPage serp = homePage.header.clickGoButton();
			acceptAlertIfPresent(driver);
			Assert.assertTrue(serp.getResultsCountNumber()>100, env(i));
		}
	}

	@TestCase(id=1620)
	@Test
	public void dentistSearchSeeAll() {
		driver = DriverManager.getDriver();

		for (int i=0; i<2; i++) {
			driver.get(url[i]);
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			homePage.header.openFindDropdown();
			homePage.header.selectFindByDentist();

			homePage.header.enterSearchTerm("John");
			SearchResultsPage serp = homePage.header.clickShowAllLink();
			acceptAlertIfPresent(driver);
			Assert.assertTrue(serp.getResultsCountNumber()>100, env(i));
		}
	}

	@TestCase(id=1621)
	@Test
	public void dentistSearchClick() {
		driver = DriverManager.getDriver();

		for (int i=0; i<2; i++) {
			driver.get(url[i]);
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			homePage.header.openFindDropdown();
			homePage.header.selectFindByDentist();

			homePage.header.enterSearchTerm("John");
			ProfilePage profilePage = homePage.header.selectRandomName();

			Assert.assertTrue(profilePage.isSummaryPage(), env(i));
		}
	}

	@TestCase(id=1622)
	@Test
	public void uccSearchGo() {
		driver = DriverManager.getDriver();

		for (int i=0; i<2; i++) {
			driver.get(url[i]);
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			homePage.header.openFindDropdown();
			homePage.header.selectFindByUcc();
			homePage.header.enterSearchTerm("citymd");

			UccSearchResultsPage serp = homePage.header.clickGoButtonUcc();
			acceptAlertIfPresent(driver);
			Assert.assertTrue(serp.getResultsCountNumber()>=5, env(i));
		}
	}

	@TestCase(id=1623)
	@Test
	public void uccSearchSeeAll() {
		driver = DriverManager.getDriver();

		for (int i=0; i<2; i++) {
			driver.get(url[i]);
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			homePage.header.openFindDropdown();
			homePage.header.selectFindByUcc();

			homePage.header.enterSearchTerm("citymd");
			UccSearchResultsPage serp = homePage.header.clickShowAllLinkUcc();
			acceptAlertIfPresent(driver);
			Assert.assertTrue(serp.getResultsCountNumber()>=5, env(i));
		}
	}

	@TestCase(id=1624)
	@Test
	public void uccSearchClick() {
		driver = DriverManager.getDriver();

		for (int i=0; i<2; i++) {
			driver.get(url[i]);
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			homePage.header.openFindDropdown();
			homePage.header.selectFindByUcc();

			homePage.header.enterSearchTerm("citymd");
			UccProfileSummaryPage profilePage = homePage.header.selectRandomUcc();

			Assert.assertTrue(profilePage.isSummaryPage(), env(i));
		}
	}

	@TestCase(id=1625)
	@Test
	public void reviewSearchGo() {
		driver = DriverManager.getDriver();

		driver.get(url[0]);
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		ReviewPage reviewPage = homePage.header.clickReviewTab();
		reviewPage.header.enterReviewSearchTerm("John");

		ReviewSearchResultsPage reviewSerp = reviewPage.header.clickReviewGoButton();
		Assert.assertTrue(reviewSerp.isToggleProvider());
		Assert.assertTrue(reviewSerp.getResultsCountNumber()>100);
	}

	@TestCase(id=1626)
	@Test
	public void reviewSearchSeeAllDoctors() {
		driver = DriverManager.getDriver();

		driver.get(url[0]);
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		ReviewPage reviewPage = homePage.header.clickReviewTab();
		reviewPage.header.enterReviewSearchTerm("John");

		ReviewSearchResultsPage reviewSerp = reviewPage.header.clickShowAllDoctorsReview();
		Assert.assertTrue(reviewSerp.isToggleProvider());
		Assert.assertTrue(reviewSerp.getResultsCountNumber()>100);
	}

	@TestCase(id=1627)
	@Test
	public void reviewSearchSeeAllFacilities() {
		driver = DriverManager.getDriver();

		driver.get(url[0]);
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		ReviewPage reviewPage = homePage.header.clickReviewTab();
		reviewPage.header.enterReviewSearchTerm("city");

		ReviewSearchResultsPage reviewSerp = reviewPage.header.clickShowAllFacilitiesReview();
		Assert.assertTrue(reviewSerp.isToggleFacilities());
		Assert.assertTrue(reviewSerp.getResultsCountNumber()>30 && reviewSerp.getResultsCountNumber()<40);
	}

	@TestCase(id=1628)
	@Test
	public void reviewSearchClickDoctor() {
		driver = DriverManager.getDriver();

		driver.get(url[0]);
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		ReviewPage reviewPage = homePage.header.clickReviewTab();
		reviewPage.header.enterReviewSearchTerm("John");
		
		ReviewWritePage reviewWritePage = reviewPage.header.selectRandomNameReview();
			Assert.assertTrue(reviewWritePage.isDoctorReview());
	}

	@TestCase(id=1629)
	@Test
	public void reviewSearchClickFacility() {
		driver = DriverManager.getDriver();

		driver.get(url[0]);
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		ReviewPage reviewPage = homePage.header.clickReviewTab();
		reviewPage.header.enterReviewSearchTerm("city");

		ReviewWritePage reviewWritePage = reviewPage.header.selectRandomUccReview();
		Assert.assertTrue(reviewWritePage.isFacilityReview());
	}
	
	@TestCase(id=1735)
	@Test
	public void isLocationPopulated() {
		driver = DriverManager.getDriver();
		
		for (int i=0; i<2; i++) {
			driver.manage().deleteAllCookies();
			driver.get(url[i]);
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			Assert.assertTrue(homePage.header.locationSearchIsPopulated(), env(i));
	        Reporter.log(homePage.header.getCurrentPopulatedLocation());
		}
	}
	
	@TestCase(id=1736)
	@Test
	public void isLocationSynced() {
		driver = DriverManager.getDriver();
		
		driver.manage().deleteAllCookies();
		driver.get(url[0]);
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.header.openLocationBox();
		homePage.header.enterLocation("Fort Lee, NJ");
		homePage.header.selectFirstLocation();
		
		driver.get(url[1]);
		homePage = PageFactory.initElements(driver, HomePage.class);
		Assert.assertTrue(homePage.header.getCurrentPopulatedLocation().equals("Fort lee, NJ"));
	}

	private void acceptAlertIfPresent(WebDriver driver) {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();

		} catch (NoAlertPresentException e) {
			// do nothing
		}
	}
	
	private String env(int i) {
		
		if (i==0)
			return "Vitals: ";
		else
			return "MyVitals: ";
	}
}
