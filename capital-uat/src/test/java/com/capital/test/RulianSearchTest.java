package com.capital.test;

import com.capital.pages.ResultsPage;
import com.capital.pages.SearchByNameSearchPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Capital Search Test Suite
 */
public class RulianSearchTest {

    private String url;

    @Parameters({"url"})
    @BeforeMethod
    public void setup(String url) throws Exception {
        this.url = url;
    }

    @AfterMethod
    public void shutdown() {
    }

    @Test (dataProvider = "providers")
    public void userSearchTest(String name, String zip) {
        SearchByNameSearchPage searchByNameSearchPage = new SearchByNameSearchPage();

        searchByNameSearchPage.get(url);
        searchByNameSearchPage.enterProviderName(name);
        searchByNameSearchPage.enterProviderLocation(zip);
        searchByNameSearchPage.clickSearchButton();

        ResultsPage results = new ResultsPage();
        Assert.assertNotEquals(results.getTotalResultsText().trim(),"0 profiles found", "Not found: " + name + " " + zip);

    }

    @DataProvider(name = "providers", parallel = true)
    public Object[][] generateProviders() {
        Object[][] glob = dbQuery();
        return glob;
    }

    public Object[][] dbQuery() {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        Object[][] result = null;

        String query = "select distinct(first_name || ' ' || last_name)as name, pp.default_postal_code  from mdx_core.provider p\n" +
                "join mdx_core.provider_practice pp using(provider_id)\n" +
                "where (p.last_name ~* '[ysie]$' and length(p.last_name) <= 5) OR (p.first_name ~* '[ysie]$' and length(p.first_name) <= 5) \n" +
                "limit 500";

        String url = "jdbc:postgresql://10.0.7.12:5432/mdx_v4_capital";
        String user = "mdx";
        String password = "mdx";

        try {
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery(query);


            if (!rs.last()) { //If false, the result set is empty.
                System.out.println("result set is null");
                return null;
            }

            int rowCount = rs.getRow();
            rs.beforeFirst();

            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();

            result = new Object[rowCount][columnCount];

            int i = 0;
            while (rs.next()) {
                for (int j = 0; j < columnCount; j++) {
//                    System.out.println(rs.getObject(j+1));
                    result[i][j] = rs.getObject(j+1);
                }
                i++;
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(RulianSearchTest.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(RulianSearchTest.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }

        return result;
    }
}