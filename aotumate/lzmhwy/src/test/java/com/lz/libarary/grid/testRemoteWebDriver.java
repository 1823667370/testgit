package com.lz.libarary.grid;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class testRemoteWebDriver {

/*	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		WebDriver driver = new RemoteWebDriver(new URL(
				"http://106.75.75.112:4446/wd/hub/"),
				DesiredCapabilities.firefox());
		driver.get("https://passport.csdn.net/account/login");
		System.out.println("开始");
		WebDriver augmentedDriver = new Augmenter().augment(driver);
		File screenshot = ((TakesScreenshot) augmentedDriver)
				.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File("11.png"));
		driver.quit();

	}*/
	//执行webdriver的node需要DesiredCapabilities，RemoteWebDriver
/*	public static void main2(String[] args) throws IOException {
		// TODO Auto-generated method stub
		DesiredCapabilities ff =DesiredCapabilities.firefox();
		ff.setBrowserName("firefox");
		//ff.setVersion("43");
		//ff.setPlatform("WINDOWS");
		WebDriver driver = new RemoteWebDriver(new URL(
				"http://106.75.75.112:6666/wd/hub/"),
				ff);
		
		driver.get("https://passport.csdn.net/account/login");
		System.out.println("开始");
		WebDriver augmentedDriver = new Augmenter().augment(driver);
		File screenshot = ((TakesScreenshot) augmentedDriver)
				.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File("11.png"));
		driver.quit();

	}*/
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//DesiredCapabilities ff =DesiredCapabilities.firefox();
		//ff.setBrowserName("firefox");
		//ff.setVersion("43");
		//ff.setPlatform("WINDOWS");
		WebDriver driver = Browser.getRemoteDriver(new Browser("chrome"));
		
		driver.get("https://passport.csdn.net/account/login");
		System.out.println("开始");
		WebDriver augmentedDriver = new Augmenter().augment(driver);
		File screenshot = ((TakesScreenshot) augmentedDriver)
				.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File("11.png"));
		driver.quit();

	}

}
