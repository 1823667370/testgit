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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.lz.libarary.utils.Base;

/**
 * 
 * @version 1.00.00
 * @description: 社区资源管理平台-物业中心-公告页面类
 * @author: songer.xing
 * @date: 2016-07-08
 * @history:
 */
public class PropertyNote extends Page {

	@FindBy(linkText = "公告")
	private static WebElement propertyNoteLink;
	// 公告>添加公告按钮
	@FindBy(className = "add")
	private static WebElement addPropertyNoteBtn;
	// 公告>添加公告>标题
	@FindBy(id = "input_noticeTitle")
	private static WebElement noticeTitle;
	// 公告>添加公告>内容
	@FindBy(id = "input_noticeContent")
	private static WebElement noticeContent;
	// 公告>添加公告>选择发送对象 小区放大镜
	@FindBy(id = "pn_input_community_id")
	private static WebElement sendCommunity;
	// 公告>添加公告>选择小区范围下拉
	@FindBy(id = "selectSendType")
	private static WebElement selectSendType;
	// 公告>添加公告>选择单元按钮
	@FindBy(className = "h_chooseBtn")
	private static WebElement chooseUnitBtn;
	// 公告>添加公告>选择单元
	@FindBy(id = "tree_communityUnitTreeList_4_check")
	private static WebElement selectUnit;
	// 公告>添加公告>选择单元 所选单元机
	@FindBy(id = "unit_names")
	private static WebElement unitName;
	// 公告>添加公告>所选单元内容
	@FindBy(id = "p_input_unit_names")
	private static WebElement unitNameContent;	
	// 公告>添加公告>保存为草稿按钮
	@FindBy(id = "pn_input_bccg_btn")
	private static WebElement saveDraft;	
	// 公告>编辑公告>内容
	@FindBy(id = "update_noticeContent")
	private static WebElement editNoticeContent;
	// 公告>添加公告>立即发送按钮
	@FindBy(id = "pn_input_ljts_btn")
	private static WebElement sendBtn;
	// 公告>添加公告>保存
	@FindBy(className = "buttonContent")
	private static List<WebElement> saveBtn;
	// 公告>关键词
	@FindBy(className = "textInput")
	private static List<WebElement> keywords;
	static Logger log = LogManager.getLogger(PropertyNote.class.getCanonicalName());

	public static void openPropertyNoteLink() {

		propertyNoteLink.click();

	}

	public static boolean addPropertyNote(WebDriver driver,
			String noticeTitleValue, String communityNameValue)
			throws InterruptedException {
		// 等待添加小区按钮出现
		boolean isSunccess = true;
		try {
			new WebDriverWait(driver, 15).until(ExpectedConditions
					.presenceOfElementLocated(By.className("add")));
			addPropertyNoteBtn.click();
			Thread.sleep(2000);
			noticeTitle.sendKeys(noticeTitleValue);
			noticeContent.sendKeys(noticeTitleValue);
			Thread.sleep(1000);
			Select community = new Select(sendCommunity);
			community.selectByVisibleText(communityNameValue);		
			Thread.sleep(1000);
			Select selectRegion = new Select(selectSendType);
			selectRegion.selectByValue("2");
			chooseUnitBtn.click();
			new WebDriverWait(driver, 15).until(ExpectedConditions
						.presenceOfElementLocated(By.id("tree_communityUnitTreeList_4_check")));
			Thread.sleep(2000);
			selectUnit.click();
			Thread.sleep(2000);
			System.out.println("-----"+unitName.getAttribute("value"));
			if (unitName.getAttribute("value").equals("")) {
				saveBtn.get(4).click();
				saveBtn.get(2).click();
				Base.closeTab(driver);
				isSunccess = false;
				return false;
				}
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("sureChoose()");
			//saveBtn.get(3).click();
			saveBtn.get(1).click();
				Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			takeScreenShot();
			if (saveBtn.get(2) != null) {
				log.info("新增公告失败，将关闭对话框.....");
				System.out.println("--------" + saveBtn.get(1));
				saveBtn.get(2).click();
				Base.closeTab(driver);

			}
			isSunccess = false;
			return false;
		}
		if (isSunccess) {
			Thread.sleep(1000);
			new WebDriverWait(driver, 20)
					.until(ExpectedConditions.presenceOfElementLocated(By
							.cssSelector("div.msg.msgMsg")));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			Thread.sleep(1000);
			js.executeScript("alertMsg.close()");
		}
		return true;
	}

	public static boolean editPropertyNote(WebDriver driver, int row)
			throws InterruptedException {
		boolean isSunccess = true;
		try {
			// 等待添加小区按钮出现
			new WebDriverWait(driver, 15).until(ExpectedConditions
					.presenceOfElementLocated(By.className("add")));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			// 找到指定值行的修改link
			String xpath = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[9]";
			WebElement tableCell = driver.findElement(By.xpath(xpath));
			WebElement editTableCell = tableCell.findElement(By.linkText("编辑"));
			editTableCell.click();
			Thread.sleep(2000);
			System.out.println("-----"+editNoticeContent.getText());
			System.out.println("-----"+unitNameContent.getText());
			if (editNoticeContent.getText().equals("")||unitNameContent.getText().equals("")) {
				saveBtn.get(2).click();
				Thread.sleep(2000);
				Base.closeTab(driver);
				isSunccess = false;
				return false;				
			}
			js.executeScript("document.getElementById('start_time').value='2017-07-01'");
			js.executeScript("document.getElementById('end_time').value='2018-07-01'");
			saveBtn.get(0).click();
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			takeScreenShot();
			if (saveBtn.get(2) != null) {
				log.info("编辑公告失败，将关闭对话框.....");
				saveBtn.get(2).click();
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
			Thread.sleep(3000);
			String editXpath1 = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[7]";
			WebElement tableCellText1 = driver
					.findElement(By.xpath(editXpath1));
			System.out.println("-------" + tableCellText1.getText());
			String editXpath2 = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[8]";
			WebElement tableCellText2 = driver
					.findElement(By.xpath(editXpath2));
			System.out.println("-------" + tableCellText2.getText());
			if (tableCellText1.getText().equals("已推送")
					&& !(tableCellText2.getText().equals(""))) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean cancelPropertyNote(WebDriver driver, int row, int column)
			throws InterruptedException {
		// 等待添加小区按钮出现
		Thread.sleep(2000);
		// 找到指定值行的修改link
		try {
			String xpath = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[" + column + "]";
			WebElement tableCell = driver.findElement(By.xpath(xpath));
			WebElement deleteTableCell = tableCell.findElement(By
					.linkText("撤销"));
			deleteTableCell.click();
			Thread.sleep(2000);
			driver.findElement(By.className("button")).click();
			 new WebDriverWait(driver, 20)
			 .until(ExpectedConditions.presenceOfElementLocated(By
			 .cssSelector("div.msg.msgMsg")));
			 Thread.sleep(1000);
			 JavascriptExecutor js = (JavascriptExecutor) driver;
			 js.executeScript("alertMsg.close()");
			Thread.sleep(2000);			
			String editXpath = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[7]";
			WebElement tableCellText = driver
					.findElement(By.xpath(editXpath));
			System.out.println("-------" + tableCellText.getText());
			if (tableCellText.getText().equals("已撤销")) {
				return true;
			}

			return false;
		} catch (Exception e) {
			e.printStackTrace();
			takeScreenShot();
			Base.closeTab(driver);
			return false;
			// TODO: handle exception
		}
	}

}
