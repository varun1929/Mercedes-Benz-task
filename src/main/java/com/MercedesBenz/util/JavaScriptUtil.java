package com.MercedesBenz.util;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {

	private WebDriver driver;

	public JavaScriptUtil(WebDriver driver) {
		this.driver = driver;
	}

	public boolean waitAndClickShadowDomElementByJS(String jsElement, int timeOut) {
		boolean isElementClicked = false;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			Thread.sleep(timeOut * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement element = ((WebElement) js.executeScript("return " + jsElement));
		js.executeScript("arguments[0].click();", element);
		isElementClicked = true;
		return isElementClicked;
	}
	
	public boolean clickShadowDomElementByJS(String jsElement) {
		boolean isElementClicked = false;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = ((WebElement) js.executeScript("return " + jsElement));
		js.executeScript("arguments[0].click();", element);
		isElementClicked = true;
		return isElementClicked;
	}

	public boolean scrollToClickShadowDomElementByJS(String jsElement, int timeOut) {
		boolean isElementClicked = false;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			Thread.sleep(timeOut * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement element = ((WebElement) js.executeScript("return " + jsElement));
		js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
		js.executeScript("arguments[0].click();", element);
		isElementClicked = true;
		return isElementClicked;
	}

	public boolean doubleClickByJS(String jsElement) {
		boolean isElementClicked = false;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = ((WebElement) js.executeScript("return " + jsElement));
		js.executeScript("arguments[0].click();", element);
		js.executeScript("arguments[0].click();", element);
		isElementClicked = true;
		return isElementClicked;
	}
	
	public List<String> getElementText(String jsElement, String jsElementPrice) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		long length = getLengthOfElements(jsElement);
		List<String> allPrices = new ArrayList<>();
		for (int i = 0; i < length; i++) {
			String element = (String) js.executeScript("return " + jsElement + "[" + i + "]." + jsElementPrice);
			allPrices.add(element.trim());
		}
		return allPrices;
	}

	public long getLengthOfElements(String jsElement) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		long lengthOfElements = (long) js.executeScript("return " + jsElement + ".length");
		return lengthOfElements;
	}

}