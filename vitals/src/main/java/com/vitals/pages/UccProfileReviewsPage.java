package com.vitals.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vitals.DriverManager;

public class UccProfileReviewsPage {

	private final WebDriver driver;
	
	public UccProfileReviewsPage () {
		driver = DriverManager.getDriver();
	}

	@FindBy(css=".full.ucc>span>a")
	private List<WebElement> breadcrumbs;
	
	@FindBy(css=".full.ucc>span:last-child")
	private WebElement currentTrail;

	@FindBy(css="h1")
	private WebElement h1;
	
	@FindBy(css=".nav-menu")
	private WebElement menu;
	
	@FindBy(css=".nav-menu li:nth-child(4)>a")
	private WebElement menuServices;
	
	@FindBy(css=".nav-menu li:nth-child(3)>a")
	private WebElement menuAbout;
	
	@FindBy(css=".overall span.col-md-12")
	private WebElement totalRating;
	
	@FindBy(css=".rating-bars .count")
	private List<WebElement> ratingBreakdown;
	
	public boolean isTitleMatched() {
		if ((breadcrumbs.get(breadcrumbs.size()-1)).getText().equals(h1.getText()))
			return true;
		else
			return false;
	}
	
	public boolean isReviewsPage() {
		if (currentTrail.getText().equalsIgnoreCase("Reviews"))
			return true;
		else return false;
	}
	
	public int getTotalRating() {
		String rating = totalRating.getText().split(" ")[0];
		return Integer.parseInt(rating);
	}
	
	public int getTotalRatingFromBreakDown() {
		int total = 0;
		for (WebElement el : ratingBreakdown) {
			total = total + Integer.parseInt(el.getText());
		}
		return total;
	}
	
	public UccProfileReviewsPage clickMenu() {
		menu.click();
		return this;
	}
	
	public UccProfileServicesPage clickMenuServices() {
		menuServices.click();
		return PageFactory.initElements(driver, UccProfileServicesPage.class);
	}
	
	public UccProfileAboutPage clickMenuAbout() {
		menuAbout.click();
		return PageFactory.initElements(driver, UccProfileAboutPage.class);
	}
	
	public UccProfileSummaryPage goBackToSummaryPage() {
		h1.click();
		return PageFactory.initElements(driver, UccProfileSummaryPage.class);
	}
}
