package com.vitals.test;

import com.vitals.helpers.Constants;
import com.vitals.pages.patientlink.ModalEmail;
import com.vitals.pages.profile.ProfileCommonPage;
import com.vitals.pages.profile.ProfileReviewsPage;
import com.vitalsqa.testrail.TestCase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ProfileMobileTest {

    static final String drProfileMarina = "/doctors/Dr_Marina_Gafanovich/reviews";
    static final String drProfile = "/doctors/Dr_Emile_Bacha/reviews";
    static final String drProfileLink = "/doctors/Dr_Molly_Adams/profile";

    String url;

    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) throws Exception {
        this.url = url;
    }

    @TestCase(id=3474)
    @Test
    public void markAsHelpful() {
        ProfileReviewsPage reviewsPage = new ProfileReviewsPage();
        reviewsPage.get(url + drProfileMarina);
        ProfileCommonPage profile = new ProfileCommonPage();
        profile.dismissReviewIntercept();

        while (reviewsPage.moreReviewsLink().isDisplayed().value()) {
            profile.scrollToElement(reviewsPage.moreReviewsLink());
            reviewsPage.moreReviewsLink().click();
        }
        reviewsPage.getRandom(reviewsPage.helpfulLink()).click();
        reviewsPage.setImplicitWait(1);
        Assert.assertTrue(reviewsPage.hasErrorText() || reviewsPage.hasHelpfulText(), "Mark as helpful link doesn't work");
        reviewsPage.setImplicitWait(Constants.SELENIUM_IMPLICIT_WAIT);
    }

    @TestCase(id=3475)
    @Test
    public void sortReviews() {
        ProfileReviewsPage reviewsPage = new ProfileReviewsPage();
        reviewsPage.get(url + drProfile);
        ProfileCommonPage profile = new ProfileCommonPage();
        profile.dismissReviewIntercept();
        Assert.assertTrue(reviewsPage.sortByRecent(), "Reviews are not sorted by most recent");
        reviewsPage.selectDropDown(reviewsPage.sortDropDown(), "Oldest");
        Assert.assertTrue(reviewsPage.sortByOldest(), "Reviews are not sorted by oldest first");
    }

    @TestCase(id=3476)
    @Test
    public void nextPage() {
        ProfileReviewsPage reviewsPage = new ProfileReviewsPage();
        reviewsPage.get(url + drProfile);
        ProfileCommonPage profile = new ProfileCommonPage();
        profile.dismissReviewIntercept();
        profile.scrollToElement(reviewsPage.nextPageLink());
        reviewsPage.nextPageLink().click();
        Assert.assertTrue(reviewsPage.common().isCredentialsPage(), "Next page is not credentials page");
    }

    @TestCase(id=3477)
    @Test
    public void tabs() {
        ProfileCommonPage page = new ProfileCommonPage();
        page.get(url + drProfile);
        page.scrollToElement(page.mobCredentialsTab());
        page.mobLocationsTab().click();
        Assert.assertTrue(page.isLocationsAvailabilityPage(), "Locations page didn't load");
    }

    @TestCase(id=3478)
    @Test
    public void bookOnlineEmail() {
        ProfileCommonPage page = new ProfileCommonPage();
        page.get(url + drProfileMarina);
        page.dismissReviewIntercept();
        page.scrollToElement(page.plBookAppt());
        page.plBookAppt().click();
        ModalEmail modal = new ModalEmail();
        modal.fname().clearField().sendKeys("test_first");
        modal.lname().clearField().sendKeys("test_last");
        modal.radioAfternoon().click();
        modal.selectDropDown(modal.dropDownWhen(), "ASAP");
        modal.closeButton().click();
    }

    @TestCase(id=3479)
    @Test
    public void bookOnlineLink() {
        ProfileCommonPage page = new ProfileCommonPage();
        page.get(url + drProfileLink);
        page.dismissReviewIntercept();
        page.scrollToElement(page.plBookAppt());
        page.plBookAppt().click();
        Assert.assertTrue(page.getWindowsCount()==2, "No new window is opened");
    }
}