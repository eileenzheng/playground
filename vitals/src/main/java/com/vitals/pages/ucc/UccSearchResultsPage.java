package com.vitals.pages.ucc;

import java.util.ArrayList;
import java.util.List;
import com.vitals.pages.BasePage;
import com.vitals.pages.HeaderModule;
import com.vitals.pages.patientlink.PatientLinkCenterAd;
import com.vitals.helpers.Ucc;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import static org.openqa.selenium.By.cssSelector;

public class UccSearchResultsPage extends BasePage {

    PatientLinkCenterAd centerAd;
    UccSearchResultsRefinement refinement;
    HeaderModule headerModule;

    public UccSearchResultsPage() {
        centerAd = new PatientLinkCenterAd();
        refinement = new UccSearchResultsRefinement();
        headerModule = new HeaderModule();
    }

    public PatientLinkCenterAd centerAd() {
        return centerAd;
    }

    public UccSearchResultsRefinement refinement() {
        return refinement;
    }

    public HeaderModule headerModule() {
        return headerModule;
    }

    public FluentWebElement resultsTotal() {
        return span(cssSelector("h1>span:first-child"));
    }

    public FluentWebElements searchResults() {
        return divs(cssSelector(".listing>.listing-details"));
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

    public FluentWebElements uccList() {
        return searchResults();
    }

    public List<Ucc> uccResults() {
        List<Ucc> ucc = new ArrayList<Ucc>();

        for (FluentWebElement el : uccList()) {
            String name = el.getWebElement().findElement(cssSelector(".profile-name>a")).getText().trim();
            String url = el.getWebElement().findElement(cssSelector(".profile-name>a")).getAttribute("href");
            String address = el.getWebElement().findElement(cssSelector("span[itemprop=streetAddress]")).getText();
            String city = el.getWebElement().findElement(cssSelector("span[itemprop=addressLocality]")).getText();
            String state = el.getWebElement().findElement(cssSelector("span[itemprop=addressRegion]")).getText();
            String zip = el.getWebElement().findElement(cssSelector("span[itemprop=postalCode]")).getText();
            ucc.add(new Ucc(name,url,address,city,state,zip));
        }

        return ucc;
    }

    public boolean isMapEmpty() {
        return map().getText().toString().equals("");
    }
}
