package com.vitals.test;

import com.vitals.helpers.Constants;
import com.vitals.pages.profile.ProfileCommonPage;
import com.vitals.pages.profile.ProfileReviewsPage;
import com.vitalsqa.testrail.TestCase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ProfileReviewsTest {

    static final String drProfile = "/doctors/Dr_Emile_Bacha/reviews";

    String url;

    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) throws Exception {
        this.url = url;
    }

    @TestCase(id=1828)
    @Test
    public void markAsHelpful() {
        ProfileReviewsPage reviewsPage = new ProfileReviewsPage();
        reviewsPage.get(url + drProfile);
        ProfileCommonPage profile = new ProfileCommonPage();
        profile.dismissReviewIntercept();

        reviewsPage.helpfulLink().click();
        reviewsPage.setImplicitWait(1);
        Assert.assertTrue(reviewsPage.hasErrorText() || reviewsPage.hasHelpfulText(), "Mark as helpful link doesn't work");
        reviewsPage.setImplicitWait(Constants.SELENIUM_IMPLICIT_WAIT);
    }

    @TestCase(id=1830)
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

    @TestCase(id=1831)
    @Test
    public void nextPage() {
        ProfileReviewsPage reviewsPage = new ProfileReviewsPage();
        reviewsPage.get(url + drProfile);
        ProfileCommonPage profile = new ProfileCommonPage();
        profile.dismissReviewIntercept();
        reviewsPage.nextPageLink().click();
        Assert.assertTrue(reviewsPage.common().isCredentialsPage(), "Next page is not credentials page");
    }
}
