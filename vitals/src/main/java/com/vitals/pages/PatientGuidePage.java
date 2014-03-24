package com.vitals.pages;

import org.seleniumhq.selenium.fluent.FluentWebElement;
import static org.openqa.selenium.By.cssSelector;

public class PatientGuidePage extends BasePage {

    private FluentWebElement breadcrumb(){
        return span(cssSelector(".breadcrumbs>span:nth-child(4)>a>span"));
    }

	public boolean isOverviewPage() {
        return breadcrumb().getText().toString().equalsIgnoreCase("Overview");
	}
	
	public boolean isTeamPage() {
		return breadcrumb().getText().toString().equalsIgnoreCase("The Team");
	}
	
	public boolean isPreparePage() {
		return breadcrumb().getText().toString().equalsIgnoreCase("How to Prepare");
	}
	
	public boolean isQuestionPage() {
		return breadcrumb().getText().toString().equalsIgnoreCase("Questions to Ask");
	}
	
	public boolean isExpectPage() {
		return breadcrumb().getText().toString().equalsIgnoreCase("What to Expect");
	}
	
	public boolean isTreatmentPage() {
		return breadcrumb().getText().toString().equalsIgnoreCase("Treatment Options");
	}

    public FluentWebElement menuTheTeam() {
        return link(cssSelector(".pg_header>ul>li:nth-child(2)>a"));
    }

    public FluentWebElement menuPrepare() {
        return link(cssSelector(".pg_header>ul>li:nth-child(3)>a"));
    }

    public FluentWebElement menuQuestion() {
        return link(cssSelector(".pg_header>ul>li:nth-child(4)>a"));
    }

    public FluentWebElement menuExpect() {
        return link(cssSelector(".pg_header>ul>li:nth-child(5)>a"));
    }

    public FluentWebElement menuTreatment() {
        return link(cssSelector(".pg_header>ul>li:nth-child(6)>a"));
    }
}
