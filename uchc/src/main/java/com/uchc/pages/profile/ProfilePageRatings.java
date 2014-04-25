package com.uchc.pages.profile;

import com.uchc.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import static org.openqa.selenium.By.cssSelector;

public class ProfilePageRatings extends BasePage {

    public FluentWebElements selectedStars() {
        return divs(cssSelector(".star-rating-on"));
    }

    public FluentWebElement alert() {
        return li(cssSelector(".bs-callout-danger li"));
    }

    public FluentWebElement postOverallRatingButton() {
        return button(cssSelector("#form-post>.section-ph:first-child button"));
    }

    public FluentWebElements showHideDetailsLinks() {
        return links(cssSelector(".reviews-overall-comment>a"));
    }

    public FluentWebElements reviewDetails() {
        return divs(cssSelector(".reviews-details"));
    }
}
