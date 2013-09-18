//package com.capital.test;
//
//import cucumber.api.java.After;
//import cucumber.api.java.Before;
//import cucumber.api.java.en.Given;
//import cucumber.api.java.en.Then;
//import cucumber.api.java.en.When;
//import org.junit.Assert;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import pages.FacilityProfilePage;
//import pages.GlobalSearchBar;
//import pages.SimplePage;
//
//public class DansPageStepDefs {
//
//    protected FirefoxDriver driver;
//    private SimplePage simplePage;
//
//    @Before
//    public void setUp() {
//        driver = new FirefoxDriver();
//
//        simplePage = new SimplePage(driver);
//    }
//
//    @After
//    public void breakDown() {
//        driver.quit();
//        driver.close();
//    }
//
//    @Given("^i have made it to the landing page$")
//    public void i_have_made_it_to_the_landing_page() throws Throwable {
//        simplePage.go();
//    }
//
//    @When("^i click on facility profile$")
//    public void i_click_on_facility_profile() throws Throwable {
//        simplePage.clickFacilityProfileLink();
//    }
//
//    @Then("^i will see the profile page$")
//    public void i_will_see_the_profile_page() throws Throwable {
//        new FacilityProfilePage(driver) {{
//            Assert.assertEquals(getPageTitle(),"Facility Profile");
//        }};
//    }
//
//    @When("^i click on the global search bar link$")
//    public void i_click_on_the_global_search_bar_link() throws Throwable {
//        simplePage.clickGlobalSearchBarLink();
//    }
//
//    @Then("^i will see the global search bar$")
//    public void i_will_see_the_global_search_bar() throws Throwable {
//        new GlobalSearchBar(driver) {{
//            Assert.assertEquals(getPageTitle(),"Global Seafdfdrch Bar");
//        }};
//
//    }
//}
