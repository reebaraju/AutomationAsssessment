package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.LibraryUtil;

public class SignInPage extends LibraryUtil {

	private WebDriver driver;

	public SignInPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[class*='signInPage-content'] button[title='Sign up']")
	private WebElement btnSignUp;

	public SignUpPage clickOnSignUp() {
		jsClick(btnSignUp);
		return new SignUpPage(driver);
	}

}
