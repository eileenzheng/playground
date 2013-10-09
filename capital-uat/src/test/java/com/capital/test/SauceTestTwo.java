package com.capital.test;

import com.capital.pages.SpecialtyTypeSearchPage;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SauceTestTwo {

    private String url;

    @Parameters({"url"})
    @BeforeClass
    public void setup(String url) {
        this.url = url;
    }

    @Test
    public void theTest() {
        Reporter.log("THE KEY " + System.getenv("SAUCE_API_KEY"), true);
        SpecialtyTypeSearchPage specialtySearch = new SpecialtyTypeSearchPage();

        specialtySearch.go(url);

        Reporter.log(specialtySearch.getCurrentUrl(), true);
    }
}
