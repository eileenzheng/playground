package com.uchc.test;

import com.uchc.pages.HomePage;
import com.uchc.pages.SearchResultsPage;
import com.uchc.pages.browsepath.CityStatePage;
import com.uchc.pages.browsepath.GeneralBrowsePage;
import com.uchc.pages.browsepath.NameAlphabetPage;
import com.vitalsqa.testrail.TestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class BrowsePathTest {

    private String url;
    private String doctorSpecialtyUrl;
    private String dentistSpecialtyUrl;
    private String podiatristSpecialtyURl;
    private SoftAssert m_assert;

    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) {
        this.url = url;
    }

    @TestCase(id=1923)
    @Test
    public void doctorsLandingPage() {
        m_assert = new SoftAssert();
        HomePage home = new HomePage();
        home.get(url);
        home.navbar().navBarDoctors().click();
        CityStatePage landing = new CityStatePage();

        m_assert.assertTrue(landing.stateLinks().size()==51, "Incorrect # of states on doctors landing page");
        m_assert.assertTrue(landing.h1().getText().toString().equals("Find a Doctor"), "Incorrect h1 on doctors landing page");
        m_assert.assertTrue(landing.navTabActive().getText().toString().equals("Doctors by State"), "Incorrect active tab on doctors landing page");

        landing.getRandom(landing.cityLinks()).click();
        SearchResultsPage serp = new SearchResultsPage();
        m_assert.assertTrue(serp.navTabActive().getText().toString().contains("Doctors"), "Incorrect active tab on serp");
        m_assert.assertTrue(serp.resultNames().size()>0, "No results on serp");
        m_assert.assertAll();
    }

    @TestCase(id=1924)
    @Test
    public void doctorsStatePage() {
        m_assert = new SoftAssert();
        CityStatePage landing = new CityStatePage();
        landing.get(url + "/drs");
        landing.getRandom(landing.stateLinks()).click();
        GeneralBrowsePage statePage = new GeneralBrowsePage();

        m_assert.assertTrue(statePage.links().size()>0, "No city on state page");
        m_assert.assertTrue(statePage.navTabActive().getText().toString().contains("Doctors"), "Incorrect active tab on state page");

        statePage.getRandom(statePage.links()).click();
        SearchResultsPage serp = new SearchResultsPage();
        m_assert.assertTrue(serp.navTabActive().getText().toString().contains("Doctors"), "Incorrect active tab on serp");
        m_assert.assertTrue(serp.resultNames().size()>0, "No results on serp");

        m_assert.assertAll();
    }

    @TestCase(id=1925)
    @Test
    public void doctorsSpecialtyPage() {
        m_assert = new SoftAssert();
        CityStatePage landing = new CityStatePage();
        landing.get(url + "/drs");
        landing.navTabLinks().get(1).click();
        GeneralBrowsePage specialtyPage = new GeneralBrowsePage();

        m_assert.assertTrue(landing.h1().getText().toString().equals("Find Doctors by Specialty"), "Incorrect h1 on specialty page");
        m_assert.assertTrue(specialtyPage.links().size()>0, "No specialty on specialty page");
        m_assert.assertTrue(specialtyPage.navTabActive().getText().toString().equals("Doctors by Specialty"), "Incorrect active tab on specialty page");

        specialtyPage.getRandom(specialtyPage.links()).click();
        doctorSpecialtyUrl = specialtyPage.getCurrentUrl();
        m_assert.assertAll();
    }

    @TestCase(id=1926)
    @Test(dependsOnMethods="doctorsSpecialtyPage")
    public void doctorsSpecialtyLandingPage() {
        m_assert = new SoftAssert();
        CityStatePage landing = new CityStatePage();
        landing.get(url);
        landing.get(doctorSpecialtyUrl);

        m_assert.assertTrue(landing.stateLinks().size()>0, "No states on specialty landing page");
        m_assert.assertTrue(landing.cityLinks().size()>0, "No cities on specialty landing page");
        m_assert.assertTrue(landing.navTabActive().getText().toString().equals("Doctors by Specialty"), "Incorrect active tab on specialty landing page");

        landing.getRandom(landing.cityLinks()).click();
        SearchResultsPage serp = new SearchResultsPage();
        m_assert.assertTrue(serp.navTabActive().getText().toString().contains("Doctors by Specialty"), "Incorrect active tab on serp");
        m_assert.assertTrue(serp.resultNames().size()>0, "No results on serp");
        m_assert.assertAll();
    }

    @TestCase(id=1927)
    @Test (dependsOnMethods="doctorsSpecialtyPage")
    public void doctorSpecialtyStatePage() {
        m_assert = new SoftAssert();
        CityStatePage landing = new CityStatePage();
        landing.get(url);
        landing.get(doctorSpecialtyUrl);
        landing.getRandom(landing.stateLinks()).click();
        GeneralBrowsePage statePage = new GeneralBrowsePage();

        m_assert.assertTrue(statePage.links().size()>0, "No city on state page");
        m_assert.assertTrue(statePage.navTabActive().getText().toString().contains("Doctors by Specialty"), "Incorrect active tab on state page");

        statePage.getRandom(statePage.links()).click();
        SearchResultsPage serp = new SearchResultsPage();
        m_assert.assertTrue(serp.navTabActive().getText().toString().contains("Doctors by Specialty"), "Incorrect active tab on serp");
        m_assert.assertTrue(serp.resultNames().size()>0, "No results on serp");

        m_assert.assertAll();
    }

    @TestCase(id=1928)
    @Test
    public void doctorsNamePage() {
        m_assert = new SoftAssert();
        CityStatePage landing = new CityStatePage();
        landing.get(url + "/drs");
        landing.navTabLinks().get(2).click();
        GeneralBrowsePage namePage = new GeneralBrowsePage();

        m_assert.assertTrue(namePage.links().size()==51, "Incorrect # of states on name page");
        m_assert.assertTrue(namePage.h1().getText().toString().equals("Find Doctors by Name"), "Incorrect h1 on name page");
        m_assert.assertTrue(namePage.navTabActive().getText().toString().equals("Doctors by Name"), "Incorrect active tab on name page");

        m_assert.assertAll();
    }

    @TestCase(id=1929)
    @Test
    public void doctorsAlphabetPage() {
        m_assert = new SoftAssert();
        GeneralBrowsePage namePage = new GeneralBrowsePage();
        namePage.get(url + "/drs/dr_name.html");
        namePage.getRandom(namePage.links()).click();
        NameAlphabetPage alphabetPage = new NameAlphabetPage();

        m_assert.assertTrue(alphabetPage.alphabets().size()>=25, "Less than 25 letters on alphabet page");
        m_assert.assertTrue(alphabetPage.navTabActive().getText().toString().contains("Doctors by Name"), "Incorrect active tab on alphabet page");

        alphabetPage.getRandom(alphabetPage.alphabets()).click();
        SearchResultsPage serp = new SearchResultsPage();
        m_assert.assertTrue(serp.navTabActive().getText().toString().contains("Doctors by Name"), "Incorrect active tab on serp");
        m_assert.assertTrue(serp.resultNames().size()>0, "No results on serp");

        m_assert.assertAll();
    }

    @TestCase(id=1930)
    @Test
    public void dentistsLandingPage() {
        m_assert = new SoftAssert();
        HomePage home = new HomePage();
        home.get(url);
        home.navbar().navBarDentists().click();
        CityStatePage landing = new CityStatePage();

        m_assert.assertTrue(landing.stateLinks().size()==51, "Incorrect # of states on dentist landing page");
        m_assert.assertTrue(landing.h1().getText().toString().equals("Find a Dentist"), "Incorrect h1 on dentist landing page");
        m_assert.assertTrue(landing.navTabActive().getText().toString().equals("Dentists by State"), "Incorrect active tab on dentist landing page");

        landing.getRandom(landing.cityLinks()).click();
        SearchResultsPage serp = new SearchResultsPage();
        m_assert.assertTrue(serp.navTabActive().getText().toString().contains("Dentist"), "Incorrect active tab on serp");
        m_assert.assertTrue(serp.resultNames().size()>0, "No results on serp");
        m_assert.assertAll();
    }

    @TestCase(id=1931)
    @Test
    public void dentistsStatePage() {
        m_assert = new SoftAssert();
        CityStatePage landing = new CityStatePage();
        landing.get(url + "/dentist");
        landing.getRandom(landing.stateLinks()).click();
        GeneralBrowsePage statePage = new GeneralBrowsePage();

        m_assert.assertTrue(statePage.links().size()>0, "No city on state page");
        m_assert.assertTrue(statePage.navTabActive().getText().toString().contains("Dentists"), "Incorrect active tab on state page");

        statePage.getRandom(statePage.links()).click();
        SearchResultsPage serp = new SearchResultsPage();
        m_assert.assertTrue(serp.navTabActive().getText().toString().contains("Dentists"), "Incorrect active tab on serp");
        m_assert.assertTrue(serp.resultNames().size()>0, "No results on serp");

        m_assert.assertAll();
    }

    @TestCase(id=1932)
    @Test
    public void dentistsSpecialtyPage() {
        m_assert = new SoftAssert();
        CityStatePage landing = new CityStatePage();
        landing.get(url + "/dentist");
        landing.navTabLinks().get(1).click();
        GeneralBrowsePage specialtyPage = new GeneralBrowsePage();

        m_assert.assertTrue(landing.h1().getText().toString().equals("Dentists by Specialty"), "Incorrect h1 on specialty page");
        m_assert.assertTrue(specialtyPage.links().size()>0, "No specialty on specialty page");
        m_assert.assertTrue(specialtyPage.navTabActive().getText().toString().equals("Dentists by Specialty"), "Incorrect active tab on specialty page");

        specialtyPage.getRandom(specialtyPage.links()).click();
        dentistSpecialtyUrl = specialtyPage.getCurrentUrl();

        m_assert.assertAll();
    }

    @TestCase(id=1933)
    @Test(dependsOnMethods="dentistsSpecialtyPage")
    public void dentistsSpecialtyLandingPage() {
        m_assert = new SoftAssert();
        CityStatePage landing = new CityStatePage();
        landing.get(url);
        landing.get(dentistSpecialtyUrl);

        m_assert.assertTrue(landing.stateLinks().size()>0, "No states on specialty landing page");
        m_assert.assertTrue(landing.cityLinks().size()>0, "No cities on specialty landing page");
        m_assert.assertTrue(landing.navTabActive().getText().toString().equals("Dentists by Specialty"), "Incorrect active tab on specialty landing page");

        landing.getRandom(landing.cityLinks()).click();
        SearchResultsPage serp = new SearchResultsPage();
        m_assert.assertTrue(serp.navTabActive().getText().toString().contains("Dentists by Specialty"), "Incorrect active tab on serp");
        m_assert.assertTrue(serp.resultNames().size()>0, "No results on serp");
        m_assert.assertAll();
    }

    @TestCase(id=1934)
    @Test (dependsOnMethods="dentistsSpecialtyPage")
    public void dentistsSpecialtyStatePage() {
        m_assert = new SoftAssert();
        CityStatePage landing = new CityStatePage();
        landing.get(url);
        landing.get(dentistSpecialtyUrl);
        landing.getRandom(landing.stateLinks()).click();
        GeneralBrowsePage statePage = new GeneralBrowsePage();

        m_assert.assertTrue(statePage.links().size()>0, "No city on state page");
        m_assert.assertTrue(statePage.navTabActive().getText().toString().contains("Dentists by Specialty"), "Incorrect active tab on state page");

        statePage.getRandom(statePage.links()).click();
        SearchResultsPage serp = new SearchResultsPage();
        m_assert.assertTrue(serp.navTabActive().getText().toString().contains("Dentists by Specialty"), "Incorrect active tab on serp");
        m_assert.assertTrue(serp.resultNames().size()>0, "No results on serp");

        m_assert.assertAll();
    }

    @TestCase(id=1935)
    @Test
    public void dentistsNamePage() {
        m_assert = new SoftAssert();
        CityStatePage landing = new CityStatePage();
        landing.get(url + "/dentist");
        landing.navTabLinks().get(2).click();
        GeneralBrowsePage namePage = new GeneralBrowsePage();

        m_assert.assertTrue(namePage.links().size()==51, "Incorrect # of states on name page");
        m_assert.assertTrue(namePage.h1().getText().toString().equals("Find Dentists by Name"), "Incorrect h1 on name page");
        m_assert.assertTrue(namePage.navTabActive().getText().toString().equals("Dentists by Name"), "Incorrect active tab on name page");

        m_assert.assertAll();
    }

    @TestCase(id=1936)
    @Test
    public void dentistsAlphabetPage() {
        m_assert = new SoftAssert();
        GeneralBrowsePage namePage = new GeneralBrowsePage();
        namePage.get(url + "/dentist/dentist_name.html");
        namePage.getRandom(namePage.links()).click();
        NameAlphabetPage alphabetPage = new NameAlphabetPage();

        m_assert.assertTrue(alphabetPage.alphabets().size()>20, "Less than 20 letters on alphabet page");
        m_assert.assertTrue(alphabetPage.navTabActive().getText().toString().contains("Dentists by Name"), "Incorrect active tab on alphabet page");

        alphabetPage.getRandom(alphabetPage.alphabets()).click();
        SearchResultsPage serp = new SearchResultsPage();
        m_assert.assertTrue(serp.navTabActive().getText().toString().contains("Dentists by Name"), "Incorrect active tab on serp");
        m_assert.assertTrue(serp.resultNames().size()>0, "No results on serp");

        m_assert.assertAll();
    }

    @TestCase(id=1937)
    @Test
    public void podiatristsLandingPage() {
        m_assert = new SoftAssert();
        HomePage home = new HomePage();
        home.get(url);
        home.navbar().navBarPodiatrists().click();
        CityStatePage landing = new CityStatePage();

        m_assert.assertTrue(landing.stateLinks().size()==51, "Incorrect # of states on podiatrists landing page");
        m_assert.assertTrue(landing.h1().getText().toString().equals("Find a Podiatrist"), "Incorrect h1 on podiatrist landing page");
        m_assert.assertTrue(landing.navTabActive().getText().toString().equals("Podiatrists by State"), "Incorrect active tab on podiatrist landing page");

        landing.getRandom(landing.cityLinks()).click();
        SearchResultsPage serp = new SearchResultsPage();
        m_assert.assertTrue(serp.navTabActive().getText().toString().contains("Podiatrists"), "Incorrect active tab on serp");
        m_assert.assertTrue(serp.resultNames().size()>0, "No results on serp");
        m_assert.assertAll();
    }

    @TestCase(id=1938)
    @Test
    public void podiatristsStatePage() {
        m_assert = new SoftAssert();
        CityStatePage landing = new CityStatePage();
        landing.get(url + "/podiatrist");
        landing.getRandom(landing.stateLinks()).click();
        GeneralBrowsePage statePage = new GeneralBrowsePage();

        m_assert.assertTrue(statePage.links().size()>0, "No city on state page");
        m_assert.assertTrue(statePage.navTabActive().getText().toString().contains("Podiatrists"), "Incorrect active tab on state page");

        statePage.getRandom(statePage.links()).click();
        SearchResultsPage serp = new SearchResultsPage();
        m_assert.assertTrue(serp.navTabActive().getText().toString().contains("Podiatrists"), "Incorrect active tab on serp");
        m_assert.assertTrue(serp.resultNames().size()>0, "No results on serp");

        m_assert.assertAll();
    }

    @TestCase(id=1939)
    @Test
    public void podiatristsSpecialtyPage() {
        m_assert = new SoftAssert();
        CityStatePage landing = new CityStatePage();
        landing.get(url + "/podiatrist");
        landing.navTabLinks().get(1).click();
        GeneralBrowsePage specialtyPage = new GeneralBrowsePage();

        m_assert.assertTrue(landing.h1().getText().toString().equals("Podiatrists by Specialty"), "Incorrect h1 on specialty page");
        m_assert.assertTrue(specialtyPage.links().size()>0, "No specialty on specialty page");
        m_assert.assertTrue(specialtyPage.navTabActive().getText().toString().equals("Podiatrists by Specialty"), "Incorrect active tab on specialty page");

        specialtyPage.getRandom(specialtyPage.links()).click();
        podiatristSpecialtyURl = specialtyPage.getCurrentUrl();

        m_assert.assertAll();
    }

    @TestCase(id=1940)
    @Test(dependsOnMethods="podiatristsSpecialtyPage")
    public void podiatristsSpecialtyLandingPage() {
        m_assert = new SoftAssert();
        CityStatePage landing = new CityStatePage();
        landing.get(url);
        landing.get(podiatristSpecialtyURl);

        m_assert.assertTrue(landing.stateLinks().size()>0, "No states on specialty landing page");
        m_assert.assertTrue(landing.cityLinks().size()>0, "No cities on specialty landing page");
        m_assert.assertTrue(landing.navTabActive().getText().toString().equals("Podiatrists by Specialty"), "Incorrect active tab on specialty landing page");

        landing.getRandom(landing.cityLinks()).click();
        SearchResultsPage serp = new SearchResultsPage();
        m_assert.assertTrue(serp.navTabActive().getText().toString().contains("Podiatrists by Specialty"), "Incorrect active tab on serp");
        m_assert.assertTrue(serp.resultNames().size()>0, "No results on serp");
        m_assert.assertAll();
    }

    @TestCase(id=1941)
    @Test (dependsOnMethods="podiatristsSpecialtyPage")
    public void podiatristsSpecialtyStatePage() {
        m_assert = new SoftAssert();
        CityStatePage landing = new CityStatePage();
        landing.get(url);
        landing.get(podiatristSpecialtyURl);
        landing.getRandom(landing.stateLinks()).click();
        GeneralBrowsePage statePage = new GeneralBrowsePage();

        m_assert.assertTrue(statePage.links().size()>0, "No city on state page");
        m_assert.assertTrue(statePage.navTabActive().getText().toString().contains("Podiatrists by Specialty"), "Incorrect active tab on state page");

        statePage.getRandom(statePage.links()).click();
        SearchResultsPage serp = new SearchResultsPage();
        m_assert.assertTrue(serp.navTabActive().getText().toString().contains("Podiatrists by Specialty"), "Incorrect active tab on serp");
        m_assert.assertTrue(serp.resultNames().size()>0, "No results on serp");

        m_assert.assertAll();
    }

    @TestCase(id=1942)
    @Test
    public void podiatristsNamePage() {
        m_assert = new SoftAssert();
        CityStatePage landing = new CityStatePage();
        landing.get(url + "/podiatrist");
        landing.navTabLinks().get(2).click();
        GeneralBrowsePage namePage = new GeneralBrowsePage();

        m_assert.assertTrue(namePage.links().size()==51, "Incorrect # of states on name page");
        m_assert.assertTrue(namePage.h1().getText().toString().equals("Find Podiatrists by Name"), "Incorrect h1 on name page");
        m_assert.assertTrue(namePage.navTabActive().getText().toString().equals("Podiatrists by Name"), "Incorrect active tab on name page");

        m_assert.assertAll();
    }

    @TestCase(id=1943)
    @Test
    public void podiatristsAlphabetPage() {
        m_assert = new SoftAssert();
        GeneralBrowsePage namePage = new GeneralBrowsePage();
        namePage.get(url + "/podiatrist/podiatrist_name.html");
        namePage.getRandom(namePage.links()).click();
        NameAlphabetPage alphabetPage = new NameAlphabetPage();

        m_assert.assertTrue(alphabetPage.alphabets().size()>10, "Less than 10 letters on alphabet page");
        m_assert.assertTrue(alphabetPage.navTabActive().getText().toString().contains("Podiatrists by Name"), "Incorrect active tab on alphabet page");

        alphabetPage.getRandom(alphabetPage.alphabets()).click();
        SearchResultsPage serp = new SearchResultsPage();
        m_assert.assertTrue(serp.navTabActive().getText().toString().contains("Podiatrists by Name"), "Incorrect active tab on serp");
        m_assert.assertTrue(serp.resultNames().size()>0, "No results on serp");

        m_assert.assertAll();
    }
}
