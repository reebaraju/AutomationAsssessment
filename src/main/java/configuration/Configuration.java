package configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Configuration {

	private static final Logger log = LoggerFactory.getLogger(Configuration.class);
	
	private static Properties properties;

	private static final String CONFIG_FILE_PATH = System.getProperty("user.dir")
			+ "/src/test/resources/config.properties";

	private Configuration() {
	}

	static {
		try {
			FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE_PATH);
			properties = new Properties();
			properties.load(fileInputStream);
			fileInputStream.close();
			log.info("Property file loaded - {}", CONFIG_FILE_PATH);
		} catch (IOException e) {
			throw new RuntimeException("Failed to load configuration file.");
		}
	}

	public static String getPropertyFromFile(String key) {
		return properties.getProperty(key);
	}
	
	public static String getProperty(String key) {
		String value = System.getProperty(key) != null? System.getProperty(key) : getPropertyFromFile(key);
		log.info("Getting property for {} - {}", key, value);
		return value;
	}

	public static int getIntProperty(String key) {
		return Integer.parseInt(properties.getProperty(key));
	}

	public static String getBrowser() {
		return getProperty("browser");
	}

	public static String getBaseURL() {
		return getProperty("baseURL");
	}

	public static int getTimeout() {
		return getIntProperty("timeout");
	}
	
	public static int getImplicitWaitTime() {
		return getIntProperty("implicitWaitTime");
	}

	public static String getReportDirectory() {
		return getProperty("reportDirectory");
	}
}
