package com.capital.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.seleniumhq.selenium.fluent.FluentSelect;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.testng.Assert;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;

public class ResultsPageHeader extends BasePage {

    public ResultsPageHeader(WebDriver delegate) {
        super(delegate);
    }

    public FluentWebElement maleCheckBox() {
        return input(cssSelector(".seven>.row>input:nth-child(3)"));
    }

    public FluentWebElement femaleCheckBox() {
        return input(cssSelector(".seven>.row>input:nth-child(4)"));
    }

    private By searchRadiusDropDownLocator() {
        return cssSelector(".five>.row:nth-child(1)>select");
    }
    public FluentWebElement searchRadiusDropDown() {
        return select(searchRadiusDropDownLocator());
    }

    public FluentWebElement boardCertifiedCheckBox() {
        return input(cssSelector(".five>.row:nth-child(2)>input"));
    }

    private By languageDropdownLocator() {
        return cssSelector(".five>.row:nth-child(2)>input");
    }

    public FluentWebElement languageDropdown() {
        return select(languageDropdownLocator());
    }

    public FluentWebElement acceptingNewPatientsCheckBox() {
        return input(cssSelector(".three>.row:nth-child(2)>input"));
    }

    public FluentWebElement handicapAccessibleCheckBox() {
        return input(cssSelector(".three>.row:nth-child(3)>input"));
    }

    public FluentWebElement ePrescriberCheckBox() {
        return input(cssSelector(".three>.row:nth-child(4)>input"));
    }

    public FluentWebElement eveningHoursCheckBox() {
        return input(cssSelector(".three>.row:nth-child(5)>input"));
    }

    public FluentWebElement weekendHoursCheckBox() {
        return input(cssSelector(".three>.row:nth-child(6)>input"));
    }

    private By hospitalAffiliationDropDownLocator() {
        return cssSelector(".three>.row:nth-child(8)>select");
    }

    public FluentWebElement hospitalAffiliationDropDown() {
        return select(hospitalAffiliationDropDownLocator());
    }

    public FluentWebElement ACACertifiedCheckBox() {
        return input(cssSelector(".four>.row:nth-child(1)>input"));
    }

    public FluentWebElement bluePhysicianRecognitionCheckBox() {
        return input(cssSelector(".four>.row:nth-child(2)>input"));
    }

    public FluentWebElement closeButton() {
        return link(id("filterSearchClose"));
    }

    public FluentWebElement filterSearchButton() {
        return input(cssSelector(".form-inline div:nth-child(2) .btn"));
    }

    public FluentWebElement filterYourSearchButton() {
        return link(id("filterSearch"));
    }

    public FluentWebElement resetButton() {
        return link(cssSelector(".two .btn.btn-mini"));
    }

    public FluentWebElement redoSearchButton() {
        return link(cssSelector(".row>.btn.btn-mini.btn-danger"));
    }

    //Facility Filters
    private By specialDropDownLocator() {
        return cssSelector(".row>.six.columns:nth-child(1)>.row>select");
    }
    public FluentWebElement specialtyDropDown() {
        return select(specialDropDownLocator());
    }

    private By searchRadiusLocator() {
        return cssSelector(".row>.six.columns:nth-child(2)>.row>select");
    }

    public FluentWebElement searchRadius() {
        return select(searchRadiusLocator());
    }

    public FluentWebElement vaccinesAdministeredCheckBox() {
        return input(cssSelector(".five .six.columns .row>input"));
    }

    public FluentWebElement BDBariatricSurgeryCheckBox() {
        return div(cssSelector(".three>.row:nth-child(3)"));
    }

    public FluentWebElement BDCardiacCareCheckBox() {
        return div(cssSelector(".three>.row:nth-child(4)"));
    }

    public FluentWebElement BDKneeHipCheckBox() {
        return div(cssSelector(".three>.row:nth-child(5)"));
    }

    public FluentWebElement BDSpineSurgeryCheckBox() {
        return div(cssSelector(".three>.row:nth-child(6)"));
    }

    public FluentWebElement BDTransplantsCheckBox() {
        return div(cssSelector(".three>.row:nth-child(7)"));
    }

    public FluentWebElement BDComplexCancersCheckBox() {
        return div(cssSelector(".three>.row:nth-child(8)"));
    }

    public FluentWebElement QRHeartAttackTreatmentCheckBox() {
        return input(cssSelector(".four>.row:nth-child(2)>.six:nth-child(1) .row>input:nth-child(3)"));
    }

    public FluentWebElement QRHeartFailureTreatmentCheckBox() {
        return input(cssSelector(".four>.row:nth-child(2)>.six:nth-child(1) .row>input:nth-child(5)"));
    }

    public FluentWebElement QROverallMortalityRateCheckBox() {
        return input(cssSelector(".four>.row:nth-child(3)>.six>.row>input:nth-child(3)"));
    }

    public FluentWebElement QRInfantMortalityRateCheckBox() {
        return input(cssSelector(".four>.row:nth-child(3)>.six>.row>input:nth-child(5)"));
    }

    public FluentWebElement QRCommunicationCheckBox() {
        return input(cssSelector(".four>.row:nth-child(2)>.six:nth-child(2)>.row>input:nth-child(3)"));
    }

    public FluentWebElement QRPatientCareCheckBox() {
        return input(cssSelector(".four>.row:nth-child(2)>.six:nth-child(2)>.row>input:nth-child(5)"));
    }

    public FluentWebElement QREnvironmentCheckBox() {
        return input(cssSelector(".four>.row:nth-child(2)>.six:nth-child(2)>.row>input:nth-child(7)"));
    }

    public ResultsPageHeader clickGender(String gender) {
        if (gender.toLowerCase().equals("male")) {
            maleCheckBox().click();
        } else if (gender.toLowerCase().equals("female")){
            femaleCheckBox().click();
        } else {
            throw new IllegalArgumentException("Accepted arguments: male,female");
        }
        return this;
    }

    public ResultsPageHeader clickBariatricSurgeryCheckBox() {
        BDBariatricSurgeryCheckBox().click();
        return this;
    }

    public ResultsPageHeader clickFilterSearchButton() {
       filterSearchButton().click();
       return this;
    }

// 2 5 10 15 30 50 100
    public ResultsPageHeader selectSearchRadius(int radius) {
        FluentSelect sel = select(searchRadiusDropDownLocator());

        if (radius == 2) {
            sel.selectByIndex(0);
            Assert.assertEquals(sel.getFirstSelectedOption().getText(), "2 miles");
        } else if (radius == 5) {
            sel.selectByIndex(1);
            Assert.assertEquals(sel.getFirstSelectedOption().getText(), "5 miles");
        } else if (radius == 10) {
            sel.selectByIndex(2);
            Assert.assertEquals(sel.getFirstSelectedOption().getText(),"10 miles");
        } else if (radius == 15) {
            sel.selectByIndex(3);
            Assert.assertEquals(sel.getFirstSelectedOption().getText(),"15 miles");
        } else if (radius == 30) {
            sel.selectByIndex(4);
            Assert.assertEquals(sel.getFirstSelectedOption().getText(),"30 miles");
        } else if (radius == 50) {
            sel.selectByIndex(5);
            Assert.assertEquals(sel.getFirstSelectedOption().getText(),"50 miles");
        } else if (radius == 100) {
            sel.selectByIndex(6);
            Assert.assertEquals(sel.getFirstSelectedOption().getText(),"100 miles");
        } else {
            throw new IllegalArgumentException("Possible options are: 2 5 10 15 30 50 100");
        }
        return this;
    }

    public ResultsPageHeader clickBoardCertified() {
        boardCertifiedCheckBox().click();
        return this;
    }

    public ResultsPageHeader selectLanguage(String language) {
        try {
            select(languageDropdownLocator()).selectByVisibleText(language);
        } catch (NoSuchElementException e) {
            StringBuilder availableLanguages = new StringBuilder();
            for (FluentWebElement el : languageDropdown().options()) {
                availableLanguages.append(el.getText()).append(" ");
            }
            throw new NoSuchElementException("Language \"" + language + "\" not available. Available languages: " + availableLanguages.toString());
        }

        return this;
    }

    public ResultsPageHeader clickAcceptingNewPatients() {
        acceptingNewPatientsCheckBox().click();
        return this;
    }

    public ResultsPageHeader clickHandicapAccessible() {
        handicapAccessibleCheckBox().click();
        return this;
    }

    public ResultsPageHeader clickEprescriber() {
        ePrescriberCheckBox().click();
        return this;
    }

    public ResultsPageHeader clickEveningHours() {
        eveningHoursCheckBox().click();
        return this;
    }

    public ResultsPageHeader clickWeekendHours() {
        weekendHoursCheckBox().click();
        return this;
    }

    public ResultsPageHeader selectHospitalAffiliation(String affiliation) {
        try {
            select(hospitalAffiliationDropDownLocator()).selectByVisibleText(affiliation);
        } catch (NoSuchElementException e) {
            StringBuilder availableAffiliations = new StringBuilder();
            for (FluentWebElement el : hospitalAffiliationDropDown().options()) {
                availableAffiliations.append(el.getText()).append("\n");
            }
            throw new NoSuchElementException("Affiliation \"" + affiliation + "\" not available. Available affiliations:\n" + availableAffiliations.toString());
        }

        return this;
    }

    public ResultsPageHeader clickACAaffiliated() {
        ACACertifiedCheckBox().click();
        return this;
    }

    public ResultsPageHeader clickBluePhysicianRecognition() {
        bluePhysicianRecognitionCheckBox().click();
        return this;
    }

    public ResultsPageHeader clickCloseButton() {
        closeButton().click();
        return this;
    }

    public ResultsPageHeader clickFilterSearch() {
        filterSearchButton().click();
        return this;
    }

    public ResultsPageHeader clickResetButton() {
        resetButton().click();
        return this;
    }

    public ResultsPageHeader clickFilterYourSearch() {
        filterSearchButton().click();
        return this;
    }

    //Facility Methods

    public ResultsPageHeader selectSpecialty(String spec) {
        try {
            select(specialDropDownLocator()).selectByVisibleText(spec);
        } catch (NoSuchElementException e) {
            StringBuilder sb = new StringBuilder();
            for (FluentWebElement el : specialtyDropDown().options()) {
                sb.append(el.getText()).append("\n");
            }
            throw new NoSuchElementException("Specialty \"" + spec + "\" not available. Available specialties:\n" + sb.toString());
        }

        return this;
    }

    public ResultsPageHeader clickVaccinesAdministeredCheckbox() {
        Assert.assertTrue(vaccinesAdministeredCheckBox().getText().toString().contains("Vaccines Administered"),
                "'Vaccines Administered' filter not found or checkbox changed");
        vaccinesAdministeredCheckBox().click();
        Assert.assertTrue(vaccinesAdministeredCheckBox().isSelected().value(), "Failed to check 'Vaccines Administered' checkbox");
        return this;
    }

    public ResultsPageHeader clickEPrescriberCheckbox() {
        Assert.assertTrue(ePrescriberCheckBox().getText().toString().contains("e-prescriber"),
                "'e-prescriber' filter not found or checkbox changed");
        ePrescriberCheckBox().click();
        Assert.assertTrue(ePrescriberCheckBox().isSelected().value(),"Failed to check 'e-prescriber' checkbox");
        return this;
    }

    public ResultsPageHeader clickBariatricSurgeryCheckbox() {
        Assert.assertTrue(BDBariatricSurgeryCheckBox().getText().toString().contains("Bariatric Surgery"),
                "'Bariatric Surgery' filter not found or checkbox changed");
        BDBariatricSurgeryCheckBox().input().click();
        Assert.assertTrue(BDBariatricSurgeryCheckBox().input().isSelected().value(),"Failed to check 'Bariatric Surgery' checkbox");
        return this;
    }

    public ResultsPageHeader clickCardiacCareCheckbox() {
        Assert.assertTrue(BDCardiacCareCheckBox().getText().toString().contains("Cardiac Care"),
                "'Cardiac Care' filter not found or checkbox changed");
        BDCardiacCareCheckBox().input().click();
        Assert.assertTrue(BDCardiacCareCheckBox().input().isSelected().value(),"Failed to check 'Cardiac Care' checkbox");
        return this;
    }

    public ResultsPageHeader clickKneeAndHipCheckbox() {
        Assert.assertTrue(BDKneeHipCheckBox().getText().toString().contains("Knee & Hip"),
                "'Knee & Hip' filter not found or checkbox changed");
        BDKneeHipCheckBox().input().click();
        Assert.assertTrue(BDKneeHipCheckBox().input().isSelected().value(),"Failed to check 'Knee & Hip' checkbox");
        return this;
    }

    public ResultsPageHeader clickSpineSurgeryCheckbox() {
        Assert.assertTrue(BDSpineSurgeryCheckBox().getText().toString().contains("Spine Surgery"),
                "'Spine Surgery' filter not found or checkbox changed");
        BDSpineSurgeryCheckBox().input().click();
        Assert.assertTrue(BDSpineSurgeryCheckBox().input().isSelected().value(),"Failed to check 'Spine Surgery' checkbox");
        return this;
    }

    public ResultsPageHeader clickTransplantsCheckbox() {
        Assert.assertTrue(BDTransplantsCheckBox().getText().toString().contains("Transplants"),
                "'Transplants' filter not found or checkbox changed");
        BDTransplantsCheckBox().input().click();
        Assert.assertTrue(BDTransplantsCheckBox().input().isSelected().value(), "Failed to check 'Transplants' checkbox");
        return this;
    }

    public ResultsPageHeader clickComplexAndRareCancersCheckbox() {
        Assert.assertTrue(BDComplexCancersCheckBox().getText().toString().contains("Complex and Rare Cancers"),
                "'Complex and Rare Cancers' filter not found or checkbox changed");
        BDComplexCancersCheckBox().input().click();
        Assert.assertTrue(BDComplexCancersCheckBox().input().isSelected().value(), "Failed to check 'Complex and Rare Cancers' checkbox");
        return this;
    }

    public ResultsPageHeader clickHeartAttackTreatmentCheckbox() {
        Assert.assertTrue(QRHeartAttackTreatmentCheckBox().getText().toString().contains("Heart Attack Treatment"),
                "'Heart Attack Treatment' filter not found or checkbox changed");
        QRHeartAttackTreatmentCheckBox().input().click();
        Assert.assertTrue(QRHeartAttackTreatmentCheckBox().input().isSelected().value(), "Failed to check 'Heart Attack Treatment' checkbox");
        return this;
    }

    public ResultsPageHeader clickHeartFailureTreatmentCheckbox() {
        Assert.assertTrue(QRHeartFailureTreatmentCheckBox().getText().toString().contains("Heart Failure Treatment"),
                "'Heart Failure Treatment' filter not found or checkbox changed");
        QRHeartFailureTreatmentCheckBox().input().click();
        Assert.assertTrue(QRHeartFailureTreatmentCheckBox().input().isSelected().value(), "Failed to check 'Heart Failure Treatment' checkbox");
        return this;
    }

    public ResultsPageHeader clickOverallMortalityRateCheckbox() {
        Assert.assertTrue(QROverallMortalityRateCheckBox().getText().toString().contains("Overall Mortality Rate"),
                "'Overall Mortality Rate' filter not found or checkbox changed");
        QROverallMortalityRateCheckBox().input().click();
        Assert.assertTrue(QROverallMortalityRateCheckBox().input().isSelected().value(), "Failed to check 'Overall Mortality Rate' checkbox");
        return this;
    }

    public ResultsPageHeader clickInfantMortalityRateCheckbox() {
        Assert.assertTrue(QRInfantMortalityRateCheckBox().getText().toString().contains("Infant Mortality Rate"),
                "'Infant Mortality Rate' filter not found or checkbox changed");
        QRInfantMortalityRateCheckBox().input().click();
        Assert.assertTrue(QRInfantMortalityRateCheckBox().input().isSelected().value(), "Failed to check 'Infant Mortality Rate' checkbox");
        return this;
    }

    public ResultsPageHeader clickCommunicationCheckbox() {
        Assert.assertTrue(QRCommunicationCheckBox().getText().toString().contains("Communication"),
                "'Communication' filter not found or checkbox changed");
        QRCommunicationCheckBox().input().click();
        Assert.assertTrue(QRCommunicationCheckBox().input().isSelected().value(), "Failed to check 'Communication' checkbox");
        return this;
    }

    public ResultsPageHeader clickPatientCareCheckbox() {
        Assert.assertTrue(QRPatientCareCheckBox().getText().toString().contains("Patient Care"),
                "'Patient Care' filter not found or checkbox changed");
        QRPatientCareCheckBox().input().click();
        Assert.assertTrue(QRPatientCareCheckBox().input().isSelected().value(), "Failed to check 'Patient Care' checkbox");
        return this;
    }

    public ResultsPageHeader clickEnvironmentCheckbox() {
        Assert.assertTrue(QREnvironmentCheckBox().getText().toString().contains("Environment"),
                "'Environment' filter not found or checkbox changed");
        QREnvironmentCheckBox().input().click();
        Assert.assertTrue(QREnvironmentCheckBox().input().isSelected().value(), "Failed to check 'Environment' checkbox");
        return this;
    }
}
