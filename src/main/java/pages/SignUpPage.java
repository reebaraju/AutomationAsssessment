package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.LibraryUtil;

public class SignUpPage extends LibraryUtil {

	private WebDriver driver;

	public SignUpPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[name='customer.firstname']")
	private WebElement txtFirstName;

	@FindBy(css = "[name='customer.lastname']")
	private WebElement txtLastName;

	@FindBy(css = "[name='customer.email']")
	private WebElement txtEmail;
	
	@FindBy(css = "[name='password']")
	private WebElement txtPassword;

	@FindBy(css = "button[type='submit'][title='Register']")
	private WebElement btnRegister;

	public void enterFirstName(String firstName) {
		enterText(txtFirstName, firstName);
	}

	public void enterLastName(String lastName) {
		enterText(txtLastName, lastName);
	}

	public void enterEmail(String email) {
		enterText(txtEmail, email);
	}
	
	public void enterPassword(String password) {
		enterText(txtPassword, password);
	}

	public void clickRegisterButton() {
		click(btnRegister);
	}

	public void signupToAccount(String firstName, String lastName, String email, String password) throws InterruptedException {
		enterFirstName(firstName);
		enterLastName(lastName);
		enterEmail(email);
		enterPassword(password);
		clickRegisterButton();
		Thread.sleep(5000);
	}

}
