package com.vitals.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.vitals.DriverManager;
import com.vitals.helpers.Ucc;

public class UccSearchResultsPage {
	private final WebDriver driver;
    public final HeaderPage header;
    public final FooterPage footer;
    public final UccSearchResultsRefinement refinement;
    public final PatientLinkCenterAd centerAd;

    public UccSearchResultsPage() {
    	driver = DriverManager.getDriver();
        header = PageFactory.initElements(driver, HeaderPage.class);
        footer = PageFactory.initElements(driver, FooterPage.class);
        refinement = PageFactory.initElements(driver, UccSearchResultsRefinement.class);
        centerAd = PageFactory.initElements(driver, PatientLinkCenterAd.class);
    }

    @FindBy (css="#result-count")
    private WebElement resultsTotal;

    @FindBy (css=".listing>.listing-details")
    private List<WebElement> searchResults;
    
    public int getResultsCountNumber() {
    	String count = resultsTotal.getText();
    	String[] split = count.split(",");
    	if (split.length==1)
    		return Integer.parseInt(split[0]);
    	else
    		return Integer.parseInt(split[0].concat(split[1]));
    }

    public List<WebElement> uccList() {
        return searchResults;
    }

    public List<Ucc> uccResults() {
        List<Ucc> ucc = new ArrayList<Ucc>();

        for (WebElement el : uccList()) {
            String name = el.findElement(By.cssSelector(".profile-name>a")).getText().trim();
            String url = el.findElement(By.cssSelector(".profile-name>a")).getAttribute("href");
            String address = el.findElement(By.cssSelector("span[itemprop=streetAddress]")).getText();
            String city = el.findElement(By.cssSelector("span[itemprop=addressLocality]")).getText();
            String state = el.findElement(By.cssSelector("span[itemprop=addressRegion]")).getText();
            String zip = el.findElement(By.cssSelector("span[itemprop=postalCode]")).getText();
            ucc.add(new Ucc(name,url,address,city,state,zip));
        }

        return ucc;
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
