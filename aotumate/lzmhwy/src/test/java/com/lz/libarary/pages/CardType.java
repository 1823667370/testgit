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

import com.lz.libarary.utils.Base;

/**
 * 
 * @version 1.00.00
 * @description: 社区资源管理平台-门禁管理-卡类型页面类
 * @author: songer.xing
 * @date: 2016-07-03
 * @history:
 */
public class CardType extends Page {

	@FindBy(linkText = "卡类型")
	private static WebElement cardTypeLink;
	// 卡类型>添加卡类型按钮
	@FindBy(className = "add")
	private static WebElement addCardTypeBtn;
	// 卡类型>添加卡类型>所属物业放大镜
	@FindBy(id = "cadeTypeInputpropertyDialog")
	private static WebElement cadeTypeAddDialog;
	// 卡类型>添加卡类型>卡类型
	@FindBy(name = "cardType.name")
	private static List<WebElement> cardTypeName;
	// 卡类型>添加卡类型>有效周期
	@FindBy(name = "cardType.cycle_type")
	private static List<WebElement> cardCycletype;
	// 卡类型>添加卡类型>有效周期
	@FindBy(id = "update_cycle_date_type")
	private static WebElement cardDateType;
	// 卡类型>添加卡类型>有效时段
	@FindBy(name = "cardType.time_type")
	private static List<WebElement> cardTimeType;
	// 卡类型>查询按钮
	@FindBy(css = "button[class='buttonSeacher'][type='submit']")
	private static WebElement seachBtn;
	// 卡类型>编辑link
	@FindBy(linkText = "编辑")
	private static WebElement editLink;
	// 卡类型>添加住户>保存
	@FindBy(className = "buttonContent")
	private static List<WebElement> saveBtn;
	// 卡类型>关键词
	@FindBy(className = "textInput")
	private static List<WebElement> keywords;
	static Logger log = LogManager.getLogger(CardType.class.getCanonicalName());

	public static void openCardTypeLink() {

		cardTypeLink.click();

	}

	public static boolean addCardType(WebDriver driver, String propertyNameValue,
			String cardTypeNameValue) throws InterruptedException {
		// 等待添加小区按钮出现
		boolean isSunccess = true;
		try {
			new WebDriverWait(driver, 15).until(ExpectedConditions
					.presenceOfElementLocated(By.className("add")));
			addCardTypeBtn.click();
			cadeTypeAddDialog.click();
			if (Base.selectProperty(driver, propertyNameValue,2)) {
				cardTypeName.get(2).sendKeys(cardTypeNameValue);

				cardCycletype.get(0).isSelected();
				cardTimeType.get(0).isSelected();
				saveBtn.get(0).click();
			}else {
				takeScreenShot();
				if (saveBtn.get(1)!=null) {
					log.info("新增卡类型失败，将关闭对话框.....");
					System.out.println("--------"+saveBtn.get(1));
					saveBtn.get(1).click();	
					Base.closeTab(driver);
					isSunccess = false;
					return false;
			}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			takeScreenShot();
			if (saveBtn.get(1)!=null) {
				log.info("新增卡类型失败，将关闭对话框.....");
				System.out.println("--------"+saveBtn.get(1));
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
			return true;
		}
		return false;
	}	public static boolean addWyCardType(WebDriver driver, String propertyNameValue,
			String cardTypeNameValue) throws InterruptedException {
		// 等待添加小区按钮出现
		boolean isSunccess = true;
		try {
			new WebDriverWait(driver, 15).until(ExpectedConditions
					.presenceOfElementLocated(By.className("add")));
			addCardTypeBtn.click();
			Thread.sleep(2000);
			cardTypeName.get(2).sendKeys(cardTypeNameValue);

			cardCycletype.get(0).isSelected();
			cardTimeType.get(0).isSelected();
			saveBtn.get(0).click();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			takeScreenShot();
			if (saveBtn.get(1)!=null) {
				log.info("新增卡类型失败，将关闭对话框.....");
				System.out.println("--------"+saveBtn.get(1));
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
			return true;
		}
		return false;
	}
	
	public static boolean editCardType(WebDriver driver, String timeTypeValue,
			int row) throws InterruptedException {
		boolean isSunccess = true;
		try {
			// 等待添加小区按钮出现

			JavascriptExecutor js = (JavascriptExecutor) driver;
			// 找到指定值行的修改link
			String xpath = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[4]";
			WebElement tableCell = driver.findElement(By.xpath(xpath));
			WebElement editTableCell = tableCell.findElement(By.linkText("编辑"));
			editTableCell.click();
			Thread.sleep(2000);
			new WebDriverWait(driver, 15).until(ExpectedConditions
					.presenceOfElementLocated(By.name("cardType.cycle_type")));
			if (cardCycletype.get(0).isSelected()
					&& !(cardCycletype.get(1).isSelected())) {
				cardCycletype.get(1).click();
				Select selectCounty = new Select(cardDateType);
				selectCounty.selectByValue("1");
			} else {
				log.info("卡类型编辑界面，有效周期radio选择错误!");
				Reporter.log("卡类型编辑界面，有效周期radio选择错误!");
				saveBtn.get(1).click();	
				Base.closeTab(driver);
				return false;

			}

			if (cardTimeType.get(0).isSelected()
					&& !(cardTimeType.get(1).isSelected())) {
				cardTimeType.get(1).click();
				js.executeScript("document.getElementById('time_type_dd').children[0].children[0].value='08:00:00'");
				js.executeScript("document.getElementById('time_type_dd').children[0].children[1].value='18:00:00'");
			} else {
				log.info("卡类型编辑界面，有效时段radio选择错误!");
				Reporter.log("卡类型编辑界面，有效时段radio选择错误!");
				saveBtn.get(1).click();	
				Base.closeTab(driver);
				return false;
			}
			saveBtn.get(0).click();
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			takeScreenShot();
			if (saveBtn.get(1)!=null) {
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
			String editXpath = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[3]";
			WebElement tableCellText = driver.findElement(By.xpath(editXpath));
			System.out.println("-------" + tableCellText.getText());
			if (tableCellText.getText().equals(timeTypeValue)) {
				return true;
			}
		}
		Base.closeTab(driver);
		return false;
	}


}
