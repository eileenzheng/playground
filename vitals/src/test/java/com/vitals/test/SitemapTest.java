package com.vitals.test;

import com.vitals.pages.HomePage;
import com.vitals.pages.sitemap.*;
import com.vitalsqa.testrail.TestCase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SitemapTest {

    String url;
    SoftAssert m_assert;

    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) throws Exception {
        this.url = url;
    }

    @TestCase(id=2449)
    @Test
    public void locationSiteMap(){
        m_assert = new SoftAssert();
        LocationsPage locationPage = new LocationsPage();
        locationPage.get(url + "/locations");

        m_assert.assertTrue(locationPage.specialties().size()==55, "# of specialties not equal to 55");
        m_assert.assertTrue(locationPage.groupPractices().size()==51, "# of states for GP not equal to 51");
        m_assert.assertAll();
    }

    @TestCase(id=1852)
    @Test
    public void checkSpecialtyLocationPage() {
        m_assert = new SoftAssert();
        HomePage home = new HomePage();
        home.get(url);
        home.getRandom(home.locationLinks()).click();

        CityStatePage locationPage = new CityStatePage();

        m_assert.assertTrue(locationPage.states().size()>=50 && locationPage.states().size()<=51, "# of states not between 50 & 51! " + locationPage.states().size());
        m_assert.assertTrue(locationPage.cities().size()>=230 && locationPage.states().size()<=255, "# of cities not between 230 & 255 " + locationPage.cities().size());
        m_assert.assertAll();
    }

    @TestCase(id=1853)
    @Test
    public void checkSpecialtyStatePage() {
        HomePage home = new HomePage();
        home.get(url);
        home.getRandom(home.locationLinks()).click();
        CityStatePage locationPage = new CityStatePage();
        locationPage.getRandom(locationPage.states()).click();

        StatePage statePage = new StatePage();
        Assert.assertTrue(statePage.cities().size()>=3, "Less than 3 cities!");
    }

    @TestCase(id=2450)
    @Test
    public void checkUccLocationPage() {
        m_assert = new SoftAssert();
        CityStatePage locationPage = new CityStatePage();
        locationPage.get(url + "/locations/urgent-care");

        m_assert.assertTrue(locationPage.states().size()>=50 && locationPage.states().size()<=51, "# of states not between 50 & 51! " + locationPage.states().size());
        m_assert.assertTrue(locationPage.cities().size() >= 230 && locationPage.states().size() <= 255, "# of cities not between 230 & 255 " + locationPage.cities().size());
        m_assert.assertAll();
    }

    @TestCase(id=2451)
    @Test
    public void specialtiesSiteMap() {
        SpecialtiesPage specialtiesPage = new SpecialtiesPage();
        specialtiesPage.get(url + "/specialties");

        Assert.assertTrue(specialtiesPage.specialties().size()>=55 && specialtiesPage.specialties().size()<=57, "# of specialties not between 55 & 57");
    }

    @TestCase(id=2452)
    @Test
    public void nameSiteMap() {
        m_assert = new SoftAssert();
        DirectoryPage directoryPage = new DirectoryPage();
        directoryPage.get(url + "/directory");
        m_assert.assertTrue(directoryPage.doctors().size()==100, "# of doctors displayed does not equal 100");

        while (directoryPage.currentAlphabet().getText().toString().equals("Recent")) {
            directoryPage.getRandom(directoryPage.alphabet()).click();
        }
        m_assert.assertTrue(directoryPage.doctors().size()==100, "# of doctors displayed does not equal 100");

        directoryPage.getRandom(directoryPage.pagination()).click();
        // if last page
        if (directoryPage.currentPage().getText().toString().equals(directoryPage.lastJumpPageLink().getText().toString())) {
            m_assert.assertTrue(directoryPage.doctors().size() >= 1, "Last page: # of doctor not >1 ");
        }
        // not last page
        else {
            m_assert.assertTrue(directoryPage.doctors().size() == 100, "# of doctors displayed does not equal 100");
        }

        m_assert.assertAll();
    }

    @TestCase(id=2453)
    @Test
    public void conditionSiteMap() {
        m_assert = new SoftAssert();
        ConditionsPage conditionsPage = new ConditionsPage();
        conditionsPage.get(url + "/conditions");
        m_assert.assertTrue(conditionsPage.conditions().size()==50, "# of conditions displayed does not equal 50");

        while (conditionsPage.currentAlphabet().getText().toString().equals("Featured")) {
            conditionsPage.getRandom(conditionsPage.alphabet()).click();
        }
        m_assert.assertTrue(conditionsPage.conditions().size()<=50 && conditionsPage.conditions().size()>1, "# of conditions displayed not between 1 and 50");

        if (conditionsPage.conditions().size()==50) {
            conditionsPage.getRandom(conditionsPage.pagination()).click();
            m_assert.assertTrue(conditionsPage.conditions().size()<=50 && conditionsPage.conditions().size()>1, "# of conditions displayed not between 1 and 50");

        }

        m_assert.assertAll();
    }

    @TestCase(id=2454)
    @Test
    public void insuranceSiteMap() {
        m_assert = new SoftAssert();
        InsurancePage insurancePage = new InsurancePage();
        insurancePage.get(url + "/insurances");
        m_assert.assertTrue(insurancePage.companies().size()> 160 && insurancePage.companies().size()< 170, "# of insurance companies displayed not between 160 and 170");
        m_assert.assertTrue(insurancePage.plans().size()> 640 && insurancePage.plans().size()< 650, "# of insurance plans displayed not between 640 and 650");

        while (insurancePage.currentAlphabet().getText().toString().equals("All")) {
            insurancePage.getRandom(insurancePage.alphabet()).click();
        }
        m_assert.assertTrue(insurancePage.companies().size()>= 1, "Less than 1 insurance company displayed");
        m_assert.assertTrue(insurancePage.plans().size()>=1, "Less than 1 insurance plan displayed");

        m_assert.assertAll();
    }

}
