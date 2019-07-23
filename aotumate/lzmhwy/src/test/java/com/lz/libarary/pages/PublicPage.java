package com.lz.libarary.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.lz.libarary.utils.Base;

/**
 * 
 * @version 1.00.00
 * @description: 社区资源管理平台公共页面类
 * @author: songer.xing
 * @date: 2016-6-10
 * @history:
 */

public class PublicPage extends Page {
	// 社区资源左边菜单栏列表
	@FindBy(className = "downActive")
	private static List<WebElement> leftList;
	@FindBy(className = "upActive")
	private static List<WebElement> disLeftList;
	// 查询输入框
	@FindBy(className = "textInput")
	private static List<WebElement> keywords;
	// 查询按钮
	@FindBy(css = "button[class='buttonSeacher'][type='submit']")
	private static WebElement seachBtn;
	// 读取卡号>读卡模式radio
	@FindBy(name = "read_card_add_type")
	private static List<WebElement> readCardType;
	// 读取卡号>卡种类
	@FindBy(id = "read_card_type_2")
	private static WebElement cardType;
	// 读取卡号>卡号
	@FindBy(id = "read_card_caccount_2")
	private static WebElement cardNo;
	@FindBy(className = "buttonContent")
	private static List<WebElement> saveBtn;
	static Logger log = LogManager.getLogger(PropertyNote.class.getCanonicalName());

	public static boolean clickLeftListAction(WebDriver driver, Integer index) {
		new WebDriverWait(driver, 20).until(ExpectedConditions
				.presenceOfElementLocated(By.className("downActive")));
		leftList.get(index).click();
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.presenceOfElementLocated(By.className("upActive")));
		log.info(disLeftList.get(0).getAttribute("class"));
		return disLeftList.get(0).getAttribute("class").contains("upActive");
	}

	public static boolean closeLeftListAction(WebDriver driver) {
		new WebDriverWait(driver, 20).until(ExpectedConditions
				.presenceOfElementLocated(By.className("upActive")));
		disLeftList.get(0).click();
		By locator = By.className("upActive");
		if (!(Base.isElementExsit(driver, locator))) {
			return true;
		}

		return false;
	}

	/**
	 * 通用查找
	 */
	public static void findKeywords(WebDriver driver, String keywordsValue)
			throws InterruptedException {
		try {
			Thread.sleep(3000);
			keywords.get(0).clear();
			keywords.get(0).sendKeys(keywordsValue);
			seachBtn.click();
			log.info("查找成功");
		} catch (Exception e) {
			log.info("查找失败");
			log.error(e.toString());			
			takeScreenShot();
			Base.closeTab(driver);
			// TODO: handle exception
		}
		// seachBtn.get(1).click();
	}

	/**
	 * 通用删除
	 */
	public static boolean delete(WebDriver driver, int row, int column)
			throws InterruptedException {
		Thread.sleep(2000);
		// 找到指定值行的修改link
		try {
			String xpath = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[" + column + "]";
			WebElement tableCell = driver.findElement(By.xpath(xpath));
			WebElement deleteTableCell = tableCell.findElement(By
					.linkText("删除"));
			deleteTableCell.click();
			Thread.sleep(1000);
			driver.findElement(By.className("button")).click();
			// new WebDriverWait(driver, 20)
			// .until(ExpectedConditions.presenceOfElementLocated(By
			// .cssSelector("div.msg.msgMsg")));
			// JavascriptExecutor js = (JavascriptExecutor) driver;
			// js.executeScript("alertMsg.close()");
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.presenceOfElementLocated(By
							.xpath("//*[@class='correct']/div[2]/ul/li/a")));
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@class='correct']/div[2]/ul/li/a"))
					.click();
			Thread.sleep(1000);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			takeScreenShot();
			Base.closeTab(driver);
			// TODO: handle exception
		}
		return false;
	}

	/**
	 * 通用查找
	 */
	public static boolean readCardNo(WebDriver driver, String cardNoValue)
			throws InterruptedException {
		try {

			if (readCardType.get(0).isSelected()
					&& !(readCardType.get(1).isSelected())) {
				readCardType.get(1).click();
				Select selectCounty = new Select(cardType);
				selectCounty.selectByValue("IC卡");
				cardNo.sendKeys(cardNoValue);
				saveBtn.get(2).click();
			} else {
				saveBtn.get(3).click();
				log.info("读卡界面，读卡模式radio默认错误!");
				Reporter.log("读卡界面，读卡模式radio默认错误!");
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			takeScreenShot();
			return false;
		}
		return true;
	}
}
