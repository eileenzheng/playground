package com.core.pages;

import org.seleniumhq.selenium.fluent.FluentWebElement;

import static org.openqa.selenium.By.id;

public class ProfilePage extends BasePage {

    public ProfilePage() {
    }

    public FluentWebElement doctorName() {
        return div(id("placeholder"));
    }

    public FluentWebElement doctorSpecialtyList() {
        return div(id("placeholder"));
    }

    public FluentWebElement animatedDonut() {
        return div(id("placeholder"));
    }

    public FluentWebElement estimatedCost() {
        return div(id("placeholder"));
    }

    public FluentWebElement insuranceCost() {
        return div(id("placeholder"));
    }

}
