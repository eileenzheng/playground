package com.vitals.pages.ucc;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vitals.DriverManager;

public class UccSitemapPage {
	private final WebDriver driver;

	public UccSitemapPage () {
		driver = DriverManager.getDriver();
	}
	
	@FindBy(css="h1")
	private WebElement title;
	
	@FindBy(css=".col-sm-4 li>a")
	private List<WebElement> states;
	
	@FindBy(css=".col-sm-4 li>div>a")
	private List<WebElement> cities;
	
	public UccSitemapStatePage clickState() {
		int rand = (int) Math.floor(Math.random() * (states.size() - 1));
		states.get(rand).click();
		return PageFactory.initElements(driver, UccSitemapStatePage.class);
	}
	
	public UccSearchResultsPage clickCity() {
		int rand = (int) Math.floor(Math.random() * (cities.size() - 1));
		cities.get(rand).click();
		return PageFactory.initElements(driver, UccSearchResultsPage.class);
	}
}