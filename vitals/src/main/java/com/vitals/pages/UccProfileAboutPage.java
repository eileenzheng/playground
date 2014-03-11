package com.vitals.pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.vitals.DriverManager;

public class UccProfileAboutPage {
	
	private final WebDriver driver;
	
	public UccProfileAboutPage () {
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
	
	@FindBy(css=".nav-menu li:nth-child(2)>a")
	private WebElement menuReviews;
	
	@FindBy(css=".nav-menu li:nth-child(4)>a")
	private WebElement menuServices;
	
	public boolean isTitleMatched() {
		if ((breadcrumbs.get(breadcrumbs.size()-1)).getText().equals(h1.getText()))
			return true;
		else
			return false;
	}
	
	public boolean isAboutPage() {
		if (currentTrail.getText().equalsIgnoreCase("About"))
			return true;
		else return false;
	}
	
	public UccProfileAboutPage clickMenu() {
		menu.click();
		return this;
	}
	
	public UccProfileReviewsPage clickMenuReviews() {
		menuReviews.click();
		return PageFactory.initElements(driver, UccProfileReviewsPage.class);
	}
	
	public UccProfileServicesPage clickMenuServices() {
		menuServices.click();
		return PageFactory.initElements(driver, UccProfileServicesPage.class);
	}
	
	public UccProfileSummaryPage goBackToSummaryPage() {
		h1.click();
		return PageFactory.initElements(driver, UccProfileSummaryPage.class);
	}
}
