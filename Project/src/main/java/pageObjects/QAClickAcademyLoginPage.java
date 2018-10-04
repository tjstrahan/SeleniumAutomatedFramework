package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class QAClickAcademyLoginPage {

	WebDriver driver;

	public QAClickAcademyLoginPage(WebDriver driver) {

		this.driver = driver;

		// PageFactory.initElements(driver, this);
	}

	/**
	 * Objects to be returned via methods below to test case
	 */
	// @FindBy(xpath = "//input[@id='user_email']")
	// WebElement email;

	By email = By.xpath("//input[@id='user_email']");

	// @FindBy(xpath = "//input[@id='user_password']")
	// WebElement password;

	By password = By.xpath("//input[@id='user_password']");

	// @FindBy(xpath = "//input[@value='Log In']")
	// WebElement loginbutton;

	By loginbutton = By.cssSelector("[class='btn btn-primary btn-md login-button']");

	/**
	 * Methods should be set up at bottom and then will be accessed by object of the
	 * class created in HomePage test
	 * 
	 * @return
	 */

	public WebElement enteremail() {
		return driver.findElement(email);
	}

	public WebElement enterpassword() {
		return driver.findElement(password);
	}

	public WebElement loginclick() {
		return driver.findElement(loginbutton);
	}

}
