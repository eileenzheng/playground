package pages;

import org.seleniumhq.selenium.fluent.FluentWebElement;
import static org.openqa.selenium.By.cssSelector;

public class HomePage extends BasePage {

    public FluentWebElement homePhoto() {
        return img(cssSelector("#hplogo"));
    }
}
