package com.vitals.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.vitals.DriverManager;
import com.vitals.helpers.Constants;

public class PatientLinkCenterAd {
	private final WebDriver driver;
	
	public PatientLinkCenterAd () {
		driver = DriverManager.getDriver();
	}
	
	@FindBy(css=".result.sponsored.patient-link .tabs span")
	private WebElement sponsor;
	
	@FindBy(css=".result.sponsored.patient-link .avatar .pic a img")
	private WebElement photo;
	
	@FindBy(css=".result.sponsored.patient-link .logo")
	private WebElement logo;
	
	@FindBy(css=".result.sponsored.patient-link .head h4 a")
	private WebElement name;
	
	@FindBy(css=".result.sponsored.patient-link .info .specialty")
	private WebElement specialty;
	
	@FindBy(css=".result.sponsored.patient-link .info .practice address span>span")
	private List<WebElement> address;
	
	@FindBy(css=".result.sponsored.patient-link .info .practice address>span:not(.title):nth-last-of-type(3)")
	private WebElement city;
	
	@FindBy(css=".result.sponsored.patient-link .info .practice address>span:not(.title):nth-last-of-type(2)")
	private WebElement state;
	
	@FindBy(css=".result.sponsored.patient-link .info .practice address>span:not(.title):last-of-type")
	private WebElement zip;
	
	@FindBy(css=".result.sponsored.patient-link .book-online")
	private WebElement bookButton;
	
	@FindBy(css=".result.sponsored.patient-link .call-appointment strong")
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
	
	public String getAddressLine1() {
		return address.get(0).getText();
	}
	
	public String getAddressLine2() {
		if (address.size()>1)
			return address.get(1).getText();
		else
			return null;
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
	
	public boolean isLogoPresent() {
		return isElementPresent(logo);
	}
	
	public String getLogoUrl() {
		return logo.findElement(By.cssSelector("a")).getAttribute("href");
	}
	
	public boolean isBookPresent() {
		return isElementPresent(bookButton);
	}
	
	public PatientLinkBookModal clickBook() {
		bookButton.click();
		return PageFactory.initElements(driver, PatientLinkBookModal.class);
	}
	
	public boolean isPhoneNumberPresent() {
		return isElementPresent(phoneNumber);
	}
	
	public String getPhoneNumber() {
		return phoneNumber.getText();
	}
	
	public boolean isElementPresent (WebElement el) {
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		try {
			if (el.isDisplayed()) {
				driver.manage().timeouts().implicitlyWait(Constants.SELENIUM_IMPLICIT_WAIT, TimeUnit.SECONDS);
				return true;
			}
			else {
				driver.manage().timeouts().implicitlyWait(Constants.SELENIUM_IMPLICIT_WAIT, TimeUnit.SECONDS);
				return false;
			}
		}
		catch (NoSuchElementException e) {
			driver.manage().timeouts().implicitlyWait(Constants.SELENIUM_IMPLICIT_WAIT, TimeUnit.SECONDS);
			return false;
		}
	}
}
