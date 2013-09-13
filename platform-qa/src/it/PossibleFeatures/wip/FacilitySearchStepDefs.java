package test.wip;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.hamcrest.Matchers;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.HomePage;
import pages.ProfilePage;
import pages.SearchResults;

import static org.hamcrest.MatcherAssert.assertThat;

public class FacilitySearchStepDefs {

    protected FirefoxDriver driver;
    private HomePage homePage;
    private SearchResults searchResults;
    private ProfilePage profilePage;

    @Before
    public void setUp() {
        driver = new FirefoxDriver();

        homePage = new HomePage(driver);
        searchResults = new SearchResults(driver);
        profilePage = new ProfilePage(driver);

    }

    @Given("^I am on the home page$")
    public void I_am_on_the_home_page() {
        driver.get("http://demo.vitalschoice.com/hcsc/");
    }

    @When("^I search for a provider in \"([^\"]*)\"$")
    public void I_search_for_a_provider_in(String search) {
        homePage.searchBar().sendKeys(search);
        homePage.searchButton().click();
    }


    @Then("^display a list of results$")
    public void display_a_list_of_results() {
        assertThat(searchResults.resultList().size(), Matchers.greaterThan(0));
    }

    @And("^confirm facility name, location name, location specialties, location address, and location phone number are visible$")
    public void confirm_facility_name_location_name_location_specialties_location_address_and_location_phone_number_are_visible() {
        //assert something here
    }
}
