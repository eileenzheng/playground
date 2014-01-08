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
    public final PatientLinkRrAd rrAd;

    public SearchResultsPage() {
    	driver = DriverManager.getDriver();
        header = PageFactory.initElements(driver, HeaderPage.class);
        footer = PageFactory.initElements(driver, FooterPage.class);
        refinement = PageFactory.initElements(driver, SearchResultsRefinement.class);
        centerAd = PageFactory.initElements(driver, PatientLinkCenterAd.class);
        rrAd = PageFactory.initElements(driver, PatientLinkRrAd.class);
    }

    @FindBy(css="#leaderboard_top")
    private WebElement topAdBox;

    @FindBy (css=".advert.rectangle.cbox")
    private WebElement rightTopAdBox;

    @FindBy (css="#result-count")
    private WebElement resultsTotal;

    @FindBy (css="#sort")
    private WebElement sortByDropdown;

    @FindBy (css="#results>.result")
    private List<WebElement> searchResults;

    @FindBy (css=".block.patientguide")
    private WebElement rightPatientGuideBlock;

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

    public List<WebElement> drList() {
        return searchResults;
    }

    public List<Profile> doctorResults(List<WebElement> searchResults) {
        List<Profile> doc = new ArrayList<Profile>();

        for (WebElement el : searchResults) {
            String name = el.findElement(By.cssSelector(".details>.head>h4>a")).getText().trim();
            String url = el.findElement(By.cssSelector(".details>.head>h4>a")).getAttribute("href");
            doc.add(new Profile(name,url));
        }

        return doc;
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

}


