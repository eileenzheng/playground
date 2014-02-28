package com.uchc.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class PatientLinkRrAd extends PatientLinkAd {
	
	public PatientLinkRrAd() {
		super();
	}

	@FindBy(css = ".bluebox-featured-rr .featured_cmt .provider-name>a")
	private List<WebElement> name;

	@FindBy(css = ".bluebox-featured-rr .featured_cmt")
	private List<WebElement> content;

	public List<WebElement> getName() {
		return name;
	}

	public List<WebElement> getAddress() {
		return parseAddress(content, ".feature-address");
	}

	public List<WebElement> getMakeAppt() {
		return parseElement(content, ".book-online>a");
	}

	public List<WebElement> getPhoneNumber() {
		return parseElement(content, ".call-appointment .tel");
	}

	public List<WebElement> getLogo() {
		return parseElement(content, ".logo");
	}
}
