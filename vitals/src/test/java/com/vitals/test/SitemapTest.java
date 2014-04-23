package com.vitals.test;

import com.vitals.pages.BasePage;
import com.vitals.pages.HomePage;
import com.vitals.pages.sitemap.CityStatePage;
import com.vitals.pages.sitemap.StatePage;
import com.vitalsqa.testrail.TestCase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SitemapTest {

    String url;
    SoftAssert m_assert;
    BasePage page;

    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) throws Exception {
        this.url = url;
    }

    @TestCase(id=1852)
    @Test
    public void checkSpecialtyPage() {
        m_assert = new SoftAssert();
        HomePage home = new HomePage();
        home.get(url);
        home.getRandom(home.locationLinks()).click();

        CityStatePage locationPage = new CityStatePage();
        m_assert.assertTrue(locationPage.states().size()==51, "Less than 51 states! " + locationPage.states().size());
        m_assert.assertTrue(locationPage.cities().size()==253, "Less than 253 cities! " + locationPage.cities().size());

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
}
