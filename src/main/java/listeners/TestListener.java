package listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import reports.ExtentManager;
import reports.ExtentTestManager;

public class TestListener implements ITestListener {

	private static final Logger log = LoggerFactory.getLogger(TestListener.class);

	@Override
	public void onStart(ITestContext context) {
		ExtentManager.getInstance();
		log.info("<<<<< TEST SUITE STARTED >>>>>");
	}

	@Override
	public void onTestStart(ITestResult result) {
		String testName = getTestMethodName(result);
		ExtentTestManager.createTest(testName);
		log.info("<<<<< Test Case Started : {} >>>>>", testName);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentTestManager.logPass("Test Passed!");
		log.info("<<<<< Test Case Completed [PASS] : {} >>>>>", getTestMethodName(result));
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ExtentTestManager.logFail("Test failed! : " + result.getThrowable());
		log.info(result.getThrowable().toString());
		log.info("<<<<< Test Case Completed [FAIL] : {} >>>>>", getTestMethodName(result));
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentTestManager.logWarning("Test skipped: " + result.getMethod().getMethodName());
	}

	@Override
	public void onFinish(ITestContext context) {
		ExtentManager.flush();
		log.info("<<<<< TEST SUITE ENDED >>>>>");
	}

	private String getTestMethodName(ITestResult result) {
		return result.getMethod().getMethodName();
	}

}
