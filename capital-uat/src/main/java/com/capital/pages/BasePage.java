package com.capital.pages;

import com.capital.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.Mouse;
import org.seleniumhq.selenium.fluent.FluentWebDriver;

import java.util.List;
import java.util.Set;


public abstract class BasePage extends FluentWebDriver implements WebDriver, HasInputDevices, JavascriptExecutor, HasCapabilities {

    private WebDriver webDriver;

    public BasePage(){
        super(DriverManager.getDriver());
        this.webDriver = DriverManager.getDriver();
    }

    public void get(String url) {
        webDriver().get(url);
    }

    public String getCurrentUrl() {
        return webDriver().getCurrentUrl();
    }

    public String getTitle() {
        return webDriver().getTitle();
    }

    public List<WebElement> findElements(By by) {
        return webDriver().findElements(by);
    }

    public WebElement findElement(By by) {
        return webDriver().findElement(by);
    }

    public String getPageSource() {
        return webDriver().getPageSource();
    }

    public void close() {
        webDriver().close();
    }

    public void quit() {
        webDriver().quit();
    }

    public Set<String> getWindowHandles() {
        return webDriver().getWindowHandles();
    }

    public String getWindowHandle() {
        return webDriver().getWindowHandle();
    }

    public TargetLocator switchTo() {
        return webDriver().switchTo();
    }

    public Navigation navigate() {
        return webDriver().navigate();
    }

    public Options manage() {
        return webDriver().manage();
    }

    // From HasInputDevices

    public Keyboard getKeyboard() {
        return ((HasInputDevices) webDriver()).getKeyboard();
    }

    public Mouse getMouse() {
        return ((HasInputDevices) webDriver()).getMouse();
    }

    // From JavascriptExecutor

    public Object executeScript(String s, Object... args) {
        return ((JavascriptExecutor) webDriver()).executeScript(s, args);
    }

    public Object executeAsyncScript(String s, Object... args) {
        return ((JavascriptExecutor) webDriver()).executeAsyncScript(s, args);
    }

    // From HasCapabilities

    public Capabilities getCapabilities() {
        return ((HasCapabilities) webDriver()).getCapabilities();
    }

    protected WebDriver webDriver() {
        return webDriver;
    }


}
