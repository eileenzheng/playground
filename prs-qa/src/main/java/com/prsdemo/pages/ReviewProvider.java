package com.prsdemo.pages;

import com.prsdemo.helpers.Constants;
import org.seleniumhq.selenium.fluent.FluentSelect;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;

public class ReviewProvider extends BasePage {

    public void go(String drID) {
        get(Constants.PRS_DEMO_SITE + "/rate/Doctor/" + drID);
    }

    public ReviewProvider acceptTerms() {
        div(id("C15")).click();
        return this;
    }

    public ReviewProvider checkVerifyServicesReceived() {
        input(id("C3")).click();
        return this;
    }

    public FluentWebElements reviewQuestions() {
        return divs(cssSelector(".C17"));
    }

    public ReviewProvider rateQuestion(FluentSelect element, int rating) {
        if (rating < 1 && rating > Constants.MAX_RATING) throw new IllegalArgumentException("Rating must be between 1 and 5");

        element.lis(cssSelector(".D20>.E11>.rating_5>li")).get(rating - 1).link(cssSelector("a")).click();
        return this;

    }

    public String getAnswer(int question) {
        if (question < 1 && question > Constants.MAX_QUESTIONS) throw new IllegalArgumentException("Question must be between 1 and 5");

        String answer = inputs(cssSelector("#B4>input")).get(question - 1).getAttribute("value").toString();

        return answer.equals("") ? "Not Answered" : answer;

    }

    public ReviewProvider recommendProvider(FluentWebElement element) {
        element.link(cssSelector("#D9>a:nth-child(1)")).click();
        return this;
    }

    public ReviewProvider dontRecommendProvider(FluentWebElement element) {
        element.link(cssSelector("#D9>a:nth-child(3)")).click();
        return this;
    }

    public ReviewProvider additionalComments(FluentWebElement element, String string) {
        element.textarea(cssSelector(".D22>.E20>#Comments_field")).sendKeys(string);
        return this;
    }

    public ReviewProvider resetForm() {
        link(id("D1")).click();
        return this;
    }

    public ReviewProvider cancelSubmission() {
        div(id("C9")).click();
        return this;
    }

    public ReviewProvider submitReview() {
        div(id("C8")).click();
        return this;
    }




}
