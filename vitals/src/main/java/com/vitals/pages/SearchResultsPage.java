package com.vitals.pages;

import com.vitals.DriverManager;
import com.vitals.helpers.Profile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsPage {

    private final WebDriver driver;
    public final HeaderPage header;
    public final FooterPage footer;
    public final SearchResultsRefinement refinement;
    public final PatientLinkCenterAd centerAd;

    public SearchResultsPage() {
    	driver = DriverManager.getDriver();
        header = PageFactory.initElements(driver, HeaderPage.class);
        footer = PageFactory.initElements(driver, FooterPage.class);
        refinement = PageFactory.initElements(driver, SearchResultsRefinement.class);
        centerAd = PageFactory.initElements(driver, PatientLinkCenterAd.class);
    }

    @FindBy (css="h1>span:first-child")
    private WebElement resultsTotal;

    @FindBy (css="#results-content .v-pwl.listing")
    private List<WebElement> searchResults;
    
    @FindBy (css=".breadcrumb .trail")
    private List<WebElement> breadcrumb;
    
    @FindBy (css=".breadcrumb li:last-child")
    private WebElement breadcrumbCurrent;
    
    @FindBy (css="h1>span:not(#result-count)")
    private List<WebElement> h1;
    
    @FindBy (css="meta[name=description]")
    private WebElement description;
    
    @FindBy (css="#map")
    private WebElement map;
    
    public int getResultsCountNumber() {
    	String count = resultsTotal.getText();
    	String[] split = count.split(",");
    	if (split.length==1)
    		return Integer.parseInt(split[0]);
    	else
    		return Integer.parseInt(split[0].concat(split[1]));
    }

    public List<WebElement> drList() {
        return searchResults;
    }

    public List<Profile> doctorResults(List<WebElement> searchResults) {
        List<Profile> doc = new ArrayList<Profile>();

        for (WebElement el : searchResults) {
            String name = el.findElement(By.cssSelector(".profile-name>a")).getText().trim();
            String url = el.findElement(By.cssSelector(".profile-name>a")).getAttribute("href");
            doc.add(new Profile(name,url));
        }

        return doc;
    }
    
    public List<String> getBreadcrumbText() {
    	List<String> text = new ArrayList<String>();
    	for (int i=0; i<breadcrumb.size(); i++) {
    		text.add(breadcrumb.get(i).getText());
    	}
    	return text;
    }
    
    public List<String> getBreadcrumbUrl() {
    	List<String> url = new ArrayList<String>();
    	for (int i=0; i<breadcrumb.size(); i++) {
    		url.add(breadcrumb.get(i).getAttribute("href"));
    	}
    	return url;
    }
    
    public String getBreadcrumbCurrentText() {
    	return breadcrumbCurrent.getText();
    }
    
    public List<String> getH1Text() {
    	List<String> text = new ArrayList<String>();
    	for (int i=0; i<h1.size(); i++) {
    		text.add(h1.get(i).getText());
    	}
    	return text;
    }
    
    public String getDescription() {
    	return description.getAttribute("content");
    }
    
    public boolean isMapEmpty() {
    	if (map.getText().equals(""))
    		return true;
    	else
    		return false;
    }
}


