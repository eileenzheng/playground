package com.vitals.test;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.vitals.DriverManager;
import com.vitals.pages.HomePage;
import com.vitals.pages.ProfilePage;
import com.vitals.pages.ReviewPage;
import com.vitals.pages.ReviewSearchResultsPage;
import com.vitals.pages.SearchResultsPage;
import com.vitals.pages.UccProfileSummaryPage;
import com.vitals.pages.UccSearchResultsPage;

public class MastheadTest {

	WebDriver driver;
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
	public void doctorSearchGo() {
		driver = DriverManager.getDriver();

		for (int i=0; i<2; i++) {
			driver.get(url[i]);
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);

			homePage.header.enterSearchTerm("John");
			SearchResultsPage serp = homePage.header.clickGoButton();
			acceptAlertIfPresent(driver);
			Assert.assertTrue(serp.getResultsCountNumber()>100);
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
			Assert.assertTrue(serp.getResultsCountNumber()>100);
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

			Assert.assertTrue(profilePage.isSummaryPage());
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
			Assert.assertTrue(serp.getResultsCountNumber()>100);
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
			Assert.assertTrue(serp.getResultsCountNumber()>100);
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

			Assert.assertTrue(profilePage.isSummaryPage());
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
			Assert.assertTrue(serp.getResultsCountNumber()>=5);
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
			Assert.assertTrue(serp.getResultsCountNumber()>=5);
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

			Assert.assertTrue(profilePage.isSummaryPage());
		}
	}

	@Test
	public void reviewSearchGo() {
		driver = DriverManager.getDriver();

		for (int i=0; i<2; i++) {
			driver.get(url[i]);
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			homePage.header.hoverReviewTab();
			homePage.header.enterReviewSearchTerm("John");

			ReviewSearchResultsPage reviewSerp = homePage.header.clickReviewGoButton();
			acceptAlertIfPresent(driver);
			Assert.assertTrue(reviewSerp.isToggleProvider());
			Assert.assertTrue(reviewSerp.getResultsCountNumber()>100);
		}
	}

	@Test
	public void reviewSearchSeeAllDoctors() {
		driver = DriverManager.getDriver();

		for (int i=0; i<2; i++) {
			driver.get(url[i]);
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			homePage.header.hoverReviewTab();
			homePage.header.enterReviewSearchTerm("John");

			ReviewSearchResultsPage reviewSerp = homePage.header.clickShowAllDoctorsReview();
			acceptAlertIfPresent(driver);
			Assert.assertTrue(reviewSerp.isToggleProvider());
			Assert.assertTrue(reviewSerp.getResultsCountNumber()>100);
		}
	}

	@Test
	public void reviewSearchSeeAllFacilities() {
		driver = DriverManager.getDriver();

		for (int i=0; i<2; i++) {
			driver.get(url[i]);
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			homePage.header.hoverReviewTab();
			homePage.header.enterReviewSearchTerm("city");

			ReviewSearchResultsPage reviewSerp = homePage.header.clickShowAllFacilitiesReview();
			acceptAlertIfPresent(driver);
			Assert.assertTrue(reviewSerp.isToggleFacilities());
			Assert.assertTrue(reviewSerp.getResultsCountNumber()>100);
		}
	}

	@Test
	public void reviewSearchClickDoctor() {
		driver = DriverManager.getDriver();

		for (int i=0; i<2; i++) {
			driver.get(url[i]);
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			homePage.header.hoverReviewTab();
			homePage.header.enterReviewSearchTerm("John");

			ReviewPage reviewPage = homePage.header.clickRandomNameReview();
			Assert.assertTrue(reviewPage.isDoctorReview());
		}
	}

	@Test
	public void reviewSearchClickFacility() {
		driver = DriverManager.getDriver();

		for (int i=0; i<2; i++) {
			driver.get(url[i]);
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			homePage.header.hoverReviewTab();
			homePage.header.enterReviewSearchTerm("city");

			ReviewPage reviewPage = homePage.header.clickRandomUccReview();
			Assert.assertTrue(reviewPage.isFacilityReview());
		}
	}

	private void acceptAlertIfPresent(WebDriver driver) {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();

		} catch (NoAlertPresentException e) {
			// do nothing
		}
	}
}
