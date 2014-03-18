package com.vitals.pages.wlw;

import com.vitals.DriverManager;
import com.vitals.helpers.Profile;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import java.util.List;

public class SearchPage {

    private final WebDriver driver;
    public final HeaderPage header;

    public SearchPage() {
        driver = DriverManager.getDriver();
        header = PageFactory.initElements(driver, HeaderPage.class);
    }

    @FindBy(css=".contentHeader")
    private WebElement heading;

    @FindBy(css="#specialist_id")
    private WebElement specialtyDropDown;

    @FindBy(css="#location")
    private WebElement locationTextBox;

    @FindBy(css="input[type='image']")
    private WebElement searchButton;

    @FindBy(css=".next a")
    private WebElement nextLink;

    @FindBy(css=".prev a")
    private WebElement prevLink;

    @FindBy(css=".pagination li:not(.next):not(.prev) a")
    private List<WebElement> pageLinks;

    @FindBy(css=".pagination li.active")
    private WebElement currentPage;

    @FindBy(css=".details a")
    private List<WebElement> names;

    @FindBy(css="#result-count")
    private WebElement result;

    @FindBy(css="#specialist_id option")
    private List<WebElement> options;

    @FindBy(css="#sort")
    private WebElement sortDropDown;

    @FindBy(css="#sort options")
    private List<WebElement> sortOptions;

    public SearchPage selectOption(String text){
        Select drop = new Select(specialtyDropDown);
        drop.selectByVisibleText(text);
        return PageFactory.initElements(driver, SearchPage.class);
    }

    public SearchPage enterZipCode(String text){
        locationTextBox.clear();
        locationTextBox.sendKeys(text);
        return PageFactory.initElements(driver, SearchPage.class);
    }

    public SearchPage clickSearch(){
        searchButton.click();
        return PageFactory.initElements(driver,SearchPage.class);
    }

    public int getResultCount() {
        String count = result.getText().split(" ")[3];
        String[] split = count.split(",");
        if (split.length==1)
            return Integer.parseInt(split[0]);
        else
            return Integer.parseInt(split[0].concat(split[1]));
    }

    public SearchPage sortBy(String byText) {
        Select sortDrop = new Select(sortDropDown);
        sortDrop.selectByVisibleText(byText);
        return PageFactory.initElements(driver, SearchPage.class);
    }

    public SearchPage clickNext() {
        nextLink.click();
        return this;
    }

    public SearchPage clickPrev() {
        prevLink.click();
        return this;
    }

    public SearchPage clickRandomPage() {
        int rand = (int) Math.floor(Math.random() * (pageLinks.size() - 1));
        pageLinks.get(rand).click();
        return this;
    }

    public String getCurrentPageNumber() {
        return currentPage.getText();
    }

    public boolean isProviderMatchingVitals(List<Profile> vitalsProfile) {
        int len;
        if (names.size() > vitalsProfile.size())
            len = vitalsProfile.size();
        else
            len = names.size();

        for (int i=0; i<len; i++) {
            Reporter.log(names.get(i).getText() + " vs " + vitalsProfile.get(i).getName());
            if (!names.get(i).getText().equals(vitalsProfile.get(i).getName()))
                return false;
        }
        return true;
    }

    public boolean isProfileLinkCorrect() {
        String profileUrl;
        for (int i=0; i<names.size(); i++) {
            profileUrl = names.get(i).getAttribute("href");
            if (!profileUrl.contains("http://www.vitals.com/doctors/Dr_")) {
                return false;
            }
        }
        return true;
    }
}
