package com.uchc.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class PatientLinkRrAd extends PatientLinkAd {
	
	public PatientLinkRrAd () {
		super();
	}
	
	@FindBy(css=".bluebox-featured-rr .featured_cmt .provider_name>a")
	private List<WebElement> name;
	
	@FindBy(css=".bluebox-featured-rr .featured_cmt .feature_address:not(.orange)")
	private List<WebElement> address;
	
	@FindBy(css=".bluebox-featured-rr .featured_cmt .details>a:last-child")
	private List<WebElement> makeAppt;
	
	@FindBy(css=".bluebox-featured-rr .featured_cmt .feature_address.orange")
	private List<WebElement> phoneNumber;
	
	public List<WebElement> getName() {
		return name;
	}
	
	public List<WebElement> getAddress() {
		return address;
	}
	
	public List<WebElement> getMakeAppt() {
		return makeAppt;
	}
	
	public List<WebElement> getPhoneNumber() {
		return phoneNumber;
	}
}
