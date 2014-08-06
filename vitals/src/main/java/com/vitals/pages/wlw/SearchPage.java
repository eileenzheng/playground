package com.vitals.pages.wlw;

import com.vitals.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import static org.openqa.selenium.By.cssSelector;

public class SearchPage extends BasePage {

    HeaderBar headerBar;

    public SearchPage () {
        headerBar = new HeaderBar();
    }

    public HeaderBar headerPage(){
        return headerBar;
    }

    public FluentWebElement heading() {
        return div(cssSelector(".contentHeader"));
    }

    public FluentWebElement specialtyDropDown() {
        return select(cssSelector("#specialist_id"));
    }

    public FluentWebElement locationTextBox() {
        return input(cssSelector("#location"));
    }

    public FluentWebElement searchButton() {
        return input(cssSelector("input[type='image']"));
    }

    public FluentWebElement nextLink() {
        return link(cssSelector(".next a"));
    }

    public FluentWebElement prevLink() {
        return link(cssSelector(".prev a"));
    }

    public FluentWebElements pageLinks() {
        return links(cssSelector(".pagination li:not(.next):not(.prev) a"));
    }

    public FluentWebElement currentPage() {
        return li(cssSelector(".pagination li.active"));
    }

    public FluentWebElements names() {
        return links(cssSelector(".details a"));
    }

    public FluentWebElements specialties() {
        return divs(cssSelector("div.specialty"));
    }

    public FluentWebElements cities() {
        return spans(cssSelector("span[itemprop=addressLocality]"));
    }

    public FluentWebElements states() {
        return spans(cssSelector("span[itemprop=addressRegion]"));
    }

    public FluentWebElement result() {
        return span(cssSelector("#result-count"));
    }

    public FluentWebElement sortDropDown() {
        return select(cssSelector("#sort"));
    }

    public int getResultCount() {
        String count = result().getText().toString().split(" ")[3];
        String[] split = count.split(",");
        if (split.length==1)
            return Integer.parseInt(split[0]);
        else
            return Integer.parseInt(split[0].concat(split[1]));
    }

    public boolean isProfileLinkCorrect() {
        String profileUrl;
        for (int i=0; i<names().size(); i++) {
            profileUrl = names().get(i).getAttribute("href").toString();
            if (!profileUrl.contains("http://www.vitals.com/doctors/Dr_")) {
                return false;
            }
        }
        return true;
    }
}
