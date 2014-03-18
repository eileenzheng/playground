package com.vitals.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PatientGuidePage {
	
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
		return breadcrumb.getText().equalsIgnoreCase("Overview");
	}
	
	public boolean isTeamPage() {
		return breadcrumb.getText().equalsIgnoreCase("The Team");
	}
	
	public boolean isPreparePage() {
		return breadcrumb.getText().equalsIgnoreCase("How to Prepare");
	}
	
	public boolean isQuestionPage() {
		return breadcrumb.getText().equalsIgnoreCase("Questions to Ask");
	}
	
	public boolean isExpectPage() {
		return breadcrumb.getText().equalsIgnoreCase("What to Expect");
	}
	
	public boolean isTreatmentPage() {
		return breadcrumb.getText().equalsIgnoreCase("Treatment Options");
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
