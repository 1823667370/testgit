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
import com.lz.libarary.pages.CardHouseManagement;
import com.lz.libarary.pages.CardPublicManagement;
import com.lz.libarary.pages.CardToBlackList;
import com.lz.libarary.pages.CardType;
import com.lz.libarary.pages.EquipmentManagement;
import com.lz.libarary.pages.ExpireCardManagement;
import com.lz.libarary.pages.HouseManagement;
import com.lz.libarary.pages.LoginPage;
import com.lz.libarary.pages.Page;
import com.lz.libarary.pages.PropertyDM;
import com.lz.libarary.pages.PropertyNote;
import com.lz.libarary.pages.PublicPage;
import com.lz.libarary.pages.ResidentManagement;
import com.lz.libarary.pages.accCard;
import com.lz.libarary.utils.Base;
import com.lz.libarary.utils.SeleniumEventListener;
import com.lz.libarary.utils.TestNGListener;
import com.lz.libarary.utils.UIScreenShot;
import com.lz.libarary.utils.tableAction;

/**
 * 
 * @version 1.00.00
 * @description: 物业管理云平台控制类
 * @author: songer.xing
 * @date: 2016-11-19
 * @history:
 */
@org.testng.annotations.Listeners({ TestNGListener.class })
public class wyController extends UIScreenShot {

	private static final String lzmhUrl = "https://lzmhqa.lz-qs.com:8990/lzmh_wy_web/index";
	private static final String lzmhUrl1 = "https://lzmh.lz-qs.com:5099/system/login_verify";
	static Logger log = LogManager.getLogger(wyController.class.getCanonicalName());
	private WebDriver driver;
	protected EventFiringWebDriver eventDriver;
	private int row;

	@Test(description = "login for lzmh system")
	@Parameters({ "loginNameValue", "passwordValue" })
	public void systemLogin(String loginNameValue, String passwordValue)
			throws InterruptedException {
		driver.get(lzmhUrl);
		Assert.assertTrue(LoginPage.loginWyAction(driver, loginNameValue,
				passwordValue));
		// org.apache.logging.log4j.Logger logger =
		// LogManager.getLogger(CommunityController.class.getName());
		// logger.info("log4j");
		log.info("登录物业管理云平台pass!");
		Reporter.log("登录物业管理云平台pass!");
	}
	
	@Test(description = "login for lzmh system")
	@Parameters({"loginNameValue", "passwordValue"})
	public void systemLogin_P(String loginNameValue, String passwordValue) throws InterruptedException {
		driver.get(lzmhUrl1);
		Assert.assertTrue(LoginPage.loginCommunityAction_P(driver, loginNameValue, passwordValue));
		log.info("登录物业管理云平台pass!");
		Reporter.log("登录物业管理云平台pass!");
	}

	@Test(description = "community for lzmh system")
	@Parameters({ "keywordsCommunityValue","communityAddrCheck", "wyPartitionValue",
			"building1Value", "unitList1Value", "floorStartValue",
			"floorEndValue", "roomStartValue", "roomNumValue", "phoneValue",
			"propertyNameValue" })
	public void communityAction(String keywordsCommunityValue,String communityAddrCheck,
			String wyPartitionValue, String building1Value,
			String unitList1Value, String floorStartValue,
			String floorEndValue, String roomStartValue, String roomNumValue,
			String phoneValue, String propertyNameValue)
			throws InterruptedException {
		// 进入小区管理
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(2000);
		Assert.assertTrue(PublicPage.clickLeftListAction(driver, 0));
		Thread.sleep(2000);
		HouseManagement.openCommunityLink();
		Thread.sleep(2000);
		PublicPage.findKeywords(driver, keywordsCommunityValue);
		Thread.sleep(3000);
		Assert.assertTrue(tableAction.isArrLoop(
				tableAction.tableRows(driver, 2), keywordsCommunityValue));
		log.info("小区查找成功");
		Reporter.log("小区查找成功!");
		row = tableAction.returntableRow(driver, keywordsCommunityValue, 2);
		// 判断是否修改小区成功
		log.info("--------");
		Assert.assertTrue(HouseManagement.wyHouseCheck(driver, propertyNameValue, communityAddrCheck, row));
		log.info("核对小区信息pass!");
		Reporter.log("核对小区信息pass!");
		Thread.sleep(2000);
		Assert.assertTrue(HouseManagement.addWyBuilding(driver, row,
				wyPartitionValue, building1Value, unitList1Value,
				floorStartValue, floorEndValue, roomStartValue, roomNumValue));
		log.info("添加楼宇pass!");
		Reporter.log("添加楼宇pass!");
		Thread.sleep(2000);
		Assert.assertTrue(HouseManagement.deletWyBuilding(driver, row));
		log.info("删除楼宇pass!");
		Reporter.log("删除楼宇pass!");
		Base.closeTab2(driver);
		Thread.sleep(2000);
		Assert.assertTrue(HouseManagement.deWyPeople(driver, row));
		log.info("删除户主pass!");
		Reporter.log("删除户主pass!");
		Thread.sleep(2000);
		Assert.assertTrue(HouseManagement.addWyPeople(driver, row, phoneValue));
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
	public void residentAction(String communityNameValue,
			String residentPhoneValue,String residentCity, String appUserNameValue,
			String userBirthdayValue, String buildingPartitionValue,
			String roomValue, String cycleTypeValue, String timeTypeValue)
			throws InterruptedException {
		// 点击左边菜单物业中心
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(2000);
		ResidentManagement.openResidentLink();
		Thread.sleep(2000);
		ResidentManagement.addResident(driver, communityNameValue,
				residentPhoneValue, appUserNameValue, userBirthdayValue);
		Thread.sleep(3000);
		ResidentManagement.findKeywords(driver, residentPhoneValue);
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

	@Test(description = "propertyNote for lzmh system")
	@Parameters({ "communityNameValue", "noticeTitleValue",
			"cardEngineerPhone", "cardEngineerNoValue" })
	public void propertyNoteAction(String communityNameValue,
			String noticeTitleValue, String cardEngineerPhone,
			String cardEngineerNoValue) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(2000);
		PropertyNote.openPropertyNoteLink();
		Thread.sleep(2000);
		Assert.assertTrue(PropertyNote.addPropertyNote(driver,
				noticeTitleValue, communityNameValue));
		Thread.sleep(2000);
		PublicPage.findKeywords(driver, noticeTitleValue);
		Thread.sleep(3000);
		Assert.assertTrue(tableAction.isArr2Loop(driver,
				tableAction.returntableRows(driver, communityNameValue, 2),
				"草稿", 7));
		// Assert.assertTrue(tableAction.isArrLoop(
		// tableAction.tableRows(driver, 2), communityNameValue));
		// Assert.assertTrue(tableAction.isArrLoop(
		// tableAction.tableRows(driver, 7), "草稿"));
		log.info("公告草稿添加pass!并查找成功");
		Reporter.log("公告草稿pass!并查找成功!");
		row = tableAction.returntableRow(driver, communityNameValue, 2);
		// 编辑草稿并发送
		Assert.assertTrue(PropertyNote.editPropertyNote(driver, row));
		log.info("编辑草稿并推送pass!");
		Reporter.log("编辑草稿并推送pass!");
		Thread.sleep(2000);
		Assert.assertTrue(PropertyNote.cancelPropertyNote(driver, row, 9));
		log.info("公告撤销pass!");
		Reporter.log("公告撤销pass!");
		Thread.sleep(2000);
		Assert.assertTrue(PublicPage.delete(driver, row, 9));
		log.info("删除已撤销的公告pass!");
		Reporter.log("删除已撤销的公告pass!");
		Base.closeTab(driver);
	}

	@Test(description = "propertyDM for lzmh system")
	@Parameters({ "communityNameValue", "noticeTitleValue",
			"cardEngineerPhone", "cardEngineerNoValue" })
	public void propertyDMAction(String communityNameValue,
			String noticeTitleValue, String cardEngineerPhone,
			String cardEngineerNoValue) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		Thread.sleep(2000);
		PropertyDM.openPropertyDMLink();
		Thread.sleep(2000);
		Assert.assertTrue(PropertyDM.addPropertyDM(driver, noticeTitleValue,
				communityNameValue));
		Thread.sleep(2000);
		PublicPage.findKeywords(driver, noticeTitleValue);
		Thread.sleep(3000);
		Assert.assertTrue(tableAction.isArr2Loop(driver,
				tableAction.returntableRows(driver, noticeTitleValue, 2),
				"已推送", 6));
		// Assert.assertTrue(tableAction.isArrLoop(
		// tableAction.tableRows(driver, 2), noticeTitleValue));
		// Assert.assertTrue(tableAction.isArrLoop(
		// tableAction.tableRows(driver, 6), "已推送"));
		log.info("私信推送pass!并查找成功");
		Reporter.log("私信推送pass!并查找成功!");
		row = tableAction.returntableRow(driver, noticeTitleValue, 2);
		// 取消推送
		Assert.assertTrue(PropertyDM.cancelPropertyDM(driver, row, 8));
		log.info("撤销私信pass!");
		Reporter.log("撤销私信pass!");
		Thread.sleep(2000);
		Assert.assertTrue(PublicPage.delete(driver, row, 8));
		log.info("删除已撤销的私信pass!");
		Reporter.log("删除已撤销的私信pass!");
		Base.closeTab(driver);
	}

	@Test(description = "login for lzmh system")
	@Parameters({ "propertyNameValue", "cardTypeNameValue", "timeTypeValue" })
	public void cardTypeAction(String propertyNameValue,
			String cardTypeNameValue, String timeTypeValue)
			throws InterruptedException {
		// 点击左边菜单物业中心
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		Thread.sleep(2000);
	      //刷新当前页面
	    driver.navigate().refresh();
		Thread.sleep(3000);
		Assert.assertTrue(PublicPage.clickLeftListAction(driver, 2));
		Thread.sleep(2000);
		CardType.openCardTypeLink();
		Thread.sleep(2000);
		Assert.assertTrue(CardType.addWyCardType(driver, propertyNameValue,
				cardTypeNameValue));
		Thread.sleep(2000);
		PublicPage.findKeywords(driver, cardTypeNameValue);
		Thread.sleep(3000);
		Assert.assertTrue(tableAction.isArrLoop(
				tableAction.tableRows(driver, 2), cardTypeNameValue));
		log.info("卡类型添加pass!并查找成功");
		Reporter.log("卡类型pass!并查找成功!");

		row = tableAction.returntableRow(driver, cardTypeNameValue, 2);
		// 判断是否修改物业成功
		Assert.assertTrue(CardType.editCardType(driver, timeTypeValue, row));
		log.info("编辑卡类型pass!");
		Reporter.log("编辑卡类型pass!");
		Thread.sleep(2000);
		// Assert.assertTrue(PublicPage.delete(driver, row, 4));
		// log.info("删除卡类型pass!");
		// Reporter.log("删除卡类型pass!");
		Base.closeTab(driver);
	}

	@Test(description = "cardPublic for lzmh system")
	@Parameters({ "communityNameValue", "cardNoValue", "cardContactValue",
			"cardTypeNameValue", "phoneValue" })
	public void cardPublicAction(String communityNameValue, String cardNoValue,
			String cardContactValue, String cardTypeNameValue, String phoneValue)
			throws InterruptedException {
		// 点击左边菜单物业中心
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		Thread.sleep(2000);
		CardPublicManagement.openCardPublicManagementLink();
		Thread.sleep(2000);
		Assert.assertTrue(CardPublicManagement.addCardPublic(driver,
				communityNameValue, cardNoValue, cardContactValue,
				cardTypeNameValue));
		Thread.sleep(2000);
		Base.closeTab(driver);
		Thread.sleep(2000);
		// 打卡门禁卡-------
		accCard.openAccCardLink();
		Thread.sleep(2000);
		// 输入门禁卡号查询
		accCard.findKeywords(driver, cardNoValue);
		log.info("公卡，门禁卡界面查找成功");
		Reporter.log("公卡，门禁卡界面查找成功!");
		Thread.sleep(4000);
		row = tableAction.returntableRow(driver, cardNoValue, 1);
		Assert.assertTrue(accCard.accCardCheck(driver, "已激活", "否", "是", "否",
				row));
		log.info("公卡添加pass!门禁卡界面核对信息成功");
		Reporter.log("公卡pass!门禁卡界面核对信息成功!");
		Thread.sleep(2000);
		Base.closeTab(driver);
		Thread.sleep(2000);
		// 门禁卡核对结束--------
		CardPublicManagement.openCardPublicManagementLink();
		Thread.sleep(2000);
		CardPublicManagement.findKeywords(driver, cardNoValue);
		Thread.sleep(3000);
		Assert.assertTrue(tableAction.isArrLoop(
				tableAction.tableRows(driver, 3), cardNoValue));
		log.info("公卡添加pass!并查找成功");
		Reporter.log("公卡pass!并查找成功!");

		row = tableAction.returntableRow(driver, cardNoValue, 3);
		// 判断是否修改物业成功
		Assert.assertTrue(CardPublicManagement.editCardPublic(driver,
				phoneValue, cardNoValue, cardTypeNameValue, row));
		log.info("编辑公卡pass!");
		Reporter.log("编辑公卡pass!");
		Thread.sleep(2000);
		// 移到黑名单并判断移走后本页无此记录，到黑名单页面查找可以查找到此条记录
		Assert.assertTrue(CardPublicManagement.cardToBalackList(driver, row));
		CardPublicManagement.findKeywords(driver, cardNoValue);
		Thread.sleep(3000);
		Assert.assertFalse(tableAction.isArrLoop(
				tableAction.tableRows(driver, 3), cardNoValue));
		log.info("公卡移到黑名单后，公卡界面无此记录pass!");
		Reporter.log("公卡移到黑名单后，公卡界面无此记录pass!");
		Base.closeTab(driver);
		Thread.sleep(2000);
		// 打卡门禁卡-------
		accCard.openAccCardLink();
		Thread.sleep(2000);
		// 输入门禁卡号查询
		accCard.findKeywords(driver, cardNoValue);
		log.info("公卡，门禁卡界面查找成功");
		Reporter.log("公卡，门禁卡界面查找成功!");
		Thread.sleep(4000);
		row = tableAction.returntableRow(driver, cardNoValue, 1);
		Assert.assertTrue(accCard.accCardCheck(driver, "未激活", "否", "是", "是",
				row));
		log.info("公卡移入黑名单!门禁卡界面核对信息成功");
		Reporter.log("公卡移入黑名单!门禁卡界面核对信息成功!");
		Thread.sleep(2000);
		Base.closeTab(driver);
		Thread.sleep(2000);
		// 门禁卡核对结束--------
		CardToBlackList.openCardToBlackListLink();
		Thread.sleep(2000);
		PublicPage.findKeywords(driver, cardNoValue);
		Thread.sleep(3000);
		row = tableAction.returntableRow(driver, cardNoValue, 3);
		Assert.assertTrue(CardToBlackList.moveBlackList(driver, row));
		PublicPage.findKeywords(driver, cardNoValue);
		Thread.sleep(3000);
		Assert.assertFalse(tableAction.isArrLoop(
				tableAction.tableRows(driver, 3), cardNoValue));
		log.info("公卡移出黑名单后，黑名单界面无此记录pass!");
		Reporter.log("公卡移出黑名单后，黑名单界面无此记录pass!");
		Base.closeTab(driver);
		Thread.sleep(2000);
		// 打卡门禁卡-------
		accCard.openAccCardLink();
		Thread.sleep(2000);
		// 输入门禁卡号查询
		accCard.findKeywords(driver, cardNoValue);
		log.info("公卡，门禁卡界面查找成功");
		Reporter.log("公卡，门禁卡界面查找成功!");
		Thread.sleep(4000);
		row = tableAction.returntableRow(driver, cardNoValue, 1);
		Assert.assertTrue(accCard.accCardCheck(driver, "未激活", "否", "是", "否",
				row));
		log.info("公卡移出黑名单pass!门禁卡界面核对信息成功");
		Reporter.log("公卡移出黑名单pass!门禁卡界面核对信息成功!");
		Thread.sleep(2000);
		Base.closeTab(driver);
		Thread.sleep(2000);
		// 门禁卡核对结束--------
		CardPublicManagement.openCardPublicManagementLink();
		Thread.sleep(2000);
		CardPublicManagement.findKeywords(driver, cardNoValue);
		Thread.sleep(3000);
		row = tableAction.returntableRow(driver, cardNoValue, 3);
		Assert.assertTrue(PublicPage.delete(driver, row, 13));
		log.info("删除公卡pass!");
		Reporter.log("删除公卡pass!");
		Base.closeTab(driver);
		Thread.sleep(2000);
		// 打卡门禁卡-------
		accCard.openAccCardLink();
		// 输入门禁卡号查询
		Thread.sleep(2000);
		accCard.findKeywords(driver, cardNoValue);
		Thread.sleep(4000);
		row = tableAction.returntableRow(driver, cardNoValue, 1);
		Assert.assertFalse(accCard.isAccCard(driver, cardNoValue, row));
		log.info("公卡删除pass!门禁卡无此公卡记录");
		Reporter.log("公卡删除pass!门禁卡无此公卡记录!");
		Thread.sleep(3000);
		Base.closeTab(driver);
		Thread.sleep(2000);
		// 门禁卡核对结束--------
	}

	@Test(description = "cardHouse for lzmh system")
	@Parameters({ "communityNameValue", "cardHouseNoValue", "cardContactValue",
			"phoneValue", "dateValue", "cardTypeNameValue" })
	public void cardHouseAction(String communityNameValue,
			String cardHouseNoValue, String cardContactValue,
			String phoneValue, String dateValue, String cardTypeNameValue)
			throws InterruptedException {
		// 点击左边菜单物业中心
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		Thread.sleep(2000);
		CardHouseManagement.openCardHouseManagementLink();
		Thread.sleep(2000);
		Assert.assertTrue(CardHouseManagement.addCardHouse(driver,
				communityNameValue, cardHouseNoValue, cardContactValue,
				dateValue, cardTypeNameValue));
		Thread.sleep(2000);
		CardHouseManagement.findKeywords(driver, cardHouseNoValue);
		Thread.sleep(3000);
		Assert.assertTrue(tableAction.isArrLoop(
				tableAction.tableRows(driver, 3), cardHouseNoValue));
		log.info("住户卡添加pass!并查找成功");
		Reporter.log("住户卡pass!并查找成功!");

		row = tableAction.returntableRow(driver, cardHouseNoValue, 3);
		// 判断是否修改物业成功
		Assert.assertTrue(CardHouseManagement.editCardHouse(driver, phoneValue,
				cardHouseNoValue, row, dateValue, cardTypeNameValue));
		log.info("编辑住户卡pass!");
		Reporter.log("编辑住户卡pass!");
		Thread.sleep(2000);
		// 移到黑名单并判断移走后本页无此记录，到黑名单页面查找可以查找到此条记录
		Assert.assertTrue(CardHouseManagement.cardToBalackList(driver, row));
		CardHouseManagement.findKeywords(driver, cardHouseNoValue);
		Thread.sleep(3000);
		Assert.assertFalse(tableAction.isArrLoop(
				tableAction.tableRows(driver, 3), cardHouseNoValue));
		log.info("住户卡移到黑名单后，住户卡界面无此记录pass!");
		Reporter.log("住户卡移到黑名单后，住户卡界面无此记录pass!");
		Base.closeTab(driver);
		Thread.sleep(2000);
		CardToBlackList.openCardToBlackListLink();
		Thread.sleep(2000);
		PublicPage.findKeywords(driver, cardHouseNoValue);
		Thread.sleep(3000);
		row = tableAction.returntableRow(driver, cardHouseNoValue, 3);
		Assert.assertTrue(CardToBlackList.moveBlackList(driver, row));
		Thread.sleep(2000);
		PublicPage.findKeywords(driver, cardHouseNoValue);
		Thread.sleep(3000);
		Assert.assertFalse(tableAction.isArrLoop(
				tableAction.tableRows(driver, 3), cardHouseNoValue));
		Thread.sleep(2000);
		Base.closeTab(driver);
		log.info("住户卡移出黑名单后，黑名单界面无此记录pass!");
		Reporter.log("住户卡移出黑名单后，黑名单界面无此记录pass!");
		// CardHouseManagement.openCardHouseManagementLink();
		// PublicPage.findKeywords(driver, cardHouseNoValue);
		// Thread.sleep(2000);
		// row = tableAction.returntableRow(driver, cardHouseNoValue, 2);
		// Assert.assertTrue(PublicPage.delete(driver, row, 12));
		// log.info("删除住户卡pass!");
		// Reporter.log("删除住户卡pass!");
		//Base.closeTab(driver);
		// 打卡门禁卡-------
		Thread.sleep(2000);
		accCard.openAccCardLink();
		// 输入门禁卡号查询
		Thread.sleep(2000);
		accCard.findKeywords(driver, cardHouseNoValue);
		log.info("住户卡，门禁卡界面查找成功");
		Reporter.log("住户卡，门禁卡界面查找成功!");
		Thread.sleep(4000);
		row = tableAction.returntableRow(driver, cardHouseNoValue, 1);
		Assert.assertTrue(accCard.accCardCheck(driver, "已激活", "否", "否", "否",
				row));
		log.info("住户卡!门禁卡界面核对信息pass");
		Reporter.log("住户卡!门禁卡界面核对信息pass");
		Thread.sleep(2000);
		Base.closeTab(driver);
		Thread.sleep(2000);
		// 门禁卡核对结束--------
		CardHouseManagement.openCardHouseManagementLink();
		Thread.sleep(2000);
		CardHouseManagement.findKeywords(driver, cardHouseNoValue);
		Thread.sleep(3000);
		row = tableAction.returntableRow(driver, cardHouseNoValue, 3);
		Assert.assertTrue(PublicPage.delete(driver, row, 13));
		log.info("删除住户卡pass!");
		Reporter.log("删除住户卡pass!");
		Base.closeTab(driver);
		Thread.sleep(2000);
		// 打卡门禁卡-------
		accCard.openAccCardLink();
		// 输入门禁卡号查询
		Thread.sleep(2000);
		accCard.findKeywords(driver, cardHouseNoValue);
		Thread.sleep(4000);
		row = tableAction.returntableRow(driver, cardHouseNoValue, 1);
		Assert.assertFalse(accCard.isAccCard(driver, cardHouseNoValue, row));
		log.info("住户卡删除pass!门禁卡无此住户记录");
		Reporter.log("住户卡删除pass!门禁卡无此住户记录!");
		Thread.sleep(3000);
		Base.closeTab(driver);
		Thread.sleep(2000);
		// 门禁卡核对结束--------
	}

	@Test(description = "blackList for lzmh system")
	@Parameters({ "communityNameValue", "cardBalackNoValue",
			"cardContactValue", "dateValue", "cardTypeNameValue" })
	public void blackListAction(String communityNameValue,
			String cardBalackNoValue, String cardContactValue,
			String dateValue, String cardTypeNameValue)
			throws InterruptedException {
		// 点击左边菜单物业中心
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		Thread.sleep(2000);
		CardHouseManagement.openCardHouseManagementLink();
		Thread.sleep(2000);
		Assert.assertTrue(CardHouseManagement.addCardHouse(driver,
				communityNameValue, cardBalackNoValue, cardContactValue,
				dateValue, cardTypeNameValue));
		Thread.sleep(3000);
		Base.closeTab(driver);
		// 打卡门禁卡-------
		Thread.sleep(2000);
		accCard.openAccCardLink();
		// 输入门禁卡号查询
		Thread.sleep(2000);
		accCard.findKeywords(driver, cardBalackNoValue);
		log.info("住户卡添加pass!门禁卡界面查找成功");
		Reporter.log("住户卡pass!门禁卡界面查找成功!");
		Thread.sleep(4000);
		row = tableAction.returntableRow(driver, cardBalackNoValue, 1);
		Assert.assertTrue(accCard.accCardCheck(driver, "已激活", "否", "否", "否",
				row));
		log.info("住户卡添加pass!门禁卡界面核对信息成功");
		Reporter.log("住户卡pass!门禁卡界面核对信息成功!");
		Thread.sleep(2000);
		Base.closeTab(driver);
		Thread.sleep(2000);
		// 门禁卡核对结束--------
		// 进入住户卡查找
		CardHouseManagement.openCardHouseManagementLink();
		Thread.sleep(2000);
		CardHouseManagement.findKeywords(driver, cardBalackNoValue);
		Thread.sleep(3000);
		Assert.assertTrue(tableAction.isArrLoop(
				tableAction.tableRows(driver, 3), cardBalackNoValue));
		log.info("住户卡添加pass!并查找成功");
		Reporter.log("住户卡pass!并查找成功!");
		row = tableAction.returntableRow(driver, cardBalackNoValue, 3);
		Thread.sleep(2000);
		// 移到黑名单并判断移走后本页无此记录，到黑名单页面查找可以查找到此条记录
		Assert.assertTrue(CardHouseManagement.cardToBalackList(driver, row));
		Thread.sleep(2000);
		CardHouseManagement.findKeywords(driver, cardBalackNoValue);
		Thread.sleep(3000);
		Assert.assertFalse(tableAction.isArrLoop(
				tableAction.tableRows(driver, 3), cardBalackNoValue));
		log.info("住户卡移到黑名单后，住户卡界面无此记录pass!");
		Reporter.log("住户卡移到黑名单后，住户卡无此记录pass!");
		Thread.sleep(2000);
		Base.closeTab(driver);
		Thread.sleep(2000);
		// 打卡门禁卡-------
		accCard.openAccCardLink();
		// 输入门禁卡号查询
		Thread.sleep(2000);
		accCard.findKeywords(driver, cardBalackNoValue);
		log.info("住户卡移入黑名单后门禁卡界面查找成功");
		Reporter.log("住户卡移入黑名单后门禁卡界面查找成功!");
		Thread.sleep(4000);
		row = tableAction.returntableRow(driver, cardBalackNoValue, 1);
		Assert.assertTrue(accCard.accCardCheck(driver, "已激活", "否", "否", "是",
				row));
		log.info("住户卡移入黑名单后,门禁卡界面核对信息成功");
		Reporter.log("住户卡移入黑名单后,门禁卡界面核对信息成功!");
		Thread.sleep(2000);
		Base.closeTab(driver);
		Thread.sleep(2000);
		// 门禁卡核对结束--------
		// 进入黑名单
		CardToBlackList.openCardToBlackListLink();
		Thread.sleep(2000);
		PublicPage.findKeywords(driver, cardBalackNoValue);
		Thread.sleep(3000);
		row = tableAction.returntableRow(driver, cardBalackNoValue, 3);
		Assert.assertTrue(PublicPage.delete(driver, row, 8));
		Thread.sleep(2000);
		PublicPage.findKeywords(driver, cardBalackNoValue);
		Thread.sleep(3000);
		Assert.assertFalse(tableAction.isArrLoop(
				tableAction.tableRows(driver, 3), cardBalackNoValue));
		log.info("移入黑名单后，删除pass!");
		Reporter.log("移入黑名单后，删除pass!");
		Thread.sleep(2000);
		Base.closeTab(driver);
		Thread.sleep(2000);
		// 打卡门禁卡-------
		accCard.openAccCardLink();
		Thread.sleep(2000);
		// 输入门禁卡号查询
		accCard.findKeywords(driver, cardBalackNoValue);
		Thread.sleep(4000);
		row = tableAction.returntableRow(driver, cardBalackNoValue, 1);
		Assert.assertFalse(accCard.isAccCard(driver, cardBalackNoValue, row));
		log.info("门禁卡界面无此黑卡");
		Reporter.log("门禁卡界面无此黑卡");
		Thread.sleep(2000);
		Base.closeTab(driver);
		Thread.sleep(2000);
		// 门禁卡核对结束--------
	}

	@Test(description = "expireCard for lzmh system")
	@Parameters({ "communityNameValue", "expireCardNoValue",
			"cardContactValue", "expireDateValue", "phoneValue",
			"cardTypeNameValue" })
	public void expireCardAction(String communityNameValue,
			String expireCardNoValue, String cardContactValue,
			String expireDateValue, String phoneValue, String cardTypeNameValue)
			throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		Thread.sleep(2000);
		CardHouseManagement.openCardHouseManagementLink();
		Thread.sleep(2000);
		Assert.assertTrue(CardHouseManagement.addCardHouse(driver,
				communityNameValue, expireCardNoValue, cardContactValue,
				expireDateValue, cardTypeNameValue));
		Thread.sleep(2000);
		CardHouseManagement.findKeywords(driver, expireCardNoValue);
		Thread.sleep(3000);
		Assert.assertFalse(tableAction.isArrLoop(
				tableAction.tableRows(driver, 3), expireCardNoValue));
		log.info("过期卡添加pass!并查找无此记录成功");
		Reporter.log("过期卡pass!并查找无此记录成功!");
		Base.closeTab(driver);
		Thread.sleep(2000);
		// 打卡门禁卡-------
		accCard.openAccCardLink();
		// 输入门禁卡号查询
		Thread.sleep(2000);
		accCard.findKeywords(driver, expireCardNoValue);
		log.info("过期住户卡，门禁卡界面查找成功");
		Reporter.log("过期住户卡，门禁卡界面查找成功!");
		Thread.sleep(4000);
		row = tableAction.returntableRow(driver, expireCardNoValue, 1);
		Assert.assertTrue(accCard.accCardCheck(driver, "已激活", "是", "否", "否",
				row));
		log.info("过期住户卡添加pass!门禁卡界面核对信息成功");
		Reporter.log("过期住户卡添加pass!门禁卡界面核对信息成功!");
		Thread.sleep(2000);
		Base.closeTab(driver);
		Thread.sleep(2000);
		// 门禁卡核对结束--------
		// 进入过期卡，查找，重启，验证
		ExpireCardManagement.openCardToBlackListLink();
		Thread.sleep(2000);
		ExpireCardManagement.findKeywords(driver, expireCardNoValue);
		Thread.sleep(3000);
		Assert.assertTrue(tableAction.isArrLoop(
				tableAction.tableRows(driver, 3), expireCardNoValue));
		log.info("过期卡界面查找过期卡pass!");
		Reporter.log("过期卡界面查找过期卡pass!");
		row = tableAction.returntableRow(driver, expireCardNoValue, 3);
		Assert.assertTrue(ExpireCardManagement.cardExpireEnable(driver, row));
		Thread.sleep(2000);
		ExpireCardManagement.findKeywords(driver, expireCardNoValue);
		Thread.sleep(3000);
		Assert.assertFalse(tableAction.isArrLoop(
				tableAction.tableRows(driver, 3), expireCardNoValue));
		log.info("过期卡重启成功，本页无此记录pass!");
		Reporter.log("过期卡重启成功，本页无此记录pass!");
		Base.closeTab(driver);
		Thread.sleep(2000);
		// 打卡门禁卡-------
		accCard.openAccCardLink();
		Thread.sleep(2000);
		// 输入门禁卡号查询
		accCard.findKeywords(driver, expireCardNoValue);
		log.info("重启过期住户卡，门禁卡界面查找成功");
		Reporter.log("重启过期住户卡，门禁卡界面查找成功!");
		Thread.sleep(4000);
		row = tableAction.returntableRow(driver, expireCardNoValue, 1);
		Assert.assertTrue(accCard.accCardCheck(driver, "已激活", "否", "否", "否",
				row));
		log.info("重启过期住户卡pass!门禁卡界面核对信息成功");
		Reporter.log("重启过期住户卡pass!门禁卡界面核对信息成功!");
		Thread.sleep(2000);
		Base.closeTab(driver);
		Thread.sleep(2000);
		// 门禁卡核对结束--------
		// 进入住户卡界面验证是否重启成功，并使其再次成为过期卡，删除
		CardHouseManagement.openCardHouseManagementLink();
		Thread.sleep(2000);
		CardHouseManagement.findKeywords(driver, expireCardNoValue);
		Thread.sleep(3000);
		Assert.assertTrue(tableAction.isArrLoop(
				tableAction.tableRows(driver, 3), expireCardNoValue));
		log.info("住户卡界面验证重启过期卡pass!");
		Reporter.log("住户卡界面验证重启过期卡pass!");
		row = tableAction.returntableRow(driver, expireCardNoValue, 3);
		Assert.assertTrue(CardHouseManagement.editExpireCard(driver,
				phoneValue, expireCardNoValue, row, expireDateValue));
		Thread.sleep(2000);
		CardHouseManagement.findKeywords(driver, expireCardNoValue);
		Thread.sleep(3000);
		Assert.assertFalse(tableAction.isArrLoop(
				tableAction.tableRows(driver, 3), expireCardNoValue));
		log.info("编辑住户卡使其变成过期卡pass!");
		Reporter.log("编辑住户卡使其变成过期卡pass!");
		Thread.sleep(2000);
		Base.closeTab(driver);
		Thread.sleep(2000);
		// 进入过期卡界面
		ExpireCardManagement.openCardToBlackListLink();
		Thread.sleep(2000);
		ExpireCardManagement.findKeywords(driver, expireCardNoValue);
		Thread.sleep(3000);
		Assert.assertTrue(tableAction.isArrLoop(
				tableAction.tableRows(driver, 3), expireCardNoValue));
		row = tableAction.returntableRow(driver, expireCardNoValue, 3);
		Thread.sleep(2000);
		Assert.assertTrue(PublicPage.delete(driver, row, 10));
		log.info("过期卡，删除pass!");
		Reporter.log("过期卡，删除pass!");
		Thread.sleep(2000);
		Base.closeTab(driver);
		Thread.sleep(2000);
		// 打卡门禁卡-------
		accCard.openAccCardLink();
		// 输入门禁卡号查询
		Thread.sleep(2000);
		accCard.findKeywords(driver, expireCardNoValue);
		Thread.sleep(4000);
		row = tableAction.returntableRow(driver, expireCardNoValue, 1);
		Assert.assertFalse(accCard.isAccCard(driver, expireCardNoValue, row));
		log.info("门禁卡界面无此过期卡");
		Reporter.log("门禁卡界面无此过期卡!");
		Thread.sleep(3000);
		Base.closeTab(driver);
		Thread.sleep(2000);
		// 门禁卡核对结束--------
	}

	@Test(description = "login for lzmh system")
	@Parameters({ "equipmentNameValue",
			"communityNameValue","equipmentType" })
	public void equipmentAction(String equipmentNameValue,
			 String communityNameValue,String equipmentType)
			throws InterruptedException {
		// 点击左边菜单设备管理
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(2000);
		Assert.assertTrue(PublicPage.clickLeftListAction(driver, 0));
		Thread.sleep(2000);
		EquipmentManagement.openEquipmentStatusLink();
		Thread.sleep(3000);
		PublicPage.findKeywords(driver, equipmentNameValue);
		Thread.sleep(3000);
		Assert.assertTrue(tableAction.isArrLoop(
				tableAction.tableRows(driver, 3), communityNameValue));
		Assert.assertTrue(tableAction.isArrLoop(
				tableAction.tableRows(driver, 6), "在线"));
		log.info("查找成功并设备在线");
		Reporter.log("查找成功并设备在线!");
		row = tableAction.returntableRow(driver, equipmentNameValue, 2);
		Thread.sleep(2000);
		Assert.assertTrue(EquipmentManagement.checkWyEquipment(driver,
				communityNameValue,equipmentType, row));
		log.info("核对设备pass!");
		Reporter.log("核对设备pass!");
		Thread.sleep(2000);
		Base.closeTab(driver);
	}

	@Test(description = "clear for lzmh system")
	@Parameters({"cardTypeNameValue"})
	public void deleteAction(String cardTypeNameValue)
			throws InterruptedException {
		// 打开门禁管理
		// 打开住户卡
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(2000);
		CardType.openCardTypeLink();
		Thread.sleep(2000);
		PublicPage.findKeywords(driver, cardTypeNameValue);
		Thread.sleep(3000);
		Assert.assertTrue(tableAction.isArrLoop(
				tableAction.tableRows(driver, 2), cardTypeNameValue));
		log.info("卡类型查找成功");
		Reporter.log("卡类型查找成功!");
		row = tableAction.returntableRow(driver, cardTypeNameValue, 2);
		Assert.assertTrue(PublicPage.delete(driver, row, 4));
		log.info("删除卡类型pass!");
		Reporter.log("删除卡类型pass!");
		Thread.sleep(2000);
		Base.closeTab(driver);
	}

	@BeforeTest
	public void setUp() {
		//this.driver = new FirefoxDriver();
		 //selenium形式	
		this.driver = new EventFiringWebDriver(Browser.getRemoteDriver(new Browser("chrome"))).register(new SeleniumEventListener());
		Page.initAllPages(driver);
		this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		this.driver.manage().window().maximize();
		init(driver);
	}

	@AfterTest
	public void tearDown() {
		this.driver.close();
	}

}
