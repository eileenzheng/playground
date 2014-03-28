package com.uchc.pages;

import com.uchc.helpers.Constants;
import com.vitalsqa.listener.DriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.seleniumhq.selenium.fluent.FluentWebDriver;
import org.seleniumhq.selenium.fluent.FluentWebElement;

import java.util.concurrent.TimeUnit;

public class BasePage extends FluentWebDriver {

    private final WebDriver webDriver;

    public BasePage() {
        super(DriverManager.getDriver());
        this.webDriver = DriverManager.getDriver();
        this.setWindowSize();
        this.setImplicitWait(Constants.SELENIUM_IMPLICIT_WAIT);
    }

    public void setWindowSize() {
        Dimension dim = new Dimension(1024,768);
        if (webDriver().manage().window().getSize() != dim) {
            webDriver().manage().window().setSize(dim);
        }
    }

    public WebDriver webDriver() {
        return webDriver;
    }

    public void get(String url) {
        webDriver().get(url);
    }

    public String getCurrentUrl() {
        return webDriver().getCurrentUrl();
    }

    public void setImplicitWait(int sec) {
        webDriver().manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
    }

    public void waitUntilVisible(FluentWebElement el, Integer seconds) {
        WebDriverWait wait = new WebDriverWait(webDriver(),seconds);
        wait.until(ExpectedConditions.visibilityOf(el.getWebElement()));
    }
}
