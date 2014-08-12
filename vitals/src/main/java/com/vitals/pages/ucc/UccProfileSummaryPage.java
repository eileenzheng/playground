package com.vitals.pages.ucc;

import com.vitals.pages.BasePage;
import com.vitals.pages.HeaderModule;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import static org.openqa.selenium.By.cssSelector;

public class UccProfileSummaryPage extends BasePage {

    HeaderModule headerModule;

    public UccProfileSummaryPage() {
        headerModule = new HeaderModule();
    }

    public HeaderModule headerModule() {
        return headerModule;
    }

    public FluentWebElements breadcrumbs() {
        return links(cssSelector(".full.ucc>span>a"));
    }

    public FluentWebElement currentTrail() {
        return span(cssSelector(".full.ucc>span:last-child"));
    }

    public FluentWebElement seeAlllink() {
        return link(cssSelector(".services .see-all"));
    }

    public FluentWebElement seeMoreLink() {
        return link(cssSelector(".col-md-4:first-child .ucc-box:not(.services) .see-all"));
    }

    public FluentWebElement readWriteReviewsLink() {
        return link(cssSelector(".col-md-4:nth-child(2) .ucc-box:first-child .see-all"));
    }

    public FluentWebElement headerAddress() {
        return link(cssSelector(".address a"));
    }

    public FluentWebElement mapAddress() {
        return link(cssSelector(".col-md-4:nth-of-type(2) .col-md-12 a:nth-child(3)"));
    }

    public FluentWebElements divAdvertBox() {
        return divs(cssSelector("div.advert.cbox"));
    }

    public FluentWebElement divAdvertWrapper() {
        return div(cssSelector("div.advert-wrapper"));
    }

	public boolean isTitleMatched() {
        return (breadcrumbs().get(breadcrumbs().size() - 1)).getText().toString().equals(h1().getText().toString());
	}
}
