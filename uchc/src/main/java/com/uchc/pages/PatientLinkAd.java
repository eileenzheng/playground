package com.uchc.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import com.uchc.DriverManager;
import java.util.List;

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
      return getPhoneNumber().get(i).getText();
   }
   
   public boolean isLogoPresent(int i) {
	   return (getLogo().get(i)!=null);
   }
}
