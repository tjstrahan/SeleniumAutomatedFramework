package Academy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

//import alpha.Demo;
import pageObjects.QAClickAcademyLoginPage;
import pageObjects.QAHomePage;
import resources.base;

import java.io.IOException;

public class HomePage extends base {

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
		log.info("Driver is intialised");

		/**
		 * Now get the website as you have set up all the base methods in superclass
		 * base
		 */

		// getting the url value from projectdata.properties via base page

		// driver.get(prop.getProperty("url"));
	}

	@Test(dataProvider = "getData")

	/**
	 * This annotation tells the class to go and get the data provider method get
	 * data and then the type of data is passed into basePageNavigation(String
	 * username, String password, String text) below- make sure variables are of the
	 * right data type
	 * 
	 * Also remember that the count of the arguments you are sending into method
	 * should be same as count of arguments in each row from getData method !!
	 */

	/**
	 * Note it is important that driver is initialised as public and globally in
	 * base superclass otherwise driver objects in this class won't inherit
	 * anything!!
	 * 
	 * @throws IOException
	 */
	public void basePageNavigation(String username, String password, String text) throws IOException {

		/**
		 * Creating object for home page
		 * 
		 * Two ways to access methods of another class
		 * 
		 * 1. Inheritance 2. Creating an object of the class you want access to and
		 * passing an object into its constructor and invoke its methods
		 * 
		 */

		/**
		 * this was out of scope before so anytime second test was run it did not
		 * recognise element as it had no url to go to
		 * 
		 * SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!
		 * SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!
		 * SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!SCOPE!
		 */

		driver.get(prop.getProperty("url"));
		log.info("navigating to url");

		QAHomePage qh = new QAHomePage(driver);

		// qh.popupgone().click();

		/**
		 * Methods of the QAHomePage should appear after . so not hardcoding the value
		 * that will come in in the test case class as this will need to be changed for
		 * many test cases upon any changes values coded in pageObject class instead
		 */

		qh.loginClick().click();

		/**
		 * Creating object for login page
		 * 
		 * Two ways to access methods of another class
		 * 
		 * 1. Inheritance 2. Creating an object of the class you want access to and
		 * passing an object into its constructor and invoke its methods
		 */

		QAClickAcademyLoginPage qch = new QAClickAcademyLoginPage(driver);

		/**
		 * Methods of the QAClickAcademyLoginPage should appear after . so not
		 * hardcoding the value that will come in in the test case class as this will
		 * need to be changed for many test cases upon any changes values coded in
		 * pageObject class instead
		 * 
		 * 
		 * AVOIDING HARDCODING-USING PARAMETERIZATION AND DATA DRIVEN
		 * TECHNIQUES/ANNOTATIONS The data could be hardcoded here but better to
		 * use @DataProviders tag to pass it in via the variables passed in above
		 */

		qch.enteremail().sendKeys(username);
		qch.enterpassword().sendKeys(password);
		qch.loginclick().click();
		log.info(text);
		log.info("Moving on to next iteration of user data");

	}

	/**
	 * Parameterizing the data in a three dimensioanl array - three rows of data in
	 * the same columns and then using data driven techniques via the @DataProvider
	 * tag functionality
	 * 
	 * @return
	 */
	@DataProvider
	public Object getData() {

		/**
		 * Lecture 187- Creating multiple tests with centralised data- should run three
		 * tests as set to three rows new Object[3]
		 */
		// Maybe you need to run the same test three times with different data each time

		// 1st combination testing with unresteicted username and password
		// 2nd combo- another restricted username, password
		// 3rd combo-fraudulent credit history

		// in line below a multi dimensional array 3 stands for the number of
		// combination
		// you are testing and 2 stands for the number of values you are passing in
		// (username, password)
		// so defining an array with three rows and two columns

		Object[][] data = new Object[3][3];

		// Lets initialise 0 stands for first row 0 stands for first column
		// one row of test data is ready

		data[0][0] = "nonrestricteduser@qw.com";
		data[0][1] = "123456";
		data[0][2] = "Non Restrcited user";

		// 2nd set of data
		// second row of test data is sent in the same columns

		data[1][0] = "restricteduser@qw.com";
		data[1][1] = "456789";
		data[1][2] = "Restrcited user";

		// third set of data

		data[2][0] = "thirdsetusername@qw.com";
		data[2][1] = "222222";
		data[2][2] = "Admin user";

		return data;

	}

	@AfterTest
	public void tearDown() {

		driver.close();
		log.info("driver now closed");

		/**
		 * Setting to null so we don't haqve 220 drivers all sucking up memory for 200
		 * test case scenario
		 */
		driver = null;
	}
	/**
	 * Ensuring that after test the browser is shut down so we are not left with
	 * lots of browsers
	 */

}
