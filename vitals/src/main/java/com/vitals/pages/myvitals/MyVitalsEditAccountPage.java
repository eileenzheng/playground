package com.vitals.pages.myvitals;

import com.vitals.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import static org.openqa.selenium.By.cssSelector;

public class MyVitalsEditAccountPage extends BasePage{

    public FluentWebElement passwordTextBox() {
        return input(cssSelector("#user_password"));
    }

    public FluentWebElement passwordConfirmationTextBox() {
        return input(cssSelector("#user_password_confirmation"));
    }

    public FluentWebElement passwordCurrentTextBox() {
        return input(cssSelector("#user_current_password"));
    }

    public FluentWebElement saveChangesButton() {
        return button(cssSelector("button[type=submit]"));
    }

    public FluentWebElement editProfileButton() {
        return link(cssSelector(".span3 .button"));
    }

    public FluentWebElement claimProfileButton() {
        return link(cssSelector(".span3 .button-proceed"));
    }
}
