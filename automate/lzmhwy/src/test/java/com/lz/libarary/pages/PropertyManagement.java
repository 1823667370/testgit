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
 * @description: 社区资源管理平台-物业中心-物业管理处页面类
 * @author: songer.xing
 * @date: 2016-6-11
 * @history:
 */
public class PropertyManagement extends Page {

	@FindBy(linkText = "物业管理处")
	private static WebElement propertyLink;
	// 物业管理处>添加物业按钮
	@FindBy(className = "add")
	private static WebElement addPropertyBtn;
	// 物业管理处>添加物业>名称
	@FindBy(name = "property.name")
	private static WebElement propertyName;
	// 物业管理处>添加物业>联系人
	@FindBy(name = "property.contacts")
	private static WebElement propertyContacts;
	// 物业管理处>添加物业>联系地址
	@FindBy(name = "property.adress")
	private static WebElement propertyAdress;
	// 物业管理处>添加物业>联系方式
	@FindBy(name = "property.phone")
	private static WebElement propertyPhone;
	// 物业管理处>添加物业>备注
	@FindBy(name = "property.remarks")
	private static WebElement propertyRemarks;
	// 物业管理处>添加物业>保存
	@FindBy(className = "buttonContent")
	private static List<WebElement> saveBtn;
	// 物业管理处>关键词
	@FindBy(className = "textInput")
	private static List<WebElement> keywords;
	@FindBy(className = "buttonSeacher")
	private static List<WebElement> seachBtn;

	static Logger log = LogManager.getLogger(PropertyManagement.class.getCanonicalName());

	public static void openPropertyLink() {

		propertyLink.click();

	}

	public static void addProperty(WebDriver driver, String propertyNameValue,
			String propertyContactsValue, String propertyAdressValue,
			String propertyPhoneValue, String propertyRemarksValue)
			throws InterruptedException {
		// 等待添加小区按钮出现
		boolean isSunccess = true;
		try {
			new WebDriverWait(driver, 15).until(ExpectedConditions
					.presenceOfElementLocated(By.className("add")));
			addPropertyBtn.click();
			Thread.sleep(2000);
			propertyName.sendKeys(propertyNameValue);
			propertyContacts.sendKeys(propertyContactsValue);
			propertyAdress.sendKeys(propertyAdressValue);
			propertyPhone.sendKeys(propertyPhoneValue);
			propertyRemarks.sendKeys(propertyRemarksValue);
			Thread.sleep(2000);
			saveBtn.get(0).click();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			takeScreenShot();
			if (saveBtn.get(1) != null) {
				log.info("新增物业失败，将关闭对话框.....");
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

	public static boolean editProperty(WebDriver driver,
			String editPropertyRemarksValue, int row)
			throws InterruptedException {
		// 等待添加小区按钮出现
		boolean isSunccess = true;
		try {
			new WebDriverWait(driver, 15).until(ExpectedConditions
					.presenceOfElementLocated(By.className("add")));
			// 找到指定值行的修改link
			String xpath = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[6]";
			WebElement tableCell = driver.findElement(By.xpath(xpath));
			WebElement editTableCell = tableCell.findElement(By.linkText("编辑"));
			editTableCell.click();
			Thread.sleep(2000);
			propertyRemarks.clear();
			propertyRemarks.sendKeys(editPropertyRemarksValue);
			Thread.sleep(2000);
			saveBtn.get(0).click();
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
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("alertMsg.close()");
			Thread.sleep(2000);
			String editXpath = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[5]";
			WebElement tableCellText = driver.findElement(By.xpath(editXpath));
			System.out.println("-------" + tableCellText.getText());
			if (tableCellText.getText().equals(editPropertyRemarksValue)) {
				return true;
			}
		}
		return false;
	}

}
