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
import org.testng.Reporter;

import repackage.Repackage;

import com.lz.libarary.utils.Base;

/**
 * 
 * @version 1.00.00
 * @description: 社区资源管理平台-物业中心-住户管理处页面类
 * @author: songer.xing
 * @date: 2016-6-22
 * @history:
 */
public class ResidentManagement extends Page {

	@FindBy(linkText = "住户管理")
	private static WebElement residentLink;
	// 住户管理>添加住户按钮
	@FindBy(className = "add")
	private static WebElement addResidentBtn;
	// 住户管理>添加住户>小区名称放大镜
	@FindBy(name = "resident.community_id")
	private static List<WebElement> selectCommunity;
	// 住户管理>添加住户>加载房间按钮
	@FindBy(className = "loadCommunityRoomTree")
	private static WebElement loadCommunityRoomBtn;
	// 住户管理>添加住户>选择添加房间区
	@FindBy(id = "tree_communityRoomTreeList")
	private static WebElement roomTreeList;
	// 住户管理>添加住户>第四人文件夹 单元
	@FindBy(id = "tree_communityRoomTreeList_4_switch")
	private static WebElement tree4;
	// 住户管理>添加住户>选择房间radio
	@FindBy(id = "tree_communityRoomTreeList_5_check")
	private static WebElement checkRoom;
	// 住户管理>添加住户>住户身份
	@FindBy(name = "resident.resident_type")
	private static List<WebElement> residentType;
	// 住户管理>添加住户>手机号码
	@FindBy(name = "resident.mobile_phone")
	private static List<WebElement> phone;
	// 住户管理>添加住户>姓名
	@FindBy(name = "resident.app_user_name")
	private static WebElement appUserName;
	// 住户管理>添加住户>姓名
	@FindBy(name = "resident.birthday")
	private static WebElement userBirthday;
	// 住户管理>添加住户>保存
	@FindBy(className = "buttonContent")
	private static List<WebElement> saveBtn;
	// 住户管理>关键词
	@FindBy(className = "textInput")
	private static List<WebElement> keywords;
	// 住户管理>编辑住户>有效周期radio
	@FindBy(name = "resident.cycle_type")
	private static List<WebElement> cycleType;
	// 住户管理>编辑住户>有效时段radio
	@FindBy(name = "resident.time_type")
	private static List<WebElement> timeType;
	// 查询按钮
	@FindBy(css = "button[class='buttonSeacher'][type='submit']")
	private static WebElement seachBtn;
	static Logger log = LogManager.getLogger(PropertyNote.class.getCanonicalName());

	public static void openResidentLink() {

		residentLink.click();

	}

	public static void addResident(WebDriver driver, String communityNameValue,
			String residentPhoneValue, String appUserNameValue,
			String userBirthdayValue) throws InterruptedException {
		// 等待添加小区按钮出现
		boolean isSunccess = true;
		try {
			Thread.sleep(2000);
			new WebDriverWait(driver, 15).until(ExpectedConditions
					.presenceOfElementLocated(By.className("add")));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			addResidentBtn.click();
			Thread.sleep(2000);
			//选择小区
			Select community = new Select(selectCommunity.get(2));
			community.selectByVisibleText(communityNameValue);		
			Thread.sleep(1000);
			loadCommunityRoomBtn.click();
			Thread.sleep(4000);
			new WebDriverWait(driver, 15).until(ExpectedConditions
					.presenceOfElementLocated(By
							.id("tree_communityRoomTreeList_3_check")));			
			if (tree4.getAttribute("class").equals(
					"button level3 switch bottom_close")) {
				tree4.click();
			}
			Thread.sleep(2000);
			checkRoom.click();
			Select selectType = new Select(residentType.get(2));
			selectType.selectByValue("2");
			phone.get(2).sendKeys(residentPhoneValue);
			Thread.sleep(2000);
			new WebDriverWait(driver, 20)
					.until(ExpectedConditions.presenceOfElementLocated(By
							.name("resident.birthday")));
			Thread.sleep(4000);
			js.executeScript("arguments[0].scrollIntoView()", appUserName);
			System.out.println("---" + appUserName.getText());
			if (Base.isElementExsit(driver, By
					.name("resident.birthday"))) {
				if (appUserName.getText().equals("")
						|| appUserName.getText().equals(null)||userBirthday.getText().equals("")||appUserName.getText().equals(null)) {				
					appUserName.sendKeys(appUserNameValue);
					userBirthday.sendKeys(userBirthdayValue);
					js.executeScript("document.getElementsByName('resident.birthday')[0].value='2016-06-30'");
				}
				saveBtn.get(0).click();
				Thread.sleep(2000);
			}else{
				log.error("添加住户时，姓名不存在");
				Reporter.log("添加住户时，姓名不存在");
				takeScreenShot();
				if (saveBtn.get(1) != null) {
					log.info("新增住户失败，将关闭对话框.....");
					System.out.println("--------" + saveBtn.get(1));
					saveBtn.get(1).click();
					Base.closeTab(driver);
				}
				isSunccess = false;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			takeScreenShot();
			if (saveBtn.get(1) != null) {
				log.info("新增住户失败，将关闭对话框.....");
				System.out.println("--------" + saveBtn.get(1));
				saveBtn.get(1).click();
				Base.closeTab(driver);
			}
			isSunccess = false;
		}
		if (isSunccess) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			new WebDriverWait(driver, 20).until(ExpectedConditions
					.presenceOfElementLocated(By.id("alertMsgBox")));
			Thread.sleep(1000);
			js.executeScript("document.getElementsByClassName('toolBar')[1].getElementsByClassName('button')[1].click()");
		}

	}

	public static boolean editResident(WebDriver driver,
			String residentPhoneValue, String cycleTypeValue,
			String timeTypeValue, int row) throws InterruptedException {
		// 等待添加小区按钮出现
		boolean isSunccess = true;
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			// 找到指定值行的修改link
			String xpath = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[15]";
			new WebDriverWait(driver, 15).until(ExpectedConditions
					.presenceOfElementLocated(By.xpath(xpath)));
			WebElement tableCell = driver.findElement(By.xpath(xpath));
			WebElement editTableCell = tableCell.findElement(By
					.className("btnEdit"));
			editTableCell.click();
			Thread.sleep(2000);
			if (cycleType.get(0).isSelected()
					&& !(cycleType.get(1).isSelected())) {
				cycleType.get(1).click();
				Thread.sleep(4000);
				js.executeScript("document.getElementById('resident_cycle_start').value='2016-07-01'");
				js.executeScript("document.getElementById('resident_cycle_end').value='2018-07-01'");
			} else {
				log.info("住户界面，有效周期radio选择错误!");
				Reporter.log("住户界面，有效周期radio选择错误!");
				return false;

			}

			if (timeType.get(0).isSelected() && !(timeType.get(1).isSelected())) {
				timeType.get(1).click();
				Thread.sleep(2000);
				js.executeScript("document.getElementById('resident_time_start').value='08:00:00'");
				js.executeScript("document.getElementById('resident_time_end').value='18:00:00'");
			} else {
				log.info("住户界面，有效时段radio选择错误!");
				Reporter.log("住户界面，有效时段radio选择错误!");
				return false;
			}
			saveBtn.get(0).click();
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			takeScreenShot();
			if (saveBtn.get(1) != null) {
				log.info("编辑住户信息失败，将关闭对话框.....");
				saveBtn.get(1).click();
				Base.closeTab(driver);
			}

			isSunccess = false;
			return false;
		}
		if (isSunccess) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			new WebDriverWait(driver, 20)
					.until(ExpectedConditions.presenceOfElementLocated(By
							.cssSelector("div.msg.msgMsg")));
			Thread.sleep(1000);
			js.executeScript("alertMsg.close()");
			Thread.sleep(3000);
			String editXpath1 = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[11]";
			String editXpath2 = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[12]";
			WebElement tableCellText1 = driver
					.findElement(By.xpath(editXpath1));
			WebElement tableCellText2 = driver
					.findElement(By.xpath(editXpath2));
			System.out.println("-------" + tableCellText1.getText());
			if (tableCellText1.getText().equals(cycleTypeValue)
					&& tableCellText2.getText().equals(timeTypeValue)) {
				return true;
			}
		}
		return false;
	}

	public static boolean residentCheck(WebDriver driver,String residentCity,
			String communityNameValue, String buildingPartitionValue,
			String roomValue, String appUserNameValue,
			String residentPhoneValue, int row) throws InterruptedException {
		// 等待添加小区按钮出现
		try {
			new WebDriverWait(driver, 15).until(ExpectedConditions
					.presenceOfElementLocated(By.className("add")));
			String addrXpath = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[1]";
			WebElement addrText = driver.findElement(By.xpath(addrXpath));
			String nameXpath = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[2]";
			WebElement nameText = driver.findElement(By.xpath(nameXpath));
			String partitionXpath = "//*[@class='gridTbody']/table/tbody/tr["
					+ row + "]/td[3]";
			WebElement partitionText = driver.findElement(By
					.xpath(partitionXpath));
			String roomXpath = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[6]";
			WebElement roomText = driver.findElement(By.xpath(roomXpath));
			String residentXpath = "//*[@class='gridTbody']/table/tbody/tr["
					+ row + "]/td[7]";
			WebElement residentText = driver.findElement(By
					.xpath(residentXpath));
			String phoneXpath = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[9]";
			WebElement phoneText = driver.findElement(By.xpath(phoneXpath));
			String typeXpath = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[10]";
			WebElement typeText = driver.findElement(By.xpath(typeXpath));
			String statusXpath = "//*[@class='gridTbody']/table/tbody/tr["
					+ row + "]/td[13]";
			WebElement statusText = driver.findElement(By.xpath(statusXpath));
			log.info("所属地区：" + addrText.getText());
			log.info("小区名称：" + nameText.getText());
			log.info("分区：" + partitionText.getText());
			log.info("房间：" + roomText.getText());
			log.info("住户名：" + residentText.getText());
			log.info("手机号：" + phoneText.getText());
			log.info("住户身份：" + typeText.getText());
			log.info("钥匙状态：" + statusText.getText());
			if (addrText.getText().equals(residentCity)
					&& nameText.getText().equals(communityNameValue)
					&& partitionText.getText().equals(buildingPartitionValue)
					&& roomText.getText().equals(roomValue)
					&& residentText.getText().equals(appUserNameValue)
					&& phoneText.getText().equals(residentPhoneValue)
					&& typeText.getText().equals("住户")
					&& statusText.getText().equals("开")) {
				return true;
			}
			log.info("新增小区信息核对错误");
			takeScreenShot();
			Base.closeTab(driver);
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			takeScreenShot();
			Base.closeTab(driver);
			// TODO: handle exception
		}
		log.info("新增小区信息核对错误");
		takeScreenShot();
		Base.closeTab(driver);
		return false;
	}

	public static boolean deleteResident(WebDriver driver, int row)
			throws InterruptedException {
		Thread.sleep(2000);
		// 找到指定值行的修改link
		try {
			String xpath = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[15]";
			WebElement tableCell = driver.findElement(By.xpath(xpath));
			WebElement deleteTableCell = tableCell.findElement(By
					.className("btnDel"));
			deleteTableCell.click();
			Thread.sleep(2000);
			driver.findElement(By.className("button")).click();
			// new WebDriverWait(driver, 20)
			// .until(ExpectedConditions.presenceOfElementLocated(By
			// .cssSelector("div.msg.msgMsg")));
			// JavascriptExecutor js = (JavascriptExecutor) driver;
			// js.executeScript("alertMsg.close()");
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
	
	public static void findKeywords(WebDriver driver, String keywordsValue)
			throws InterruptedException {
		try {
			Thread.sleep(3000);
			phone.get(0).clear();
			phone.get(0).sendKeys(keywordsValue);
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
