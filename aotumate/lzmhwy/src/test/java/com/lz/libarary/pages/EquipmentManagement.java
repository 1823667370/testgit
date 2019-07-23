package com.lz.libarary.pages;

import java.util.ArrayList;
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
 * @description: 社区资源管理平台-设备管理-终端设备管理页面类
 * @author: songer.xing
 * @date: 2016-7-12
 * @history:
 */
public class EquipmentManagement extends Page {

	@FindBy(linkText = "终端设备管理")
	private static WebElement equipmentManagementLink;
	@FindBy(linkText = "设备状态查询")
	private static WebElement equipmentStatusLink;
	// 终端设备管理>添加设备按钮
	@FindBy(className = "add")
	private static WebElement addEquipmentBtn;
	// 终端设备管理>添加设备 设备名称
	@FindBy(name = "equipment.name")
	private static List<WebElement> equipmentName;
	// 终端设备管理>添加设备 设备串号
	@FindBy(name = "equipment.serial")
	private static List<WebElement> equipmentSerial;
	// 终端设备管理>添加设备 位置 选择省
	@FindBy(name = "equipment.area_id")
	private static List<WebElement> equipmentArea;
	// 终端设备管理>添加设备 位置 选择市
	@FindBy(id = "equipment_add_city_id")
	private static WebElement equipmentCity;
	// 终端设备管理>添加设备位置 选择区
	@FindBy(id = "equipment_add_district_id")
	private static WebElement equipmentDistrict;
	// 终端设备管理>添加设备 小区
	@FindBy(id = "equipment_add_community_id")
	private static WebElement equipmentCommunity;
	// 终端设备管理>添加设备 分区
	@FindBy(id = "equipment_add_partition_id")
	private static WebElement equipmentPartition;
	// 终端设备管理>添加设备 楼宇
	@FindBy(id = "equipment_add_tenement_id")
	private static WebElement equipmentTenement;
	// 终端设备管理>添加设备 单元
	@FindBy(id = "equipment_add_unit_id")
	private static WebElement equipmentUnit;
	// 终端设备管理>添加设备 安装位置
	@FindBy(id = "selectInstallPositionId")
	private static WebElement installPosition;
	// 终端设备管理>添加设备 楼宇
	@FindBy(name = "equipment.door_no")
	private static WebElement doorNo;
	// 终端设备管理>添加设备 经度
	@FindBy(name = "equipment.lon")
	private static WebElement equipmentLon;
	// 终端设备管理>添加设备 纬度
	@FindBy(name = "equipment.lat")
	private static WebElement equipmentLat;
	// 终端设备管理 保存
	@FindBy(className = "buttonContent")
	private static List<WebElement> saveBtn;
	// 终端设备管理 查询
	@FindBy(className = "buttonSeacher")
	private static List<WebElement> seacherBtn;
	// 终端设备管理>编辑设备 上传图片
	@FindBy(id = "equipmentPicFile")
	private static WebElement equipmentPicFile;
	// 终端设备管理>编辑设备 上传图片
	@FindBy(id = "equipmentAdd_SuccessFile")
	private static WebElement equipmentPic;	
	// 终端设备管理>查看 设备添加时间
	@FindBy(xpath = "//*[@class='pageFormContent h_inputChange']/div/dl[18]/dd")
	private static WebElement checkAdd;
	// 终端设备管理>查看 设备接入时间
	@FindBy(xpath = "//*[@class='pageFormContent h_inputChange']/div/dl[19]/dd")
	private static WebElement checkJoinUp;
	@FindBy(xpath = "//*[@id='equipmentAdd_SuccessFile']/span")
	private static WebElement checkImg;

	static Logger log = LogManager.getLogger(EquipmentManagement.class.getCanonicalName());
	private static String currentPath = System.getProperty("user.dir");

	public static void openEquipmentManagementLink() {

		equipmentManagementLink.click();

	}
	public static void openEquipmentStatusLink() {

		equipmentStatusLink.click();

	}

	public static boolean addEquipment(WebDriver driver,
			String equipmentNameValue, String equipmentSerialValue,String province,String city,String region,
			String communityNameValue, String buildingPartitionValue,
			String building1Value, String doorNoValue,
			String communityLonValue, String communityLatValue)
			throws InterruptedException {
		// 等待添加小区按钮出现
		boolean isSunccess = true;
		try {
			new WebDriverWait(driver, 12).until(ExpectedConditions
					.presenceOfElementLocated(By.className("add")));
			addEquipmentBtn.click();
			Thread.sleep(2000);
			equipmentName.get(2).sendKeys(equipmentNameValue);
			equipmentSerial.get(2).sendKeys(equipmentSerialValue);
            Select selectProvince = new Select(equipmentArea.get(2));
			selectProvince.selectByValue(province);
			Select selectCity = new Select(equipmentCity);
			selectCity.selectByValue(city);
			Select selectRegion = new Select(equipmentDistrict);
			selectRegion.selectByValue(region);
			Thread.sleep(1000);
			Select selectCommunity = new Select(equipmentCommunity);
			selectCommunity.selectByVisibleText(communityNameValue);
			Thread.sleep(1000);
			Select selectPartition = new Select(equipmentPartition);
			selectPartition.selectByVisibleText(buildingPartitionValue);
			Thread.sleep(1000);
			Select selectTenement = new Select(equipmentTenement);
			selectTenement.selectByVisibleText(building1Value);
			Thread.sleep(1000);
			Select selectUnit = new Select(equipmentUnit);
			selectUnit.selectByIndex(1);

			Select selectinstallPosition = new Select(installPosition);
			selectinstallPosition.selectByValue("COMMUNITY");
			doorNo.sendKeys(doorNoValue);
			equipmentLon.sendKeys(communityLonValue);
			equipmentLat.sendKeys(communityLatValue);
			saveBtn.get(0).click();
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			takeScreenShot();
			if (saveBtn.get(1) != null) {
				log.info("新增设备失败，将关闭对话框.....");
				System.out.println("--------" + saveBtn.get(1));
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
			Thread.sleep(2000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("alertMsg.close()");
			Thread.sleep(2000);
		}

		return true;
	}
	
	public static void findKeywords(WebDriver driver, String keywordsValue)
			throws InterruptedException {
		try {
			Thread.sleep(3000);
			equipmentName.get(0).sendKeys(keywordsValue);		
			seacherBtn.get(0).click();
			log.info("设备查找成功");
		} catch (Exception e) {
			log.info("设备查找失败");
			log.error(e.toString());			
			takeScreenShot();
			Base.closeTab(driver);
			// TODO: handle exception
		}
		// seachBtn.get(1).click();
	}

	public static boolean checkEquipment(WebDriver driver,
			String installPositionValue, int row) throws InterruptedException {
		// 等待添加小区按钮出现
		try {
			new WebDriverWait(driver, 12).until(ExpectedConditions
					.presenceOfElementLocated(By.className("add")));
			// 找到指定值行的修改link
			String xpath = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[12]";
			WebElement tableCell = driver.findElement(By.xpath(xpath));
			WebElement editTableCell = tableCell.findElement(By.linkText("详情"));
			editTableCell.click();
			Thread.sleep(2000);
			List<String> tableContent = new ArrayList<String>();
			for (int i = 1; i < 12; i++) {
				String checkXpath = "//*[@class='h_detailChange']/dl[" + i
						+ "]/dd";
				WebElement checkTable = driver
						.findElement(By.xpath(checkXpath));
				tableContent.add(checkTable.getText());
			}
//			for (int i = 0; i < tableContent.size(); i++) {
//				if (tableContent.get(i) == null) {
//					saveBtn.get(0).click();
//					Base.closeTab(driver);
//					return false;
//				}
//			}
			System.out.println("安装位置" + installPositionValue);
			System.out.println(tableContent.get(0));
			System.out.println(tableContent.get(1));
			System.out.println(tableContent.get(2));
			System.out.println(tableContent.get(3));
			System.out.println(tableContent.get(4));
			System.out.println(tableContent.get(5));
			System.out.println(tableContent.get(6));
			System.out.println(tableContent.get(7));
			System.out.println(tableContent.get(8));
			System.out.println(tableContent.get(9));
			System.out.println(tableContent.get(10));

			if (!(tableContent.contains(installPositionValue))) {
				saveBtn.get(0).click();
				Base.closeTab(driver);
				return false;
			}
			System.out.println("添加时间" + checkAdd.getText());
			System.out.println("接入时间" + checkJoinUp.getText());
			if (checkAdd.getText().equals("")
					|| checkJoinUp.getText().equals("")) {
				saveBtn.get(0).click();
				Base.closeTab(driver);
				return false;
			}
			saveBtn.get(0).click();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			takeScreenShot();
			saveBtn.get(0).click();
			Base.closeTab(driver);
		}

		return true;
	}

	public static boolean editEquipment(WebDriver driver, int row)
			throws InterruptedException {
		// 等待添加小区按钮出现
		boolean isSunccess = true;
		try {
			Thread.sleep(2000);
			// 找到指定值行的修改link
			String xpath = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[12]";
			new WebDriverWait(driver, 12).until(ExpectedConditions
					.presenceOfElementLocated(By.xpath(xpath)));
			WebElement tableCell = driver.findElement(By.xpath(xpath));
			WebElement editTableCell = tableCell.findElement(By.linkText("编辑"));
			editTableCell.click();
			Thread.sleep(2000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView()", equipmentPicFile);
			//本地运行屏蔽图片上传
			equipmentPicFile.sendKeys(currentPath + "/xssCommunity.png");
			Thread.sleep(2000);
			saveBtn.get(0).click();
			Thread.sleep(2000);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			takeScreenShot();
			if (saveBtn.get(1) != null) {
				log.info("编辑设备失败，将关闭对话框.....");
				System.out.println("--------" + saveBtn.get(1));
				saveBtn.get(1).click();
				Base.closeTab(driver);
			}
			isSunccess = false;
			return false;
		}
		if (isSunccess) {
			new WebDriverWait(driver, 20).until(ExpectedConditions
					.presenceOfElementLocated(By
							.xpath("//*[@class='correct']/div[2]/ul/li/a")));
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@class='correct']/div[2]/ul/li/a"))
					.click();
			Thread.sleep(2000);
			String xpath1 = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[12]";
			WebElement checkTable1 = driver.findElement(By.xpath(xpath1));
			WebElement checkEditTable1 = checkTable1.findElement(By
					.linkText("详情"));
			checkEditTable1.click();
			Thread.sleep(2000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView()", equipmentPic);
			Thread.sleep(2000);
			By locator = By.xpath("//*[@id='equipmentAdd_SuccessFile']/span");			
			if (Base.isElementExsit(driver, locator)) {
				saveBtn.get(0).click();
				System.out.println("--------3");
				return true;
			}
		}
		Base.closeTab(driver);
		return false;
	}
	
	public static boolean checkEquipment(WebDriver driver,
			String communityNameValue, String doorNoValue,int row) throws InterruptedException {
		// 等待添加小区按钮出现
		try {
			Thread.sleep(2000);
			new WebDriverWait(driver, 12).until(ExpectedConditions
					.presenceOfElementLocated(By.className("add")));
			String nameXpath = "//*[@class='gridTbody']/table/tbody/tr["
					+ row + "]/td[2]";
			WebElement nameText = driver.findElement(By.xpath(nameXpath));
			String addrXpath = "//*[@class='gridTbody']/table/tbody/tr["
					+ row + "]/td[3]";
			WebElement addrText = driver.findElement(By.xpath(addrXpath));
			String positionXpath = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[4]";
			WebElement positionText = driver.findElement(By.xpath(positionXpath));
			log.info("--------" + nameText.getText());
			log.info("--------" + addrText.getText());
			log.info("--------" + positionText.getText());	
			log.info("--------" + nameText.getText().equals(communityNameValue));
			log.info("--------" + !(addrText.getText().equals("")));
			log.info("--------" + positionText.getText().equals(doorNoValue));	
			
			if (!(addrText.getText().equals(""))
					&&nameText.getText().equals(communityNameValue)
					&& positionText.getText().equals(doorNoValue)) {
				return true;
			}
			log.info("新增设备信息核对错误");
			takeScreenShot();
			Base.closeTab(driver);			
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			takeScreenShot();
			Base.closeTab(driver);
			// TODO: handle exception
		}
		log.info("新增设备信息核对错误");
		takeScreenShot();
		Base.closeTab(driver);
		return false;
	}
	
	public static boolean checkWyEquipment(WebDriver driver,
			String communityNameValue, String equipmentType,int row) throws InterruptedException {
		// 等待添加小区按钮出现
		try {
			Thread.sleep(2000);
			String nameXpath = "//*[@class='gridTbody']/table/tbody/tr["
					+ row + "]/td[3]";
			WebElement nameText = driver.findElement(By.xpath(nameXpath));
			String addrXpath = "//*[@class='gridTbody']/table/tbody/tr["
					+ row + "]/td[1]";
			WebElement addrText = driver.findElement(By.xpath(addrXpath));
			String typeXpath = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[5]";
			WebElement typeText = driver.findElement(By.xpath(typeXpath));
			log.info("--------" + nameText.getText());
			log.info("--------" + addrText.getText());
			log.info("--------" + typeText.getText());	
			log.info("--------" + nameText.getText().equals(communityNameValue));
			log.info("--------" + !(addrText.getText().equals("")));
			log.info("--------" + typeText.getText().equals(equipmentType));	
			
			if (!(addrText.getText().equals(""))
					&&nameText.getText().equals(communityNameValue)
					&& typeText.getText().equals(equipmentType)) {
				return true;
			}
			log.info("新增设备信息核对错误");
			takeScreenShot();
			Base.closeTab(driver);			
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			takeScreenShot();
			Base.closeTab(driver);
			// TODO: handle exception
		}
		log.info("新增设备信息核对错误");
		takeScreenShot();
		Base.closeTab(driver);
		return false;
	}
}
