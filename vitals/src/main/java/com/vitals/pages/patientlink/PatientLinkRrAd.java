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
        return links(cssSelector(".related.block.border .rr-row .title>a"));
    }

    public FluentWebElements specialty() {
        return divs(cssSelector(".related.block.border .rr-row .specialty"));
    }

    public FluentWebElements address1() {
        return spans(cssSelector(".related.block.border .rr-row .details>address>span>span:first-child"));
    }

    public FluentWebElements address2() {
        return spans(cssSelector(".related.block.border .rr-row .details>address>span>span:last-child"));
    }

    public FluentWebElements city() {
        return spans(cssSelector(".related.block.border .rr-row .details address>span:nth-last-child(3)"));
    }

    public FluentWebElements state() {
        return spans(cssSelector(".related.block.border .rr-row .details address>span:nth-last-child(2)"));
    }

    public FluentWebElements zip() {
        return spans(cssSelector(".related.block.border .rr-row .details>address>span:last-of-type"));
    }

    public FluentWebElements block() {
        return divs(cssSelector(".related.block.border .row2"));
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

    public List<WebElement> phoneNumber() {
        setImplicitWait(0);
        List<WebElement> list = new ArrayList<WebElement>();
        for (FluentWebElement el : block()){
            if (el.has().link(cssSelector(".call-appointment a")))
                list.add(el.link(cssSelector(".call-appointment a")).getWebElement().findElement(cssSelector("strong")));
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
            if (el.has().img(cssSelector(".logo>img")))
                list.add(el.img(cssSelector(".logo>img")));
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
