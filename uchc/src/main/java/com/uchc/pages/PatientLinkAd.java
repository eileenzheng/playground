package com.uchc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import com.uchc.DriverManager;
import com.uchc.helpers.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class PatientLinkAd {

   private WebDriver driver;
   
   public PatientLinkAd () {
      driver = DriverManager.getDriver();
   }
   
   private List<WebElement> name;
   private List<WebElement> address;
   private List<WebElement> makeAppt;
   private List<WebElement> phoneNumber;
   private List<WebElement> logo;
   
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
   
   public List<WebElement> getLogo() {
	   return logo;
   }
   
   public int getSize() {
      return getName().size();
   }
   
   public String getName(int i) {
      return getName().get(i).getText();
   }
   
   public String getAddressLine1(int i) {
      return getAddress().get(i*3).getText();
   }
   
   public String getAddressLine2(int i) {
	   if (getAddress().get(i*3+1)!=null)
		   return getAddress().get(i*3+1).getText();
	   else
		   return null;
   }
   
   public String getCity(int i) {
      String line2 = getAddress().get(i*3+2).getText();
      return line2.substring(0, line2.indexOf(","));
   }
   
   public String getState(int i) {
      String line2 = getAddress().get(i*3+2).getText();
      return line2.substring(line2.indexOf(",")+2).split(" ")[0];
   }
   
   public String getZip(int i) {
      String line2 = getAddress().get(i*3+2).getText();
      return line2.substring(line2.indexOf(",")+2).split(" ")[1];
   }
   
   public boolean isBookPresent(int i) {
      return (getMakeAppt().get(i)!=null);
   }
   
   public PatientLinkBookForm clickBook(int i) {
      getMakeAppt().get(i).click();
      return PageFactory.initElements(driver, PatientLinkBookForm.class);
   }
   
   public String getApptUrl(int i) {
      return getMakeAppt().get(i).getAttribute("href");
   }
   
   public boolean isPhonePresent(int i) {
      return (getPhoneNumber().get(i)!=null);
   }
   
   public String getPhoneNumber(int i) {
	   if (getPhoneNumber().get(i) != null)
		   return getPhoneNumber().get(i).getText();
	   else
		   return null;
   }
   
   public boolean isLogoPresent(int i) {
	   return (getLogo().get(i)!=null);
   }
   
   public List<WebElement> parseAddress (List<WebElement> content, String css) {
	   
	   List<WebElement> address = new ArrayList<WebElement>();

		for (int i = 0; i < content.size(); i++) {
			List<WebElement> addr = content.get(i).findElements(
					By.cssSelector(css));
			if (addr.size() == 2) {
				address.add(addr.get(0)); // address line 1
				address.add(null); // address line 2
				address.add(addr.get(1)); // city/state/zip
			} else if (addr.size() == 4) {
				address.add(addr.get(1)); // address line 1
				address.add(addr.get(2)); // address line 2
				address.add(addr.get(3)); // city/state/zip
			} else if (addr.size() == 3) {
				if (addr.get(0).getText().matches(".*\\d.*")) { // most likely
																// first line is
																// address 1
					address.add(addr.get(0)); // address line 1
					address.add(addr.get(1)); // address line 2

				} else { // most likely first line is featured practice name
					address.add(addr.get(1)); // address line 1
					address.add(null); // address line 2
				}
				address.add(addr.get(2)); // city/state/zip
			}
		}
		
		return address;
   }
   
	public List<WebElement> parseElement(List<WebElement> content, String css) {
		
		List<WebElement> els = new ArrayList<WebElement>();

		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		for (int i = 0; i < content.size(); i++) {
			try {
				WebElement el = content.get(i).findElement(
						By.cssSelector(css));
				els.add(el);
			} catch (NoSuchElementException e) {
				els.add(null);
			}
		}
		driver.manage().timeouts().implicitlyWait(Constants.SELENIUM_IMPLICIT_WAIT, TimeUnit.SECONDS);

		return els;
	}
}
