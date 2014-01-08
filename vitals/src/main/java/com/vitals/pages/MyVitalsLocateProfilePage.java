package com.vitals.pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.vitals.DriverManager;

public class MyVitalsLocateProfilePage {
	
	private final WebDriver driver;

    public MyVitalsLocateProfilePage() {
    	driver = DriverManager.getDriver();
    }
    
    @FindBy(css=".alert>div")
    private WebElement noProfileAlert;
    
    @FindBy(css="#my_location")
    private WebElement cityStateTextBox;
    
    @FindBy(css="#my_name")
    private WebElement nameTextBox;
    
    @FindBy(css=".ui-menu-item>a")
    private List<WebElement> autoSuggestList;
    
    public boolean isNoProfileAlertCorrect() {
    	if (noProfileAlert.getText().equals("It looks like you haven't claimed your professional profile yet. Let's get started..."))
    		return true;
    	else
    		return false;
    }
    
    public MyVitalsLocateProfilePage enterCityState(String text) {
    	cityStateTextBox.clear();
    	cityStateTextBox.sendKeys(text);
    	return this;
    }
    
    public MyVitalsLocateProfilePage enterName(String text) {
    	nameTextBox.clear();
    	nameTextBox.sendKeys(text);
    	return this;
    }
    
    public MyVitalsLocateProfilePage clickRandomLocation() {
    	int rand = (int) Math.floor(Math.random() * (autoSuggestList.size() - 1));
    	autoSuggestList.get(rand).click();
    	return this;
    }
    
    public MyVitalsClaimProfilePage clickRandomProvider() {
    	int rand = (int) Math.floor(Math.random() * (autoSuggestList.size() - 1));
    	autoSuggestList.get(rand).click();
    	return PageFactory.initElements(driver, MyVitalsClaimProfilePage.class);
    }
    
    public boolean checkLocationSuggestions(String text) {
        Boolean flag = false;
        for (WebElement el : autoSuggestList) {
            if (el.getText().contains(text)) flag = true;
            if (flag == true) break;
        }

        return flag;
    }
    
    public boolean checkNameSuggestions(String text) {
        Boolean flag = false;
        for (WebElement el : autoSuggestList) {
            if (el.getText().contains(text)) flag = true;
            if (flag == true) break;
        }

        return flag;
    }
    
    public String getLocationSuggestions() {
        StringBuffer suggestList = new StringBuffer();

        for (WebElement el : autoSuggestList) {
                suggestList.append(el.getText().toString() + "\n");
        }

        return suggestList.toString();
    }
    
    public String getNameSuggestions() {
        StringBuffer suggestList = new StringBuffer();

        for (WebElement el : autoSuggestList) {
                suggestList.append(el.getText().toString() + "\n");
        }

        return suggestList.toString();
    }
}
