package com.vitals.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PatientLinkRrAd {
	private WebDriver driver;
	private WebDriverWait wait;
	
	public PatientLinkRrAd(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 5, 300);
	}
	
	@FindBy(css=".related.block.border .row>img")
	private List<WebElement> photo;
	
	@FindBy(css=".related.block.border .row .title>a")
	private List<WebElement> name;
	
	@FindBy(css=".related.block.border .row .specialty")
	private List<WebElement> specialty;
	
	@FindBy(css=".related.block.border .row .details>address>span>span")
	private List<WebElement> address;
	
	@FindBy(css=".related.block.border .row .details>address>span:nth-child(2)")
	private List<WebElement> city;
	
	@FindBy(css=".related.block.border .row .details>address>span:nth-child(3)")
	private List<WebElement> state;
	
	@FindBy(css=".related.block.border .row .details>address>span:nth-child(4)")
	private List<WebElement> zip;
	
	@FindBy(css=".related.block.border .patientlink-buttons")
	private List<WebElement> buttonsDiv;
	
	@FindBy(css=".qtip-focus .pl-phone")
	private WebElement phoneNumber;
	
	public String getName (int i) {
		return name.get(i).getText();
	}
	
	public String getSpecialty (int i) {
		return specialty.get(i).getText();
	}
	
	public String getAddress (int i) {
		return address.get(i).getText();
	}
	
	public String getCity (int i) {
		return city.get(i).getText();
	}
	
	public String getState (int i) {
		return state.get(i).getText();
	}
	
	public String getZip (int i) {
		return zip.get(i).getText();
	}
	
	public boolean isBookPresent (int i) {
		try {
			if (buttonsDiv.size()<=i)
				return false;
			if (isElementPresent (buttonsDiv.get(i).findElement(By.cssSelector(".pl-book-online-button"))))
				return true;
			else
				return false;
		}
		catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public PatientLinkBookModal clickBook (int i) {
		buttonsDiv.get(i).findElement(By.cssSelector(".pl-book-online-button")).click();
		return PageFactory.initElements(driver, PatientLinkBookModal.class);
	}
	
	public boolean isCallPresent (int i) {
		try {
			if (buttonsDiv.size()<=i)
				return false;
			if (isElementPresent (buttonsDiv.get(i).findElement(By.cssSelector(".pl-call-button"))))
				return true;
			else
				return false;
		}
		catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public PatientLinkRrAd clickCall (int i) {
		buttonsDiv.get(i).findElement(By.cssSelector(".pl-call-button")).click();
		wait.until(ExpectedConditions.visibilityOf(phoneNumber));
		return this;
	}
	
	public boolean isLogoPresent (int i) {
		try {
			if (buttonsDiv.size()<=i)
				return false;
			if (isElementPresent (buttonsDiv.get(i).findElement(By.cssSelector("img"))))
				return true;
			else
				return false;
		}
		catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public String getPhoneNumber() {
		return phoneNumber.getText();
	}
	
	public int getSize() {
		return name.size();
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
