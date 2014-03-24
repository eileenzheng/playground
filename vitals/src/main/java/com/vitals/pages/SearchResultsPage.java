package com.vitals.pages;

import com.vitals.helpers.Profile;
import com.vitals.pages.patientlink.PatientLinkCenterAd;
import org.openqa.selenium.WebElement;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import java.util.ArrayList;
import java.util.List;
import static org.openqa.selenium.By.cssSelector;

public class SearchResultsPage extends BasePage {

    HeaderModule headerModule;
    SearchResultsRefinement refinement;
    PatientLinkCenterAd centerAd;

    public SearchResultsPage() {
        headerModule = new HeaderModule();
        refinement = new SearchResultsRefinement();
        centerAd = new PatientLinkCenterAd();
    }

    public HeaderModule headerModule() {
        return headerModule;
    }

    public PatientLinkCenterAd centerAd() {
        return centerAd;
    }

    public SearchResultsRefinement refinement() {
        return refinement;
    }

    public FluentWebElement resultsTotal() {
        return span(cssSelector("h1>span:first-child"));
    }

    public FluentWebElements searchResults() {
        return divs(cssSelector("#results-content .v-pwl.listing"));
    }

    public FluentWebElements breadcrumb() {
        return links(cssSelector(".breadcrumb .trail"));
    }

    public FluentWebElement breadcrumbCurrent() {
        return li(cssSelector(".breadcrumb li:last-child"));
    }

    public FluentWebElements searchSentence() {
        return spans(cssSelector("h1>span:not(#result-count)"));
    }

    public WebElement description() {
        return webDriver().findElement(cssSelector("meta[name=description]"));
    }

    public FluentWebElement map() {
        return div(cssSelector("#map"));
    }

    public int getResultsCountNumber() {
    	String count = resultsTotal().getText().toString();
    	String[] split = count.split(",");
    	if (split.length==1)
    		return Integer.parseInt(split[0]);
    	else
    		return Integer.parseInt(split[0].concat(split[1]));
    }

    public FluentWebElements drList() {
        return searchResults();
    }

    public List<Profile> doctorResults(FluentWebElements searchResults) {
        List<Profile> doc = new ArrayList<Profile>();

        for (FluentWebElement el : searchResults()) {
            String name = el.link(cssSelector(".profile-name>a")).getText().toString().trim();
            String url = el.link(cssSelector(".profile-name>a")).getAttribute("href").toString();
            doc.add(new Profile(name,url));
        }

        return doc;
    }
    
    public List<String> getBreadcrumbText() {
    	List<String> text = new ArrayList<String>();
    	for (int i=0; i<breadcrumb().size(); i++) {
    		text.add(breadcrumb().get(i).getText().toString());
    	}
    	return text;
    }
    
    public List<String> getBreadcrumbUrl() {
    	List<String> url = new ArrayList<String>();
    	for (int i=0; i<breadcrumb().size(); i++) {
    		url.add(breadcrumb().get(i).getAttribute("href").toString());
    	}
    	return url;
    }
    
    public List<String> getH1Text() {
    	List<String> text = new ArrayList<String>();
    	for (int i=0; i<searchSentence().size(); i++) {
    		text.add(searchSentence().get(i).getText().toString());
    	}
    	return text;
    }
    
    public String getDescription() {
    	return description().getAttribute("content").toString();
    }
    
    public boolean isMapEmpty() {
        return map().getText().toString().equals("");
    }
}


