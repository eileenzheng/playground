package com.core.test;

import com.core.WebDriverSingleton;
import com.core.pages.ProfilePage;
import cucumber.api.DataTable;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.seleniumhq.selenium.fluent.FluentWebElement;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.openqa.selenium.By.cssSelector;

public class ProfileStepDefs {

    private ProfilePage profilePage;
    private FluentWebElement el;

    @Before()
    public void setUp() {
        profilePage = new ProfilePage();
        WebDriverSingleton.getDriver().manage().window().maximize();
    }

    @Given("^I am viewing a facility with id (\\d+)$")
    public void I_am_viewing_a_facility_with_id(int id) {
        profilePage.get("http://qa.vitalschoice.com/profile/facility?id=" + id);
    }

    @Given("^I am viewing a professional with id (\\d+)$")
    public void I_am_viewing_a_professional_with_id(int id) {
        profilePage.go("http://qa.vitalschoice.com/profile/professional?id=" + id);
    }

    @Given("^I am viewing a facility with identifiers$")
    public void I_am_viewing_a_facility_with_identifiers() {
        assertThat("Identifier module was not visible on: " + profilePage.getCurrentUrl(),
                profilePage.identifiersModuleIsPresent(),is(true));
        el = profilePage.identifiersModule();
    }

    @Given("^a (professional|facility) has more than (\\d+) awards$")
    public void a_provider_has_more_than_awards(String type, int awdCount) throws Throwable {

    }

    @Given("^a facility has less than (\\d+) awards$")
    public void a_facility_has_less_than_awards(int arg1) throws Throwable {
        assertThat(profilePage.awardsModuleCompressedCount(),lessThanOrEqualTo(arg1));
    }

    @Given("^the amenity module is visible$")
    public void the_amenity_module_is_visible() throws Throwable {
        assertThat("Amenities Module is not present on " + profilePage.getCurrentUrl(),
                profilePage.amenitiesModuleIsPresent(), is(true));
        el = profilePage.amenitiesModule();
    }

    @Then("^I will see the awards module$")
    public void I_will_see_the_provider_awards_module() {
        assertThat("Awards module was not visible",profilePage.awardsModuleIsPresent(), is(true));
        el = profilePage.awardsModule();
    }

    @Then("^I will see up to (\\d+) identifiers$")
    public void I_will_see_up_to_identifiers(int arg1) {
        assertThat("More than " + arg1 + " identifiers were shown.", profilePage.identifierList().size(), lessThanOrEqualTo(arg1));
    }

    @Then("^I will see the facility name$")
    public void I_will_see_the_facility_name() {
        assertThat("Profile name wasn't visible on: " + profilePage.getCurrentUrl(),
                profilePage.profileNameIsPresent(), is(true));
    }

    @Then("^up to (\\d+) awards will display on the module$")
    public void up_to_awards_will_display_on_the_module(int arg1) {
        assertThat(profilePage.awardsModuleCompressedCount(),is(lessThanOrEqualTo(arg1)));
    }

    @Then("^I will see a link that reads \"([^\"]*)\"$")
    public void I_will_see_a_link_that_reads(String arg1) {
        assertThat(profilePage.getViewMoreLinkText(),equalTo(arg1));
    }

    @When("^I click See Awards and Recognitions$")
    public void I_click_See_Awards_and_Recognitions() {
        profilePage.viewAwardsExpanded();
    }

    @And("^it will be displayed as \\[identifier label\\]\\[colon\\]\\[identifier\\]$")
    public void it_will_be_displayed_as_identifier_label_colon_identifier() {
        for (FluentWebElement el : profilePage.identifierList()) {
            String idText = el.getText().toString().trim();
            assertThat("Identifier: " + idText + "didn't match pattern.",idText.matches("^\\w.+:\\s?\\d.+$"),is(true));
        }
    }

    @And("^I will see the facility address$")
    public void I_will_see_the_facility_address() {
        assertThat("Profile address wasn't visible on: " + profilePage.getCurrentUrl(),
                profilePage.profileAddressIsPresent(), is(true));
    }

    @And("^I will see a get directions link$")
    public void I_will_see_a_get_directions_link() {
        assertThat("Profile directions link wasn't visible on: " + profilePage.getCurrentUrl(),
                profilePage.profileMapAndDirectionsLinkIsPresent(), is(true));
    }

    @And("^I will see a facility phone number$")
    public void I_will_see_a_facility_phone_number() {
        assertThat("Profile phone number wasn't visible on: " + profilePage.getCurrentUrl(),
                profilePage.profilePhoneNumberIsPresent(), is(true));
    }

    @And("^I will see the tier network name$")
    public void I_will_see_the_tier_network_name() {
        assertThat("Profile tier name wasn't visible on: " + profilePage.getCurrentUrl(),
                profilePage.profileTierNameIsPresent(), is(true));
    }

    @And("^I will see a photo$")
    public void I_will_see_a_photo() {
        assertThat("Profile photo wasn't visible on: " + profilePage.getCurrentUrl(),
                profilePage.profilePhotoIsPresent(), is(true));

        assertThat("Photo dimensions changed",profilePage.profileImageHeightWidth(),equalTo("168,168"));
    }

//    @And("^the header displays as \"([^\"]*)\"$")
//    public void the_header_displays_as(String arg1) {
//        String title = profilePage.getModuleTitleText(profilePage.awardsModule());
//        assertThat("Expected " + arg1 + " to be the module title. Title is currently: " + title, title, equalTo(arg1));
//    }

    @And("^I will see the award name$")
    public void I_will_see_the_award_name() {
        for (FluentWebElement el : profilePage.awardsList()) {
            assertThat("An award is missing its title on this page: " + profilePage.getCurrentUrl(),
                    profilePage.awardNameIsPresent(el), is(true));
        }
    }

    @And("^the (professional|facility) has at least (\\d+) award$")
    public void the_professional_has_at_least_award(String type, int awdCount) throws Throwable {

    }

    @Then("^I will see the module title is \"([^\"]*)\"$")
    public void I_will_see_the_module_title_is(String arg1) throws Throwable {
        assertThat(profilePage.getModuleTitleText(el),equalTo(arg1));
    }

    @And("^I will see \"([^\"]*)\"$")
    public void I_will_see(String arg1) throws Throwable {
        Boolean flag = false;
        for (FluentWebElement fl : profilePage.amenitiesList()) {
            if (profilePage.amenityName(fl).equals(arg1)) flag = true;
            if (flag) break;
        }
        assertThat("Amenity '" + arg1 + "' was not found on " + profilePage.getCurrentUrl(),flag,is(true));
    }

    @And("^I will see an amenity icon to the left of the name$")
    public void I_will_see_an_amenity_icon_to_the_left_of_the_name() throws Throwable {
        for (FluentWebElement fl : profilePage.amenitiesList()) {
            assertThat("Amenity icon missing for " + profilePage.amenityName(fl) + " found on " + profilePage.getCurrentUrl(),
                    profilePage.amenityIconIsPresent(fl),is(true));
        }
    }

    @Given("^the specialties module is visible$")
    public void the_specialties_module_is_visible() throws Throwable {
        assertThat("Specialties module was not present on " + profilePage.getCurrentUrl(),
            profilePage.specialtiesModuleIsPresent(), is(true));
        el = profilePage.specialtiesModule();
    }

    @Given("^the languages module is visible$")
    public void the_languages_module_is_visible() throws Throwable {
        assertThat("Languages module was not present on " + profilePage.getCurrentUrl(),
                profilePage.languagesModuleIsPresent(), is(true));
        el = profilePage.languagesModule();
    }

    @And("^I will see a Languages Spoken by Staff label$")
    public void I_will_see_a_Languages_Spoken_by_Staff_label() throws Throwable {
        assertThat(profilePage.languagesSpokenByStaffTitle(),equalTo("Languages Spoken by Staff"));
    }

    @And("^I will see up to (\\d+) languages$")
    public void I_will_see_up_to_languages(int arg1) throws Throwable {
        assertThat(profilePage.languageSpokenByStaffVisibleCount(),lessThanOrEqualTo(arg1));
    }

    @And("^I will see \"(.+)\" as the languages$")
    public void I_will_see_the_following_languages(List<String> languages) throws Throwable {

    }

    @Given("^the affiliations module is visible$")
    public void the_affiliations_module_is_visible() throws Throwable {
        assertThat("Affiliations module was not present on " + profilePage.getCurrentUrl(),
                profilePage.affiliationsModuleIsPresent(),is(true));
        el = profilePage.affiliationsModule();
    }

    @And("^I will see a module link that reads \"([^\"]*)\"$")
    public void I_will_see_a_module_link_that_reads(String arg1) throws Throwable {
        assertThat(profilePage.affiliationsModule().span(cssSelector(".show-more>a>span")).getText().toString().trim(),
                equalTo(arg1));
    }

    @And("^I will see a subheadings called \"([^\"]*)\"$")
    public void I_will_see_a_subheadings_called(List<String> subheading) throws Throwable {
        // Medical Group Affiliations
        assertThat("Affiliations subheading '" + subheading.get(0) + "' was not found on page " + profilePage.getCurrentUrl(),
                profilePage.affiliationsModule().div(cssSelector(".panel-heading.first>.panel-title")).getText().toString().trim(),
                equalTo(subheading.get(0)));
        // Hospital Affiliations
        assertThat("Affiliations subheading '" + subheading.get(1) + "' was not found on page " + profilePage.getCurrentUrl(),
                profilePage.affiliationsModule().div(cssSelector(".panel-heading.repeat>.panel-title")).getText().toString().trim(),
                equalTo(subheading.get(1)));
    }


    @Then("^I will see the following hospital affiliation \"([^\"]*)\"$")
    public void I_will_see_the_following_hospital_affiliation(String afflName) throws Throwable {
        assertThat(profilePage.hospitalAffiliationsList().contains(afflName), is(true));
    }

    @Given("^the professional has multiple locations$")
    public void the_professional_has_multiple_locations() throws Throwable {
        assertThat("Profile does not have multiple locations: " + profilePage.getCurrentUrl()
                , profilePage.multipleLocationsLinkIsPresent(), is(true));
    }

    @Then("^I will see the professional has \"([^\"]*)\" additional locations$")
    public void I_will_see_the_professional_has_additional_locations(String num) throws Throwable {
        assertThat("Expected " + num + " location(s) on page " + profilePage.getCurrentUrl(),
                profilePage.multipleLocationsLink().getText().toString().trim(),
                containsString(num));
    }

    @Then("^I will see a numbered pin$")
    public void I_will_see_a_numbered_pin() throws Throwable {
        profilePage.multipleLocationsLink().click();
        for (FluentWebElement fl : profilePage.locationsList()) {
            assertThat("Pin was not visible on " + profilePage.getCurrentUrl(),
                    profilePage.locationPin(fl).isDisplayed().value(),is(true));
        }
    }

    @And("^the location address$")
    public void the_location_address() throws Throwable {
        for (FluentWebElement fl : profilePage.locationsList()) {
            assertThat("Location address was not visible on " + profilePage.getCurrentUrl(),
                    profilePage.locationAddress(fl).isDisplayed().value(),is(true));
        }
    }

    @And("^a link to view profile at that location$")
    public void a_link_to_view_profile_at_that_location() throws Throwable {
        for (FluentWebElement fl : profilePage.locationsList()) {
            assertThat("Address link was not visible on " + profilePage.getCurrentUrl(),
                    profilePage.locationViewProfileAtLocationLink(fl).isDisplayed().value(),is(true));
        }
    }
}
