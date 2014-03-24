package com.vitals.pages;

import org.seleniumhq.selenium.fluent.FluentWebElement;
import static org.openqa.selenium.By.linkText;


public class FooterModule extends BasePage{

    public FluentWebElement findDrBySpecialtyLink() {
        return link(linkText("Specialty"));
    }

    public FluentWebElement findDrByNameLink() {
        return link(linkText("Name"));
    }

    public FluentWebElement findDrByGroupPracticeLink() {
        return link(linkText("Group practices"));
    }
}
