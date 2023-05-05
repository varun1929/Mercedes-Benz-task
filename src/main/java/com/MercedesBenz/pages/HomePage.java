package com.MercedesBenz.pages;

import org.openqa.selenium.WebDriver;

import com.MercedesBenz.util.JavaScriptUtil;

public class HomePage {

	private JavaScriptUtil javaScriptUtil;

	private String acceptCookies = "document.querySelector(\"cmm-cookie-banner[settings-id='fph8XBqir']\").shadowRoot.querySelector('button[data-test=\"handle-accept-all-button\"]')";
	private String headerMenu_OurModels = "document.querySelector('owc-header').shadowRoot.querySelectorAll('p.owc-header-navigation-topic__label')[0]";
	private String menuOption_Hatchbacks = "document.querySelector('vmos-flyout').shadowRoot.querySelectorAll('p')[6]";
	private String subMenuHatchbacks_AClass = "document.querySelector(\"vmos-flyout[component-id='d90117080adce2a4477e5c8c6a29d632']\").shadowRoot.querySelector(\"owc-header-flyout[flyout-title='Hatchbacks']\").querySelectorAll(\"li > a[class='@vmos-vmos-flyout-flyout-group-item__link__NeNLP']\")[0]";

	public HomePage(WebDriver driver) {
		javaScriptUtil = new JavaScriptUtil(driver);
	}

	/**
	 * Home page interactions
	 * @return true if interactions happened correctly, false otherwise
	 */
	public boolean landOnHatchbacksAClassPage() {
		javaScriptUtil.waitAndClickShadowDomElementByJS(acceptCookies, 4);
		javaScriptUtil.clickShadowDomElementByJS(headerMenu_OurModels);
		javaScriptUtil.scrollToClickShadowDomElementByJS(menuOption_Hatchbacks,1);
		return javaScriptUtil.waitAndClickShadowDomElementByJS(subMenuHatchbacks_AClass,5);
	}
}