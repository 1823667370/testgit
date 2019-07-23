package com.lz.libarary.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.lz.libarary.utils.Base;

public class SystemMangement extends Page{
	/**
	 * 
	 * @version 1.00.00
	 * @description: 社区资源管理平台-系统管理
	 * @author: songer.xing
	 * @date: 2017-05-31
	 * @history:
	 */
	@FindBy(id = "system-management")
	private static WebElement systemManagement;
	@FindBy(linkText = "用户管理")
	private static WebElement sysUserList;
	@FindBy(id = "checkAll")
	private static WebElement checkAll;
	// 住户管理>添加住户>保存
	@FindBy(className = "buttonContent")
	private static List<WebElement> saveBtn;
	@FindBy(id = "jsLogin")
	private static WebElement jsLogin;
	@FindBy(id = "login_name") 
	private static WebElement loginname;
	@FindBy(id = "passwd") 
	private static WebElement password;	
	@FindBy(id = "loginBtn") 
	private static WebElement loginBtn;	
	
	public static boolean loginSystem( WebDriver driver,String username, String pwd) throws InterruptedException {
		jsLogin.click();
		loginname.sendKeys(username);
		password.sendKeys(pwd);
		loginBtn.click();
		Thread.sleep(3000);
		systemManagement.click();
		Thread.sleep(2000);
		Base.convertHandle(driver);	
		return driver.getTitle().contains("系统管理");

	}
	
	public static void userManagementtLink() throws InterruptedException {
		sysUserList.click();
	}
	
	public static void editUser(WebDriver driver,
			int row) throws InterruptedException {
		try {
			// 找到指定值行的修改link
			String xpath = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[7]";
			new WebDriverWait(driver, 15).until(ExpectedConditions
					.presenceOfElementLocated(By.xpath(xpath)));
			WebElement tableCell = driver.findElement(By.xpath(xpath));
			WebElement editTableCell = tableCell.findElement(By
					.className("btnEdit"));
			editTableCell.click();
			Thread.sleep(3000);
			checkAll.click();
			saveBtn.get(0).click();
     }catch (Exception e) {
    	 
     }
	}
}
