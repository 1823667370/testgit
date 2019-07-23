package com.lz.libarary.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.lz.libarary.utils.Base;

/**
 * 
 * @version 1.00.00
 * @description: 社区资源管理平台-物业中心-停车费管理页面类
 * @author: songer.xing
 * @date: 2016-07-11
 * @history:
 */
public class ParkingFeeMangement extends Page {

	@FindBy(linkText = "停车费管理")
	private static WebElement parkingFeeLink;
	// 停车费管理>添加停车费按钮
	@FindBy(className = "add")
	private static WebElement addParkingFeeBtn;
	// 停车费管理>添加停车费>所属小区放大镜
	@FindBy(id = "parkingFeeListCommunityDialog")
	private static List<WebElement> parkingFeeCommunityDialog;
	// 停车费管理>添加停车费>所属房间 分区
	@FindBy(name = "parkingFee.partition_name")
	private static List<WebElement> parkingFeePartition;
	// 停车费管理>添加停车费>所属房间 分区下拉
	@FindBy(xpath = "//*[@id='suggest']/ul/li[text()='xss一区']")
	private static WebElement selectPartition;
	// 停车费管理>添加停车费>所属房间 楼宇
	@FindBy(name = "parkingFee.tenement_name")
	private static List<WebElement> parkingFeeTenement;
	// 停车费管理>添加停车费>所属房间 楼宇下拉
	@FindBy(xpath = "//*[@id='suggest']/ul/li[text()='xss楼1']")
	private static WebElement selectTenement;
	// 停车费管理>添加停车费>所属房间 单元
	@FindBy(name = "parkingFee.unit_name")
	private static List<WebElement> parkingFeeUnit;
	// 停车费管理>添加停车费>所属房间 单元下拉
	@FindBy(xpath = "//*[@id='suggest']/ul/li[text()='1单元']")
	private static WebElement selectUnit;
	// 停车费管理>添加停车费>所属房间 房间
	@FindBy(name = "parkingFee.room_name")
	private static List<WebElement> parkingFeeRoom;
	// 停车费管理>添加停车费>所属房间 房间下拉
	@FindBy(xpath = "//*[@id='suggest']/ul/li[text()='0101']")
	private static WebElement selectRoom;
	// 停车费管理>添加停车费>停车费
	@FindBy(name = "parkingFee.fee")
	private static WebElement parkingFee;
	// 停车费管理>添加停车费>备注
	@FindBy(name = "parkingFee.remark")
	private static WebElement remark;
	// 停车费管理>添加停车费>保存
	@FindBy(className = "buttonContent")
	private static List<WebElement> saveBtn;
	static Logger log = LogManager.getLogger(ParkingFeeMangement.class.getCanonicalName());

	public static void openParkingFeeLink() {

		parkingFeeLink.click();

	}

	public static boolean addParkingFee(WebDriver driver,
			String propertyFeeValue, String communityNameValue,String propertyFeeMonth)
			throws InterruptedException {
		// 等待添加小区按钮出现
		boolean isSunccess = true;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			new WebDriverWait(driver, 15).until(ExpectedConditions
					.presenceOfElementLocated(By.className("add")));
			addParkingFeeBtn.click();
			Thread.sleep(3000);
			parkingFeeCommunityDialog.get(1).click();
			Thread.sleep(2000);
			if (Base.selectCommunity(driver, communityNameValue, 5)) {
				// 关闭选择小区对话框
				Actions actions = new Actions(driver);
				actions.moveToElement(parkingFeePartition.get(2)).click()
						.perform();
				Thread.sleep(2000);
				selectPartition.click();
				Thread.sleep(1000);
				actions.moveToElement(parkingFeeTenement.get(2)).click()
						.perform();
				Thread.sleep(2000);
				selectTenement.click();
				Thread.sleep(1000);
				actions.moveToElement(parkingFeeUnit.get(2)).click().perform();
				Thread.sleep(2000);
				selectUnit.click();
				Thread.sleep(1000);
				actions.moveToElement(parkingFeeRoom.get(2)).click().perform();
				Thread.sleep(2000);
				selectRoom.click();
				Thread.sleep(1000);
				js.executeScript("document.getElementsByName('parkingFee.month')[2].value='"+propertyFeeMonth+"'");
				parkingFee.sendKeys(propertyFeeValue);
				saveBtn.get(0).click();
				Thread.sleep(2000);
			} else {
				takeScreenShot();
				if (saveBtn.get(1) != null) {
					log.info("新增停车费失败，将关闭对话框.....");
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
				log.info("新增停车费失败，将关闭对话框.....");
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

	public static boolean editParkingFee(WebDriver driver, int row,
			int column, String propertyFeeNote, String propertyFeeEditValue)
			throws InterruptedException {
		// 等待添加小区按钮出现
		Thread.sleep(2000);
		// 找到指定值行的修改link
		try {
			String editXpath2 = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[10]";
			WebElement tableCellText2 = driver
					.findElement(By.xpath(editXpath2));
			if (tableCellText2.getText().equals("待支付")) {
				System.out.println("-------" + tableCellText2.getText());
				String xpath = "//*[@class='gridTbody']/table/tbody/tr[" + row
						+ "]/td[" + column + "]";
				WebElement tableCell = driver.findElement(By.xpath(xpath));
				WebElement deleteTableCell = tableCell.findElement(By
						.linkText("编辑"));
				deleteTableCell.click();
				Thread.sleep(2000);
				parkingFee.clear();
				parkingFee.sendKeys(propertyFeeEditValue);
				remark.sendKeys(propertyFeeNote);
				saveBtn.get(0).click();
				new WebDriverWait(driver, 20)
						.until(ExpectedConditions.presenceOfElementLocated(By
								.cssSelector("div.msg.msgMsg")));
				Thread.sleep(1000);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("alertMsg.close()");
				Thread.sleep(2000);
				String editXpath1 = "//*[@class='gridTbody']/table/tbody/tr[" + row
						+ "]/td[9]";
				WebElement tableCellText1 = driver
						.findElement(By.xpath(editXpath1));
				System.out.println("-------" + tableCellText1.getText());
				String editXpath3 = "//*[@class='gridTbody']/table/tbody/tr[" + row
						+ "]/td[10]";
				WebElement tableCellText3 = driver
						.findElement(By.xpath(editXpath3));
				System.out.println("-------" + tableCellText3.getText());
				System.out.println("-------" + tableCellText1.getText());
				if (tableCellText3.getText().equals("待支付")
						&& tableCellText1.getText().equals(propertyFeeEditValue)) {
					return true;
				}			
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			takeScreenShot();
			saveBtn.get(1).click();
			Base.closeTab(driver);
			return false;
			// TODO: handle exception
		}
	}

}
