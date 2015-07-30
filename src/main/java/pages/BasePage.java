package pages;

import helper.Constants;
import listener.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.seleniumhq.selenium.fluent.FluentWebDriver;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import java.util.concurrent.TimeUnit;

public class BasePage extends FluentWebDriver{

    private final WebDriver webDriver;


    public BasePage() {
        super(DriverManager.getDriver());
    	this.webDriver = DriverManager.getDriver();
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


    public void setWindowSize(int width, int height) {
        Dimension dim = new Dimension(width,height);
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
}
