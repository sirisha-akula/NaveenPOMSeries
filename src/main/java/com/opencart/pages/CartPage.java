package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class CartPage {

	
	private WebDriver driver;
	private ElementUtil eleUtil;

	
	private By shoppingCartHeaderName = By.cssSelector("div#content h1");
	
	public CartPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);

	}

	
	public String getShoppingCartHeader() {
		return eleUtil.doGetText(shoppingCartHeaderName);
	}

}
