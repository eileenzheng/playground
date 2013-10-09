package com.capital.test;

import com.capital.pages.SpecialtyTypeSearchPage;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SauceTest {

    private String url;

    @Parameters({"url"})
    @BeforeClass
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
