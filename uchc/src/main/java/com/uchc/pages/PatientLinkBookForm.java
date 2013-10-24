package com.uchc.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.uchc.DriverManager;

public class PatientLinkBookForm {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	public PatientLinkBookForm () {
		driver = DriverManager.getDriver();
		wait = new WebDriverWait(driver, 5, 300);
	}
	
	@FindBy(css="#contact input[name=fname]")
	private WebElement fname;
	
	@FindBy(css="#contact input[name=lname]")
	private WebElement lname;
	
	@FindBy(css="#contact input[name=phone]")
	private WebElement phone;
	
	@FindBy(css="#contact input[name=email]")
	private WebElement email;
	
	@FindBy(css="#contact input[value=Any]")
	private WebElement radioAny;
	
	@FindBy(css="#contact input[value=AM]")
	private WebElement radioAm;
	
	@FindBy(css="#contact input[value=Midday]")
	private WebElement radioMidday;
	
	@FindBy(css="#contact input[value=Afternoon]")
	private WebElement radioAfternoon;
	
	@FindBy(css="#contact input[value=PM]")
	private WebElement radioPm;
	
	@FindBy(css="#contact select")
	private WebElement dropDownWhen;
	
	@FindBy(css="#contact input[name=insurance]")
	private WebElement insurance;
	
	@FindBy(css="#contact input[value=Submit]")
	private WebElement submitButton;
	
	@FindBy(css="div>span#error")
	private WebElement alert;
	
	public PatientLinkBookForm typeFirstName(String text) {
		fname.clear();
		fname.sendKeys(text);
		return this;
	}
	
	public PatientLinkBookForm typeLastName(String text) {
		lname.clear();
		lname.sendKeys(text);
		return this;
	}
	
	public PatientLinkBookForm typePhone(String text) {
		phone.clear();
		phone.sendKeys(text);
		return this;
	}
	
	public PatientLinkBookForm typeEmail(String text) {
		email.clear();
		email.sendKeys(text);
		return this;
	}
	
	public PatientLinkBookForm selectRadioAny() {
		radioAny.click();
		return this;
	}
	
	public PatientLinkBookForm selectRadioAm() {
		radioAm.click();
		return this;
	}
	
	public PatientLinkBookForm selectRadioMidday() {
		radioMidday.click();
		return this;
	}
	
	public PatientLinkBookForm selectRadioAfternoon() {
		radioAfternoon.click();
		return this;
	}
	
	public PatientLinkBookForm selectRadioPm() {
		radioPm.click();
		return this;
	}
	
	public PatientLinkBookForm openDropWhen() {
		dropDownWhen.click();
		return this;
	}
	
	public PatientLinkBookForm selectDropAsap() {
		dropDownWhen.sendKeys("ASAP");
		return this;
	}
	
	public PatientLinkBookForm selectDrop1Week() {
		dropDownWhen.sendKeys("1 week");
		return this;
	}
	
	public PatientLinkBookForm selectDrop2Weeks() {
		dropDownWhen.sendKeys("2 weeks");
		return this;
	}
	
	public PatientLinkBookForm selectDrop1Month() {
		dropDownWhen.sendKeys("1 month");
		return this;
	}
	
	public PatientLinkBookForm typeInsurance(String text) {
		insurance.clear();
		insurance.sendKeys(text);
		return this;
	}
	
	public PatientLinkBookForm submit() {
		submitButton.click();
		wait.until(ExpectedConditions.visibilityOf(alert));
		return this;
	}
	
	public boolean isSuccessful() {
		return alert.getText().equalsIgnoreCase("Appointment request submitted.");
	}
}
