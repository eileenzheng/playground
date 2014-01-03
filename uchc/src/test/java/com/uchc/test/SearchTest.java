package com.uchc.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.uchc.DriverManager;
import com.uchc.pages.HomePage;

/**
 * UCHC Test
 */
public class SearchTest {

    WebDriver driver;

    private String url;

    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) {
        this.url = url;

    }

    @AfterMethod
    public void shutdown() {
    }

    @Test
    public void simpleSearchTest() {

        DriverManager.getDriver().get(url);

        HomePage homePage = PageFactory.initElements(DriverManager.getDriver(), HomePage.class);

        homePage.selectDentistSearch();
        homePage.selectPhysicianSearch();

        homePage.enterFindADoctorLastName("Smith");
        homePage.enterFindADoctorLocation("10001");
        homePage.selectFindADoctorSpecialty("Hand Surgery");

        homePage.clickFindADoctorSearchButton();
//        homePage.clickSearchButton();
//
//        System.out.println(driver.findElement(By.cssSelector("#content>div>h1")).getText());

    }

}
