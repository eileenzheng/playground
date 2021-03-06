import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Test2 {

    String url;

    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) throws Exception {
        this.url = url;
    }

    @Test
    public void test2() {
        SoftAssert m_assert = new SoftAssert();
        double rand = Math.random();
        System.out.println("Test 2: " + rand);
        m_assert.assertTrue(rand > 0.5);
        m_assert.assertAll();
    }
}
