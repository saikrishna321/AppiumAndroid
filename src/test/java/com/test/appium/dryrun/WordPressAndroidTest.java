package com.test.appium.dryrun;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.test.util.BaseTest;

public class WordPressAndroidTest extends BaseTest {

	@Test
	public void testParallel() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("com.android2.calculator3:id/cling_dismiss")).click();
		driver.findElement(By.id("com.android2.calculator3:id/digit5")).click();
		driver.findElement(By.id("com.android2.calculator3:id/plus")).click();
		driver.findElement(By.id("com.android2.calculator3:id/digit9")).click();
		driver.findElement(By.id("com.android2.calculator3:id/equal")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String num = driver.findElement(By.xpath("//android.widget.EditText[@index=0]")).getText();
		System.out.println("Result : " + num);
		driver.findElement(By.id("com.android2.calculator3:id/digit5")).click();
		driver.findElement(By.id("com.android2.calculator3:id/plus")).click();
		driver.findElement(By.id("com.android2.calculator3:id/digit9")).click();
		driver.findElement(By.id("com.android2.calculator3:id/equal")).click();
		driver.closeApp();
	}

}
