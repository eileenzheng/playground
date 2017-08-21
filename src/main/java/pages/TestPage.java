package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TestPage extends BasePage {

    public WebElement luckyButton() {
        return webDriver().findElement(By.cssSelector("#gbqfbb"));
    }
}
