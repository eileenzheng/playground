package com.core.pages;

import cucumber.api.java.en.Given;
import org.openqa.selenium.JavascriptExecutor;
import org.seleniumhq.selenium.fluent.FluentWebElement;

import java.util.List;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;
import static org.seleniumhq.selenium.fluent.Period.secs;

public class ProfilePage extends BasePage {

    public ProfilePage() {
    }

    public void go(String url) {
        get(url);
    }

    public String profileName() {
        return h3(cssSelector(".title>h3")).getText().toString().trim();
    }

    public Boolean profileNameIsPresent() {
        return h3s(cssSelector(".title>h3")).size() > 0;
    }

    public String profileTierName() {
        return span(cssSelector(".title>h3>span")).getText().toString().trim();
    }

    public Boolean profileTierNameIsPresent() {
        return spans(cssSelector(".title>h3>span")).size() > 0;
    }

    public Boolean profilePhotoIsPresent() {
        return imgs(cssSelector(".image>img")).size() > 0;
    }

    public String profileAddressText() {
        return (String) ((JavascriptExecutor) webDriver()).executeScript("return document.querySelector('.address').childNodes[0].textContent");
    }

    public Boolean profileAddressIsPresent() {
        String jsReturn = (String) ((JavascriptExecutor) webDriver()).executeScript("return $($('.address')).length === 0 ? '' : $($('.address')[0].childNodes[0]).text()");
        return !jsReturn.equals("");
    }

    public Boolean profileMapAndDirectionsLinkIsPresent() {
        return links(cssSelector(".address.ng-binding>a")).size() > 0;
    }

    public Boolean profilePhoneNumberIsPresent() {
        return spans(cssSelector(".icon.phone>span")).size() > 0;
    }

    public String profileImageHeightWidth() {
        String height = ((JavascriptExecutor) webDriver())
                .executeScript("return $('.image>img')[0].height").toString();
        String width = ((JavascriptExecutor) webDriver())
                .executeScript("return $('.image>img')[0].width").toString();
        //Return ex. 168,168
        return height + "," + width;
    }

    public Boolean specialtiesModuleIsPresent() {
        return divs(cssSelector(".specialties")).size() > 0;
    }

    public FluentWebElement specialtiesModule() {
        return div(cssSelector(".specialties"));
    }

    public FluentWebElement awardsModule() {
        return div(cssSelector(".awards"));
    }

    public Boolean awardsModuleIsPresent() {
        return divs(cssSelector(".awards")).size() > 0;
    }

    public Integer awardsModuleCompressedCount() {
        return awardsModule().lis(cssSelector(".panel-body>ul>li[class*=award]")).size();
    }

    public String getModuleTitleText(FluentWebElement fl) {
        return fl.h4(cssSelector(".panel-title")).getText().toString().trim();
    }

    public String getViewMoreLinkText() {
        if (awardsModule().spans(cssSelector(".panel-body>.nextLink>span")).size() > 0) {
            return awardsModule().spans(cssSelector(".panel-body>.nextLink>span")).getText().toString().trim();
        } else {
            return "";
        }
    }

    public List<FluentWebElement> awardsList() {
        return awardsModule().lis(cssSelector(".panel-body>ul>li[class*=award]"));
    }

    public Boolean awardNameIsPresent(FluentWebElement fl) {
        return fl.links(cssSelector("div>a")).size() > 0;
    }

    public String awardNameText(FluentWebElement fl) {
        return fl.links(cssSelector("div>a")).getText().toString().trim();
    }

    public ProfilePage viewAwardsExpanded() {
        awardsModule().link(cssSelector(".nextLink")).click();
//        pageLoadWait();
        return this;
    }

    private void pageLoadWait() {
        without(secs(5)).div(id("pageLoading"));
    }

    public FluentWebElement identifiersModule() {
        return div(cssSelector(".identifiers"));
    }

    public Boolean identifiersModuleIsPresent() {
        return divs(cssSelector(".identifiers")).size() > 0;
    }

    public String identifierModuleHeaderText() {
        return identifiersModule().h4(cssSelector(".panel-heading>.panel-title")).getText().toString();
    }

    public List<FluentWebElement> identifierList() {
        return identifiersModule().spans(cssSelector(".panel-body>ul>li>span"));
    }

    public String identifierText (FluentWebElement fl) {
        return fl.getText().toString().trim();
    }

    public Boolean amenitiesModuleIsPresent() {
        return divs(cssSelector(".amenities")).size() > 0;
    }

    public FluentWebElement amenitiesModule() {
        return div(cssSelector(".amenities"));
    }

    public String amenitiesModuleTitle() {
        return h4(cssSelector(".amenities>.panel-heading>h4")).getText().toString().trim();
    }

    public List<FluentWebElement> amenitiesList() {
        return lis(cssSelector(".amenities>.panel-body>ul>li[class*=repeat-list]"));
    }

    public String amenityName(FluentWebElement fl) {
        return fl.span(cssSelector(".ng-scope")).getText().toString().trim();
    }

    public Boolean amenityIconIsPresent(FluentWebElement fl) {
        return fl.spans(cssSelector("span[class*=icon]")).size() > 0;
    }

    public Boolean languagesModuleIsPresent() {
        return divs(cssSelector(".langauges")).size() > 0;
    }

    public String languagesSpokenByStaffTitle() {
        return p(cssSelector(".languages>.panel-body>p")).getText().toString().trim();
    }

    public Integer languageSpokenByStaffVisibleCount() {
        return lis(cssSelector(".languages>.panel-body>ul>li[class*=repeat-list]")).size();
    }

    public List<FluentWebElement> languagesSpokenByStaffList() {
        return spans(cssSelector(".languages>.panel-body>ul>li[class*=repeat-list]>span"));
    }

}
