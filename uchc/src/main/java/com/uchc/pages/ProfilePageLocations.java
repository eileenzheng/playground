package com.uchc.pages;

import org.seleniumhq.selenium.fluent.FluentWebElement;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.linkText;

public class ProfilePageLocations extends BasePage {

    public FluentWebElement drivingDirectionsLink() {
        return link(cssSelector("a[title*='Directions to Dr. ']"));
    }

    public FluentWebElement viewAllLocationsLink() {
        return link(linkText("View All Locations"));
    }

    public FluentWebElement compareLink() {
        return link(cssSelector("a[title*='to other Physicians']"));
    }

    public FluentWebElement getDoctorSummaryLink() {
        return link(cssSelector("a[title*='Get a summary of Dr.']"));
    }

    public FluentWebElement map() {
        return div(cssSelector("#map-combined"));
    }
}
