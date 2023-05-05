package com.MercedesBenz.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

/**
 * 
 * @author varungupta
 *
 */
public class DriverFactory {

	public WebDriver driver;
	public Properties prop;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * This method is used to initialize the webdriver on the basis of given browser
	 * name.
	 * 
	 * @param browser
	 * @return this method will return driver
	 */
	public WebDriver initDriver(Properties prop) {

		String browser = prop.getProperty("browser").toLowerCase();

		switch (browser) {
		case "chrome":
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--remote-allow-origins=*");
			tlDriver.set(new ChromeDriver(chromeOptions));
			break;
		case "firefox":
			tlDriver.set(new FirefoxDriver());
			break;
		case "safari":
			tlDriver.set(new SafariDriver());
			break;
		default:
			System.out.println("Please pass the correct browser name: " + browser);
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));

		return getDriver();
	}

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * this method is used to initialize the properties on the basis of given
	 * environment
	 * 
	 * @return this method returns prop
	 */
	public Properties initProp() {

		prop = new Properties();
		try {
			FileInputStream config = new FileInputStream("./src/test/resources/config/config.properties");
			prop.load(config);
			FileInputStream testData = new FileInputStream("./src/test/resources/testdata/testData.properties");
			prop.load(testData);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}
	
	/**
	 * take screenshot
	 * 
	 */
	public static String getScreenshot() {

		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);

		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;

	}

}