package com.lz.libarary.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.lz.libarary.utils.Base;

/**
 * 
 * @version 1.00.00
 * @description: 社区资源管理平台-门禁管理-黑名单页面类
 * @author: songer.xing
 * @date: 2016-07-05
 * @history:
 */
public class CardToBlackList extends Page {

	@FindBy(linkText = "黑名单管理")
	private static WebElement cardToBlackListLink;
	// 住户卡管理>添加住户卡>保存
	@FindBy(className = "buttonContent")
	private static List<WebElement> saveBtn;
	// 住户卡管理>关键词
	@FindBy(className = "textInput")
	private static List<WebElement> keywords;
	static Logger log = LogManager.getLogger(CardToBlackList.class.getCanonicalName());
	

	public static void openCardToBlackListLink() {

		cardToBlackListLink.click();

	}

	public static boolean moveBlackList(WebDriver driver, int row) throws InterruptedException {
		// 等待添加小区按钮出现
		try {
			new WebDriverWait(driver, 15).until(ExpectedConditions
					.presenceOfElementLocated(By.className("delete")));
			String xpath = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[8]";
			WebElement tableCell = driver.findElement(By.xpath(xpath));
			WebElement deleteTableCell = tableCell.findElement(By
					.linkText("移出黑名单"));
			deleteTableCell.click();
			Thread.sleep(2000);
			driver.findElement(By.className("button")).click();
			Thread.sleep(2000);
			new WebDriverWait(driver, 20).until(ExpectedConditions
					.presenceOfElementLocated(By
							.xpath("//*[@class='correct']/div[2]/ul/li/a")));
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@class='correct']/div[2]/ul/li/a"))
					.click();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			takeScreenShot();
			Base.closeTab(driver);
			// TODO: handle exception
		}
		return false;
	}
		
}
