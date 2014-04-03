package com.vitals.pages.profile;

import com.vitals.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;

import java.util.List;

import static org.openqa.selenium.By.cssSelector;

public class ProfileReviewsPage extends BasePage {

    public FluentWebElement helpfulLink() {
        return link(cssSelector("a.helpful"));
    }

    public FluentWebElement abuseLink() {
        return link(cssSelector("a.abuse"));
    }

    public boolean hasHelpfulText() {
        return has().span(cssSelector("span.helpful"));
    }

    public boolean hasAbuseText() {
        return has().span(cssSelector("span.abuse"));
    }

    public boolean hasErrorText() {
        return has().span(cssSelector("span.error"));
    }

    public FluentWebElement sortDropDown() {
        return select(cssSelector("#sort-by"));
    }

    public FluentWebElements dates() {
        return spans(cssSelector(".value-title"));
    }

    public boolean sortByRecent() {
        for (int i=0; i< dates().size()-1; i++) {
            String current = dates().get(i).getAttribute("title").toString();
            String next = dates().get(i+1).getAttribute("title").toString();
            if (convertDate(current) < convertDate(next))
                return false;
        }
        return true;
    }

    public boolean sortByOldest() {
        for (int i=0; i< dates().size()-1; i++) {
            String current = dates().get(i).getAttribute("title").toString();
            String next = dates().get(i+1).getAttribute("title").toString();
            if (convertDate(current) > convertDate(next)) {
                System.out.println(current + next);
                return false;
            }
        }
        return true;
    }

    private int convertDate(String date) {
        return Integer.parseInt(date.substring(0, 4).concat(date.substring(5, 7)).concat(date.substring(8, 10)));
    }
}
