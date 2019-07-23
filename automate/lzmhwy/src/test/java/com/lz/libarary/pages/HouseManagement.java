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
import org.testng.Assert;
import org.testng.Reporter;

import com.lz.libarary.utils.Base;

/**
 * 
 * @version 1.00.00
 * @description: 社区资源管理平台-物业中心-小区管理页面类
 * @author: songer.xing
 * @date: 2016-6-10
 * @history:
 */
public class HouseManagement extends Page {

	@FindBy(linkText = "小区管理")
	private static WebElement houseLink;
	// 小区管理>添加小区按钮
	@FindBy(className = "add")
	private static WebElement addHouseBtn;
	// 小区管理>添加小区 小区名称
	@FindBy(name = "community.name")
	private static WebElement communityName;
	// 小区管理>添加小区 所属物业查找
	@FindBy(id = "communityInputPropertyDialog")
	private static WebElement communityInputPropertyDialog;
	// 小区管理>添加小区 分公司查找
	@FindBy(className = "h_seachDialog")
	private static List<WebElement> h_seachDialog;
	// 小区管理>添加小区 所属地区 选择省
	@FindBy(className = "combox required")
	private static List<WebElement> communityProvince;
	// 小区管理>添加小区 所属地区 选择市
	@FindBy(id = "community_input_city_id")
	private static WebElement communityCity;
	// 小区管理>添加小区 所属地区 选择区
	@FindBy(id = "community_input_region_id")
	private static WebElement communityRegion;
	// 小区管理>添加小区 所属物业
	@FindBy(name = "community.property_name")
	private static WebElement communityProperty;
	// 小区管理>添加小区 小区地址
	@FindBy(name = "community.community_addr")
	private static WebElement communityAddr;
	// 小区管理>添加小区 经度
	@FindBy(name = "community.lon")
	private static WebElement communityLon;
	// 小区管理>添加小区 纬度
	@FindBy(name = "community.lat")
	private static WebElement communityLat;
	// 小区管理>添加小区 小区类型
	@FindBy(className = "community.community_type")
	private static List<WebElement> communityType;
	// 小区管理>添加小区 保存按钮
	@FindBy(id = "communityAddButton")
	private static WebElement communitySaveBtn;
	// 小区管理 关键词
	@FindBy(className = "textInput")
	private static List<WebElement> keywords;
	// 小区管理>添加小区 上传图片
	@FindBy(id = "communityPicFile")
	private static WebElement communityPicFile;
	// 小区管理>添加小区 上传图片
	@FindBy(name = "community.property_developers")
	private static WebElement communityDevelopers;
	@FindBy(className = "btnRedS")
	private static WebElement editSaveBtn;
	@FindBy(css = "button.close.btnBlueS")
	private static WebElement closeBtn;
	// 小区管理>楼宇管理 第一层文件夹 小区
	@FindBy(id = "tree_communityTreeList_1_span")
	private static WebElement treeList1;
	// 小区管理>楼宇管理 第一层文件夹 ，添加分区按钮
	@FindBy(id = "community_addBtn_tree_communityTreeList_1")
	private static WebElement addTreeList1;
	// 小区管理>楼宇管理 小区管理 > 添加分区
	@FindBy(name = "partition.name")
	private static WebElement partitionName;
	// 小区管理>楼宇管理 第二层文件夹 分区
	@FindBy(id = "tree_communityTreeList_2_span")
	private static WebElement treeList2;
	// 小区管理>楼宇管理 第二层文件夹 删除分区
	@FindBy(id = "tree_communityTreeList_2_remove")
	private static WebElement reTreeList2;
	// 小区管理>楼宇管理 小区管理 > 添加楼宇按钮
	@FindBy(id = "community_addBtn_tree_communityTreeList_2")
	private static WebElement addTreeList2;
	// 小区管理>楼宇管理 小区管理 > 添加楼宇
	@FindBy(name = "tenementList[0].name")
	private static WebElement building1;
	// 小区管理>楼宇管理 第三层文件夹 楼宇
	@FindBy(id = "tree_communityTreeList_3_span")
	private static WebElement treeList3;
	// 小区管理>楼宇管理 第三层文件夹 删除楼宇
	@FindBy(id = "tree_communityTreeList_3_remove")
	private static WebElement reTreeList3;
	// 小区管理>楼宇管理 小区管理 > 添加单元
	@FindBy(id = "community_addBtn_tree_communityTreeList_3")
	private static WebElement addTreeList3;
	// 小区管理>楼宇管理 小区管理 > 添加楼宇
	@FindBy(name = "unitList[0].name")
	private static WebElement unitList1;
	// 小区管理>楼宇管理 第四层文件夹,单元
	@FindBy(id = "tree_communityTreeList_4_span")
	private static WebElement treeList4;
	// 小区管理>楼宇管理 第四层文件夹,删除单元
	@FindBy(id = "tree_communityTreeList_4_remove")
	private static WebElement reTreeList4;
	// 小区管理>楼宇管理 小区管理 > 添加房间
	@FindBy(id = "community_addBtn_tree_communityTreeList_4")
	private static WebElement addTreeList4;
	// 小区管理>楼宇管理 第五层文件夹，房间
	@FindBy(id = "tree_communityTreeList_5_span")
	private static WebElement treeList5;
	// 小区管理>楼宇管理 第五层文件夹，删除房间
	@FindBy(id = "tree_communityTreeList_5_remove")
	private static WebElement reTreeList5;
	// 小区管理>楼宇管理 小区管理 > 添加住户
	@FindBy(id = "community_addBtn_tree_communityTreeList_5")
	private static WebElement addTreeList5;
	// 小区管理>楼宇管理 小区管理 > 添加房间,单个新增radio
	@FindBy(name = "add_type")
	private static List<WebElement> addType;
	// 小区管理>楼宇管理 小区管理 > 添加房间,单个房间
	@FindBy(name = "room.name")
	private static WebElement roomName;
	@FindBy(name = "room.phone")
	private static WebElement roomPhone;
	// 小区管理>小区住户详情 第四层文件夹 单元折叠符
	@FindBy(id = "tree_communityTreeList_4_switch")
	private static WebElement tree4;
	// 小区管理>小区住户详情 第五层文件夹 单元折叠符
	@FindBy(id = "tree_communityTreeList_5_switch")
	private static WebElement tree5;
	// 小区管理>小区住户详情 第6层文件夹 住户
	@FindBy(id = "tree_communityTreeList_6_span")
	private static WebElement treeList6;
	// 小区管理>小区住户详情 第6层文件夹 住户删除
	@FindBy(id = "tree_communityTreeList_6_remove")
	private static WebElement reTreeList6;
	// 小区管理>小区住户详情 住户手机
	@FindBy(name = "resident.mobile_phone")
	private static WebElement residentPhone;
	// 小区管理>楼宇管理 第一层文件夹 ，物业分区
	@FindBy(id = "tree_communityTreeList_5_span")
	private static WebElement wyPartition;
	// 小区管理>楼宇管理，物业分区旁边加号（添加楼宇按钮）
	@FindBy(id = "community_addBtn_tree_communityTreeList_5")
	private static WebElement addWyBuilding;
	// 小区管理>楼宇管理，物业楼宇
	@FindBy(id = "tree_communityTreeList_6_span")
	private static WebElement wyBuilding;
	// 小区管理>楼宇管理，物业楼宇旁边加号（添加单元按钮）
	@FindBy(id = "community_addBtn_tree_communityTreeList_6")
	private static WebElement addWyUnit;
	// 小区管理>楼宇管理，物业单元
	@FindBy(id = "tree_communityTreeList_7_span")
	private static WebElement wyUnit;
	// 小区管理>楼宇管理，物业单元旁边加号（添加房间按钮）
	@FindBy(id = "community_addBtn_tree_communityTreeList_7")
	private static WebElement addWyRoom;
	// 小区管理>楼宇管理，批量添加房间 起始楼层
	@FindBy(name = "floor_start")
	private static WebElement floorStart;
	// 小区管理>楼宇管理，批量添加房间 尾数楼层
	@FindBy(name = "floor_end")
	private static WebElement floorEnd;
	// 小区管理>楼宇管理，批量添加房间 起始房号
	@FindBy(name = "room_start")
	private static WebElement roomStart;
	// 小区管理>楼宇管理，批量添加房间 每层几间
	@FindBy(name = "room_num")
	private static WebElement roomNum;
	// 小区管理>楼宇管理，物业房间
	@FindBy(id = "tree_communityTreeList_8_span")
	private static WebElement wyRoom;
	// 小区管理>楼宇管理 删除房间按钮
	@FindBy(id = "tree_communityTreeList_8_remove")
	private static WebElement deWyRoom;
	// 小区管理>楼宇管理 删除单元按钮
	@FindBy(id = "tree_communityTreeList_7_remove")
	private static WebElement deWyUnit;
	// 小区管理>楼宇管理 删除楼宇按钮
	@FindBy(id = "tree_communityTreeList_6_remove")
	private static WebElement deWyBuilding;
	// 小区管理>楼宇管理 删除分区按钮
	@FindBy(id = "tree_communityTreeList_5_remove")
	private static WebElement deWyPartition;
	@FindBy(name = "resident.app_user_name")
	private static WebElement appUserName;
	@FindBy(name = "resident.birthday")
	private static WebElement residentBirthday;
	@FindBy(name = "community.bluetooth_key")
	private static WebElement blueKey;	
	@FindBy(name = "community.invite_code_max_count")
	private static WebElement inviteCount;

	static Logger log = LogManager.getLogger(HouseManagement.class.getCanonicalName());
	private static String currentPath = System.getProperty("user.dir");

	public static void openCommunityLink() {

		houseLink.click();

	}

	public static boolean addCommunity(WebDriver driver,
			String communityNameValue, String province,String city,String region,String communityAddrValue,
			String communityLonValue, String communityLatValue, String blueKeyValue,
			String inviteCountValue,
			String propertyNameValue,String brandNameValue) throws InterruptedException {
		// 等待添加小区按钮出现
		boolean isSunccess = true;
		try {
			addHouseBtn.click();
			Thread.sleep(2000);
			//小区名称
			communityName.sendKeys(communityNameValue);			
			// 所属地区
			Select selectProvince = new Select(driver.findElements(
					By.cssSelector("select.combox.required")).get(0));
			selectProvince.selectByValue(province);
			Select selectCity = new Select(communityCity);
			selectCity.selectByValue(city);
			Select selectRegion = new Select(communityRegion);
			selectRegion.selectByValue(region);
			//小区地址
			communityAddr.sendKeys(communityAddrValue);
			//经纬度
			((JavascriptExecutor)driver).executeScript("arguments[0].value='"
							+ communityLonValue + "'",communityLon);
			((JavascriptExecutor)driver).executeScript("arguments[0].value='"
					+ communityLatValue + "'",communityLat);
			//小区类型
			Select selectCommunityType = new Select(driver.findElements(
					By.name("community.community_type")).get(2));
			selectCommunityType.selectByVisibleText("真实小区");
			Thread.sleep(1000);
			//蓝牙密钥
			blueKey.sendKeys(blueKeyValue);
			//邀请码次数
			inviteCount.sendKeys(inviteCountValue);
			Thread.sleep(1000);
			communityInputPropertyDialog.click();
			Thread.sleep(2000);
			if (!(Base.selectProperty(driver, propertyNameValue, 7))) {
				closeBtn.click();
				isSunccess = false;
				return false;
			}
			Thread.sleep(2000);
			
			//分公司
			h_seachDialog.get(3).click();
			Thread.sleep(2000);
			if (!(Base.selectBranch(driver, brandNameValue, 7))) {
				log.info("选择分公司失败");
				closeBtn.click();
				isSunccess = false;
				return false;
			}
			Thread.sleep(2000);
			communitySaveBtn.click();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			takeScreenShot();
			closeBtn.click();
			Base.closeTab(driver);
			isSunccess = false;
			return false;
		}

		if (isSunccess) {
			new WebDriverWait(driver, 20)
					.until(ExpectedConditions.presenceOfElementLocated(By
							.cssSelector("div.msg.msgMsg")));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			Thread.sleep(1000);
			js.executeScript("alertMsg.close()");
		}
		return true;
	}

	public static boolean editCommunity(WebDriver driver,
			String editcommunityDevelopersValue, int row)
			throws InterruptedException {
		// 等待添加小区按钮出现
		boolean isSunccess = true;
		try {
			new WebDriverWait(driver, 15).until(ExpectedConditions
					.presenceOfElementLocated(By.className("add")));
			// 找到指定值行的修改link
			String xpath = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[14]";
			WebElement tableCell = driver.findElement(By.xpath(xpath));
			WebElement editTableCell = tableCell.findElement(By.linkText("编辑"));
			editTableCell.click();
			Thread.sleep(2000);
			communityDevelopers.sendKeys(editcommunityDevelopersValue);
			//jenkins_linux,本地无法远程传图片
			communityPicFile.sendKeys(currentPath + "/"+"xssCommunity.png");
			new WebDriverWait(driver, 15)
					.until(ExpectedConditions.presenceOfElementLocated(By
							.cssSelector("input.communityAddDelFile.closeBtn")));
			editSaveBtn.click();
			
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			takeScreenShot();
			isSunccess = false;
			closeBtn.click();
			Base.closeTab(driver);
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
			String editXpath = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[7]";
			WebElement tableCellText = driver.findElement(By.xpath(editXpath));
			System.out.println("-------" + tableCellText.getText());
			if (tableCellText.getText().equals(editcommunityDevelopersValue)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean houseCheck(WebDriver driver,
			String propertyNameValue, String communityAddrCheck,String brandNameValue,int row) throws InterruptedException {
		// 等待添加小区按钮出现
		try {
			String addrXpath = "//*[@class='gridTbody']/table/tbody/tr["
					+ row + "]/td[2]";
			WebElement addrText = driver.findElement(By.xpath(addrXpath));
			String typeXpath = "//*[@class='gridTbody']/table/tbody/tr["
					+ row + "]/td[3]";
			WebElement typeText = driver.findElement(By.xpath(typeXpath));
			String propertyXpath = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[5]";
			WebElement propertyText = driver.findElement(By.xpath(propertyXpath));
			String brandXpath = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[6]";
			WebElement brandText = driver.findElement(By.xpath(brandXpath));
			log.info("--------" + addrText.getText());
			log.info("--------" + typeText.getText());
			log.info("--------" + propertyText.getText());
			log.info("--------" + brandText.getText());	
			
			if (addrText.getText().equals(communityAddrCheck)
					&& typeText.getText().equals("真实小区")
					&& propertyText.getText().equals(propertyNameValue)&&brandText.getText().equals(brandNameValue)) {
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
	public static boolean wyHouseCheck(WebDriver driver,
			String propertyNameValue,String communityAddrCheck, int row) throws InterruptedException {
		// 等待添加小区按钮出现
		try {
			String addrXpath = "//*[@class='gridTbody']/table/tbody/tr["
					+ row + "]/td[1]";
			WebElement addrText = driver.findElement(By.xpath(addrXpath));
			String propertyXpath = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[4]";
			WebElement propertyText = driver.findElement(By.xpath(propertyXpath));
			log.info("--------" + addrText.getText());
			log.info("--------" + propertyText.getText());	
			
			if (addrText.getText().equals(communityAddrCheck)
					&& propertyText.getText().equals(propertyNameValue)) {
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
	public static boolean addBuilding(WebDriver driver, int row,
			String partitionValue, String building1Value,
			String unitList1Value, String roomValue, String phoneValue)
			throws InterruptedException {
		// 等待添加小区按钮出现
		try {
			new WebDriverWait(driver, 15).until(ExpectedConditions
					.presenceOfElementLocated(By.className("add")));
			// 找到指定值行的修改link
			String xpath = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[14]";
			WebElement tableCell = driver.findElement(By.xpath(xpath));
			WebElement builingLink = tableCell.findElement(By.linkText("楼宇管理"));
			builingLink.click();
			 Thread.sleep(2000);
			// 增加分区
			treeList1.click();
			addTreeList1.click();
			partitionName.sendKeys(partitionValue);
			editSaveBtn.click();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			new WebDriverWait(driver, 20)
					.until(ExpectedConditions.presenceOfElementLocated(By
							.cssSelector("div.msg.msgMsg")));
			Thread.sleep(1000);
			js.executeScript("alertMsg.close()");
			Thread.sleep(1000);
			Assert.assertEquals(treeList2.getText(), partitionValue);
			// 增加楼宇
			treeList2.click();
			addTreeList2.click();
			building1.sendKeys(building1Value);
			editSaveBtn.click();
			new WebDriverWait(driver, 20)
					.until(ExpectedConditions.presenceOfElementLocated(By
							.cssSelector("div.msg.msgMsg")));
			Thread.sleep(1000);
			js.executeScript("alertMsg.close()");
			Thread.sleep(1000);
			// 增加单元
			treeList3.click();
			addTreeList3.click();
			unitList1.sendKeys(unitList1Value);
			editSaveBtn.click();
			Thread.sleep(2000);
			new WebDriverWait(driver, 20)
					.until(ExpectedConditions.presenceOfElementLocated(By
							.cssSelector("div.msg.msgMsg")));
			Thread.sleep(1000);
			js.executeScript("alertMsg.close()");
			Thread.sleep(1000);
			// 增加房间
			treeList4.click();
			addTreeList4.click();
			addType.get(1).click();
			roomName.sendKeys(roomValue);
			//roomPhone.sendKeys(phoneValue);
			editSaveBtn.click();
			Thread.sleep(2000);
			new WebDriverWait(driver, 20)
					.until(ExpectedConditions.presenceOfElementLocated(By
							.cssSelector("div.msg.msgMsg")));
			Thread.sleep(1000);
			js.executeScript("alertMsg.close()");
			Thread.sleep(2000);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			takeScreenShot();
			Base.closeTab2(driver);
			Base.closeTab(driver);
			return false;
			// TODO: handle exception
		}

	}
	public static boolean addWyBuilding(WebDriver driver, int row,
			String wyPartitionValue, String building1Value,
			String unitList1Value, String floorStartValue, String floorEndValue,
			String roomStartValue, String roomNumValue)
			throws InterruptedException {
		// 等待添加小区按钮出现
		try {
			// 找到指定值行的修改link
			String xpath = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[5]";
			WebElement tableCell = driver.findElement(By.xpath(xpath));
			WebElement builingLink = tableCell.findElement(By.linkText("楼宇管理"));
			builingLink.click();
			 Thread.sleep(2000);
			// 增加分区
			treeList1.click();
			addTreeList1.click();
			partitionName.sendKeys(wyPartitionValue);
			editSaveBtn.click();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			new WebDriverWait(driver, 20)
					.until(ExpectedConditions.presenceOfElementLocated(By
							.cssSelector("div.msg.msgMsg")));
			Thread.sleep(1000);
			js.executeScript("alertMsg.close()");
			Thread.sleep(1000);
			Assert.assertEquals(wyPartition.getText(), wyPartitionValue);
			// 增加楼宇
			wyPartition.click();
			addWyBuilding.click();
			building1.sendKeys(building1Value);
			editSaveBtn.click();
			new WebDriverWait(driver, 20)
					.until(ExpectedConditions.presenceOfElementLocated(By
							.cssSelector("div.msg.msgMsg")));
			Thread.sleep(1000);
			js.executeScript("alertMsg.close()");
			Thread.sleep(1000);
			// 增加单元
			wyBuilding.click();
			addWyUnit.click();
			unitList1.sendKeys(unitList1Value);
			editSaveBtn.click();
			Thread.sleep(2000);
			new WebDriverWait(driver, 20)
					.until(ExpectedConditions.presenceOfElementLocated(By
							.cssSelector("div.msg.msgMsg")));
			Thread.sleep(1000);
			js.executeScript("alertMsg.close()");
			Thread.sleep(1000);
			// 增加房间
			wyUnit.click();
			addWyRoom.click();
			//addType.get(1).click();
			floorStart.sendKeys(floorStartValue);
			floorEnd.sendKeys(floorEndValue);
			roomStart.sendKeys(roomStartValue);
			roomNum.sendKeys(roomNumValue);
			//roomPhone.sendKeys(phoneValue);
			editSaveBtn.click();
			Thread.sleep(2000);
			new WebDriverWait(driver, 20)
					.until(ExpectedConditions.presenceOfElementLocated(By
							.cssSelector("div.msg.msgMsg")));
			Thread.sleep(1000);
			js.executeScript("alertMsg.close()");
			Thread.sleep(2000);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			takeScreenShot();
			Base.closeTab2(driver);
			Base.closeTab(driver);
			return false;
			// TODO: handle exception
		}

	}
	
	public static boolean deletWyBuilding(WebDriver driver, int row)
			throws InterruptedException {
		// 等待添加小区按钮出现
		try {
			wyRoom.click();
			deWyRoom.click();
			alterBox(driver);
			wyUnit.click();
			deWyUnit.click();
			alterBox(driver);
			wyBuilding.click();
			deWyBuilding.click();
			alterBox(driver);
			wyPartition.click();
			deWyPartition.click();
			alterBox(driver);
			Thread.sleep(1000);
			Base.closeTab2(driver);
			Thread.sleep(2000);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			takeScreenShot();
			Base.closeTab2(driver);
			Base.closeTab(driver);
			return false;
			// TODO: handle exception
		}

	}
	public static boolean deWyPeople(WebDriver driver, int row)
			throws InterruptedException {
		// 等待添加小区按钮出现
		try {
			// 找到指定值行的修改link
			Thread.sleep(2000);
			String xpath = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[5]";
			WebElement tableCell = driver.findElement(By.xpath(xpath));
			WebElement editTableCell = tableCell.findElement(By
					.linkText("小区住户详情"));
			editTableCell.click();
			Thread.sleep(2000);
			System.out.println("------" + tree4.getAttribute("class"));
			if (tree4.getAttribute("class").equals(
					"button level3 switch bottom_close")) {
				Thread.sleep(2000);
				tree4.click();
				System.out.println("------");
			}
			Thread.sleep(2000);
			if (tree5.getAttribute("class").equals(
					"button level4 switch bottom_close")) {
				Thread.sleep(1000);
				tree5.click();
				System.out.println("------");
			}
			new WebDriverWait(driver, 20)
			.until(ExpectedConditions.presenceOfElementLocated(By
					.id("tree_communityTreeList_6_span")));
			if (Base.isElementExsit(driver,
					By.id("tree_communityTreeList_6_span"))) {
				treeList6.click();
				reTreeList6.click();
				Thread.sleep(1000);
				driver.findElement(
						By.xpath("//*[@class='confirm']/div[2]/ul/li[1]/a"))
						.click();
				Thread.sleep(1000);
				driver.findElement(
						By.xpath("//*[@class='correct']/div[2]/ul/li/a"))
						.click();
				Thread.sleep(3000);
				//Base.closeTab2(driver);
				return true;
			} else {
				log.info("不存在住户!");
				Reporter.log("不存在住户!");
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			takeScreenShot();
			Base.closeTab2(driver);
			Base.closeTab(driver);
			// TODO: handle exception
		}

		return false;

	}
	public static boolean addPeople(WebDriver driver, int row,
			String phoneValue) throws InterruptedException {
		// 等待添加小区按钮出现
		try {
			new WebDriverWait(driver, 15).until(ExpectedConditions
					.presenceOfElementLocated(By.className("add")));
			// 找到指定值行的修改link
			String xpath = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[14]";
			WebElement tableCell = driver.findElement(By.xpath(xpath));
			WebElement builingLink = tableCell.findElement(By
					.linkText("小区住户详情"));
			builingLink.click();
			Thread.sleep(2000); 
			// 打开第四层单元折叠文件夹
			new WebDriverWait(driver, 20).until(ExpectedConditions
					.presenceOfElementLocated(By
							.id("tree_communityTreeList_4_switch")));
			Thread.sleep(2000);
			System.out.println("------" + tree4.getAttribute("class"));
			if (tree4.getAttribute("class").equals(
					"button level3 switch bottom_close")) {
				Thread.sleep(1000);
				tree4.click();
				System.out.println("------");
			}
			Thread.sleep(1000);
			treeList5.click();
			addTreeList5.click();
			Thread.sleep(2000);
			residentPhone.sendKeys(phoneValue);//
			Thread.sleep(3000);
			System.out.println("---" + appUserName.getAttribute("readonly"));
			System.out.println("---" + appUserName.isDisplayed());
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView()", editSaveBtn);
			new WebDriverWait(driver, 20)
					.until(ExpectedConditions.presenceOfElementLocated(By
							.name("resident.app_user_name")));
			Thread.sleep(2000);
			if (appUserName.getAttribute("readonly") == null
					|| appUserName.getAttribute("readonly") == "") {
				appUserName.sendKeys("邢松松");
				String dateJs = "document.getElementsByClassName('required Wdate readonly textInput').removeAttribute('readOnly');document.getElementById('dateTimeId').setAttribute('value','2012-10-25');";
				((JavascriptExecutor) driver).executeScript(dateJs);
				residentBirthday.sendKeys("2016-06-22");
				System.out.println("------");
			}
			editSaveBtn.click();
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
			Base.closeTab2(driver);
			Base.closeTab(driver);
			// TODO: handle exception
		}
		return false;

	}
	
	public static boolean addWyPeople(WebDriver driver, int row,
			String phoneValue) throws InterruptedException {
		// 等待添加小区按钮出现
		try {
			Thread.sleep(1000);
			treeList5.click();
			addTreeList5.click();
			Thread.sleep(2000);
			residentPhone.sendKeys(phoneValue);//
			Thread.sleep(3000);
			System.out.println("---" + appUserName.getAttribute("readonly"));
			System.out.println("---" + appUserName.isDisplayed());
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView()", editSaveBtn);
			new WebDriverWait(driver, 20)
					.until(ExpectedConditions.presenceOfElementLocated(By
							.name("resident.app_user_name")));
			Thread.sleep(2000);
			if (appUserName.getAttribute("readonly") == null
					|| appUserName.getAttribute("readonly") == "") {
				appUserName.sendKeys("邢松松");
				String dateJs = "document.getElementsByClassName('required Wdate readonly textInput').removeAttribute('readOnly');document.getElementById('dateTimeId').setAttribute('value','2012-10-25');";
				((JavascriptExecutor) driver).executeScript(dateJs);
				residentBirthday.sendKeys("2016-06-22");
				System.out.println("------");
			}
			editSaveBtn.click();
			Thread.sleep(2000);
			new WebDriverWait(driver, 20)
					.until(ExpectedConditions.presenceOfElementLocated(By
							.cssSelector("div.msg.msgMsg")));
			Thread.sleep(2000);
			js.executeScript("alertMsg.close()");
			Thread.sleep(2000);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			takeScreenShot();
			Base.closeTab2(driver);
			Base.closeTab(driver);
			// TODO: handle exception
		}
		return false;

	}

	public static boolean deletePeople(WebDriver driver, int row)
			throws InterruptedException {
		// 等待添加小区按钮出现
		try {
			new WebDriverWait(driver, 15).until(ExpectedConditions
					.presenceOfElementLocated(By.className("add")));
			// 找到指定值行的修改link
			Thread.sleep(2000);
			String xpath = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[14]";
			WebElement tableCell = driver.findElement(By.xpath(xpath));
			WebElement editTableCell = tableCell.findElement(By
					.linkText("小区住户详情"));
			editTableCell.click();
			Thread.sleep(2000);
			System.out.println("------" + tree4.getAttribute("class"));
			if (tree4.getAttribute("class").equals(
					"button level3 switch bottom_close")) {
				Thread.sleep(2000);
				tree4.click();
				System.out.println("------");
			}
			Thread.sleep(2000);
			if (tree5.getAttribute("class").equals(
					"button level4 switch bottom_close")) {
				Thread.sleep(1000);
				tree5.click();
				System.out.println("------");
			}
					
			if (Base.isElementExsit(driver,
					By.id("tree_communityTreeList_6_span"))) {
				treeList6.click();
				reTreeList6.click();
				Thread.sleep(2000);
				new WebDriverWait(driver, 15).until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[@class='confirm']/div[2]/ul/li[1]/a")));
				driver.findElement(
						By.xpath("//*[@class='confirm']/div[2]/ul/li[1]/a"))
						.click();
				Thread.sleep(1000);
				new WebDriverWait(driver, 15).until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[@class='correct']/div[2]/ul/li/a")));
				driver.findElement(
						By.xpath("//*[@class='correct']/div[2]/ul/li/a"))
						.click();
				Thread.sleep(3000);
				Base.closeTab2(driver);
				return true;
			} else {
				log.info("不存在住户!");
				Reporter.log("不存在住户!");
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			takeScreenShot();
			Base.closeTab2(driver);
			Base.closeTab(driver);
			// TODO: handle exception
		}

		return false;

	}

	public static boolean deleteBuilding(WebDriver driver, int row)
			throws InterruptedException {
		// 等待添加小区按钮出现
		try {

			new WebDriverWait(driver, 15).until(ExpectedConditions
					.presenceOfElementLocated(By.className("add")));
			// 找到指定值行的修改link
			Thread.sleep(2000);
			String xpath = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[14]";
			WebElement tableCell = driver.findElement(By.xpath(xpath));
			WebElement editTableCell = tableCell.findElement(By
					.linkText("楼宇管理"));
			editTableCell.click();
			Thread.sleep(2000);
			new WebDriverWait(driver, 20).until(ExpectedConditions
					.presenceOfElementLocated(By
							.id("tree_communityTreeList_4_switch")));

			System.out.println("------" + tree4.getAttribute("class"));
			if (tree4.getAttribute("class").equals(
					"button level3 switch bottom_close")) {
				Thread.sleep(2000);
				tree4.click();
				System.out.println("------");
			}
			treeList5.click();
			reTreeList5.click();
			alterBox(driver);
			treeList4.click();
			reTreeList4.click();
			alterBox(driver);
			treeList3.click();
			reTreeList3.click();
			alterBox(driver);
			treeList2.click();
			reTreeList2.click();
			alterBox(driver);
			Thread.sleep(1000);
			Base.closeTab2(driver);
			Thread.sleep(2000);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			takeScreenShot();
			Base.closeTab2(driver);
			Base.closeTab(driver);
			// TODO: handle exception
		}
		return false;

	}

	public static void alterBox(WebDriver driver) throws InterruptedException {
		Thread.sleep(3000);
		new WebDriverWait(driver, 20).until(ExpectedConditions
				.presenceOfElementLocated(By
						.xpath("//*[@class='confirm']/div[2]/ul/li[1]/a")));
		driver.findElement(By.xpath("//*[@class='confirm']/div[2]/ul/li[1]/a"))
				.click();
		Thread.sleep(2000);
		new WebDriverWait(driver, 20).until(ExpectedConditions
				.presenceOfElementLocated(By
						.xpath("//*[@class='correct']/div[2]/ul/li/a")));
		driver.findElement(By.xpath("//*[@class='correct']/div[2]/ul/li/a"))
				.click();
	}
}
