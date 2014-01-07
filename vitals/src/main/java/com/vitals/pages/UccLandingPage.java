package com.vitals.pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vitals.DriverManager;

public class UccLandingPage {
	private WebDriver driver;

	public UccLandingPage () {
		driver = DriverManager.getDriver();
	}
	
	@FindBy(css=".trail.current>span")
	private WebElement breadcrumb;
	
	@FindBy(css=".full h1")
	private WebElement title;
	
	@FindBy(css=".column-list .state")
	private List<WebElement> states;
	
	@FindBy(css=".column-list>ul>li>ul>li>a")
	private List<WebElement> cities;
	
	public boolean isTitleMatched() {
		return (title.getText().equals(breadcrumb.getText()));
	}
	
	public UccStatePage clickState() {
		int rand = (int) Math.floor(Math.random() * (states.size() - 1));
		states.get(rand).click();
		return PageFactory.initElements(driver, UccStatePage.class);
	}
	
	public UccCityPage clickCity() {
		int rand = (int) Math.floor(Math.random() * (cities.size() - 1));
		cities.get(rand).click();
		return PageFactory.initElements(driver, UccCityPage.class);
	}
}