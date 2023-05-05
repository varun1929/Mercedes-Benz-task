package com.MercedesBenz.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.MercedesBenz.util.FileUtil;
import com.MercedesBenz.util.JavaScriptUtil;

public class CarConfiguratorPage {

	private JavaScriptUtil javaScriptUtil;
	private FileUtil fileUtil;

	private String btn_Filter = "document.querySelector('owcc-car-configurator').shadowRoot.querySelector('ccwb-multi-select')";
	private String select_Diesel = "document.querySelector('owcc-car-configurator').shadowRoot.querySelectorAll('ccwb-checkbox')[0].shadowRoot.querySelector('label > ccwb-text.wb-text')";
	private String filterResults = "document.querySelector('owcc-car-configurator').shadowRoot.querySelector('cc-motorization-comparison').querySelectorAll('div[class=\"ng-star-inserted\"]')";
	private String results_Price = "querySelector('ccwb-card').querySelector('ccwb-text > span').innerHTML.toString()";

	public CarConfiguratorPage(WebDriver driver) {
		javaScriptUtil = new JavaScriptUtil(driver);
		fileUtil = new FileUtil();
	}

	public boolean FilterFuelType() {
		javaScriptUtil.scrollToClickShadowDomElementByJS(btn_Filter, 10);
		javaScriptUtil.waitAndClickShadowDomElementByJS(select_Diesel, 1);
		return javaScriptUtil.doubleClickByJS(btn_Filter);
	}

	public boolean ValidatePriceRange(String priceMin, String priceMax) {

		List<String> priceList = javaScriptUtil.getElementText(filterResults, results_Price);
		boolean flag = false;
		for (int i = 0; i < priceList.size(); i++) {
			int numberPrice = Integer.parseInt(priceList.get(0).replaceAll("[^0-9]", ""));

			if (numberPrice >= Integer.parseInt(priceMin) && numberPrice <= Integer.parseInt(priceMax)) {
				flag = true;
			} else {
				flag = false;
				break;
			}

		}
		return flag;
	}

	public boolean writeHighestAndLowestPriceToFile(String browserName) {
		List<String> priceList = javaScriptUtil.getElementText(filterResults, results_Price);
		return fileUtil.addFirstAndLastIndexInTextFile(priceList,browserName);
	}

}
