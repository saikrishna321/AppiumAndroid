package com.test.appium.dryrun;

import com.test.util.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class WordPressAndroidTest extends BaseTest {

	@Test
	public void testMethodOne_1() throws Exception  {

		System.out.println("ThreadName: " + Thread.currentThread().getName() + Thread.currentThread().getStackTrace()[1].getClassName());
		Thread.sleep(3000);
		getDriver().findElement(By.id("com.android2.calculator3:id/cling_dismiss")).click();
		getDriver().findElement(By.id("com.android2.calculator3:id/digit2")).click();
		getDriver().findElement(By.id("com.android2.calculator3:id/plus")).click();
		getDriver().findElement(By.id("com.android2.calculator3:id/digit9")).click();
		getDriver().findElement(By.id("com.android2.calculator3:id/equal")).click();
		getDriver().findElement(By.id("com.android2.calculator3:id/plus")).click();
		getDriver().findElement(By.id("com.android2.calculator3:id/digit9")).click();
		getDriver().findElement(By.id("com.android2.calculator3:id/equal")).click();
		//getDriver().close();
	}
}
