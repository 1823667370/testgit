package com.lz.libarary.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * @author songer.xing
 *
 */

public class UIScreenShot {

	WebDriver driver;
	
	Log log = LogFactory.getLog(this.getClass());

	public WebDriver getDriver() {
		return driver;
	}

	/**
	 * init test case
	 * 
	 * @param driver
	 */
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public void init(WebDriver driver) {
		log.info("Start WebDriver");
		setDriver(driver);
	}

	/**
	 * stop webdriver
	 * 
	 * @param driver
	 */
	public void stop() {
		log.info("Stop WebDriver");
		driver.quit();

	}

	/**
	 * @author songer.xing
	 */
	public void takeScreenShot() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd_HHmmss");
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		String dateStr = sf.format(date);
		String path = this.getClass().getSimpleName() + "_" + dateStr + ".png";
		takeScreenShot((TakesScreenshot) this.getDriver(), path);
	}
	
	public void pageSource(WebDriver driver) {
		String s=driver.getPageSource();
		//s=s.substring(s.indexOf("{"), s.indexOf("}"));	       
		Logger logger = LogManager.getLogger(UIScreenShot.class.getName());
		logger.info("当前页面的源码:"+s);
	}

	/**
	 * @author songer.xing
	 * @param drivername
	 * @param path
	 */
	public void takeScreenShot(TakesScreenshot drivername, String path) {
		// this method will take screen shot ,require two parameters ,one is
		// driver name, another is file name
		String currentPath = System.getProperty("user.dir"); // get current work
		log.info(currentPath);
		//将截取的屏幕以文件形式返回
		File scrFile = drivername.getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy
		try {
			log.info("save snapshot path is:" + currentPath + "/resultImage" + "/" + path);
			//保存
			FileUtils.copyFile(scrFile, new File(currentPath + "/" + "resultImage" + "/" + path));
//			String filePath=currentPath + "resultImage" + "\\" + path;
//			Reporter.log(filePath);
//            //这里实现把图片链接直接输出到结果文件中，通过邮件发送结果则可以直接显示图片
//			log.info("<img src=\"../" + filePath + "\"/>");
//			System.out.println("<img src=\"../" + filePath + "\"/>");
//		    Reporter.log("<img src=\"../" + filePath + "\"/>");
		} catch (Exception e) {
			log.error("Can't save screenshot");
			e.printStackTrace();
		} finally {
			log.info("screen shot finished");
		}
	}
	

}
