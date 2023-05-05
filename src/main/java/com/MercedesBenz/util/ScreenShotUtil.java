package com.MercedesBenz.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShotUtil {

	private WebDriver driver;

	public ScreenShotUtil(WebDriver driver) {
		this.driver = driver;
	}

	public boolean takeScreenshot(String browserName) {
		boolean screenshotTaken = false;

		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir") + "/screenshot/" + browserName + "-" + System.currentTimeMillis() + ".png";

		File directory = new File(System.getProperty("user.dir") + "/screenshot/");
		if (!directory.exists()) {
			directory.mkdirs();
		}

		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
			screenshotTaken = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return screenshotTaken;
	}
}
