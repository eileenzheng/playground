package com.vitals.pages;

import org.seleniumhq.selenium.fluent.FluentWebElement;
import static org.openqa.selenium.By.cssSelector;

public class ReviewSearchResultsPage extends BasePage {

    public FluentWebElement resultsTotal() {
        return p(cssSelector("#result-count"));
    }

    public FluentWebElement activeToggle() {
        return link(cssSelector(".toggle-provider .active"));
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
