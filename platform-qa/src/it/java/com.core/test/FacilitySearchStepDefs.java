package com.core.test;

import com.core.WebDriverSingleton;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import com.core.pages.HomePage;
import com.core.pages.ProfilePage;
import com.core.pages.SearchResults;
import org.seleniumhq.selenium.fluent.FluentWebElements;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @And("^I have submitted the search results$")
    public void I_have_submitted_the_search_results() {
        //homePage.searchButton().click();
        homePage.clickDemoFacilitySearch();
    }

    //PUI-206
    @When("^there are more than (\\d+) records$")
    public void there_are_more_than_records(int count) {
        assertThat("Less than 10 records displayed",
                searchResults.resultList().size(), greaterThanOrEqualTo(count));
    }

    @Then("^up to (\\d+) results are displayed by default$")
    public void up_to_results_are_displayed_by_default(int count) {
        assertThat("There were less than 10 records displayed",
                searchResults.resultList().size(), equalTo(count));
    }

    //@PUI-133
    @When("^a search for a facility is completed$")
    public void a_search_for_a_facility_is_completed() {
        assertThat(driver.getCurrentUrl(), containsString("facilities"));
    }


    @Then("^I will see records for facilities that contain a name$")
    public void I_will_see_records_for_facilities_that_contain_a_name() {
        results = searchResults.resultList();
        for (FluentWebElement el : results) {
            assertThat(el.div(cssSelector(".name.ng-binding")).isDisplayed().value(), equalTo(true));
        }
    }

//    //not visible yet
//    @And("^I will see the location name if different from the facility name$")
//    public void I_will_see_the_location_name_if_different_from_the_facility_name() {
//        for (FluentWebElement el : searchResults.resultList()) {
//            assertThat(el.div(cssSelector(".name.ng-binding")).getText().toString(), containsString("Result"));
//        }
//    }

    @And("^I will see the location specialties if they exist$")
    public void I_will_see_the_location_specialties_if_they_exist() {
        searchResults.assertSpecializationsArePresent(searchResults.resultList());
    }

    @And("^I will see a location address if it exists$")
    public void I_will_see_a_location_address_if_it_exists() {
        for (FluentWebElement el : results) {
            assertThat(el.span(cssSelector(".address.ng-binding")).isDisplayed().value(), equalTo(true));
        }
    }

    @And("^I will see a location phone number if it exists$")
    public void I_will_see_a_location_phone_number_if_it_exists() {
        for (FluentWebElement el : results) {
            assertThat(el.span(cssSelector(".phone.ng-scope.ng-binding")).isDisplayed().value(), equalTo(true));
        }
    }

    //@PUI-207
    @Then("^I will see a small map displaying the facilities$")
    public void I_will_see_a_small_map_displaying_the_facilities() {
        assertThat(searchResults.googleMap().isDisplayed(), equalTo(true));
    }


    //@PUI-135
    @And("^I view a facility from the search results$")
    public void I_view_a_facility_from_the_search_results() {
        assertThat(searchResults.resultList().size(), greaterThan(0));
    }

    @And("^the specialties will only be displayed once$")
    public void the_specialties_will_only_be_displayed_once(){
        for (FluentWebElement el : searchResults.resultList()) {
            List<String> resultSpecializations = searchResults.getSpecializations(el);
            searchResults.assertSpecializationDisplayedOnce(resultSpecializations);
        }
    }

    @Then("^I will see an average cost for the provider$")
    public void I_will_see_an_average_cost_for_the_provider() {
        for (FluentWebElement el : searchResults.resultList()) {
            assertThat("Average cost was not displayed for provider: " +
                    el.div(cssSelector(".name")).getText().toString(),
                    el.div(cssSelector(".contact")).span(cssSelector(".cost"))
                            .isDisplayed().value(), equalTo(true));
        }
    }

    //@PUI-49
    @Then("^I will see a range of costs for all providers$")
    public void I_will_see_a_range_of_costs_for_all_providers() {
        assertThat("Average cost containter is not visible", 
                searchResults.averageCostContainerIsPresent(), equalTo(true));
    }


    @And("^the range will display for \"([^\"]*)\"$")
    public void the_range_will_display_for(String arg1) {
        assertThat(searchResults.getCostContainerText(), containsString(arg1));
    }

    @And("^the cost will be \"([^\"]*)\"$")
    public void the_cost_will_be(String arg1) {
        assertThat(searchResults.getCostContainerText(), containsString(arg1));
    }

    @And("^the plan will contribute \"([^\"]*)\"$")
    public void the_plan_will_contribute(String arg1) throws Throwable {
        assertThat(searchResults.getCostContainerText(), containsString(arg1));
    }
}
