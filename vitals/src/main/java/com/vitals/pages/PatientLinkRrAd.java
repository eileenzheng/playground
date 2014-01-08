package com.vitals.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.vitals.DriverManager;

import java.util.List;

public class PatientLinkRrAd {
	private final WebDriver driver;
	
	public PatientLinkRrAd() {
		driver = DriverManager.getDriver();
	}
	
	@FindBy(css=".related.block.border .rr-row img")
	private List<WebElement> photo;
	
	@FindBy(css=".related.block.border .rr-row .title>a")
	private List<WebElement> name;
	
	@FindBy(css=".related.block.border .rr-row .specialty")
	private List<WebElement> specialty;
	
	@FindBy(css=".related.block.border .rr-row .details address>span:nth-last-child(3)")
	private List<WebElement> city;
	
	@FindBy(css=".related.block.border .rr-row .details address>span:nth-last-child(2)")
	private List<WebElement> state;
	
	@FindBy(css=".related.block.border .rr-row .details>address>span:last-of-type")
	private List<WebElement> zip;
	
	@FindBy(css=".related.block.border .row2")
	private List<WebElement> bottomRow;
	
	@FindBy(css=".related.block.border .rr-row")
	private List<WebElement> topRow;
	
	public String getName (int i) {
		return name.get(i).getText();
	}
	
	public String getSpecialty (int i) {
		return specialty.get(i).getText();
	}
	
	public String getAddressLine1 (int i) {
		List<WebElement> address = topRow.get(i).findElements(By.cssSelector(".details>address>span>span"));
		return address.get(0).getText();
	}
	
	public String getAddressLine2 (int i) {
		List<WebElement> address = topRow.get(i).findElements(By.cssSelector(".details>address>span>span"));
		if (address.size()>1)
			return address.get(1).getText();
		else
			return null;
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
	
	public boolean isLogoPresent (int i) {
		try {
			return isElementPresent(bottomRow.get(i).findElement(By.cssSelector(".logo")));
		}
		catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public String getLogoUrl (int i) {
		return (bottomRow.get(i).findElement(By.cssSelector(".logo a")).getAttribute("href"));
	}
	
	public boolean isBookPresent (int i) {
		try {
			if (isElementPresent (bottomRow.get(i).findElement(By.cssSelector(".book-online"))))
				return true;
			else
				return false;
		}
		catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public PatientLinkBookModal clickBook (int i) {
		bottomRow.get(i).findElement(By.cssSelector(".book-online")).click();
		return PageFactory.initElements(driver, PatientLinkBookModal.class);
	}
	
	public boolean isPhoneNumbePresent (int i) {
		try {
			if (isElementPresent (bottomRow.get(i).findElement(By.cssSelector(".call-appointment strong"))))
					return true;
			else
				return false;
		}
		catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public String getPhoneNumber (int i) {
		return bottomRow.get(i).findElement(By.cssSelector(".call-appointment strong")).getText();
	}
	
	public int getSize() {
		return name.size();
	}
	
	public boolean isElementPresent (WebElement el) {
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
