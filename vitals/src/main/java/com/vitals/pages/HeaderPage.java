package com.vitals.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import com.vitals.DriverManager;
import java.util.List;

public class HeaderPage {

    private final WebDriver driver;
    private final Actions builder;
    private final WebDriverWait wait;
    private final JavascriptExecutor jse;

    public HeaderPage() {
    	driver = DriverManager.getDriver();
    	builder = new Actions(driver);
    	wait = new WebDriverWait(driver,15,2000);
    	jse = (JavascriptExecutor) driver;
    }

    @FindBy(css=".masthead-logo>a")
    private WebElement logo;
    
    @FindBy(css=".find .type-select .mini-selector")
    private WebElement findDropdown;
    
    @FindBy(css="a[data-type=\"1\"]")
    private WebElement findByDoctor;
    
    @FindBy(css="a[data-type=\"4\"]")
    private WebElement findByDentist;
    
    @FindBy(css="a[data-type=\"10\"]")
    private WebElement findByUcc;
    
    @FindBy(css="#q")
    private WebElement searchTextBox;
    
    @FindBy(css="#review_q")
    private WebElement reviewSearchTextBox;
    
    @FindBy(css=".ui-autocomplete-category")
    private List<WebElement> autocompleteCategories;
    
    @FindBy(css=".ui-menu-item.parent>a,.ui-menu-item.sub>a")
    private List<WebElement> specialtySuggestions;

    @FindBy(css=".ui-menu-item[data-id]>a")
    private List<WebElement> conditionSuggestions;
    
    @FindBy(css=".ui-menu-item[data-provider-id]>a")
    private List<WebElement> nameSuggestions;
    
    @FindBy(css=".ui-menu-item[data-facility-id]>a")
    private List<WebElement> uccSuggestions;
    
    @FindBy(css=".ui-menu-item:last-child>a")
    private WebElement showAllLink;
    
    @FindBy(linkText="Show all doctors...")
    private WebElement showAllDoctors;
    
    @FindBy(linkText="Show all facilities...")
    private WebElement showAllFacilities;

    @FindBy(css=".location .location-textbox")
    private WebElement locationTextBoxSelector;
    
    @FindBy(css=".location .location-form .ui-autocomplete-input")
    private WebElement locationTextBox;

    @FindBy(css=".ui-menu-item>a")
    private List<WebElement> locationSuggestions;

    @FindBy(css=".go-button>button")
    private WebElement goButton;
    
    @FindBy(css=".review button")
    private WebElement reviewGoButton;
    
    @FindBy(css=".session-info li>a:nth-child(2)")
    private WebElement urgentCareLink;

    @FindBy(css=".session-info li>a:nth-child(1)")
    private WebElement signUpLink;

    @FindBy(css=".session-info li>a:nth-child(3)")
    private WebElement signInLink;
    
    @FindBy(css=".when-signed-in .email")
    private WebElement signedInEmailLink;
    
    @FindBy(css=".edit-profile")
    private WebElement editProfileLink;
    
    @FindBy(css=".sign-out")
    private WebElement signOutLink;
    
    @FindBy(css=".nav-tab.get-info")
    private WebElement getInfoTab;

    @FindBy(css=".nav-tab.write-review")
    private WebElement writeReviewTab;
    
    public HeaderPage openFindDropdown () {
    	findDropdown.click();
    	return this;
    }
    
    public HeaderPage clickFindByDoctor() {
    	findByDoctor.click();
    	return this;
    }
    
    public HeaderPage clickFindByDentist() {
    	findByDentist.click();
    	return this;
    }
    
    public HeaderPage clickFindByUcc() {
    	findByUcc.click();
    	return this;
    }

    public HeaderPage enterSearchTerm (String text) {
    	searchTextBox.clear();
        searchTextBox.sendKeys(text);
        wait.until(ExpectedConditions.visibilityOfAllElements(autocompleteCategories));
        return this;
    }
    
    public HeaderPage enterReviewSearchTerm (String text) {
    	reviewSearchTextBox.clear();
        reviewSearchTextBox.sendKeys(text);
        wait.until(ExpectedConditions.visibilityOfAllElements(autocompleteCategories));
        return this;
    }
    
    public SearchResultsPage clickGoButton() {
        goButton.click();
        return PageFactory.initElements(driver,SearchResultsPage.class);
    }
    
    public ReviewSearchResultsPage clickReviewGoButton() {
    	reviewGoButton.click();
        return PageFactory.initElements(driver,ReviewSearchResultsPage.class);
    }

    public UccSearchResultsPage clickGoButtonUcc() {
        goButton.click();
        return PageFactory.initElements(driver,UccSearchResultsPage.class);
    }

    public boolean checkNameSuggestions(String suggestion) {
        Boolean flag = false;
        for (WebElement el : nameSuggestions) {
            if (el.getText().contains(suggestion)) flag = true;
            if (flag == true) break;
        }

        return flag;
    }

    public String getNameSuggestions() {
        StringBuffer drs = new StringBuffer();

        for (WebElement el : nameSuggestions) {
            if (el.findElements(By.tagName("span")).size() > 0)
                drs.append(el.findElements(By.tagName("span")).get(0).getText().toString() + "\n");
        }

        return drs.toString();
    }
    
    public ProfilePage clickRandomName() {
    	int mid = nameSuggestions.size() / 2;
    	nameSuggestions.get(mid).click();
    	
    	return PageFactory.initElements(driver, ProfilePage.class);
    }
    
    public UccProfileSummaryPage clickRandomUcc() {
    	int mid = uccSuggestions.size() / 2;
    	uccSuggestions.get(mid).click();
    	
    	return PageFactory.initElements(driver, UccProfileSummaryPage.class);
    }
    
    public ReviewPage clickRandomNameReview() {
    	int mid = nameSuggestions.size() / 2;
    	nameSuggestions.get(mid).click();
    	
    	return PageFactory.initElements(driver, ReviewPage.class);
    }
    
    public ReviewPage clickRandomUccReview() {
    	int mid = uccSuggestions.size() / 2;
    	uccSuggestions.get(mid).click();
    	
    	return PageFactory.initElements(driver, ReviewPage.class);
    }

    public String getSpecialtySearchSuggestions() {
    	StringBuffer specs = new StringBuffer();
        
        for (WebElement el : specialtySuggestions) {
            specs.append(el.getText().toString() + "\n");
        }

        return specs.toString();
    }

    public SearchResultsPage clickRandomSpecialty() {
        int mid = specialtySuggestions.size() / 2;
        Reporter.log("Clicking> " + specialtySuggestions.get(mid).getText().toString());
        specialtySuggestions.get(mid).click();

        return PageFactory.initElements(driver, SearchResultsPage.class);
    }

    public SearchResultsPage clickFirstSpecialty() {
        specialtySuggestions.get(0).click();
        return PageFactory.initElements(driver, SearchResultsPage.class);
    }

    public String getConditionSearchSuggestions() {
        StringBuffer conds = new StringBuffer();

        for (WebElement el : conditionSuggestions) {
            conds.append(el.getText().toString() + "\n");
        }

        return conds.toString();
    }
    
    public SearchResultsPage clickRandomCondition() {
        int mid = conditionSuggestions.size() / 2;
        Reporter.log("Clicking> " + conditionSuggestions.get(mid).getText().toString());
        conditionSuggestions.get(mid).click();

        return PageFactory.initElements(driver, SearchResultsPage.class);
    }
    
    public SearchResultsPage clickShowAllLink() {
    	showAllLink.click();
    	return PageFactory.initElements(driver, SearchResultsPage.class);
    }
    
    public UccSearchResultsPage clickShowAllLinkUcc() {
    	showAllLink.click();
    	return PageFactory.initElements(driver, UccSearchResultsPage.class);
    }
    
    public ReviewSearchResultsPage clickShowAllDoctorsReview() {
    	showAllDoctors.click();
    	return PageFactory.initElements(driver, ReviewSearchResultsPage.class);
    }
    
    public ReviewSearchResultsPage clickShowAllFacilitiesReview() {
    	showAllFacilities.click();
    	return PageFactory.initElements(driver, ReviewSearchResultsPage.class);
    }
    
    public HeaderPage openLocationBox() {
    	locationTextBoxSelector.click();
    	return this;
    }
    
    public HeaderPage enterLocation(String location) {
    	locationTextBox.clear();
    	locationTextBox.sendKeys(location);
    	return this;
    }
    
    public boolean checkLocationSuggestions(String suggestion) {
        Boolean flag = false;
        for (WebElement el : locationSuggestions) {
            if (el.getText().contains(suggestion)) flag = true;
            if (flag == true) break;
        }

        return flag;
    }

    public boolean locationSearchIsPopulated() {
        return !locationTextBox.getAttribute("value").equalsIgnoreCase("enter location");
    }

    public String getCurrentPopulatedLocation() {
        return locationTextBox.getAttribute("value").toString();
    }
    
    public HeaderPage hoverReviewTabOld() {
    	Actions hoverOver = builder.moveToElement(writeReviewTab);
    	hoverOver.perform();
    	return this;
    }
    
    public HeaderPage hoverReviewTab() {
    	jse.executeScript("jQuery('a.nav-tab.write-review').trigger('mouseenter');");
    	return this;
    }
    
    public UccLandingPage clickUrgentCareLink() {
    	urgentCareLink.click();
    	return PageFactory.initElements(driver, UccLandingPage.class);
    }
    
    public MyVitalsSignInPage clickSignIn() {
    	signInLink.click();
    	return PageFactory.initElements(driver,MyVitalsSignInPage.class);
    }
    
    public HeaderPage clickSignedInEmail() {
    	signedInEmailLink.click();
    	return this;
    }
    
    public MyVitalsEditAccountPage clickEditProfile() {
    	editProfileLink.click();
    	return PageFactory.initElements(driver, MyVitalsEditAccountPage.class);
    }
}
