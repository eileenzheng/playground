package com.core.pages;

import static org.openqa.selenium.By.cssSelector;

public class FacilityProfilePage extends BasePage {

    public FacilityProfilePage() {

    }

    public String getPageTitle() {
        return getTitle();
    }

    public SimplePage clickHomeLink() {
        link(cssSelector(".col-lg-12>a")).click();
        return new SimplePage();
    }
}
