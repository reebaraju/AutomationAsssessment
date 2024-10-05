package tests;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import configuration.Configuration;
import configuration.DriverManager;
import reports.ExtentManager;

public class TestBase {

	protected WebDriver driver;

	@BeforeClass
	public void initialize() {
		driver = DriverManager.getDriver();
		ExtentManager.getInstance();
		launchURL();
	}

	@AfterClass
	public void tearDown() {
		DriverManager.quitDriver();
	}

	public void launchURL() {
		String baseURL = Configuration.getBaseURL();
		driver.get(baseURL);
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {

		String jsonContent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

	public Object[][] getJsonDataToObjectArray(String filepath) throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(filepath);
		Object[][] results = new Object[data.size()][1];
		int index = 0;
		for (HashMap<String, String> temp : data) {
			results[index++] = new Object[] { temp };
		}
		return results;
	}

}
