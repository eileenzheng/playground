package com.vitals.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.vitals.DriverManager;

public class PatientLinkCenterAd {
	private WebDriver driver;
	private WebDriverWait wait;
	
	public PatientLinkCenterAd () {
		driver = DriverManager.getDriver();
		this.wait = new WebDriverWait(driver, 5, 300);
	}
	
	@FindBy(css=".result.sponsored.patient-link .tab>span")
	private WebElement sponsor;
	
	@FindBy(css=".result.sponsored.patient-link .avatar>.pic>a>img")
	private WebElement photo;
	
	@FindBy(css=".result.sponsored.patient-link .head>h4>a")
	private WebElement name;
	
	@FindBy(css=".result.sponsored.patient-link .info .specialty")
	private WebElement specialty;
	
	@FindBy(css=".result.sponsored.patient-link .info .practice>address>[itemprop=streetAddress]>span")
	private WebElement address;
	
	@FindBy(css=".result.sponsored.patient-link .info .practice>address>[itemprop=addressLocality]")
	private WebElement city;
	
	@FindBy(css=".result.sponsored.patient-link .info .practice>address>[itemprop=addressRegion]")
	private WebElement state;
	
	@FindBy(css=".result.sponsored.patient-link .info .practice>address>[itemprop=postalCode]")
	private WebElement zip;
	
	@FindBy(css=".result.sponsored.patient-link .patientlink-buttons .pl-book-online-button")
	private WebElement bookButton;
	
	@FindBy(css=".result.sponsored.patient-link .patientlink-buttons .pl-call-button")
	private WebElement callButton;
	
	@FindBy(css=".qtip-focus .pl-phone")
	private WebElement phoneNumber;
	
	public String getName() {
		return name.getText();
	}
	
	public String getSponsor() {
		return sponsor.getText();
	}
	
	public String getSpecialty() {
		return specialty.getText();
	}
	
	public String getAddress() {
		return address.getText();
	}
	
	public String getCity() {
		return city.getText();
	}
	
	public String getState() {
		return state.getText();
	}
	
	public String getZip() {
		return zip.getText();
	}
	
	public boolean isBookPresent() {
		return isElementPresent(bookButton);
	}
	
	public PatientLinkBookModal clickBook() {
		bookButton.click();
		return PageFactory.initElements(driver, PatientLinkBookModal.class);
	}
	
	public boolean isCallPresent() {
		return isElementPresent(callButton);
	}
	
	public PatientLinkCenterAd clickCall() {
		callButton.click();
		wait.until(ExpectedConditions.visibilityOf(phoneNumber));
		return this;
	}
	
	public String getPhoneNumber() {
		return phoneNumber.getText();
	}
	
	public static boolean isElementPresent (WebElement el) {
		try {
			if (el.isDisplayed())
				return true;
			else
				return false;
		}
		catch (NoSuchElementException e) {
			return false;
		}
	}
}
