import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;

public class Test2 {

    String url;

    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) throws Exception {
        this.url = url;
    }

    @Test
    public void checkTitle(){
        HomePage page = new HomePage();
        page.get(url);

        Assert.assertTrue(page.getTitle().equals("Google"));
    }
}
