package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class HomePage {

    private WebDriver driver;

    public HomePage (WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(name="q")
    private WebElement largeSearchBox;

    @FindBy(css="#body-search-box>div:nth-child(3)>input")
    private WebElement largeSearchButton;

    @FindBy(css="#looking_for_doctor")
    private WebElement selectPhysicianSearch;

    @FindBy(css="#looking_for_dentist")
    private WebElement selectDentistSearch;

    @FindBy(css="tbody>tr:nth-child(2)>td>.body-search-input-s")
    private WebElement findADocLastNameTextBox;

    @FindBy(css="tbody>tr:nth-child(3)>td>.body-search-input-s")
    private WebElement findADocCityStateZipTextBox;

    @FindBy(css="#doctorRange")
    private WebElement findADocDistanceDropDown;

    @FindBy (css="#doctorSpecialty")
    private WebElement findADocSpecialtyDropdown;

    @FindBy (css="tbody>tr:nth-child(6)>td>input")
    private WebElement findADocSearchButton;

    @FindBy(css=".main>.body-box-container:nth-child(9)>.body-box tbody>tr:nth-child(1) input")
    private WebElement nursingHomeNameTextBox;

    @FindBy(css=".main>.body-box-container:nth-child(9)>.body-box tbody>tr:nth-child(2) input")
    private WebElement nursingHomeCityStateZipTextBox;

    @FindBy(css=".main>.body-box-container:nth-child(9)>.body-box tbody>tr:nth-child(3) select")
    private WebElement nursingHomeDistanceDropDown;

    @FindBy(css=".main>.body-box-container:nth-child(9)>.body-box tbody>tr:nth-child(4) input")
    private WebElement nursingHomeSearchButton;

    @FindBy(css=".main>.body-box-container:nth-child(13)>.body-box tbody>tr:nth-child(1) input")
    private WebElement hospitalNameTextBox;

    @FindBy(css=".main>.body-box-container:nth-child(13)>.body-box tbody>tr:nth-child(2) input")
    private WebElement hospitalCityStateZipTextBox;

    @FindBy(css=".main>.body-box-container:nth-child(13)>.body-box tbody>tr:nth-child(3) select")
    private WebElement hospitalDistanceDropDown;

    @FindBy(css=".main>.body-box-container:nth-child(13)>.body-box tbody>tr:nth-child(4) input")
    private WebElement hospitalSearchButton;

    public HomePage selectPhysicianSearch() {
        selectPhysicianSearch.click();
        return this;
    }

    public HomePage selectDentistSearch() {
        selectDentistSearch.click();
        return this;
    }

    public HomePage enterFindADoctorLastName(String name) {
        findADocLastNameTextBox.sendKeys(name);
        return this;
    }

    public HomePage enterFindADoctorLocation(String location) {
        findADocCityStateZipTextBox.sendKeys(location);
        return this;
    }

    public HomePage selectFindADoctorDistance(int miles) {
        Select sel = new Select(findADocDistanceDropDown);

        switch(miles) {
            case 5:     sel.selectByIndex(0);
                break;
            case 10:    sel.selectByIndex(1);
                break;
            case 15:    sel.selectByIndex(2);
                break;
            case 25:    sel.selectByIndex(3);
                break;
            case 50:    sel.selectByIndex(4);
                break;
            case 100:   sel.selectByIndex(5);
                break;
            default:    throw new IllegalArgumentException("Miles must be one of the following: [5,10,15,25,50,100]");
        }

        return this;
    }

    public HomePage selectFindADoctorSpecialty(String spec) {
        Select sel = new Select(findADocSpecialtyDropdown);

        try {

            sel.selectByVisibleText(spec);

        } catch (NoSuchElementException e) {
            StringBuilder buf = new StringBuilder();

            List<WebElement> elms = findADocSpecialtyDropdown.findElements(By.tagName("option"));

            for (WebElement el : elms) {
                buf.append(el.getText()).append(", ");
            }

            throw new IllegalArgumentException("Specialty not in list possible specialties are:\n" + buf.toString());
        }

        return this;
    }

    public void clickFindADoctorSearchButton() {
        findADocSearchButton.click();
    }


    public HomePage enterProviderSearch(String provider) {
        largeSearchBox.sendKeys(provider);
        return this;
    }

    public void clickLargeSearchButton() {
        largeSearchButton.click();
    }


    public HomePage enterNursingHomeName(String name) {
        nursingHomeNameTextBox.sendKeys(name);
        return this;
    }

    public HomePage enterNursingHomeLocation(String location) {
        nursingHomeCityStateZipTextBox.sendKeys(location);
        return this;
    }

    public HomePage selectNursingHomeDistance(int miles) {
        Select sel = new Select(nursingHomeDistanceDropDown);

        switch(miles) {
            case 5:     sel.selectByIndex(0);
                break;
            case 10:    sel.selectByIndex(1);
                break;
            case 25:    sel.selectByIndex(2);
                break;
            case 50:    sel.selectByIndex(3);
                break;
            case 100:   sel.selectByIndex(4);
                break;
            default:    throw new IllegalArgumentException("Miles must be one of the following: [5,10,25,50,100]");
        }

        return this;
    }

    public void clickNursingHomeSearch() {
        nursingHomeSearchButton.click();
    }

    public HomePage enterHospitalName(String name) {
        hospitalNameTextBox.sendKeys(name);
        return this;
    }

    public HomePage enterHospitalLocation(String location) {
        hospitalCityStateZipTextBox.sendKeys(location);
        return this;
    }

    public HomePage selectHospitalDistance(int miles) {
        Select sel = new Select(hospitalDistanceDropDown);

        switch(miles) {
            case 5:     sel.selectByIndex(0);
                break;
            case 10:    sel.selectByIndex(1);
                break;
            case 25:    sel.selectByIndex(2);
                break;
            case 50:    sel.selectByIndex(3);
                break;
            case 100:   sel.selectByIndex(4);
                break;
            default:    throw new IllegalArgumentException("Miles must be one of the following: [5,10,25,50,100]");
        }

        return this;
    }

    public void clickHospitalSearch() {
        hospitalSearchButton.click();
    }

}
