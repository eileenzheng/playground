package com.prsdemo.pages;

import com.prsdemo.helpers.Constants;
import org.seleniumhq.selenium.fluent.FluentSelect;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;

public class ModAccess extends BasePage {

    public void go() {
        get(Constants.PRS_DEMO_SITE + Constants.PRS_MOD_MANAGER);
        login();
    }

    private void login() {
        input(id("username")).sendKeys(Constants.PRS_MOD_MANAGER_USERNAME);
        input(id("password")).sendKeys(Constants.PRS_MOD_MANAGER_PASSWORD);
        input(id("btn_submit")).click();
    }

    public FluentWebElement modStatusSelectAllCheckBox() {
        return input(id("selectAll"));
    }

    public FluentWebElement modStatusEscalatedCheckBox() {
        return input(id("modStatus_escalated"));
    }

    public FluentWebElement modStatusUnmoderatedCheckBox() {
        return input(id("modStatus_unmoderated"));
    }

    public FluentWebElement modStatusForRemoderationCheckBox() {
        return input(id("modStatus_flagged"));
    }

    public FluentWebElement modStatusAcceptedCheckBox() {
        return input(id("modStatus_approved"));
    }

    public FluentWebElement modStatusRejectedCheckBox() {
        return input(id("modStatus_rejected"));
    }

    public FluentWebElement modStatusOnHoldCheckBox() {
        return input(id("modStatus_onhold"));
    }

    public boolean checkedValueOf(FluentWebElement element) {
        return element.isSelected().value();
    }

    public ModAccess check(FluentWebElement element) {
        if (!checkedValueOf(element)) {
            element.click();
        }
        return this;
    }

    public ModAccess unCheck(FluentWebElement element) {
        if (checkedValueOf(element)) {
            element.click();
        }
        return this;
    }

    private FluentWebElement startDateTextBox() {
        return input(id("startDate"));
    }

    private FluentWebElement endDateTextBox() {
        return input(id("endDate"));
    }

    public ModAccess enterStartDate(String date) {
        startDateTextBox().sendKeys(date);
        return this;
    }

    public ModAccess enterEndDate(String date) {
        endDateTextBox().sendKeys(date);
        return this;
    }

    private FluentWebElement reviewIDTextBox() {
        return input(id("reviewId"));
    }

    private FluentWebElement memberIDTextBox() {
        return input(id("reviewerId"));
    }

    public ModAccess enterReviewID(String id) {
        reviewIDTextBox().sendKeys(id);
        return this;
    }

    public ModAccess enterMemberID(String id) {
        memberIDTextBox().sendKeys(id);
        return this;
    }

    private FluentWebElement providerIDTextBox() {
        return input(id("predicateToken"));
    }

    private FluentSelect providerTypeDropDown() {
        return select(id("predicateType"));
    }

    public ModAccess enterProviderID(String id) {
        providerIDTextBox().sendKeys(id);
        return this;
    }

    public ModAccess selectProviderType(String value) {
        if (value.toLowerCase().equals("doctor")) {
            providerTypeDropDown().selectByValue(value);
        } else {
            throw new IllegalArgumentException("Value must be \"Doctor\"");
        }
        return this;
    }

    private FluentWebElement filterButton() {
        return div(id("filterButton"));
    }

    public ModAccess clickFilterButton() {
        filterButton().click();
        return this;
    }

    private FluentWebElement resetFiltersLink() {
        return div(cssSelector("#search_frame>div:nth-child(9)>div>div:nth-child(2)"));
    }

    public ModAccess clickResetFiltersLink() {
        resetFiltersLink().click();
        return this;
    }

    private FluentWebElement logoutLink() {
        return link(id("logout_link"));
    }

    public ModAccess clickLogoutLink() {
        logoutLink().click();
        return this;
    }

    private FluentWebElement currentFiltersText() {
        return div(cssSelector("#A3>div"));
    }

    public String currentFilters() {
        return currentFiltersText().getText().toString();
    }

    /**
     * Returns the total number of reviews available
     * @return
     */
    public String totalReviewCount() {
        return parsePaginationString(3);
    }

    /**
     * Returns the current start review pagination
     * ex. "11 - 20 of 100" will return 11
     * @return
     */
    public String currentReviewPageStartNumber() {
        return parsePaginationString(1);
    }

    /**
     * Returns the current end review pagination
     * ex. "11 - 20 of 100" will return 20
     * @return
     */
    public String currentReviewPageEndNumber() {
        return parsePaginationString(2);
    }

    /**
     * Parse the pagination string found on the modmanager pager
     * @param group 1 for start review, 2 for current page max, 3 for total review count
     *              ex."Viewing reviews 1 - 10 of 97"
     *              group 1 = 1, group 2 = 10, group 3 = 97
     * @return String
     */
    private String parsePaginationString(int group) {
        String a = div(cssSelector("#A4>div:nth-child(2)>div")).getText().toString();
        Pattern regex = Pattern.compile("(\\d+)\\s\\-\\s(\\d+)\\sof\\s(\\d+)");
        Matcher m = regex.matcher(a);
        return m.find() ? m.group(group) : null;
    }

    private FluentSelect sortBy() {
        return select(cssSelector("#sort_by>select"));
    }

    public ModAccess sortByDateOldest() {
        sortBy().selectByValue("oldest");
        return this;
    }

    public ModAccess sortByDateNewest() {
        sortBy().selectByValue("newest");
        return this;
    }

    /**
     * Finds all the reviews on the current page
     * @return
     */
    private FluentWebElements reviews() {
        return divs(cssSelector("form[id^=review_id_]"));
    }

    private String getId(FluentWebElement element) {
        return element.getAttribute("id").toString().replaceAll("review_id_(\\d+)$","$1");
    }


    /**
     * Get the reviewid from the given review
     * @param element
     * @return
     */
    private String reviewId(FluentWebElement element) {
        String elementID = getId(element);
        return trimmedElement(element.div(id("review_"+elementID))).replaceAll("Review ID: (\\d+)$","$1");
    }

    private String screenName(FluentWebElement element) {
        String elementID = getId(element);
        return trimmedElement(div(id("Screenname_"+elementID))).replaceAll("Screen Name: (\\d+)$","$1");
    }

    private String headline(FluentWebElement element) {
        String elementID = getId(element);
        return trimmedElement(div(id("Headline_"+elementID))).replaceAll("Headline: (\\d+)$","$1");
    }

    private String comment(FluentWebElement element) {
        String elementID = getId(element);
        return trimmedElement(div(id("Comments_" + elementID))).replaceAll("Comment: (\\d+)$","$1");
    }

    private String response(FluentWebElement element) {
        String elementID = getId(element);
        if (divs(id("Response_" + elementID)).size() > 0) {
            return trimmedElement(div(id("Response_"+elementID))).replaceAll("Response: (\\d+)$","$1");
        } else {
            return "";
        }
    }

    private String memberid(FluentWebElement element) {
        String elementID = getId(element);
        return trimmedElement(div(id("reviewerid_"+elementID))).replaceAll("Member ID: (\\d+)$","$1");
    }

    private String submitted(FluentWebElement element) {
        String elementID = getId(element);
        return trimmedElement(div(id("date_"+elementID))).replaceAll("Submitted: (\\d+)$","$1");
    }

    private String providerId(FluentWebElement element) {
        String elementID = getId(element);
        return trimmedElement(div(id("providerId_"+elementID))).replaceAll("Provider ID: (\\d+)$","$1");
    }

    /**
     * Generate a list of reviews from the info scraped from the page
     * @param elements
     * @return
     */
    public List<Review> processReviews() {
        List<Review> reviews = new ArrayList<Review>();
        for (FluentWebElement el : reviews()) {
            Review rev = new Review();
            rev.reviewId = reviewId(el);
            rev.screenName = screenName(el);
            rev.headline = headline(el);
            rev.comment = comment(el);
            rev.response = response(el);
            rev.memberid = memberid(el);
            rev.submitted = submitted(el);
            rev.providerid = providerId(el);
            reviews.add(rev);
        }
        return reviews;
    }

    public String listReviews(List<Review> reviews) {
        StringBuilder sb = new StringBuilder();
        String separator = "";
        for (Review rev : reviews) {
            sb.append(separator).append(rev.toString());
            separator = ",\n";
        }
        return sb.toString();
    }

    class Review {
        String reviewId;
        String screenName;
        String headline;
        String comment;
        String response;
        String memberid;
        String submitted;
        String providerid;

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(reviewId).append(",")
                    .append(screenName).append(",")
                    .append(headline).append(",")
                    .append(comment).append(",")
                    .append(response).append(",")
                    .append(memberid).append(",")
                    .append(submitted).append(",")
                    .append(providerid).append(",");
            return sb.toString();
        }
    }
}
