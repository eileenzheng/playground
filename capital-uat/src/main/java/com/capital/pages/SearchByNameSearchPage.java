package com.capital.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SearchByNameSearchPage extends BasePage {

    protected SearchSideBar sideBar;

    public SearchByNameSearchPage() {
        this.sideBar = PageFactory.initElements(webDriver(), SearchSideBar.class);
    }

    @FindBy (css=".selectPlan")
    private List<WebElement> providerTypeRadioButtons;

    @FindBy (name="criteria[provider-name]")
    private WebElement providerNameTextBox;

    @FindBy (css="#provider_location")
    private WebElement providerLocationTextBox;

    @FindBy (css="#provider_plan")
    private WebElement planDropDown;

    @FindBy (css=".btn.btn-primary")
    private WebElement searchButton;

    @FindBy (css=".panel>h2")
    private WebElement searchHeaderText;

    @FindBy (css=".alert-box.warning")
    private WebElement alertBox;

    public SearchByNameSearchPage selectPhysicianType() {
        providerTypeRadioButtons.get(0).click();
        return this;
    }

    public SearchByNameSearchPage selectFacilityType() {
        providerTypeRadioButtons.get(1).click();
        return this;
    }

    public boolean isPhysicianTypeSelected() {
        return providerTypeRadioButtons.get(0).isSelected();
    }

    public boolean isFacilityTypeSelected() {
        return providerTypeRadioButtons.get(1).isSelected();
    }

    public SearchByNameSearchPage enterProviderName(String input) {
        providerNameTextBox.sendKeys(input);
        return this;
    }

    public SearchByNameSearchPage enterProviderLocation(String input) {
        providerLocationTextBox.sendKeys(input);
        return this;
    }

    public SearchByNameSearchPage selectPlan(String input) {
        Select sel = new Select(planDropDown);
        sel.selectByVisibleText(input);
        return this;
    }

    public ResultsPage clickSearchButton() {
        searchButton.click();
        return PageFactory.initElements(webDriver(), ResultsPage.class);
    }

    public String getErrorText() {
        return alertBox.getText();
    }

}
