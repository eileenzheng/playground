package com.uchc.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.uchc.DriverManager;

public class HeaderPage {
	
	private WebDriver driver;
	
	public HeaderPage() {
		driver = DriverManager.getDriver();
	}
	
	@FindBy(css=".nav .mainli:first-of-type a")
    private WebElement navHome;
	
	@FindBy(css=".nav .mainli:nth-of-type(2) a")
	private WebElement navDoctors;
	
	@FindBy(css=".nav .mainli:nth-of-type(3) a")
	private WebElement navNhs;
	
	@FindBy(css=".nav .mainli:nth-of-type(4) a")
	private WebElement navHospitals;
	
	@FindBy(css=".nav .mainli:nth-of-type(5) a")
	private WebElement navCandC;
	
	@FindBy(css=".nav .mainli:nth-of-type(6) a")
	private WebElement navPharmacies;
	
	@FindBy(css=".nav .mainli:nth-of-type(7) a")
	private WebElement navJobs;
	
	@FindBy(css=".nav .mainli ul>li:first-of-type a")
	private WebElement subnavPhysicians;
	
	@FindBy(css=".nav .mainli ul>li:nth-of-type(2) a")
	private WebElement subnavDentists;
	
	@FindBy(css=".nav .mainli ul>li:nth-of-type(3) a")
	private WebElement subnavPodiatrist;
	
	public HomePage clickHome() {
		navHome.click();
		return PageFactory.initElements(driver, HomePage.class);
	}
}
