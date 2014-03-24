package com.vitals.pages.myvitals;

import com.vitals.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import static org.openqa.selenium.By.cssSelector;

public class MyVitalsHomePage extends BasePage {

    public FluentWebElement alertText() {
        return div(cssSelector(".alert>div"));
    }

    public FluentWebElements buttons() {
        return links(cssSelector(".button-large"));
    }
    
    public boolean isSignInSuccessful() {
        return alertText().getText().toString().equals("Signed in successfully.");
    }
    
    public boolean isAccountUpdateSuccessful() {
        return alertText().getText().toString().equals("You updated your account successfully.");
    }

}
