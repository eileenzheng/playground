package com.vitals.pages.patientlink;

import com.vitals.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import static org.openqa.selenium.By.cssSelector;

public class ModalIframe extends BasePage {

    public FluentWebElement closeButton() {
        return link(cssSelector(".modal.in .modal-header .close"));
    }
}