package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.LibraryUtil;

public class MiniCartPopup extends LibraryUtil {

	private WebDriver driver;

	public MiniCartPopup(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".feedbackPopup-root-1VT a[title='View Bag']")
	private WebElement btnViewBag;

	public ShoppingBagPage clickViewBagButton() {
		jsClick(btnViewBag);
		return new ShoppingBagPage(driver);
	}

}
