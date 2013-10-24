package com.vitals.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vitals.DriverManager;

import java.util.List;

public class CitySpecStatePage {
	private WebDriver driver;
	public final HeaderPage header;
	public final FooterPage footer;

	public CitySpecStatePage () {
		driver = DriverManager.getDriver();
		this.header = PageFactory.initElements(driver, HeaderPage.class);
		this.footer = PageFactory.initElements(driver, FooterPage.class);
	}
	
	@FindBy(css=".trail")
	private List<WebElement> breadcrumbs;
	
	@FindBy(css=".full h1")
	private WebElement title;
	
	@FindBy(css=".column-list>ul>li>ul>li>a")
	private List<WebElement> cities;
	
	public boolean isTitleMatched() {
		String specialty = breadcrumbs.get(1).getText();
		String state = breadcrumbs.get(2).getText();
		return (title.getText().contains(state) && title.getText().contains(specialty));
	}
	
	public CitySpecPage clickCity() {
		int rand = (int) Math.floor(Math.random() * (cities.size() - 1));
		cities.get(rand).click();
		return PageFactory.initElements(driver, CitySpecPage.class);
	}
	
}
