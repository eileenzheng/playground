package com.prsdemo.pages;

import com.prsdemo.helpers.Constants;
import org.seleniumhq.selenium.fluent.FluentSelect;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;

public class DoctorReviews extends BasePage {

    public void go(String drID) {
        get(Constants.PRS_DEMO_SITE + "/reviews/Doctor/" + drID);
    }

    public FluentWebElement reviewThisDoctorButton() {
        return link(cssSelector("#C7>a"));
    }

    public FluentWebElement previousPageLink() {
        return link(cssSelector("#B7>a:nth-child(1)"));
    }

    public FluentWebElement nextPageLink() {
        return link(cssSelector("#B7>a:nth-child(3)"));
    }

    public FluentWebElements patientReviews() {
        return divs(cssSelector("body>.A2:nth-child(8)>.B9"));
    }

    public String reviewCount() {
        return link(id("D4")).getText().toString().trim();
    }

    public void clickReviewThisDoctor() {
        reviewThisDoctorButton().click();
    }

    public void clickPreviousPageLink() {
        previousPageLink().click();
    }

    public void clickNextPageLink() {
        nextPageLink().click();
    }

    public String getReviewDate(FluentWebElement element) {
        return element.div(cssSelector(".C6"))
                .div(cssSelector("D3:nth-child(1)"))
                .div(cssSelector("E1"))
                .getText().toString();
    }

    public String getComments(FluentWebElement element) {
        return element.div(cssSelector(".C6"))
                .div(cssSelector("D3:nth-child(2)"))
                .div(cssSelector("E1"))
                .getText().toString();
    }

    /**
     * Use this to get the div containing the thumbs up/down and flag links
     * @param element
     * @return FluentWebElement
     */
    private FluentWebElement getWasThisHelpfulDiv(FluentWebElement element) {
        return element.div(cssSelector(".C6"))
                .div(cssSelector("D3:nth-child(3)"))
                .div(cssSelector("E1"));
    }

    public void clickThumbsUp(FluentWebElement element) {
        element.link(cssSelector("span:nth-child(2)>a")).click();
    }

    public String getThumbsUpCount(FluentWebElement element) {
        return element.span(cssSelector("span:nth-child(3)")).getText().toString().trim();
    }

    public void clickThumbsDown(FluentWebElement element) {
        element.link(cssSelector("span:nth-child(5)>a")).click();
    }

    public String getThumbsDownCount(FluentWebElement element) {
        return element.span(cssSelector("span:nth-child(6)")).getText().toString().trim();
    }

    public void clickFlag(FluentWebElement element) {
        element.link(cssSelector("span:nth-child(8)>a")).click();
    }

    public void clickAreYouSureYes(FluentWebElement element) {
        element.link(cssSelector("span:nth-child(10)>span>a:nth-child(1)")).click();
    }

    public void clickAreYouSureNo(FluentWebElement element) {
        element.link(cssSelector("span:nth-child(10)>span>a:nth-child(2)")).click();
    }

    public String getFlagCount(FluentWebElement element) {
        return element.span(cssSelector("span:nth-child(9)")).getText().toString().trim();
    }

    public String getDrResponse(FluentWebElement element) {
        return element.div(cssSelector(".C6"))
                .div(cssSelector("D3:nth-child(4)"))
                .div(cssSelector("E1"))
                .getText().toString().trim();
    }

    public FluentSelect getSortByDropDown() {
        return select(id("ddl_sortBy"));
    }

    // oldtonew, mosthelpful, leasthelpful, experience: lowtohigh, experience: hightolow
    public void selectSortType(FluentSelect select, String value) {
        select.selectByValue(value);
    }


}
