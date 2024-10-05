package tests;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SignInPage;

public class Test_CheckoutPdt_NewUser extends TestBase {

	@Test(testName = "Validate product checkout for new user", dataProvider = "getData")
	public void validateProductCheckoutForNewUser(HashMap<String, String> input) throws InterruptedException {

//		String searchkey = "MAGNA-TILES";
//		String productName = "MAGNA-TILES - Safari Animals 25-Piece Set";
//
//		String firstName = "Tester";
//		String lastName = "One";
//		String email = "testerone@gmail.com";
//		String password = "Test@123";

		HomePage objHomePage = new HomePage(driver);
		objHomePage
			.searchProduct(input.get("searchkey"))
			.addProductToCart(input.get("productName"))
			.clickViewBagButton()
			.increaseProductQuantityByCount(input.get("productName"), 4)
			.clickProceedToCheckoutButton();

		SignInPage objSignInPage = new SignInPage(driver);
		objSignInPage
			.clickOnSignUp()
			.signupToAccount(input.get("firstName"), input.get("lastName"), input.get("email"), input.get("password"));

	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\testData\\TestData.json";			
		return getJsonDataToObjectArray(filePath);

	}

}
