package com.vitals.pages;

import com.vitals.helpers.Constants;
import com.vitalsqa.listener.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.seleniumhq.selenium.fluent.FluentWebDriver;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import java.util.concurrent.TimeUnit;
import static org.openqa.selenium.By.cssSelector;

public class BasePage extends FluentWebDriver{

    private final WebDriver webDriver;


    public BasePage() {
        super(DriverManager.getDriver());
    	this.webDriver = DriverManager.getDriver();
        this.setWindowSize();
        this.setImplicitWait(Constants.SELENIUM_IMPLICIT_WAIT);
    }


    public WebDriver webDriver() {
        return webDriver;
    }

    public void get(String url) {
        webDriver().get(url);
    }

    public String getTitle() {
        return webDriver().getTitle();
    }

    public String getCurrentUrl() {
        return webDriver().getCurrentUrl();
    }


    public void setWindowSize() {
        Dimension dim = new Dimension(1024,768);
        if (webDriver().manage().window().getSize() != dim) {
            webDriver().manage().window().setSize(dim);
        }
    }

    public void setImplicitWait(int sec) {
        webDriver().manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
    }

    public void waitUntilInvisible(final By by, final Integer seconds) {
        WebDriverWait wait = new WebDriverWait(webDriver(),seconds);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public void waitUntilVisible(FluentWebElement el, Integer seconds) {
        WebDriverWait wait = new WebDriverWait(webDriver(),seconds);
        wait.until(ExpectedConditions.visibilityOf(el.getWebElement()));
    }

    public FluentWebElement getRandom(FluentWebElements list) {
        int rand = (int) Math.floor(Math.random() * (list.size() - 1));
        return list.get(rand);
    }

    public void deleteCookies() {
        webDriver().manage().deleteAllCookies();
    }

    public void acceptAlertIfPresent() {
        try {
            Alert alert = webDriver().switchTo().alert();
            alert.accept();

        } catch (NoAlertPresentException e) {
            // do nothing
        }
    }

    public void switchIframe(String css) {
        webDriver().switchTo().frame(webDriver().findElement(cssSelector(css)));
    }

    public void switchWindow(String window) {
        webDriver().switchTo().window(window);
    }

    public String getMainWindow() {
        return webDriver().getWindowHandle();
    }

    public void selectDropDown(FluentWebElement el, String text) {
        Select dropdown = new Select(el.getWebElement());
        dropdown.selectByVisibleText(text);
    }

    public void waitForJQuery() {
        int counter1=0, counter2=0;
        if ((Boolean) ((JavascriptExecutor) webDriver()).executeScript("return jQuery.active==1")) {
            do {
                if (counter1 > 600) // just so it's not stuck in while loop forever in case something goes wrong
                    break;
                else
                    counter1++;

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // do nothing
                }
            } while ((Boolean) ((JavascriptExecutor) webDriver()).executeScript("return jQuery.active==1"));
        }
        else {
            do {
                if (counter2 > 1200)
                    break;
                else
                    counter2++;

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e2) {
                    // do nothing
                }
            } while ((Boolean) ((JavascriptExecutor) webDriver()).executeScript("return jQuery.active==0"));
            waitForJQuery();
        }
    }

    public String getPageSource() {
        return webDriver().getPageSource();
    }

    public String getStyle(FluentWebElement element, String property) {
        return element.getWebElement().getCssValue(property);
    }

    public FluentWebElement divMaincontent() {
        return div(cssSelector(".main-content"));
    }
}
