package com.vitals.pages.ucc;

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

	@FindBy(css=".full.ucc>span>a")
	private List<WebElement> breadcrumbs;
	
	@FindBy(css=".full.ucc>span:last-child")
	private WebElement currentTrail;

	@FindBy(css="h1")
	private WebElement h1;
	
	@FindBy(css=".services .see-all")
	private WebElement seeAllLink;
	
	@FindBy(css=".col-md-4:first-child .ucc-box:not(.services) .see-all")
	private WebElement seeMoreLink;
	
	@FindBy(css=".col-md-4:nth-child(2) .ucc-box:first-child .see-all")
	private WebElement readWriteReviewsLink;
	
	@FindBy(css=".address a")
	private WebElement headerAddress;
	
	@FindBy(css=".col-md-4:nth-of-type(2) .col-md-12 a:nth-child(3)")
	private WebElement mapAddress;
	
	public boolean isTitleMatched() {
        return (breadcrumbs.get(breadcrumbs.size() - 1)).getText().equals(h1.getText());
	}
	
	public boolean isSummaryPage() {
        return currentTrail.getText().equalsIgnoreCase("Summary");
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
        return readWriteReviewsLink.getText().equals("Read patient reviews");
	}
	
	// this method should only be used when hasRating() returns true
	public UccProfileReviewsPage clickReadWriteReviewsLink() {
		readWriteReviewsLink.click();
		return PageFactory.initElements(driver, UccProfileReviewsPage.class);
	}
	
	public boolean isAddressMatched() {
		return (headerAddress.getAttribute("href").equals(mapAddress.getAttribute("href")));
	}
}
