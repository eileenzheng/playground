package com.vitals.pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.vitals.DriverManager;

public class UccProfileSummaryPage {
	
	private final WebDriver driver;
	
	public UccProfileSummaryPage () {
		driver = DriverManager.getDriver();
	}

	@FindBy(css="span[itemprop=title]")
	private List<WebElement> breadcrumbs;

	@FindBy(css="h1")
	private WebElement h1;
	
	@FindBy(css=".services .see-all")
	private WebElement seeAllLink;
	
	@FindBy(css=".about .see-all")
	private WebElement seeMoreLink;
	
	@FindBy(css=".col2-reviews .see-all")
	private WebElement readWriteReviewsLink;
	
	@FindBy(css=".address a")
	private List<WebElement> mapLinks;
	
	
	public boolean isTitleMatched() {
		if ((breadcrumbs.get(breadcrumbs.size()-2)).getText().equals(h1.getText()))
			return true;
		else
			return false;
	}
	
	public boolean isSummaryPage() {
		if ((breadcrumbs.get(breadcrumbs.size()-1)).getText().equalsIgnoreCase("Summary"))
			return true;
		else return false;
	}
	
	public UccProfileServicesPage clickSeeAllLink() {
		seeAllLink.click();
		return PageFactory.initElements(driver, UccProfileServicesPage.class);
	}
	
	public UccProfileAboutPage clickSeeMoreLink() {
		seeMoreLink.click();
		return PageFactory.initElements(driver, UccProfileAboutPage.class);
	}
	
	public boolean hasRating() {
		if (readWriteReviewsLink.getText().equals("Read patient reviews"))
			return true;
		else
			return false;
	}
	
	// this method should only be used when hasRating() returns true
	public UccProfileReviewsPage clickReadWriteReviewsLink() {
		readWriteReviewsLink.click();
		return PageFactory.initElements(driver, UccProfileReviewsPage.class);
	}
	
	public boolean isAddressMatched() {
		if (mapLinks.get(0).getAttribute("href").equals(mapLinks.get(1).getAttribute("href")))
			return true;
		else return false;
	}
}
