package com.vitals.pages.sitemap;

import com.vitals.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import static org.openqa.selenium.By.cssSelector;

public class SpecialtiesPage extends BasePage {

    public FluentWebElements specialties() {
        return links(cssSelector(".specialty li>a"));
    }
}
