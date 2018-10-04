package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class QAHomePage {

	WebDriver driver;

	/**
	 * Constructor to take homepage testing driver
	 * 
	 * @param driver
	 */
	public QAHomePage(WebDriver driver) {

		/**
		 * So this line of code takes the test case driver passed in from HomePage class
		 * and assigns it to the local driver of this class using this keyword now
		 * driver is the test case driver passed in and it will use Chrome as set up in
		 * HomePage class
		 */

		// so assigning this class driver variable (in blue) to the driver passed in
		// from HomePage Test(orange)
		// once constructor all set up
		this.driver = driver;

		/**
		 * Don't forget to initialise this if you want page factory to run !!!!!
		 */
		// PageFactory.initElements(driver, this);
	}

	// @FindBy(css = "[class='sumome-react-svg-image-container']")
	// WebElement popupdelete;

	/**
	 * Objects to be returned via methods below to test case
	 */
	// @FindBy(xpath = "//span[contains(text(),'Login')]")
	// WebElement login;

	By login = By.cssSelector("[class='fa fa-lock fa-lg']");

	/**
	 * <section id="content" style="padding:30px 0 !important">
	 * <div class="container"> <div class="text-center">
	 * <h2>Featured Courses</h2> </div> </div> </section>
	 * 
	 * Note the above HTML find the id replace with # then find the children by
	 * putting a a space and then the tag you want
	 */

	By featuredcoursestitle = By.cssSelector("#content h2");

	/**
	 * But if something else is unique suchg as class name you can also use css
	 * selector like this which is way of overcoming class compound ie no no gaps in
	 * class name problem
	 */
	By navbar = By.cssSelector("[class='nav navbar-nav navbar-right']");

	// public WebElement popupgone() {
	// return popupdelete;
	// }
	/**
	 * Methods should be set up at bottom and then will be accessed by object of the
	 * class created in HomePage test
	 * 
	 * @return
	 */
	public WebElement loginClick() {
		return driver.findElement(login);
	}

	public WebElement courseClick() {
		return driver.findElement(featuredcoursestitle);
	}

	public WebElement isnavbarDisplayed() {
		return driver.findElement(navbar);
	}
}
