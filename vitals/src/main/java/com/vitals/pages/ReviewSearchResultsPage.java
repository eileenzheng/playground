package com.vitals.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReviewSearchResultsPage {
	
	@FindBy(css="#result-count")
    private WebElement resultsTotal;
	
	@FindBy(css=".toggle-provider .active")
	private WebElement activeToggle;

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
    
    public boolean isToggleProvider() {
    	return (activeToggle.getText().equals("Doctors"));
    }
    
    public boolean isToggleFacilities() {
    	return (activeToggle.getText().equals("Facilities"));
    }
}
