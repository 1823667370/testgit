package com.lz.libarary.utils;


import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;


public class jquery {
	//1、jquery Foundation官网，已加载jquery库
	//处理已经加载jquery库的页面
	//public void isJquery(WebDriver driver){
	public static void main(String[] args){
	    WebDriver driver= new FirefoxDriver();
		JavascriptExecutor jse =(JavascriptExecutor)driver;
		driver.get("http://www.jquery.org/");
		List<WebElement> elements=(List<WebElement>)jse.executeScript("return jQuery.find('.menu-item')");
		Assert.assertEquals("Members", elements.get(3).getText());
		
	}
	//处理未加载jquery库的页面
	  //加载jquery
	private void injectjQueryIfNeeded(WebDriver driver){
		if (!jQueryLoaded(driver)) {
			injectJQuery(driver);
		}
	}
	
	 /*
     * 判断是否加载jquery
     * 返回true表示已加载jquery
     */
    public Boolean jQueryLoaded(WebDriver driver){
        Boolean loaded=true;
        try{
            loaded = (Boolean)((JavascriptExecutor)driver).executeScript("return jQuery()!=null");
            System.out.println("页面本身已加载jquery");
        }catch(Exception e){
            loaded = false;
        }
        return loaded;
    }
 
    //注入jquery
    //通过添加<script>元素将jquery库注入到页面的head部分，<script>元素通过引用Google CDN(内容分发网络)来加载jquery库
    //该CDN可以从jquery官网获取，调试时可以在调试工具中输入加载jquery的javascript脚本，看提示验证是否成功
    public void injectJQuery(WebDriver driver){
        //在head中拼出加载jquery的html,固定写法
        String jquery = "var headID=document.getElementsByTagName(\"head\")[0];" +
                        "var newScript = document.createElement('script');" +
                        "newScript.type='text/javascript';" +
                        "newScript.src='http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js';" +
                        "headID.appendChild(newScript);";
        //执行js
        ((JavascriptExecutor)driver).executeScript(jquery);
        System.out.println("手动加载jquery");
    }
    
    //测试函数
//    public static void main(String args[]){
//        //加载chrome驱动
//    	jquery j=new jquery();
//    	String DriverPath = "F:\\AutoTest\\selenium\\chromedriver.exe";
//    	WebDriver  driver = new ChromeDriver();
//        System.setProperty("webdriver.chrome.driver",DriverPath);
//        driver = new ChromeDriver();
// 
//        driver.get("http://www.baidu.com");
//        j.injectjQueryIfNeeded(driver);
//        driver.navigate().to("http://weibo.kedacom.com");
//        j.injectjQueryIfNeeded(driver);
//        driver.quit();
//    }
}
