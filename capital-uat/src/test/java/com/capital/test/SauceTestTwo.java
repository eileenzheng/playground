package com.capital.test;

import com.capital.pages.SpecialtyTypeSearchPage;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class SauceTestTwo {

    @Test
    public void theTest() {
        Reporter.log("THE KEY " + System.getenv("SAUCE_API_KEY"), true);
        SpecialtyTypeSearchPage specialtySearch = new SpecialtyTypeSearchPage();

        specialtySearch.go("https://providerfinder.capbluecross.com");

        Reporter.log(specialtySearch.getCurrentUrl(), true);
    }
}
