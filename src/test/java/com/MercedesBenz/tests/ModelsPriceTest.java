package com.MercedesBenz.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.MercedesBenz.base.BaseTest;
import com.MercedesBenz.pages.CarConfiguratorPage;
import com.MercedesBenz.pages.OverviewPage;
import com.MercedesBenz.util.ScreenShotUtil;

public class ModelsPriceTest extends BaseTest {

	private CarConfiguratorPage carConfiguratorPage;
	private OverviewPage overviewPage;
	private ScreenShotUtil screenShotUtil;

	@BeforeClass
	public void setup() {
		overviewPage = new OverviewPage(driver);
		carConfiguratorPage = new CarConfiguratorPage(driver);
		screenShotUtil = new ScreenShotUtil(driver);
	}

	@Test(description = "Validate A Class models price are between £15,000 and £60,000")
	public void verifyAClassModelPriceTest() {

		Assert.assertTrue(homePage.landOnHatchbacksAClassPage(), "Hatchback model not selected");
		Assert.assertTrue(overviewPage.clickOnBuildYourCar(), "Build your car not clicked");
		Assert.assertTrue(carConfiguratorPage.FilterFuelType(), "Fuel type filter not selected");
		Assert.assertTrue(carConfiguratorPage.ValidatePriceRange(prop.getProperty("priceRangeMin"),
				prop.getProperty("priceRangeMax")), "The Prices are not in expected range");
		Assert.assertTrue(screenShotUtil.takeScreenshot(prop.getProperty("browser")), "Screenshot failed for filtered result");
		Assert.assertTrue(carConfiguratorPage.writeHighestAndLowestPriceToFile(prop.getProperty("browser")),
				"Price not recorded in text file for filtered result");

	}

}
