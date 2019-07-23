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
 * @description: 社区资源管理平台-门禁管理-公卡页面类
 * @author: songer.xing
 * @date: 2016-07-04
 * @history:
 */
public class CardPublicManagement extends Page {

	@FindBy(linkText = "公卡管理")
	private static WebElement cardPublicManagementLink;
	// 公卡管理>添加公卡按钮
	@FindBy(className = "add")
	private static WebElement addCardPublicBtn;
	// 公卡管理>添加公卡>选择小区
	@FindBy(name = "card.community_id")
	private static List<WebElement> cardCommunity;
	// 公卡管理>添加公卡>加载单元按钮
	@FindBy(css = "button.loadCommunityUnitTree.h_buttonChange")
	private static WebElement loadCommunityUnitTree;
	// 公卡管理>添加公卡>右侧树形小区 单元
	@FindBy(id = "tree_communityUnitTreeList_4_check")
	private static WebElement treeList4;
	// 公卡管理>添加公卡>读取卡号按钮
	@FindBy(id = "cadePublicInpDialog")
	private static WebElement readCardBtn;
	// 公卡管理>添加公卡>读卡模式radio
	@FindBy(name = "read_card_add_type")
	private static List<WebElement> readCardType;
	// 公卡管理>添加公卡>卡种类
	@FindBy(id = "read_card_type_2")
	private static WebElement cardType;
	// 住户卡管理>添加住户卡>卡种类
	@FindBy(name = "card.card_type_id")
	private static List<WebElement> wyCardType;
	// 公卡管理>添加公卡>卡号
	@FindBy(id = "read_card_caccount_2")
	private static WebElement cardNo;
	// 公卡管理>添加公卡>公卡 姓名
	@FindBy(name = "card.name")
	private static List<WebElement> cardName;
	// 公卡管理>添加公卡>公卡 姓名
	@FindBy(name = "card.contact")
	private static WebElement cardContact;
	// 公卡管理>添加公卡>卡类型
	@FindBy(name = "card.card_type_name")
	private static WebElement cardTypeName;
	// 公卡管理>查询按钮
	@FindBy(css = "button[class='buttonSeacher'][type='submit']")
	private static WebElement seachBtn;
	// 公卡管理>编辑link
	@FindBy(linkText = "编辑")
	private static WebElement editLink;
	// 公卡管理>编辑link
	@FindBy(name = "card.card_status")
	private static WebElement cardStatus;
	// 公卡管理>添加住户>保存
	@FindBy(className = "buttonContent")
	private static List<WebElement> saveBtn;
	// 公卡管理>关键词
	@FindBy(name = "card.card_num")
	private static WebElement keywords;
	static Logger log = LogManager.getLogger(CardPublicManagement.class.getCanonicalName());

	public static void openCardPublicManagementLink() {

		cardPublicManagementLink.click();

	}

	public static boolean addCardPublic(WebDriver driver,
			String communityNameValue, String cardNoValue,
			String cardContactValue, String cardTypeNameValue)
			throws InterruptedException {
		// 等待添加小区按钮出现
		boolean isSunccess = true;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			new WebDriverWait(driver, 15).until(ExpectedConditions
					.presenceOfElementLocated(By.className("add")));
			addCardPublicBtn.click();
			Thread.sleep(3000);
			Select community = new Select(cardCommunity.get(2));
			community.selectByVisibleText(communityNameValue);
			loadCommunityUnitTree.click();
			Thread.sleep(4000);
			treeList4.click();
			Thread.sleep(2000);
			readCardBtn.click();
			Thread.sleep(2000);
			if (PublicPage.readCardNo(driver, cardNoValue)) {
				Thread.sleep(2000);
				Select cardType = new Select(wyCardType.get(2));
				cardType.selectByVisibleText(cardTypeNameValue);
				js.executeScript("document.getElementById('public_cycle_start').value='2016-07-01'");
				js.executeScript("document.getElementById('public_cycle_end').value='2018-07-01'");
				cardName.get(2).sendKeys(cardContactValue);
				saveBtn.get(0).click();
				Thread.sleep(2000);
			} else {
				takeScreenShot();
				saveBtn.get(1).click();
				Base.closeTab(driver);
				isSunccess = false;
				return false;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			takeScreenShot();
			if (saveBtn.get(3) != null) {
				log.info("读取卡号失败，将关闭对话框.....");
				System.out.println("--------" + saveBtn.get(1));
				saveBtn.get(3).click();

			}
			if (saveBtn.get(1) != null) {
				log.info("新增公卡失败，将关闭对话框.....");
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
			Thread.sleep(1000);
			js.executeScript("alertMsg.close()");
		}
		return true;
	}

	public static boolean editCardPublic(WebDriver driver, String phoneValue,
			String cardNoValue, String cardTypeNameValue, int row)
			throws InterruptedException {
		boolean isSunccess = true;
		Thread.sleep(2000);
		try {
			// 等待添加小区按钮出现
			new WebDriverWait(driver, 15).until(ExpectedConditions
					.presenceOfElementLocated(By.className("add")));
			String statusXpath = "//*[@class='gridTbody']/table/tbody/tr["
					+ row + "]/td[10]";
			WebElement statusText = driver.findElement(By.xpath(statusXpath));
			if (!(statusText.getText().equals("已激活"))) {
				Base.closeTab(driver);
				return false;
			}
			// 找到指定值行的修改link
			String xpath = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[13]";
			WebElement tableCell = driver.findElement(By.xpath(xpath));
			WebElement editTableCell = tableCell.findElement(By.linkText("编辑"));
			editTableCell.click();
			Thread.sleep(2000);
			cardContact.sendKeys(phoneValue);
			// 未激活
			Select selectStatus = new Select(cardStatus);
			selectStatus.selectByValue("2");
			saveBtn.get(0).click();
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			takeScreenShot();
			if (saveBtn.get(1) != null) {
				log.info("编辑公卡失败，将关闭对话框.....");
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
			Thread.sleep(1000);
			js.executeScript("alertMsg.close()");
			Thread.sleep(2000);
			String editXpath = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[3]";
			WebElement tableCellText = driver.findElement(By.xpath(editXpath));
			// 手机号
			String phoneXpath = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[5]";
			WebElement phoneText = driver.findElement(By.xpath(phoneXpath));
			// 卡类型
			String cardTypeXpath = "//*[@class='gridTbody']/table/tbody/tr["
					+ row + "]/td[6]";
			WebElement cardTypeXpathText = driver.findElement(By
					.xpath(cardTypeXpath));
			// 卡种类
			String typeXpath = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[7]";
			WebElement typeText = driver.findElement(By.xpath(typeXpath));
			// 卡状态
			String cardStateXpath = "//*[@class='gridTbody']/table/tbody/tr["
					+ row + "]/td[10]";
			WebElement cardStateText = driver.findElement(By
					.xpath(cardStateXpath));
			// 卡修改时间
			String editTimeXpath = "//*[@class='gridTbody']/table/tbody/tr["
					+ row + "]/td[12]";
			WebElement editTimeText = driver.findElement(By
					.xpath(editTimeXpath));
			log.info("-------" + tableCellText.getText());
			log.info("-------" + phoneText.getText());
			log.info("-------" + cardTypeXpathText.getText());
			log.info("-------" + typeText.getText());
			log.info("-------" + cardStateText.getText());
			log.info("-------" + editTimeText.getText().equals(""));
			if (tableCellText.getText().equals(cardNoValue)
					&& phoneText.getText().equals(phoneValue)
					&& cardTypeXpathText.getText().equals(cardTypeNameValue)
					&& typeText.getText().equals("IC卡")
					&& cardStateText.getText().equals("未激活")
					&& !(editTimeText.getText().equals(""))) {
				return true;
			}
		}
		Base.closeTab(driver);
		return false;
	}

	public static boolean cardToBalackList(WebDriver driver, int row)
			throws InterruptedException {
		// 等待添加小区按钮出现
		new WebDriverWait(driver, 15).until(ExpectedConditions
				.presenceOfElementLocated(By.className("add")));
		// 找到指定值行的修改link
		try {
			String xpath = "//*[@class='gridTbody']/table/tbody/tr[" + row
					+ "]/td[13]";
			WebElement tableCell = driver.findElement(By.xpath(xpath));
			WebElement deleteTableCell = tableCell.findElement(By
					.linkText("移至黑名单"));
			deleteTableCell.click();
			Thread.sleep(2000);
			driver.findElement(By.className("button")).click();
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
		Base.closeTab(driver);
		return false;
	}
	
	public static void findKeywords(WebDriver driver, String keywordsValue)
			throws InterruptedException {
		try {
			Thread.sleep(3000);
			keywords.clear();
			keywords.sendKeys(keywordsValue);
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
