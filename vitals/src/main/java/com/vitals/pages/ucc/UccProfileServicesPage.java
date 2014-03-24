package com.vitals.pages.ucc;

import com.vitals.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import static org.openqa.selenium.By.cssSelector;

public class UccProfileServicesPage extends BasePage {

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

    public FluentWebElement menuReviews() {
        return link(cssSelector(".nav-menu li:nth-child(2)>a"));
    }

    public FluentWebElements servicesText() {
        return divs(cssSelector(".row.services>div.col-md-9"));
    }

    public FluentWebElements servicesTitle() {
        return h3s(cssSelector(".row.services h3"));
    }

    public boolean isTitleMatched() {
        return (breadcrumbs().get(breadcrumbs().size() - 1)).getText().toString().equals(h1().getText().toString());
    }
	
	public String getServicesTitle() {
		StringBuilder buf = new StringBuilder();
		for (FluentWebElement el : servicesTitle()) {
			buf.append(el.getText().toString()).append(",");
		}
		buf.deleteCharAt(buf.length()-1);
		return buf.toString();
	}
	
	public int getNumberOfUnavailableService() {
		int i=0;
		for (FluentWebElement el: servicesText()) {
			if (el.getText().toString().contains("Our data indicates this center does not offer"))
				i++;
		}
		return i;
	}
}
