package com.vitals.pages.patientlink;

import com.vitalsqa.listener.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ModalEmail {
	private final WebDriver driver;
	private final WebDriverWait wait;
	
	public ModalEmail() {
		driver = DriverManager.getDriver();
		wait = new WebDriverWait(driver, 5, 300);
	}
	
	@FindBy(css=".modal.in #BookingFirstName")
	private WebElement fname;
	
	@FindBy(css=".modal.in #BookingLastName")
	private WebElement lname;
	
	@FindBy(css=".modal.in #BookingPhone")
	private WebElement phone;
	
	@FindBy(css=".modal.in #BookingEmail")
	private WebElement email;
	
	@FindBy(css=".modal.in [value=any]")
	private WebElement radioAny;
	
	@FindBy(css=".modal.in [value=am")
	private WebElement radioAm;
	
	@FindBy(css=".modal.in [value=midday]")
	private WebElement radioMidday;
	
	@FindBy(css=".modal.in [value=afternoon]")
	private WebElement radioAfternoon;
	
	@FindBy(css=".modal.in [value=pm]")
	private WebElement radioPm;
	
	@FindBy(css=".modal.in #BookingApptWhen")
	private WebElement dropDownWhen;
	
	@FindBy(css=".modal.in #BookingInsurance")
	private WebElement insurance;
	
	@FindBy(css=".modal.in .btn.btn-primary.submit-modal-form")
	private WebElement submitButton;
	
	@FindBy(css=".modal.in .notice .alert")
	private WebElement alert;
	
	@FindBy(css=".modal.in .modal-header .close")
	private WebElement close;
	
	public ModalEmail typeFirstName(String text) {
		fname.clear();
		fname.sendKeys(text);
		return this;
	}
	
	public ModalEmail typeLastName(String text) {
		lname.clear();
		lname.sendKeys(text);
		return this;
	}
	
	public ModalEmail typePhone(String text) {
		phone.clear();
		phone.sendKeys(text);
		return this;
	}
	
	public ModalEmail typeEmail(String text) {
		email.clear();
		email.sendKeys(text);
		return this;
	}
	
	public ModalEmail selectRadioAny() {
		radioAny.click();
		return this;
	}
	
	public ModalEmail selectRadioAm() {
		radioAm.click();
		return this;
	}
	
	public ModalEmail selectRadioMidday() {
		radioMidday.click();
		return this;
	}
	
	public ModalEmail selectRadioAfternoon() {
		radioAfternoon.click();
		return this;
	}
	
	public ModalEmail selectRadioPm() {
		radioPm.click();
		return this;
	}
	
	public ModalEmail openDropWhen() {
		dropDownWhen.click();
		return this;
	}
	
	public ModalEmail selectDropAsap() {
		dropDownWhen.sendKeys("ASAP");
		return this;
	}
	
	public ModalEmail selectDrop1Week() {
		dropDownWhen.sendKeys("1 week");
		return this;
	}
	
	public ModalEmail selectDrop2Weeks() {
		dropDownWhen.sendKeys("2 weeks");
		return this;
	}
	
	public ModalEmail selectDrop1Month() {
		dropDownWhen.sendKeys("1 month");
		return this;
	}
	
	public ModalEmail typeInsurance(String text) {
		insurance.clear();
		insurance.sendKeys(text);
		return this;
	}
	
	public ModalEmail submit() {
		submitButton.click();
		wait.until(ExpectedConditions.visibilityOf(alert));
		return this;
	}
	
	public boolean isSuccessful() {
		return alert.getText().equalsIgnoreCase("Good job! Your appointment request has been submitted.");
	}
	
	public void close() {
		close.click();
	}
	
}
