package com.uchc.pages;

import org.seleniumhq.selenium.fluent.FluentWebElements;
import java.util.List;

public abstract class PatientLinkAd extends BasePage {

    public abstract FluentWebElements name();

    public abstract List<String> address1();

    public abstract List<String> address2();

    public abstract List<String> city();

    public abstract List<String> state();

    public abstract List<String> zip();

    public abstract FluentWebElements block();

    public abstract FluentWebElements bookButton();

    public abstract FluentWebElements phoneNumber();

    public abstract FluentWebElements logo();

    public int getSize() {
        return name().size();
    }

    public boolean hasAddress2(int i) {
        return address2().get(i)!=null;
    }
}
