package com.vitals.pages;

import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import static org.openqa.selenium.By.cssSelector;

public class ReviewSearchResultsPage extends BasePage {

    public FluentWebElement resultsTotal() {
        return p(cssSelector("#result-count"));
    }

    public FluentWebElement activeToggle() {
        return link(cssSelector(".toggle-provider .active"));
    }

    public FluentWebElement searchBox() {
        return input(cssSelector("#search-review .search-main"));
    }

    public FluentWebElement reviewResults() {
        return div(cssSelector(".listing"));
    }

    public FluentWebElements listingNames() {
        return links(cssSelector(".listing h4>a:last-child"));
    }
    
    public FluentWebElements listingCities() {
        return spans(cssSelector(".listing span[itemprop=addressLocality]"));
    }

    public FluentWebElements listingStates() {
        return spans(cssSelector(".listing span[itemprop=addressRegion]"));
    }
    
    public int getResultsCountNumber() {
    	String[] split = resultsTotal().getText().toString().split(" ");
    	String count = split[split.length-2];
    	split = count.split(",");
    	if (split.length==1)
    		return Integer.parseInt(split[0]);
    	else
    		return Integer.parseInt(split[0].concat(split[1]));
    }
}
