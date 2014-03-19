package com.vitals.pages.patientlink;

import com.vitalsqa.listener.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import com.vitals.helpers.Constants;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class PatientLinkAd {

   private WebDriver driver;
   
   public PatientLinkAd () {
      driver = DriverManager.getDriver();
   }
   
   private List<WebElement> name;
   private List<WebElement> address1;
   private List<WebElement> address2;
   private List<WebElement> city;
   private List<WebElement> state;
   private List<WebElement> zip;
   private List<WebElement> specialty;
   private List<WebElement> bookOnline;
   private List<WebElement> phoneNumber;
   private List<WebElement> logo;
   
   public List<WebElement> getName() {
      return name;
   }

   public List<WebElement> getSpecialty() {
	  return specialty;
   }
   
   public List<WebElement> getAddressLine1() {
      return address1;
   }
   
   public List<WebElement> getAddressLine2() {
	  return address2;
   }
   
   public List<WebElement> getCity() {
      return city;
   }
   
   public List<WebElement> getState() {
      return state;
   }
   
   public List<WebElement> getZip() {
      return zip;
   }
   
   public List<WebElement> getBookOnline() {
      return bookOnline;
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
   
   public String getSpecialty(int i) {
	   return getSpecialty().get(i).getText();
   }
   
   public String getAddressLine1(int i) {
      return getAddressLine1().get(i).getText();
   }
   
   public String getAddressLine2(int i) {
		if (getAddressLine1().get(i).getText().equals(getAddressLine2().get(i).getText())) {
			return null; // no address line 2
		}
		else {
			return getAddressLine2().get(i).getText();
		}
   }
   
   public String getCity(int i) {
      return getCity().get(i).getText();
   }
   
   public String getState(int i) {
      return getState().get(i).getText();
   }
   
   public String getZip(int i) {
	   return getZip().get(i).getText();
   }
   
   public boolean isBookPresent(int i) {
      return (getBookOnline().get(i)!=null);
   }
   
   public ModalEmail clickBook(int i) {
      getBookOnline().get(i).click();
      return PageFactory.initElements(driver, ModalEmail.class);
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
