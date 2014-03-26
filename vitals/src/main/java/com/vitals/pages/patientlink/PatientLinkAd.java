package com.vitals.pages.patientlink;

import com.vitals.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;

import java.util.List;

public abstract class PatientLinkAd extends BasePage {

    public abstract FluentWebElements name();

    public abstract  FluentWebElements specialty();

    public abstract FluentWebElements address1();

    public abstract FluentWebElements address2();

    public abstract FluentWebElements city();

    public abstract FluentWebElements state();

    public abstract FluentWebElements zip();

    public abstract FluentWebElements block();

    public abstract FluentWebElements bookButton();

    public abstract List<WebElement> phoneNumber();

    public abstract FluentWebElements logo();

    public abstract int getSize();

    public boolean hasAddress2(int i) {
        return !address1().get(i).getText().toString().equals(address2().get(i).getText().toString());
    }
}
