package com.lz.libarary.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.lz.libarary.utils.Base;

/**
 * 
 * @version 1.00.00
 * @description: 社区资源管理平台-门禁管理-过期卡管理页面类
 * @author: songer.xing
 * @date: 2016-07-06
 * @history:
 */
public class ExpireCardManagement extends Page {

	@FindBy(linkText = "过期卡管理")
	private static WebElement expireCardManagementLink;
	// 过期卡管理>重启>保存
	@FindBy(className = "buttonContent")
	private static List<WebElement> saveBtn;
	@FindBy(name = "card.card_num")
	private static WebElement keywords;
	// 查询按钮
	@FindBy(css = "button[class='buttonSeacher'][type='submit']")
	private static WebElement seachBtn;
	static Logger log = LogManager.getLogger(ExpireCardManagement.class.getCanonicalName());

	public static void openCardToBlackListLink() {

		expireCardManagementLink.click();

	}

	public static boolean cardExpireEnable(WebDriver driver, int row) throws InterruptedException {
		// 等待添加小区按钮出现
		try {
			String xpath = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[10]";
			WebElement tableCell = driver.findElement(By.xpath(xpath));
			WebElement deleteTableCell = tableCell.findElement(By
					.linkText("重启"));
			deleteTableCell.click();
			Thread.sleep(4000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("document.getElementById('expire_cycle_start').value='2016-07-01'");
			js.executeScript("document.getElementById('expire_cycle_end').value='2018-07-01'");
			saveBtn.get(0).click();
			Thread.sleep(2000);
			new WebDriverWait(driver, 20)
			.until(ExpectedConditions.presenceOfElementLocated(By
					.cssSelector("div.msg.msgMsg")));
			Thread.sleep(1000);
	        js.executeScript("alertMsg.close()");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			takeScreenShot();
			Base.closeTab(driver);
			// TODO: handle exception
		}
		return false;
	}
	
	public static void findKeywords(WebDriver driver, String keywordsValue)
			throws InterruptedException {
		try {
			Thread.sleep(3000);
			keywords.clear();
			keywords.sendKeys(keywordsValue);
			seachBtn.click();
		} catch (Exception e) {
			e.printStackTrace();
			takeScreenShot();
			Base.closeTab(driver);
			// TODO: handle exception
		}
		// seachBtn.get(1).click();
	}
}
