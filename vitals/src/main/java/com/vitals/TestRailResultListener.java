package com.vitals;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestRailResultListener implements ISuiteListener {

	private final static int PASS = 1;
	private final static int FAIL = 5;

	TestRailFactory trf;
	int test_run_id;

	@Override
	public void onStart(ISuite suite) {

	}

	@Override
	public void onFinish(ISuite suite) {
		List<IInvokedMethod> methods = suite.getAllInvokedMethods();

		List<ITestResult> passedTests = new ArrayList<ITestResult>();
		List<ITestResult> failedTests = new ArrayList<ITestResult>();

		ITestResult result;
		int status;

		for (IInvokedMethod method : methods) {
			if (method.isTestMethod()) {
				result = method.getTestResult();
				status = result.getStatus();
				if (status == ITestResult.SUCCESS) {
					passedTests.add(result);
				} else if (status == ITestResult.FAILURE) {
					failedTests.add(result);
				}
			}
		}

		JSONArray passed = processResults(passedTests, PASS);
		JSONArray failed = processResults(failedTests, FAIL);
		// Concat both arrays
		JSONArray testResults = concatArray(passed, failed);

		trf = new TestRailFactory();

		int project_id = Integer.parseInt(suite.getParameter("projectId"));
		int suite_id = Integer.parseInt(suite.getParameter("suiteId"));

		if (suite.getParameter("testRunId") != null) {
			test_run_id = Integer.parseInt(suite.getParameter("testRunId"));
		} else {
			String runName = "TestNG Test Run " + new Date();
			test_run_id = trf.createRun(project_id, suite_id, runName);
		}

		// Send the results
		trf.sendResults(test_run_id, testResults);

	}

	private JSONArray processResults(List<ITestResult> tests, int status) {
		JSONArray testResults = new JSONArray();
		String comment = "";

		for (ITestResult testMethod : tests) {
			Method method = testMethod.getMethod().getConstructorOrMethod()
					.getMethod();

			if (method.getAnnotation(TestCase.class) != null) {

				// Test Execution time
				Long totalTime = testMethod.getEndMillis()
						- testMethod.getStartMillis();
				String time = TimeUnit.MILLISECONDS.toSeconds(totalTime) + "s";
				time = time.equals("0s") ? "1s" : time;

				// If it failed get the cause from the error
				if (testMethod.getStatus() == ITestResult.FAILURE) {
					comment = testMethod.getThrowable() != null ? testMethod
							.getThrowable().getMessage() : "";
				}

				// Get the array of ids associated to the test
				long[] ids = method.getAnnotation(TestCase.class).id();

				// Set the results for each test id
				for (long id : ids) {
					HashMap<String, Object> tr = new HashMap<String, Object>();
					tr.put("case_id", id);
					tr.put("status_id", status);
					tr.put("comment", comment);
					tr.put("elapsed", time);

					testResults.add(new JSONObject(tr));
				}
			}
		}
		return testResults;
	}

	private JSONArray concatArray(JSONArray... arrs) {
		JSONArray result = new JSONArray();
		for (JSONArray arr : arrs) {
			for (Object anArr : arr) {
				result.add(anArr);
			}
		}
		return result;
	}

}