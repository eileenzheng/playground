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
	
	@FindBy(css=".cmt_inner_content")
	private List<WebElement> content;
	
	public List<WebElement> getName() {
		return name;
	}
	
	public List<WebElement> getAddress() {
		return parseAddress(content, ".address-well");
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
