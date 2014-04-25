package com.uchc.pages.profile;

import com.uchc.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.linkText;

public class ProfilePageSummary extends BasePage {

    public FluentWebElement phoneNumberLink() {
        return link(cssSelector("a[title*='Phone Number for Dr.']"));
    }

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

    public FluentWebElement moreInfoOnReportLink() {
        return link(linkText("More information available on your free report"));
    }

    public FluentWebElements ratingStars() {
        return links(cssSelector(".star-rating>a"));
    }

    public FluentWebElement map() {
        return div(cssSelector("#map-small-1"));
    }
}
