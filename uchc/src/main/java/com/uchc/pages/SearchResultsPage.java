package com.uchc.pages;

import com.uchc.pages.patientlink.PatientLinkCenterAd;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import static org.openqa.selenium.By.cssSelector;

public class SearchResultsPage extends BasePage {

    PatientLinkCenterAd centerAd;

    public SearchResultsPage() {
        centerAd = new PatientLinkCenterAd();
    }

    public PatientLinkCenterAd centerAd() {
        return centerAd;
    }

    public FluentWebElements navTabLinks() {
        return links(cssSelector(".tab-menu .nav-tabs a"));
    }

    public FluentWebElement navTabActive() {
        return li(cssSelector(".tab-menu .nav-tabs .active"));
    }

    public FluentWebElement jumpPageDropDown() {
        return select(cssSelector("#jump_page_link"));
    }

    public FluentWebElement jumpPageActive() {
        return option(cssSelector("#jump_page_link option[selected='selected']"));
    }

    public FluentWebElements paginationLinks() {
        return links(cssSelector(".pagination li>a"));
    }

    public FluentWebElement paginationCurrent() {
        return link(cssSelector(".pagination li.active>a"));
    }

    public FluentWebElements resultPhotos() {
        return links(cssSelector(".search-results:not(.featured) .portrait a"));
    }

    public FluentWebElements resultNames() {
        return links(cssSelector(".search-results:not(.featured) .name"));
    }

    public FluentWebElements resultsRating1(){
        return links(cssSelector(".search-results:not(.featured) .star-rating-control a[title='Poor']"));
    }

    public FluentWebElements resultsRating2() {
        return links(cssSelector(".search-results:not(.featured) .star-rating-control a[title='Fair']"));
    }

    public FluentWebElements resultsRating3() {
        return links(cssSelector(".search-results:not(.featured) .star-rating-control a[title='Good']"));
    }

    public FluentWebElements resultsRating4() {
        return links(cssSelector(".search-results:not(.featured) .star-rating-control a[title='Very Good']"));
    }

    public FluentWebElements resultsRating5() {
        return links(cssSelector(".search-results:not(.featured) .star-rating-control a[title='Excellent']"));
    }

    public FluentWebElements resultGetReportLinks() {
        return links(cssSelector(".search-results:not(.featured) .list-inline li:first-child>a"));
    }

    public FluentWebElements resultsViewProfileLinks() {
        return links(cssSelector(".search-results:not(.featured) .list-inline li:last-child>a"));
    }

    public FluentWebElements browsePanelNameLinks() {
        return links(cssSelector(".col-md-4>.row>.panel-info div>a"));
    }

    public FluentWebElement browsePaneActivePage() {
        return span(cssSelector(".col-md-4>.row>.panel-info .active"));
    }

    public FluentWebElement resultSentence() {
        return li(cssSelector("h1~ul>li"));
    }

    public int totalResults() {
        String count = resultSentence().getText().toString().split(" ")[0];
        String[] split = count.split(",");
        if (split.length==1)
            return Integer.parseInt(split[0]);
        else
            return Integer.parseInt(split[0].concat(split[1]));
    }

    public boolean isCorrectJumpPage() {
        return isNameRangeCorrect(jumpPageActive().getText().toString());
    }

    public boolean isCorrectBrowsePane() {
        return isNameRangeCorrect(browsePaneActivePage().getText().toString());
    }

    private boolean isNameRangeCorrect(String value) {
        String start = value.split(" - ")[0].trim();
        String end = value.split(" - ")[1].trim();
        return (resultNames().get(0).getText().toString().contains(start) &&
                resultNames().get(resultNames().size()-1).getText().toString().contains(end));
    }
}
