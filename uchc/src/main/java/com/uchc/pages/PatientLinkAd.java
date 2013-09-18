package com.uchc.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public abstract class PatientLinkAd {

	private WebDriver driver;
	
	public PatientLinkAd (WebDriver driver) {
		this.driver = driver;
	}
	
	private List<WebElement> name;
	private List<WebElement> address;
	private List<WebElement> makeAppt;
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
	
	public int getSize() {
		return getName().size();
	}
	
	public String getName(int i) {
		return getName().get(i).getText();
	}
	
	public String getAddress(int i) {
		return getAddress().get(i*2).getText();
	}
	
	public String getCity(int i) {
		String line2 = getAddress().get(i*2+1).getText();
		return line2.substring(0, line2.indexOf(","));
	}
	
	public String getState(int i) {
		String line2 = getAddress().get(i*2+1).getText();
		return line2.substring(line2.indexOf(",")+2).split(" ")[0];
	}
	
	public String getZip(int i) {
		String line2 = getAddress().get(i*2+1).getText();
		return line2.substring(line2.indexOf(",")+2).split(" ")[1];
	}
	
	public boolean isBookPresent(int i) {
		return getMakeAppt().get(i).getText().equals("Make Appointment");
	}
	
	public PatientLinkBookForm clickBook(int i) {
		getMakeAppt().get(i).click();
		return PageFactory.initElements(driver, PatientLinkBookForm.class);
	}
	
	public String getApptUrl(int i) {
		return getMakeAppt().get(i).getAttribute("href");
	}
	
	public boolean isPhonePresent(int i) {
		if (getPhoneNumber().size()<= i)
			return false;
		else
			return isElementPresent(getPhoneNumber().get(i));
	}
	
	public String getPhoneNumber(int i) {
		return getPhoneNumber().get(i).getText();
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
