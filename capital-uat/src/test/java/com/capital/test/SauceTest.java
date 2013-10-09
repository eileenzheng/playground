package com.capital.test;

import com.capital.pages.SpecialtyTypeSearchPage;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SauceTest {

    private String theurl;

    @Parameters({"theurl"})
    @BeforeMethod
    public void setup(String theurl) {
        this.theurl = theurl;
    }

    @Test
    public void theTest() {
        Reporter.log("THE KEY " + System.getenv("SAUCE_API_KEY"), true);
        SpecialtyTypeSearchPage specialtySearch = new SpecialtyTypeSearchPage();

        specialtySearch.go(theurl);

        Reporter.log(specialtySearch.getCurrentUrl(), true);
    }
}
