package com.capital.test;

import com.capital.pages.SpecialtyTypeSearchPage;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SauceTestTwo {

    private String url;

    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) {
        this.url = url;
    }

    @Test
    public void theTest() {
        SpecialtyTypeSearchPage specialtySearch = new SpecialtyTypeSearchPage();

        specialtySearch.go(url);

        Reporter.log(specialtySearch.getCurrentUrl(), true);
    }
}
