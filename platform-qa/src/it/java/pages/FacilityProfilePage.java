package pages;

import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.By.cssSelector;

/**
 * Created with IntelliJ IDEA.
 * User: sargenziano
 * Date: 9/12/13
 * Time: 3:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class FacilityProfilePage extends BasePage {

    public FacilityProfilePage(WebDriver delegate) {
        super(delegate);
    }

    public String getPageTitle() {
        return getTitle();
    }

    public SimplePage clickHomeLink() {
        link(cssSelector(".col-lg-12>a")).click();
        return new SimplePage(delegate);
    }
}
