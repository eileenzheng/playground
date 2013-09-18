package com.capital.pages;

import com.capital.helpers.Profile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;

public class ResultsPage extends BasePage {

    public ResultsPageHeader filter;

    public ResultsPage(WebDriver delegate) {
        super(delegate);
        filter = new ResultsPageHeader(delegate);
    }

    public FluentWebElements resultsList() {
        return divs(cssSelector(".panel.less-pad"));
    }

    public FluentWebElement filterSearchButton() {
        return link(id("filterSearch"));
    }

    public FluentWebElement resetFilterButton() {
        return link(cssSelector("#filter-panel .btn.btn-mini"));
    }

    public FluentWebElement totalResultText() {
        return span(cssSelector(".three.columns.phone-two>span"));
    }

    public FluentWebElements pageNumbers() {
        return lis(cssSelector(".pagination>li"));

    }

    public ResultsPage clickFilterYourSearch() {
        filterSearchButton().click();
        return this;
    }

    public ResultsPage getDrResult(int index) {
        resultsList().get(index);
        return this;
    }

    public int getResultPageCount() {
        return resultsList().size();
    }

    public String getTotalResultsText() {
        return totalResultText().getText().toString();
    }

    public List<Profile> doctorResults() {
        List<Profile> doc = new ArrayList<Profile>();

        for (FluentWebElement el : resultsList()) {
            String name = el.h4(cssSelector(".six.columns.mobile>h4")).getText().toString().trim();
            String url = el.link(cssSelector(".btn.btn-primary")).getAttribute("href").toString();

            Profile dr = new Profile(name,url);

            dr.setSpecialty(el.span(cssSelector(".six.columns.mobile>span")).getText().toString());
            dr.setAddress(el.div(cssSelector(".row>.four.columns:nth-child(1)")).getText().toString());
            dr.setStarRating(el.div(cssSelector(".eight.columns.phone-two")).getText().toString());
            doc.add(dr);
        }

        return doc;
    }

    public List<Profile> facilityResults() {
        List<Profile> doc = new ArrayList<Profile>();

        for (FluentWebElement el : resultsList()) {
            String name = el.h4(cssSelector(".alert-box>h4")).getText().toString().trim();
            String url = el.link(By.cssSelector(".btn.btn-primary")).getAttribute("href").toString();

            Profile dr = new Profile(name,url);

            dr.setSpecialty(el.span(cssSelector(".alert-box>span")).getText().toString());
            dr.setAddress(el.div(cssSelector(".nine.columns.mobile>.row:nth-child(2)>.four.columns:nth-child(1)")).getText().toString());
            doc.add(dr);
        }

        return doc;
    }

    public Boolean facilityHasBlueDistinction(FluentWebElement facility, String bdImage) {
        Boolean flag = false;
        // For each search result
        // Check that it has blue distinction
        By byCss = By.cssSelector(".nine>.row>.four.columns.hide-on-phones:nth-child(2)>img");

        if (facility.imgs(byCss).size() > 0) {
            // Then check that it contains the correct distinction as per filter
            for (FluentWebElement img : facility.imgs(byCss)) {
                if (img.getAttribute("src").toString().contains(bdImage)) {
                    flag = true;
                    break;
                }
            }
        }

        return flag;
    }

    public ResultsPage goToPageNumber(int page) {
        for (FluentWebElement el : pageNumbers()) {
            if (el.getText().toString().equals(page)) {
                el.click();
                break;
            }
        }
        return this;
    }

    public ResultsPage pageJumpForward() {
        for (FluentWebElement el : pageNumbers()) {
            if (el.getText().toString().equals("»")) {
                el.click();
                break;
            }
        }
        return this;
    }

    public ResultsPage pageJumpBack() {
        for (FluentWebElement el : pageNumbers()) {
            if (el.getText().toString().equals("«")) {
                el.click();
                break;
            }
        }
        return this;
    }

    public String getFacilityName(FluentWebElement facility) {
        return facility.h4(cssSelector(".alert-box>h4")).getText().toString().trim();
    }

}
