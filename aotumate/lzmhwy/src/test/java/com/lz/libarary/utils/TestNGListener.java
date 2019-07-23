package com.lz.libarary.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * 
 * @author songer.xing
 *
 */
public class TestNGListener extends TestListenerAdapter {
	Log log = LogFactory.getLog(this.getClass());

	@Override
	public void onTestSuccess(ITestResult tr) {
		log.info("Test Success");
		super.onTestSuccess(tr);
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		log.error("Test Failure");
		super.onTestFailure(tr);
		takeScreenShot(tr);
	}

	public void takeScreenShot(ITestResult tr) {
		UIScreenShot b = (UIScreenShot) tr.getInstance();
		WebDriver currentDirver = b.getDriver();
		System.out.println(currentDirver.getTitle());
		//b.pageSource(currentDirver);
		b.takeScreenShot();

	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		log.error("Test Skipped");
		super.onTestSkipped(tr);
	}

	@Override
	public void onTestStart(ITestResult result) {
		log.info("Test Finsh");
		super.onTestStart(result);
	}

	@Override
	public void onStart(ITestContext testContext) {
		log.info("Test Start");
		super.onStart(testContext);
	}

	@Override
	public void onFinish(ITestContext testContext) {
		log.info("Test Finish");
		super.onFinish(testContext);
	}

}
