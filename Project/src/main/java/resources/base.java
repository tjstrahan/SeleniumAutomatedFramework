package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.core.util.FileUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

public class base {

	private static Logger log = LogManager.getLogger(base.class.getName());

	/*
	 * Creating webdriver as globally so that access of object will be avialable to
	 * all browsers used throughout inheriting classes. Also named static so that
	 * another class cannot modify this
	 */

	public static WebDriver driver;

	// Defining properties as public so that inheriting test cases can use values
	// from projectdata.properties

	// WHEN IT COMES TO OOPS THINK ABOUT SCOPE OF VARIABLES (PUBLIC) AS WELL
	// AS INHERITANCE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! BEFORE PUTTING GLOBALLY AS
	// PUBLIC IT WAS RESTRICTED TO driverInvocation() method of base page class not
	// to all classes

	public Properties prop = new Properties();

	@Test

	/**
	 * Create base class for framework- write one re-usable method to initialise
	 * driver and all 50 test cases should come off this
	 * 
	 * @throws IOException
	 * 
	 *             Note this has ability to run tests on different browsers this
	 *             method can also be synchronized using implicit wait so timing of
	 *             test cases is held in one place and by calling this method
	 *             whoever is using it will get reurned a driver object with correct
	 *             broswer and correct synchronization
	 * 
	 */
	public WebDriver driverInvocation() throws IOException {

		// Properties prop = new Properties();

		/**
		 * path to where properties file is -file name is passed in How to create the
		 * properties file ?
		 * 
		 * Go to package-right click-new-file-nameoffiletypedhere.properties
		 * 
		 * Inside this file you can set username, password, url and browser globally SO
		 * THEY DON'T NEED TO BE HARDCODED IN TEST CASES ANY CHANGES ARE MADE ONLY ONCE
		 * in properties file in case of changes in client requirements
		 * 
		 * 
		 * I am also creating an object called fis and using Java class FileInputStream
		 * on it which passes in path to the properties file we want to use
		 */
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\User\\Project\\src\\main\\java\\resources\\projectdata.properties");

		// fis object knows where files is, prop object now used to integrate this file
		// now need to connect the two objects using properties class method
		// .load(InputStream)
		prop.load(fis);

		// this should end up as selenium as getting it from global properties file so
		// using
		// properties class .getProperty method to retrieve the browser value from
		// global properties file
		System.out.println(prop.getProperty("browser"));

		// using string variable browserName to take property of broswer
		String browserName = prop.getProperty("browser");

		//// using string variable url to take property of url
		String url = prop.getProperty("url");

		// the browser being used can also be stored in properties file and changed in
		// case
		// client decides he wants everything run on different browser
		// so here is the -+code to switch between four browsers as and when required by
		// using properties file and value attached to browser key
		// internet explorer is set as default if value is blank

		// At this stage selenium-server-standalone jar had to be imported to run
		// follwoing selenium code using driver.

		/**
		 * not hardcoded here everything will be taken care of by the if/else if/else
		 * statement also remeber driver has been set globally at top of class to enable
		 * each driver in each statement to use driver commands in different browsers
		 */

		/**
		 * note .equals must be used when you are extracting value from the property I
		 * wanted to clarify if I understand this correctly:
		 *
		 * == -> is a reference comparison, i.e. both objects point to the same memory
		 * location .equals() -> evaluates to the comparison of values in the objects
		 * 
		 * 
		 */

		if (browserName.equals("chrome")) {
			// System.setProperty("webdriver.chrome.driver", "C:\\Work\\chromedriver.exe");
			driver = new ChromeDriver();

		}

		else if (browserName.equals("firefox"))

		{

			System.setProperty("webdriver.gecko.driver", "C:\\Work\\geckodriver.exe");
			driver = new FirefoxDriver();

		}

		else

		{

			driver = new InternetExplorerDriver();

		}
		/**
		 * This will apply to all test cases in framework. Base the amount of time
		 * passed on how long application is taking to load
		 * 
		 * You need to set this here as loading time may change so you dont want to code
		 * this is in every test case
		 */
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		/**
		 * returning a webdriver to whoever call this
		 */
		return driver;

	}

	/**
	 * Screenshot method But how do we trigger this for failed tests ? Listeners in
	 * test ng file Listeners in ng listen out for a failure and then will execute
	 * upon failure of a test if specified in testng.xml
	 * 
	 * Also giving the method an argument of result so that .getName method can be
	 * used on listener to show what screenshots belong to what failed tests in your
	 * concatenated file title string below
	 * 
	 * @throws IOException
	 */

	public static void getScreenshot(String result) throws IOException {

		// when method executed webdriver takes a screenshot wherever your broswer is
		// snd virtually stores somewhere in source variable scrFile
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		// File path would change based on Mac/Linux/Windows
		// copying file from virtual machine to local machine

		FileUtils.copyFile(scrFile, new File("C://test//" + result + "screenshot.png"));

	}
}
