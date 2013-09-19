package com.core.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.not;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;

public class SearchResults extends BasePage {

    public SearchResults() {
    }

    public FluentWebElements resultList() {
        return links(cssSelector(".result"));
    }

    public void assertNamesArePresent(FluentWebElements elements) {
        for (FluentWebElement el : elements) {
            assertThat(el.div(cssSelector(".name")).isDisplayed().value(), equalTo(true));
        }
    }

    public List<String> resultName(FluentWebElements elements) {
        List<String> retValue = new ArrayList<String>();

        for (FluentWebElement el : elements) {
            retValue.add(el.link(cssSelector(".name")).getText().toString());
        }

        return retValue;
    }

    public void assertAddressesArePresent(FluentWebElements elements) {
        for (FluentWebElement el : elements) {
            assertThat(el.div(cssSelector(".contact"))
                    .span(cssSelector(".address"))
                    .isDisplayed().value(), equalTo(true));
        }
    }

    public List<String> resultAddresses(FluentWebElements elements) {
        List<String> retValue = new ArrayList<String>();

        for (FluentWebElement el : elements) {
            retValue.add(el.div(cssSelector(".contact"))
                    .span(cssSelector(".address")).getText().toString());
        }

        return retValue;
    }

    public void assertSpecializationsArePresent(FluentWebElements elements) {
        for (FluentWebElement el : elements) {
            // Make sure we have specializations
            assertThat(el.div(cssSelector(".specializations")).isDisplayed().value(), equalTo(true));
            // Make sure it's populated
            assertThat(el.div(cssSelector(".specializations")).spans().size(), greaterThan(0));
        }
    }

    public List<String> resultSpecializations(FluentWebElements elements) {
        List<String> retValue = new ArrayList<String>();

        for (FluentWebElement el : elements) {
            if (el.div(cssSelector(".specializations")).spans().size() > 1) {
                StringBuilder sb = new StringBuilder();
                String delim = "";
                for (FluentWebElement elm : el.div(cssSelector(".specializations")).spans()) {
                    sb.append(delim).append(elm.getText().toString());
                    delim = "|";
                }
                retValue.add(sb.toString());

            } else {
                retValue.add(el.div(cssSelector(".specializations")).span().getText().toString());
            }

        }

        return retValue;
    }

    /**
     * Return a single list of specializations
     * @param element
     * @return
     */
    public List<String> getSpecializations(FluentWebElement element) {
        List<String> retValue = new ArrayList<String>();

        for (FluentWebElement elm : element.div(cssSelector(".specializations")).spans()) {
            retValue.add(elm.getText().toString());
        }

        return retValue;
    }

    public void assertSpecializationDisplayedOnce(List<String> specs) {
        if (specs.size() == 1) {
            return;
        } else {
            for (int i = 0; i < specs.size(); i++) {
                for (int j = 0; j < specs.size(); j++) {
                    if (i != j) {
                        assertThat(specs.get(i), not(equalTo(specs.get(j))));
                    }
                }
            }
        }
    }

    public FluentWebElement averageCostContainer() {
        return div(cssSelector(".row>.col-lg-12"));
    }

    public boolean averageCostContainerIsPresent() {
        return divs(cssSelector(".row>.col-lg-12")).size() > 0;
    }

    public String getCostContainerText() {
        return averageCostContainer().p().getText().toString();
    }

    public FluentWebElement seeMoreLink() {
        return div(id("placeholder"));
    }

    public boolean seeMoreLinkIsVisible() {
        return seeMoreLink().isDisplayed().value();
    }

    public WebElement googleMap(){
        return webDriver().findElement(By.cssSelector(".map"));
    }

    public List<Result> makeResults(FluentWebElements resultList) {
        List<Result> results = new ArrayList<Result>();

        for (FluentWebElement el : resultList) {
            String name = el.link(cssSelector(".name")).getText().toString();
            List<String> specializations = getSpecializations(el);
            String address = el.div(cssSelector(".contact")).span(cssSelector(".address")).getText().toString();
            String phone = el.div(cssSelector(".contact")).span(cssSelector(".phone")).getText().toString();
            results.add(new Result(name, specializations, address, phone));
        }

        return results;
    }

    class Result {

        String name;
        List<String> specializations;
        String address;
        String phone;

        public Result(String name, List<String> specializations, String address, String phone) {
            this.name = name;
        }
    }
}
