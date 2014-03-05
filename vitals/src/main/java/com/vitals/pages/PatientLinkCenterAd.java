package com.vitals.pages;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PatientLinkCenterAd extends PatientLinkAd {
	
	public PatientLinkCenterAd () {
		super();
	}
	
	@FindBy(css=".listing-featured .profile-name>a")
	private List<WebElement> name;
	
	@FindBy(css=".listing-featured .location-distance")
	private List<WebElement> specialty;
	
	@FindBy(css=".listing-featured span[itemprop=streetAddress]>span:first-child")
	private List<WebElement> address1;
	
	@FindBy(css=".listing-featured span[itemprop=streetAddress]>span:last-child")
	private List<WebElement> address2;
	
	@FindBy(css=".listing-featured span[itemprop=addressLocality]")
	private List<WebElement> city;
	
	@FindBy(css=".listing-featured span[itemprop=addressRegion]")
	private List<WebElement> state;
	
	@FindBy(css=".listing-featured span[itemprop=postalCode]")
	private List<WebElement> zip;
	
	@FindBy(css=".listing-featured")
	private List<WebElement> block;
	
	@FindBy(css=".result.sponsored.patient-link .book-online")
	private WebElement bookButton;
	
	@FindBy(css=".result.sponsored.patient-link .call-appointment strong")
	private WebElement phoneNumber;
	
	public List<WebElement> getName() {
		return name;
	}
	
	public List<WebElement> getSpecialty () {
		return specialty;
	}
	
	public List<WebElement> getAddressLine1() {
		return address1;
	}
	
	public List<WebElement> getAddressLine2() {
		return address2;
	}
	
	public List<WebElement> getCity () {
		return city;
	}
	
	public List<WebElement> getState () {
		return state;
	}
	
	public List<WebElement> getZip () {
		return zip;
	}
	
	public List<WebElement> getBookOnline() {
		return parseElement(block, ".book-button>a");
	}
	
	public List<WebElement> getPhoneNumber() {
		return parseElement(block, ".appointment-info>strong");
	}
	
	public List<WebElement> getLogo() {
		return parseElement(block, ".sponsorship img");
	}
}
