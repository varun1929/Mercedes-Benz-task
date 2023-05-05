package com.MercedesBenz.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.MercedesBenz.factory.DriverFactory;
import com.MercedesBenz.pages.HomePage;

public class BaseTest {

	public WebDriver driver;
	public DriverFactory df;
	public Properties prop;
	public HomePage homePage;

	/**
	 * Initialize the setup before running the test
	 * @param browser
	 * @param testName
	 */
	@Parameters({"browser","testname"})
	@BeforeTest
	public void setUp(String browser, String testName) {
		df = new DriverFactory();
		prop = df.initProp();
		
		if(browser!=null) {
			prop.setProperty("browser", browser);
			prop.setProperty("testname", testName);
		}	
		driver = df.initDriver(prop);
		homePage = new HomePage(driver);
	}
	
	/**
	 * Close browser once test is complete
	 */
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
