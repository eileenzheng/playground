package com.vitals.pages.ucc;

import com.vitals.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import static org.openqa.selenium.By.cssSelector;

public class UccProfileReviewsPage extends BasePage {

    public FluentWebElements breadcrumbs() {
        return links(cssSelector(".full.ucc>span>a"));
    }

    public FluentWebElement currentTrail() {
        return span(cssSelector(".full.ucc>span:last-child"));
    }

    public FluentWebElement menu() {
        return div(cssSelector(".nav-menu"));
    }

    public FluentWebElement menuAbout() {
        return link(cssSelector(".nav-menu li:nth-child(3)>a"));
    }

    public FluentWebElement menuServices() {
        return link(cssSelector(".nav-menu li:nth-child(4)>a"));
    }

    public FluentWebElement totalRating() {
        return span(cssSelector(".overall span.col-md-12"));
    }

    public FluentWebElements ratingBreakdown() {
        return tds(cssSelector(".rating-bars .count"));
    }

    public boolean isTitleMatched() {
        return (breadcrumbs().get(breadcrumbs().size() - 1)).getText().toString().equals(h1().getText().toString());
    }
	
	public int getTotalRating() {
		String rating = totalRating().getText().toString().split(" ")[0];
		return Integer.parseInt(rating);
	}
	
	public int getTotalRatingFromBreakDown() {
		int total = 0;
		for (FluentWebElement el : ratingBreakdown()) {
			total = total + Integer.parseInt(el.getText().toString());
		}
		return total;
	}
}
