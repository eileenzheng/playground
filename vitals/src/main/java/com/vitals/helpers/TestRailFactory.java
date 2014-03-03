package com.vitals.helpers;

import com.gurock.testrail.APIClient;
import com.gurock.testrail.APIException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestRailFactory {

    private final static String CLIENT_URL = "http://testrail.mdx.med/";
    private final static String USER = "tester@vitals.com";
    private final static String PASS = "vitals123";

    APIClient client;

    public TestRailFactory() {
        client = new APIClient(CLIENT_URL);
        client.setUser(USER);
        client.setPassword(PASS);
    }

    /**
     * Creates a test run in TestRail and returns the Test Run ID
     * @param project_id
     * @param suite_id
     * @param name
     * @return int test run id
     */
    public int createRun(int project_id, int suite_id, String name) {
        Map<String,Object> testRun = new HashMap<String,Object>();
        testRun.put("suite_id", suite_id);
        testRun.put("name", name);

        JSONObject reply = null;
        try {
            reply = (JSONObject) client.sendPost("add_run/" + project_id, testRun);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }

        return reply != null ? ((Long) reply.get("id")).intValue() : 0;
    }

    /**
     * Adds a test result to a given test case that is a part of a test run
     * @param run_id
     * @param case_id
     * @param status_id
     */
    public void sendResult(int run_id, long case_id, int status_id, String comment, String time) {
        Map<String,Object> postResult = new HashMap<String,Object>();
        postResult.put("elapsed", time);
        postResult.put("status_id", status_id);
        postResult.put("comment", comment);

        try {
            client.sendPost("add_result_for_case/" + run_id + "/" + case_id, postResult); //Casts to JSONObject
        } catch (IOException e) {
            // No error
        } catch (APIException e) {
            // No error
        }
    }

    /**
     * Adds a complete list of results for a given test run
     * @param run_id
     * @param results
     */
    public void sendResults(int run_id, JSONArray results) {
        Map<String,JSONArray> postResult = new HashMap<String, JSONArray>();
        postResult.put("results", results);

        JSONObject tr = new JSONObject(postResult);

        try {
            client.sendPost("add_results_for_cases/" + run_id,tr); //Casts to JSONArray

        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
    }

}
