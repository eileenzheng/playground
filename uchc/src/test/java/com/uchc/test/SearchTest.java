package com.uchc.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.uchc.pages.HomePage;
import com.uchc.runners.LocalTestRunner;

/**
 * UCHC Test
 */
public class SearchTest extends LocalTestRunner {

    private WebDriver driver;

    @Parameters({"domain","user","pw"})
    @Test
    public void simpleTest(String domain,@Optional("")String user, @Optional("")String pw) {
        driver = getDriver();

        if (user.equals("") && pw.equals("")) {
            driver.get(getUrl(domain));
        } else {
            driver.get(getHttpSecureUrl(user,pw,domain));
        }


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
