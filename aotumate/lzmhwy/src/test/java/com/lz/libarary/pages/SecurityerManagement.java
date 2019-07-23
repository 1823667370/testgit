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
 * @description: 社区资源管理平台-物业中心-住户管理处页面类
 * @author: songer.xing
 * @date: 2016-6-22
 * @history:
 */
public class SecurityerManagement extends Page {

	@FindBy(linkText = "保安管理")
	private static WebElement securityerLink;
	// 保安管理>添加保安按钮
	@FindBy(className = "add")
	private static WebElement addSecurityerBtn;
	// 保安管理>添加保安>选择小区 省
	@FindBy(css = "select.combox.required")
	private static List<WebElement> securityerArea;
	// 保安管理>添加保安>选择小区 市
	@FindBy(id = "securityer_city_id")
	private static WebElement securityerCity;
	// 保安管理>添加保安>选择小区 区
	@FindBy(id = "securityer_county_id")
	private static WebElement securityerCounty;
	// 保安管理>添加保安>选择小区 小区名称
	@FindBy(id = "securityer_community_id")
	private static WebElement securityerCommunity;
	// 保安管理>添加保安>手机号
	@FindBy(name = "securityer.mobile_phone")
	private static List<WebElement> securityerPhone;
	// 保安管理>添加保安>手机号
	@FindBy(name = "securityer.name")
	private static WebElement securityerName;
	// 保安管理>添加保安>呼叫按键
	@FindBy(name = "securityer.call_key")
	private static WebElement securityerKey;
	// 保安管理>添加保安>保存
	@FindBy(className = "buttonContent")
	private static List<WebElement> saveBtn;
	// 保安管理>关键词
	@FindBy(className = "textInput")
	private static List<WebElement> keywords;
	// 保安管理>编辑住户>有效周期radio
	@FindBy(name = "resident.cycle_type")
	private static List<WebElement> cycleType;
	// 住户管理>编辑住户>有效时段radio
	@FindBy(name = "resident.time_type")
	private static List<WebElement> timeType;
	static Logger log = LogManager.getLogger(SecurityerManagement.class.getCanonicalName());

	public static void openSecurityerLink() {

		securityerLink.click();

	}

	public static void addSecurityer(WebDriver driver,
			String securityerPhoneValue, String securityerKeyValue,
			String securityerNameValue,String communityNameValue) throws InterruptedException {
		// 等待添加小区按钮出现
		boolean isSunccess = true;
		try {
			new WebDriverWait(driver, 15).until(ExpectedConditions
					.presenceOfElementLocated(By.className("add")));
			addSecurityerBtn.click();
			Thread.sleep(2000);
			Select selectArea = new Select(securityerArea.get(0));
			selectArea.selectByValue("110000");
			Select selectCity = new Select(securityerCity);
			selectCity.selectByValue("110100");
			Select selectCounty = new Select(securityerCounty);
			selectCounty.selectByValue("110101");
			Select selectCommunity = new Select(securityerCommunity);
			selectCommunity.selectByVisibleText(communityNameValue);
			Thread.sleep(2000);
			securityerPhone.get(2).sendKeys(securityerPhoneValue);
			new WebDriverWait(driver, 20).until(ExpectedConditions
					.presenceOfElementLocated(By.name("securityer.name")));
			Thread.sleep(1000);
			if (securityerName.getText().equals("")
					|| securityerName.getText().equals(null)) {
				securityerName.sendKeys(securityerNameValue);
			}
			securityerKey.sendKeys(securityerKeyValue);
			Thread.sleep(1000);
			saveBtn.get(0).click();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			takeScreenShot();
			if (saveBtn.get(1) != null) {
				log.info("新增保安失败，将关闭对话框.....");
				System.out.println("--------" + saveBtn.get(1));
				saveBtn.get(1).click();
				Base.closeTab(driver);
			}
			isSunccess = false;
		}
		if (isSunccess) {
			new WebDriverWait(driver, 20)
					.until(ExpectedConditions.presenceOfElementLocated(By
							.cssSelector("div.msg.msgMsg")));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("alertMsg.close()");
		}
	}

}
