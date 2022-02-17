package com.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
    private TreeMap<String, String> productMap ;
    
	private By productHeaderName = By.cssSelector("div#content h1");
	private By productImageCount = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By productPriceData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By quantity = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");
	private By itemCartBtn = By.cssSelector("div#cart button");
	private By viewCartBtn = By.xpath("(//i[@class='fa fa-shopping-cart'])[position()=3]");

	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);

	}
	
	public String getProductHeaderName() {
		return eleUtil.doGetText(productHeaderName);
	}
	
	public int getProductImageCount() {
		return eleUtil.waitForElementsVisible(productImageCount, 10).size();
	}
	
	//HashMap: it does not maintain the order
	//LinkedHashMap: it maintains the insertion order
	//TreeMap: it maintains the sorted order based on key
	public Map<String,String> getProductInfo() {
		productMap = new TreeMap<String, String>();
		productMap.put("name", getProductHeaderName());
		productMap.put("totalImageCount", String.valueOf(getProductImageCount()));
		getProductMetaData();
		getProductPriceData();
		return productMap;
	}
	
//    Brand: Apple
//    Product Code: Product 18
//    Reward Points: 800
//    Availability: Out Of Stock
private void getProductMetaData() {
		List<WebElement> metaDataList = eleUtil.getElements(productMetaData);
		for(WebElement e : metaDataList) {
			String text = e.getText();
			String meta[] = text.split(":");
			String key = meta[0].trim();
			String value= meta[1].trim();
			productMap.put(key, value);
		}
	}

//    $2,000.00
//    Ex Tax: $2,000.00
	private void getProductPriceData() {
		List<WebElement> metaPriceList = eleUtil.getElements(productPriceData);
		String price = metaPriceList.get(0).getText().trim();
		String ExTaxPrice = metaPriceList.get(1).getText().trim();
		productMap.put("Price", price);
		productMap.put("ExTaxPrice", ExTaxPrice);
		}
	
	
	public CartPage addToCart(String quantityValue) {
		eleUtil.doSendKeys(quantity, quantityValue);
		eleUtil.doClick(addToCartBtn);
		eleUtil.doClick(itemCartBtn);
		eleUtil.doClick(viewCartBtn);
		return new CartPage(driver);
		
	}
}


