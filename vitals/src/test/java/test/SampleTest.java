package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchResultsPage;
import runners.RemoteTestRunner;

import java.util.ArrayList;
import java.util.List;

public class SampleTest extends RemoteTestRunner {

    WebDriver driver;
    static final String url = "http://www.vitals.com";

    Object[][] docs;
    //List<String> links;
    List<DrInfo> links;


    @DataProvider(name = "doctor-list", parallel = true)
    public Object[][] getDrs(ITestContext context) {
        docs = new Object[links.size()][];
        for (int i = 0; i<links.size(); i++) {
            docs[i] = new Object[] {links.get(i)};
        }
        return docs;
    }

    @Test
    public void simpleTest() {
        driver = getDriver();

        driver.get(url);
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);

        System.out.println(homePage.homePhotoImage());

        homePage.header.enterName("Smith");
        SearchResultsPage results = homePage.header.clickSearch();

        System.out.println("TOTAL RESULTS> " + results.getResultsCount());

        results.refinement.genderSelectMale();
        results.refinement.clickBoardCertified();
        results.refinement.clickUSEducated();

        System.out.println("TOTAL RESULTS> " + results.getResultsCount());

    }

    @Test
    public void checkAddresses() {
        driver = getDriver();

        driver.get(url);
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);

        System.out.println(homePage.homePhotoImage());

        homePage.header.enterName("Smith");
        SearchResultsPage results = homePage.header.clickSearch();

        System.out.println("TOTAL RESULTS> " + results.getResultsCount());

        List<WebElement> doctors = results.drList();
        links = new ArrayList<DrInfo>();

        for (WebElement dr : doctors) {
            links.add(new DrInfo(dr.findElement(By.cssSelector(".practice>address>span>span")).getText(), dr.findElement(By.cssSelector(".pic>a")).getAttribute("href")));
        }

    }

    @Test (dataProvider = "doctor-list", dependsOnMethods = {"checkAddresses"})
    public void checkTheDocTests(DrInfo info) {
        driver = getDriver();

        driver.get(info.profilePageUrl);

        System.out.println("Results Page> " + info.searchResultAddressOne);

        String addressProfile = driver.findElement(By.cssSelector(".info>div>address>span>span")).getText();

        System.out.println("Profile Page> " + addressProfile);

        Assert.assertEquals(addressProfile, info.searchResultAddressOne);
    }

    @Test
    public void checkTheOffset() {
        driver = getDriver();

        driver.get("http://www.vitals.com/specialists/allergists/louisiana/baton-rouge");
        WebElement topAd = driver.findElement(By.cssSelector(".advert.leaderboard-variable.cbox"));
        System.out.println(topAd.getAttribute("offsetLeft"));
        System.out.println(topAd.getAttribute("offsetWidth"));
        System.out.println(topAd.getAttribute("offsetTop"));

        driver.get("http://www.vitals.com/search?utf8=%E2%9C%93&type=name&provider_type=1&q=smith&location=New+York%2C+New+York");
        topAd = driver.findElement(By.cssSelector(".advert.leaderboard-variable.cbox"));
        System.out.println(topAd.getAttribute("offsetLeft"));
        System.out.println(topAd.getAttribute("offsetWidth"));
        System.out.println(topAd.getAttribute("offsetTop"));
    }

    private class DrInfo {
        String searchResultAddressOne;
        String profilePageUrl;

        public DrInfo(String a, String b) {
            this.searchResultAddressOne = a;
             this.profilePageUrl = b;
        }
    }
}
