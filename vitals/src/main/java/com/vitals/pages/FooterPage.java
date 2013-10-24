package com.vitals.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vitals.DriverManager;

import java.util.List;

public class FooterPage {

    private WebDriver driver;

    public FooterPage() {
    	driver = DriverManager.getDriver();
    }

    @FindBy(css=".find-by-nav>.contain>a:nth-child(2)")
    private WebElement findDrBySpecialtyLink;

    @FindBy (css=".find-by-nav>.contain>a:nth-child(3)")
    private WebElement findDrByNameLink;

    @FindBy (css=".find-by-nav>.contain>a:nth-child(4)")
    private WebElement findDrByGroupPracticeLink;

    @FindBy (css=".sitemap>dl:nth-child(1)>dd>a")
    private WebElement patientEduGuideLink;

    @FindBy (css=".sitemap>dl:nth-child(2)>dd:nth-child(2)>a")
    private WebElement partnerWithUsLink;

    @FindBy (css=".sitemap>dl:nth-child(2)>dd:nth-child(3)>a")
    private WebElement advertiseWithUsLink;

    @FindBy (css=".sitemap>dl:nth-child(2)>dd:nth-child(5)>a")
    private WebElement spotlightLink;

    @FindBy (css=".sitemap>dl:nth-child(3)>dd:nth-child(2)>a")
    private WebElement aboutVitalsLink;

    @FindBy (css=".sitemap>dl:nth-child(3)>dd:nth-child(3)>a")
    private WebElement forDoctorsLink;

    @FindBy (css=".sitemap>dl:nth-child(3)>dd:nth-child(4)>a")
    private WebElement contactUsLink;

    @FindBy (css=".sitemap>dl:nth-child(3)>dd:nth-child(5)>a")
    private WebElement privacyPolicyLink;

    @FindBy (css=".facebook")
    private WebElement fbButton;

    @FindBy (css=".twitter")
    private WebElement twitterButton;

    @FindBy (css=".google")
    private WebElement googlePlusButton;

    @FindBy (css=".pinterest")
    private WebElement pinterestButton;

    @FindBy (css=".extras.small>dl>dd>a")
    private WebElement letUsKnowLink;

    @FindBy (css=".final>p>a")
    private WebElement termsOfUseImageLink;

    @FindBy (css=".final>p>a>img")
    private WebElement termsOfUseImage;

    @FindBy (css=".final>p:nth-child(1)")
    private WebElement currentCopyRightText;

    @FindBy (css=".contain dd>a")
    private List<WebElement> specialtyList;

    public FooterPage clickSpecialtyLink() {
        findDrBySpecialtyLink.click();
        return this;
    }

    public CitySpecLandingPage clickRandomSpecialty() {
        int rand = (int) Math.floor(Math.random() * (specialtyList.size()-1));
        specialtyList.get(rand).click();
        return PageFactory.initElements(driver, CitySpecLandingPage.class);
    }


}
