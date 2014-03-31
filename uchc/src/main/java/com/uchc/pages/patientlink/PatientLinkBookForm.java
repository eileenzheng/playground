package com.uchc.pages.patientlink;

import com.uchc.pages.BasePage;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import static org.openqa.selenium.By.cssSelector;

public class PatientLinkBookForm extends BasePage {

	public FluentWebElement fname() {
        return input(cssSelector("#contact input[name=fname]"));
    }
	
	public FluentWebElement lname() {
        return input(cssSelector("#contact input[name=lname]"));
    }

    public FluentWebElement phone() {
        return input(cssSelector("#contact input[name=phone]"));
    }

    public FluentWebElement email() {
        return input(cssSelector("#contact input[name=email]"));
    }

    public FluentWebElement radioAny() {
        return input(cssSelector("#contact input[value=Any]"));
    }

    public FluentWebElement radioAm() {
        return input(cssSelector("#contact input[value=AM]"));
    }

    public FluentWebElement radioMidday() {
        return input(cssSelector("#contact input[value=Midday]"));
    }

    public FluentWebElement radioAfternoon() {
        return input(cssSelector("#contact input[value=Afternoon]"));
    }

    public FluentWebElement radioPM() {
        return input(cssSelector("#contact input[value=PM]"));
    }

    public FluentWebElement dropDownWhen() {
        return select(cssSelector("#contact select"));
    }
	
	public FluentWebElement insurance() {
        return input(cssSelector("#contact input[name=insurance]"));
    }

    public FluentWebElement submitButton() {
        return input(cssSelector("#contact input[value=Submit]"));
    }

    public FluentWebElement alert() {
        return div(cssSelector("#contact input[value=Submit]"));
    }
}
