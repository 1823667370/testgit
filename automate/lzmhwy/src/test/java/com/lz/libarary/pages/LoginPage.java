package com.lz.libarary.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.lz.libarary.utils.Base;

/**
 * 
 * @version 1.00.00
 * @description: 社区资源管理平台登录页
 * @author: songer.xing
 * @date: 2016-6-10
 * @history:
 */
public  class LoginPage extends Page{
	//0统一登录平台，1社区资源管理平台，2播控平台，3经分
	@FindBy(className = "top") 
	private static List<WebElement> communityLogo;
	@FindBy(id = "login_name") 
	private static WebElement loginname;
	@FindBy(id = "passwd") 
	private static WebElement password;	
	@FindBy(id = "validateCode") 
	private static WebElement validateCode;
	@FindBy(id = "loginBtn") 
	private static WebElement loginBtn;
	@FindBy(id = "areaLogin") 
	private static WebElement wyLoginBtn;
	@FindBy(id = "areaLogin") 
	private static WebElement loginBtn1;
	static Logger log = LogManager.getLogger(LoginPage.class.getCanonicalName());
	//private WebDriver driver;
	//private String url;
	
	
/*	public LoginPage(WebDriver driver,String url){
		this.driver=driver;
		this.url=url;
		this.driver.get(this.url);
	}*/

	public static boolean loginCommunity( WebDriver driver,String username, String pwd) throws InterruptedException {
		communityLogo.get(1).click();
		loginname.sendKeys(username);
		password.sendKeys(pwd);
		loginBtn.click();
		Thread.sleep(3000);
		Base.convertHandle(driver);	
		return driver.getTitle().contains("联掌门户社区资源管理平台");

	}
	
	public static boolean loginCommunityAction( WebDriver driver,String username, String pwd) throws InterruptedException {
		loginname.sendKeys(username);
		password.sendKeys(pwd);
		wyLoginBtn.click();
		Thread.sleep(3000);
		return driver.getTitle().contains("联掌门户社区资源管理平台");

	}
	
	public static boolean loginWyAction( WebDriver driver,String username, String pwd) throws InterruptedException {
		loginname.sendKeys(username);
		password.sendKeys(pwd);
		wyLoginBtn.click();
		Thread.sleep(3000);
		//Base.convertHandle(driver);	
		return driver.getTitle().contains("联掌门户物业管理云平台");

	}
	
	public static boolean loginCommunityAction_P( WebDriver driver,String username, String pwd) throws InterruptedException {
		loginname.sendKeys(username);
		password.sendKeys(pwd);
		loginBtn1.click();
		Thread.sleep(3000);
		return driver.getTitle().contains("联掌门户物业管理云平台");

	}

}
