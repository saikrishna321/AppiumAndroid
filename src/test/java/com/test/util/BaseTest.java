package com.test.util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest{

	protected AndroidDriver<MobileElement> driver;

	
	@BeforeTest
	public void setUp() throws MalformedURLException {	
		Reporter.log(System.getProperty("deviceName"));
		System.out.println("Appium Server Ports**********" + System.getProperty("environment"));
		System.out.println("device ID**********" + System.getProperty("deviceId"));
		System.out.println("http://127.0.0.1:" + System.getProperty("environment") + "/wd/hub");

			DesiredCapabilities capabilities = DesiredCapabilities.android();
			capabilities.setCapability("deviceName", "Android");
			capabilities.setCapability("platformName", "android");
			capabilities.setCapability("platformVersion", "5.X");
		    capabilities.setCapability("browserName","");
			capabilities.setCapability("app", System.getProperty("user.dir") + "/build/AndroidCalculator.apk");
			capabilities.setCapability("package", "com.android2.calculator3");
			capabilities.setCapability("appActivity", "com.android2.calculator3.Calculator");

			driver = new AndroidDriver<MobileElement>(
					new URL("http://127.0.0.1:" + System.getProperty("environment") + "/wd/hub"), capabilities);
	}

	@AfterTest
	public void kill() {
		driver.quit();
	}

	public void waitForElement(By id, int time) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable((id)));
	}

	public AppiumDriver<MobileElement> getDriver(){
		return driver;
	}
}
