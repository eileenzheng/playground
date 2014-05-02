package com.uchc.pages;

import org.seleniumhq.selenium.fluent.FluentWebElement;
import static org.openqa.selenium.By.cssSelector;

public class NavBar extends BasePage {

    public FluentWebElement navBarHome() {
        return link(cssSelector(".navbar-nav li:first-child a"));
    }

    public FluentWebElement navBarDoctors() {
        return link(cssSelector(".navbar-nav li:nth-child(2) a"));
    }

    public FluentWebElement navBarDentists() {
        return link(cssSelector(".navbar-nav li:nth-child(3) a"));
    }

    public FluentWebElement navBarPodiatrists() {
        return link(cssSelector(".navbar-nav li:nth-child(4) a"));
    }
}
