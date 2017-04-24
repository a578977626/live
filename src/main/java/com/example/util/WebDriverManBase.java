package com.example.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import io.github.bonigarcia.wdm.PhantomJsDriverManager;

public abstract class WebDriverManBase {
	private static WebDriver driver;
	static{
		 PhantomJsDriverManager.getInstance().setup();
//		 ChromeDriverManager.getInstance().setup();
//		 driver = new ChromeDriver();
	}
	
	protected static WebDriver getDriver() {
		if(driver==null){
			driver = new PhantomJSDriver();
		}
        return driver;
    }
	
	public static void teardown() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
