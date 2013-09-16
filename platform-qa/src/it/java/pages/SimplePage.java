package pages;

import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.By.cssSelector;

/**
 * For only checking local
 */
public class SimplePage extends BasePage {

    public SimplePage(WebDriver delegate) {
        super(delegate);
    }

    public SimplePage go() {
        get("http://localhost:8000/index.html");
        return this;
    }

    public SimplePage clickFacilityProfileLink() {
        link(cssSelector(".col-lg-12>a:nth-child(2)")).click();
        return this;
    }

    public SimplePage clickGlobalSearchBarLink() {
        link(cssSelector(".col-lg-12>a:nth-child(4)")).click();
        return this;
    }


}
