package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class ProductInfoTest extends BaseTest{
	
	@BeforeClass
	public void productInfoSetUp() {
		accPage= loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void productHeaderTest() {
		resultsPage = accPage.doSearch("MacBook");
		productInfoPage= resultsPage.selectProduct("MacBook Pro");
		Assert.assertEquals(productInfoPage.getProductHeaderName(), "MacBook Pro");
	}
	
	@DataProvider
	public Object[][] productData() {
		return new Object[][] {
			
			{"MacBook", "MacBook Pro", Constants.MACBOOK_IMAGES_COUNT},
			{"MacBook", "MacBook Air", Constants.MACBOOK_IMAGES_COUNT},
			{"iMac", "iMac", Constants.IMAC_IMAGES_COUNT},
			

		};
		
	}
	
	@Test(dataProvider = "productData")
	public void productImagesCountTest(String productName, String mainProductName, int imagesCount) {
		resultsPage = accPage.doSearch(productName);
		productInfoPage= resultsPage.selectProduct(mainProductName);
		int totalImages = productInfoPage.getProductImageCount();
		System.out.println("total images for : " + "MacBook Pro " + ":" + totalImages);
		Assert.assertEquals(totalImages, imagesCount);
	}

//	Brand : Apple
//	Availability : Out Of Stock
//	ExTaxPrice : Ex Tax: $2,000.00
//	Price : $2,000.00
//	name : MacBook Pro
//	Product Code : Product 18
//	Reward Points : 800
//	totalImageCount : 4

//output from LinkedHashMap (it maintains the order)
//	name : MacBook Pro
//	totalImageCount : 4
//	Brand : Apple
//	Product Code : Product 18
//	Reward Points : 800
//	Availability : Out Of Stock
//	Price : $2,000.00
//	ExTaxPrice : Ex Tax: $2,000.00

	//Treemap : it maintains the sorted order based on key
//Availability : Out Of Stock
//	Brand : Apple
//	ExTaxPrice : Ex Tax: $2,000.00
//	Price : $2,000.00
//	Product Code : Product 18
//	Reward Points : 800
//	name : MacBook Pro
//	totalImageCount : 4

	@Test
	public void productDataTest() {
		resultsPage = accPage.doSearch("MacBook");
		productInfoPage= resultsPage.selectProduct("MacBook Pro");
		Map<String, String> actProductInfoMap = productInfoPage.getProductInfo();
		actProductInfoMap.forEach((k,v) ->System.out.println(k+" : "+v));
		softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfoMap.get("Price"), "$2,000.00");
		softAssert.assertEquals(actProductInfoMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(actProductInfoMap.get("name"), "MacBook Pro");
		softAssert.assertAll();

//		Note: Whenever we are having only one assertion,, we have to use Hard Assertion and if there are multiple
//		assertions in the test method we need to use softAssertion.
//		
	}
	
	@Test
	public void addToCartTest() {
		resultsPage = accPage.doSearch("MacBook");
		productInfoPage= resultsPage.selectProduct("MacBook Pro");
		cartPage = productInfoPage.addToCart("1");
		String  actTitle = cartPage.getShoppingCartHeader();
		Assert.assertTrue(actTitle.contains("Shopping Cart"));

	}
}
