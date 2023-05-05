package com.MercedesBenz.pages;

import org.openqa.selenium.WebDriver;

import com.MercedesBenz.util.JavaScriptUtil;

public class OverviewPage {

	private JavaScriptUtil javaScriptUtil;

	private String btn_BuildYourCar = "document.querySelector(\"owc-stage\").shadowRoot.querySelector(\"div[class='owc-stage-cta-buttons owc-stage-cta-buttons--dark'] > a\")";

	public OverviewPage(WebDriver driver) {
		javaScriptUtil = new JavaScriptUtil(driver);
	}

	public boolean clickOnBuildYourCar() {
		return javaScriptUtil.waitAndClickShadowDomElementByJS(btn_BuildYourCar,1);
	}

}
