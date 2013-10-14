package com.core.test;

import com.core.WebDriverSingleton;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import com.core.pages.HomePage;
import com.core.pages.SearchResults;
import org.seleniumhq.selenium.fluent.FluentWebElements;


import java.net.MalformedURLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.openqa.selenium.By.cssSelector;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class FacilitySearchStepDefs {

    protected WebDriver driver;
    private HomePage homePage;
    private SearchResults searchResults;

    // Hold results here
    private FluentWebElements results;


    @Before({"@search"})
    public void setUp() throws MalformedURLException {
        homePage = new HomePage();
        searchResults = new SearchResults();
        this.driver = WebDriverSingleton.getInstance();
    }

    @Given("^I have done a zip code search for a facility$")
    public void I_have_done_a_zip_code_search_for_a_facility() {
        //homePage.searchBar().sendKeys("10001");
        homePage.go();

    }

    // For @Given
    @And("^I have submitted the search results$")
    public void I_have_submitted_the_search_results() {
        //homePage.searchButton().click();
        //homePage.clickDemoFacilitySearch();
        // So we can get more than 10 results until they implement actual searching
        homePage.go("http://qa.vitalschoice.com/search?type=facilities&geo_location=New%20York&restrict[radius]=50");
    }

    // PUI-206
    @When("^there are more than (\\d+) records$")
    public void there_are_more_than_records(int count) {
        assertThat("Less than 10 records displayed",
                searchResults.resultList().size(), greaterThanOrEqualTo(count));
    }

    // PUI-206
    @Then("^up to (\\d+) results are displayed by default$")
    public void up_to_results_are_displayed_by_default(int count) {
        assertThat("Exactly 10 records were not displayed",
                searchResults.resultList().size(), equalTo(count));
    }

    // PUI-133
    @When("^a search for a facility is completed$")
    public void a_search_for_a_facility_is_completed() {
        assertThat(driver.getCurrentUrl(), containsString("facilities"));
    }

    // PUI-133
    @Then("^I will see records for facilities that contain a name$")
    public void I_will_see_records_for_facilities_that_contain_a_name() {
        results = searchResults.resultList();
        // Are the names present?
        searchResults.assertNamesArePresent(results);
        // Are the names visible?
        for (FluentWebElement el : results) {
            assertThat(el.div(cssSelector(".name")).isDisplayed().value(), equalTo(true));
        }
    }

//    //not visible yet
//    @And("^I will see the location name if different from the facility name$")
//    public void I_will_see_the_location_name_if_different_from_the_facility_name() {
//        for (FluentWebElement el : searchResults.resultList()) {
//            assertThat(el.div(cssSelector(".name.ng-binding")).getText().toString(), containsString("Result"));
//        }
//    }

    // PUI-133
    @And("^I will see the location specialties if they exist$")
    public void I_will_see_the_location_specialties_if_they_exist() {
        searchResults.assertSpecializationsArePresent(searchResults.resultList());
    }

    // PUI-133
    @And("^I will see a location address if it exists$")
    public void I_will_see_a_location_address_if_it_exists() {
        for (FluentWebElement el : results) {
            assertThat(el.span(cssSelector(".address.ng-binding")).isDisplayed().value(), equalTo(true));
        }
    }

    // PUI-133
    @And("^I will see a location phone number if it exists$")
    public void I_will_see_a_location_phone_number_if_it_exists() {
        for (FluentWebElement el : results) {
            assertThat(el.span(cssSelector(".phone.ng-scope.ng-binding")).isDisplayed().value(), equalTo(true));
        }
    }

    // PUI-207
    @Then("^I will see a small map displaying the facilities$")
    public void I_will_see_a_small_map_displaying_the_facilities() {
        assertThat(searchResults.googleMap().isDisplayed(), equalTo(true));
    }


    // PUI-135
    @And("^I view a facility from the search results$")
    public void I_view_a_facility_from_the_search_results() {
        assertThat(searchResults.resultList().size(), greaterThan(0));
    }

    // PUI-135
    @And("^the specialties will only be displayed once$")
    public void the_specialties_will_only_be_displayed_once(){
        for (FluentWebElement el : searchResults.resultList()) {
            List<String> resultSpecializations = searchResults.getSpecializations(el);
            searchResults.assertSpecializationDisplayedOnce(resultSpecializations);
        }
    }

    // PUI-51
    @Then("^I will see an average cost for the provider$")
    public void I_will_see_an_average_cost_for_the_provider() {
        for (FluentWebElement el : searchResults.resultList()) {
            assertThat("Average cost was not displayed for provider: " +
                    el.div(cssSelector(".name")).getText().toString(),
                    el.div(cssSelector(".contact")).span(cssSelector(".cost"))
                            .isDisplayed().value(), equalTo(true));
        }
    }

    // PUI-49
    @Then("^I will see a range of costs for all providers$")
    public void I_will_see_a_range_of_costs_for_all_providers() {
        assertThat("Average cost containter is not visible", 
                searchResults.averageCostContainerIsPresent(), equalTo(true));
    }

    // PUI-49
    @And("^the range will display for \"([^\"]*)\"$")
    public void the_range_will_display_for(String arg1) {
        assertThat(searchResults.getCostContainerProcedureCostText(), containsString(arg1));
    }

    // PUI-49
    @And("^the cost will be \"([^\"]*)\"$")
    public void the_cost_will_be(String arg1) {
        assertThat(searchResults.getCostContainerProcedureCostText(), containsString(arg1));
    }

    // PUI-49
    @And("^the plan will contribute \"([^\"]*)\"$")
    public void the_plan_will_contribute(String arg1) {
        assertThat(searchResults.getCostContainerInsContributionText(), containsString(arg1));
    }

    // PUI-49
    @And("^the payer cost is displayed as 'We will be contributing approximately \\$\\[LOW COST\\] - \\$\\[HIGH COST\\]\\.'$")
    public void the_payer_cost_is_displayed_as() {
        Pattern regex = Pattern.compile("We will be contributing approximately \\$[0-9,]+\\s?-\\s?\\$[0-9,]+\\.", Pattern.DOTALL);
        Matcher regexMatcher = regex.matcher(searchResults.getCostContainerInsContributionText().trim());
        assertThat("Text does not match. Check the text of: " + searchResults.getCostContainerInsContributionText(),
                regexMatcher.find(),
                equalTo(true));
    }

    // PUI-49
    @And("^the range is displayed as 'On your insurance plan, this type of \\[PROCEDURE NAME\\] will cost you approximately \\$\\[LOW COST\\] - \\$\\[HIGH COST\\]\\.'$")
    public void the_range_is_displayed_as_() {
        Pattern regex = Pattern.compile("On your insurance plan, this type of [a-zA-Z\\s]+ will cost you approximately \\$[0-9,]+\\s?-\\s?\\$[0-9,]+\\.", Pattern.DOTALL);
        Matcher regexMatcher = regex.matcher(searchResults.getCostContainerProcedureCostText().trim());
        assertThat("Text does not match. Check the text of: " + searchResults.getCostContainerProcedureCostText(),
                regexMatcher.find(),
                equalTo(true));
    }

    // PUI-51
    @And("^cost is displayed as 'Avg \\$\\[avg cost\\]'$")
    public void cost_is_displayed_as_Avg_$_avg_cost_() {
        Pattern regex = Pattern.compile("AVG\\$[0-9,]+", Pattern.DOTALL);
        Matcher regexMatcher;
        for (FluentWebElement el : searchResults.resultList()) {
            regexMatcher = regex.matcher(searchResults.getAvgCostForProvider(el));
            assertThat("Text does not match. Check the text of: " + searchResults.getAvgCostForProvider(el),
                    regexMatcher.find(),
                    equalTo(true));
        }
    }
}
