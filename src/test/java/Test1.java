import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.TestPage;

public class Test1 {

    String url;

    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) throws Exception {
        this.url = url;
    }

    @Test
    public void test1() {
        SoftAssert m_assert = new SoftAssert();
        double rand = Math.random();
        System.out.println("Test 1: " + rand);
        m_assert.assertTrue(rand>0.5);
        m_assert.assertAll();
    }

    @Test
    public void google() {
        TestPage page = new TestPage();
        page.get(url);
        Assert.assertTrue(page.luckyButton().isDisplayed());
    }
}
