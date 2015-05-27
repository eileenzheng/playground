package com.vitals.pages.patientlink;

import com.vitals.helpers.Constants;
import org.openqa.selenium.WebElement;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import java.util.ArrayList;
import java.util.List;
import static org.openqa.selenium.By.cssSelector;

public class PatientLinkRrAd extends PatientLinkAd {

    public FluentWebElements name() {
        return h4s(cssSelector(".related-result h4"));
    }

    public FluentWebElements specialty() {
        return ps(cssSelector(".related-result .specialty"));
    }

    public FluentWebElements address1() {
        return spans(cssSelector(".related-result .details>address>span>span:first-child"));
    }

    public FluentWebElements address2() {
        return spans(cssSelector(".related-result .details>address>span>span:last-child"));
    }

    public FluentWebElements city() {
        return spans(cssSelector(".related-result .details address>span:nth-last-child(3)"));
    }

    public FluentWebElements state() {
        return spans(cssSelector(".related-result .details address>span:nth-last-child(2)"));
    }

    public FluentWebElements zip() {
        return spans(cssSelector(".related-result .details address>span:last-of-type"));
    }

    public FluentWebElements block() {
        return divs(cssSelector(".related-result"));
    }

    public FluentWebElements bookButton() {
        setImplicitWait(0);
        List<FluentWebElement> list = new ArrayList<FluentWebElement>();
        for (FluentWebElement el : block()){
            if (el.has().link(cssSelector(".modal-call")))
                list.add(el.link(cssSelector(".modal-call")));
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
            if (el.has().span(cssSelector(".call-wrapper .text>span")))
                list.add(el.span(cssSelector(".call-wrapper .text>span")));
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
            if (el.has().img(cssSelector(".sponsor>img")))
                list.add(el.img(cssSelector(".sponsor>img")));
            else
                list.add(null);
        }
        setImplicitWait(Constants.SELENIUM_IMPLICIT_WAIT);
        return makeFluentWebElements(list,null,null);
    }
}
