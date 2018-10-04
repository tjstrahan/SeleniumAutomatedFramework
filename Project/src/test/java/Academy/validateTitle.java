package Academy;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.QAHomePage;
import resources.base;

public class validateTitle extends base {

	/**
	 * Including log functionality- Logs will enable you to find out where the
	 * failure is without even going to analyse code-if you have 50 to 100 tests
	 * this is very helpful
	 */

	private static Logger log = LogManager.getLogger(base.class.getName());

	/**
	 * Ensuring that before each test base driver and base url are initialised-
	 * keeps things clearer
	 * 
	 * @throws IOException
	 */
	@BeforeTest
	public void initialise() throws IOException {

		/**
		 * Call driverInvocation method from base and put into your own driver object
		 * for this class SO IN TEST CASES THERE IS NO MESSING AROUND WITH BROWSERS DATA
		 * IS DONE FROM PROPERTIES FILE AND CONTROL IS DONE FROM BASE CLASS. SO ANY
		 * CHANGE TO BROSWER FROM CLIENT JUST CHANGE IN PROPERTIES FILE
		 */
		driver = driverInvocation();

		/**
		 * Now get the website as you have set up all the base methods in superclass
		 * base
		 */

		// getting the url value from projectdata.properties via base page

		driver.get(prop.getProperty("url"));
	}

	@Test
	/**
	 * Note it is important that driver is initialised as public and globally in
	 * base superclass otherwise driver objects in this class won't inherit
	 * anything!!
	 * 
	 * @throws IOException
	 */
	public void validateAppTitle() throws IOException {

		/**
		 * Creating object for home page
		 * 
		 * Two ways to access methods of another class
		 * 
		 * 1. Inheritance 2. Creating an object of the class you want access to and
		 * passing an object into its constructor and invoke its methods
		 * 
		 */

		QAHomePage qh = new QAHomePage(driver);

		// Compare the text from the browser to the actual value (actual value will be
		// specified by client)
		// qh.courseClick().getText();

		// qh.isnavbarDisplayed().isDisplayed();

		// Test NG provides this
		// Assert.assertEquals(expected, actual);
		// if what is is in the qh.courseClick().getText() object does not equal
		// featured courses script should fail

		Assert.assertEquals(qh.courseClick().getText(), "FEATURED COURSES123");
		log.info("text is incorrect");

		// Boolean to see if navbar is present in code incase new release has changed it

		// Assert.assertTrue(qh.isnavbarDisplayed().isDisplayed());

	}

	/**
	 * Ensuring that after test the browser is shut down so we are not left with
	 * lots of browsers
	 */
	@AfterTest
	public void tearDown() {
		driver.close();

		/**
		 * Setting to null so we don't haqve 200 drivers all sucking up memory for 200
		 * test case scenario
		 */
		driver = null;
	}

}
