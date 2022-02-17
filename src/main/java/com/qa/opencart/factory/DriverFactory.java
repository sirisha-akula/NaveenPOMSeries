package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	
	public Properties prop;
	
	public OptionsManager optionsManager;
	
	public static String highlight;
	
	public static  ThreadLocal<WebDriver> tlDriver = new ThreadLocal();

	/**
	 * this method is used to intialize the driver using the browser name
	 * @param browserName
	 * @return this method returns WebDriver
	 */
	public WebDriver init_driver(Properties prop) {
		
		String browserName = prop.getProperty("browser");
		highlight = prop.getProperty("highlight").trim();
		
		System.out.println("browser name is : " + browserName);
		optionsManager = new OptionsManager(prop);

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));

		
		} else if (browserName.equalsIgnoreCase("safari")) {

			tlDriver.set(new SafariDriver());
			
		} else {
			System.out.println("Please pass the right browser name : " + browserName);
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url").trim());
		
		return getDriver();
		
	}
	/**
	 * this will return the thread local copy of the driver
	 * @return
	 */
		public static WebDriver getDriver() {
			
			return tlDriver.get();
		}


	/**
	 * this method is used to initialize the properties
	 * 
	 * @return this method returns the Properties class ref
	 */
	public Properties init_prop() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/main/resources/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	//ThreadLocal -- JDK 8 ---> create a local copy  of driver
	//set driver with TL
	//get driver  -- driver
	//driver null problem
	//u can take driver copy  anywhere in ur framework
	//better thread management
	//to avoid the dead lock condition -- TL driver copy
	//large testcase count 	-- 200 , 300 TCS --> proper  test result
	
	
	/**
	 * take screenshot
	 */
	public String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
}
