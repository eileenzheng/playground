package com.capital.test;

import com.capital.pages.SpecialtyTypeSearchPage;
import org.junit.Test;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class SauceTest {

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
