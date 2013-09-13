package pages;

import org.openqa.selenium.WebDriver;

/**
 * Created with IntelliJ IDEA.
 * User: sargenziano
 * Date: 9/12/13
 * Time: 3:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class GlobalSearchBar extends BasePage {

    public GlobalSearchBar(WebDriver delegate) {
        super(delegate);
    }

    public String getPageTitle() {
        return getTitle();
    }
}
