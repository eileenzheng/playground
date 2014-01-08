package com.vitals.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.vitals.DriverManager;
import com.vitals.helpers.Ucc;

public class UccCityPage {
	private final WebDriver driver;
	public final PatientLinkCenterAd centerAd;
	public final PatientLinkRrAd rrAd;
	
	WebDriverWait wait;

	public UccCityPage () {
		driver = DriverManager.getDriver();
		centerAd = PageFactory.initElements(driver, PatientLinkCenterAd.class);
		rrAd = PageFactory.initElements(driver, PatientLinkRrAd.class);
	}

	@FindBy(css=".trail")
	private List<WebElement> breadcrumbs;
	
	@FindBy(css=".highlights h1")
	private WebElement title;
	
	@FindBy (css=".result.block")
	private List<WebElement> results;
	
	@FindBy (css=".count.block")
	private WebElement count;
	
	public boolean isTitleMatched() {
		String city = breadcrumbs.get(3).getText();
		return (title.getText().contains(city) && title.getText().contains("Urgent Care"));
	}
	
	public boolean hasResult() {
		// this page should return at least one result, or it won't be generated
		if (results.size()>=1)
			return true;
		else return false;
	}
	
	public List<Ucc> getResults() {
        List<Ucc> uccs = new ArrayList<Ucc>();

        for (WebElement el : results) {
            String name = el.findElement(By.cssSelector(".head>h4>a")).getText().trim();
            String url = el.findElement(By.cssSelector(".head>h4>a")).getAttribute("href");
            String address = el.findElement(By.cssSelector(".info address>[itemprop=streetAddress]>span")).getText();
            String city = el.findElement(By.cssSelector(".info address>[itemprop=addressLocality]")).getText();
            String state = el.findElement(By.cssSelector(".info address>[itemprop=addressRegion]")).getText();
            String zip = el.findElement(By.cssSelector(".info address>[itemprop=postalCode]")).getText();
            uccs.add(new Ucc(name,url,address,city,state,zip));
        }

        return uccs;
	}
}
