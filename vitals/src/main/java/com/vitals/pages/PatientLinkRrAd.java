package com.vitals.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class PatientLinkRrAd extends PatientLinkAd {
	
	public PatientLinkRrAd() {
		super();
	}
	
	@FindBy(css=".related.block.border .rr-row .title>a")
	private List<WebElement> name;
	
	@FindBy(css=".related.block.border .rr-row .specialty")
	private List<WebElement> specialty;
	
	@FindBy(css=".related.block.border .rr-row .details>address>span>span:first-child")
	private List<WebElement> address1;
	
	@FindBy(css=".related.block.border .rr-row .details>address>span>span:last-child")
	private List<WebElement> address2;
	
	@FindBy(css=".related.block.border .rr-row .details address>span:nth-last-child(3)")
	private List<WebElement> city;
	
	@FindBy(css=".related.block.border .rr-row .details address>span:nth-last-child(2)")
	private List<WebElement> state;
	
	@FindBy(css=".related.block.border .rr-row .details>address>span:last-of-type")
	private List<WebElement> zip;
	
	@FindBy(css=".related.block.border .row2")
	private List<WebElement> block;
	
	public List<WebElement> getName () {
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
		return parseElement(block, ".modal-call");
	}
	
	public List<WebElement> getPhoneNumber() {
		return parseElement(block, ".call-appointment strong");
	}
	
	public List<WebElement> getLogo() {
		return parseElement(block, ".logo>img");
	}
}
