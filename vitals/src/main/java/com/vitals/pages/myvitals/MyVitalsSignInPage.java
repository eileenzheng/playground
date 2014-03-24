package com.vitals.pages.myvitals;

import com.vitals.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import static org.openqa.selenium.By.cssSelector;

public class MyVitalsSignInPage extends BasePage {

    public FluentWebElement emailTextBox() {
        return input(cssSelector(".sign-in input[type=email]"));
    }

    public FluentWebElement passwordTextBox() {
        return input(cssSelector(".sign-in input[type=password]"));
    }
    
    public FluentWebElement signInButton() {
        return input(cssSelector(".sign-in .button"));
    }
}
