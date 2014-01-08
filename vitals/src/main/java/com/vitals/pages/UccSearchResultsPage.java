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
    public final PatientLinkRrAd rrAd;

    public UccSearchResultsPage() {
    	driver = DriverManager.getDriver();
        header = PageFactory.initElements(driver, HeaderPage.class);
        footer = PageFactory.initElements(driver, FooterPage.class);
        refinement = PageFactory.initElements(driver, UccSearchResultsRefinement.class);
        centerAd = PageFactory.initElements(driver, PatientLinkCenterAd.class);
        rrAd = PageFactory.initElements(driver, PatientLinkRrAd.class);
    }

    @FindBy (css="#result-count")
    private WebElement resultsTotal;

    @FindBy (css="#sort")
    private WebElement sortByDropdown;

    @FindBy (css=".featured-results-body")
    private List<WebElement> searchResults;

    public String getResultsCount() {
        return resultsTotal.getText();
    }
    
    public int getResultsCountNumber() {
    	String[] split = getResultsCount().split(" ");
    	String count = split[split.length-2];
    	split = count.split(",");
    	if (split.length==1)
    		return Integer.parseInt(split[0]);
    	else
    		return Integer.parseInt(split[0].concat(split[1]));
    }

    public List<WebElement> uccList() {
        return searchResults;
    }

    public List<Ucc> uccResults(List<WebElement> searchResults) {
        List<Ucc> ucc = new ArrayList<Ucc>();

        for (WebElement el : searchResults) {
            String name = el.findElement(By.cssSelector(".info-head>h4>a")).getText().trim();
            String url = el.findElement(By.cssSelector(".info-head>h4>a")).getAttribute("href");
            String address = el.findElement(By.cssSelector(".info address>[itemprop=streetAddress]>span")).getText();
            String city = el.findElement(By.cssSelector(".info address>[itemprop=addressLocality]")).getText();
            String state = el.findElement(By.cssSelector(".info address>[itemprop=addressRegion]")).getText();
            String zip = el.findElement(By.cssSelector(".info address>[itemprop=postalCode]")).getText();
            ucc.add(new Ucc(name,url,address,city,state,zip));
        }

        return ucc;
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
