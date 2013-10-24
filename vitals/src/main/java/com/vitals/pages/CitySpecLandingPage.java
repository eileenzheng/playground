package com.vitals.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vitals.DriverManager;

import java.util.List;

public class CitySpecLandingPage {
	private WebDriver driver;
	public final HeaderPage header;
	public final FooterPage footer;

	public CitySpecLandingPage () {
		driver = DriverManager.getDriver();
		this.header = PageFactory.initElements(driver, HeaderPage.class);
		this.footer = PageFactory.initElements(driver, FooterPage.class);
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
}
