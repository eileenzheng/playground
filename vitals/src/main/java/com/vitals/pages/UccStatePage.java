package com.vitals.pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.vitals.DriverManager;

public class UccStatePage {
	private WebDriver driver;

	public UccStatePage () {
		driver = DriverManager.getDriver();
	}
	
	@FindBy(css=".trail")
	private List<WebElement> breadcrumbs;
	
	@FindBy(css=".full h1")
	private WebElement title;
	
	@FindBy(css=".column-list>ul>li>ul>li>a")
	private List<WebElement> cities;
	
	public boolean isTitleMatched() {
		String state = breadcrumbs.get(2).getText();
		return (title.getText().contains(state) && title.getText().contains("Urgent Care"));
	}
	
	public UccCityPage clickCity() {
		int rand = (int) Math.floor(Math.random() * (cities.size() - 1));
		cities.get(rand).click();
		return PageFactory.initElements(driver, UccCityPage.class);
	}
	
}
