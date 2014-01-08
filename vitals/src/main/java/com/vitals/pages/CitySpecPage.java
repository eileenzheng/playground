package com.vitals.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.vitals.DriverManager;
import com.vitals.helpers.Constants;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CitySpecPage {
	private final WebDriver driver;
	public final PatientLinkCenterAd centerAd;
	public final PatientLinkRrAd rrAd;

	public CitySpecPage () {
		driver = DriverManager.getDriver();
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
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		try {
			if (results.size() >= 1) {
				driver.manage().timeouts().implicitlyWait(Constants.SELENIUM_IMPLICIT_WAIT, TimeUnit.SECONDS);
				return true;
			} else {
				driver.manage().timeouts().implicitlyWait(Constants.SELENIUM_IMPLICIT_WAIT, TimeUnit.SECONDS);
				return false;
			}
		} catch (NoSuchElementException e) {
			driver.manage().timeouts().implicitlyWait(Constants.SELENIUM_IMPLICIT_WAIT, TimeUnit.SECONDS);
			return false;
		}
	}
	
	public boolean hasNext() {
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		try {
			if (next.isDisplayed()) {
				driver.manage().timeouts().implicitlyWait(Constants.SELENIUM_IMPLICIT_WAIT, TimeUnit.SECONDS);
				return true;
			}
			else {
				driver.manage().timeouts().implicitlyWait(Constants.SELENIUM_IMPLICIT_WAIT, TimeUnit.SECONDS);
				return false;
			}
		} catch (NoSuchElementException e) {
			driver.manage().timeouts().implicitlyWait(Constants.SELENIUM_IMPLICIT_WAIT, TimeUnit.SECONDS);
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
	
	public List<WebElement> getResults() {
		return results;
	}
}
