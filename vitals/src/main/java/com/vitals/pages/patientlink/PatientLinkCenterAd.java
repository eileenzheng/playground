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
        return links(cssSelector(".listing-featured .profile-name>a"));
    }

    public FluentWebElements specialty() {
        return divs(cssSelector(".listing-featured .location-distance"));
    }

    public FluentWebElements address1() {
        return spans(cssSelector(".listing-featured span[itemprop=streetAddress]>span:first-child"));
    }

    public FluentWebElements address2() {
        return spans(cssSelector(".listing-featured span[itemprop=streetAddress]>span:last-child"));
    }

    public FluentWebElements city() {
        return spans(cssSelector(".listing-featured span[itemprop=addressLocality]"));
    }

    public FluentWebElements state() {
        return spans(cssSelector(".listing-featured span[itemprop=addressRegion]"));
    }

    public FluentWebElements zip() {
        return spans(cssSelector(".listing-featured span[itemprop=postalCode]"));
    }

    public FluentWebElements block() {
        return divs(cssSelector(".listing-featured"));
    }

    public FluentWebElements bookButton() {
        setImplicitWait(0);
        List<FluentWebElement> list = new ArrayList<FluentWebElement>();
        for (FluentWebElement el : block()){
            if (el.has().link(cssSelector(".book-button>a")))
                list.add(el.link(cssSelector(".book-button>a")));
            else
                list.add(null);
        }
        setImplicitWait(Constants.SELENIUM_IMPLICIT_WAIT);
        return makeFluentWebElements(list,null,null);
    }

    public List<WebElement> phoneNumber() {
        setImplicitWait(0);
        List<WebElement> list = new ArrayList<WebElement>();
        for (FluentWebElement el : block()){
            if (el.has().span(cssSelector(".appointment-info"))) {
                list.add(el.span(cssSelector(".appointment-info")).getWebElement().findElement(cssSelector("strong")));
            }
            else
                list.add(null);
        }
        setImplicitWait(Constants.SELENIUM_IMPLICIT_WAIT);
        return list;
    }

    public FluentWebElements logo() {
        setImplicitWait(0);
        List<FluentWebElement> list = new ArrayList<FluentWebElement>();
        for (FluentWebElement el : block()){
            if (el.has().img(cssSelector(".sponsorship img")))
                list.add(el.img(cssSelector(".sponsorship img")));
            else
                list.add(null);
        }
        setImplicitWait(Constants.SELENIUM_IMPLICIT_WAIT);
        return makeFluentWebElements(list,null,null);
    }

    public int getSize() {
        return name().size();
    }
}
