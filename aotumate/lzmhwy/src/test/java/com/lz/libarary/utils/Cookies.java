package com.lz.libarary.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

//webdriver提供一系列cookies的操作来获取、填写、删除cookies的方法，节省了多次在登录页面查找元素并填写登录信息的时间
//将获取的cookies信息保存到文件中以备后续使用
//在jsp中处理cookie数据的常用方法：
//getDomain（）；返回cookie的域名.
//getMaxAge（）；返回cookie的存活时间
//getName（）；返回cookie的名字
//getPath（）；返回cookie适用的路径
//getSecure（）；如果浏览器通过安全协议发送Cookie将返回true值，如果浏览器使用标准协议刚返回false值
//getValue（）；返回cookie的值
//getVersion（）；返回cookie所遵从的协议版本setComment(String purpose）；设置cookie的注释
//setPath(String url）；设置Cookie的适用路径
//setSecure(Boolean flag）；设置浏览器是否仅仅使用安全协议来发送cookie，例如使用Https或ssl
//setValue(String newvalue);cookie创建后设置一个新的值
//setVersion(int v）；设置cookie所遵从的协议版本
//selenium WebDriver 通过driver.manage().getCookies() 和driver.manage().addCookie(ck); 获取cookie 加载cookie
public class Cookies {
	public void getCookies(WebDriver driver) {
		File cookieFile = new File("cookies.txt");
		try {
			cookieFile.delete();
			cookieFile.createNewFile();
			FileWriter fw = new FileWriter(cookieFile);
			BufferedWriter bw = new BufferedWriter(fw);
			System.out.println("获取："+driver.manage().getCookies());
			for (Cookie cookie : driver.manage().getCookies()) {
				bw.write(cookie.getName() + ";" + cookie.getValue() + ";"
						+ cookie.getDomain() + ";" + cookie.getPath() + ";"
						+ cookie.getExpiry() + ";" + cookie.isSecure());
				bw.newLine();
			}
			bw.flush();
			bw.close();
			fw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public void addCookies(WebDriver driver) {
		try {
			File cookiesFile = new File("cookies.txt");
			FileReader fr = new FileReader(cookiesFile);
			BufferedReader br = new BufferedReader(fr);
			String line;
			while ((line = br.readLine()) != null) {
				StringTokenizer str = new StringTokenizer(line, ";");
				while (str.hasMoreTokens()) {
					String name = str.nextToken();
					String value = str.nextToken();
					String domain = str.nextToken();
					String path = str.nextToken();
					Date expiry = null;
					String dt;
					if (!(dt = str.nextToken()).equals(null)) {
						//expiry=new Date(dt);

					}
					boolean isSecure = new Boolean(str.nextToken())
							.booleanValue();
					Cookie cookie = new Cookie(name, value, domain, path, expiry,
							isSecure);
					System.out.println("读到"+cookie);
					driver.manage().addCookie(cookie);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		WebDriver driver =new FirefoxDriver();
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
//		driver.get("https://passport.csdn.net/account/login");
//		driver.findElement(By.id("username")).sendKeys("songer_xing");
//		driver.findElement(By.id("password")).sendKeys("xss484743414341");
//		driver.findElement(By.className("logging")).click();
		Cookies ck =new Cookies();
	/*	ck.getCookies(driver);
		driver.close();
		String currentWindow = driver.getWindowHandle();
		driver.switchTo().window(currentWindow).close();*/
		driver.get("https://passport.csdn.net/account/login");
		
		ck.addCookies(driver);
		driver.get("http://www.csdn.net/");
	}
}
