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
 * @description: 社区资源管理平台-物业中心-私信页面类
 * @author: songer.xing
 * @date: 2016-07-09
 * @history:
 */
public class PropertyDM extends Page {

	@FindBy(linkText = "私信")
	private static WebElement propertyDMLink;
	// 私信>添加私信按钮
	@FindBy(className = "add")
	private static WebElement addPropertyDMBtn;
	// 私信>添加私信>标题
	@FindBy(name = "propertyPm.title")
	private static List<WebElement> DMTitle;
	// 私信>添加私信>内容
	@FindBy(name = "propertyPm.content")
	private static WebElement DMContent;
	// 私信>添加私信>选择发送对象 小区放大镜
	@FindBy(id = "pm_input_community_id")
	private static WebElement sendCommunity;
	// 私信>添加私信>选择小区范围下拉
	@FindBy(id = "send_type")
	private static WebElement selectSendType;
	// 私信>添加私信>选择房间按钮
	@FindBy(className = "h_buttonChange")
	private static WebElement chooseRoomBtn;
	// 私信>添加私信>选择房间
	@FindBy(id = "tree_communityRoomTreeList_5_check")
	private static WebElement selectRoom;
	// 私信>添加私信>选择房间 所选房间
	@FindBy(id = "room_name")
	private static WebElement roomName;
	// 私信>添加私信>所选房间内容
	@FindBy(id = "p_input_room_name")
	private static WebElement roomNameContent;
	// 私信>编辑私信>内容
	@FindBy(id = "update_noticeContent")
	private static WebElement editNoticeContent;
	// 私信>添加私信>保存
	@FindBy(className = "buttonContent")
	private static List<WebElement> saveBtn;
	// 私信>关键词
	@FindBy(className = "textInput")
	private static List<WebElement> keywords;
	static Logger log = LogManager.getLogger(PropertyDM.class.getCanonicalName());

	public static void openPropertyDMLink() {

		propertyDMLink.click();

	}

	public static boolean addPropertyDM(WebDriver driver,
			String noticeTitleValue, String communityNameValue)
			throws InterruptedException {
		// 等待添加小区按钮出现
		boolean isSunccess = true;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			new WebDriverWait(driver, 15).until(ExpectedConditions
					.presenceOfElementLocated(By.className("add")));
			addPropertyDMBtn.click();
			Thread.sleep(2000);
			DMTitle.get(2).sendKeys(noticeTitleValue);
			DMContent.sendKeys(noticeTitleValue);
			js.executeScript("document.getElementById('pm_start_time').value='2016-07-01'");
			js.executeScript("document.getElementById('pm_end_time').value='2018-07-01'");
			Select selectRegion = new Select(selectSendType);
			selectRegion.selectByValue("2");
			Thread.sleep(1000);
			Select community = new Select(sendCommunity);
			community.selectByVisibleText(communityNameValue);
			;
			Thread.sleep(1000);
			chooseRoomBtn.click();
			new WebDriverWait(driver, 15).until(ExpectedConditions
					.presenceOfElementLocated(By
							.id("tree_communityRoomTreeList_5_check")));
			Thread.sleep(1000);
			selectRoom.click();
			Thread.sleep(2000);
			System.out.println("-----" + roomName.getAttribute("value"));
			if (roomName.getAttribute("value").equals("")) {
				saveBtn.get(3).click();
				saveBtn.get(1).click();
				Base.closeTab(driver);
				isSunccess = false;
				return false;
			}
			saveBtn.get(2).click();
			Thread.sleep(1000);
			System.out.println("-----" + roomNameContent.getAttribute("value"));
			if (roomNameContent.getAttribute("value").equals("")) {
				isSunccess = false;
				saveBtn.get(1).click();
				Base.closeTab(driver);
				return false;
			}
			saveBtn.get(0).click();
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			takeScreenShot();
			if (saveBtn.get(1) != null) {
				log.info("新增私信失败，将关闭对话框.....");
				System.out.println("--------" + saveBtn.get(1));
				saveBtn.get(1).click();
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
			Thread.sleep(1000);
			js.executeScript("alertMsg.close()");
		}
		return true;
	}

	public static boolean cancelPropertyDM(WebDriver driver, int row, int column)
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
					+ "]/td[6]";
			WebElement tableCellText = driver.findElement(By.xpath(editXpath));
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
