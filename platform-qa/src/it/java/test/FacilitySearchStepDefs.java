package test;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import pages.HomePage;
import pages.ProfilePage;
import pages.SearchResults;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.By.cssSelector;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class FacilitySearchStepDefs {

    protected WebDriver driver;
    private HomePage homePage;
    private SearchResults searchResults;
    private ProfilePage profilePage;

    @Before
    public void setUp() throws MalformedURLException {
//        driver = new FirefoxDriver();
        URL server = new URL("http://thvitdatadev01.mdx.med:4444/wd/hub");
        DesiredCapabilities caps = DesiredCapabilities.firefox();

        driver = new RemoteWebDriver(server, caps);

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        homePage = new HomePage(driver);
        searchResults = new SearchResults(driver);
        profilePage = new ProfilePage(driver);

    }

    @After
    public void breakDown() {
        driver.close();
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

//    //PUI-206
//    @When("^there are more than (\\d+) records$")
//    public void there_are_more_than_records(int count) {
//        assertThat(searchResults.resultList().size(), greaterThanOrEqualTo(count));
//    }
//
//    @Then("^up to (\\d+) results are displayed by default$")
//    public void up_to_results_are_displayed_by_default(int count) {
//        assertThat(searchResults.resultList().size(), greaterThanOrEqualTo(count));
//    }
//
//    @And("^a See More link is displayed$")
//    public void a_See_More_link_is_displayed() {
//        assertThat(searchResults.seeMoreLink().isDisplayed().value(), equalTo(true));
//    }

    //@PUI-133
    @When("^a search for a facility is completed$")
    public void a_search_for_a_facility_is_completed() {
        assertThat(driver.getCurrentUrl(), containsString("facilities"));
    }


    @Then("^I will see records for facilities that contain a name$")
    public void I_will_see_records_for_facilities_that_contain_a_name() {
        for (FluentWebElement el : searchResults.resultList()) {
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
        // Given all of the results
        for (FluentWebElement el : searchResults.resultList()) {
            // Check that there are specialties
            if (el.spans(cssSelector(".ng-scope.ng-binding")).size() > 0) {
                // Loop through the specialties
                for (FluentWebElement spec : el.spans(cssSelector(".ng-scope.ng-binding"))) {
                    System.out.println(spec.getText().toString());
                    //assertThat(spec.div(cssSelector(".ng-scope.ng-binding")).getText().toString(), containsString("Result"));
                }
            }
        }
    }

    @And("^I will see a location address if it exists$")
    public void I_will_see_a_location_address_if_it_exists() {
        for (FluentWebElement el : searchResults.resultList()) {
            assertThat(el.span(cssSelector(".address.ng-binding")).isDisplayed().value(), equalTo(true));
        }
    }

    @And("^I will see a location phone number if it exists$")
    public void I_will_see_a_location_phone_number_if_it_exists() {
        for (FluentWebElement el : searchResults.resultList()) {
            assertThat(el.span(cssSelector(".phone.ng-scope.ng-binding")).isDisplayed().value(), equalTo(true));
        }
    }

    //@PUI-207
    @Then("^I will see a small map displaying the facilities$")
    public void I_will_see_a_small_map_displaying_the_facilities() {
        assertThat(searchResults.googleMap().isDisplayed(), equalTo(true));
    }


//    //@PUI-135
//    @And("^I select a facility from the search results$")
//    public void I_select_a_facility_from_the_search_results() {
//        searchResults.resultList().get(0).click();
//    }
//
//    @Then("^I will see all the specialties this facility supports$")
//    public void I_will_see_all_the_specialties_this_facility_supports() {
//        assertThat(profilePage.doctorSpecialtyList().isDisplayed().value(), equalTo(true));
//    }

}
