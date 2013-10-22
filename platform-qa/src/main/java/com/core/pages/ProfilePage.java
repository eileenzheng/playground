package com.core.pages;

import org.seleniumhq.selenium.fluent.FluentWebElement;

import java.util.List;

import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;
import static org.seleniumhq.selenium.fluent.Period.secs;

public class ProfilePage extends BasePage {

    public ProfilePage() {
    }

    public String providerName() {
        return h3(cssSelector(".title>h3")).getText().toString().trim();
    }

    public FluentWebElement doctorSpecialtyModule() {
        return div(cssSelector(".specialties"));
    }

    public String doctorSpecialtyModuleHeaderText() {
        return doctorSpecialtyModule().h4(cssSelector("panel-title")).getText().toString().trim();
    }
//    public FluentWebElement doctorSpecialtyModuleList() {
//        return FluentWebElement
//    }

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
        if (awardsModule().links(cssSelector(".panel-body>ul>li[ng-show]>a")).size() > 0) {
            return awardsModule().link(cssSelector(".panel-body>ul>li[ng-show]>a")).getText().toString().trim();
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
        pageLoadWait();
        return this;
    }

    private void pageLoadWait() {
        without(secs(5)).div(id("pageLoading"));
    }

}
