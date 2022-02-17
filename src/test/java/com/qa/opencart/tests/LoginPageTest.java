package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EPIC - 100 : Design Login Page  for open cart application ")
@Story("US - 101: Login Page Features")
public class LoginPageTest extends BaseTest{
	
	@Description("login page title test")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void loginPageTitleTest() {
		String title = loginpage.getLoginPageTitle();
		System.out.println("Login Page Title is : " + title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}

	@Description("login page url test")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void loginPageUrlTest() {
		String url = loginpage.getLoginPageUrl();
		System.out.println("Login Page Url is : " + url);
		Assert.assertTrue(url.contains(Constants.LOGIN_PAGE_URL_FRACTION));
	}
	@Description("Testing forgotpasswork link on login page")
	@Severity(SeverityLevel.CRITICAL)
	@Test(enabled = true)
	public void loginPageForgotPwdLinkTest() {
		Assert.assertTrue(loginpage.isforgotPwdLinkExist());
	
	}
	
	@DataProvider
	public Object[][] getRegisterData() {
		Object regData [][] = ExcelUtil.getTestData(Constants.LOGIN_SHEET_NAME);
		return regData;
		
	}
	
	@Test(dataProvider = "getRegisterData")
	@Severity(SeverityLevel.BLOCKER)
	public void loginTest(String userName, String password) {
	//accPage = loginpage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	accPage =loginpage.doLogin(userName, password);
//	String accPagetitle = accPage.getAccountPageTitle();
//	Assert.assertEquals(accPagetitle, Constants.ACCOUNTS_PAGE_TITLE);
	Assert.assertTrue(accPage.logoutLinkExist());
	}
	
	
}
