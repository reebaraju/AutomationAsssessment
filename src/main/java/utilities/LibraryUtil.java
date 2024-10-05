package utilities;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reports.ExtentTestManager;

public class LibraryUtil {

	private WebDriver driver;
	private WebDriverWait wait;

	private static final Logger log = LoggerFactory.getLogger(LibraryUtil.class);

	protected LibraryUtil(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // add wait time to property file
	}

	public void click(WebElement element) {
		try {
			waitForElementClickable(element);
			element.click();
			log.info("Clicked on element: " + element.toString());
			ExtentTestManager.logInfo("Clicked on element: " + element.toString());
		} catch (Exception e) {
			log.error("Failed to click on element: " + element.toString());
			ExtentTestManager.logFail("Failed to click on element: " + element.toString());
			throw e;
		}
	}

	public void enterText(WebElement element, String text) {
		try {
			waitForElementVisible(element);
			element.clear();
			element.sendKeys(text);
			log.info("Entered text '" + text + "' into element: " + element.toString());
			ExtentTestManager.logInfo("Entered text '" + text + "' into element: " + element.toString());
		} catch (Exception e) {
			log.error("Failed to enter text into element: " + element.getAccessibleName());
			ExtentTestManager.logFail("Failed to enter text into element: " + element.toString());
			throw e;
		}
	}

	public void waitForElementVisible(WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			log.info("Element is visible: " + element.toString());
		} catch (Exception e) {
			log.error("Element is not visible: " + element.toString());
			throw e;
		}
	}

	public void waitForElementClickable(WebElement element) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			log.info("Element is clickable: " + element.toString());
		} catch (Exception e) {
			log.error("Element is not clickable: " + element.toString());
			throw e;
		}
	}

	public String getText(WebElement element) {
		try {
			waitForElementVisible(element);
			String text = element.getText();
			log.info("Text retrieved from element: " + element.toString() + " is '" + text + "'");
			return text;
		} catch (Exception e) {
			log.error("Failed to retrieve text from element: " + element.toString());
			throw e;
		}
	}

	public void navigateToUrl(String url) { // revisit
		try {
			driver.get(url);
			log.info("Navigated to URL: " + url);
		} catch (Exception e) {
			log.error("Failed to navigate to URL: " + url);
			throw e;
		}
	}

	public void waitForTitleContains(String title) {
		try {
			wait.until(ExpectedConditions.titleContains(title));
			log.info("Page title contains: " + title);
		} catch (Exception e) {
			log.error("Failed to find page title containing: " + title);
			throw e;
		}
	}

	public void logInformation(String message) {
		log.info(message);
		ExtentTestManager.logInfo(message);
	}

	public void jsClick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

}
