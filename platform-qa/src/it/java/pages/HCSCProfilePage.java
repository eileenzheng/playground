package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver;
import org.seleniumhq.selenium.fluent.FluentWebElement;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;

public class HCSCProfilePage extends BasePage {

    public HCSCProfilePage (WebDriver delegate) {
        super(delegate);
    }

    public void go() {
        get("http://demo.vitalschoice.com/hcsc/#profile_professional_new_costs");
    }

    private FluentWebElement costEstimateBox() {
        return div(id("profile_costs"));
    }

    public boolean costEstimateBoxIsVisible() {
        return costEstimateBox().isDisplayed().value();
    }

    private FluentWebElement costEstimateBoxTitle() {
        return costEstimateBox().h4(cssSelector(".panel-title"));
    }

    public String costEstimate() {
        return costEstimateBoxTitle().getText().toString();
    }

    public boolean yourEstimatedCostIsVisible() {
        return costEstimateBox().div(cssSelector(".panel-body")).div().img().isDisplayed().value();
    }

    public boolean yourProcedureCostIsVisible() {
        return costEstimateBox().div(cssSelector(".panel-body")).div().img().isDisplayed().value();
    }

}
