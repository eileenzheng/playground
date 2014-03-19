package com.vitals.pages.ucc;

import java.util.List;

import com.vitalsqa.listener.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
        return (breadcrumbs.get(breadcrumbs.size() - 1)).getText().equals(h1.getText());
	}
	
	public boolean isAboutPage() {
        return currentTrail.getText().equalsIgnoreCase("About");
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
