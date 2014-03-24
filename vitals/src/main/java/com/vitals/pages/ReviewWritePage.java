package com.vitals.pages;

import org.seleniumhq.selenium.fluent.FluentWebElement;
import static org.openqa.selenium.By.cssSelector;

public class ReviewWritePage extends BasePage {

    public FluentWebElement headline() {
        return h1(cssSelector("#post-review h1"));
    }
	
	public boolean isDoctorReview() {
		return (headline().getText().toString().equals("Tell us about your experience with this doctor."));
	}
	
	public boolean isFacilityReview() {
		return (headline().getText().toString().contains("Tell us about your experience at"));
	}
}
