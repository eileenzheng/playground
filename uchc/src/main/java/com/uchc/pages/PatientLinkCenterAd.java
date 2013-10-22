package com.uchc.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PatientLinkCenterAd extends PatientLinkAd {

	public PatientLinkCenterAd () {
		super();
	}
	
	@FindBy(css=".cmt_inner_content .provider-name-cw>a")
	private List<WebElement> name;
	
	@FindBy(css=".cmt_inner_content .address_well")
	private List<WebElement> address;
	
	@FindBy(css=".cmt_inner_content .details_well>div:nth-child(2)>a:first-child")
	private List<WebElement> makeAppt;
	
	@FindBy(css=".cmt_inner_content .details_well .details_phone")
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
