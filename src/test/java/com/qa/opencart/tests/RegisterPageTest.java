package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest{
	
	@BeforeClass
	public void regSetUp() {
		regPage = loginpage.goToRegisterPage();
	}

//	This method is to read data from DataProvider
//	@DataProvider
//	public Object[][] RegTestData() {
//		return new Object[][] { {"prabhas1", "raju1", "prabhasr1@gmail.com", "9873154178", "prabha1@123", "no"},
//								{"nani", "macha", "nanim@gmail.com", "9073154123", "nanni@123", "yes"}
//			
//		};
//	}
	
	public String getRandomNumber() {  
		Random randomGen = new Random();
		String email = "siriautomation"+randomGen.nextInt(1000)+"@gmail.com";
		return email;
	}
	//after completing testing we can go to database and delete emails id where email like %"siriautmation"
	
	@DataProvider
	public Object[][] getRegisterData() {
		Object regData [][] = ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
		return regData;
		
	}
	
	@Test(dataProvider = "getRegisterData")
	public void userRegistrationTest(String firstName, String lastName, 
							String telephone,String password, String subscribe) {
		Assert.assertTrue(regPage.accountRegistration(firstName, lastName, getRandomNumber(), telephone, password, subscribe));
	}
}
