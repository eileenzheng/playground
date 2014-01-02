package com.uchc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.uchc.DriverManager;
import com.uchc.helpers.Constants;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PatientLinkCenterAd extends PatientLinkAd {

	private WebDriver driver;
	
	public PatientLinkCenterAd () {
		super();
		driver = DriverManager.getDriver();
	}
	
	@FindBy(css=".cmt_inner_content .provider-name-cw>a")
	private List<WebElement> name;
	
	@FindBy(css=".cmt_inner_content")
	private List<WebElement> content;
	
	public List<WebElement> getName() {
		return name;
	}
	
	public List<WebElement> getAddress() {
		List<WebElement> address = new ArrayList<WebElement>();
		
		for (int i=0; i<content.size(); i++) {
			List<WebElement> addr = content.get(i).findElements(By.cssSelector(".address-well"));
			if (addr.size()==2) {
				address.add(addr.get(0)); // address line 1
				address.add(null); // address line 2
				address.add(addr.get(1)); // city/state/zip
			}
			else if (addr.size()==4) {
				address.add(addr.get(1)); // address line 1
				address.add(addr.get(2)); // address line 2
				address.add(addr.get(3)); // city/state/zip
			}
			else if (addr.size()==3) {
				if (addr.get(0).getText().matches(".*\\d.*")) { // most likely first line is address 1
					address.add(addr.get(0)); // address line 1
					address.add(addr.get(1)); // address line 2
					
				}
				else { // most likely first line is featured practice name
					address.add(addr.get(1)); // address line 1
					address.add(null); // address line 2
				}
				address.add(addr.get(2)); // city/state/zip
			}
		}
		return address;
	}
	
	public List<WebElement> getMakeAppt() {
		List <WebElement> bookButton = new ArrayList<WebElement>();
		
		for (int i=0; i<content.size(); i++) {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			try {
				WebElement book = content.get(i).findElement(By.cssSelector(".book-online>a"));
				bookButton.add(book);
			}
			catch (NoSuchElementException e) {
				bookButton.add(null);
			}
			driver.manage().timeouts().implicitlyWait(Constants.SELENIUM_IMPLICIT_WAIT, TimeUnit.SECONDS);
		}
		
		return bookButton;
	}
	
	public List<WebElement> getPhoneNumber() {
		List <WebElement> phoneNumber = new ArrayList<WebElement>();
		
		for (int i=0; i<content.size(); i++) {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			try {
				WebElement phone = content.get(i).findElement(By.cssSelector(".call-appointment .tel"));
				phoneNumber.add(phone);
			}
			catch (NoSuchElementException e) {
				phoneNumber.add(null);
			}
			driver.manage().timeouts().implicitlyWait(Constants.SELENIUM_IMPLICIT_WAIT, TimeUnit.SECONDS);
		}
		
		return phoneNumber;
	}
	
	public List<WebElement> getLogo() {
		List<WebElement> logo = new ArrayList<WebElement>();

		for (int i = 0; i < content.size(); i++) {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			try {
				WebElement image = content.get(i).findElement(
						By.cssSelector(".logo"));
				logo.add(image);
			} catch (NoSuchElementException e) {
				logo.add(null);
			}
			driver.manage().timeouts().implicitlyWait(Constants.SELENIUM_IMPLICIT_WAIT, TimeUnit.SECONDS);
		}

		return logo;
	}
}
