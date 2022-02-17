package com.qa.opencart.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.opencart.pages.AccountsPage;
import com.opencart.pages.CartPage;
import com.opencart.pages.LoginPage;
import com.opencart.pages.ProductInfoPage;
import com.opencart.pages.RegisterPage;
import com.opencart.pages.ResultsPage;
import com.qa.opencart.factory.DriverFactory;

public class BaseTest {
	
	DriverFactory df;
	WebDriver driver;
	Properties prop;
	
	LoginPage loginpage;
	AccountsPage accPage;
	RegisterPage regPage;
	ResultsPage resultsPage;
	ProductInfoPage productInfoPage;
	CartPage cartPage;
	SoftAssert softAssert;
	
	
	@BeforeTest
	public void setUp() {
		df = new DriverFactory();
		prop = df.init_prop();
		driver = df.init_driver(prop);
		//driver = df.init_driver(prop.getProperty("browser"));
		loginpage = new LoginPage(driver);
		softAssert= new SoftAssert();
	}
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();

}
}