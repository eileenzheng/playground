package com.vitals.pages.patientlink;

import com.vitals.helpers.Constants;
import com.vitals.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import static org.openqa.selenium.By.cssSelector;

public class ModalEmail extends BasePage {

    public FluentWebElement fname() {
        return input(cssSelector(".modal.in #BookingFirstName"));
    }

    public FluentWebElement lname() {
        return input(cssSelector(".modal.in #BookingLastName"));
    }

    public FluentWebElement phone() {
        return input(cssSelector(".modal.in #BookingPhone"));
    }

    public FluentWebElement email() {
        return input(cssSelector(".modal.in #BookingEmail"));
    }

    public FluentWebElement radioAny() {
        return input(cssSelector(".modal.in [value=any]"));
    }

    public FluentWebElement radioAm() {
        return input(cssSelector(".modal.in [value=am"));
    }

    public FluentWebElement radioMidday() {
        return input(cssSelector(".modal.in [value=midday]"));
    }

    public FluentWebElement radioAfternoon() {
        return input(cssSelector(".modal.in [value=afternoon]"));
    }

    public FluentWebElement radioPm() {
        return input(cssSelector(".modal.in [value=pm]"));
    }

    public FluentWebElement dropDownWhen() {
        return select(cssSelector(".modal.in #BookingApptWhen"));
    }

    public FluentWebElement insurance() {
        return input(cssSelector(".modal.in #BookingInsurance"));
    }

    public FluentWebElement submitButton() {
        return button(cssSelector(".modal.in .btn.btn-primary.submit-modal-form"));
    }

    public FluentWebElement alert() {
        return div(cssSelector(".modal.in .notice .alert"));
    }

    public FluentWebElement closeButton() {
        return link(cssSelector(".modal.in .modal-header .close"));
    }

	public void submit() {
		submitButton().click();
        waitUntilVisible(alert(), Constants.SELENIUM_EXPLICIT_WAIT);
    }
}
