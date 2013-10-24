package com.vitals.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.vitals.DriverManager;

import java.util.List;

public class ProfilePage {

    private WebDriver driver;
    public final HeaderPage header;
    public final FooterPage footer;
    public final PatientLinkRrAd rrAd;

    WebDriverWait wait;

    public ProfilePage() {
    	driver = DriverManager.getDriver();
        this.header = PageFactory.initElements(driver,HeaderPage.class);
        this.footer = PageFactory.initElements(driver,FooterPage.class);
        this.rrAd = PageFactory.initElements(driver, PatientLinkRrAd.class);
        this.wait = new WebDriverWait(driver, 15, 3000);
    }

    @FindBy(css="#view-full")
    private WebElement viewFullProfileButton;

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

    @FindBy(css=".trail.current>span")
    private List<WebElement> breadCrumbTrail;

    @FindBy(css="#doctor_video")
    private WebElement drVideo;

    @FindBy(linkText="Doctor's site")
    private WebElement plDrSite;

    @FindBy(css=".ribbon.wide>div>a")
    private WebElement plBookAppt;

    @FindBy(css=".ribbon.wide>div")
    private WebElement plPhoneNumber;

    public boolean viewFullProfileButtonIsVisible() {
        return viewFullProfileButton.isDisplayed();
    }

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
        return drVideo.isDisplayed();
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
        return isElementPresent (plBookAppt);
    }

    public PatientLinkBookModal clickBookAppt() {
        plBookAppt.click();
        return PageFactory.initElements(driver, PatientLinkBookModal.class);
    }

    public boolean isPLPhoneNumberPresent() {
        if (isElementPresent (plPhoneNumber)) {
            if (getPLPhoneNumber().equals("Book appointment"))
                return false;
            else
                return true;
        }
        else
            return false;
    }

    public String getPLPhoneNumber() {
        return plPhoneNumber.getText();
    }

    public static boolean isElementPresent (WebElement el) {
        try {
            if (el.isDisplayed())
                return true;
            else
                return false;
        }
        catch (NoSuchElementException e) {
            return false;
        }
    }
}
