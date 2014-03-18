package com.vitals.pages.wlw;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderPage {

    @FindBy(css=".header")
    private WebElement header;

    @FindBy(css="img[src*='logo']")
    private WebElement logo;

    public String getHeaderText() {
        return header.getText();
    }

    public boolean isLogoDisplayed() {
        return logo.isDisplayed();
    }
}
