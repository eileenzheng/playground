package com.vitals.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReviewWritePage {
    
	@FindBy(css="#post-review h1")
    private WebElement headlineText;
	
	public boolean isDoctorReview() {
		return (headlineText.getText().equals("Tell us about your experience with this doctor."));
	}
	
	public boolean isFacilityReview() {
		return (headlineText.getText().contains("Tell us about your experience at"));
	}
}
