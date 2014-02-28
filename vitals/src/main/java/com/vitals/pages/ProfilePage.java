package com.vitals.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.vitals.DriverManager;
import com.vitals.helpers.Constants;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProfilePage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    public final HeaderPage header;
    public final FooterPage footer;
    public final PatientLinkRrAd rrAd;

    public ProfilePage() {
    	driver = DriverManager.getDriver();
    	wait = new WebDriverWait(driver, 15, 3000);
        header = PageFactory.initElements(driver,HeaderPage.class);
        footer = PageFactory.initElements(driver,FooterPage.class);
        rrAd = PageFactory.initElements(driver, PatientLinkRrAd.class);
    }

    @FindBy(css=".tabs td:nth-child(1)>a")
    private WebElement summaryTab;

    @FindBy (css=".tabs td:nth-child(2)>a")
    private WebElement patientReviewsTab;

    @FindBy (css=".tabs td:nth-child(3)>a")
    private WebElement credentialsTab;

    @FindBy (css=".tabs td:nth-child(4)>a")
    private WebElement locationsAvailableTab;

    @FindBy (css=".tabs td:nth-child(5)>a")
    private WebElement acceptedInsuranceTab;

    @FindBy (css=".tabs td:nth-child(6)>a")
    private WebElement sponsoredTab;

    @FindBy(css=".additionalInformation>span")
    private List<WebElement> breadCrumbTrail;

    @FindBy(linkText="Video profile")
    private WebElement drVideo;

    @FindBy(linkText="Doctor's site")
    private WebElement plDrSite;

    // list because of bug on site returns 3 elements sometimes
    @FindBy(css=".patient-link .contact-links .modal-call")
    private List<WebElement> plBookAppt;

    // list because of bug on site returns 3 elements sometimes
    @FindBy(css=".patient-link .call-appointment strong")
    private List<WebElement> plPhoneNumber;
    
    // list because of bug on site returns 3 elements sometimes
    @FindBy(css=".claim-profile>a")
    private List<WebElement> claimProfileLink;
    
    // list because of bug on site returns 3 elements sometimes
    @FindBy(css=".name.awards .current")
    private List<WebElement> name;

    public boolean isSummaryPage() {
        boolean flag = false;

        for (WebElement el : breadCrumbTrail) {
            if (el.getText().equals("Summary")) flag = true;
        }

        return flag;
    }

    public ProfilePage clickPatientReviewsTab() {
        patientReviewsTab.click();
        wait.until(ExpectedConditions.visibilityOf(patientReviewsTab));
        return this;
    }

    public boolean isPatientReviewsPage() {
        boolean flag = false;

        for (WebElement el : breadCrumbTrail) {
            if (el.getText().equals("Patient Reviews")) flag = true;
        }

        return flag;
    }

    public ProfilePage clickCredentials() {
        credentialsTab.click();
        wait.until(ExpectedConditions.visibilityOf(credentialsTab));
        return this;
    }

    public boolean isCredentialsPage() {
        boolean flag = false;

        for (WebElement el : breadCrumbTrail) {
            if (el.getText().equals("Credentials")) flag = true;
        }

        return flag;
    }

    public ProfilePage clickLocationsAvailabilityTab() {
        locationsAvailableTab.click();
        wait.until(ExpectedConditions.visibilityOf(locationsAvailableTab));
        return this;
    }

    public boolean isLocationsAvailabilityPage() {
        boolean flag = false;

        for (WebElement el : breadCrumbTrail) {
            if (el.getText().equals("Locations & Availability")) flag = true;
        }

        return flag;
    }

    public ProfilePage clickAcceptedInsurance() {
        acceptedInsuranceTab.click();
        wait.until(ExpectedConditions.visibilityOf(acceptedInsuranceTab));
        return this;
    }

    public boolean isAcceptedInsurancePage() {
        boolean flag = false;

        for (WebElement el : breadCrumbTrail) {
            if (el.getText().equals("Accepted Insurance")) flag = true;
        }

        return flag;
    }

    public boolean drVideoIsVisible() {
        return isElementPresent(drVideo);
    }

    public boolean isDrSitePresent() {
        return isElementPresent (plDrSite);
    }

    public String getSiteUrl() {
        String onClick = plDrSite.getAttribute("onclick");
        int begin = onClick.indexOf("http");
        int end = onClick.indexOf("', '_blank'");
        return onClick.substring(begin, end);
    }

    public boolean isBookApptPresent() {
    	if (plBookAppt.size()==1)
    		return isElementPresent (plBookAppt.get(0));
    	else
    		return isElementPresent (plBookAppt.get(1));
    }

    public PatientLinkBookModal clickBookAppt() {
    	if (plBookAppt.size()==1)
    		plBookAppt.get(0).click();
    	else
    		plBookAppt.get(1).click();
        return PageFactory.initElements(driver, PatientLinkBookModal.class);
    }

    public boolean isPLPhoneNumberPresent() {
    	if (plPhoneNumber.size()==1)
    		return isElementPresent (plPhoneNumber.get(0));
    	else
    		return isElementPresent (plPhoneNumber.get(1));
    }

    public String getPLPhoneNumber() {
    	if (plPhoneNumber.size()==1)
    		return plPhoneNumber.get(0).getText();
    	else
    		return plPhoneNumber.get(1).getText();
    }

    public boolean isElementPresent (WebElement el) {
    	driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        try {
            if (el.isDisplayed()) {
            	driver.manage().timeouts().implicitlyWait(Constants.SELENIUM_IMPLICIT_WAIT, TimeUnit.SECONDS);
                return true;
            }
            else {
            	driver.manage().timeouts().implicitlyWait(Constants.SELENIUM_IMPLICIT_WAIT, TimeUnit.SECONDS);
                return false;
            }
        }
        catch (NoSuchElementException e) {
        	driver.manage().timeouts().implicitlyWait(Constants.SELENIUM_IMPLICIT_WAIT, TimeUnit.SECONDS);
            return false;
        }
    }
    
    public MyVitalsClaimProfilePage clickClaimProfileLink () {
    	if (claimProfileLink.size()==1)
    		claimProfileLink.get(0).click();
    	else
    		claimProfileLink.get(1).click();
    	return PageFactory.initElements(driver, MyVitalsClaimProfilePage.class);
    }
    
    public WebElement getName() {
    	if (name.size()==1)
    		return name.get(0);
    	else
    		return name.get(1);
    }
}
