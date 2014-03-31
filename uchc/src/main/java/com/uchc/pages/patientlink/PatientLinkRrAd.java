package com.uchc.pages.patientlink;

import com.uchc.helpers.Constants;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.By.cssSelector;

public class PatientLinkRrAd extends PatientLinkAd {

    public FluentWebElements name() {
        return links(cssSelector(".bluebox-featured-rr .header p>a"));
    }

    private FluentWebElements addressBlock() {
        return addresses(cssSelector(".bluebox-featured-rr address"));
    }

    public List<String> address1() {
        List<String> addr = new ArrayList<String>();

        for (int i=0; i<addressBlock().size(); i++) {
            String[] lines = addressBlock().get(i).getText().toString().split("\n");
            addr.add(lines[0]);
        }

        return addr;
    }

    public List<String> address2() {
        List<String> addr = new ArrayList<String>();

        for (int i=0; i<addressBlock().size(); i++) {
            String[] lines = addressBlock().get(i).getText().toString().split("\n");
            if (lines.length==2)
                addr.add(null);
            else if (lines.length==3)
                addr.add(lines[1]);
            else
                addr.add(null);
        }

        return addr;
    }

    public List<String> city() {
        List<String> city = new ArrayList<String>();

        for (int i=0; i<addressBlock().size(); i++) {
            String[] lines = addressBlock().get(i).getText().toString().split("\n");
            String lastline = lines[lines.length-1];
            city.add(lastline.substring(0, lastline.indexOf(",")));
        }

        return city;
    }

    public List<String> state() {
        List<String> state = new ArrayList<String>();

        for (int i=0; i<addressBlock().size(); i++) {
            String[] lines = addressBlock().get(i).getText().toString().split("\n");
            String lastline = lines[lines.length-1];
            state.add(lastline.substring(lastline.indexOf(",")+2).split(" ")[0]);
        }

        return state;
    }

    public List<String> zip() {
        List<String> zip = new ArrayList<String>();

        for (int i=0; i<addressBlock().size(); i++) {
            String[] lines = addressBlock().get(i).getText().toString().split("\n");
            String lastline = lines[lines.length-1];
            zip.add(lastline.substring(lastline.indexOf(",")+2).split(" ")[1]);
        }

        return zip;
    }

    public FluentWebElements block() {
        return divs(cssSelector(".bluebox-featured-rr .featured"));
    }

    public FluentWebElements bookButton() {
        setImplicitWait(0);
        List<FluentWebElement> list = new ArrayList<FluentWebElement>();
        for (FluentWebElement el : block()){
            if (el.has().link(cssSelector(".btn")))
                list.add(el.link(cssSelector(".btn")));
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
            if (el.has().link(cssSelector(".phone a")))
                list.add(el.link(cssSelector(".phone a")));
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
            if (el.has().img(cssSelector(".logo img")))
                list.add(el.img(cssSelector(".logo img")));
            else
                list.add(null);
        }
        setImplicitWait(Constants.SELENIUM_IMPLICIT_WAIT);
        return makeFluentWebElements(list,null,null);
    }
}
