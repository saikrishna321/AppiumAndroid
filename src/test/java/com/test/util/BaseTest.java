package com.test.util;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class BaseTest {

	protected AndroidDriver<MobileElement> driver;

	@BeforeTest
	public void validAuthenticationTest() throws MalformedURLException {
		System.out.println("Appium Server Ports**********" + System.getProperty("environment"));
		System.out.println("device ID**********" + System.getProperty("deviceId"));
		System.out.println("device OS**********" + System.getProperty("deviceOS"));
		System.out.println("http://127.0.0.1:" + System.getProperty("environment") + "/wd/hub");

		DesiredCapabilities capabilities = DesiredCapabilities.android();
		capabilities.setCapability("deviceName", "Android");
		capabilities.setCapability("platformName", "android");
		capabilities.setCapability("platformVersion", System.getProperty("deviceOS"));
		capabilities.setCapability("app", System.getProperty("user.dir") + "/build/AndroidCalculator.apk");
		capabilities.setCapability("package", "com.android2.calculator3");
		capabilities.setCapability("appActivity", "com.android2.calculator3.Calculator");
		// capabilities.setCapability("udid", System.getProperty("deviceID"));

		driver = new AndroidDriver<MobileElement>(
				new URL("http://127.0.0.1:" + System.getProperty("environment") + "/wd/hub"), capabilities);

	}

	@AfterTest
	public void kill() {
		driver.quit();
	}
}
