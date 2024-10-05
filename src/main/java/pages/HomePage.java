package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.LibraryUtil;

public class HomePage extends LibraryUtil {

	private WebDriver driver;

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".headerPanel-account-lMI button[title='Register']")
	private WebElement btnRegister;

	@FindBy(css = ".algoliaSearchBox-form-3JZ input[type='search']")
	private WebElement searchBox;

	@FindBy(css = ".algoliaSearchBox-submit-3Vv[type='submit'] span")
	private WebElement btnSearch;

	public SignUpPage clickRegisterButton() {
		click(btnRegister);
		return new SignUpPage(driver);
	}

	public void enterProductNameInSearchBox(String productName) {
		enterText(searchBox, productName);
	}

	public void clickSearchButton() {
		click(btnSearch);
	}

	public SearchResultsPage searchProduct(String productName) throws InterruptedException {
		enterProductNameInSearchBox(productName);
		clickSearchButton();
		clickSearchButton();
		Thread.sleep(3000);
		return new SearchResultsPage(driver);
	}

}
