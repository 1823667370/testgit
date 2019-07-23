package com.lz.libarary.utils;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.apache.commons.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

/**
 * 
 * @version 1.00.00
 * @description: 基础公共功能类
 * @author: songer.xing
 * @date: 2016-6-10
 * @history:
 */
public class Base {

	private static Logger log = Logger.getLogger(Log.class.getName());

	/**
	 * @description:转换窗口句柄
	 * @param
	 * @return
	 */
	public static void convertHandle(WebDriver driver) {

		log.info("Old page title is:" + driver.getTitle());
		Reporter.log("Old page title is:" + driver.getTitle());
		String currentWindow = driver.getWindowHandle();// 获取当前窗口句柄
		Set<String> handles = driver.getWindowHandles();// 获取所有窗口句柄
		Iterator<String> it = handles.iterator();
		while (it.hasNext()) {
			if (currentWindow == it.next()) {
				continue;
			}
			
			driver.switchTo().window(currentWindow).close();
			WebDriver window = driver.switchTo().window(it.next());// 转换到新窗口
			log.info("New page title is:" + window.getTitle());
			Reporter.log("New page title is:" + window.getTitle());
		}

	}

	public static void closeTab(WebDriver driver) {
		try {
			WebElement tab = driver.findElement(By.className("selected"));
			Thread.sleep(1000);
			WebElement closeTab = tab.findElement(By.className("close"));
			Thread.sleep(1000);
			closeTab.click();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void closeTab2(WebDriver driver) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("document.getElementsByClassName('close')[1].click()");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static boolean isElementExsit(WebDriver driver, By locator) {
		boolean flag = false;
		try {
			WebElement element = driver.findElement(locator);
			flag = null != element;
		} catch (NoSuchElementException e) {
			System.out.println("Element:" + locator.toString() + " is not exsit!");
			return false;
		}
		return flag;
		
	}

	/**
	 * @description:物业选择弹出框
	 * @param
	 * @return
	 * */
	public static boolean selectProperty(WebDriver driver, String propertyName,Integer index)
			throws InterruptedException {
		try {
		// Thread.sleep(2000);
		new WebDriverWait(driver, 15)
				.until(ExpectedConditions.presenceOfElementLocated(By
						.xpath("//*[@class='dialogContent layoutBox unitBox']/div[2]/div[@class='grid']")));
		int row = 0;
		// 查询弹出框物业
		JavascriptExecutor popJs = (JavascriptExecutor) driver;
		popJs.executeScript("document.getElementsByClassName('searchItmCont h_searchItmCont clearfix')["+ index + "].getElementsByClassName('textInput')[0].value='"
				+ propertyName + "'");
		popJs.executeScript("document.getElementsByClassName('buttonSeacher')[2].click()");
		Thread.sleep(2000);
		row = tableAction.dialogReturntableRow(driver, propertyName);
		String xpath = "//*[@class='dialogContent layoutBox unitBox']/div[2]/div[@class='grid']/div[2]/div[@class='gridTbody']/table/tbody/tr["
				+ row + "]/td[6]";
		WebElement tableCell = driver.findElement(By.xpath(xpath));
		WebElement popSelectBtn = tableCell.findElement(By.linkText("选择"));
		popSelectBtn.click();
		return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("选择物业失败，将关闭对话框.....");
			JavascriptExecutor closeJs = (JavascriptExecutor) driver;
			closeJs.executeScript("document.getElementsByClassName('close')[3].click()");
			return false;
		}

	}
	
	/**
	 * @description:物业选择弹出框
	 * @param
	 * @return
	 * */
	public static boolean selectBranch(WebDriver driver, String BranchName,Integer index)
			throws InterruptedException {
		try {
		// Thread.sleep(2000);
		new WebDriverWait(driver, 15)
				.until(ExpectedConditions.presenceOfElementLocated(By
						.xpath("//*[@class='dialogContent layoutBox unitBox']/div[2]/div[@class='grid']")));
		int row = 0;
		// 查询弹出框物业
		JavascriptExecutor popJs = (JavascriptExecutor) driver;
		popJs.executeScript("document.getElementsByClassName('searchItmCont h_searchItmCont clearfix')["+ index + "].getElementsByClassName('textInput')[0].value='"
				+ BranchName + "'");
		popJs.executeScript("document.getElementsByClassName('buttonSeacher')[2].click()");
		Thread.sleep(5000);
		row = tableAction.dialogReturntableRow(driver, BranchName);
		String xpath = "//*[@class='dialogContent layoutBox unitBox']/div[2]/div[@class='grid']/div[2]/div[@class='gridTbody']/table/tbody/tr["
				+ row + "]/td[3]";
		WebElement tableCell = driver.findElement(By.xpath(xpath));
		WebElement popSelectBtn = tableCell.findElement(By.linkText("选择"));
		popSelectBtn.click();	
	//	((JavascriptExecutor)driver).executeScript("document.getElementsByClassName('btnSelect')[0].click()");
		log.info("选择分公司成功");
		return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("选择分公司，将关闭对话框.....");
			JavascriptExecutor closeJs = (JavascriptExecutor) driver;
			closeJs.executeScript("document.getElementsByClassName('close')[3].click()");
			return false;
		}

	}
	
	public static boolean selectCommunity(WebDriver driver, String communityName,Integer index)
			throws InterruptedException {
		try {
	    Thread.sleep(1000);
		int row = 0;
		// 查询弹出框小区
		JavascriptExecutor popJs = (JavascriptExecutor) driver;
		popJs.executeScript("document.getElementsByClassName('searchItmCont h_searchItmCont clearfix')["+ index + "].getElementsByClassName('textInput')[0].value='"
				+ communityName + "'");
		popJs.executeScript("document.getElementsByClassName('buttonSeacher')[2].click()");
		Thread.sleep(2000);
		row = tableAction.dialogResidentBox(driver, communityName);
		String xpath = "//*[@class='dialogContent layoutBox unitBox']/div[2]/div[@class='grid']/div[2]/div[@class='gridTbody']/table/tbody/tr["
				+ row + "]/td[7]";

		WebElement tableCell = driver.findElement(By.xpath(xpath));
		WebElement popSelectBtn = tableCell.findElement(By.linkText("选择"));
		popSelectBtn.click();
		return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("选择小区失败，将关闭对话框.....");
			JavascriptExecutor closeJs = (JavascriptExecutor) driver;
			closeJs.executeScript("document.getElementsByClassName('close')[3].click()");
			return false;
		}
	}
	
	public static boolean isElementPresent(WebDriver driver,By by) { 
		    try { 
		      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		      driver.findElement(by); 
		      return true; 
		    } catch (NoSuchElementException e) { 
		      return false; 
		    } 
		  }

	
}
