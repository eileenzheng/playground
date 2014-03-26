package com.vitals.pages.myvitals;

import com.vitals.helpers.Constants;
import com.vitals.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import static org.openqa.selenium.By.cssSelector;

public class MyVitalsLocateProfilePage extends BasePage{

    public FluentWebElement noProfileAlert() {
        return div(cssSelector(".alert>div"));
    }

    public FluentWebElement cityStateTextBox() {
        return input(cssSelector("#my_location"));
    }

    public FluentWebElement nameTextBox() {
        return input (cssSelector("#my_name"));
    }

    public FluentWebElements autoSuggestList() {
        return links(cssSelector(".ui-menu-item>a"));
    }

    public boolean isNoProfileAlertCorrect() {
        return noProfileAlert().getText().toString().equals("It looks like you haven't claimed your professional profile yet. Let's get started...");
    }
    
    public void enterCityState(String text) {
    	cityStateTextBox().clearField();
    	cityStateTextBox().sendKeys(text);
        waitUntilVisible(autoSuggestList().get(0), Constants.SELENIUM_EXPLICIT_WAIT);
    }
    
    public void enterName(String text) {
    	nameTextBox().clearField();
    	nameTextBox().sendKeys(text);
    	waitUntilVisible(autoSuggestList().get(0), Constants.SELENIUM_EXPLICIT_WAIT);
    }
    
    public boolean checkLocationSuggestions(String text) {
        Boolean flag = false;
        for (FluentWebElement el : autoSuggestList()) {
            if (el.getText().toString().contains(text)) flag = true;
            if (flag) break;
        }

        return flag;
    }
    
    public boolean checkNameSuggestions(String text) {
        Boolean flag = false;
        for (FluentWebElement el : autoSuggestList()) {
            if (el.getText().toString().contains(text)) flag = true;
            if (flag) break;
        }

        return flag;
    }
    
    public String getLocationSuggestions() {
        StringBuilder suggestList = new StringBuilder();

        for (FluentWebElement el : autoSuggestList()) {
                suggestList.append(el.getText().toString()).append("\n");
        }

        return suggestList.toString();
    }
    
    public String getNameSuggestions() {
        StringBuilder suggestList = new StringBuilder();

        for (FluentWebElement el : autoSuggestList()) {
                suggestList.append(el.getText().toString()).append("\n");
        }

        return suggestList.toString();
    }
}
