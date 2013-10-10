package com.capital.test;

import com.capital.pages.SpecialtyTypeSearchPage;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SauceTest {

    private String url;

    //@Parameters({"url"})
    @BeforeSuite
    public void setup() {
        this.url = "https://providerfinder.capbluecross.com";
    }

    @Test
    public void theTest() {
        SpecialtyTypeSearchPage specialtySearch = new SpecialtyTypeSearchPage();

        specialtySearch.go(url);

        Reporter.log(specialtySearch.getCurrentUrl(), true);
    }
}
