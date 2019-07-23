package com.lz.libarary.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testng.CommunityController;

import com.lz.libarary.utils.Base;
import com.mysql.jdbc.log.Log;

/**
 * 
 * @version 1.00.00
 * @description: 社区资源管理平台-门禁管理-门禁卡查询页面类
 * @author: songer.xing
 * @date: 2016-07-16
 * @history:
 */
public class accCard extends Page {

	@FindBy(linkText = "门禁卡查询")
	private static WebElement accCardLink;
	// 住户卡管理>添加住户卡>保存
	@FindBy(name = "cardNexus.card_num")
	private static WebElement accCardNo;
	// 查询按钮
	@FindBy(css = "button[class='buttonSeacher'][type='submit']")
	private static WebElement seachBtn;

	static Logger log = LogManager.getLogger(accCard.class.getCanonicalName());

	public static void openAccCardLink() {

		accCardLink.click();

	}

	public static void findKeywords(WebDriver driver, String keywordsValue)
			throws InterruptedException {
		try {
			new WebDriverWait(driver, 15).until(ExpectedConditions
					.presenceOfElementLocated(By.name("cardNexus.card_num")));
			Thread.sleep(3000);
			accCardNo.clear();
			accCardNo.sendKeys(keywordsValue);
			seachBtn.click();
		} catch (Exception e) {
			e.printStackTrace();
			takeScreenShot();
			Base.closeTab(driver);
			// TODO: handle exception
		}
		// seachBtn.get(1).click();
	}

	public static boolean accCardCheck(WebDriver driver,
			String statusTextValue,String expireTextValue,String typeTextValue,String balackTextValue, int row) throws InterruptedException {
		// 等待添加小区按钮出现
		try {
			String statusXpath = "//*[@class='gridTbody']/table/tbody/tr["
					+ row + "]/td[8]";
			log.info("-----------1");
			WebElement statusText = driver.findElement(By.xpath(statusXpath));
			String expireXpath = "//*[@class='gridTbody']/table/tbody/tr["
					+ row + "]/td[9]";
			WebElement expireText = driver.findElement(By.xpath(expireXpath));
			String typeXpath = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[10]";
			WebElement typeText = driver.findElement(By.xpath(typeXpath));
			String balackXpath = "//*[@class='gridTbody']/table/tbody/tr["
					+ row + "]/td[11]";
			log.info("-----------2");
			WebElement balackText = driver.findElement(By.xpath(balackXpath));
			log.info("-----------3");
			log.info("--------" + statusText.getText());
			log.info("--------" + expireText.getText());
			log.info("--------" + typeText.getText());
			log.info("--------" + balackText.getText());
			if (statusText.getText().equals(statusTextValue)
					&& expireText.getText().equals(expireTextValue)
					&& typeText.getText().equals(typeTextValue)
					&& balackText.getText().equals(balackTextValue)) {
				return true;
			}
			log.info("-----------4");
			takeScreenShot();
			Base.closeTab(driver);			
			return false;
		} catch (Exception e) {
			log.info("-----------5");
			log.error(e.toString());
			takeScreenShot();
			Base.closeTab(driver);
			// TODO: handle exception
		}
		takeScreenShot();
		Base.closeTab(driver);
		return false;
	}
	
	public static boolean isAccCard(WebDriver driver,
			String cardNoValue, int row) throws InterruptedException {
		// 等待添加小区按钮出现
		try {
			if (row==-1) {
				return false;
				
			}
			new WebDriverWait(driver, 15).until(ExpectedConditions
					.presenceOfElementLocated(By.name("cardNexus.card_num")));
			String cardNoXpath = "//*[@class='gridTbody']/table/tbody/tr["
					+ row + "]/td[1]";
			WebElement cardNoText = driver.findElement(By.xpath(cardNoXpath));			
			log.info("--------" + cardNoText.getText());
			if (cardNoText.getText().equals(cardNoValue)) {
				return true;
			}
			takeScreenShot();
			Base.closeTab(driver);
			return false;
		} catch (Exception e) {
			log.error(e.toString());
			takeScreenShot();
			Base.closeTab(driver);
			return false;
			// TODO: handle exception
		}
	}

}
