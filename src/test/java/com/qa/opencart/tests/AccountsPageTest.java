package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class AccountsPageTest  extends BaseTest{
	
	
	@BeforeClass
	public void accPageSetUp() {
		accPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@Test
	public void accPageTitleTest() {
		String title = accPage.getAccountPageTitle();
		System.out.println("Account page title : " +title);
		Assert.assertEquals(title, Constants.ACCOUNTS_PAGE_TITLE);
	}
	
	
	@Test
	public void accPageUrlTest() {
		String url = accPage.getAccountPageUrl();
		System.out.println("Account page url : " +url);
		Assert.assertTrue(url.contains(Constants.ACCOUNTS_PAGE_URL_FRACTION));
	}
	
	@Test
	public void accPageHeaderTest() {
		String header = accPage.getAccPageHeader();
		System.out.println("Account page header is : " +header);
		Assert.assertEquals(header, Constants.ACCOUNTS_PAGE_HEADER);
	}
	
	@Test
	public void LogoutLinkTest() {
		Assert.assertTrue(accPage.logoutLinkExist());
	}
	
	@Test
	public void SearchExistTest() {
		Assert.assertTrue(accPage.searchExist());
	}
	
	@Test
	public void accPageSectionsTest() {
		List<String> accSectionsList = accPage.getAccPageSections();
		System.out.println("acc page sections List : " +accSectionsList);
		Assert.assertEquals(accSectionsList, Constants.ACCOUNTS_PAGE_SECTION_LIST);
	}
	
	@DataProvider
	public Object[][] ProductData() {
		return new Object[][] {
			{"iMac"},
			{"MacBook"},
			{"Apple"}
			
		};
		
	}
	
	@Test(dataProvider = "ProductData")
	public void searchTest(String productName) {
		resultsPage = accPage.doSearch(productName);
		Assert.assertTrue(resultsPage.getProductListCount()> 0);
	}
	
	
	@DataProvider
	public Object[][] productSelectData() {
		return new Object[][] {
			
			{"MacBook", "MacBook Pro"},
			{"MacBook", "MacBook Air"},
			{"iMac", "iMac"},
			{"Apple", "Apple Cinema 30\""},

		};
		
	}
	
	@Test(dataProvider = "productSelectData")
	public void selectProductTest(String productName, String mainProductName) {
		resultsPage = accPage.doSearch(productName);
		productInfoPage = resultsPage.selectProduct(mainProductName);
		Assert.assertEquals(productInfoPage.getProductHeaderName(), mainProductName);
		
	}
	
}
