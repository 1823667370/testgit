package com.lz.libarary.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
/**
 * @version:1.0
 * @description:
 * @author songer.xing
 * @date:2017-4-18
 * @history:
 */

public class SeleniumEventListener implements WebDriverEventListener {
	private String locator = null;
	static Logger log = LogManager.getLogger(SeleniumEventListener.class.getCanonicalName());

	@Override
	public void beforeNavigateTo(String url, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterNavigateTo(String url, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeFindBy(final By by, WebElement element, WebDriver driver) {
		try {
			WebDriverWait wait = new WebDriverWait(driver,
					15);
			wait.until(new ExpectedCondition<WebElement>() {
				@Override
				public WebElement apply(WebDriver driver) {
					return driver.findElement(by);
				}
				
			}).isDisplayed();
			log.info("beforeFindBy:seaching............"
					+ driver.findElement(by));
		} catch (Exception e) {
			try {
				log.error("beforeFindBy监听15秒" + " " + by + "不可见");
			} catch (Exception e2) {
				log.error("beforeFindBy监听15秒,by不能按格式切割！");
			}
		}

	}

	@Override
	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeClickOn(WebElement element, WebDriver driver) {
//		try {
//			// 不需要element判空，为空是不会进入此方法，能进入此方法，元素肯定是对的
//			log.info("beforeClickOn:Clicking............"
//					+ splitElement(element));
//		} catch (Exception e) {
//			// TODO: handle exception
//			log.error("element不能按格式切割！");
//		}
//		
	}

	@Override
	public void afterClickOn(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onException(Throwable throwable, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}
	
	private String splitElement(WebElement element) {
		String str = element.toString().split("-> ")[1];
		return str.substring(0, str.length() - 1);
	}

}
