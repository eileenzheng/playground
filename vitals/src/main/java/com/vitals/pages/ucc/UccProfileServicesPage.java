package com.vitals.pages.ucc;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.vitals.DriverManager;

public class UccProfileServicesPage {
	
	private final WebDriver driver;
	
	public UccProfileServicesPage () {
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
	
	@FindBy(css=".nav-menu li:nth-child(3)>a")
	private WebElement menuAbout;
	
	@FindBy(css=".row.services>div.col-md-9")
	private List<WebElement> servicesText;
	
	@FindBy(css=".row.services h3")
	private List<WebElement> servicesTitle;
	
	public boolean isTitleMatched() {
		if ((breadcrumbs.get(breadcrumbs.size()-1)).getText().equals(h1.getText()))
			return true;
		else
			return false;
	}
	
	public boolean isServicesPage() {
		if (currentTrail.getText().equalsIgnoreCase("Services"))
			return true;
		else return false;
	}
	
	public String getServicesTitle() {
		StringBuilder buf = new StringBuilder();
		for (WebElement el : servicesTitle) {
			buf.append(el.getText()).append(",");
		}
		buf.deleteCharAt(buf.length()-1);
		return buf.toString();
	}
	
	public int getNumberOfUnavailableService() {
		int i=0;
		for (WebElement el: servicesText) {
			if (el.getText().contains("Our data indicates this center does not offer"))
				i++;
		}
		return i;
	}
	
	public UccProfileServicesPage clickMenu() {
		menu.click();
		return this;
	}
	
	public UccProfileReviewsPage clickMenuReviews() {
		menuReviews.click();
		return PageFactory.initElements(driver, UccProfileReviewsPage.class);
	}
	
	public UccProfileAboutPage clickMenuAbout() {
		menuAbout.click();
		return PageFactory.initElements(driver, UccProfileAboutPage.class);
	}
	
	public UccProfileSummaryPage goBackToSummaryPage() {
		h1.click();
		return PageFactory.initElements(driver, UccProfileSummaryPage.class);
	}
}
