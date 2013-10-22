package com.uchc.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.uchc.DriverManager;
import com.uchc.pages.HomePage;

/**
 * UCHC Test
 */
public class SearchTest {

    private WebDriver driver;

    @Parameters({"url"})
    @Test
    public void simpleTest(String url) {
        driver = DriverManager.getDriver();

        driver.get(url);


        HomePage homePage = PageFactory.initElements(driver, HomePage.class);

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
