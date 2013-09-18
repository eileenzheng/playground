package com.vitals.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CitySpecPage {
	private WebDriver driver;
	public final HeaderPage header;
	public final FooterPage footer;
	public final PatientLinkCenterAd centerAd;
	public final PatientLinkRrAd rrAd;
	
	WebDriverWait wait;

	public CitySpecPage (WebDriver driver) {
		this.driver = driver;
		this.header = PageFactory.initElements(driver, HeaderPage.class);
		this.footer = PageFactory.initElements(driver, FooterPage.class);
		this.centerAd = PageFactory.initElements(driver, PatientLinkCenterAd.class);
		this.rrAd = PageFactory.initElements(driver, PatientLinkRrAd.class);
	}

	@FindBy(css=".trail")
	private List<WebElement> breadcrumbs;
	
	@FindBy(css=".highlights h1")
	private WebElement title;
	
	@FindBy (css=".result.block")
	private List<WebElement> results;
	
	@FindBy (css=".count.block")
	private WebElement count;
	
	@FindBy (css=".next>a")
	private WebElement next;
	
	@FindBy (css=".pagination .active>a")
	private WebElement activePage;
	
	public boolean isTitleMatched() {
		String specialty = breadcrumbs.get(1).getText();
		String city = breadcrumbs.get(3).getText();
		return (title.getText().contains(city) && title.getText().contains(specialty));
	}
	
	public boolean hasResult() {
		// this page should return at least one result, or it won't be generated
		if (results.size()>=1)
			return true;
		else return false;
	}
	
	public boolean hasNext() {
		try {
			if (next.isDisplayed())
				return true;
			else
				return false;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public CitySpecPage clickNext() {
		next.click();
		return this;
	}
	
	public String getActivePageNumber () {
		return activePage.getText();
	}
}
