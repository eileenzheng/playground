package com.vitals.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class FooterPage {

    @FindBy(linkText="Specialty")
    private WebElement findDrBySpecialtyLink;

    @FindBy (linkText="Name")
    private WebElement findDrByNameLink;

    @FindBy (linkText="Group practices")
    private WebElement findDrByGroupPracticeLink;
}
