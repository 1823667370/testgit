package com.lz.libarary.utils;

import java.util.logging.Logger;

import org.apache.commons.logging.Log;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;

public class TestNGRetry implements IRetryAnalyzer{
	    
	private int retryCount = 1;
	private static int maxRetryCount=2;
	private static Logger log = Logger.getLogger(Log.class.getName());

	@Override
	public boolean retry(ITestResult result) {
		if (retryCount < maxRetryCount) {
			String message = "running retry for  '" + result.getName() + "' on class " + this.getClass().getName() + " Retrying "
					+ retryCount + " times";
			log.info(message);
			Reporter.setCurrentTestResult(result);
			Reporter.log("RunCount=" + (retryCount + 1));
			retryCount++;
			return true;
		}
		return false;
	}


}
