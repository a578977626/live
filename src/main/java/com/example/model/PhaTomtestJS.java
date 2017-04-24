package com.example.model;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class PhaTomtestJS { 
	public static void fff(){
		
//		D:\\phantomjs-2.1.1-windows\\phantomjs-2.1.1-windows\bin\\phantomjs.exe
//		C:\\selenium\\phantomjs.exe
//		System.setProperty("phantomjs.binary.path","D:/phantomjs-2.1.1-windows/phantomjs-2.1.1-windows/bin/phantomjs.exe");// set phantomjs exe path  
//		
//		DesiredCapabilities desiredCapabilities = DesiredCapabilities.phantomjs();  
//		desiredCapabilities.setCapability("loadImages",false);  
//		
//		PhantomJSDriver driver = new PhantomJSDriver(desiredCapabilities);  
//		driver.get("http://www.baidu.com");  
//		System.out.println("START!!!!!!!!!!!!!!!!!!!!!!!!!!");
//		System.out.println("only this-------------------------------"+driver.getPageSource());  
//		System.out.println("END!!!!!!!!!!!!!!!!!!!!!!!!!!");
//		driver.close();
//		driver.quit();
		String source ="saddd<div><img src=\"https://h5-test.by-health.com/tcbjHome/uploadFile/min/03188e65-2d44-4cc6-ad84-7bd4353067f3.jpg\"><br>dfd</div><div><img src=\"https://h5-test.by-health.com/tcbjHome/uploadFile/min/19eb5c8e-00bb-418b-93bc-17dc9b126616.PNG\"><br><br></div>";
		Document doc = Jsoup.parse(source);
		Elements eles = doc.getAllElements();
		String test = doc.text();
		if(test.length()>5){
			String res = test.substring(0, 5);
					System.out.println(res);
		}
		System.out.println(test);
	}
  
//    public static void main(String[] args) throws IOException {  
//    	fff();
//    }  

}
