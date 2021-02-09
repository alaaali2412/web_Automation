package com.lucky.qa.utilities;

import com.lucky.qa.connectors.Hook;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class ListenerHelper implements ITestListener {
    public static WebDriver driver;

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		 Reporter.getOutput(result); //will give the output logged in testng reporter

		//super.onTestSuccess(result);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		Reporter.getOutput(result); //will give the output logged in testng reporter

		//super.onTestFailure(result);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		Reporter.getOutput(result); //will give the output logged in testng reporter

		//super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

}
