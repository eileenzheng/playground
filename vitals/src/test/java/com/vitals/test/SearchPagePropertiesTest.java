package com.vitals.test;

import com.vitalsqa.testrail.TestCase;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.vitals.pages.HomePage;
import com.vitals.pages.SearchResultsPage;
import java.util.List;

public class SearchPagePropertiesTest {

    SoftAssert m_assert;
    String url;
    String url_plain;
    String name = "John";
    String insurance = "United Healthcare";
    String plan = "Definity Choice Plus";
    String condition = "Diabetes";
    String specialty = "Dermatologist";
    String dentistSpecialty = "Dentist";
    String uccName="citymd";
    
    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) throws Exception {
        this.url = url;
        if (url.contains("staging")) {
            url_plain = "http://staging.vitals.com";
        }
        else {
            url_plain = "http://www.vitals.com";
        }
    }
    
    @TestCase(id=1672)
    @Test
    public void nameSearchInsurancePlan() {
        HomePage homePage = new HomePage();
        homePage.deleteCookies();
        homePage.get(url);

        homePage.headerModule().insurance().click();
        homePage.headerModule().enterInsuranceCompany(insurance);
        homePage.headerModule().insuranceSuggestions().get(0).click();
        homePage.headerModule().openInsurancePlan();
        homePage.headerModule().insurancePlanSuggestions().get(0).click();
        homePage.headerModule().saveInsuranceButton().click();
        homePage.headerModule().enterSearchTerm(name);
        homePage.headerModule().showAll().get(0).click();

        SearchResultsPage results = new SearchResultsPage();

        m_assert = new SoftAssert();
        boolean breadcrumb = breadcrumbSearch(results);
        m_assert.assertTrue(breadcrumb, "Breadcrumb text or link is incorrect");
        Reporter.log(results.getBreadcrumbText().toString() + results.breadcrumbCurrent().getText().toString() + results.getBreadcrumbUrl().toString());
        boolean h1 = h1NameSearch(results.getH1Text(), results.headerModule().locationTextBox().getAttribute("value").toString(), "doctor");
        boolean ins = (results.getH1Text().get(0)).equalsIgnoreCase(insurance + " - " + plan);
        m_assert.assertTrue(h1 && ins, "H1 is incorrect");
        Reporter.log(results.getH1Text().toString());

        m_assert.assertTrue(results.getTitle().equals("Find a Doctor or Dentist - Vitals.com"),
        		"Title is incorrect");
        m_assert.assertTrue(results.getDescription().equals("Find a doctor or dentist near you on Vitals.com"),
        		"Meta description is incorrect");

        m_assert.assertAll();
    }

    @TestCase(id=1673)
    @Test
    public void nameSearchInsuranceCompany() {
        HomePage homePage = new HomePage();
        homePage.deleteCookies();
        homePage.get(url);

        homePage.headerModule().insurance().click();
        homePage.headerModule().enterInsuranceCompany(insurance);
        homePage.headerModule().insuranceSuggestions().get(0).click();
        homePage.headerModule().saveInsuranceButton().click();
        homePage.headerModule().enterSearchTerm(name);
        homePage.headerModule().showAll().get(0).click();

        SearchResultsPage results = new SearchResultsPage();

        m_assert = new SoftAssert();
        boolean breadcrumb = breadcrumbSearch(results);
        m_assert.assertTrue(breadcrumb, "Breadcrumb text or link is incorrect");
        Reporter.log(results.getBreadcrumbText().toString() + results.breadcrumbCurrent().getText().toString() + results.getBreadcrumbUrl().toString());
        boolean h1 = h1NameSearch(results.getH1Text(),results.headerModule().locationTextBox().getAttribute("value").toString(), "doctor");
        boolean ins = (results.getH1Text().get(0)).equalsIgnoreCase(insurance);
        m_assert.assertTrue(h1 && ins, "H1 is incorrect");
        Reporter.log(results.getH1Text().toString());

        m_assert.assertTrue(results.getTitle().equals("Find a Doctor or Dentist - Vitals.com"),
        		"Title is incorrect");
        m_assert.assertTrue(results.getDescription().equals("Find a doctor or dentist near you on Vitals.com"),
        		"Meta description is incorrect");

        m_assert.assertAll();
    }

    @TestCase(id=1674)
    @Test
    public void nameSearchNoInsurance() {
        HomePage homePage = new HomePage();
        homePage.deleteCookies();
        homePage.get(url);

        homePage.headerModule().enterSearchTerm(name);
        homePage.headerModule().showAll().get(0).click();
        SearchResultsPage results = new SearchResultsPage();

        m_assert = new SoftAssert();
        boolean breadcrumb = breadcrumbSearch(results);
        m_assert.assertTrue(breadcrumb, "Breadcrumb text or link is incorrect");
        Reporter.log(results.getBreadcrumbText().toString() + results.breadcrumbCurrent().getText().toString() + results.getBreadcrumbUrl().toString());
        boolean h1 = h1NameSearch(results.getH1Text(),results.headerModule().locationTextBox().getAttribute("value").toString(), "doctor");
        m_assert.assertTrue(h1, "H1 is incorrect");
        Reporter.log(results.getH1Text().toString());

        m_assert.assertTrue(results.getTitle().equals("Find a Doctor or Dentist - Vitals.com"),
        		"Title is incorrect");
        m_assert.assertTrue(results.getDescription().equals("Find a doctor or dentist near you on Vitals.com"),
        		"Meta description is incorrect");

        m_assert.assertAll();
    }

    @TestCase(id=1675)
    @Test
    public void nameSearchDentist() {
        HomePage homePage = new HomePage();
        homePage.get(url);

        homePage.headerModule().enterSearchTerm(name);
        homePage.headerModule().showAll().get(1).click(); // 1 is dentist for search term 'john'

        SearchResultsPage results = new SearchResultsPage();

        m_assert = new SoftAssert();
        boolean breadcrumb = breadcrumbSearch(results);
        m_assert.assertTrue(breadcrumb, "Breadcrumb text or link is incorrect");
        Reporter.log(results.getBreadcrumbText().toString() + results.breadcrumbCurrent().getText().toString() + results.getBreadcrumbUrl().toString());
        boolean h1 = h1NameSearch(results.getH1Text(),results.headerModule().locationTextBox().getAttribute("value").toString(), "dentist");
        m_assert.assertTrue(h1, "H1 is incorrect");
        Reporter.log(results.getH1Text().toString());

        m_assert.assertTrue(results.getTitle().equals("Find a Doctor or Dentist - Vitals.com"),
        		"Title is incorrect");
        m_assert.assertTrue(results.getDescription().equals("Find a doctor or dentist near you on Vitals.com"),
        		"Meta description is incorrect");

        m_assert.assertAll();
    }

    @TestCase(id=1676)
    @Test
    public void conditionSearchInsurancePlan() {
        HomePage homePage = new HomePage();
        homePage.deleteCookies();
        homePage.get(url);

        homePage.headerModule().insurance().click();
        homePage.headerModule().enterInsuranceCompany(insurance);
        homePage.headerModule().insuranceSuggestions().get(0).click();
        homePage.headerModule().openInsurancePlan();
        homePage.headerModule().insurancePlanSuggestions().get(0).click();
        homePage.headerModule().saveInsuranceButton().click();
        homePage.headerModule().enterSearchTerm(condition);
        homePage.headerModule().conditionSuggestions().get(0).click();

        SearchResultsPage results = new SearchResultsPage();

        m_assert = new SoftAssert();
        boolean breadcrumb = breadcrumbSearch(results);
        m_assert.assertTrue(breadcrumb, "Breadcrumb text or link is incorrect");
        Reporter.log(results.getBreadcrumbText().toString() + results.breadcrumbCurrent().getText().toString() + results.getBreadcrumbUrl().toString());
        boolean h1 = h1ConditionSearch(results.getH1Text(), results.headerModule().locationTextBox().getAttribute("value").toString());
        boolean ins = (results.getH1Text().get(0)).equalsIgnoreCase(insurance + " - " + plan);
        m_assert.assertTrue(h1 && ins, "H1 is incorrect");
        Reporter.log(results.getH1Text().toString());

        m_assert.assertTrue(results.getTitle().equals("Find a Doctor or Dentist - Vitals.com"),
        		"Title is incorrect");
        m_assert.assertTrue(results.getDescription().equals("Find a doctor or dentist near you on Vitals.com"),
        		"Meta description is incorrect");

        m_assert.assertAll();
    }

    @TestCase(id=1677)
    @Test
    public void conditionSearchInsuranceCompany() {
        HomePage homePage = new HomePage();
        homePage.deleteCookies();
        homePage.get(url);

        homePage.headerModule().insurance().click();
        homePage.headerModule().enterInsuranceCompany(insurance);
        homePage.headerModule().insuranceSuggestions().get(0).click();
        homePage.headerModule().saveInsuranceButton().click();
        homePage.headerModule().enterSearchTerm(condition);
        homePage.headerModule().conditionSuggestions().get(0).click();

        SearchResultsPage results = new SearchResultsPage();

        m_assert = new SoftAssert();
        boolean breadcrumb = breadcrumbSearch(results);
        m_assert.assertTrue(breadcrumb, "Breadcrumb text or link is incorrect");
        Reporter.log(results.getBreadcrumbText().toString() + results.breadcrumbCurrent().getText().toString() + results.getBreadcrumbUrl().toString());
        boolean h1 = h1ConditionSearch(results.getH1Text(),results.headerModule().locationTextBox().getAttribute("value").toString());
        boolean ins = (results.getH1Text().get(0)).equalsIgnoreCase(insurance);
        m_assert.assertTrue(h1 && ins, "H1 is incorrect");
        Reporter.log(results.getH1Text().toString());

        m_assert.assertTrue(results.getTitle().equals("Find a Doctor or Dentist - Vitals.com"),
        		"Title is incorrect");
        m_assert.assertTrue(results.getDescription().equals("Find a doctor or dentist near you on Vitals.com"),
        		"Meta description is incorrect");

        m_assert.assertAll();
    }

    @TestCase(id=1678)
    @Test
    public void conditionSearchNoInsurance() {
        HomePage homePage = new HomePage();
        homePage.deleteCookies();
        homePage.get(url);

        homePage.headerModule().enterSearchTerm(condition);
        homePage.headerModule().conditionSuggestions().get(0).click();

        SearchResultsPage results = new SearchResultsPage();

        m_assert = new SoftAssert();
        boolean breadcrumb = breadcrumbSearch(results);
        m_assert.assertTrue(breadcrumb, "Breadcrumb text or link is incorrect");
        Reporter.log(results.getBreadcrumbText().toString() + results.breadcrumbCurrent().getText().toString() + results.getBreadcrumbUrl().toString());
        boolean h1 = h1ConditionSearch(results.getH1Text(),results.headerModule().locationTextBox().getAttribute("value").toString());
        m_assert.assertTrue(h1, "H1 is incorrect");
        Reporter.log(results.getH1Text().toString());

        m_assert.assertTrue(results.getTitle().equals("Find a Doctor or Dentist - Vitals.com"),
        		"Title is incorrect");
        m_assert.assertTrue(results.getDescription().equals("Find a doctor or dentist near you on Vitals.com"),
        		"Meta description is incorrect");

        m_assert.assertAll();
    }

    @TestCase(id=1679)
    @Test
    public void specialtySearchInsurancePlan() {
        HomePage homePage = new HomePage();
        homePage.deleteCookies();
        homePage.get(url);

        homePage.headerModule().insurance().click();
        homePage.headerModule().enterInsuranceCompany(insurance);
        homePage.headerModule().insuranceSuggestions().get(0).click();
        homePage.headerModule().openInsurancePlan();
        homePage.headerModule().insurancePlanSuggestions().get(0).click();
        homePage.headerModule().saveInsuranceButton().click();
        homePage.headerModule().enterSearchTerm(specialty);
        homePage.headerModule().specialtySuggestions().get(0).click();

        SearchResultsPage results = new SearchResultsPage();

        m_assert = new SoftAssert();
        boolean breadcrumb = breadcrumbSearch(results);
        m_assert.assertTrue(breadcrumb, "Breadcrumb text or link is incorrect");
        Reporter.log(results.getBreadcrumbText().toString() + results.breadcrumbCurrent().getText().toString() + results.getBreadcrumbUrl().toString());
        boolean h1 = h1SpecialtySearch(results.getH1Text(), results.headerModule().locationTextBox().getAttribute("value").toString(), specialty);
        boolean ins = (results.getH1Text().get(0)).equalsIgnoreCase(insurance + " - " + plan);
        m_assert.assertTrue(h1 && ins, "H1 is incorrect");
        Reporter.log(results.getH1Text().toString());

        m_assert.assertTrue(results.getTitle().equals("Find a Doctor or Dentist - Vitals.com"),
        		"Title is incorrect");
        m_assert.assertTrue(results.getDescription().equals("Find a doctor or dentist near you on Vitals.com"),
        		"Meta description is incorrect");

        m_assert.assertAll();
    }

    @TestCase(id=1680)
    @Test
    public void specialtySearchInsuranceCompany() {
        HomePage homePage = new HomePage();
        homePage.deleteCookies();
        homePage.get(url);

        homePage.headerModule().insurance().click();
        homePage.headerModule().enterInsuranceCompany(insurance);
        homePage.headerModule().insuranceSuggestions().get(0).click();
        homePage.headerModule().saveInsuranceButton().click();
        homePage.headerModule().enterSearchTerm(specialty);
        homePage.headerModule().specialtySuggestions().get(0).click();

        SearchResultsPage results = new SearchResultsPage();

        m_assert = new SoftAssert();
        boolean breadcrumb = breadcrumbSearch(results);
        m_assert.assertTrue(breadcrumb, "Breadcrumb text or link is incorrect");
        Reporter.log(results.getBreadcrumbText().toString() + results.breadcrumbCurrent().getText().toString() + results.getBreadcrumbUrl().toString());
        boolean h1 = h1SpecialtySearch(results.getH1Text(),results.headerModule().locationTextBox().getAttribute("value").toString(), specialty);
        boolean ins = (results.getH1Text().get(0)).equalsIgnoreCase(insurance);
        m_assert.assertTrue(h1 && ins, "H1 is incorrect");
        Reporter.log(results.getH1Text().toString());

        m_assert.assertTrue(results.getTitle().equals("Find a Doctor or Dentist - Vitals.com"),
        		"Title is incorrect");
        m_assert.assertTrue(results.getDescription().equals("Find a doctor or dentist near you on Vitals.com"),
        		"Meta description is incorrect");

        m_assert.assertAll();
    }

    @TestCase(id=1681)
    @Test
    public void specialtySearchNoInsurance() {
        HomePage homePage = new HomePage();
        homePage.deleteCookies();
        homePage.get(url);

        homePage.headerModule().enterSearchTerm(specialty);
        homePage.headerModule().specialtySuggestions().get(0).click();

        SearchResultsPage results = new SearchResultsPage();

        m_assert = new SoftAssert();
        boolean breadcrumb = breadcrumbSearch(results);
        m_assert.assertTrue(breadcrumb, "Breadcrumb text or link is incorrect");
        Reporter.log(results.getBreadcrumbText().toString() + results.breadcrumbCurrent().getText().toString() + results.getBreadcrumbUrl().toString());
        boolean h1 = h1SpecialtySearch(results.getH1Text(),results.headerModule().locationTextBox().getAttribute("value").toString(), specialty);
        m_assert.assertTrue(h1, "H1 is incorrect");
        Reporter.log(results.getH1Text().toString());

        m_assert.assertTrue(results.getTitle().equals("Find a Doctor or Dentist - Vitals.com"),
        		"Title is incorrect");
        m_assert.assertTrue(results.getDescription().equals("Find a doctor or dentist near you on Vitals.com"),
        		"Meta description is incorrect");

        m_assert.assertAll();
    }

    @TestCase(id=1682)
    @Test
    public void specialtySearchDentist() {
        HomePage homePage = new HomePage();
        homePage.get(url);

        homePage.headerModule().enterSearchTerm(dentistSpecialty);
        homePage.headerModule().specialtySuggestions().get(0).click();

        SearchResultsPage results = new SearchResultsPage();

        m_assert = new SoftAssert();
        boolean breadcrumb = breadcrumbSearch(results);
        m_assert.assertTrue(breadcrumb, "Breadcrumb text or link is incorrect");
        Reporter.log(results.getBreadcrumbText().toString() + results.breadcrumbCurrent().getText().toString() + results.getBreadcrumbUrl().toString());
        boolean h1 = h1SpecialtySearch(results.getH1Text(),results.headerModule().locationTextBox().getAttribute("value").toString(), dentistSpecialty);
        m_assert.assertTrue(h1, "H1 is incorrect");
        Reporter.log(results.getH1Text().toString());

        m_assert.assertTrue(results.getTitle().equals("Find a Doctor or Dentist - Vitals.com"),
        		"Title is incorrect");
        m_assert.assertTrue(results.getDescription().equals("Find a doctor or dentist near you on Vitals.com"),
        		"Meta description is incorrect");

        m_assert.assertAll();
    }

    @TestCase(id=1683)
    @Test
    public void conditionBrowse() {
        SearchResultsPage results = new SearchResultsPage();
        results.get(url + "/condition/diabetes");

        m_assert = new SoftAssert();
        boolean breadcrumb = breadcrumbCondition(results);
        m_assert.assertTrue(breadcrumb, "Breadcrumb text or link is incorrect");
        Reporter.log(results.getBreadcrumbText().toString() + results.breadcrumbCurrent().getText().toString() + results.getBreadcrumbUrl().toString());
        boolean h1 = h1ConditionSearch(results.getH1Text(),results.headerModule().locationTextBox().getAttribute("value").toString());
        m_assert.assertTrue(h1, "H1 is incorrect");
        Reporter.log(results.getH1Text().toString());

        m_assert.assertTrue(results.getTitle().equals("Diabetes Experts - Read Patient Reviews & Get Informed - Vitals.com"),
        		"Title is incorrect");
        m_assert.assertTrue(results.getDescription().equals("Find a diabetes expert near you, read patient reviews, and get informed on Vitals.com"),
        		"Meta description is incorrect");

        m_assert.assertAll();
    }

    @TestCase(id=1684)
    @Test
    public void specialtyBrowse() {
        SearchResultsPage results = new SearchResultsPage();
        results.get(url + "/dermatologists");

        m_assert = new SoftAssert();
        boolean breadcrumb = breadcrumbSpecialty(results);
        m_assert.assertTrue(breadcrumb, "Breadcrumb text or link is incorrect");
        Reporter.log(results.getBreadcrumbText().toString() + results.breadcrumbCurrent().getText().toString() + results.getBreadcrumbUrl().toString());
        boolean h1 = h1SpecialtySearch(results.getH1Text(),results.headerModule().locationTextBox().getAttribute("value").toString(), specialty);
        m_assert.assertTrue(h1, "H1 is incorrect");
        Reporter.log(results.getH1Text().toString());

        m_assert.assertTrue(results.getTitle().equals("Find a Dermatologist near you, Read Patient Reviews, & Get Informed - Vitals.com"),
        		"Title is incorrect");
        m_assert.assertTrue(results.getDescription().equals("Find dermatologists, read patient reviews, and get informed on Vitals.com"),
        		"Meta description is incorrect");

        m_assert.assertAll();
    }

    @TestCase(id=1685)
    @Test
    public void specialtyBrowseInsurance() {
        SearchResultsPage results = new SearchResultsPage();
        results.get(url + "/dermatologists/united-healthcare");

        m_assert = new SoftAssert();
        boolean breadcrumb = breadcrumbSpecialtyInsurance(results);
        m_assert.assertTrue(breadcrumb, "Breadcrumb text or link is incorrect");
        Reporter.log(results.getBreadcrumbText().toString() + results.breadcrumbCurrent().getText().toString() + results.getBreadcrumbUrl().toString());
        boolean h1 = h1SpecialtySearch(results.getH1Text(),results.headerModule().locationTextBox().getAttribute("value").toString(), specialty);
        m_assert.assertTrue(h1, "H1 is incorrect");
        Reporter.log(results.getH1Text().toString());

        m_assert.assertTrue(results.getTitle().equals("Find United Healthcare Dermatologists near you, Read Patient Reviews, & Get Informed - Vitals.com"),
        		"Title is incorrect");
        m_assert.assertTrue(results.getDescription().equals("Find United Healthcare dermatologists near you, read patient reviews, and get informed on Vitals.com"),
        		"Meta description is incorrect");

        m_assert.assertAll();
    }

    @TestCase(id=1686)
    @Test
    public void specialtyBrowseInsurancePlan() {
        SearchResultsPage results = new SearchResultsPage();
        results.get(url + "/dermatologists/united-healthcare/definity-choice-plus");

        m_assert = new SoftAssert();
        boolean breadcrumb = breadcrumbSpecialtyInsurancePlan(results);
        m_assert.assertTrue(breadcrumb, "Breadcrumb text or link is incorrect");
        Reporter.log(results.getBreadcrumbText().toString() + results.breadcrumbCurrent().getText().toString() + results.getBreadcrumbUrl().toString());
        boolean h1 = h1SpecialtySearch(results.getH1Text(),results.headerModule().locationTextBox().getAttribute("value").toString(), specialty);
        boolean ins = (results.getH1Text().get(0)).equalsIgnoreCase(insurance + " - " + plan);
        m_assert.assertTrue(h1 && ins, "H1 is incorrect");
        Reporter.log(results.getH1Text().toString());

        m_assert.assertTrue(results.getTitle().equals("Find Definity Choice Plus Dermatologists near you, Read Patient Reviews, & Get Informed - Vitals.com"),
        		"Title is incorrect");
        m_assert.assertTrue(results.getDescription().equals("Find Definity Choice Plus dermatologists near you, read patient reviews, and get informed on Vitals.com"),
        		"Meta description is incorrect");

        m_assert.assertAll();
    }

    @TestCase(id=1687)
    @Test
    public void specialtyBrowseCity() {
        SearchResultsPage results = new SearchResultsPage();
        results.get(url + "/dermatologists/ny/new-york");

        m_assert = new SoftAssert();
        boolean breadcrumb = breadcrumbSpecialtyCity(results);
        m_assert.assertTrue(breadcrumb, "Breadcrumb text or link is incorrect");
        Reporter.log(results.getBreadcrumbText().toString() + results.breadcrumbCurrent().getText().toString() + results.getBreadcrumbUrl().toString());
        boolean h1 = h1SpecialtySearch(results.getH1Text(),results.headerModule().locationTextBox().getAttribute("value").toString(), specialty);
        m_assert.assertTrue(h1, "H1 is incorrect");
        Reporter.log(results.getH1Text().toString());

        m_assert.assertTrue(results.getTitle().equals("Dermatologists in New York, NY - Read Patient Reviews - Vitals"),
        		"Title is incorrect");
        m_assert.assertTrue(results.getDescription().equals("Find dermatologists in New York, NY, read patient reviews, and get informed on Vitals.com"),
        		"Meta description is incorrect");

        m_assert.assertAll();
    }

    @TestCase(id=1688)
    @Test
    public void specialtyBrowseCityInsurance() {
        SearchResultsPage results = new SearchResultsPage();
        results.get(url + "/dermatologists/ny/new-york/united-healthcare");

        m_assert = new SoftAssert();
        boolean breadcrumb = breadcrumbSpecialtyCityInsurance(results);
        m_assert.assertTrue(breadcrumb, "Breadcrumb text or link is incorrect");
        Reporter.log(results.getBreadcrumbText().toString() + results.breadcrumbCurrent().getText().toString() + results.getBreadcrumbUrl().toString());
        boolean h1 = h1SpecialtySearch(results.getH1Text(),results.headerModule().locationTextBox().getAttribute("value").toString(), specialty);
        m_assert.assertTrue(h1, "H1 is incorrect");
        Reporter.log(results.getH1Text().toString());

        m_assert.assertTrue(results.getTitle().equals("United Healthcare Dermatologists in New York, NY - Read Patient Reviews & Get Informed - Vitals.com"),
        		"Title is incorrect");
        m_assert.assertTrue(results.getDescription().equals("Find United Healthcare dermatologists in New York, NY, read patient reviews, and get informed on Vitals.com"),
        		"Meta description is incorrect");

        m_assert.assertAll();
    }

    @TestCase(id=2058)
    @Test
    public void nameSearchUcc() {
        HomePage homePage = new HomePage();
        homePage.get(url);

        homePage.headerModule().enterSearchTerm(uccName);
        homePage.headerModule().showAll().get(homePage.headerModule().showAll().size()-1).click();

        SearchResultsPage results = new SearchResultsPage();

        m_assert = new SoftAssert();
        boolean breadcrumb = breadcrumbSearch(results);
        m_assert.assertTrue(breadcrumb, "Breadcrumb text or link is incorrect");
        Reporter.log(results.getBreadcrumbText().toString() + results.breadcrumbCurrent().getText().toString() + results.getBreadcrumbUrl().toString());
        boolean h1 = h1UccSearch(results.getH1Text(),results.headerModule().locationTextBox().getAttribute("value").toString());
        m_assert.assertTrue(h1, "H1 is incorrect");
        Reporter.log(results.getH1Text().toString());

        m_assert.assertTrue(results.getTitle().equals("Find an Urgent Care Center near you, Read Patient Reviews, & Get Informed - Vitals.com"),
                "Title is incorrect");
        m_assert.assertTrue(results.getDescription().equals("Find urgent care centers near you, read patient reviews, and get informed on Vitals.com."),
                "Meta description is incorrect");

        m_assert.assertAll();
    }

    @TestCase(id=2059)
    @Test
    public void mainBrowseUcc() {
        HomePage homePage = new HomePage();
        homePage.deleteCookies();
        homePage.get(url + "/urgent-care");

        SearchResultsPage results = new SearchResultsPage();

        m_assert = new SoftAssert();
        boolean breadcrumb = breadcrumbUccMainBrowse(results);
        m_assert.assertTrue(breadcrumb, "Breadcrumb text or link is incorrect");
        Reporter.log(results.getBreadcrumbText().toString() + results.breadcrumbCurrent().getText().toString() + results.getBreadcrumbUrl().toString());
        boolean h1 = h1UccBrowse(results.getH1Text(),results.headerModule().locationTextBox().getAttribute("value").toString());
        m_assert.assertTrue(h1, "H1 is incorrect");
        Reporter.log(results.getH1Text().toString());

        m_assert.assertTrue(results.getTitle().equals("Find an Urgent Care Center near you, Read Patient Reviews, & Get Informed - Vitals.com"),
                "Title is incorrect");
        m_assert.assertTrue(results.getDescription().equals("Find urgent care centers near you, read patient reviews, and get informed on Vitals.com."),
                "Meta description is incorrect");

        m_assert.assertAll();
    }

    @TestCase(id=2060)
    @Test
    public void cityBrowseUcc() {
        HomePage homePage = new HomePage();
        homePage.deleteCookies();
        homePage.get(url + "/urgent-care/ny/new-york");

        SearchResultsPage results = new SearchResultsPage();

        m_assert = new SoftAssert();
        boolean breadcrumb = breadcrumbUccCityBrowse(results);
        m_assert.assertTrue(breadcrumb, "Breadcrumb text or link is incorrect");
        Reporter.log(results.getBreadcrumbText().toString() + results.breadcrumbCurrent().getText().toString() + results.getBreadcrumbUrl().toString());
        boolean h1 = h1UccBrowse(results.getH1Text(),results.headerModule().locationTextBox().getAttribute("value").toString());
        m_assert.assertTrue(h1, "H1 is incorrect");
        Reporter.log(results.getH1Text().toString());

        m_assert.assertTrue(results.getTitle().equals("New York, NY Urgent Care Centers - Read Patient Reviews & Get Informed - Vitals.com"),
                "Title is incorrect");
        m_assert.assertTrue(results.getDescription().equals("Find urgent care centers in New York, NY, read patient reviews, and get informed on Vitals.com."),
                "Meta description is incorrect");

        m_assert.assertAll();
    }


    private boolean h1NameSearch (List<String> h1, String locationTerm, String type) {
    	int len = h1.size();
    	if (!h1.get(len-5).contains(type))
            return false;
    	else if (!h1.get(len-4).equals("named"))
            return false;
    	else if (!h1.get(len-3).equalsIgnoreCase(name))
            return false;
    	else if (!h1.get(len-2).equals("near"))
            return false;
    	else
            return h1.get(len - 1).equals(locationTerm);
    }

    private boolean h1ConditionSearch (List<String> h1, String locationTerm) {
    	int len = h1.size();
    	if (!h1.get(len-5).contains("doctor"))
    		return false;
    	else if (!h1.get(len-4).contains("who treat"))
    		return false;
    	else if (!h1.get(len-3).equalsIgnoreCase(condition))
    		return false;
    	else if (!h1.get(len-2).equals("near"))
    		return false;
    	else return h1.get(len - 1).equals(locationTerm);
    }

    private boolean h1SpecialtySearch (List<String> h1, String locationTerm, String spec) {
    	int len = h1.size();
    	if (!h1.get(len-3).contains(spec.toLowerCase()))
    		return false;
    	else if (!h1.get(len-2).equals("near"))
    		return false;
    	else return h1.get(len - 1).equals(locationTerm);
    }

    private boolean h1UccSearch (List<String> h1, String locationTerm) {
        int len = h1.size();
        if (!h1.get(len-5).equals("Urgent Care Centers"))
            return false;
        else if (!h1.get(len-4).equals("named"))
            return false;
        else if (!h1.get(len-3).contains(uccName))
            return false;
        else if (!h1.get(len-2).equals("near"))
            return false;
        else return h1.get(len - 1).equals(locationTerm);
    }

    private boolean h1UccBrowse (List<String> h1, String locationTerm) {
        int len = h1.size();
        if (!h1.get(len-4).equals("Urgent Care Centers"))
            return false;
        else if (!h1.get(len-2).equals("near"))
            return false;
        else return h1.get(len - 1).equals(locationTerm);
    }

    private boolean breadcrumbSearch(SearchResultsPage results) {
    	if (!results.breadcrumbCurrent().getText().toString().equals("Search"))
    		return false;
    	else if (!results.getBreadcrumbText().get(0).equals("Home"))
    	   return false;
    	else return results.getBreadcrumbUrl().get(0).contains(url_plain.toLowerCase());
    }

    private boolean breadcrumbCondition(SearchResultsPage results) {
    	if (!results.breadcrumbCurrent().getText().toString().equals("Diabetes experts"))
    		return false;
    	else if (!results.getBreadcrumbText().get(0).equals("Home"))
    		return false;
    	else if (!results.getBreadcrumbText().get(1).equals("Find a doctor by condition"))
    		return false;
    	else if (!results.getBreadcrumbUrl().get(0).contains(url_plain.toLowerCase()))
    		return false;
    	else return !(!results.getBreadcrumbUrl().get(1).contains(url_plain.toLowerCase()) ||
                    !results.getBreadcrumbUrl().get(1).contains("/conditions"));
    }

    private boolean breadcrumbSpecialty(SearchResultsPage results) {
    	if (!results.breadcrumbCurrent().getText().toString().equals("Find a Dermatologist"))
    		return false;
    	else if (!results.getBreadcrumbText().get(0).equals("Home"))
    		return false;
    	else return results.getBreadcrumbUrl().get(0).contains(url_plain.toLowerCase());
    }

    private boolean breadcrumbSpecialtyInsurance(SearchResultsPage results) {
    	if (!results.breadcrumbCurrent().getText().toString().equals(insurance))
    		return false;
    	else if (!results.getBreadcrumbText().get(0).equals("Home"))
    		return false;
    	else if (!results.getBreadcrumbText().get(1).equals("Find a Dermatologist"))
    		return false;
    	else if (!results.getBreadcrumbUrl().get(0).contains(url_plain.toLowerCase()))
    		return false;
    	else return !(!results.getBreadcrumbUrl().get(1).contains(url_plain.toLowerCase()) ||
                    !results.getBreadcrumbUrl().get(1).contains("/dermatologists"));
    }

    private boolean breadcrumbSpecialtyInsurancePlan(SearchResultsPage results) {
    	if (!results.breadcrumbCurrent().getText().toString().equals(plan))
    		return false;
    	else if (!results.getBreadcrumbText().get(0).equals("Home"))
    		return false;
    	else if (!results.getBreadcrumbText().get(1).equals("Find a Dermatologist"))
    		return false;
    	else if (!results.getBreadcrumbText().get(2).equals(insurance))
    		return false;
    	else if (!results.getBreadcrumbUrl().get(0).contains(url_plain.toLowerCase()))
    		return false;
    	else if (!results.getBreadcrumbUrl().get(1).contains(url_plain.toLowerCase()) ||
    			!results.getBreadcrumbUrl().get(1).contains("/dermatologists"))
    			return false;
    	else return !(!results.getBreadcrumbUrl().get(2).contains(url_plain.toLowerCase()) ||
                    !results.getBreadcrumbUrl().get(2).contains("/dermatologists/united-healthcare"));
    }

    private boolean breadcrumbSpecialtyCity(SearchResultsPage results) {
    	if (!results.breadcrumbCurrent().getText().toString().equals("New York Dermatologists"))
    		return false;
    	else if (!results.getBreadcrumbText().get(0).equals("Home"))
    		return false;
    	else if (!results.getBreadcrumbText().get(1).equals("Find a Dermatologist"))
    		return false;
    	else if (!results.getBreadcrumbText().get(2).equals("NY"))
    		return false;
    	else if (!results.getBreadcrumbUrl().get(0).contains(url_plain.toLowerCase()))
    		return false;
    	else if (!results.getBreadcrumbUrl().get(1).contains(url_plain.toLowerCase()) ||
    			!results.getBreadcrumbUrl().get(1).contains("/dermatologists"))
    			return false;
    	else return !(!results.getBreadcrumbUrl().get(2).contains(url_plain.toLowerCase()) ||
                    !results.getBreadcrumbUrl().get(2).contains("/locations/dermatologists/ny"));
    }

    private boolean breadcrumbSpecialtyCityInsurance(SearchResultsPage results) {
    	if (!results.breadcrumbCurrent().getText().toString().equals(insurance))
    		return false;
    	else if (!results.getBreadcrumbText().get(0).equals("Home"))
    		return false;
    	else if (!results.getBreadcrumbText().get(1).equals("Find a Dermatologist"))
    		return false;
    	else if (!results.getBreadcrumbText().get(2).equals("NY"))
    		return false;
    	else if (!results.getBreadcrumbText().get(3).equals("New York Dermatologists"))
    		return false;
    	else if (!results.getBreadcrumbUrl().get(0).contains(url_plain.toLowerCase()))
    		return false;
    	else if (!results.getBreadcrumbUrl().get(1).contains(url_plain.toLowerCase()) ||
    			!results.getBreadcrumbUrl().get(1).contains("/dermatologists"))
    			return false;
    	else if (!results.getBreadcrumbUrl().get(2).contains(url_plain.toLowerCase()) ||
    			!results.getBreadcrumbUrl().get(2).contains("/locations/dermatologists/ny"))
    			return false;
    	else if (!results.getBreadcrumbUrl().get(3).contains(url_plain.toLowerCase()) ||
    			!results.getBreadcrumbUrl().get(3).contains("/dermatologists/ny/new-york"))
    			return false;
    	else
    		return true;
    }

    private boolean breadcrumbUccCityBrowse(SearchResultsPage results) {
        if (!results.breadcrumbCurrent().getText().toString().equals("New York urgent care centers"))
            return false;
        else if (!results.getBreadcrumbText().get(0).equals("Home"))
            return false;
        else if (!results.getBreadcrumbText().get(1).equals("Find an urgent care center"))
            return false;
        else if (!results.getBreadcrumbText().get(2).equals("NY"))
            return false;
        else if (!results.getBreadcrumbUrl().get(0).contains(url_plain.toLowerCase()))
            return false;
        else if (!results.getBreadcrumbUrl().get(1).contains(url_plain.toLowerCase()) ||
                !results.getBreadcrumbUrl().get(1).contains("/urgent-care"))
            return false;
        else if (!results.getBreadcrumbUrl().get(2).contains(url_plain.toLowerCase()) ||
                !results.getBreadcrumbUrl().get(2).contains("/locations/urgent-care/ny"))
            return false;
        else
            return true;
    }

    private boolean breadcrumbUccMainBrowse(SearchResultsPage results) {
        if (!results.breadcrumbCurrent().getText().toString().equals("Find an urgent care center"))
            return false;
        else if (!results.getBreadcrumbText().get(0).equals("Home"))
            return false;
        else if (!results.getBreadcrumbUrl().get(0).contains(url_plain.toLowerCase()))
            return false;
        else
            return true;
    }
}