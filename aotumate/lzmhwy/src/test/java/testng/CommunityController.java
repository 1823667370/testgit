package testng;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lz.libarary.grid.Browser;
import com.lz.libarary.pages.EquipmentManagement;
import com.lz.libarary.pages.HouseManagement;
import com.lz.libarary.pages.LoginPage;
import com.lz.libarary.pages.Page;
import com.lz.libarary.pages.PublicPage;
import com.lz.libarary.pages.ResidentManagement;
import com.lz.libarary.pages.SystemMangement;
import com.lz.libarary.utils.Base;
import com.lz.libarary.utils.SeleniumEventListener;
import com.lz.libarary.utils.TestNGListener;
import com.lz.libarary.utils.UIScreenShot;
import com.lz.libarary.utils.tableAction;

/**
 * 
 * @version 1.00.00
 * @description: 社区资源管理平台控制类
 * @author: songer.xing
 * @date: 2016-7-13
 * @history:
 */
@org.testng.annotations.Listeners({ TestNGListener.class })
public class CommunityController extends UIScreenShot {

	private static final String lzmhUrl = "https://lzmhqa.lz-qs.com:8490/lzmh_wlw_web/system/login_logout";
	private static final String lzmhUrl1 = "https://lzmh.lz-qs.com:5092/lzmh_wlw/system/login_logout";
	private static final String lzmhUrl2 = "https://lzmhqa.lz-qs.com:8590/lzmh_portal/index";
	static Logger log = LogManager.getLogger(CommunityController.class.getCanonicalName());
	private WebDriver driver;
	protected EventFiringWebDriver eventDriver;
	private int row;


	@Test(description = "login for lzmh system")
	@Parameters({ "loginNameValue", "passwordValue", "validateCodeValue" })
	public void systemLogin(String loginNameValue, String passwordValue,
			String validateCodeValue) throws InterruptedException {
		driver.get(lzmhUrl);
		Assert.assertTrue(LoginPage.loginCommunityAction(driver,
				loginNameValue, passwordValue));
		// org.apache.logging.log4j.Logger logger =
		// LogManager.getLogger(CommunityController.class.getName());
		// logger.info("log4j");
		log.info("登录社区资源管理平台pass!");
		Reporter.log("登录社区资源管理平台pass!");
	}
	
	@Test(description = "login for lzmh system")
	@Parameters({ "loginNameValue", "passwordValue"})
	public void systemLogin_P(String loginNameValue, String passwordValue) throws InterruptedException {
		driver.get(lzmhUrl1);
		Assert.assertTrue(LoginPage.loginCommunityAction_P(driver, loginNameValue, passwordValue));
		log.info("登录社区资源管理平台pass!");
		Reporter.log("登录社区资源管理平台pass!");
	}

	@Test(description = "community for lzmh system")
	@Parameters({ "communityNameValue","province","city","region", "communityAddrValue",
			"communityLonValue", "communityLatValue", "blueKeyValue",
			"inviteCountValue","communityAddrCheck", "keywordsCommunityValue",
			"communityDevelopersValue", "buildingPartitionValue",
			"building1Value", "unitList1Value", "roomValue", "phoneValue",
			"propertyNameValue","brandNameValue" })
	public void communityAction(String communityNameValue,String province,String city,String region,
			String communityAddrValue, String communityLonValue,
			String communityLatValue, String blueKeyValue,
			String inviteCountValue, String communityAddrCheck,String keywordsCommunityValue,
			String communityDevelopersValue, String buildingPartitionValue,
			String building1Value, String unitList1Value, String roomValue,
			String phoneValue, String propertyNameValue,String brandNameValue)
			throws InterruptedException {
		// 进入小区管理
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		Thread.sleep(2000);
		// 点击左边菜单物业中心
		Assert.assertTrue(PublicPage.clickLeftListAction(driver, 0));
		Thread.sleep(2000);
		HouseManagement.openCommunityLink();
		// 添加小区
		Thread.sleep(2000);
		Assert.assertTrue(HouseManagement.addCommunity(driver, communityNameValue, province, city, region, communityAddrValue, communityLonValue, communityLatValue, blueKeyValue, inviteCountValue, propertyNameValue,brandNameValue));
		// 查找添加的小区
		Thread.sleep(2000);
		PublicPage.findKeywords(driver, keywordsCommunityValue);
		Thread.sleep(3000);
		Assert.assertTrue(tableAction.isArrLoop(
				tableAction.tableRows(driver, 1), keywordsCommunityValue));
		log.info("添加小区pass!并查找成功");
		Reporter.log("添加小区pass!并查找成功!");
		row = tableAction.returntableRow(driver, keywordsCommunityValue, 1);
		// 判断是否修改小区成功
		Assert.assertTrue(HouseManagement.houseCheck(driver, propertyNameValue, communityAddrCheck,brandNameValue, row));
		log.info("核对小区信息pass!");
		Reporter.log("核对小区信息pass!");
		//本地无法上传图片到远端，屏蔽图片
//		Assert.assertTrue(HouseManagement.editCommunity(driver,
//				communityDevelopersValue, row));
//		log.info("编辑小区pass!");
//		Reporter.log("编辑小区pass!");
		Thread.sleep(2000);
		Assert.assertTrue(HouseManagement.addBuilding(driver, row,
				buildingPartitionValue, building1Value, unitList1Value,
				roomValue, phoneValue));
		log.info("添加楼宇pass!");
		Reporter.log("添加楼宇pass!");
		Base.closeTab2(driver);
		Thread.sleep(2000);
		Assert.assertTrue(HouseManagement.addPeople(driver, row, phoneValue));
		log.info("添加户主pass!");
		Reporter.log("添加户主pass!");
		Base.closeTab2(driver);
		Thread.sleep(2000);
		Base.closeTab(driver);
	}

	@Test(description = "resident for lzmh system")
	@Parameters({ "communityNameValue", "residentPhoneValue","residentCity",
			"appUserNameValue", "userBirthdayValue", "buildingPartitionValue",
			"roomValue", "cycleTypeValue", "timeTypeValue" })
	public void residentAction(String communityNameValue,String residentPhoneValue,
			String residentCity, String appUserNameValue,
			String userBirthdayValue, String buildingPartitionValue,
			String roomValue, String cycleTypeValue, String timeTypeValue)
			throws InterruptedException {
		// 点击左边菜单物业中心
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		Thread.sleep(2000);

		Assert.assertTrue(PublicPage.clickLeftListAction(driver, 0));
		Thread.sleep(2000);
		ResidentManagement.openResidentLink();
		Thread.sleep(2000);
		ResidentManagement.addResident(driver, communityNameValue,
				residentPhoneValue, appUserNameValue, userBirthdayValue);
		Thread.sleep(3000);
		PublicPage.findKeywords(driver, residentPhoneValue);
		Thread.sleep(3000);
		Assert.assertTrue(tableAction.isArrLoop(
				tableAction.tableRows(driver, 9), residentPhoneValue));
		log.info("添加住户pass!并查找成功");
		Reporter.log("添加住户pass!并查找成功!");
		row = tableAction.returntableRow(driver, residentPhoneValue, 9);
		// 判断是否修改物业成功
		Assert.assertTrue(ResidentManagement.residentCheck(driver, residentCity, communityNameValue, buildingPartitionValue, roomValue, appUserNameValue, residentPhoneValue, row));
		log.info("核对住户信息pass!");
		Reporter.log("核对住户信息pass!");
		Assert.assertTrue(ResidentManagement.editResident(driver,
				residentPhoneValue, cycleTypeValue, timeTypeValue, row));
		log.info("编辑住户pass!");
		Reporter.log("编辑住户pass!");
		Thread.sleep(2000);
		Assert.assertTrue(ResidentManagement.deleteResident(driver, row));
		log.info("删除住户pass!");
		Reporter.log("删除住户pass!");
		Base.closeTab(driver);
	}

	@Test(description = "login for lzmh system")
	@Parameters({ "equipmentNameValue", "equipmentSerialValue","province","city","region",
			"communityNameValue", "buildingPartitionValue", "building1Value",
			"doorNoValue", "communityLonValue", "communityLatValue",
			"installPositionValue" })
	public void equipmentAction(String equipmentNameValue,
			String equipmentSerialValue, String province,String city,String region,String communityNameValue,
			String buildingPartitionValue, String building1Value,
			String doorNoValue, String communityLonValue,
			String communityLatValue, String installPositionValue)
			throws InterruptedException {
		// 点击左边菜单设备管理
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		Thread.sleep(2000);
		Assert.assertTrue(PublicPage.clickLeftListAction(driver, 0));
		Thread.sleep(2000);
		EquipmentManagement.openEquipmentManagementLink();
		Thread.sleep(2000);
		Assert.assertTrue(EquipmentManagement.addEquipment(driver, equipmentNameValue, equipmentSerialValue, province, city, region, communityNameValue, buildingPartitionValue, building1Value, doorNoValue, communityLonValue, communityLatValue));
		Thread.sleep(2000);
		EquipmentManagement.findKeywords(driver, equipmentNameValue);
		Thread.sleep(3000);
		Assert.assertTrue(tableAction.isArrLoop(
				tableAction.tableRows(driver, 5), equipmentSerialValue));
		for (int i = 0; i < 30; i++) {
			if (!(tableAction
					.isArrLoop(tableAction.tableRows(driver, 11), "在线"))) {
				Thread.sleep(2000);
			} else {
				break;
			}
		}
		Thread.sleep(2000);
		Assert.assertTrue(tableAction.isArrLoop(
				tableAction.tableRows(driver, 11), "在线"));
		log.info("新增设备pass!并查找成功");
		Reporter.log("新增设备pass!并查找成功!");
		row = tableAction.returntableRow(driver, equipmentSerialValue, 5);
		Thread.sleep(2000);
		Assert.assertTrue(EquipmentManagement.checkEquipment(driver,
				communityNameValue, doorNoValue, row));
		log.info("核对设备信息pass!");
		Reporter.log("核对设备信息pass!");
		Assert.assertTrue(EquipmentManagement.checkEquipment(driver,
				installPositionValue, row));
		log.info("查看设备pass!");
		Reporter.log("查看pass!");
		Thread.sleep(2000);
//		Assert.assertTrue(EquipmentManagement.editEquipment(driver, row));
//		log.info("编辑设备pass!");
//		Reporter.log("编辑设备pass!");
//		Thread.sleep(2000);
		// Assert.assertTrue(PublicPage.delete(driver, row, 13));
		// log.info("删除设备pass!");
		// Reporter.log("删除设备pass!");
		Base.closeTab(driver);
	}

	@Test(description = "clear for lzmh system")
	@Parameters({ "equipmentNameValue", "keywordsCommunityValue","equipmentSerialValue" })
	public void deleteAction(String equipmentNameValue,
			String keywordsCommunityValue,String equipmentSerialValue) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		Thread.sleep(2000);
		Assert.assertTrue(PublicPage.clickLeftListAction(driver, 1));
		// 打开终端设备界面
		Thread.sleep(2000);
		EquipmentManagement.openEquipmentManagementLink();
		Thread.sleep(2000);
		EquipmentManagement.findKeywords(driver, equipmentNameValue);
		Thread.sleep(3000);
		Assert.assertTrue(tableAction.isArrLoop(
				tableAction.tableRows(driver, 11), "在线"));
		log.info("查找设备成功!");
		Reporter.log("查找设备成功!");
		row = tableAction.returntableRow(driver, equipmentSerialValue, 5);
		Assert.assertTrue(PublicPage.delete(driver, row, 12));
		log.info("删除设备成功!");
		Reporter.log("删除设备成功!");
		Thread.sleep(3000);
		Base.closeTab(driver);
		Thread.sleep(2000);
		// 打开物业中心
		// Assert.assertTrue(PublicPage.closeLeftListAction(driver));
		Assert.assertTrue(PublicPage.clickLeftListAction(driver, 0));
		Thread.sleep(2000);
		// 打开小区管理
		HouseManagement.openCommunityLink();
		Thread.sleep(2000);
		PublicPage.findKeywords(driver, keywordsCommunityValue);
		Thread.sleep(5000);
		Assert.assertTrue(tableAction.isArrLoop(
				tableAction.tableRows(driver, 1), keywordsCommunityValue));
		log.info("查找小区成功");
		Reporter.log("查找小区成功!");
		row = tableAction.returntableRow(driver, keywordsCommunityValue, 1);
		Assert.assertTrue(HouseManagement.deletePeople(driver, row));
		log.info("删除住户pass!");
		Reporter.log("删除住户pass!");
		Thread.sleep(2000);
		// PublicPage.findKeywords(driver, keywordsCommunityValue);
		// Thread.sleep(2000);
		// row = tableAction.returntableRow(driver, keywordsCommunityValue, 2);
		// 判断是否修改物业成功
		Assert.assertTrue(HouseManagement.deleteBuilding(driver, row));
		log.info("删除楼宇pass!");
		Reporter.log("删除楼宇pass!");
		Assert.assertTrue(PublicPage.delete(driver, row, 14));
		log.info("删除小区pass!");
		Reporter.log("删除小区pass!");
		Thread.sleep(3000);
		Base.closeTab(driver);
		/*
		 * Thread.sleep(2000); PropertyManagement.openPropertyLink();
		 * Thread.sleep(2000); PublicPage.findKeywords(driver, keywordsValue);
		 * Thread.sleep(3000); Assert.assertTrue(tableAction.isArrLoop(
		 * tableAction.tableRows(driver, 1), keywordsValue));
		 * log.info("查找物业成功"); Reporter.log("查找物业成功!"); row =
		 * tableAction.returntableRow(driver, keywordsValue, 1);
		 * Assert.assertTrue(PublicPage.delete(driver, row, 6));
		 * log.info("删除物业pass!"); Reporter.log("删除物业pass!");
		 */

	}
	
	@Test(description = "community for lzmh systemMangement")
	@Parameters({ "loginNameValue", "passwordValue","userWy" })
	public void SystemAction(String username,String pwd,String userWy) throws InterruptedException{
		driver.get(lzmhUrl2);
		Assert.assertTrue(SystemMangement.loginSystem(driver, username, pwd));
		Thread.sleep(2000);
		SystemMangement.userManagementtLink();
		Thread.sleep(2000);
		PublicPage.findKeywords(driver, userWy);
		Thread.sleep(5000);
		Assert.assertTrue(tableAction.isArrLoop(
				tableAction.tableRows(driver, 1), userWy));
		log.info("查找物业用户成功！");
		Reporter.log("查找物业用户成功!");
		row = tableAction.returntableRow(driver, userWy, 1);
		Thread.sleep(2000);		
		SystemMangement.editUser(driver, row);
		
	}

	@BeforeTest
	public void setUp() {
		//this.driver = new FirefoxDriver();
		 //selenium形式	
		//eventDriver = new EventFiringWebDriver(driver);
		//eventDriver.register(new SeleniumEventListener());
//		System.setProperty("webdriver.chrome.driver",
//				System.getProperty("user.dir")+"/tools/chromedriver");
		this.driver = new EventFiringWebDriver(Browser.getRemoteDriver(new Browser("chrome"))).register(new SeleniumEventListener());
		Page.initAllPages(driver);
		this.driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		this.driver.manage().window().maximize();
		init(driver);
	}

	@AfterTest
	public void tearDown() {
		this.driver.close();
	}

}
