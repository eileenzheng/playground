package com.vitals.pages.myvitals;

import com.vitals.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import static org.openqa.selenium.By.cssSelector;

public class MyVitalsEditBasicInfoPage extends BasePage{

    public FluentWebElement alertText() {
        return div(cssSelector(".alert>div"));
    }
    
    public boolean isProfileLinkSuccessAlertShown() {
        return alertText().getText().toString().equals("Your profile is now linked.");
    }

}
