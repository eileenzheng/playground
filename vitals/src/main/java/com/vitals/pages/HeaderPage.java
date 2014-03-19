package com.vitals.pages;

import com.vitals.pages.myvitals.MyVitalsEditAccountPage;
import com.vitals.pages.myvitals.MyVitalsSignInPage;
import com.vitals.pages.ucc.UccProfileSummaryPage;
import com.vitals.pages.ucc.UccSearchResultsPage;
import com.vitals.pages.ucc.UccSitemapPage;
import com.vitalsqa.listener.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import com.vitals.helpers.Constants.SearchType;

import java.util.List;

public class HeaderPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final JavascriptExecutor jse;

    public HeaderPage() {
    	driver = DriverManager.getDriver();
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
    
    @FindBy(css=".categorical-autosuggest .ui-menu-item.parent>a,.ui-menu-item.sub>a")
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
    
    @FindBy(css=".mini-selector.my-any-insurance")
    private WebElement insurance;
    
    @FindBy(css=".insurance_box")
    private WebElement insuranceBox;
    
    @FindBy(css=".insurance_company.ui-autocomplete-input")
    private WebElement insuranceCompanyTextBox;
    
    @FindBy(css=".ui-menu-item>a")
    private List<WebElement> insuranceSuggestions;
    
    @FindBy(css=".insurance_plan")
    private WebElement insurancePlanDropDown;
    
    @FindBy(css=".plan.ui-menu-item>a")
    private List<WebElement> insurancePlanSuggestions;
    
    @FindBy(css=".save_insurance")
    private WebElement saveInsuranceButton;
    
    public HeaderPage openFindDropdown () {
    	findDropdown.click();
    	return this;
    }
    
    public HeaderPage selectFindByDoctor() {
    	findByDoctor.click();
    	return this;
    }
    
    public HeaderPage selectFindByDentist() {
    	findByDentist.click();
    	return this;
    }
    
    public HeaderPage selectFindByUcc() {
    	findByUcc.click();
    	return this;
    }

    public HeaderPage enterSearchTerm (String text) {
    	searchTextBox.clear();
        searchTextBox.sendKeys(text);
        wait.until(ExpectedConditions.visibilityOfAllElements(autocompleteCategories));
        return this;
    }
    
    public String getSearchTerm() {
    	return searchTextBox.getAttribute("value");
    }
    
    public HeaderPage enterReviewSearchTerm (String text) {
    	reviewSearchTextBox.clear();
        reviewSearchTextBox.sendKeys(text);
        wait.until(ExpectedConditions.visibilityOfAllElements(autocompleteCategories));
        return this;
    }
    
    public HeaderPage openInsuranceModal() {
    	insurance.click();
    	return this;
    }
    
    public HeaderPage enterInsuranceCompany(String text) {
    	wait.until(ExpectedConditions.visibilityOf(insuranceBox));
    	insuranceCompanyTextBox.clear();
    	insuranceCompanyTextBox.sendKeys(text);
    	return this;
    }
    
    public HeaderPage selectFirstInsurance() {
    	wait.until(ExpectedConditions.visibilityOfAllElements(insuranceSuggestions));
    	insuranceSuggestions.get(0).click();
    	return this;
    }
    
    public HeaderPage openInsurancePlan() {
    	insurancePlanDropDown.click();
    	return this;
    }
    
    public HeaderPage selectFirstInsurancePlan() {
       	if (insurancePlanSuggestions.size()==0) // workaround to remote random failing
    		openInsurancePlan();
    	wait.until(ExpectedConditions.visibilityOfAllElements(insurancePlanSuggestions));
    	insurancePlanSuggestions.get(0).click();
    	return this;
    }
    
    public HeaderPage clickSaveInsuranceButton() {
    	saveInsuranceButton.click();
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


    public boolean checkSuggestions(SearchType type, String suggestion) {
    	List<WebElement> suggestions = null;
    	
    	switch (type) {
    	case NAME:
    		suggestions = nameSuggestions;
    	case SPECIALTY:
    		suggestions = specialtySuggestions;
    	case CONDITION:
    		suggestions = conditionSuggestions;
    	case UCC:
    		suggestions = uccSuggestions;
    	case LOCATION:
    		suggestions = locationSuggestions;
    	}
    	
        Boolean flag = false;
        for (WebElement el : suggestions) {
            if (el.getText().toLowerCase().contains(suggestion.toLowerCase())) flag = true;
            if (flag) break;
        }
 
        return flag;
    }

    public String getNameSuggestions() {
        StringBuffer drs = new StringBuffer();

        for (WebElement el : nameSuggestions) {
            if (el.findElements(By.tagName("span")).size() > 0)
                drs.append(el.findElements(By.tagName("span")).get(0).getText() + "\n");
        }

        return drs.toString();
    }
    
    public ProfilePage selectRandomName() {
    	int mid = nameSuggestions.size() / 2;
    	nameSuggestions.get(mid).click();
    	
    	return PageFactory.initElements(driver, ProfilePage.class);
    }
    
    public UccProfileSummaryPage selectRandomUcc() {
    	int mid = uccSuggestions.size() / 2;
    	uccSuggestions.get(mid).click();
    	
    	return PageFactory.initElements(driver, UccProfileSummaryPage.class);
    }
    
    public ReviewWritePage selectRandomNameReview() {
    	int mid = nameSuggestions.size() / 2;
    	nameSuggestions.get(mid).click();
    	
    	return PageFactory.initElements(driver, ReviewWritePage.class);
    }
    
    public ReviewWritePage selectRandomUccReview() {
    	int mid = uccSuggestions.size() / 2;
    	uccSuggestions.get(mid).click();
    	
    	return PageFactory.initElements(driver, ReviewWritePage.class);
    }

    public String getSpecialtySearchSuggestions() {
    	StringBuffer specs = new StringBuffer();
        
        for (WebElement el : specialtySuggestions) {
            specs.append(el.getText() + "\n");
        }

        return specs.toString();
    }

    public SearchResultsPage selectRandomSpecialty() {
        int mid = specialtySuggestions.size() / 2;
        Reporter.log("Clicking> " + specialtySuggestions.get(mid).getText());
        specialtySuggestions.get(mid).click();

        return PageFactory.initElements(driver, SearchResultsPage.class);
    }

    public SearchResultsPage selectFirstSpecialty() {
    	wait.until(ExpectedConditions.visibilityOfAllElements(specialtySuggestions));
        specialtySuggestions.get(0).click();
        return PageFactory.initElements(driver, SearchResultsPage.class);
    }

    public String getConditionSearchSuggestions() {
        StringBuffer conds = new StringBuffer();

        for (WebElement el : conditionSuggestions) {
            conds.append(el.getText() + "\n");
        }

        return conds.toString();
    }
    
    public SearchResultsPage selectRandomCondition() {
        int mid = conditionSuggestions.size() / 2;
        Reporter.log("Clicking> " + conditionSuggestions.get(mid).getText());
        conditionSuggestions.get(mid).click();

        return PageFactory.initElements(driver, SearchResultsPage.class);
    }
    
	public SearchResultsPage selectFirstCondition() {
		conditionSuggestions.get(0).click();
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
    	wait.until(ExpectedConditions.visibilityOfAllElements(locationSuggestions));
    	return this;
    }
    
    public HeaderPage selectFirstLocation() {
    	locationSuggestions.get(0).click();
    	return this;
    }

    public boolean locationSearchIsPopulated() {
        return (!locationTextBox.getAttribute("value").equals("") && !locationTextBox.getAttribute("value").equals(","));
    }

    public String getCurrentPopulatedLocation() {
        return locationTextBox.getAttribute("value");
    }
    
    public ReviewPage clickReviewTab() {
    	writeReviewTab.click();
    	return PageFactory.initElements(driver, ReviewPage.class);
    }
    
    public HeaderPage hoverReviewTab() {
    	jse.executeScript("jQuery('a.nav-tab.write-review').trigger('mouseenter');");
    	return this;
    }
    
    public UccSitemapPage clickUrgentCareLink() {
    	urgentCareLink.click();
    	return PageFactory.initElements(driver, UccSitemapPage.class);
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
