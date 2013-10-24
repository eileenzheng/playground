package com.vitals.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vitals.DriverManager;

import java.util.List;

public class PatientGuideLandingPage {
	private WebDriver driver;
	public final HeaderPage header;
	public final FooterPage footer;

	public PatientGuideLandingPage () {
		driver = DriverManager.getDriver();
		this.header = PageFactory.initElements(driver, HeaderPage.class);
		this.footer = PageFactory.initElements(driver, FooterPage.class);
	}
	
	@FindBy(css=".breadcrumbs>span:nth-child(2)>a>span")
	private WebElement breadcrumb;
	
	@FindBy(linkText="Learn more")
	private WebElement learnMore;
	
	@FindBy(css=".top_patient_guides p>a")
	private List<WebElement> topGuides;
	
	@FindBy(css=".column-list li>a")
	private List<WebElement> guidesAtoZ;
	
	public boolean isLandingPage() {
		if (breadcrumb.getText().equals("Patient Education"))
			return true;
		else
			return false;
	}
	
	public PatientGuidePage clickLearnMore() {
		learnMore.click();
		return PageFactory.initElements(driver, PatientGuidePage.class);
	}
	
	public PatientGuidePage clickTopGuide() {
		int rand = (int) Math.floor(Math.random() * (topGuides.size() - 1));
		topGuides.get(rand).click();
		return PageFactory.initElements(driver, PatientGuidePage.class);
	}
	
	public PatientGuidePage clickAtoZGuide() {
		int rand = (int) Math.floor(Math.random() * (guidesAtoZ.size() - 1));
		guidesAtoZ.get(rand).click();
		return PageFactory.initElements(driver, PatientGuidePage.class);
	}
	
}
