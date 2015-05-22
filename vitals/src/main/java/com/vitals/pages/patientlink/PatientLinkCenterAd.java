package com.vitals.pages.patientlink;

import java.util.ArrayList;
import java.util.List;
import com.vitals.helpers.Constants;
import org.openqa.selenium.WebElement;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import static org.openqa.selenium.By.cssSelector;

public class PatientLinkCenterAd extends PatientLinkAd {

	public FluentWebElements name() {
        return links(cssSelector(".serplist-listing-featured .serplist-listing-title>a"));
    }

    public FluentWebElements specialty() {
        return divs(cssSelector(".serplist-listing-featured .serplist-listing-type"));
    }

    public FluentWebElements address1() {
        return spans(cssSelector(".serplist-listing-featured .serplist-listing-address>span>span:first-child"));
    }

    public FluentWebElements address2() {
        return spans(cssSelector(".serplist-listing-featured .serplist-listing-address>span>span:last-child"));
    }

    public FluentWebElements city() {
        return spans(cssSelector(".serplist-listing-featured .serplist-listing-address>span:nth-last-child(3)"));
    }

    public FluentWebElements state() {
        return spans(cssSelector(".serplist-listing-featured .serplist-listing-address>span:nth-last-child(2)"));
    }

    public FluentWebElements zip() {
        return spans(cssSelector(".serplist-listing-featured .serplist-listing-address>span:last-child"));
    }

    public FluentWebElements block() {
        return divs(cssSelector(".serplist-listing-featured"));
    }

    public FluentWebElements bookButton() {
        setImplicitWait(0);
        List<FluentWebElement> list = new ArrayList<FluentWebElement>();
        for (FluentWebElement el : block()){
            if (el.has().link(cssSelector(".serplist-listing-featured-button a.modal-call")))
                list.add(el.link(cssSelector(".serplist-listing-featured-button a.modal-call")));
            else
                list.add(null);
        }
        setImplicitWait(Constants.SELENIUM_IMPLICIT_WAIT);
        return makeFluentWebElements(list,null,null);
    }

    public FluentWebElements phoneNumber() {
        setImplicitWait(0);
        List<FluentWebElement> list = new ArrayList<FluentWebElement>();
        for (FluentWebElement el : block()){
            if (el.has().link(cssSelector(".call-appt")))
                list.add(el.link(cssSelector(".call-appt")));
            else
                list.add(null);
        }
        setImplicitWait(Constants.SELENIUM_IMPLICIT_WAIT);
        return makeFluentWebElements(list,null,null);
    }

    public FluentWebElements logo() {
        setImplicitWait(0);
        List<FluentWebElement> list = new ArrayList<FluentWebElement>();
        for (FluentWebElement el : block()){
            if (el.has().img(cssSelector(".serplist-listing-sponsorship img")))
                list.add(el.img(cssSelector(".serplist-listing-sponsorship img")));
            else
                list.add(null);
        }
        setImplicitWait(Constants.SELENIUM_IMPLICIT_WAIT);
        return makeFluentWebElements(list,null,null);
    }
}
