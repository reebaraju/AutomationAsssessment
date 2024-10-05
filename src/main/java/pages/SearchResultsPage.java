package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.LibraryUtil;

public class SearchResultsPage extends LibraryUtil {

	private WebDriver driver;

	public SearchResultsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "div.item-root-1iS")
	private List<WebElement> resultItems;


	private By txtProductName = By.cssSelector(".item-nameAndBtnWrapper-4IV a.item-name-3AC span");
	private By addToCart = By.cssSelector(".galleryCartButton-cartBuyIcon-3LD");

	public List<WebElement> getSearchResultList() {
		waitForElementVisible(resultItems.getFirst());
		return resultItems;
	}

	public WebElement getProduct(String productName) {
		WebElement prod = getSearchResultList().stream()
				.filter(product -> product.findElement(txtProductName).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;
	}

	public MiniCartPopup addProductToCart(String productName) throws InterruptedException {
		WebElement prod = getProduct(productName);
		prod.findElement(addToCart).click();
		logInformation(String.format("Clicked on add product to cart for %s", productName));
		return new MiniCartPopup(driver);
	}

}
