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
import com.core.pages.HCSCProfilePage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class HCSC_CostEstimatorStepDefs {

    protected WebDriver driver;
    private HCSCProfilePage profilePage;
    private Scenario scenario;


    @Before("@cost_estimator")
    public void setUp(Scenario scenario) throws MalformedURLException {
//        driver = new FirefoxDriver();
//        URL server = new URL("http://thvitdatadev01.mdx.med:4444/wd/hub");
//        DesiredCapabilities caps = DesiredCapabilities.firefox();
//
//        driver = new RemoteWebDriver(server, caps);
//
//        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//        driver.manage().window().maximize();
        this.driver = WebDriverSingleton.getInstance();

        profilePage = new HCSCProfilePage();

        this.scenario = scenario;

    }

//    @After({"@cost_estimator"})
//    public void breakDown() {
//        driver.quit();
//    }


    @Given("^I have searched doctors that perform \"([^\"]*)\"$")
    public void I_have_searched_doctors_that_perform(String arg1) {
        scenario.write("Running this against HCSC demo site");
        profilePage.go();
    }


    @When("^I select doctor \"([^\"]*)\" from the results$")
    public void I_select_doctor_from_the_results(String arg1) {
        assertThat("Url incorrect", driver.getCurrentUrl().contains("profile_professional_new_costs"), equalTo(true));
    }


    @Then("^I will see a cost estimate box for \"([^\"]*)\"$")
    public void I_will_see_a_cost_estimate_box_for(String arg1) {
        assertThat("Cost Estimate box was not visible", profilePage.costEstimateBoxIsVisible(), equalTo(true));
        assertThat(arg1 + " was not visible in the Cost Estimate box", profilePage.costEstimate(), containsString(arg1));
    }


    @And("^I will see a break down of what I owe vs the total cost$")
    public void I_will_see_a_break_down_of_what_I_owe_vs_the_total_cost() {
        assertThat("Breakdown was not visible", profilePage.yourEstimatedCostIsVisible(), equalTo(true));
        assertThat("Breakdown was not visible", profilePage.yourProcedureCostIsVisible(), equalTo(true));
    }
}
