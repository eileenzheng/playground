package com.vitals.test;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.vitals.DriverManager;
import com.vitals.pages.HomePage;
import com.vitals.pages.ProfilePage;
import com.vitals.pages.ReviewPage;
import com.vitals.pages.ReviewWritePage;
import com.vitals.pages.ReviewSearchResultsPage;
import com.vitals.pages.SearchResultsPage;
import com.vitals.pages.UccProfileSummaryPage;
import com.vitals.pages.UccSearchResultsPage;
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
	}
	
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

	@Test
	public void doctorSearchClick() {
		driver = DriverManager.getDriver();

		for (int i=0; i<2; i++) {
			driver.get(url[i]);
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);

			homePage.header.enterSearchTerm("John");
			ProfilePage profilePage = homePage.header.clickRandomName();

			Assert.assertTrue(profilePage.isSummaryPage(), env(i));
		}
	}

	@Test
	public void dentistSearchGo() {
		driver = DriverManager.getDriver();

		for (int i=0; i<2; i++) {
			driver.get(url[i]);
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			homePage.header.openFindDropdown();
			homePage.header.clickFindByDentist();

			homePage.header.enterSearchTerm("John");

			SearchResultsPage serp = homePage.header.clickGoButton();
			acceptAlertIfPresent(driver);
			Assert.assertTrue(serp.getResultsCountNumber()>100, env(i));
		}
	}

	@Test
	public void dentistSearchSeeAll() {
		driver = DriverManager.getDriver();

		for (int i=0; i<2; i++) {
			driver.get(url[i]);
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			homePage.header.openFindDropdown();
			homePage.header.clickFindByDentist();

			homePage.header.enterSearchTerm("John");
			SearchResultsPage serp = homePage.header.clickShowAllLink();
			acceptAlertIfPresent(driver);
			Assert.assertTrue(serp.getResultsCountNumber()>100, env(i));
		}
	}

	@Test
	public void dentistSearchClick() {
		driver = DriverManager.getDriver();

		for (int i=0; i<2; i++) {
			driver.get(url[i]);
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			homePage.header.openFindDropdown();
			homePage.header.clickFindByDentist();

			homePage.header.enterSearchTerm("John");
			ProfilePage profilePage = homePage.header.clickRandomName();

			Assert.assertTrue(profilePage.isSummaryPage(), env(i));
		}
	}

	@Test
	public void uccSearchGo() {
		driver = DriverManager.getDriver();

		for (int i=0; i<2; i++) {
			driver.get(url[i]);
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			homePage.header.openFindDropdown();
			homePage.header.clickFindByUcc();
			homePage.header.enterSearchTerm("citymd");

			UccSearchResultsPage serp = homePage.header.clickGoButtonUcc();
			acceptAlertIfPresent(driver);
			Assert.assertTrue(serp.getResultsCountNumber()>=5, env(i));
		}
	}

	@Test
	public void uccSearchSeeAll() {
		driver = DriverManager.getDriver();

		for (int i=0; i<2; i++) {
			driver.get(url[i]);
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			homePage.header.openFindDropdown();
			homePage.header.clickFindByUcc();

			homePage.header.enterSearchTerm("citymd");
			UccSearchResultsPage serp = homePage.header.clickShowAllLinkUcc();
			acceptAlertIfPresent(driver);
			Assert.assertTrue(serp.getResultsCountNumber()>=5, env(i));
		}
	}

	@Test
	public void uccSearchClick() {
		driver = DriverManager.getDriver();

		for (int i=0; i<2; i++) {
			driver.get(url[i]);
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			homePage.header.openFindDropdown();
			homePage.header.clickFindByUcc();

			homePage.header.enterSearchTerm("citymd");
			UccProfileSummaryPage profilePage = homePage.header.clickRandomUcc();

			Assert.assertTrue(profilePage.isSummaryPage(), env(i));
		}
	}

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

	@Test
	public void reviewSearchSeeAllFacilities() {
		driver = DriverManager.getDriver();

		driver.get(url[0]);
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		ReviewPage reviewPage = homePage.header.clickReviewTab();
		reviewPage.header.enterReviewSearchTerm("city");

		ReviewSearchResultsPage reviewSerp = reviewPage.header.clickShowAllFacilitiesReview();
		Assert.assertTrue(reviewSerp.isToggleFacilities());
		Assert.assertTrue(reviewSerp.getResultsCountNumber()==80);
	}

	@Test
	public void reviewSearchClickDoctor() {
		driver = DriverManager.getDriver();

		driver.get(url[0]);
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		ReviewPage reviewPage = homePage.header.clickReviewTab();
		reviewPage.header.enterReviewSearchTerm("John");
		
		ReviewWritePage reviewWritePage = reviewPage.header.clickRandomNameReview();
			Assert.assertTrue(reviewWritePage.isDoctorReview());
	}

	@Test
	public void reviewSearchClickFacility() {
		driver = DriverManager.getDriver();

		driver.get(url[0]);
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		ReviewPage reviewPage = homePage.header.clickReviewTab();
		reviewPage.header.enterReviewSearchTerm("city");

		ReviewWritePage reviewWritePage = reviewPage.header.clickRandomUccReview();
		Assert.assertTrue(reviewWritePage.isFacilityReview());
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
