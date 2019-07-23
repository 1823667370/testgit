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
 * @description: 社区资源管理平台-门禁管理-工程卡页面类
 * @author: songer.xing
 * @date: 2016-07-03
 * @history:
 */
public class CardEngineerMangement extends Page {

	@FindBy(linkText = "工程卡管理")
	private static WebElement cardEngineerLink;
	// 工程卡>添加工程卡按钮
	@FindBy(className = "add")
	private static WebElement addCardEngineerBtn;
	// 工程卡>添加工程卡>所属物业放大镜
	@FindBy(id = "cardEngineeringCommunitysDialog")
	private static WebElement cardEngineerAddDialog;
	// 工程卡>添加工程卡>读取卡号按钮
	@FindBy(id = "cardEngineeringInpDialog")
	private static WebElement cardReadBtn;
	// 工程卡>添加工程卡>工程卡 姓名
	@FindBy(name = "card.name")
	private static WebElement cardName;
	// 工程卡>添加工程卡>工程卡 手机号码
	@FindBy(name = "card.contact")
	private static WebElement cardContact;
	// 卡类型>添加住户>保存
	@FindBy(className = "buttonContent")
	private static List<WebElement> saveBtn;
	// 卡类型>关键词
	@FindBy(className = "textInput")
	private static List<WebElement> keywords;
	static Logger log = LogManager.getLogger(CardEngineerMangement.class.getCanonicalName());

	public static void openCardEngineerLink() {

		cardEngineerLink.click();

	}

	public static boolean addCardEngineer(WebDriver driver,
			String communityNameValue, String cardEngineerNameValue,
			String cardEngineerPhone,String cardEngineerNoValue) throws InterruptedException {
		// 等待添加小区按钮出现
		boolean isSunccess = true;
		try {
			new WebDriverWait(driver, 15).until(ExpectedConditions
					.presenceOfElementLocated(By.className("add")));
			addCardEngineerBtn.click();
			Thread.sleep(2000);
			cardEngineerAddDialog.click();
			Thread.sleep(2000);
			if (Base.selectCommunity(driver, communityNameValue, 3)) {
				// 关闭选择小区对话框
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("document.getElementsByClassName('close')[3].click()");
				cardReadBtn.click();
				Thread.sleep(2000);
				if (PublicPage.readCardNo(driver, cardEngineerNoValue)) {
					cardName.sendKeys(cardEngineerNameValue);
					cardContact.sendKeys(cardEngineerPhone);
					saveBtn.get(0).click();
				}else {
					takeScreenShot();
					saveBtn.get(1).click();	
					Base.closeTab(driver);
					isSunccess = false;
					return false;
				}
				
			} else {
				takeScreenShot();
				if (saveBtn.get(1) != null) {
					log.info("新增工程卡失败，将关闭对话框.....");
					System.out.println("--------" + saveBtn.get(1));
					saveBtn.get(1).click();
					Base.closeTab(driver);
					isSunccess = false;
				}
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			takeScreenShot();
			if (saveBtn.get(1) != null) {
				log.info("新增卡类型失败，将关闭对话框.....");
				System.out.println("--------" + saveBtn.get(1));
				saveBtn.get(1).click();
				Base.closeTab(driver);
				
			}
			isSunccess = false;
			return false;
		}
		if (isSunccess) {
			Thread.sleep(1000);
			new WebDriverWait(driver, 20).until(ExpectedConditions
					.presenceOfElementLocated(By.id("alertMsgBox")));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			Thread.sleep(1000);
			js.executeScript("document.getElementsByClassName('toolBar')[1].getElementsByClassName('button')[1].click()");
		}
		return true;
	}

	public static boolean editCardEngineer(WebDriver driver, 
			int row) throws InterruptedException {
		boolean isSunccess = true;
		try {
			// 等待添加小区按钮出现
			new WebDriverWait(driver, 15).until(ExpectedConditions
					.presenceOfElementLocated(By.className("add")));
			// 找到指定值行的修改link
			String xpath = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[8]";
			WebElement tableCell = driver.findElement(By.xpath(xpath));
			WebElement editTableCell = tableCell.findElement(By.linkText("编辑"));
			editTableCell.click();
			Thread.sleep(2000);
			cardContact.clear();			
			saveBtn.get(0).click();
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			takeScreenShot();
			if (saveBtn.get(1) != null) {
				log.info("新增卡类型失败，将关闭对话框.....");
				saveBtn.get(1).click();
				Base.closeTab(driver);
			}

			isSunccess = false;
			return false;
		}
		if (isSunccess) {
			new WebDriverWait(driver, 20)
					.until(ExpectedConditions.presenceOfElementLocated(By
							.cssSelector("div.msg.msgMsg")));
			Thread.sleep(1000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("alertMsg.close()");
			Thread.sleep(2000);
			String editXpath1 = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[4]";
			WebElement tableCellText1 = driver.findElement(By.xpath(editXpath1));
			System.out.println("-------" + tableCellText1.getText());
			String editXpath2 = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[7]";
			WebElement tableCellText2 = driver.findElement(By.xpath(editXpath2));
			System.out.println("-------" + tableCellText2.getText());
			if (tableCellText1.getText().equals("")&&!(tableCellText2.getText().equals(""))) {
				return true;
			}
		}
		Base.closeTab(driver);
		return false;
	}

}
