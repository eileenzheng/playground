package com.vitals.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PatientGuidePage {
	private WebDriver driver;
	public final HeaderPage header;
	public final FooterPage footer;
	
	WebDriverWait wait;
	
	public PatientGuidePage (WebDriver driver) {
		this.driver = driver;
		this.header = PageFactory.initElements(driver, HeaderPage.class);
		this.footer = PageFactory.initElements(driver, FooterPage.class);
		this.wait = new WebDriverWait(driver, 15, 3000);
	}
	
	@FindBy(css=".breadcrumbs>span:nth-child(4)>a>span")
	private WebElement breadcrumb;
	
	@FindBy(css=".pg_header>ul>li:nth-child(2)>a")
	private WebElement menuTheTeam;
	
	@FindBy(css=".pg_header>ul>li:nth-child(3)>a")
	private WebElement menuHowToPrepare;
	
	@FindBy(css=".pg_header>ul>li:nth-child(4)>a")
	private WebElement menuQuestionsToAsk;
	
	@FindBy(css=".pg_header>ul>li:nth-child(5)>a")
	private WebElement menuWhatToExpect;
	
	@FindBy(css=".pg_header>ul>li:nth-child(6)>a")
	private WebElement menuTreatmentOptions;
	
	public boolean isOverviewPage() {
		return breadcrumb.getText().equals("Overview");
	}
	
	public boolean isTeamPage() {
		return breadcrumb.getText().equals("The Team");
	}
	
	public boolean isPreparePage() {
		return breadcrumb.getText().equals("How to Prepare");
	}
	
	public boolean isQuestionPage() {
		return breadcrumb.getText().equals("Questions to Ask");
	}
	
	public boolean isExpectPage() {
		return breadcrumb.getText().equals("What to Expect");
	}
	
	public boolean isTreatmentPage() {
		return breadcrumb.getText().equals("Treatment Options");
	}
	
	public PatientGuidePage clickTeam() {
		menuTheTeam.click();
		return this;
	}
	
	public PatientGuidePage clickPrepare() {
		menuHowToPrepare.click();
		return this;
	}
	
	public PatientGuidePage clickQuestion() {
		menuQuestionsToAsk.click();
		return this;
	}
	
	public PatientGuidePage clickExpect() {
		menuWhatToExpect.click();
		return this;
	}
	
	public PatientGuidePage clickTreatment() {
		menuTreatmentOptions.click();
		return this;
	}
}
