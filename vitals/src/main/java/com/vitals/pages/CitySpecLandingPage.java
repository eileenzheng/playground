package com.vitals.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vitals.DriverManager;

import java.util.ArrayList;
import java.util.List;

public class CitySpecLandingPage {
	private final WebDriver driver;

	public CitySpecLandingPage () {
		driver = DriverManager.getDriver();
	}
	
	@FindBy(css=".trail.current>span")
	private WebElement breadcrumb;
	
	@FindBy(css=".full h1")
	private WebElement title;
	
	@FindBy(css=".state")
	private List<WebElement> states;
	
	@FindBy(css=".column-list>ul>li>ul>li>a")
	private List<WebElement> cities;
	
	public boolean isTitleMatched() {
		return (title.getText().equals(breadcrumb.getText()));
	}
	
	public CitySpecStatePage clickState() {
		int rand = (int) Math.floor(Math.random() * (states.size() - 1));
		states.get(rand).click();
		return PageFactory.initElements(driver, CitySpecStatePage.class);
	}
	
	public CitySpecPage clickCity() {
		int rand = (int) Math.floor(Math.random() * (cities.size() - 1));
		cities.get(rand).click();
		return PageFactory.initElements(driver, CitySpecPage.class);
	}
	
	public List<String> getCityUrl(int x) {
		List<String> urls = new ArrayList<String>();
		int rand;
		for (int i=0; i<x; i++) {
			rand = (int) Math.floor(Math.random() * (cities.size() - 1));
			urls.add(cities.get(rand).getAttribute("href"));
		}
		return urls;
	}
}
