package com.vitals.test;

import com.vitalsqa.listener.DriverManager;
import com.vitalsqa.testrail.TestCase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.vitals.pages.HomePage;
import com.vitals.pages.SearchResultsPage;
import java.util.List;

public class SearchPagePropertiesTest {

    WebDriver driver;
    SoftAssert m_assert;
    String url;
    String name = "Todd";
    String insurance = "United Healthcare";
    String plan = "Definity Choice Plus";
    String condition = "Diabetes";
    String specialty = "Dermatologist";
    String dentistSpecialty = "Dentist";
    
    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) throws Exception {
        this.url = url;
    }
    
    @TestCase(id=1672)
    @Test
    public void nameSearchInsurancePlan() {
        driver = DriverManager.getDriver();

        driver.get(url);
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);

        homePage.header.openInsuranceModal();
        homePage.header.enterInsuranceCompany(insurance);
        homePage.header.selectFirstInsurance();
        homePage.header.openInsurancePlan();
        homePage.header.selectFirstInsurancePlan();
        homePage.header.clickSaveInsuranceButton();
        homePage.header.enterSearchTerm(name);

        SearchResultsPage results = homePage.header.clickGoButton();
        
        m_assert = new SoftAssert();
        boolean breadcrumb = breadcrumbSearch(results);
        m_assert.assertTrue(breadcrumb, "Breadcrumb text or link is incorrect");
        Reporter.log(results.getBreadcrumbText().toString() + results.getBreadcrumbCurrentText() + results.getBreadcrumbUrl().toString());
        boolean h1 = h1NameSearch(results.getH1Text(), results.header.getCurrentPopulatedLocation(), "doctor");
        boolean ins = (results.getH1Text().get(0)).equalsIgnoreCase(insurance + " - " + plan);
        m_assert.assertTrue(h1 && ins, "H1 is incorrect");
        Reporter.log(results.getH1Text().toString());

        m_assert.assertTrue(driver.getTitle().equals("Find a Doctor or Dentist - Vitals.com"), 
        		"Title is incorrect");
        m_assert.assertTrue(results.getDescription().equals("Find a doctor or dentist near you on Vitals.com"), 
        		"Meta description is incorrect");
        
        m_assert.assertAll();
    }
    
    @TestCase(id=1673)
    @Test
    public void nameSearchInsuranceCompany() {
        driver = DriverManager.getDriver();

        driver.get(url);
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);

        homePage.header.openInsuranceModal();
        homePage.header.enterInsuranceCompany(insurance);
        homePage.header.selectFirstInsurance();
        homePage.header.clickSaveInsuranceButton();
        homePage.header.enterSearchTerm(name);

        SearchResultsPage results = homePage.header.clickGoButton();
        
        m_assert = new SoftAssert();
        boolean breadcrumb = breadcrumbSearch(results);
        m_assert.assertTrue(breadcrumb, "Breadcrumb text or link is incorrect");
        Reporter.log(results.getBreadcrumbText().toString() + results.getBreadcrumbCurrentText() + results.getBreadcrumbUrl().toString());
        boolean h1 = h1NameSearch(results.getH1Text(),results.header.getCurrentPopulatedLocation(), "doctor");
        boolean ins = (results.getH1Text().get(0)).equalsIgnoreCase(insurance);
        m_assert.assertTrue(h1 && ins, "H1 is incorrect");
        Reporter.log(results.getH1Text().toString());

        m_assert.assertTrue(driver.getTitle().equals("Find a Doctor or Dentist - Vitals.com"), 
        		"Title is incorrect");
        m_assert.assertTrue(results.getDescription().equals("Find a doctor or dentist near you on Vitals.com"), 
        		"Meta description is incorrect");
        
        m_assert.assertAll();
    }
    
    @TestCase(id=1674)
    @Test
    public void nameSearchNoInsurance() {
        driver = DriverManager.getDriver();
        driver.manage().deleteAllCookies();
        driver.get(url);
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.header.enterSearchTerm(name);

        SearchResultsPage results = homePage.header.clickGoButton();
        
        m_assert = new SoftAssert();
        boolean breadcrumb = breadcrumbSearch(results);
        m_assert.assertTrue(breadcrumb, "Breadcrumb text or link is incorrect");
        Reporter.log(results.getBreadcrumbText().toString() + results.getBreadcrumbCurrentText() + results.getBreadcrumbUrl().toString());
        boolean h1 = h1NameSearch(results.getH1Text(),results.header.getCurrentPopulatedLocation(), "doctor");
        m_assert.assertTrue(h1, "H1 is incorrect");
        Reporter.log(results.getH1Text().toString());

        m_assert.assertTrue(driver.getTitle().equals("Find a Doctor or Dentist - Vitals.com"), 
        		"Title is incorrect");
        m_assert.assertTrue(results.getDescription().equals("Find a doctor or dentist near you on Vitals.com"), 
        		"Meta description is incorrect");
        
        m_assert.assertAll();
    }
    
    @TestCase(id=1675)
    @Test
    public void nameSearchDentist() {
        driver = DriverManager.getDriver();
        driver.manage().deleteAllCookies();
        driver.get(url);
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.header.openFindDropdown();
        homePage.header.selectFindByDentist();
        homePage.header.enterSearchTerm(name);

        SearchResultsPage results = homePage.header.clickGoButton();
        
        m_assert = new SoftAssert();
        boolean breadcrumb = breadcrumbSearch(results);
        m_assert.assertTrue(breadcrumb, "Breadcrumb text or link is incorrect");
        Reporter.log(results.getBreadcrumbText().toString() + results.getBreadcrumbCurrentText() + results.getBreadcrumbUrl().toString());
        boolean h1 = h1NameSearch(results.getH1Text(),results.header.getCurrentPopulatedLocation(), "dentist");
        m_assert.assertTrue(h1, "H1 is incorrect");
        Reporter.log(results.getH1Text().toString());

        m_assert.assertTrue(driver.getTitle().equals("Find a Doctor or Dentist - Vitals.com"), 
        		"Title is incorrect");
        m_assert.assertTrue(results.getDescription().equals("Find a doctor or dentist near you on Vitals.com"), 
        		"Meta description is incorrect");
        
        m_assert.assertAll();
    }
    
    @TestCase(id=1676)
    @Test
    public void conditionSearchInsurancePlan() {
        driver = DriverManager.getDriver();

        driver.get(url);
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);

        homePage.header.openInsuranceModal();
        homePage.header.enterInsuranceCompany(insurance);
        homePage.header.selectFirstInsurance();
        homePage.header.openInsurancePlan();
        homePage.header.selectFirstInsurancePlan();
        homePage.header.clickSaveInsuranceButton();
        homePage.header.enterSearchTerm(condition);
        
        SearchResultsPage results = homePage.header.selectFirstCondition();
        
        m_assert = new SoftAssert();
        boolean breadcrumb = breadcrumbSearch(results);
        m_assert.assertTrue(breadcrumb, "Breadcrumb text or link is incorrect");
        Reporter.log(results.getBreadcrumbText().toString() + results.getBreadcrumbCurrentText() + results.getBreadcrumbUrl().toString());
        boolean h1 = h1ConditionSearch(results.getH1Text(), results.header.getCurrentPopulatedLocation());
        boolean ins = (results.getH1Text().get(0)).equalsIgnoreCase(insurance + " - " + plan);
        m_assert.assertTrue(h1 && ins, "H1 is incorrect");
        Reporter.log(results.getH1Text().toString());

        m_assert.assertTrue(driver.getTitle().equals("Find a Doctor or Dentist - Vitals.com"), 
        		"Title is incorrect");
        m_assert.assertTrue(results.getDescription().equals("Find a doctor or dentist near you on Vitals.com"), 
        		"Meta description is incorrect");
        
        m_assert.assertAll();
    }
    
    @TestCase(id=1677)
    @Test
    public void conditionSearchInsuranceCompany() {
        driver = DriverManager.getDriver();

        driver.get(url);
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);

        homePage.header.openInsuranceModal();
        homePage.header.enterInsuranceCompany(insurance);
        homePage.header.selectFirstInsurance();
        homePage.header.clickSaveInsuranceButton();
        homePage.header.enterSearchTerm(condition);
        
        SearchResultsPage results = homePage.header.selectFirstCondition();
        
        m_assert = new SoftAssert();
        boolean breadcrumb = breadcrumbSearch(results);
        m_assert.assertTrue(breadcrumb, "Breadcrumb text or link is incorrect");
        Reporter.log(results.getBreadcrumbText().toString() + results.getBreadcrumbCurrentText() + results.getBreadcrumbUrl().toString());
        boolean h1 = h1ConditionSearch(results.getH1Text(),results.header.getCurrentPopulatedLocation());
        boolean ins = (results.getH1Text().get(0)).equalsIgnoreCase(insurance);
        m_assert.assertTrue(h1 && ins, "H1 is incorrect");
        Reporter.log(results.getH1Text().toString());

        m_assert.assertTrue(driver.getTitle().equals("Find a Doctor or Dentist - Vitals.com"), 
        		"Title is incorrect");
        m_assert.assertTrue(results.getDescription().equals("Find a doctor or dentist near you on Vitals.com"), 
        		"Meta description is incorrect");
        
        m_assert.assertAll();
    }
    
    @TestCase(id=1678)
    @Test
    public void conditionSearchNoInsurance() {
        driver = DriverManager.getDriver();
        driver.manage().deleteAllCookies();
        driver.get(url);
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.header.enterSearchTerm(condition);
        
        SearchResultsPage results = homePage.header.selectFirstCondition();
        
        m_assert = new SoftAssert();
        boolean breadcrumb = breadcrumbSearch(results);
        m_assert.assertTrue(breadcrumb, "Breadcrumb text or link is incorrect");
        Reporter.log(results.getBreadcrumbText().toString() + results.getBreadcrumbCurrentText() + results.getBreadcrumbUrl().toString());
        boolean h1 = h1ConditionSearch(results.getH1Text(),results.header.getCurrentPopulatedLocation());
        m_assert.assertTrue(h1, "H1 is incorrect");
        Reporter.log(results.getH1Text().toString());

        m_assert.assertTrue(driver.getTitle().equals("Find a Doctor or Dentist - Vitals.com"), 
        		"Title is incorrect");
        m_assert.assertTrue(results.getDescription().equals("Find a doctor or dentist near you on Vitals.com"), 
        		"Meta description is incorrect");
        
        m_assert.assertAll();
    }
    
    @TestCase(id=1679)
    @Test
    public void specialtySearchInsurancePlan() {
        driver = DriverManager.getDriver();

        driver.get(url);
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);

        homePage.header.openInsuranceModal();
        homePage.header.enterInsuranceCompany(insurance);
        homePage.header.selectFirstInsurance();
        homePage.header.openInsurancePlan();
        homePage.header.selectFirstInsurancePlan();
        homePage.header.clickSaveInsuranceButton();
        homePage.header.enterSearchTerm(specialty);
        SearchResultsPage results = homePage.header.selectFirstSpecialty();
        
        m_assert = new SoftAssert();
        boolean breadcrumb = breadcrumbSearch(results);
        m_assert.assertTrue(breadcrumb, "Breadcrumb text or link is incorrect");
        Reporter.log(results.getBreadcrumbText().toString() + results.getBreadcrumbCurrentText() + results.getBreadcrumbUrl().toString());
        boolean h1 = h1SpecialtySearch(results.getH1Text(), results.header.getCurrentPopulatedLocation(), specialty);
        boolean ins = (results.getH1Text().get(0)).equalsIgnoreCase(insurance + " - " + plan);
        m_assert.assertTrue(h1 && ins, "H1 is incorrect");
        Reporter.log(results.getH1Text().toString());

        m_assert.assertTrue(driver.getTitle().equals("Find a Doctor or Dentist - Vitals.com"), 
        		"Title is incorrect");
        m_assert.assertTrue(results.getDescription().equals("Find a doctor or dentist near you on Vitals.com"), 
        		"Meta description is incorrect");
        
        m_assert.assertAll();
    }
    
    @TestCase(id=1680)
    @Test
    public void specialtySearchInsuranceCompany() {
        driver = DriverManager.getDriver();

        driver.get(url);
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);

        homePage.header.openInsuranceModal();
        homePage.header.enterInsuranceCompany(insurance);
        homePage.header.selectFirstInsurance();
        homePage.header.clickSaveInsuranceButton();
        homePage.header.enterSearchTerm(specialty);
        
        SearchResultsPage results = homePage.header.selectFirstSpecialty();
        
        m_assert = new SoftAssert();
        boolean breadcrumb = breadcrumbSearch(results);
        m_assert.assertTrue(breadcrumb, "Breadcrumb text or link is incorrect");
        Reporter.log(results.getBreadcrumbText().toString() + results.getBreadcrumbCurrentText() + results.getBreadcrumbUrl().toString());
        boolean h1 = h1SpecialtySearch(results.getH1Text(),results.header.getCurrentPopulatedLocation(), specialty);
        boolean ins = (results.getH1Text().get(0)).equalsIgnoreCase(insurance);
        m_assert.assertTrue(h1 && ins, "H1 is incorrect");
        Reporter.log(results.getH1Text().toString());

        m_assert.assertTrue(driver.getTitle().equals("Find a Doctor or Dentist - Vitals.com"), 
        		"Title is incorrect");
        m_assert.assertTrue(results.getDescription().equals("Find a doctor or dentist near you on Vitals.com"), 
        		"Meta description is incorrect");
        
        m_assert.assertAll();
    }
    
    @TestCase(id=1681)
    @Test
    public void specialtySearchNoInsurance() {
        driver = DriverManager.getDriver();
        driver.manage().deleteAllCookies();
        driver.get(url);
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.header.enterSearchTerm(specialty);
        
        SearchResultsPage results = homePage.header.selectFirstSpecialty();
        
        m_assert = new SoftAssert();
        boolean breadcrumb = breadcrumbSearch(results);
        m_assert.assertTrue(breadcrumb, "Breadcrumb text or link is incorrect");
        Reporter.log(results.getBreadcrumbText().toString() + results.getBreadcrumbCurrentText() + results.getBreadcrumbUrl().toString());
        boolean h1 = h1SpecialtySearch(results.getH1Text(),results.header.getCurrentPopulatedLocation(), specialty);
        m_assert.assertTrue(h1, "H1 is incorrect");
        Reporter.log(results.getH1Text().toString());
        
        m_assert.assertTrue(driver.getTitle().equals("Find a Doctor or Dentist - Vitals.com"), 
        		"Title is incorrect");
        m_assert.assertTrue(results.getDescription().equals("Find a doctor or dentist near you on Vitals.com"), 
        		"Meta description is incorrect");
        
        m_assert.assertAll();
    }
    
    @TestCase(id=1682)
    @Test
    public void specialtySearchDentist() {
        driver = DriverManager.getDriver();
        driver.manage().deleteAllCookies();
        driver.get(url);
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.header.openFindDropdown();
        homePage.header.selectFindByDentist();
        homePage.header.enterSearchTerm(dentistSpecialty);
        
        SearchResultsPage results = homePage.header.selectFirstSpecialty();
        
        m_assert = new SoftAssert();
        boolean breadcrumb = breadcrumbSearch(results);
        m_assert.assertTrue(breadcrumb, "Breadcrumb text or link is incorrect");
        Reporter.log(results.getBreadcrumbText().toString() + results.getBreadcrumbCurrentText() + results.getBreadcrumbUrl().toString());
        boolean h1 = h1SpecialtySearch(results.getH1Text(),results.header.getCurrentPopulatedLocation(), dentistSpecialty);
        m_assert.assertTrue(h1, "H1 is incorrect");
        Reporter.log(results.getH1Text().toString());
        
        m_assert.assertTrue(driver.getTitle().equals("Find a Doctor or Dentist - Vitals.com"), 
        		"Title is incorrect");
        m_assert.assertTrue(results.getDescription().equals("Find a doctor or dentist near you on Vitals.com"), 
        		"Meta description is incorrect");
        
        m_assert.assertAll();
    }
    
    @TestCase(id=1683)
    @Test
    public void conditionBrowse() {
        driver = DriverManager.getDriver();

        driver.get(url + "/condition/diabetes");
        
        SearchResultsPage results = PageFactory.initElements(driver, SearchResultsPage.class);
        
        m_assert = new SoftAssert();
        boolean breadcrumb = breadcrumbCondition(results);
        m_assert.assertTrue(breadcrumb, "Breadcrumb text or link is incorrect");
        Reporter.log(results.getBreadcrumbText().toString() + results.getBreadcrumbCurrentText() + results.getBreadcrumbUrl().toString());
        boolean h1 = h1ConditionSearch(results.getH1Text(),results.header.getCurrentPopulatedLocation());
        m_assert.assertTrue(h1, "H1 is incorrect");
        Reporter.log(results.getH1Text().toString());
        
        m_assert.assertTrue(driver.getTitle().equals("Diabetes Experts - Read Patient Reviews & Get Informed - Vitals.com"), 
        		"Title is incorrect");
        m_assert.assertTrue(results.getDescription().equals("Find a diabetes expert near you, read patient reviews, and get informed on Vitals.com"), 
        		"Meta description is incorrect");
        
        m_assert.assertAll();
    }
    
    @TestCase(id=1684)
    @Test
    public void specialtyBrowse() {
        driver = DriverManager.getDriver();

        driver.get(url + "/dermatologists");
        
        SearchResultsPage results = PageFactory.initElements(driver, SearchResultsPage.class);
        
        m_assert = new SoftAssert();
        boolean breadcrumb = breadcrumbSpecialty(results);
        m_assert.assertTrue(breadcrumb, "Breadcrumb text or link is incorrect");
        Reporter.log(results.getBreadcrumbText().toString() + results.getBreadcrumbCurrentText() + results.getBreadcrumbUrl().toString());
        boolean h1 = h1SpecialtySearch(results.getH1Text(),results.header.getCurrentPopulatedLocation(), specialty);
        m_assert.assertTrue(h1, "H1 is incorrect");
        Reporter.log(results.getH1Text().toString());
        
        m_assert.assertTrue(driver.getTitle().equals("Find a Dermatologist near you, Read Patient Reviews, & Get Informed - Vitals.com"), 
        		"Title is incorrect");
        m_assert.assertTrue(results.getDescription().equals("Find dermatologists, read patient reviews, and get informed on Vitals.com"), 
        		"Meta description is incorrect");

        m_assert.assertAll();
    }
    
    @TestCase(id=1685)
    @Test
    public void specialtyBrowseInsurance() {
        driver = DriverManager.getDriver();

        driver.get(url + "/dermatologists/united-healthcare");
        
        SearchResultsPage results = PageFactory.initElements(driver, SearchResultsPage.class);
        
        m_assert = new SoftAssert();
        boolean breadcrumb = breadcrumbSpecialtyInsurance(results);
        m_assert.assertTrue(breadcrumb, "Breadcrumb text or link is incorrect");
        Reporter.log(results.getBreadcrumbText().toString() + results.getBreadcrumbCurrentText() + results.getBreadcrumbUrl().toString());
        boolean h1 = h1SpecialtySearch(results.getH1Text(),results.header.getCurrentPopulatedLocation(), specialty);
        m_assert.assertTrue(h1, "H1 is incorrect");
        Reporter.log(results.getH1Text().toString());
        
        m_assert.assertTrue(driver.getTitle().equals("Find United Healthcare Dermatologists near you, Read Patient Reviews, & Get Informed - Vitals.com"), 
        		"Title is incorrect");
        m_assert.assertTrue(results.getDescription().equals("Find United Healthcare dermatologists near you, read patient reviews, and get informed on Vitals.com"), 
        		"Meta description is incorrect");

        m_assert.assertAll();
    }
    
    @TestCase(id=1686)
    @Test
    public void specialtyBrowseInsurancePlan() {
        driver = DriverManager.getDriver();

        driver.get(url + "/dermatologists/united-healthcare/definity-choice-plus");
        
        SearchResultsPage results = PageFactory.initElements(driver, SearchResultsPage.class);
        
        m_assert = new SoftAssert();
        boolean breadcrumb = breadcrumbSpecialtyInsurancePlan(results);
        m_assert.assertTrue(breadcrumb, "Breadcrumb text or link is incorrect");
        Reporter.log(results.getBreadcrumbText().toString() + results.getBreadcrumbCurrentText() + results.getBreadcrumbUrl().toString());
        boolean h1 = h1SpecialtySearch(results.getH1Text(),results.header.getCurrentPopulatedLocation(), specialty);
        boolean ins = (results.getH1Text().get(0)).equalsIgnoreCase(insurance + " - " + plan);
        m_assert.assertTrue(h1 && ins, "H1 is incorrect");
        Reporter.log(results.getH1Text().toString());
        
        m_assert.assertTrue(driver.getTitle().equals("Find Definity Choice Plus Dermatologists near you, Read Patient Reviews, & Get Informed - Vitals.com"), 
        		"Title is incorrect");
        m_assert.assertTrue(results.getDescription().equals("Find Definity Choice Plus dermatologists near you, read patient reviews, and get informed on Vitals.com"), 
        		"Meta description is incorrect");

        m_assert.assertAll();
    }
    
    @TestCase(id=1687)
    @Test
    public void specialtyBrowseCity() {
        driver = DriverManager.getDriver();

        driver.get(url + "/dermatologists/ny/new-york");
        
        SearchResultsPage results = PageFactory.initElements(driver, SearchResultsPage.class);
        
        m_assert = new SoftAssert();
        boolean breadcrumb = breadcrumbSpecialtyCity(results);
        m_assert.assertTrue(breadcrumb, "Breadcrumb text or link is incorrect");
        Reporter.log(results.getBreadcrumbText().toString() + results.getBreadcrumbCurrentText() + results.getBreadcrumbUrl().toString());
        boolean h1 = h1SpecialtySearch(results.getH1Text(),results.header.getCurrentPopulatedLocation(), specialty);
        m_assert.assertTrue(h1, "H1 is incorrect");
        Reporter.log(results.getH1Text().toString());
        
        m_assert.assertTrue(driver.getTitle().equals("New York, NY Dermatologists - Read Patient Reviews & Get Informed - Vitals.com"), 
        		"Title is incorrect");
        m_assert.assertTrue(results.getDescription().equals("Find dermatologists in New York, NY, read patient reviews, and get informed on Vitals.com"), 
        		"Meta description is incorrect");
        
        m_assert.assertAll();
    }
    
    @TestCase(id=1688)
    @Test
    public void specialtyBrowseCityInsurance() {
        driver = DriverManager.getDriver();

        driver.get(url + "/dermatologists/ny/new-york/united-healthcare");
        
        SearchResultsPage results = PageFactory.initElements(driver, SearchResultsPage.class);
        
        m_assert = new SoftAssert();
        boolean breadcrumb = breadcrumbSpecialtyCityInsurance(results);
        m_assert.assertTrue(breadcrumb, "Breadcrumb text or link is incorrect");
        Reporter.log(results.getBreadcrumbText().toString() + results.getBreadcrumbCurrentText() + results.getBreadcrumbUrl().toString());
        boolean h1 = h1SpecialtySearch(results.getH1Text(),results.header.getCurrentPopulatedLocation(), specialty);
        m_assert.assertTrue(h1, "H1 is incorrect");
        Reporter.log(results.getH1Text().toString());
       
        m_assert.assertTrue(driver.getTitle().equals("United Healthcare Dermatologists in New York, NY - Read Patient Reviews & Get Informed - Vitals.com"), 
        		"Title is incorrect");
        m_assert.assertTrue(results.getDescription().equals("Find United Healthcare dermatologists in New York, NY, read patient reviews, and get informed on Vitals.com"), 
        		"Meta description is incorrect");
      
        m_assert.assertAll();
    }
    
    
    private boolean h1NameSearch (List<String> h1, String locationTerm, String type) {
    	int len = h1.size();
    	if (!h1.get(len-5).contains(type))
    		return false;
    	else if (!h1.get(len-4).equals("named"))
    		return false;
    	else if (!h1.get(len-3).contains(name))
    		return false;
    	else if (!h1.get(len-2).equals("in"))
    		return false;
    	else return h1.get(len - 1).equals(locationTerm);
    }
    
    private boolean h1ConditionSearch (List<String> h1, String locationTerm) {
    	int len = h1.size();
    	if (!h1.get(len-5).contains("doctor"))
    		return false;
    	else if (!h1.get(len-4).contains("who treat"))
    		return false;
    	else if (!h1.get(len-3).equalsIgnoreCase(condition))
    		return false;
    	else if (!h1.get(len-2).equals("in"))
    		return false;
    	else return h1.get(len - 1).equals(locationTerm);
    }
    
    private boolean h1SpecialtySearch (List<String> h1, String locationTerm, String spec) {
    	int len = h1.size();
    	if (!h1.get(len-3).contains(spec.toLowerCase()))
    		return false;
    	else if (!h1.get(len-2).equals("in"))
    		return false;
    	else return h1.get(len - 1).equals(locationTerm);
    }
    
    private boolean breadcrumbSearch(SearchResultsPage results) {
    	if (!results.getBreadcrumbCurrentText().equals("Search"))
    		return false;
    	else if (!results.getBreadcrumbText().get(0).equals("Home"))
    	   return false;
    	else return results.getBreadcrumbUrl().get(0).contains(url.toLowerCase());
    }
    
    private boolean breadcrumbCondition(SearchResultsPage results) {
    	if (!results.getBreadcrumbCurrentText().equals("Diabetes experts"))
    		return false;
    	else if (!results.getBreadcrumbText().get(0).equals("Home"))
    		return false;
    	else if (!results.getBreadcrumbText().get(1).equals("Find a doctor by condition"))
    		return false;
    	else if (!results.getBreadcrumbUrl().get(0).contains(url.toLowerCase()))
    		return false;
    	else return !(!results.getBreadcrumbUrl().get(1).contains(url.toLowerCase()) ||
                    !results.getBreadcrumbUrl().get(1).contains("/conditions"));
    }
    
    private boolean breadcrumbSpecialty(SearchResultsPage results) {
    	if (!results.getBreadcrumbCurrentText().equals("Find a Dermatologist"))
    		return false;
    	else if (!results.getBreadcrumbText().get(0).equals("Home"))
    		return false;
    	else return results.getBreadcrumbUrl().get(0).contains(url.toLowerCase());
    }
    
    private boolean breadcrumbSpecialtyInsurance(SearchResultsPage results) {
    	if (!results.getBreadcrumbCurrentText().equals(insurance))
    		return false;
    	else if (!results.getBreadcrumbText().get(0).equals("Home"))
    		return false;
    	else if (!results.getBreadcrumbText().get(1).equals("Find a Dermatologist"))
    		return false;
    	else if (!results.getBreadcrumbUrl().get(0).contains(url.toLowerCase()))
    		return false;
    	else return !(!results.getBreadcrumbUrl().get(1).contains(url.toLowerCase()) ||
                    !results.getBreadcrumbUrl().get(1).contains("/dermatologists"));
    }
    
    private boolean breadcrumbSpecialtyInsurancePlan(SearchResultsPage results) {
    	if (!results.getBreadcrumbCurrentText().equals(plan))
    		return false;
    	else if (!results.getBreadcrumbText().get(0).equals("Home"))
    		return false;
    	else if (!results.getBreadcrumbText().get(1).equals("Find a Dermatologist"))
    		return false;
    	else if (!results.getBreadcrumbText().get(2).equals(insurance))
    		return false;
    	else if (!results.getBreadcrumbUrl().get(0).contains(url.toLowerCase()))
    		return false;
    	else if (!results.getBreadcrumbUrl().get(1).contains(url.toLowerCase()) || 
    			!results.getBreadcrumbUrl().get(1).contains("/dermatologists"))
    			return false;
    	else return !(!results.getBreadcrumbUrl().get(2).contains(url.toLowerCase()) ||
                    !results.getBreadcrumbUrl().get(2).contains("/dermatologists/united-healthcare"));
    }
    
    private boolean breadcrumbSpecialtyCity(SearchResultsPage results) {
    	if (!results.getBreadcrumbCurrentText().equals("New York Dermatologists"))
    		return false;
    	else if (!results.getBreadcrumbText().get(0).equals("Home"))
    		return false;
    	else if (!results.getBreadcrumbText().get(1).equals("Find a Dermatologist"))
    		return false;
    	else if (!results.getBreadcrumbText().get(2).equals("NY"))
    		return false;
    	else if (!results.getBreadcrumbUrl().get(0).contains(url.toLowerCase()))
    		return false;
    	else if (!results.getBreadcrumbUrl().get(1).contains(url.toLowerCase()) || 
    			!results.getBreadcrumbUrl().get(1).contains("/dermatologists"))
    			return false;
    	else return !(!results.getBreadcrumbUrl().get(2).contains(url.toLowerCase()) ||
                    !results.getBreadcrumbUrl().get(2).contains("/locations/dermatologists/ny"));
    }
    
    private boolean breadcrumbSpecialtyCityInsurance(SearchResultsPage results) {
    	if (!results.getBreadcrumbCurrentText().equals(insurance))
    		return false;
    	else if (!results.getBreadcrumbText().get(0).equals("Home"))
    		return false;
    	else if (!results.getBreadcrumbText().get(1).equals("Find a Dermatologist"))
    		return false;
    	else if (!results.getBreadcrumbText().get(2).equals("NY"))
    		return false;
    	else if (!results.getBreadcrumbText().get(3).equals("New York Dermatologists"))
    		return false;
    	else if (!results.getBreadcrumbUrl().get(0).contains(url.toLowerCase()))
    		return false;
    	else if (!results.getBreadcrumbUrl().get(1).contains(url.toLowerCase()) || 
    			!results.getBreadcrumbUrl().get(1).contains("/dermatologists"))
    			return false;
    	else if (!results.getBreadcrumbUrl().get(2).contains(url.toLowerCase()) || 
    			!results.getBreadcrumbUrl().get(2).contains("/locations/dermatologists/ny"))
    			return false;
    	else if (!results.getBreadcrumbUrl().get(3).contains(url.toLowerCase()) || 
    			!results.getBreadcrumbUrl().get(3).contains("/dermatologists/ny/new-york"))
    			return false;
    	else
    		return true;
    }
}