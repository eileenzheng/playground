package com.uchc.pages;

import com.uchc.pages.patientlink.PatientLinkCenterAd;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;

import java.util.List;

import static org.openqa.selenium.By.cssSelector;

public class SearchResultsPage extends BasePage {

    PatientLinkCenterAd centerAd;

    public SearchResultsPage() {
        centerAd = new PatientLinkCenterAd();
    }

    public PatientLinkCenterAd centerAd() {
        return centerAd;
    }

    public FluentWebElement jumpPageDropDown() {
        return select(cssSelector("#jump_page_link"));
    }

    public FluentWebElements paginationLinks() {
        return links(cssSelector(".pagination li>a"));
    }
}
