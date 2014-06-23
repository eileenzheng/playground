package com.uchc.pages;

import com.uchc.helpers.Constants;
import com.vitalsqa.listener.DriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.seleniumhq.selenium.fluent.FluentWebDriver;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.By.cssSelector;

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

    public void switchIframe(String css) {
        webDriver().switchTo().frame(webDriver().findElement(cssSelector(css)));
    }

    public String getPageSource() {
        int i=0;
        String source = webDriver().getPageSource();
        while (!source.contains("<body") && i<100) {
            source = webDriver().getPageSource();
            try {
                Thread.sleep(100);
                i++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return source;
    }

    public FluentWebElement getRandom(FluentWebElements list) {
        int rand = (int) Math.floor(Math.random() * (list.size() - 1));
        return list.get(rand);
    }

    public void selectDropDown(FluentWebElement el) {
        Select dropdown = new Select(el.getWebElement());
        int rand = (int) Math.floor(Math.random() * (el.options().size()-1));
        dropdown.selectByIndex(rand);
    }
}
