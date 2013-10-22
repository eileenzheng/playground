package com.presby.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import com.presby.DriverManager;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Postgres/Solr test
 * Queries postgresdb for list of provider names that end in y,s,i,e and then tries to find
 * the provider in Solr. If Solr returns no results the test will fail.
 *
 * pass -DqueryLimit={limit} in the mvn command to set the postgres query limit. If no limit set it will default to 1
 */
public class PgSolrTest {

    String query = "select distinct(first_name || ' ' || last_name)as name, pp.default_state_code  from mdx_core.provider p\n" +
            "join mdx_core.provider_practice pp using(provider_id)\n" +
            "where (p.last_name ~* '[ysie]$' and length(p.last_name) <= 5) OR (p.first_name ~* '[ysie]$' and length(p.first_name) <= 5) \n" +
            "limit ";

    String queryAll = "select distinct(first_name || ' ' || last_name)as name, pp.default_state_code  from mdx_core.provider p\n" +
            "join mdx_core.provider_practice pp using(provider_id)\n" +
            "limit ";

    String queryFacility =  "select distinct name, default_state_code " +
            "from mdx_core.facility " +
            "where facility_type_code not in ('U', 'M') " +
            "limit ";

    String solrPostUrlStem;

    @BeforeMethod
    @Parameters ({"solrIndex","solrIndexIp"})
    public void setLocation(String solrIndex, String solrIndexIp) {
        this.solrPostUrlStem = "http://" + solrIndexIp + ":8080/solr/" + solrIndex + "/select/?q=";
    }

    @Test (dataProvider = "providers")
    public void limitedProviderSolrSearchTest(String name, String state) {

        String base = "(account_store:capital AND provider_type_store:physician AND (last_name_search:\"%s\"^5 full_name_search:\"%s\"~30) AND state_match:\"%s\")";

        String formatted = String.format(base, name, name, state);

        try {
            formatted = URLEncoder.encode(formatted, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String url = solrPostUrlStem + formatted;
        // Use below if you want to see currently generated stuff -- Good when solr job is building
        // String url = "http://10.0.4.23:8080/solr/contractsalt/select/?q=" + formatted;

        DriverManager.getDriver().get(url);

        try {
            String xml = DriverManager.getDriver().getPageSource();
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new InputSource(new ByteArrayInputStream(xml.getBytes("utf-8"))));

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("result");
            String result = nList.item(0).getAttributes().getNamedItem("numFound").getTextContent();
            Assert.assertNotEquals(result,"0","No results found for: " + name + " in State: " + state);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test (dataProvider = "allProviders")
    public void allProviderSolrTest(String name, String state) {

        String base = "(account_store:capital AND provider_type_store:physician AND (last_name_search:\"%s\"^5 full_name_search:\"%s\"~30) AND state_match:\"%s\")";

        String formatted = String.format(base, name, name, state);

        try {
            formatted = URLEncoder.encode(formatted, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String url = solrPostUrlStem + formatted;
        // Use below if you want to see currently generated stuff -- Good when solr job is building
        // String url = "http://10.0.4.23:8080/solr/contractsalt/select/?q=" + formatted;

        DriverManager.getDriver().get(url);

        try {
            String xml = DriverManager.getDriver().getPageSource();
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new InputSource(new ByteArrayInputStream(xml.getBytes("utf-8"))));

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("result");
            String result = nList.item(0).getAttributes().getNamedItem("numFound").getTextContent();
            Assert.assertNotEquals(result,"0","No results found for: " + name + " in State: " + state);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test (dataProvider = "allFacilities")
    public void allFacilitiesSolrTest(String name, String state) {

        String base = "(account_store:capital AND provider_type_store:facility AND (facility_name_search:\"%s\"~10) AND state_match:\"%s\")";

        String formatted = String.format(base, name, state);

        try {
            formatted = URLEncoder.encode(formatted, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String url = solrPostUrlStem + formatted;
        // Use below if you want to see currently generated stuff -- Good when solr job is building
        // String url = "http://10.0.4.23:8080/solr/contractsalt/select/?q=" + formatted;

        DriverManager.getDriver().get(url);

        try {
            String xml = DriverManager.getDriver().getPageSource();
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new InputSource(new ByteArrayInputStream(xml.getBytes("utf-8"))));

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("result");
            String result = nList.item(0).getAttributes().getNamedItem("numFound").getTextContent();
            Assert.assertNotEquals(result,"0","No results found for: " + name + " in State: " + state);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @DataProvider(name = "providers", parallel = true)
    public Object[][] generateProviders() {
        int limit = System.getProperty("queryLimit") != null ? Integer.parseInt(System.getProperty("queryLimit")) : 1;
        return dbQuery(query,Integer.toString(limit));
    }

    @DataProvider(name = "allProviders", parallel = true)
    public Object[][] generateAllProviders() {
        int limit = System.getProperty("queryLimit") != null ? Integer.parseInt(System.getProperty("queryLimit")) : 1;
        return dbQuery(queryAll,Integer.toString(limit));
    }

    @DataProvider(name = "allFacilities", parallel = true)
    public Object[][] generateAllFacilities() {
        int limit = System.getProperty("queryLimit") != null ? Integer.parseInt(System.getProperty("queryLimit")) : 1;
        return dbQuery(queryFacility,Integer.toString(limit));
    }

    public Object[][] dbQuery(String query, String limit) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        Object[][] result = null;

        String url = "jdbc:postgresql://10.0.7.12:5432/mdx_v4_phs";
        String user = "mdx";
        String password = "mdx";

        try {
            con = java.sql.DriverManager.getConnection(url, user, password);
            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            // Take the query at the top of this class and concatenate limit to it.
            rs = st.executeQuery(query + limit);


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
                    result[i][j] = rs.getObject(j+1);
                }
                i++;
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(PgSolrTest.class.getName());
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
                Logger lgr = Logger.getLogger(PgSolrTest.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }

        return result;
    }

//    @Test (dataProvider = "providers")
//    public void userSearchTest(String name, String zip) {
//        SearchByNameSearchPage searchByNameSearchPage = new SearchByNameSearchPage();
//
//        searchByNameSearchPage.get(url);
//        searchByNameSearchPage.enterProviderName(name);
//        searchByNameSearchPage.enterProviderLocation(zip);
//        searchByNameSearchPage.clickSearchButton();
//
//        ResultsPage results = new ResultsPage();
//        Assert.assertNotEquals(results.getTotalResultsText().trim(),"0 profiles found", "Not found: " + name + " " + zip);
//
//    }
}