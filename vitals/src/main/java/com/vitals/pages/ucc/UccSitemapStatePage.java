package com.vitals.pages.ucc;

import java.util.List;

import com.vitalsqa.listener.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UccSitemapStatePage {
	private final WebDriver driver;

	public UccSitemapStatePage () {
		driver = DriverManager.getDriver();
	}
	
	@FindBy(css=".column-list li>a")
	private List<WebElement> cities;
	
	public UccSearchResultsPage clickCity() {
		int rand = (int) Math.floor(Math.random() * (cities.size() - 1));
		cities.get(rand).click();
		return PageFactory.initElements(driver, UccSearchResultsPage.class);
	}
	
	public boolean hasResults() {
		return cities.size()>0;
	}
}
