package Academy;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import resources.base;

public class listeners implements ITestListener {

	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * After importing base resources calling base class to get screenshot method
	 * for any failed tests- this also needs to be flagged up in testng xml file
	 * 
	 * Naming the result of the onTestFailure output as result then passing result
	 * into screenshot method where it will get name of the failed test as
	 * well-otherwise in scenario where there were multiple failures one screenhsot
	 * would overwrite another with no idea of where or how many failures there was
	 */
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub

		try {
			base.getScreenshot(result.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

}
